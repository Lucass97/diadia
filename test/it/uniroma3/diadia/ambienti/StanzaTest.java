package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	Stanza n11;
	Stanza n12;
	Attrezzo coltello;
	Attrezzo falce;
	Attrezzo spada;
	Attrezzo mouse;
	Attrezzo topo;
	Attrezzo osso;
	Attrezzo pantofola;
	
	/**
	 * Due casi:
	 * this.n11 -> stanza vuota
	 * this.n12 -> stanza con qualche attrezzo
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		/* Creazione stanze */
		this.n11 = new Stanza("n11");
		this.n12 = new Stanza("n12");
		
		/* Inizializzazione attrezzi */
		this.osso = new Attrezzo("Osso",5);
		this.pantofola = new Attrezzo("Pantofola",1);
		this.mouse = new Attrezzo("Mouse",1);
		this.topo = new Attrezzo("Topo",1);
		this.coltello = new Attrezzo("Coltello",1);
		this.falce = new Attrezzo("Falce",2);
		this.spada = new Attrezzo("Spada",3);
		
		this.n12.addAttrezzo(this.osso);
		this.n12.addAttrezzo(this.pantofola);
		this.n12.addAttrezzo(this.spada);
		this.n12.addAttrezzo(this.topo);
		
	}
	
	@Test
	public void testAddAttrezzo() {
		
		/* Test per n11, ovvero la stanza vuota */
		assertTrue(n11.addAttrezzo(this.osso));
		assertTrue(n11.addAttrezzo(this.spada));
		
		/*===========================================================*/
		
		/* Test per n12, ovvero la stanza con qualche attrezzo. */
		// I seguenti attrezzi che verranno aggiunti non erano già presenti nella stanza.
		assertTrue(n12.addAttrezzo(this.falce));
		assertTrue(n12.addAttrezzo(this.coltello));
	}

	@Test
	public void testHasAttrezzo() {
		
		/* Test per n11, ovvero la stanza vuota */
		assertFalse(n11.hasAttrezzo(this.osso.getNome()));
		assertFalse(n11.hasAttrezzo(this.pantofola.getNome()));
		assertFalse(n11.hasAttrezzo(this.mouse.getNome()));
		assertFalse(n11.hasAttrezzo(this.topo.getNome()));
		assertFalse(n11.hasAttrezzo(this.coltello.getNome()));
		assertFalse(n11.hasAttrezzo(this.falce.getNome()));
		assertFalse(n11.hasAttrezzo(this.spada.getNome()));
		
		/*===========================================================*/
		
		/* Test per n12, ovvero la stanza con qualche attrezzo */
		
		// test per gli attrezzi presenti nella stanza
		assertTrue(n12.hasAttrezzo(this.osso.getNome()));
		assertTrue(n12.hasAttrezzo(this.pantofola.getNome()));
		assertTrue(n12.hasAttrezzo(this.spada.getNome()));
		assertTrue(n12.hasAttrezzo(this.topo.getNome()));
		// test per gli attrezzi non presenti nella stanza
		assertFalse(n12.hasAttrezzo(this.falce.getNome()));
		assertFalse(n12.hasAttrezzo(this.coltello.getNome()));
		assertFalse(n12.hasAttrezzo(this.mouse.getNome()));
		
		/*===========================================================*/
		
	}

	@Test
	public void testGetAttrezzo() {
		
		/* Test per n11, ovvero la stanza vuota */
		assertNull(n11.getAttrezzo(this.osso.getNome()));
		assertNull(n11.getAttrezzo(this.pantofola.getNome()));
		assertNull(n11.getAttrezzo(this.mouse.getNome()));
		assertNull(n11.getAttrezzo(this.topo.getNome()));
		assertNull(n11.getAttrezzo(this.coltello.getNome()));
		assertNull(n11.getAttrezzo(this.falce.getNome()));
		assertNull(n11.getAttrezzo(this.spada.getNome()));
		assertNull(n11.getAttrezzo("Cane"));
		
		/*===========================================================*/
		
		/* Test per n12, ovvero la stanza con qualche attrezzo */
		assertEquals(this.osso, n12.getAttrezzo(this.osso.getNome()));
		assertEquals(this.pantofola, n12.getAttrezzo(this.pantofola.getNome()));
		assertEquals(this.spada, n12.getAttrezzo(this.spada.getNome()));
		assertEquals(this.topo, n12.getAttrezzo(this.topo.getNome()));
		
		assertNull(n12.getAttrezzo(this.falce.getNome()));
		assertNull(n12.getAttrezzo(this.coltello.getNome()));
		assertNull(n12.getAttrezzo(this.mouse.getNome()));
		assertNull(n12.getAttrezzo("Cane"));
		
		/*===========================================================*/
	}
	
	@Test
	public void testGetAttrezzi() {
		
		/*Creazione possibili liste */
		
		List <Attrezzo> listaAttrezziInN12CheMiAspetto = new ArrayList<Attrezzo>();
		listaAttrezziInN12CheMiAspetto.add(this.osso);
		listaAttrezziInN12CheMiAspetto.add(this.pantofola);
		listaAttrezziInN12CheMiAspetto.add(this.spada);
		listaAttrezziInN12CheMiAspetto.add(this.topo);
		
		List <Attrezzo> listaAttrezziDiversa = new ArrayList<Attrezzo>();
		listaAttrezziDiversa.add(this.falce);
		listaAttrezziDiversa.add(this.spada);
		listaAttrezziDiversa.add(this.osso);
		
		/*===========================================================*/
		
		/* Test per n11, ovvero la stanza vuota */
		assertNotEquals(listaAttrezziInN12CheMiAspetto, n11.getAttrezzi());
		assertNotEquals(listaAttrezziDiversa, n11.getAttrezzi());
		
		/*===========================================================*/
		
		/* Test per n12, ovvero la stanza con qualche attrezzo */
		assertEquals(listaAttrezziInN12CheMiAspetto,n12.getAttrezzi());
		assertNotEquals(listaAttrezziDiversa, n12.getAttrezzi());
		
		/*===========================================================*/
	}

	@Test
	public void testRemoveAttrezzo() {
		/* Test per n11, ovvero la stanza vuota */
		
		assertFalse(this.n11.removeAttrezzo(this.falce));
		assertFalse(this.n11.removeAttrezzo(this.spada));
		
		/*===========================================================*/
		
		/* Test per n12, ovvero la stanza con qualche attrezzo */
		
		assertTrue(this.n12.removeAttrezzo(this.spada));
		assertFalse(this.n12.removeAttrezzo(this.spada)); // questo perche si puo rimuovere una sola volta
		assertTrue(this.n12.removeAttrezzo(this.pantofola));
		assertFalse(this.n12.removeAttrezzo(this.pantofola));
		assertFalse(this.n12.removeAttrezzo(this.falce));
		assertFalse(this.n12.removeAttrezzo(this.mouse));
		
		/*===========================================================*/
		
	}

}
