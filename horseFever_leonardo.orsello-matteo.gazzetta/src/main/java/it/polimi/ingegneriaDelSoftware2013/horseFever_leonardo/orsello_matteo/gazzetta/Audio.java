package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Classe che gestisce l'audio del gioco per ora esegue solo la soundtrack
 * 
 */
public class Audio {
	
	/**
	 * 
	 * Costruttore della classe Audio
	 *
	 * @param filename nome del file audio da riprodurre
	 * @see Audio
	 */
	public Audio(final String filename) {
		// Nuovo thread per ogni file audio riprodotto
		Thread audio = new Thread() {
			@Override
			public void run() {
				play(filename);
			}
		};
		audio.start();
	}

	/**
	 * 
	 * Riproduzione del file audio passato
	 * 
	 * @param filename
	 *            Path del file audio da riprodurre
	 * @exceptions IOException
	 */
	public void play(String filename) {
		try {
			// Input stream del file audio
			AudioInputStream in = AudioSystem.getAudioInputStream(ResourceLoader.load(filename));
			AudioInputStream din = null;
			AudioFormat baseFormat = in.getFormat();

			AudioFormat decodedFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(), Parametri.SAMPLESIZE,
					baseFormat.getChannels(), baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(), false);

			din = AudioSystem.getAudioInputStream(decodedFormat, in);
			// Play now.
			rawplay(decodedFormat, din);
			in.close();

		} catch (IOException e) {
			Write.write("ERRORE IO FILE AUDIO /"+filename);
		} catch (UnsupportedAudioFileException e) {
			Write.write("ERRORE AUDIO NON SUPPORTATO");
		} catch (LineUnavailableException e) {
			Write.write("ERRORE AUDIO");
		}

	}

	private void rawplay(AudioFormat targetFormat, AudioInputStream din)
			throws IOException, LineUnavailableException {
		byte[] data = new byte[Parametri.AUDIO_BYTE];
		SourceDataLine line = getLine(targetFormat);
		if (line != null) {
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1) {
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1) {
					nBytesWritten = line.write(data, 0, nBytesRead);
				}
			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		}
	}

	private SourceDataLine getLine(AudioFormat audioFormat)
			throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

}