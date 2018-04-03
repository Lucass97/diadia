package it.uniroma3.diadia.giocatore;

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
	
	public Borsa getBorsa() {
		return borsa;
	}
	public Giocatore() {
		this.CFU = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
}
