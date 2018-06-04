package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Questa classe modella il comando aiuto.
 * Quando eseguito verrà stampato su schermo l'elenco dei comandi disponibili
 * @author Luca Gregori
 * @versione 1.1
 */
public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa","saluta", "interagisci", "guarda" };

	@Override
	public String eseguiComando(Partita partita) {
		
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) 
			stringBuilder.append((elencoComandi[i]+" "));
		return stringBuilder.toString();
	}

	@Override
	public String getNome() {
		return "aiuto";
	}

}
