package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {
	
	private Attrezzo falce;
	private Attrezzo piccone;
	private Partita partita;
	private ComandoPosa posa;
	
	@Before
	public void setUp() throws Exception {
		
		this.falce = new Attrezzo("falce", 2);
		this.piccone = new Attrezzo("piccone",3);
		
		/* Caso partita1:
		 * Oggetti presenti in borsa: falce e piccone */
		this.partita = new Partita();
		this.partita.getGiocatore().posaAttrezzoInBorsa(this.falce);
		this.partita.getGiocatore().posaAttrezzoInBorsa(this.piccone);
		
		this.posa = new ComandoPosa();
	}

	@Test
	public void testEseguiComando() {
		
		this.posa.setParametro("falce"); 
		this.posa.eseguiComando(this.partita);
		assertSame(this.falce,this.partita.getStanzaCorrente().getAttrezzo("falce"));
		
		this.posa.setParametro("piccone"); 
		this.posa.eseguiComando(this.partita);
		assertSame(this.piccone,this.partita.getStanzaCorrente().getAttrezzo("piccone"));
	}
	
	@Test
	public void testEseguiComandoConParametroNonValido() {
		this.posa.setParametro("martello"); 
		this.posa.eseguiComando(this.partita);
		assertNull(this.partita.getStanzaCorrente().getAttrezzo("martello"));
		
		this.posa.setParametro("cintura"); 
		this.posa.eseguiComando(this.partita);
		assertNull(this.partita.getStanzaCorrente().getAttrezzo("cintura"));
	}

}
