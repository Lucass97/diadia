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
	
    private Borsa borsaVuota;
    private Borsa borsaStandard;
    private Borsa borsaCapiente;
    
    private Attrezzo forbice;
    private Attrezzo fucile;
    private Attrezzo pala;
    private Attrezzo osso;
    private Attrezzo piccone;
    private Attrezzo mazza;
    
    /**
     * Tre casi:
     * this.borsaVuota -> senza attrezzi e capienza pari a 10 (standard)
     * this.borsaStandard -> con qualche attrezzo e capienza pari a 10 (standard)
     * this.borsaCapiente -> con qualche attrezzo e capienza pari a 20
     * @throws Exception
     */
	@Before
	public void setUp() throws Exception {
		
		/* Creazione borse */
		this.borsaVuota = new Borsa(); // per default peso massimo = 10 
		this.borsaStandard = new Borsa(); // per default peso massimo = 10
		this.borsaCapiente = new Borsa(20); // peso massimo = 20
		
		/*===========================================================*/
		
		/* Inizializzazione attrezzi */
		this.forbice = new Attrezzo("Forbice", 1);
		this.fucile = new Attrezzo("Fucile", 5);
		this.pala = new Attrezzo("Pala",2);
		this.osso = new Attrezzo("Osso",3);
		this.piccone = new Attrezzo("Piccone",2);
		this.mazza = new Attrezzo("Mazza",2);
		
		/*===========================================================*/
		
		this.borsaStandard.addAttrezzo(this.osso);
		this.borsaStandard.addAttrezzo(this.forbice);
		this.borsaStandard.addAttrezzo(this.piccone);
		this.borsaStandard.addAttrezzo(this.pala);
		
		this.borsaCapiente.addAttrezzo(this.forbice);
		this.borsaCapiente.addAttrezzo(this.fucile);
		this.borsaCapiente.addAttrezzo(this.pala);
		this.borsaCapiente.addAttrezzo(this.osso);
		this.borsaCapiente.addAttrezzo(this.piccone);
		this.borsaCapiente.addAttrezzo(this.mazza);
		
		/*===========================================================*/
	}

	@Test
	public void testAddAttrezzo() {
		/* Test per borsa vuota */
		assertTrue(this.borsaVuota.addAttrezzo(this.forbice));
		assertTrue(this.borsaVuota.addAttrezzo(this.fucile));
		assertTrue(this.borsaVuota.addAttrezzo(this.pala));
		assertFalse(this.borsaVuota.addAttrezzo(this.osso)); //false perche supera il peso massimo
		assertTrue(this.borsaVuota.addAttrezzo(this.mazza));
		assertFalse(this.borsaVuota.addAttrezzo(this.piccone)); //false perche supera il peso massimo
	}

	@Test
	public void testGetAttrezzo() {
		/* Test per borsa standard*/
		assertEquals(this.osso,this.borsaStandard.getAttrezzo("Osso"));
		assertEquals(this.forbice,this.borsaStandard.getAttrezzo("Forbice"));
		assertEquals(this.piccone,this.borsaStandard.getAttrezzo("Piccone"));
		assertEquals(this.pala,this.borsaStandard.getAttrezzo("Pala"));
		assertNull(this.borsaStandard.getAttrezzo("Mazza"));
		assertNull(this.borsaStandard.getAttrezzo("Fucile"));
	}

	@Test
	public void testIsEmpty() {
		/* Test per borsa vuota*/
		assertTrue(borsaVuota.isEmpty());
		
		/*===========================================================*/
		
		/* Test per borsa standard e borsa capiente*/
		assertFalse(borsaStandard.isEmpty());
		assertFalse(borsaCapiente.isEmpty());
		
		/*===========================================================*/
		
	}

	@Test
	public void testHasAttrezzo() {
		/* Test per borsa standard*/
		assertTrue(this.borsaStandard.hasAttrezzo("Osso"));
		assertTrue(this.borsaStandard.hasAttrezzo("Forbice"));
		assertTrue(this.borsaStandard.hasAttrezzo("Piccone"));
		assertTrue(this.borsaStandard.hasAttrezzo("Pala"));
		assertFalse(this.borsaStandard.hasAttrezzo("Mazza"));
		assertFalse(this.borsaStandard.hasAttrezzo("Fucile"));
	}

	@Test
	public void testRemoveAttrezzo() {
		
		/* Test per borsa vuota */
		assertNull(this.borsaVuota.removeAttrezzo("Mazza"));
		
		/*===========================================================*/
		
		/* Test per borsa standard */
		assertEquals(this.forbice,this.borsaStandard.removeAttrezzo("Forbice"));
		assertNull(this.borsaStandard.removeAttrezzo("Forbice"));
		
		assertEquals(this.osso,this.borsaStandard.removeAttrezzo("Osso"));
		assertNull(this.borsaStandard.removeAttrezzo("Osso"));
		
		assertEquals(this.piccone,this.borsaStandard.removeAttrezzo("Piccone"));
		assertNull(this.borsaStandard.removeAttrezzo("Piccone"));
		
		assertNull(this.borsaStandard.removeAttrezzo("Fucile"));
		
		/*===========================================================*/
		
		/* Test per borsa capiente */
		assertEquals(this.forbice,this.borsaCapiente.removeAttrezzo("Forbice"));
		assertNull(this.borsaCapiente.removeAttrezzo("Forbice"));
		
		assertEquals(this.osso,this.borsaCapiente.removeAttrezzo("Osso"));
		assertNull(this.borsaCapiente.removeAttrezzo("Osso"));
		
		assertEquals(this.fucile,this.borsaCapiente.removeAttrezzo("Fucile"));
		assertNull(this.borsaCapiente.removeAttrezzo("Fucile"));
		
		/*===========================================================*/
		
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		/* Test per borsa capiente */
		List<Attrezzo> listaOrdinata = this.borsaCapiente.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> iteratoreAttrezzi = listaOrdinata.iterator();
		
		assertEquals(this.forbice, iteratoreAttrezzi.next()); //Forbice:1
		assertEquals(this.mazza, iteratoreAttrezzi.next()); //Mazza:2
		assertEquals(this.pala, iteratoreAttrezzi.next()); //Pala:2
		assertEquals(this.piccone, iteratoreAttrezzi.next()); //Piccone:2
		assertEquals(this.osso, iteratoreAttrezzi.next()); //Osso:3
		assertEquals(this.fucile, iteratoreAttrezzi.next()); //Fucile:5
		
		System.out.println("testGetContenutoOrdinatoPerPeso() \n\t-> " + listaOrdinata + "\n");
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome() {
		/* Test per borsa capiente */
		SortedSet<Attrezzo> insiemeOrdinato = this.borsaCapiente.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> iteratoreAttrezzi = insiemeOrdinato.iterator();
		
		assertEquals(this.forbice, iteratoreAttrezzi.next()); //Forbice:1
		assertEquals(this.fucile, iteratoreAttrezzi.next()); //Fucile:5
		assertEquals(this.mazza, iteratoreAttrezzi.next()); //Mazza:2
		assertEquals(this.osso, iteratoreAttrezzi.next()); //Osso:3
		assertEquals(this.pala, iteratoreAttrezzi.next()); //Pala:2
		assertEquals(this.piccone, iteratoreAttrezzi.next()); //Piccone:2
		
		System.out.println("testGetContenutoOrdinatoPerNome() \n\t-> " + insiemeOrdinato + "\n");
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		/* Test per borsa capiente */
		Map<Integer,Set<Attrezzo>> raggruppamento = this.borsaCapiente.getContenutoRaggruppatoPerPeso();
		Iterator<Attrezzo> iteratoreAttrezziConStessoPeso;
		
		/* Gruppo con peso 1 */
		iteratoreAttrezziConStessoPeso = raggruppamento.get(1).iterator();
		assertEquals(this.forbice, iteratoreAttrezziConStessoPeso.next()); //Forbice:1
		/* Gruppo con peso 2 */
		iteratoreAttrezziConStessoPeso = raggruppamento.get(2).iterator();
		assertEquals(this.mazza, iteratoreAttrezziConStessoPeso.next()); //Mazza:2
		assertEquals(this.pala, iteratoreAttrezziConStessoPeso.next()); //Pala:2
		assertEquals(this.piccone, iteratoreAttrezziConStessoPeso.next()); //Piccone:2
		/* Gruppo con peso 3 */
		iteratoreAttrezziConStessoPeso = raggruppamento.get(3).iterator();
		assertEquals(this.osso, iteratoreAttrezziConStessoPeso.next()); //Osso:3
		/* Gruppo con peso 5 */
		iteratoreAttrezziConStessoPeso = raggruppamento.get(5).iterator();
		assertEquals(this.fucile, iteratoreAttrezziConStessoPeso.next()); //Fucile:5
		
		System.out.println("testGetContenutoRaggruppatoPerPeso() \n\t-> " + raggruppamento + "\n");
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso(){
		/* Test per borsa capiente */
		SortedSet<Attrezzo> insiemeOrdinato = this.borsaCapiente.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> iteratoreAttrezzi = insiemeOrdinato.iterator();
		
		assertEquals(this.forbice, iteratoreAttrezzi.next()); //Forbice:1
		assertEquals(this.mazza, iteratoreAttrezzi.next()); //Mazza:2
		assertEquals(this.pala, iteratoreAttrezzi.next()); //Pala:2
		assertEquals(this.piccone, iteratoreAttrezzi.next()); //Piccone:2
		assertEquals(this.osso, iteratoreAttrezzi.next()); //Osso:3
		assertEquals(this.fucile, iteratoreAttrezzi.next()); //Fucile:5
		System.out.println("testGetSortedSetOrdinatoPerPeso() \n\t-> " + insiemeOrdinato + "\n");
	}

}
