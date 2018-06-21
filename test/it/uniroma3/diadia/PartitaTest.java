package it.uniroma3.diadia;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Partita partita1;
	private Partita partita2;
	private Partita partita3;
	private Stanza biblioteca;
	private Stanza atrio;
	private Stanza aulaN11;

	
	@Before
	public void setUp() throws Exception {
		this.partita1 = new Partita();
		this.partita2 = new Partita();
		this.partita3 = new Partita();
		
		this.biblioteca = new Stanza("Biblioteca");
		this.atrio = new Stanza("Atrio");
		atrio.impostaStanzaAdiacente(Direzione.convertString("est"), aulaN11);
		this.aulaN11 = new Stanza("Aula N11");
		
		this.partita2.setStanzaCorrente(aulaN11);
		this.partita2.getGiocatore().setCFU(0);
		this.partita3.getGiocatore().setCFU(5);
		this.partita3.setStanzaCorrente(biblioteca);
	}

	@Test
	public void testGetStanzaVincente() {
		assertEquals(this.biblioteca, this.partita1.getStanzaVincente());
		assertEquals(this.biblioteca, this.partita2.getStanzaVincente());
	}

	@Test
	public void testGetStanzaCorrente() {
		assertEquals(this.atrio, this.partita1.getStanzaCorrente());
		assertNotEquals(this.aulaN11, this.partita1.getStanzaCorrente());
		assertNotEquals(this.biblioteca, this.partita1.getStanzaCorrente());
		
		assertEquals(this.aulaN11, this.partita2.getStanzaCorrente());
		assertNotEquals(this.atrio, this.partita2.getStanzaCorrente());
		assertNotEquals(this.biblioteca, this.partita2.getStanzaCorrente());
		
		assertEquals(this.biblioteca, this.partita3.getStanzaCorrente());
		assertNotEquals(this.atrio, this.partita3.getStanzaCorrente());
		assertNotEquals(this.atrio, this.partita2.getStanzaCorrente());
	}
	
	@Test
	public void testGetCFU(){
		assertEquals(20, this.partita1.getGiocatore().getCFU());
		assertEquals(0, this.partita2.getGiocatore().getCFU());
		assertEquals(5, this.partita3.getGiocatore().getCFU());
	}

	@Test
	public void testVinta() {
		assertFalse(partita1.vinta());
		assertFalse(partita2.vinta());
		assertTrue(partita3.vinta());
	}

	@Test
	public void testIsFinita() {
		assertFalse(partita1.isFinita());
		assertTrue(partita2.isFinita());
		assertTrue(partita3.isFinita());
	}

}
