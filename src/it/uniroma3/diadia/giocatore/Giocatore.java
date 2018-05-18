package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella un giocatore
 * @author Luca Gregori (492480) & Cristian Rotaru (492486)
 * @version 1.0
 * @see Borsa
 */
public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	
	private int CFU;
	private Borsa borsa;

	public int getCFU() {
		return this.CFU;
	}

	public void setCFU(int cfu) {
		this.CFU = cfu;
	}
	
	/**
	 * Questo metodo permette di prelevare un attrezzo dalla borsa.
	 * L'attrezzo prelevato non sarà più prensente nella borsa.
	 * @param nomeAttrezzo - paramentro di ricerca dell'attrezzo.
	 * @return un riferimento all'attrezzo appena prelevato,
	 * null in caso non lo si trovi.
	 */
	public Attrezzo prendiAttrezzoInBorsa(String nomeAttrezzo) {
		Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);
		borsa.removeAttrezzo(nomeAttrezzo);
		return attrezzo;
	}
	
	/**
	 * Questo metodo permette di posare un attrezzo nella borsa.
	 * @param attrezzo - parametro di ricerca.
	 * @return true se l'attrezzo è stato aggiunto alla borsa correttamente,
	 * false altrimenti
	 */
	public boolean posaAttrezzoInBorsa(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	
	public String toStringBorsa() {
		return this.borsa.toString();
	}
	
	public Giocatore() {
		this(CFU_INIZIALI);
	}
	
	public Giocatore(int cfuIniziali) {
		this.CFU = cfuIniziali;
		this.borsa = new Borsa();
	}
	
}
