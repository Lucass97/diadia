package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;


/**
 * Questa classe modella il comando guarda.
 * Quando eseguito verrà stampato su schermo tutte le informazioni relative
 * alla stanza corrente oppure alla borsa. Ciò che verrà stampato è deciso tramite parametro.
 * @author Luca Gregori
 * @version 1.4
 */
public class ComandoGuarda extends AbstractComando {

	@Override
	public String eseguiComando(Partita partita) {

		if(this.getParametro() == null) {
			return "Per favore specifica cosa guardare... stanza o borsa?";
		}
		switch(this.getParametro()) {
		case "stanza": 
			Stanza stanzaCorrente = partita.getStanzaCorrente();
			return "Ti trovi nella seguente stanza: " + stanzaCorrente.getNome() + 
					"\nDescrizione stanza: " + stanzaCorrente.getDescrizione();
		case "borsa":
			Giocatore giocatore = partita.getGiocatore();
			return giocatore.toStringBorsa();
		default:
			return this.getParametro() + ", parametro non corretto... "
			+ "Per favore usa stanza o borsa";
		}
	}

	@Override
	public String getNome() {
		return "guarda";
	}

}
