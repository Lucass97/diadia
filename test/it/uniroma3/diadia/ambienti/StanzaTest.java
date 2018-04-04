package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	Stanza n11;
	Stanza n12;
	Stanza n13;
	Attrezzo coltello;
	Attrezzo falce;
	Attrezzo spada;
	Attrezzo mouse;
	Attrezzo topo;
	Attrezzo osso;
	Attrezzo pantofola;
	
	@Before
	public void setUp() throws Exception {
		
		this.n11 = new Stanza("n11");
		this.n12 = new Stanza("n12");
		this.n13 = new Stanza("n13");
		
		//inizializzazione attrezzi
		this.osso = new Attrezzo("Osso",5);
		this.pantofola = new Attrezzo("Pantofola",1);
		this.mouse = new Attrezzo("Mouse",1);
		this.topo = new Attrezzo("Topo",1);
		this.coltello = new Attrezzo("Coltello",1);
		this.falce = new Attrezzo("Falce",2);
		this.spada = new Attrezzo("Spada",3);
	}

	@Test
	public void testAddAttrezzo() {
		assertTrue(n11.addAttrezzo(this.osso));
	}

	@Test
	public void testHasAttrezzo() {
		assertTrue(n11.addAttrezzo(this.pantofola));
		assertTrue(n11.hasAttrezzo("Pantofola"));
		assertFalse("Errore 2",n11.hasAttrezzo("Lanterna"));
	}

	@Test
	public void testGetAttrezzo() {
		assertTrue(n11.addAttrezzo(this.mouse));
		assertTrue(n11.addAttrezzo(this.topo));
		
		assertEquals(this.mouse, n11.getAttrezzo("Mouse"));
		assertEquals(this.topo, n11.getAttrezzo("Topo"));
		assertSame(this.mouse, n11.getAttrezzo("Mouse"));
		assertSame(this.topo, n11.getAttrezzo("Topo"));
		assertNull(n11.getAttrezzo("Cane"));
		
	}
	
	@Test
	public void testGetAttrezzi() {
		Attrezzo [] attrezzi = new Attrezzo[10];
		attrezzi[0] = this.coltello;
		attrezzi[1] = this.falce;
		attrezzi[2] = this.spada;
		assertTrue(n12.addAttrezzo(coltello));
		assertTrue(n12.addAttrezzo(falce));
		assertTrue(n12.addAttrezzo(spada));
		assertArrayEquals(attrezzi, n12.getAttrezzi());
		
		
	}

	@Test
	public void testRemoveAttrezzo() {
		assertFalse(n13.removeAttrezzo(this.pantofola));
		assertTrue(n13.addAttrezzo(this.coltello));
		assertTrue(n13.addAttrezzo(this.falce));
		assertTrue(n13.addAttrezzo(this.spada));
		assertTrue(n13.removeAttrezzo(this.spada));
		
	}

}
