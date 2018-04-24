package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Questa classe modella il comando non valido.
 * Quando eseguito verrà stampato su schermo che il comando non è valido
 * @author Luca Gregori
 * @version 1.0
 */
public class ComandoNonValido implements Comando {

	@Override
	public void eseguiComando(Partita partita) {
		System.out.println("Comando non valido!");
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return "sconosciuto";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
