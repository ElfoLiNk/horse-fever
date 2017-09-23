/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.ResourceLoader;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.parser.XmlParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Struttura dati delle carte azione
 */
public class CartaAzione {
    /**
     * Attributi: identifier: Numero identificativo della carta lettera: Lettera della
     * carta agisce: Campo della partita in cui agisce effetto: Numero che
     * modifica il campo in cui agisce descrizione: Descrizione della carta
     * azione
     */
    private int identifier;
    private String lettera;
    private String nome;
    private int effetto;
    private String agisce;
    private String descrizione;

    /**
     * Crea una lista di tipo <CartaAzione> effettuando il parsing di un file
     * xml contenente le carte azione.
     *
     * @return la lista delle carte azioni lette dal file xml
     */
    public static List<CartaAzione> caricaCarteAzione() {
        // Parso il file
        return XmlParser.parseXmlAzioni(ResourceLoader.load("carteAzione.xml"));
    }

    /**
     * Override del metodo toString per stampare a video il nome e la
     * descrizione della carta azione
     */
    @Override
    public String toString() {
        return this.nome + " :\n\t" + this.descrizione + "\n";
    }

    /**
     * Applico gli effetti delle carte azione che agiscono alla partenza
     *
     * @param scuderie La lista delle scuderie della partita
     */
    public void carteAzionePartenza(final List<Scuderia> scuderie) {
        // Controllo tutte le scuderie
        for (final Scuderia scuderia : scuderie) {

            // Prendo le carte azione della scuderia
            final List<CartaAzione> carte = scuderia.getCarteAzione();

            if (carte != null) {
                // Controllo tutte le carte della scuderia
                for (final CartaAzione carta : new ArrayList<>(carte)) {
                    // Analizzo solo le carte azione che influiscono la partenza
                    if ("Partenza".equals(carta.getAgisce())) {
                        if (carta.getEffetto() < 0 && scuderia.getMovimento() > 0) {
                            // Applico l'effetto
                            scuderia.setMovimento(scuderia.getMovimento() + carta.getEffetto());
                        } else if (carta.getEffetto() == 0) {
                            // Applico l'effetto
                            scuderia.setMovimento(0);
                        } else if (carta.getEffetto() == 1) {
                            // Applico l'effetto
                            scuderia.setMovimento(scuderia.getMovimento() + 1);
                        } else if (carta.getEffetto() > 0) {
                            // Applico l'effetto
                            scuderia.setMovimento(carta.getEffetto());
                        }

                        // Rimuovo la carta
                        scuderia.removeCartaAzione(carte.indexOf(carta));
                    }
                }
            }
        }
    }

    /**
     * Applico gli effetti delle carte azione che agiscono sul movimento
     *
     * @param scuderie La lista delle scuderie della partita
     */
    public void carteAzioneMovimento(final List<Scuderia> scuderie) {
        // Controllo tutte le scuderie
        for (final Scuderia scuderia : scuderie) {

            // Prendo le carte azione della scuderia
            final List<CartaAzione> carte = scuderia.getCarteAzione();

            // Controllo tutte le carte della scuderia
            for (final CartaAzione carta : carte) {
                // Analizzo solo le carte azione che influiscono il movimento
                if ("Movimento".equals(carta.getAgisce())) {
                    // Se la scuderia è ultima
                    if (carta.getEffetto() == Parametri.QUATTRO && scuderia.isUltimo()) {
                        // Si muove di 4
                        scuderia.setMovimento(Parametri.QUATTRO);
                    }
                    // Se la scuderia è prima
                    if (carta.getEffetto() == 0 && scuderia.isPrimo()) {
                        // Non si muove
                        scuderia.setMovimento(0);
                    }
                }
            }
        }

    }

