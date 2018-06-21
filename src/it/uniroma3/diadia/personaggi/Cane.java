package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final String CIBO_PREFERITO_DEFAULT = "polpetta";
	private static final Attrezzo ATTREZZO_CANE_DEFAULT = new Attrezzo("osso",1);
	
	private static final String MESSAGGIO_AGISCI = "Mi piace annusare i sederi, a te?";
	private static final String MESSAGGIO_REGAlO_ACCETTATO = "Bau!";
	private static final String MESSAGGIO_REGALO_RIFIUTATO = "Argh! sei stato ferito dal cane! Ora hai ";
	
	private String ciboPreferito;
	private Attrezzo attrezzoDelCane;
	
	public Cane() {
		super();
		this.ciboPreferito = CIBO_PREFERITO_DEFAULT;
		this.attrezzoDelCane = ATTREZZO_CANE_DEFAULT;
	}
	
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
			partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
			String CFU = partita.getGiocatore().getCFU() + "CFU";
			return MESSAGGIO_REGALO_RIFIUTATO + CFU;
		}
	}

	public Attrezzo getAttrezzoDelCane() {
		return attrezzoDelCane;
	}

	public void setAttrezzoDelCane(Attrezzo attrezzoDelCane) {
		this.attrezzoDelCane = attrezzoDelCane;
	}

	public String getCiboPreferito() {
		return ciboPreferito;
	}
	public void setCiboPreferito(String ciboPreferito) {
		this.ciboPreferito = ciboPreferito;
		
	}

}
