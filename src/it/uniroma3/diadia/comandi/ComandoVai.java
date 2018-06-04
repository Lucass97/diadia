package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Questa classe modella il comando vai.
 * Quando eseguito il giocatore si sposterà nella stanza adiacente a quella corrente,
 * indicata tramite il parametro direzione.
 * @author Luca Gregori
 * @versione 1.1
 */
public class ComandoVai extends AbstractComando {

	@Override
	public String eseguiComando(Partita partita) {
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(this.getParametro() == null)
			return "Dove vuoi andare? Devi specificare una direzione";
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.getParametro());
		if(prossimaStanza == null)
			return "Direzione inesistente";
		
		partita.setStanzaCorrente(prossimaStanza);
		partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
		return "Hai consumato un CFU ora te ne rimangono: " + partita.getGiocatore().getCFU();

	}

	@Override
	public String getNome() {
		return "vai";
	}

}
