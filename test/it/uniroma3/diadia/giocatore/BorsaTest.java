package it.uniroma3.diadia.giocatore;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
    private Borsa borsa1;
    private Borsa borsa2;
    private Borsa borsa3;
    private Borsa borsa4;
    private Attrezzo forbice;
    private Attrezzo fucile;
    private Attrezzo pala;
    private Attrezzo osso;
    private Attrezzo piccone;
    private Attrezzo mazza;
	@Before
	public void setUp() throws Exception {
		this.borsa1 = new Borsa(); //per default peso massimo = 10
		this.borsa2 = new Borsa(); 
		this.borsa3 = new Borsa(); //borsa vuota
		this.borsa4 = new Borsa(20);
		
		this.forbice = new Attrezzo("Forbice", 1);
		this.fucile = new Attrezzo("Fucile", 5);
		this.pala = new Attrezzo("Pala",2);
		this.osso = new Attrezzo("Osso",3);
		this.piccone = new Attrezzo("Piccone",2);
		this.mazza = new Attrezzo("Mazza",2);
		
		this.borsa2.addAttrezzo(this.osso);
		this.borsa2.addAttrezzo(this.forbice);
		this.borsa2.addAttrezzo(this.piccone);
		this.borsa2.addAttrezzo(this.pala);
		
		this.borsa4.addAttrezzo(this.forbice);
		this.borsa4.addAttrezzo(this.fucile);
		this.borsa4.addAttrezzo(this.pala);
		this.borsa4.addAttrezzo(this.osso);
		this.borsa4.addAttrezzo(this.piccone);
		this.borsa4.addAttrezzo(this.mazza);
	}

	@Test
	public void testAddAttrezzo() {
		/* Test per borsa 1*/
		assertTrue(this.borsa1.addAttrezzo(this.forbice));
		assertTrue(this.borsa1.addAttrezzo(this.fucile));
		assertTrue(this.borsa1.addAttrezzo(this.pala));
		assertFalse(this.borsa1.addAttrezzo(this.osso)); //false perche supera il peso massimo
		assertTrue(this.borsa1.addAttrezzo(this.mazza));
		assertFalse(this.borsa1.addAttrezzo(this.piccone)); //false perche supera il peso massimo
	}

	@Test
	public void testGetAttrezzo() {
		/* Test per borsa 2*/
		assertSame(this.osso,this.borsa2.getAttrezzo("Osso"));
		assertSame(this.forbice,this.borsa2.getAttrezzo("Forbice"));
		assertSame(this.piccone,this.borsa2.getAttrezzo("Piccone"));
		assertSame(this.pala,this.borsa2.getAttrezzo("Pala"));
		assertNotSame(this.mazza,this.borsa2.getAttrezzo("Mazza"));
		assertNotSame(this.fucile,this.borsa2.getAttrezzo("Fucile"));
	}

	@Test
	public void testIsEmpty() {
		/* Test per borsa 2*/
		assertFalse(borsa2.isEmpty());
		assertTrue(borsa3.isEmpty());
	}

	@Test
	public void testHasAttrezzo() {
		/* Test per borsa 2*/
		assertTrue(this.borsa2.hasAttrezzo("Osso"));
		assertTrue(this.borsa2.hasAttrezzo("Forbice"));
		assertTrue(this.borsa2.hasAttrezzo("Piccone"));
		assertTrue(this.borsa2.hasAttrezzo("Pala"));
		assertFalse(this.borsa2.hasAttrezzo("Mazza"));
		assertFalse(this.borsa2.hasAttrezzo("Fucile"));
		
		/* Test per borsa 3*/
		assertFalse(this.borsa3.hasAttrezzo("Mazza"));
	}

	@Test
	public void testRemoveAttrezzo() {
		
		/* Test per borsa 3 */
		assertNull(this.borsa3.removeAttrezzo("Mazza"));
		
		/* Test per borsa 4 */
		assertSame(this.forbice,this.borsa4.removeAttrezzo("Forbice"));
		assertFalse(this.borsa4.hasAttrezzo("Forbice"));
		
		assertSame(this.osso,this.borsa4.removeAttrezzo("Osso"));
		assertFalse(this.borsa4.hasAttrezzo("Osso"));
		
		assertSame(this.fucile,this.borsa4.removeAttrezzo("Fucile"));
		assertFalse(this.borsa4.hasAttrezzo("Fucile"));
		
	}
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		List<Attrezzo> listaOrdinata = this.borsa4.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> iteratoreAttrezzi = listaOrdinata.iterator();
		assertEquals(this.forbice, iteratoreAttrezzi.next());
		assertEquals(this.mazza, iteratoreAttrezzi.next());
		assertEquals(this.pala, iteratoreAttrezzi.next());
		assertEquals(this.piccone, iteratoreAttrezzi.next());
		assertEquals(this.osso, iteratoreAttrezzi.next());
		assertEquals(this.fucile, iteratoreAttrezzi.next());
		System.out.println("testGetContenutoOrdinatoPerPeso() \n\t-> " + listaOrdinata + "\n");
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> insiemeOrdinato = this.borsa4.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> iteratoreAttrezzi = insiemeOrdinato.iterator();
		assertEquals(this.forbice, iteratoreAttrezzi.next());
		assertEquals(this.fucile, iteratoreAttrezzi.next());
		assertEquals(this.mazza, iteratoreAttrezzi.next());
		assertEquals(this.osso, iteratoreAttrezzi.next());
		assertEquals(this.pala, iteratoreAttrezzi.next());
		assertEquals(this.piccone, iteratoreAttrezzi.next());
		System.out.println("testGetContenutoOrdinatoPerNome() \n\t-> " + insiemeOrdinato + "\n");
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Map<Integer,Set<Attrezzo>> raggruppamento = this.borsa4.getContenutoRaggruppatoPerPeso();
		Iterator<Attrezzo> iteratoreAttrezziConStessoPeso;
		iteratoreAttrezziConStessoPeso = raggruppamento.get(1).iterator();
		assertEquals(this.forbice, iteratoreAttrezziConStessoPeso.next());
		iteratoreAttrezziConStessoPeso = raggruppamento.get(2).iterator();
		assertEquals(this.mazza, iteratoreAttrezziConStessoPeso.next());
		assertEquals(this.pala, iteratoreAttrezziConStessoPeso.next());
		assertEquals(this.piccone, iteratoreAttrezziConStessoPeso.next());
		iteratoreAttrezziConStessoPeso = raggruppamento.get(3).iterator();
		assertEquals(this.osso, iteratoreAttrezziConStessoPeso.next());
		iteratoreAttrezziConStessoPeso = raggruppamento.get(5).iterator();
		assertEquals(this.fucile, iteratoreAttrezziConStessoPeso.next());
		System.out.println("testGetContenutoRaggruppatoPerPeso() \n\t-> " + raggruppamento + "\n");
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> insiemeOrdinato = this.borsa4.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> iteratoreAttrezzi = insiemeOrdinato.iterator();
		assertEquals(this.forbice, iteratoreAttrezzi.next());
		assertEquals(this.mazza, iteratoreAttrezzi.next());
		assertEquals(this.pala, iteratoreAttrezzi.next());
		assertEquals(this.piccone, iteratoreAttrezzi.next());
		assertEquals(this.osso, iteratoreAttrezzi.next());
		assertEquals(this.fucile, iteratoreAttrezzi.next());
		System.out.println("testGetSortedSetOrdinatoPerPeso() \n\t-> " + insiemeOrdinato + "\n");
	}

}
