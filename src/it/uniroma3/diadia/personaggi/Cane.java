package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_AGISCI = "Mi piace annusare i sederi, a te?";
	private static final String MESSAGGIO_REGAlO_ACCETTATO = "Bau";
	private static final String MESSAGGIO_REGALO_RIFIUTATO = "Argh";
	
	private String ciboPreferito;
	private Attrezzo attrezzoDelCane;
	
	public Cane(String nome, String presentazione, String ciboPreferito, Attrezzo attrezzoDelCane) {
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
		this.attrezzoDelCane = attrezzoDelCane;
	}

	@Override
	public String agisci(Partita partita) {
		return MESSAGGIO_AGISCI;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(this.ciboPreferito)) {
			partita.getStanzaCorrente().addAttrezzo(attrezzoDelCane);
			return MESSAGGIO_REGAlO_ACCETTATO;
		}
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return MESSAGGIO_REGALO_RIFIUTATO;
		}
	}

}
