package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	private Stanza stanzaBuia;
	private Attrezzo piccone;
	private Attrezzo lanterna;
	private static final String MEX = "Qui c'è buio pesto!";

	@Before
	public void setUp() throws Exception {
		this.stanzaBuia = new StanzaBuia("N11","lanterna");
		this.piccone = new Attrezzo("piccone",4);
		this.lanterna = new Attrezzo("lanterna",1);
	}
	
	@Test
	public void testGetDescrizioneConAttrezzoPerFareLuce() {
		this.stanzaBuia.addAttrezzo(this.lanterna);
		assertNotEquals(MEX, this.stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneSenzaAttrezzoPerFareLuce() {
		this.stanzaBuia.addAttrezzo(this.piccone);
		assertEquals(MEX, this.stanzaBuia.getDescrizione());
	}

}
