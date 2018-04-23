package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosa implements Comando{
	
	private String nomeAttrezzo;

	@Override
	public void eseguiComando(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Giocatore giocatore =partita.getGiocatore();
		Attrezzo attrezzoInBorsa = giocatore.prendiAttrezzoInBorsa(nomeAttrezzo);
		if(attrezzoInBorsa == null)
			System.out.println("Attrezzo " + nomeAttrezzo + " non presente in borsa");
		else if(stanzaCorrente.addAttrezzo(attrezzoInBorsa))
			System.out.println("Attrezzo " + nomeAttrezzo + " posato");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;	
	}
	
}
