package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO, Luca Gregori(492480) & Cristian Rotaru(492486)
 * @see Stanza
 * @version 1.5
 */

public class Partita {
	
	private Giocatore giocatore;
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private boolean finita;
	
	public Partita(){
		this.giocatore = new Giocatore();
		this.labirinto = new Labirinto();
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.finita = false;
	}
	
	/**
	 * Questo metodo permette di prelevare un attrezzo dalla stanza corrente e
	 * depositarlo nella borsa del giocatore.
	 * @param nomeAttrezzo - parametro di ricerca per trovare l'attrezzo nella stanza corrente.
	 */
	public void prendiAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoPreso = this.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(this.stanzaCorrente.removeAttrezzo(attrezzoPreso)) {
			if(giocatore.posaAttrezzoInBorsa(attrezzoPreso))
				System.out.println("Attrezzo "+ attrezzoPreso + " prelevato");
		}else
			System.out.println("Non esiste alcun attrezzo " + nomeAttrezzo + " nella stanza " + this.stanzaCorrente.getNome());
	}
	
	/**
	 * Questo metodo permette di prelevare un attrezzo dalla borsa e 
	 * depositarlo nella stanza corrente.
	 * @param nomeAttrezzo - parametro di ricerca per trovare l'attrezzo nella borsa.
	 */
	public void posaAttrezzo(String nomeAttrezzo) {
	    Attrezzo attrezzoInBorsa = this.giocatore.prendiAttrezzoInBorsa(nomeAttrezzo);
		if(attrezzoInBorsa == null)
			System.out.println("Attrezzo " + nomeAttrezzo + " non presente in borsa");
		else if(this.stanzaCorrente.addAttrezzo(attrezzoInBorsa))
			System.out.println("Attrezzo " + nomeAttrezzo + " posato");
	}


	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaFinale();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente().equals(this.getStanzaVincente());
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCFU() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCFU();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCFU(cfu); 	
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
}
