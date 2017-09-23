package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.*;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.SystemIn;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.SystemOut;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Locale;

/**
 * Classe che gestisce tutta la partita: Crea le liste dei mazzi Imposta i
 * giocatori Gestisce i turni e le corse
 */
public class Partita {
    public final String[] COLORI = {"NERO", "BLU ", "VERDE", "ROSSO", "GIALLO",
            "BIANCO"};
    private final List<Scuderia> arrivati;
    private List<Giocatore> giocatori;
    /**
     * Liste delle carte del mazzo
     */
    private List<CartaPersonaggio> cartePersonaggio;
    private List<CartaAzione> carteAzione;
    private List<String> carteMovimento;
    private int turni;
    private int ngiocatori;
    private int primogiocatore;
    private List<Scuderia> scuderie;
    /**
     * Gestione Scommesse
     */
    private int tempint;
    private List<Scuderia> classifica;

    Partita() {
        this.arrivati = new ArrayList<>();
        this.giocatori = new ArrayList<>();
        this.turni = 0;
        this.ngiocatori = 0;
        this.primogiocatore = 0;
        this.scuderie = new ArrayList<>();
        this.classifica = new ArrayList<>();
    }

    /**
     * @return the giocatori
     */
    public List<Giocatore> getGiocatori() {
        return giocatori;
    }

    /**
     * @param newGiocatori the giocatori to set
     */
    public void setGiocatori(final List<Giocatore> newGiocatori) {
        this.giocatori = newGiocatori;
    }

    /**
     * Creo liste dei Mazzi Carte
     *
     * @throws IOException
     */
    public void setListe() throws IOException {
        // Creazione mazzo Personaggi
        cartePersonaggio = CartaPersonaggio.caricaCartePersionaggio();
        // Creazione mazzo Azioni
        carteAzione = CartaAzione.caricaCarteAzione();
        // Creazione mazzo Movimento
        carteMovimento = CartaMovimento.caricaCarteMovimento();

    }

    /**
     * @return the cartePersonaggio
     */
    public List<CartaPersonaggio> getCartePersonaggio() {
        return cartePersonaggio;
    }

    /**
     * @return the carteMovimento
     */
    public List<String> getCarteMovimento() {
        return carteMovimento;
    }

    /**
     * @return the scuderie
     */
    public List<Scuderia> getScuderie() {
        return scuderie;
    }

    /**
     * @param newScuderie the scuderie to set
     */
    public void setScuderie(final List<Scuderia> newScuderie) {
        this.scuderie = newScuderie;
    }

    /**
     * @param newNgiocatori the ngiocatori to set
     */
    public void setNgiocatori(final int newNgiocatori) {
        this.ngiocatori = newNgiocatori;
    }

    /**
     * Richiesta del numero di giocatori a terminale e imposto il numero di
     * turni necessari
     *
     * @exceptions
     * @see Giocatore
     */
    public void setNumGiocatori() {
        do {
            SystemOut.write("In quanti volete giocare?");
            ngiocatori = SystemIn.readInt();

        } while (ngiocatori < Parametri.MIN_GIOCATORI
                || ngiocatori > Parametri.MAX_GIOCATORI);

        if (ngiocatori == Parametri.DUE || ngiocatori == Parametri.TRE
                || ngiocatori == Parametri.SEI) {
            turni = Parametri.SEI;
        } else if (ngiocatori == Parametri.QUATTRO) {
            turni = Parametri.QUATTRO;
        } else {
            turni = Parametri.CINQUE;
        }
    }

