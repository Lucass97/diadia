package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Questo comando modella il comando fine.
 * Quando eseguito la partita verrà terminata.
 * @author Luca Gregori
 * @versione 1.1
 */
public class ComandoFine extends AbstractComando {
	
	private static final String MEX_DI_USCITA = "Partita terminata... Grazie per aver giocato!";
	
	@Override
	public String eseguiComando(Partita partita) {
		partita.setFinita();
		return MEX_DI_USCITA;
	}

	@Override
	public String getNome() {
		return "fine";
	}

}
