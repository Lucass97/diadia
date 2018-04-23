package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Questa classe modella il comando non valido.
 * Quando eseguito verrà stampato su schermo che il comando non è valido
 * @author lucag
 *
 */
public class ComandoNonValido implements Comando {

	@Override
	public void eseguiComando(Partita partita) {
		System.out.println("Comando non valido!");
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

}
