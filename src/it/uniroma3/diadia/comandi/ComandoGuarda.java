package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Questa classe modella il comando guarda.
 * Quando eseguito verrà stampato su schermo tutte le informazione relativa alla stanza corrente
 * @author Luca Gregori
 * @version 1.0
 */
public class ComandoGuarda implements Comando {

	@Override
	public void eseguiComando(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		System.out.println("Ti trovi nella seguente stanza: " + stanzaCorrente.getNome());
		System.out.println("Descrizione stanza: " + stanzaCorrente.getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

}
