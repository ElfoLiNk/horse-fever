/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.Partita;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.SystemIn;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.SystemOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Struttura dati della scuderia
 */
public class Scuderia {
    private final Partita partita;
    private final List<CartaAzione> carteAzione;
    private List<Scommessa> scomesse;
    private int segnalino;
    private String colore;
    private int quotazione;
    private boolean arrivato;
    private int classifica;
    private int posizione;
    private int movimento;
    // Sprint inizializzato a -1 per controllo carte azione
    private int sprint = -1;
    // booleani per carte azione che agiscono sul Movimento
    private boolean ultimo;
    private boolean primo;
    // 1 Vince fotofinish , 0 Perde fotofinish , -1 Controllo le quotazioni
    private int fotofinish = -1;

    public Scuderia(final Partita newPartita, final String newColore) {
        this.carteAzione = new ArrayList<>();
        this.scomesse = new ArrayList<>();
        this.partita = newPartita;
        this.colore = newColore;
        this.arrivato = false;
        this.classifica = 0;
        this.posizione = 0;
        this.movimento = -1;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return " ) " + colore + "\t" + scomesse.size()
                + "\t\t1:" + quotazione;
    }

    /**
     * Pago le scommesse dopo di che resetto la lista scomesse
     *
     * @see Scommessa
     */
    public void pagaScomesse() {
        // Ciclo tutte le scommesse
        for (int a = scomesse.size() - 1; a >= 0; a--) {
            // Ciclo tutti i giocatori
            for (final Giocatore giocatore : partita.getGiocatori()) {
                // Verifico che il giocatore sia lo stesso che ha effettuato la scomessa
                final Scommessa scommessa = scomesse.get(a);
                if (scommessa.getNomeGiocatore().equals(giocatore.getNome())) {

                    // Pago le scommesse vincenti
                    if (scommessa.getTipoScommessa() == Scommessa.Tiposcommessa.VINCENTE && classifica == 1) {
                        giocatore.aggiornaSoldi(scommessa.getSoldi() * quotazione);
                        giocatore.aggiornaPuntiVittoria(Parametri.TRE);

                    }
                    // Pago le scommesse piazzate
                    if (scommessa.getTipoScommessa() == Scommessa.Tiposcommessa.PIAZZATO) {
                        giocatore.aggiornaSoldi(scommessa.getSoldi() * 2);
                        giocatore.aggiornaPuntiVittoria(1);
                    }
                }
            }
        }
        // Resetto la lista delle scommesse
        scomesse.clear();

    }

    /**
     * Resetto la lista delle scommesse della scuderia
     *
     * @see Scommessa
     */
    public void clearScomesse() {
        scomesse.clear();
    }

    /**
     * Aggiorno le quotazioni in base all'arrivo
     *
     * @param arrivo
     * @see Scuderia
     */
    public void aggiornaQuotazioni(final int arrivo) {
        if (arrivo > quotazione - 1) {
            quotazione += 1;
        }
        if (arrivo < quotazione - 1) {
            quotazione -= 1;
        }
    }

    /**
     * Rimuovo la carta azione dalla lista per ID
     *
     * @param idCarta ID della carta azione
     */
    public void removeCartaAzioneByID(final int idCarta) {
        carteAzione.removeIf(carta -> carta.getIdentifier() == idCarta);
    }

    /**
     * Rimuovo la carta azione dalla lista per posizione
     *
     * @param index Posizione della carta azione nella lista
     */
    public void removeCartaAzione(final int index) {
        carteAzione.remove(index);

    }

