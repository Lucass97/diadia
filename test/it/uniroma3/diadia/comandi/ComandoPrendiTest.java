package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {
	
	private Attrezzo falce;
	private Attrezzo piccone;
	private Partita partita;
	private ComandoPrendi prendi;

	@Before
	public void setUp() throws Exception {
		this.falce = new Attrezzo("falce", 2);
		this.piccone = new Attrezzo("piccone",3);
		
		/* Caso partita1:
		 * Oggetti presenti nella stanza corrente: osso */
		this.partita = new Partita();
		
		this.prendi = new ComandoPrendi();
	}

	@Test
	public void testEseguiComando() {
		assertNotNull(this.partita.getStanzaCorrente().getAttrezzo("osso"));
		this.prendi.setParametro("osso");
		this.prendi.eseguiComando(this.partita);
		assertNull(this.partita.getStanzaCorrente().getAttrezzo("osso"));
	}
	
	@Test
	public void testEseguiComandoConParametroNonValido() {
		assertNull(this.partita.getStanzaCorrente().getAttrezzo("falce"));
		this.prendi.setParametro("falce");
		this.prendi.eseguiComando(this.partita);
		assertNull(this.partita.getStanzaCorrente().getAttrezzo("falce"));
		assertNotNull(this.partita.getStanzaCorrente().getAttrezzo("osso"));
	}
	
}
