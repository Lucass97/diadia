package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Questa classe modella un labirinto
 * @author Luca Gregori (492480) & Cristian Rotaru (492486)
 * @version 1.0
 * @see Stanza
 * @see Attrezzo
 */
public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	
	/***
	 * Questo metodo crea un labirinto, popolandolo di stanze.
	 * Ciascuna stanza verrà eventualmente popolata di uno o più attrezzi.
	 */
	private void creaLabirinto() {
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
    	Attrezzo piediporco = new Attrezzo("piediporco",5);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo pallina = new Attrezzo("pallina", 1);
		Attrezzo mantello = new Attrezzo("mantello",2);
		
		/* creo i personaggi */
		AbstractPersonaggio balto = new Cane("balto","Sono un cane parlante, qualche problema?","osso",pallina);
		AbstractPersonaggio mandragora = new Strega("mandragora", "Ihihih! Accetta il mio aiuto e ne uscirai vivo da qui.");
		AbstractPersonaggio merlino = new Mago("merlino", "Sono un mago tuttofare, se serve qualcosa chiedi pure.", mantello);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new StanzaMagica("Aula N11");
		Stanza aulaN10 = new StanzaBuia("Aula N10");
		Stanza laboratorio = new StanzaBloccata("Laboratorio Campus",Direzione.OVEST);
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzione.NORD, biblioteca);
		atrio.impostaStanzaAdiacente(Direzione.SUD, aulaN11);
		atrio.impostaStanzaAdiacente(Direzione.SUD, aulaN10);
		atrio.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.EST, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.OVEST, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.NORD, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.EST, aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
		laboratorio.impostaStanzaAdiacente(Direzione.EST, atrio);
		laboratorio.impostaStanzaAdiacente(Direzione.OVEST, aulaN11);
		biblioteca.impostaStanzaAdiacente(Direzione.SUD, atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		aulaN11.addAttrezzo(piediporco);
		atrio.addAttrezzo(osso);
		
		/* metto i personaggi nelle stanze */
		atrio.setPersonaggio(merlino);
		aulaN11.setPersonaggio(balto);
		aulaN10.setPersonaggio(mandragora);

		// il gioco comincia nell'atrio
        this.stanzaIniziale = atrio;  
		this.stanzaFinale = biblioteca;
	}
	
	/**
	 * Questo metodo restituisci la stanza iniziale del labirinto.
	 * @return un riferimento alla stanza inziale.
	 */
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	/**
	 * Questo metodo restituisce la stanza finale del labirinto.
	 * @return un riferimento alla stanza finale.
	 */
	public Stanza getStanzaFinale() {
		return this.stanzaFinale;
	}
	
	public Labirinto() {
		//this.creaLabirinto();
		CaricatoreLabirinto c;
		try {
			 c = new CaricatoreLabirinto("labirinto.txt");
				this.stanzaIniziale = c.getStanzaIniziale();
				this.stanzaFinale = c.getStanzaVincente();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.stanzaIniziale != null)
			System.out.println(this.stanzaIniziale);
		else
			System.out.println("null");
	}
}