    /**
     * Chiedo i soldi che l'utente vuole scommettere su questa scuderia e il
     * tipo di scomesse
     *
     * @param tocca indice dell'array dei giocatori
     * @return 0 se la scomesse è dello stesso tipo di una già effettuata 1 se
     * è valida
     * @see Scommessa
     */
    public int effettuaScommessa(final int tocca) {
        final Scommessa ultimaScomessa = scomesse.get(scomesse.size() - 1);
        final String nomegiocatore = ultimaScomessa.getNomeGiocatore();
        final Giocatore giocatore = partita.getGiocatori().get(tocca);

        SystemOut.write("Quanti soldi vuoi scommettere?");

        int soldiscommessa;
        boolean tryAgain;
        do {
            tryAgain = false;
            soldiscommessa = SystemIn.readInt();
            // controllo se il giocatore ha abbastanza soldi per effettuare la
            // scomessa dell'importo da lui scelto
            if (soldiscommessa > giocatore.getSoldi()) {
                SystemOut.write("Non puoi scommettere più di quanti soldi possiedi.\n"
                        + " Inserisci un nuovo importo:");
                tryAgain = true;
            }
            // controllo validità scomessa : scomessa>=pv*100
            if (soldiscommessa < giocatore.getPuntiVittoria() * Parametri.MIN_SCOMMESSA) {
                SystemOut.write("L'importo scommesso non è valido, questo deve essere\n"
                        + "come minimo pari a \"punti vittoria posseduti\" * 100.\n Inserire"
                        + " un nuovo importo :");
                tryAgain = true;
            }
        } while (tryAgain);

        // Salvo i soldi scommessi nella scomessa
        ultimaScomessa.setSoldi(soldiscommessa);

        // Chiedo se scomessa v (vincente) o p (piazzata) e lo imposto nella scomessa
        final char tipo = SystemIn.readTipoScommessa();
        ultimaScomessa.setTipoScommessa(tipo);

        // Verifico che il giocatore non ha gia effettuato questo tipo di scomessa
        int flagsc = 1;
        final Scommessa.Tiposcommessa tipoScommessa = ultimaScomessa.getTipoScommessa();
        for (int a = 0; a < scomesse.size() - 1; a++) {
            if (nomegiocatore.equals(scomesse.get(a).getNomeGiocatore())
                    && tipoScommessa == scomesse.get(a).getTipoScommessa()) {
                SystemOut.write("\nQuesta scomesse è già stata effettuata, non è possibile ripeterela stessa scomesse."
                        + "\nE' possibile fare due scommesse sulla stessa scuderia, ma bisogna modificare il tipo di scomesse");
                flagsc = 0;
            }
        }

        if (flagsc == 1) {

            // tolgo al giocatore i soldi che ha scommesso
            giocatore.aggiornaSoldi(-soldiscommessa);
        }

        return flagsc;
    }

    /**
     * Controllo se la scuderia ha le carte azione che rimuovono tutte le carte
     * azione positive e/o negative assegnate alla scuderia
     *
     * @see CartaAzione
     */
    public void checkCarteAzione() {
        for (final CartaAzione cartaAzione : new ArrayList<>(carteAzione)) {
            if (cartaAzione.getIdentifier() == Parametri.FRITZ_FINDEN) {
                for (int j = Parametri.MIN_NEGATIVE; j < Parametri.MAX_NEGATIVE; j++) {
                    removeCartaAzioneByID(j);
                }
                // Rimuovo anche la carta stessa
                removeCartaAzioneByID(Parametri.FRITZ_FINDEN);
            } else if (cartaAzione.getIdentifier() == Parametri.ROCHELLE_RECHERCHE) {
                for (int j = Parametri.MIN_POSITIVE; j < Parametri.MAX_POSITIVE; j++) {
                    removeCartaAzioneByID(j);
                }
                // Rimuovo anche la carta stessa
                removeCartaAzioneByID(Parametri.ROCHELLE_RECHERCHE);
            }
        }
    }

    /**
     * Rimuove le carte azioni con la stessa lettera dalla lista delle carte
     * azioni della scuderia
     *
     * @see CartaAzione
     */
    public void checkLetteraCarteAzione() {
        /* HashMap di tutti gli attributi analizzati */
        final HashMap<String, CartaAzione> attributi = new HashMap<>();
        /* Lista delle carte con la stessa lettera */
        final List<CartaAzione> carteuguali = new ArrayList<>();

        // Controllo tutte le carte della scuderia
        for (final CartaAzione carta : carteAzione) {
            // Controllo con l'hashmap se la lettera della carta è gia presente
            if (attributi.containsKey(carta.getLettera())) {
                carteuguali.add(carta);
                carteuguali.add(attributi.get(carta.getLettera()));
            }
            // Inserisco nell'hashmap
            attributi.put(carta.getLettera(), carta);
        }
        /* Rimuovo dalla lista le carte con la stessa lettera */
        carteAzione.removeAll(carteuguali);
    }

