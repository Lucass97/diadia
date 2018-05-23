package it.uniroma3.diadia.giocatore;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {

	private Giocatore giocatore1;
	private Giocatore giocatore2; 
	private Attrezzo forbice;
    private Attrezzo fucile;
    private Attrezzo pala;
	private Attrezzo osso;
	private Attrezzo piccone;
	private Attrezzo mazza;
	
	/**
	 * Set up di due casi:
	 * this.giocatore1 -> giocatore con borsa vuota.
	 * this.giocatore2 -> giocatore con degli attrezzi.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		/* Creazione giocatori */
		this.giocatore1 = new Giocatore();
		this.giocatore2 = new Giocatore();
		
		/* Creazione oggetti */
		this.forbice = new Attrezzo("Forbice", 1);
		this.fucile = new Attrezzo("Fucile", 5);
		this.pala = new Attrezzo("Pala",2);
		this.osso = new Attrezzo("Osso",3);
		this.piccone = new Attrezzo("Piccone",2);
		this.mazza = new Attrezzo("Mazza",2);
		
		/* Riempimento borsa del giocatore2 */
		this.giocatore2.posaAttrezzoInBorsa(this.forbice);
		this.giocatore2.posaAttrezzoInBorsa(this.fucile);
		this.giocatore2.posaAttrezzoInBorsa(this.mazza);
		this.giocatore2.posaAttrezzoInBorsa(this.piccone);
		this.giocatore2.posaAttrezzoInBorsa(this.osso); // in realta non viene aggiunto
	}
	
	@Test
	public void testPosaAttrezzoInBorsa() {
		/* Test giocatore1 */
		assertTrue(this.giocatore1.posaAttrezzoInBorsa(this.forbice));
		assertTrue(this.giocatore1.posaAttrezzoInBorsa(this.fucile));
		assertTrue(this.giocatore1.posaAttrezzoInBorsa(this.pala));
		assertFalse(this.giocatore1.posaAttrezzoInBorsa(this.osso)); //non c'è piu spazio nella borsa
	}
	
	@Test
	public void testPrendiAttrezzoInBorsa() {
		
		/* Test giocatore2 */
		assertEquals(this.forbice, this.giocatore2.prendiAttrezzoInBorsa("Forbice"));
		assertEquals(this.fucile, this.giocatore2.prendiAttrezzoInBorsa("Fucile"));
		assertEquals(this.mazza, this.giocatore2.prendiAttrezzoInBorsa("Mazza"));
		assertEquals(this.piccone, this.giocatore2.prendiAttrezzoInBorsa("Piccone"));
		assertNull(this.giocatore2.prendiAttrezzoInBorsa("Osso")); //non c'è in borsa
		assertNull(this.giocatore2.prendiAttrezzoInBorsa("Pala")); //non c'è in borsa
		assertNull(this.giocatore2.prendiAttrezzoInBorsa("Forbice")); //è gia stato preso
	}


}
