package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Questa classe modella il comando non valido.
 * Quando eseguito verrà stampato su schermo che il comando non è valido
 * @author Luca Gregori
 * @version 1.1
 */
public class ComandoNonValido extends AbstractComando {
	
	private static final String MESSAGGIO_COMANDO_NON_VALIDO = "Comando non valido!";
	
	@Override
	public String eseguiComando(Partita partita) {
		return MESSAGGIO_COMANDO_NON_VALIDO;
	}

	@Override
	public String getNome() {
		return "sconosciuto";
	}
	
}
