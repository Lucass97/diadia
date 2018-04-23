package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
/**
 * Questo comando modella il comando fine.
 * Quando eseguito la partita verrà terminata.
 * @author Luca Gregori
 * @versione 1.0
 */
public class ComandoFine implements Comando {

	@Override
	public void eseguiComando(Partita partita) {
		partita.setFinita();

	}

	@Override
	public void setParametro(String parametro) {
	}

}
