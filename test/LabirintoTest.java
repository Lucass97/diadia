import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {
	private Labirinto labirinto1;
	private Stanza atrio;
	private Stanza biblioteca;
	private Stanza aulaN11;
	@Before
	public void setUp() throws Exception {
		this.labirinto1 = new Labirinto();
		this.atrio = new Stanza("Atrio");
		this.biblioteca = new Stanza("Biblioteca");
		this.aulaN11 = new Stanza("Aula n11");
	}

	@Test
	public void testGetStanzaIniziale() {
		assertEquals(this.atrio, this.labirinto1.getStanzaIniziale());
		assertNotEquals(this.biblioteca, this.labirinto1.getStanzaIniziale());
		assertNotEquals(this.aulaN11, this.labirinto1.getStanzaIniziale());
	}

	@Test
	public void testGetStanzaFinale() {
		assertEquals(this.biblioteca, this.labirinto1.getStanzaFinale());
		assertNotEquals(this.atrio, this.labirinto1.getStanzaFinale());
		assertNotEquals(this.aulaN11, this.labirinto1.getStanzaFinale());
	}

}
