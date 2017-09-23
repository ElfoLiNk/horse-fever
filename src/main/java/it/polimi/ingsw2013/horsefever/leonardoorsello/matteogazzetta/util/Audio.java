package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.Parametri;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine;
import java.io.IOException;

/**
 * Classe che gestisce l'audio del gioco per ora esegue solo la soundtrack
 */
public class Audio {
    Thread audio;

    /**
     * Costruttore della classe Audio
     *
     * @param filename nome del file audio da riprodurre
     * @see Audio
     */
    public Audio(final String filename) {
        // Nuovo thread per ogni file audio riprodotto
        audio = new Thread(() -> play(filename));
    }

    /**
     * Avvia un thread dove riproduce il file passato al costruttore della
     * classe
     *
     * @see Thread, Audio
     */
    public void start() {
        audio.start();
    }

    /**
     * Riproduzione del file audio passato
     *
     * @param filename Path del file audio da riprodurre
     */
    public void play(final String filename) {
        try {
            // Input stream del file audio
            final AudioInputStream audioIn = AudioSystem
                    .getAudioInputStream(ResourceLoader.load(filename));
            AudioInputStream decodedAudioIn;
            final AudioFormat baseFormat = audioIn.getFormat();

            final AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(), Parametri.SAMPLESIZE,
                    baseFormat.getChannels(), baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(), false);

            decodedAudioIn = AudioSystem.getAudioInputStream(decodedFormat, audioIn);
            // Play now.
            rawplay(decodedFormat, decodedAudioIn);
            audioIn.close();

        } catch (IOException e) {
            SystemOut.write("ERRORE IO FILE AUDIO " + filename);
            return;
        } catch (UnsupportedAudioFileException e) {
            SystemOut.write("ERRORE AUDIO NON SUPPORTATO");
            return;
        } catch (LineUnavailableException e) {
            SystemOut.write("ERRORE AUDIO");
            return;
        }
        // LOOP AUDIO SOUNDTRACK
        play(filename);
    }

    private void rawplay(final AudioFormat targetFormat, final AudioInputStream decodedAudioIn)
            throws IOException, LineUnavailableException {
        final byte[] data = new byte[Parametri.AUDIO_BYTE];
        final SourceDataLine line = getLine(targetFormat);
        if (line != null) {
            // Start
            line.start();
            int nBytesRead = 0;
            while (nBytesRead != -1) {
                nBytesRead = decodedAudioIn.read(data, 0, data.length);
                if (nBytesRead != -1) {
                    line.write(data, 0, nBytesRead);
                }
            }
            // Stop
            line.drain();
            line.stop();
            line.close();
            decodedAudioIn.close();
        }
    }

    private SourceDataLine getLine(final AudioFormat audioFormat)
            throws LineUnavailableException {
        SourceDataLine res;
        final DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        res.open(audioFormat);
        return res;
    }

}
