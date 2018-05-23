package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO, Luca Gregori
 * @see Attrezzo
 * @version 1.3
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	
	private String nome;
    private List<Attrezzo> attrezzi;
    private Map<String,Stanza> stanzeAdiacenti; 
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome - il nome della stanza.
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>(); 
        this.attrezzi = new ArrayList<Attrezzo>();
    }

    /**
     * Imposta una stanza adiacente.
     * @param direzione - direzione in cui sara' posta la stanza adiacente.
     * @param stanza - stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	if(this.stanzeAdiacenti.size() == NUMERO_MASSIMO_DIREZIONI)
    		return;
    	else
    		this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata.
     * @param direzione - usata come parametro di ricerca.
     * @return la stanza corrispondente alla direzione, null se non esiste.
     */
	public Stanza getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce il nome della stanza.
     * @return il nome della stanza.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza.
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	return this.attrezzi.add(attrezzo);
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa.
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : this.stanzeAdiacenti.keySet()) {
    		risultato.append(" " + direzione);
    	}
    	risultato.append("\nAttrezzi nella stanza: ");
    	for(Attrezzo attrezzo : this.attrezzi)
    		risultato.append(attrezzo.toString()+" ");
    	
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @param nomeAttrezzo - chiave di ricerca.
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
    	return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo - usato come chiave di ricerca.
	 * @return l'attrezzo presente nella stanza.
     * null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzoCercato : this.attrezzi) {
			if (attrezzoCercato.getNome().equals(nomeAttrezzo))
				return attrezzoCercato;
		}
		return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza.
	 * @param attrezzo - usato come chiave di ricerca.
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo);
	}

	/**
	 * Questo metodo restituisce l'insieme delle direzione disponibili attualmente.
	 * @return un insieme di direzioni.
	 */
	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
    }
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	
	/**
	 * Metodo che confronta due stanze tra di loro.
	 * Due stanza sono diverse se e solo se ha il nome diverso.
	 */
	@Override
	public boolean equals(Object o) {
		Stanza stanzaDaConfrontare = (Stanza) o;
		if(this.nome.equals(stanzaDaConfrontare.getNome()))
			return true;
		return false;
	}
	
	/**
	 * Metodo che genera un hash code unico in grado di identificare l'oggetto corrente.
	 * l'hash code in questione è generato usando l'hash code del nome della stanza.
	 */
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

}