    /**
     * Applico gli effetti delle carte azione che agiscono al traguardo
     *
     * @see CartaAzione
     */
    public void carteAzioneTraguardo() {
        // Controllo tutte le carte della scuderia
        for (int j = 0; j < carteAzione.size(); j++) {
            // Applico solo le carte azione che modificano il traguardo
            if (carteAzione.get(j).getAgisce().equals("Traguardo")) {
                // La scuderia avanza di posizione del numero dell'effetto
                if (carteAzione.get(j).getEffetto() > 0) {
                    setPosizione(getPosizione()
                            + carteAzione.get(j).getEffetto());
                }
                // La scuderia si ferma al traguardo
                if (carteAzione.get(j).getEffetto() == 0) {
                    setPosizione(Parametri.TRAGUARDO);
                }
                // Rimuovo la carta
                carteAzione.remove(j);
            }
        }
    }

    /**
     * @return the quotazione
     */
    public int getQuotazione() {
        return this.quotazione;
    }

    /**
     * @param newQuotazione the quotazione to set
     */
    public void setQuotazione(final int newQuotazione) {
        this.quotazione = newQuotazione;
    }

    /**
     * @return the colore
     */
    public String getColore() {
        return this.colore;
    }

    /**
     * @param newColore the colore to set
     */
    public void setColore(final String newColore) {
        this.colore = newColore;
    }

    /**
     * @return the movimento
     */
    public int getMovimento() {
        return this.movimento;
    }

    /**
     * @param newMovimento the movimento to set
     */
    public void setMovimento(final int newMovimento) {
        this.movimento = newMovimento;
    }

    /**
     * @return scomesse la lista delle scommesse della scuderia
     */
    public List<Scommessa> getscommessa() {
        return scomesse;
    }

    /**
     * @param newScomessa the scomesse to set
     */
    public void setScomesse(final List<Scommessa> newScomessa) {
        this.scomesse = newScomessa;
    }

    /**
     * @return carteAzione della scuderia
     */
    public List<CartaAzione> getCarteAzione() {
        return carteAzione;
    }

    /**
     * @param cartaAzione la carta da aggiungere alla scuderia
     */
    public void addCarteAzione(final CartaAzione cartaAzione) {
        this.carteAzione.add(cartaAzione);
    }

    /**
     * @return the sprint
     */
    public int getSprint() {
        return sprint;
    }

    /**
     * @param newSprint the sprint to set
     */
    public void setSprint(final int newSprint) {
        this.sprint = newSprint;
    }

    /**
     * @return se la scuderia è ultima o no
     */
    public boolean isUltimo() {
        return ultimo;
    }

    /**
     * @param newUltimo the ultimo to set
     */
    public void setUltimo(final boolean newUltimo) {
        this.ultimo = newUltimo;
    }

    /**
     * @return se la scuderia è prima o no
     */
    public boolean isPrimo() {
        return primo;
    }

    /**
     * @param newPrimo the primo to set
     */
    public void setPrimo(final boolean newPrimo) {
        this.primo = newPrimo;
    }

    /**
     * @return the posizione
     */
    public int getPosizione() {
        return posizione;
    }

    /**
     * @param newPosizione the posizione to set
     */
    public void setPosizione(final int newPosizione) {
        this.posizione = newPosizione;
    }

    /**
     * @return the fotofinish
     */
    public int getFotofinish() {
        return fotofinish;
    }

    /**
     * @param newFotofinish the fotofinish to set
     */
    public void setFotofinish(final int newFotofinish) {
        this.fotofinish = newFotofinish;
    }

    /**
     * @return the segnalino
     */
    public int getSegnalino() {
        return segnalino;
    }

    /**
     * @param newSegnalino the segnalino to set
     */
    public void setSegnalino(final int newSegnalino) {
        this.segnalino = newSegnalino;
    }

    /**
     * @param addSegnalino the segnalino to add
     */
    public void aggiornaSegnalino(final int addSegnalino) {
        this.segnalino += addSegnalino;
    }

    /**
     * @return se la scuderie è arrivata o no
     */
    public boolean isArrivato() {
        return arrivato;
    }

    /**
     * @param newArrivato the arrivato to set
     */
    public void setArrivato(final boolean newArrivato) {
        this.arrivato = newArrivato;
    }

    /**
     * @return the classifica
     */
    public int getClassifica() {
        return classifica;
    }

    /**
     * @param newClassifica the classifica to set
     */
    public void setClassifica(final int newClassifica) {
        this.classifica = newClassifica;
    }

    /**
     * Svuota la lista delle carte azione
     *
     * @see CartaAzione
     */
    public void resetCarteAzione() {
        carteAzione.clear();
    }
}
