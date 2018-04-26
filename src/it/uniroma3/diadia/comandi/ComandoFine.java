package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
/**
 * Questo comando modella il comando fine.
 * Quando eseguito la partita verrà terminata.
 * @author Luca Gregori
 * @versione 1.0
 */
public class ComandoFine implements Comando {
	private static final String MEX_DI_USCITA = "Partita terminata... Grazie per aver giocato!";
	
	@Override
	public void eseguiComando(Partita partita) {
		System.out.println(MEX_DI_USCITA);
		partita.setFinita();

	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return "fine";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
