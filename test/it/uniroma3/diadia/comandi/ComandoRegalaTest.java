package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Mago;

public class ComandoRegalaTest {
	
	private AbstractComando comandoRegala;
	private Partita partita;
	private Stanza aulaN09;
	private Attrezzo osso;
	private AbstractPersonaggio merlino;
	
	@Before
	public void setUp() throws Exception {
		this.osso = new Attrezzo("osso",2);
		this.aulaN09 = new Stanza("AulaN09");
		this.merlino = new Mago("Merlino", "roba...", null);
		this.aulaN09.setPersonaggio(this.merlino);
		this.partita = new Partita();
		this.partita.setStanzaCorrente(this.aulaN09); // ci troviano in aula n09
		Giocatore giocatore = this.partita.getGiocatore();
		giocatore.posaAttrezzoInBorsa(this.osso); //l'attrezzo da regalare deve essere in borsa
		this.comandoRegala = new ComandoRegala();
	}

	@Test
	public void testEseguiComando() {
		
		assertEquals(2,this.osso.getPeso());
		
		this.comandoRegala.setParametro(this.osso.getNome());
		this.comandoRegala.eseguiComando(this.partita);
		
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Attrezzo attrezzoRegalato = stanzaCorrente.getAttrezzo(this.osso.getNome());
		
		assertEquals(this.osso, attrezzoRegalato);
		assertEquals(1,attrezzoRegalato.getPeso());
	}

}
