/**
 *
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.util.List;

/**
 * Struttura dati delle carte azione
 */
public class CarteAzione {
    /**
     * Attributi: id: Numero identificativo della carta lettera: Lettera della
     * carta agisce: Campo della partita in cui agisce effetto: Numero che
     * modifica il campo in cui agisce descrizione: Descrizione della carta
     * azione
     */
    private int id;
    private String lettera;
    private String nome;
    private int effetto;
    private String agisce;
    private String descrizione;

    /**
     * Crea una lista di tipo <CarteAzione> effettuando il parsing di un file
     * xml contenente le carte azione.
     *
     * @return la lista delle carte azioni lette dal file xml
     */
    public static List<CarteAzione> crealistaazioni() {
        List<CarteAzione> carte;

        // Creo l'istanza parser
        final XmlParser parser = new XmlParser();

        // Parso il file
        carte = parser.parseXmlAzioni(ResourceLoader.load("carteAzione.xml"));

        return carte;
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
        for (Scuderia scuderia : scuderie) {

            // Prendo le carte azione della scuderia
            final List<CarteAzione> carte = scuderia.getCarteAzione();

            if (carte != null) {
                // Controllo tutte le carte della scuderia
                for (int j = 0; j < carte.size(); j++) {
                    // Analizzo solo le carte azione che influiscono la partenza
                    if (carte.get(j).getAgisce().equals("Partenza")) {
                        if (carte.get(j).getEffetto() < 0 && scuderia.getMovimento() > 0) {
                            // Applico l'effetto
                            scuderia.setMovimento(scuderia.getMovimento() + carte.get(j).getEffetto());
                        }
                        if (carte.get(j).getEffetto() == 0) {
                            // Applico l'effetto
                            scuderia.setMovimento(0);
                        }
                        if (carte.get(j).getEffetto() == 1) {
                            // Applico l'effetto
                            scuderia.setMovimento(scuderia.getMovimento() + 1);

                        } else if (carte.get(j).getEffetto() > 0) {
                            // Applico l'effetto
                            scuderia.setMovimento(carte.get(j).getEffetto());
                        }

                        // Rimuovo la carta
                        scuderia.removeCartaAzione(j);
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
        for (Scuderia scuderia : scuderie) {

            // Prendo le carte azione della scuderia
            final List<CarteAzione> carte = scuderia.getCarteAzione();

            // Controllo tutte le carte della scuderia
            for (CarteAzione carta : carte) {
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
        for (Scuderia scuderia : scuderie) {

            // Prendo le carte azione della scuderia
            List<CarteAzione> carte = scuderia.getCarteAzione();

            // Controllo tutte le carte della scuderia
            for (CarteAzione carta : carte) {
                // Analizzo solo le carte azione che influiscono lo sprint
                if (carta.getAgisce().equals("Sprint")) {
                    if (carta.getEffetto() == -1 && scuderia.getSprint() > 0) {
                        scuderia.setSprint(scuderia.getSprint() - 1);

                    }
                    if (carta.getEffetto() == 0) {
                        scuderia.setSprint(0);
                    }
                    if (carta.getEffetto() == 1 && scuderia.getSprint() > 0 && scuderia.getSprint() < Parametri.TRE) {
                        scuderia.setSprint(scuderia.getSprint() + 1);
                    }
                    if (carta.getEffetto() == 2) {
                        if (scuderia.getSprint() > 0) {
                            scuderia.setSprint(Parametri.TRE);
                        } else {
                            scuderia.setSprint(2);
                        }

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

            // Prendo le carte azione della scuderia
            List<CarteAzione> carte = scuderia.getCarteAzione();

            // Controllo tutte le carte della scuderia
            for (CarteAzione carta : carte) {
                // Analizzo solo le carte azione che influiscono il fotofinish
                if ("Fotofinish".equals(carta.getAgisce())) {
                    if (carta.getEffetto() == 0) {
                        scuderia.setFotofinish(0);
                    }
                    if (carta.getEffetto() == 1) {
                        scuderia.setFotofinish(1);
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

            // Prendo le carte azione della scuderia
            List<CarteAzione> carte = scuderia.getCarteAzione();

            // Controllo tutte le carte della scuderia
            for (CarteAzione carta : carte) {
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return the lettera
     */
    public String getLettera() {
        return lettera;
    }

    /**
     * @param lettera the lettera to set
     */
    public void setLettera(final String lettera) {
        this.lettera = lettera;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return the effetto
     */
    public int getEffetto() {
        return effetto;
    }

    /**
     * @param effetto the effetto to set
     */
    public void setEffetto(int effetto) {
        this.effetto = effetto;
    }

    /**
     * @return the agisce
     */
    public String getAgisce() {
        return agisce;
    }

    /**
     * @param agisce the agisce to set
     */
    public void setAgisce(String agisce) {
        this.agisce = agisce;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}