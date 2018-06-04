package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {
	
	private static final String MESSAGGIO_NESSUN_PERSONAGGIO = "Nessun personaggio presente nella stanza!";

	@Override
	public String eseguiComando(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			Attrezzo attrezzoDaRegalare = partita.getGiocatore().prendiAttrezzoInBorsa(this.getParametro());
			String msg = personaggio.riceviRegalo(attrezzoDaRegalare, partita);
			return msg;
		}
		else
			return MESSAGGIO_NESSUN_PERSONAGGIO;
	}

	@Override
	public String getNome() {
		return "regala";
	}
	
}
