package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella il comando prendi.
 * Quando eseguito l'attrezzo desiderato sarà preso dalla stanza 
 * e depositato nella borsa del giocatore.
 * @author Luca Gregori
 * @version 1.1
 * 
 */
public class ComandoPrendi extends AbstractComando{
	
	@Override
	public String eseguiComando(Partita partita) {
		
		StringBuilder stringBuilder = new StringBuilder();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Giocatore giocatore = partita.getGiocatore();
		Attrezzo attrezzoPreso = stanzaCorrente.getAttrezzo(this.getParametro());
		if(attrezzoPreso != null) {
			if(giocatore.posaAttrezzoInBorsa(attrezzoPreso)) {
				if(stanzaCorrente.removeAttrezzo(attrezzoPreso))
					stringBuilder.append("Attrezzo "+ attrezzoPreso + " prelevato");
			}
			else
				stringBuilder.append("Attrezzo troppo pesante! Non c'entra in borsa!");
		}
		else 
			stringBuilder.append("Non esiste alcun attrezzo " + this.getParametro() + " nella stanza " + stanzaCorrente.getNome());
		return stringBuilder.toString();
	}

	@Override
	public String getNome() {
		return "prendi";
	}

}
