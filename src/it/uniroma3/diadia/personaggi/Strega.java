package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_AGISCI = "Cosa c'è?";
	private static final String MESSAGGIO_REGAlO = "Poof! Ahahahahhahaha";

	public Strega() {
		super();
	}
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		return MESSAGGIO_AGISCI;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_REGAlO;
	}

}
