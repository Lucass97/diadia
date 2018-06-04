package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	
	private static final String MESSAGGIO_NESSUN_PERSONAGGIO = "Sembra che qui non ci sia nessuno!";

	@Override
	public String eseguiComando(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null ) {
			return personaggio.saluta();
		}
		else
			return MESSAGGIO_NESSUN_PERSONAGGIO;
	}

	@Override
	public String getNome() {
		return "saluta";
	}

}
