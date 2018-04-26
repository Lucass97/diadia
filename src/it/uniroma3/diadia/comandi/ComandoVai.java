package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Questa classe modella il comando vai.
 * Quando eseguito il giocatore si sposterà nella stanza adiacente a quella corrente,
 * indicata tramite il parametro direzione.
 * @author Luca Gregori
 * @versione 1.0
 */
public class ComandoVai implements Comando {
	
	private String direzione;

	@Override
	public void eseguiComando(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(this.direzione == null) {
			System.out.println("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			System.out.println("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
		System.out.println("Hai consumato un CFU ora te ne rimangono: " + partita.getCfu());

	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}

}
