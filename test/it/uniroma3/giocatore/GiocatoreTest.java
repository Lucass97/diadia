package it.uniroma3.giocatore;
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
	
	
	@Before
	public void setUp() throws Exception {
		this.giocatore1 = new Giocatore();
		this.giocatore2 = new Giocatore();
		
		this.forbice = new Attrezzo("Forbice", 1);
		this.fucile = new Attrezzo("Fucile", 5);
		this.pala = new Attrezzo("Pala",2);
		this.osso = new Attrezzo("Osso",3);
		this.piccone = new Attrezzo("Piccone",2);
		this.mazza = new Attrezzo("Mazza",2);
		
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
		assertNull(this.giocatore2.prendiAttrezzoInBorsa("Osso"));
		assertNull(this.giocatore2.prendiAttrezzoInBorsa("Pala"));
		assertNull(this.giocatore2.prendiAttrezzoInBorsa("Forbice")); //è gia stato preso
	}


}
