package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa modella il comando prendi
 * @author Luca Gregori
 * @version 1.0
 * 
 */
public class ComandoPrendi implements Comando{
	
	private String nomeAttrezzo;

	@Override
	public void eseguiComando(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Giocatore giocatore = partita.getGiocatore();
		Attrezzo attrezzoPreso = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
		if(stanzaCorrente.removeAttrezzo(attrezzoPreso)) {
			if(giocatore.posaAttrezzoInBorsa(attrezzoPreso))
				System.out.println("Attrezzo "+ attrezzoPreso + " prelevato");
		}else
			System.out.println("Non esiste alcun attrezzo " + this.nomeAttrezzo + " nella stanza " + stanzaCorrente.getNome());
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
