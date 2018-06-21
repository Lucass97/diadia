package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private Stanza stanzaBloccata;
	private Stanza stanzaAdiacenteNord;
	private Stanza stanzaAdiacenteSud;
	private Attrezzo piediporco;

	@Before
	public void setUp() throws Exception {
		/* Test in cui nella stanza (N11) è bloccata l'uscita a nord (per la N12)
		 * mentre non lo è l'uscita a sud (per la N10).
		 * L'attrezzo per accedere all'uscita nord è il piediporco */
		this.stanzaBloccata = new StanzaBloccata("N11",Direzione.NORD,"piediporco");
		this.stanzaAdiacenteNord = new Stanza("N12");
		this.stanzaAdiacenteSud = new Stanza("N10");
		this.stanzaBloccata.impostaStanzaAdiacente(Direzione.convertString("nord"), this.stanzaAdiacenteNord);
		this.stanzaBloccata.impostaStanzaAdiacente(Direzione.convertString("sud"), this.stanzaAdiacenteSud);
		this.piediporco = new Attrezzo("piediporco",3);
	}

	@Test
	public void testGetStanzaAdiacenteSenzaAttrezzo() {
		assertEquals(this.stanzaBloccata,this.stanzaBloccata.getStanzaAdiacente(Direzione.convertString("nord")));
		assertEquals(this.stanzaAdiacenteSud, this.stanzaBloccata.getStanzaAdiacente(Direzione.convertString("sud")));
	}
	
	@Test
	public void testGetStanzaAdiacenteConAttrezzoPerSbloccare() {
		this.stanzaBloccata.addAttrezzo(this.piediporco);
		assertEquals(this.stanzaAdiacenteNord, this.stanzaBloccata.getStanzaAdiacente(Direzione.convertString("nord")));
		assertEquals(this.stanzaAdiacenteSud, this.stanzaBloccata.getStanzaAdiacente(Direzione.convertString("sud")));
	}

}
