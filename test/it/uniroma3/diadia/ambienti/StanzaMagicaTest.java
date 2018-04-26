package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private Stanza stanzaMagica;
	private Attrezzo falce;
	private Attrezzo martello;
	private Attrezzo forbice;
	private Attrezzo lanterna;
	
	@Before
	public void setUp() throws Exception {
		/* Test con soglia magica 3 */
		this.stanzaMagica = new StanzaMagica("N11",3);
		this.falce = new Attrezzo("falce", 2);
		this.martello = new Attrezzo("martello",3);
		this.forbice = new Attrezzo("forbice",1);
		this.lanterna = new Attrezzo("lanterna",2);
		// Verrà modificato lanterna perchè è il quarto attrezzo inserito
		this.stanzaMagica.addAttrezzo(this.falce);
		this.stanzaMagica.addAttrezzo(this.martello);
		this.stanzaMagica.addAttrezzo(this.forbice);
		this.stanzaMagica.addAttrezzo(this.lanterna);
	}

	@Test
	public void testAddAttrezzoCheSuperaSogliaMagica() {
		Attrezzo lanternaModificata = this.stanzaMagica.getAttrezzo("anretnal");
		assertEquals("anretnal", lanternaModificata.getNome() );
		assertEquals(4, lanternaModificata.getPeso());
	}
	
	@Test
	public void testAddAttrezzoCheNonSuperaSogliaMagica() {
		assertNull(this.stanzaMagica.getAttrezzo(this.reverseNome(this.falce)));
		assertNull(this.stanzaMagica.getAttrezzo(this.reverseNome(this.martello)));
		assertNull(this.stanzaMagica.getAttrezzo(this.reverseNome(this.forbice)));
	}
	
	private String reverseNome(Attrezzo attrezzo) {
		String nome = attrezzo.getNome();
		StringBuilder stringaDaRiversare = new StringBuilder(nome);
		stringaDaRiversare.reverse();
		return stringaDaRiversare.toString();
	}

}