    /**
     * Imposto il numero di segnalini per scuderia in base al numero di
     * giocatori
     */
    public void setSegnalini() {
        for (final Scuderia scuderia : scuderie) {
            switch (ngiocatori) {
                case Parametri.DUE:
                    scuderia.setSegnalino(Parametri.UNO);
                    break;
                case Parametri.TRE:
                    scuderia.setSegnalino(Parametri.DUE);
                    break;
                case Parametri.QUATTRO:
                    scuderia.setSegnalino(Parametri.TRE);
                    turni = Parametri.CINQUE;
                    break;
                case Parametri.CINQUE:
                    scuderia.setSegnalino(Parametri.QUATTRO);
                    turni = Parametri.CINQUE;
                    break;
                case Parametri.SEI:
                    scuderia.setSegnalino(Parametri.QUATTRO);
                    turni = Parametri.CINQUE;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Costruisco il giocatore chiedendo a terminale il nome del player, gli
     * assegno una carta personaggio e modifico i soldi di conseguenza; Assegno
     * la scuderia associata alla quotazione del personaggio.
     */
    public void setGiocatori() {
        final Random rnd = new Random();
        for (int i = 0; i < ngiocatori; i++) {
            int valid;
            // Costruisco il giocatore
            final Giocatore player = new Giocatore();

            do {
                valid = 1;

                // Chiedo a terminale il nome del player
                player.setNome(i + 1);

                // Seleziono la carta personaggio del player
                tempint = rnd.nextInt(cartePersonaggio.size());
                final CartaPersonaggio personaggio = cartePersonaggio.get(tempint);

                // Assegno il nome della carta a interpreta del player
                player.setInterpreta(personaggio.getNome());

                // Assegno i corrispondenti soldi al player
                player.setSoldi(personaggio.getSoldi());

                // Assegno la corrispettiva Scuderia al player
                player.setScuderia(personaggio.getQuotazione(), scuderie);

                for (final Giocatore giocatore : giocatori) {
                    // Controllo se il nome è gia stato scelto
                    if (player.getNome().equals(giocatore.getNome())) {
                        valid = 0;
                        SystemOut.write("esiste già un altro giocatore con questo nome, cambia nome");
                        break;
                    }
                }

            } while (valid == 0);
            // Aggiungo il player alla lista di giocatori
            giocatori.add(player);

            // Elimino la carta giocatore dalla lista
            cartePersonaggio.remove(tempint);
        }
    }

    /**
     * Inizializza le Scuderie.
     */
    public void setScuderie() {
        for (int i = 0; i < Parametri.MAX_SCUDERIE; i++) {
            final Scuderia scuderia = new Scuderia(this, COLORI[i]);
            scuderie.add(scuderia);
        }
    }

    /**
     * Assegno le Scuderie alle Quotazioni in modo Random
     */
    public void setQuotazioni() {
        // lista di valori
        final List<Integer> list = new ArrayList<>();
        // Generatore random con seed l'ora
        final Random rnd = new Random(System.currentTimeMillis());

        // Continuo ad aggiungere un numero finche non trovo quello giusto
        while (list.size() < scuderie.size()) {
            final int num = rnd.nextInt(Parametri.SEI) + Parametri.DUE;
            if (!list.contains(num)) {
                list.add(num);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            scuderie.get(i).setQuotazione(list.get(i));
        }
    }

    /**
     * Associo a ogni player 1 carte azione
     */
    public void setCarteAzione() {
        // Scelgo una carta azione a caso
        final Random rnd = new Random();
        for (final Giocatore giocatore : giocatori) {
            tempint = rnd.nextInt(getCarteAzione().size());

            // Salvo la carta azione nella lista del player
            giocatore.setCarteAzione(getCarteAzione().get(tempint));

            // Elimino la carta dalla lista
            getCarteAzione().remove(tempint);
        }
    }

    /**
     * Randomizzo il primogiocatore, impostandolo uguale al indice dell'array
     * dei giocatori
     *
     * @exceptions
     * @see
     */
    public void randomPrimogiocatore() {
        final Random rnd = new Random();
        tempint = rnd.nextInt(giocatori.size());
        primogiocatore = tempint;
    }

    /**
     * Trucco la corsa con le carte azione
     *
     * @exceptions
     * @see
     */
    public void truccoCorsa() {
        int cartedagiocare = ngiocatori * 2;
        do {
            boolean finisco = true;
            for (int i = primogiocatore; i < ngiocatori && finisco; i++) {

                final Giocatore giocatore = giocatori.get(i);
                final List<CartaAzione> carteplayer = giocatore.getCarteAzione();
                SystemOut.write(giocatore.toString());
                SystemOut.write("\nScegli la carta azione da giocare:");
                // Scelta Carta Azione
                final int cartaScelta = SystemIn.readCartaAzione(carteplayer);
                // Scelta Scuderia
                SystemOut.write("\nA quale corsia vuoi applicarla?");
                SystemOut.write("\n     COLORE\tCARTE APPLICATE");
                for (final Scuderia scuderia : scuderie) {
                    SystemOut.write(scuderie.indexOf(scuderia) + " ) " + scuderia.getColore()
                            + "\t"
                            + scuderia.getCarteAzione().size());
                }
                int scuderiaScelta;
                do {
                    SystemOut.write("Seleziona corsia: ");
                    scuderiaScelta = SystemIn.readInt();
                } while (scuderiaScelta < 0 || scuderiaScelta > scuderie.size() - 1);

                // Salvo la carta nella scuderia corrispondente
                scuderie.get(scuderiaScelta).addCarteAzione(carteplayer.get(cartaScelta));

                // Rimuovo Carta dalla lista del player
                giocatore.removeCarteAzione(cartaScelta);

                // Clear console
                SystemOut.clear();

                // Check per ciclare tutti i giocatori
                if (i == ngiocatori - 1 && primogiocatore > 0) {
                    i = -1;
                } else if (i == primogiocatore - 1) {
                    finisco = false;
                }
                cartedagiocare--;
            }
        } while (cartedagiocare > 0);
    }

    /**
     * Controlla se tutte le scuderie sono arrivate alla fine della corsa
     *
     * @return true se sono tutte arrivate, false se non sono ancora tutte
     * arrivate
     * @see
     */
    public boolean checkArrivati() {
        return scuderie.stream().filter(Scuderia::isArrivato).count() == scuderie.size();
    }

    /**
     * Leggo una carta movimento e assegno un movimento ad ogni scuderia
     *
     * @exceptions
     * @see
     */
    public void movimento() {
        // Carta movimento
        int[] movimenti = new int[COLORI.length];

        // Selezione una linea random dalla Lista delle carte movimento
        final Random rnd = new Random();
        final int indiceCarta = rnd.nextInt(carteMovimento.size());
        final String randomString = carteMovimento.get(indiceCarta);

        // Elimino la linea dalla lista
        carteMovimento.remove(indiceCarta);

        // Analizzo la stringa e Salvo il movimento corretto
        final Scanner scannerString = new Scanner(randomString);
        for (int i = 0; i < Parametri.MOVIMENTI_SIZE; i++) {
            movimenti[i] = scannerString.nextInt();
        }

        // Chiudo lo scanner
        scannerString.close();

        // Salvo i movimenti nelle scuderie
        for (final Scuderia scuderia : scuderie) {
            scuderia.setMovimento(movimenti[scuderia.getQuotazione() - 2]);
        }
    }

    /**
     * Imposta lo sprint a 1 di 2 scuderie diverse
     *
     * @exceptions
     * @see
     */
    public void sprint() {
        final Random rnd = new Random();
        final int primaScuderia = rnd.nextInt(COLORI.length);
        final int secondaScuderia = rnd.nextInt(COLORI.length);
        if (primaScuderia != secondaScuderia) {
            scuderie.get(primaScuderia).setSprint(1);
            scuderie.get(secondaScuderia).setSprint(1);
        } else {
            sprint();
        }
    }

    /**
     * Controllo se il cavallo è gia arrivato o no, se non è ancora arrivato
     * sommo la sua posizione col movimento, se gia arrivato applico le carte
     * traguardo
     *
     * @exceptions
     * @see
     */
    public void posizione() {
        for (Scuderia scuderia : scuderie) {
            // Controllo se la scuderia è arrivata
            if (!scuderia.isArrivato()) {
                // Applico il movimento
                scuderia.setPosizione(scuderia.getPosizione() + scuderia.getMovimento());

                // Controllo se deve fare lo sprint
                if (scuderia.getSprint() > 0) {
                    scuderia.setPosizione(scuderia.getPosizione() + scuderia.getSprint());
                    // Reimposto a -1 lo sprint
                    scuderia.setSprint(-1);
                }

            }
            // Controllo se ha superato il traguardo
            if (scuderia.getPosizione() >= Parametri.TRAGUARDO) {
                // Imposto Arrivato = TRUE
                scuderia.setArrivato(true);
                // Applico le carte Azione del traguardo
                scuderia.carteAzioneTraguardo();
                // Se è arrivato e non è ancora nella classifica lo aggiungo
                // agli lista degli arrivati
                if (scuderia.isArrivato() && !classifica.contains(scuderia)) {
                    arrivati.add(scuderia);
                }

            }
        }
    }

    /**
     * L'utente sceglie su che scuderia scommettere finche la scuderia scelta è
     * valida e la scommessa non è dello stesso tipo di una gia effettuata dallo
     * stesso giocatore
     *
     * @param playerIndex l'indice del array dei giocatori
     * @exceptions
     * @see
     */
    public void sceltaScuderiaScommessa(final int playerIndex) {
        // Valida della scuderia scelta
        int scuderiavalida;
        // Controllo sullo stesso tipo di scommessa
        int check;
        // Indice scuderia
        int sceltaScuderia;

        do {
            // L'utente sceglie una scuderia finche non ne sceglie una valida
            do {
                // l'utente sceglie la scuderia su cui scommettere
                sceltaScuderia = SystemIn.readScuderiaScommessa(scuderie);

                // Verifico se la scuderia scelta puo avere ulteriori scommesse
                if (scuderie.get(sceltaScuderia).getSegnalino() == 0) {
                    // Imposto la scuderia come non valida
                    scuderiavalida = 0;
                    SystemOut.write("\nNon è possibile effettuare altre scommesse"
                            + "su questa scuderia. Cambia scuderia!.\n");
                } else {
                    // Imposto la scuderia come valida e rimuovo un segnalino
                    // dalla scuderia
                    scuderiavalida = 1;
                    scuderie.get(sceltaScuderia).aggiornaSegnalino(-1);
                }
            } while (scuderiavalida != 1);

            // Creo una nuova scommessa e inizio a impostare il nome del
            // giocatore e la aggiungo alla lista delle scommesse della scuderia
            // scelta
            final Scommessa scommessa = new Scommessa(giocatori.get(playerIndex).getNome());
            scuderie.get(sceltaScuderia).getscommessa().add(scommessa);

            // Effettuo la scommessa chiedendo i soldi al giocatore
            check = scuderie.get(sceltaScuderia).effettuaScommessa(playerIndex);
            // Rimuovo la scuderia se è dello stesso tipo di una già effettuata
            if (check == 0) {
                scuderie.get(sceltaScuderia).getscommessa()
                        .remove(scuderie.get(sceltaScuderia).getscommessa().size() - 1);
            }
        } while (check != 1);
    }

    /**
     * Secondo giro di scommesse in ordine anti orario a partire dall'ultimo
     * giocatore
     */
    public void secondaScommessa() {
        char chartemp = 's';

        // Ciclo i giocatori in senso anti orario
        boolean finisco = true;
        int indice;
        // Controllo specifico se il primogiocatore è 0 e faceva uscire subito
        // dal ciclo
        if (primogiocatore == 0) {
            indice = ngiocatori - 1;
        } else {
            indice = primogiocatore - 1;
        }
        for (int i = indice; i > -1 && finisco; i--) {
            final Giocatore giocatore = giocatori.get(i);
            SystemOut.write("Tocca al " + giocatore.toString());

            // Controllo se il giocatore salta il secondo giro di scommesse
            if (giocatore.getSalta() == 1) {
                SystemOut.write("Il giocatore al primo giro di scommesse ha perso dei punti vittoria"
                        + " pertanto salta anche il secondo giro di scommessa");
                // Resetto salta del giocatore che ha saltato la scommessa
                giocatore.setSalta(0);

            } else {

                int valido = 1;

                // Controllo se ha abbastanza soldi per fare una seconda
                // scommessa
                if (giocatore.getPuntiVittoria() * Parametri.MIN_SCOMMESSA > giocatore.getSoldi()) {
                    valido = 0;
                    SystemOut.write("Il giocatore non ha abbastanza danari per effettuare la scommessa minima, non puoi effettuare la seconda scommessa!");
                }

                if (valido == 1) {
                    // Chiedo se vuole effettuare una seconda scommessa
                    chartemp = SystemIn.readSecondaScommessa();

                    if (chartemp == 's') {
                        // L'utente sceglie la scuderia su cui scommettere
                        sceltaScuderiaScommessa(i);
                    }
                }
            }

            // Clear console
            SystemOut.clear();
            // Check per ciclare tutti i giocatori in senso anti orario
            if (i == 0 && primogiocatore > 0) {
                i = ngiocatori;
            } else if (i == primogiocatore) {
                finisco = false;
            }

        }
    }

    /**
     * Primo giro di scommesse in senso orario a partire dal primo giocatore
     */
    public void primascommessa() {
        // Ciclo tutti i giocatori
        boolean finisco = true;
        for (int i = primogiocatore; i < ngiocatori && finisco; i++) {
            final Giocatore giocatore = giocatori.get(i);
            SystemOut.write("Tocca al " + giocatore.toString());

            // Verifico la validità della scommessa minima
            if (giocatore.getPuntiVittoria() * Parametri.MIN_SCOMMESSA > giocatore.getSoldi()) {
                SystemOut.write("Il giocatore non ha abbastanza danari per effettuare la scommessa"
                        + " minima, pertanto perde 2 punti vittoria!");
                // Rimuovo 2 punti vittoria
                giocatore.aggiornaPuntiVittoria(Parametri.MENODUE);
                // Salta la seconda scommessa
                giocatore.setSalta(1);

            }

            // L'utente sceglie la scuderia su cui scommettere
            sceltaScuderiaScommessa(i);

            // Clear console
            SystemOut.clear();

            // Check per ciclare tutti i giocatori in senso orario
            if (i == ngiocatori - 1 && primogiocatore > 0) {
                i = -1;
            } else if (i == primogiocatore - 1) {
                // Ho ciclato tutti i giocatori
                finisco = false;
            }
        }

    }

    /**
     * @return the primogiocatore
     */
    public int getprimogiocatore() {
        return primogiocatore;
    }

    /**
     * Aggiorna il primo giocatore in senso orario
     *
     * @exceptions
     * @see
     */
    public void aggiornaprimogiocatore() {
        int estremo;
        estremo = giocatori.size();
        if (primogiocatore == estremo - 1) {
            primogiocatore = 0;
        } else {
            primogiocatore += 1;
        }
    }

    /**
     * @return the carteAzione
     */
    public List<CartaAzione> getCarteAzione() {
        return carteAzione;
    }

    /**
     * Metodo che riempie la lista classifica con le scuderie in ordine di
     * arrivo
     *
     * @param scuderie delle scuderie arrivate
     * @see Scuderia
     */
    public void creaClassifica(final List<Scuderia> scuderie) {
        int max;
        final List<Scuderia> fotofinish = new ArrayList<>();
        int idmax = -1;

        while (!scuderie.isEmpty()) {
            max = 0;
            for (int i = 0; i < scuderie.size(); i++) {
                final Scuderia scuderia = scuderie.get(i);
                if (scuderia.getPosizione() > max) {
                    max = scuderia.getPosizione();
                    idmax = i;
                } else if (max == scuderia.getPosizione()) {
                    // Aggiungo al fotofinish la scuderia con quotazione uguale
                    // al massimo
                    fotofinish.add(scuderia);
                    // Aggiungo al fotofinish scuderia precedente con
                    // quotazione uguale alla nuova
                    fotofinish.add(scuderie.get(idmax));
                    // Rimuovo nuova scuderia con quotazione uguale al massimo
                    scuderie.remove(scuderia);
                    // Rimuovo scuderia precedente con quotazione uguale
                    // alla nuova
                    scuderie.remove(scuderie.get(idmax));

                }
            }
            if (!fotofinish.isEmpty()) {
                checkFotofinish(fotofinish);
            } else {
                classifica.add(scuderie.get(idmax));
                scuderie.remove(scuderie.get(idmax));
            }
        }

        // Imposto l'intero classifica delle scuderie in classifica
        int i = 1;
        for (final Scuderia scuderia : classifica) {
            scuderia.setClassifica(i++);
        }
    }

    /**
     * @return the classifica
     */
    public List<Scuderia> getClassifica() {
        return classifica;
    }

    /**
     * @param newClassifica the classifica to set
     */
    public void setClassifica(final List<Scuderia> newClassifica) {
        this.classifica = newClassifica;
    }

    /**
     * Imposto chi vince il fotofinish, confrontando le quotazioni e
     * controllando se gli è stata applicata una carta azione che modifica il
     * fotofinish
     *
     * @param fotofinish Lista delle scuderie che partecipano al fotofinish
     * @exceptions
     * @see
     */
    public void checkFotofinish(final List<Scuderia> fotofinish) {
        int max = 0;
        int idmax = -1;

        // Controllo se è stata applicata una carta azione fotofinish
        for (int j = 0; j < fotofinish.size(); j++) {
            Scuderia scuderia = fotofinish.get(j);
            if (scuderia.getFotofinish() == 1) {
                // Lo aggiungo subito alla classifica
                classifica.add(scuderia);
                // Lo rimuovo dal fotofinish
                fotofinish.remove(j);
            } else if (scuderia.getFotofinish() == 0) {
                // Imposto la quotazione 1 cosi da farlo sempre perdere nel
                // confronto successivo
                scuderia.setQuotazione(Parametri.OTTO);
            }

            while (fotofinish.size() > 0) {
                // Trovo la scuderia con quotazione piu alta (1:2)
                max = Parametri.DIECI;
                for (int i = 0; i < fotofinish.size(); i++) {
                    Scuderia scuderiaB = fotofinish.get(i);
                    if (scuderiaB.getQuotazione() < max) {
                        max = scuderiaB.getQuotazione();
                        idmax = i;
                    } else if (scuderiaB.getQuotazione() == max) {
                        // Vince il fotofinish l'utlimo giocatore con quotazione
                        // piu alta
                        classifica.add(scuderiaB);
                        fotofinish.remove(i);
                    }
                }
                classifica.add(fotofinish.get(idmax));
                fotofinish.remove(idmax);

            }
        }
    }

    /**
     * Turno di gioco, caratterizzato da una scommessa, truccare la corsa e una
     * seconda eventuale scommessa
     *
     * @throws IOException
     * @exceptions
     * @see
     */
    public void turno() throws IOException {

        int turno = 0;
        do {
            SystemOut.clear();
            SystemOut.header("TURNO " + (turno + 1));
            setCarteAzione();
            setCarteAzione();
            setSegnalini();

            primascommessa();
            truccoCorsa();
            secondaScommessa();
            corsa();
            for (int i = 0; i < Parametri.PODIO_SIZE; i++) {
                classifica.get(i).pagaScomesse();
            }
            pagascuderie();
            checkeliminato();
            SystemOut.printClassifica(classifica);
            SystemOut.printQuotazioni(scuderie);
            SystemOut.leaderboard(giocatori);
            // Reset Carte Azione Player
            resetCarteAzione();
            // Reset Scommesse
            resetScommesse();
            // Rimischio il mazzo
            setListe();
            // Primogiocatore in senso orario
            aggiornaprimogiocatore();
            // Reset posizione e arrivo scuderie
            resetScuderie();
            turno++;

            // Controllo se è rimasto un solo giocatore che vince
            // automaticamente la partita
            if (giocatori.size() == 1) {
                turno = turni;
            }
            if (turno < turni) {
                SystemOut.write("\nPremi invio per passare al turno successivo");
                SystemIn.readInt();
            }
        } while (turno < turni);
        String vincitore = trovaVincitore();
        SystemOut.write("\nVince il giocatore: "
                + vincitore.toUpperCase(Locale.getDefault()));

    }

    /**
     * Resetta a 0 la posizione e imposta a false l'arrivo di ogni scuderia alla
     * fine del turno. Svuoto anche le liste di arrivo, classifica e fotofinish.
     *
     * @see Scuderia
     */
    private void resetScuderie() {
        for (Scuderia scuderia : scuderie) {
            scuderia.setPosizione(0);
            scuderia.setClassifica(0);
            scuderia.setArrivato(false);
            scuderia.setSprint(-1);
            scuderia.setFotofinish(-1);
        }
        arrivati.clear();
        classifica.clear();
    }

    /**
     * Trova il giocatore con il maggior numero di punti vittoria, se 2
     * giocatori hanno lo stesso numero di punti vittoria vince quello con piu
     * soldi
     *
     * @return il nome del giocatore con il maggior numero di punti vittoria
     * @see Giocatore
     */
    public String trovaVincitore() {
        int max = 0;
        int idmax = -1;

        if (giocatori.size() > 0) {
            // Ciclo tutti i giocatori rimasti
            for (Giocatore giocatore : giocatori) {
                // Trovo il giocatore con i pv massimi
                if (giocatore.getPuntiVittoria() > max) {
                    max = giocatore.getPuntiVittoria();
                    idmax = giocatori.indexOf(giocatore);

                    // Se ci sono due giocatori con gli stessi pv vince quello
                    // con più soldi
                } else if (max == giocatore.getPuntiVittoria()
                        && giocatore.getSoldi() > giocatori
                        .get(idmax).getSoldi()) {
                    max = giocatore.getPuntiVittoria();
                    idmax = giocatori.indexOf(giocatore);
                }
            }

        }
        return giocatori.get(idmax).getNome();
    }

    /**
     * Pagamento all’eventuale proprietario (le scuderie possono non essere di
     * nessuno) della scuderia il cui cavallo arriva primo: 600 secondo: 400
     * terzo: 200
     */
    public void pagascuderie() {
        for (Giocatore player : giocatori) {
            if (player.getScuderia().equals(classifica.get(0).getColore())) {
                player.setSoldi(player.getSoldi() + Parametri.SOLDI_PRIMA_SCUDERIA);
            }
            if (player.getScuderia().equals(classifica.get(1).getColore())) {
                player.setSoldi(player.getSoldi() + Parametri.SOLDI_SECONDA_SCUDERIA);
            }
            if (player.getScuderia().equals(classifica.get(2).getColore())) {
                player.setSoldi(player.getSoldi() + Parametri.SOLDI_TERZA_SCUDERIA);
            }
        }

    }

    /**
     * Svuoto la lista delle carte azione di ogni giocatore e di ogni scuderia
     */
    private void resetCarteAzione() {
        for (Scuderia scuderia : scuderie) {
            scuderia.resetCarteAzione();
        }
        for (Giocatore player : giocatori) {
            player.resetCarteAzione();
        }

    }

    /**
     * Reset delle scommesse di tutte le scuderie
     */
    private void resetScommesse() {
        for (Scuderia scuderia : scuderie) {
            scuderia.clearScomesse();
        }
    }

    /**
     * Fase di corsa del gioco:
     */
    public void corsa() {
        boolean partenza = true;
        do {
            // Controllo carte stessa lettera e quelle che eliminano le
            // negative/positive
            for (Scuderia scuderia : scuderie) {
                scuderia.checkCarteAzione();
                scuderia.checkLetteraCarteAzione();
            }
            CartaAzione azioni = new CartaAzione();
            movimento();
            if (partenza) {
                azioni.carteAzionePartenza(scuderie);
                partenza = false;
            } else {
                azioni.carteAzioneMovimento(scuderie);
            }
            sprint();
            azioni.carteAzioneSprint(scuderie);
            posizione();
            azioni.carteAzioneFotofinish(scuderie);
            creaClassifica(arrivati);
            aggiornaQuotazioni();
            SystemOut.printCorsa(scuderie);
        } while (!checkArrivati());
    }

    /**
     * Aggiorna le quotazioni delle scuderie in base all'arrivo
     */
    private void aggiornaQuotazioni() {
        for (Scuderia scuderia : classifica) {
            scuderia.aggiornaQuotazioni(scuderia.getClassifica());
        }
    }

    /**
     * Controllo se qualche giocatore non è piu in grado di giocare e lo elimino
     * dalla partita
     */
    public void checkeliminato() {
        // Ciclo tutti i giocatori
        for (int i = 0; i < giocatori.size(); i++) {

            int pv = giocatori.get(i).getPuntiVittoria();
            int soldi = giocatori.get(i).getSoldi();
            if (pv <= 2 && soldi < pv * Parametri.MIN_SCOMMESSA) {
                SystemOut.write("\nIl giocatore "
                        + giocatori.get(i).getNome()
                        .toUpperCase(Locale.getDefault())
                        + " non ha né abbastanza soldi né abbastanza punti vittoria"
                        + " per proseguire la partita, pertento è eliminato"
                        + "\nCIAO CIAO "
                        + giocatori.get(i).getNome()
                        .toUpperCase(Locale.getDefault()));
                giocatori.remove(i);
                ngiocatori--;
                i--;
            }
        }
    }
}