    /**
     * Applico gli effetti delle carte azione che agiscono sullo sprint
     *
     * @param scuderie La lista delle scuderie della partita
     */
    public void carteAzioneSprint(final List<Scuderia> scuderie) {
        // Controllo tutte le scuderie
        for (final Scuderia scuderia : scuderie) {
            // Controllo tutte le carte della scuderia
            for (final CartaAzione carta : scuderia.getCarteAzione()) {
                // Analizzo solo le carte azione che influiscono lo sprint
                if ("Sprint".equals(carta.getAgisce())) {
                    switch (carta.getEffetto()) {
                        case 0:
                            scuderia.setSprint(0);
                            break;
                        case -1:
                            if (scuderia.getSprint() > 0) {
                                scuderia.setSprint(scuderia.getSprint() - 1);
                            }
                            break;
                        case 1:
                            if (scuderia.getSprint() > 0 && scuderia.getSprint() < Parametri.TRE) {
                                scuderia.setSprint(scuderia.getSprint() + 1);
                            }
                            break;
                        case 2:
                            if (scuderia.getSprint() > 0) {
                                scuderia.setSprint(Parametri.TRE);
                            } else {
                                scuderia.setSprint(2);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }

    }

    /**
     * Riceve la lista delle scuderie con la stessa posizione e applica le carte
     * Fotofinish se presenti
     *
     * @param scuderie ricevuta da findFotofinish
     */
    public void carteAzioneFotofinish(final List<Scuderia> scuderie) {
        // Controllo tutte le scuderie
        for (Scuderia scuderia : scuderie) {
            // Controllo tutte le carte della scuderia
            for (final CartaAzione carta : scuderia.getCarteAzione()) {
                // Analizzo solo le carte azione che influiscono il fotofinish
                if ("Fotofinish".equals(carta.getAgisce())) {
                    switch (carta.getEffetto()) {
                        case 0:
                            scuderia.setFotofinish(0);
                            break;
                        case 1:
                            scuderia.setFotofinish(1);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * Applico le carte azione che modificano la quotazione della scuderia,
     * l'algoritmo non si applica se la quotazione non rimane tra (1:1 - 1:7)
     *
     * @param scuderie la lista delle scuderie della partita
     */
    public void carteAzioneQuotazione(final List<Scuderia> scuderie) {
        // Controllo tutte le scuderie
        for (Scuderia scuderia : scuderie) {
            // Controllo tutte le carte della scuderia
            for (final CartaAzione carta : scuderia.getCarteAzione()) {
                // Analizzo solo le carte azione che influiscono le quotazioni
                if ("Quotazione".equals(carta.getAgisce())) {
                    if (carta.getEffetto() > 0 && scuderia.getQuotazione() > carta.getEffetto() + 1) {
                        scuderia.setQuotazione(scuderia.getQuotazione() - carta.getEffetto());
                    } else if (scuderia.getQuotazione() < carta.getEffetto() + Parametri.SETTE) {
                        scuderia.setQuotazione(scuderia.getQuotazione() - carta.getEffetto());
                    }
                }
            }
        }

    }

    /**
     * @return the identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * @param newIdentifier the identifier to set
     */
    public void setIdentifier(final int newIdentifier) {
        this.identifier = newIdentifier;
    }

    /**
     * @return the lettera
     */
    public String getLettera() {
        return lettera;
    }

    /**
     * @param newLettera the lettera to set
     */
    public void setLettera(final String newLettera) {
        this.lettera = newLettera;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param newNome the nome to set
     */
    public void setNome(final String newNome) {
        this.nome = newNome;
    }

    /**
     * @return the effetto
     */
    public int getEffetto() {
        return effetto;
    }

    /**
     * @param newEffetto the effetto to set
     */
    public void setEffetto(final int newEffetto) {
        this.effetto = newEffetto;
    }

    /**
     * @return the agisce
     */
    public String getAgisce() {
        return agisce;
    }

    /**
     * @param newAgisce the agisce to set
     */
    public void setAgisce(final String newAgisce) {
        this.agisce = newAgisce;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param newDescrizione the descrizione to set
     */
    public void setDescrizione(final String newDescrizione) {
        this.descrizione = newDescrizione;
    }
}
