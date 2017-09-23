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
    private List<Scommessa> scommessa;
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

    public Scuderia(final Partita partita, String colore) {
        this.carteAzione = new ArrayList<>();
        this.scommessa = new ArrayList<>();
        this.partita = partita;
        this.colore = colore;
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
        return " ) " + colore + "\t" + scommessa.size()
                + "\t\t1:" + quotazione;
    }

    /**
     * Pago le scommesse dopo di che resetto la lista scommessa
     *
     * @see Scommessa
     */
    public void pagascommessa() {
        // Ciclo tutte le scommesse
        for (int a = scommessa.size() - 1; a >= 0; a--) {
            // Ciclo tutti i giocatori
            for (int z = 0; z < partita.getarraygiocatori().size(); z++) {
                // Verifico che il giocatore sia lo stesso che ha effettuato la
                // scommessa
                if (scommessa.get(a).getNomeGiocatore()
                        .equals(partita.getarraygiocatori().get(z).getNome())) {

                    // Pago le scommesse vincenti
                    if (scommessa.get(a).getTiposcommessa() == Scommessa.Tiposcommessa.VINCENTE
                            && classifica == 1) {
                        partita.getarraygiocatori()
                                .get(z)
                                .aggiornaSoldi(
                                        scommessa.get(a).getSoldi()
                                                * quotazione);
                        partita.getarraygiocatori().get(z)
                                .aggiornaPuntiVittoria(Parametri.TRE);

                    }
                    // Pago le scommesse piazzate
                    if (scommessa.get(a).getTiposcommessa() == Scommessa.Tiposcommessa.PIAZZATO) {
                        partita.getarraygiocatori().get(z)
                                .aggiornaSoldi(scommessa.get(a).getSoldi() * 2);
                        partita.getarraygiocatori().get(z).aggiornaPuntiVittoria(1);
                    }
                }
            }
        }
        // Resetto la lista delle scommesse
        scommessa.clear();

    }

    /**
     * Resetto la lista delle scommesse della scuderia
     *
     * @see Scommessa
     */
    public void clearscommessa() {
        scommessa.clear();
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
     * @param i Posizione della carta azione nella lista
     */
    public void removeCartaAzione(final int i) {
        carteAzione.remove(i);

    }

    /**
     * Chiedo i soldi che l'utente vuole scommettere su questa scuderia e il
     * tipo di scommessa
     *
     * @param tocca indice dell'array dei giocatori
     * @return 0 se la scommessa è dello stesso tipo di una già effettuata 1 se
     * è valida
     * @see Scommessa
     */
    public int effettuaScommessa(final int tocca) {

        int flagsc = 1;
        char chartemp = 'v';
        Scommessa.Tiposcommessa tiposcommessatemp;

        final int i = scommessa.size() - 1;
        final String nomegiocatore = scommessa.get(i).getNomeGiocatore();
        final int soldigiocatore = partita.getarraygiocatori().get(tocca).getSoldi();
        final int pvgiocatore = partita.getarraygiocatori().get(tocca).getPuntiVittoria();
        int soldiscommessa;

        SystemOut.write("Quanti soldi vuoi scommettere?");

        boolean tryAgain;
        do {
            tryAgain = false;
            soldiscommessa = SystemIn.readInt();
            // controllo se il giocatore ha abbastanza soldi per effettuare la
            // scommessa dell'importo da lui scelto
            if (soldiscommessa > soldigiocatore) {
                SystemOut.write("Non puoi scommettere più di quanti soldi possiedi.\n"
                        + " Inserisci un nuovo importo:");
                tryAgain = true;
            }
            // controllo validità scommessa : scommessa>=pv*100
            if (soldiscommessa < pvgiocatore * Parametri.MIN_SCOMMESSA) {
                SystemOut.write("L'importo scommesso non è valido, questo deve essere\n"
                        + "come minimo pari a \"punti vittoria posseduti\" * 100.\n Inserire"
                        + " un nuovo importo :");
                tryAgain = true;
            }
        } while (tryAgain);

        // Salvo i soldi scommessi nella scommessa
        scommessa.get(i).setSoldi(soldiscommessa);

        // Chiedo se scommessa v (vincente) o p (piazzata) e lo imposto nella
        // scommessa
        chartemp = SystemIn.readTipoScommessa();
        scommessa.get(i).setTiposcommessa(chartemp);

        // Verifico che il giocatore non ha gia effettuato questo tipo di
        // scommessa
        tiposcommessatemp = scommessa.get(i).getTiposcommessa();
        for (int a = 0; a < i; a++) {
            if (nomegiocatore.equals(scommessa.get(a).getNomeGiocatore())
                    && tiposcommessatemp == scommessa.get(a).getTiposcommessa()) {
                SystemOut.write("\nQuesta scommessa è già stata effettuata, non è possibile ripeterela stessa scommessa."
                        + "\nE' possibile fare due scommesse sulla stessa scuderia, ma bisogna modificare il tipo di scommessa");
                flagsc = 0;
            }
        }

        if (flagsc == 1) {

            // tolgo al giocatore i soldi che ha scommesso
            partita.getarraygiocatori().get(tocca)
                    .aggiornaSoldi(-soldiscommessa);
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
     * @return scommessa la lista delle scommesse della scuderia
     */
    public List<Scommessa> getscommessa() {
        return scommessa;
    }

    /**
     * @param newScomessa the scommessa to set
     */
    public void setScommessa(final List<Scommessa> newScomessa) {
        this.scommessa = newScomessa;
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
     * @param sprint the sprint to set
     */
    public void setSprint(final int sprint) {
        this.sprint = sprint;
    }

    /**
     * @return se la scuderia è ultima o no
     */
    public boolean isUltimo() {
        return ultimo;
    }

    /**
     * @param ultimo the ultimo to set
     */
    public void setUltimo(final boolean ultimo) {
        this.ultimo = ultimo;
    }

    /**
     * @return se la scuderia è prima o no
     */
    public boolean isPrimo() {
        return primo;
    }

    /**
     * @param primo the primo to set
     */
    public void setPrimo(final boolean primo) {
        this.primo = primo;
    }

    /**
     * @return the posizione
     */
    public int getPosizione() {
        return posizione;
    }

    /**
     * @param posizione the posizione to set
     */
    public void setPosizione(final int posizione) {
        this.posizione = posizione;
    }

    /**
     * @return the fotofinish
     */
    public int getFotofinish() {
        return fotofinish;
    }

    /**
     * @param fotofinish the fotofinish to set
     */
    public void setFotofinish(final int fotofinish) {
        this.fotofinish = fotofinish;
    }

    /**
     * @return the segnalino
     */
    public int getSegnalino() {
        return segnalino;
    }

    /**
     * @param segnalino the segnalino to set
     */
    public void setSegnalino(final int segnalino) {
        this.segnalino = segnalino;
    }

    /**
     * @param temp the segnalino to add
     */
    public void aggiornaSegnalino(final int temp) {
        this.segnalino += temp;
    }

    /**
     * @return se la scuderie è arrivata o no
     */
    public boolean isArrivato() {
        return arrivato;
    }

    /**
     * @param arrivato the arrivato to set
     */
    public void setArrivato(final boolean arrivato) {
        this.arrivato = arrivato;
    }

    /**
     * @return the classifica
     */
    public int getClassifica() {
        return classifica;
    }

    /**
     * @param classifica the classifica to set
     */
    public void setClassifica(final int classifica) {
        this.classifica = classifica;
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
