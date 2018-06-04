package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella il comando posa.
 * Quando eseguito l'attrezzo desiderato sarà preso dalla borsa del giocatore 
 * e depositato nella stanza.
 * @author Luca Gregori
 * @version 1.1
 * 
 */
public class ComandoPosa extends AbstractComando{

	@Override
	public String eseguiComando(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Giocatore giocatore = partita.getGiocatore();
		Attrezzo attrezzoInBorsa = giocatore.prendiAttrezzoInBorsa(this.getParametro());
		if(attrezzoInBorsa == null)
			return "Attrezzo " + this.getParametro() + " non presente in borsa";
		else if(stanzaCorrente.addAttrezzo(attrezzoInBorsa))
			return "Attrezzo " + this.getParametro() + " posato";
		else return null;
	}

	@Override
	public String getNome() {
		return "posa";
	}
	
}
