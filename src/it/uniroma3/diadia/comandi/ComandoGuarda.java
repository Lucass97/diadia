package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;


/**
 * Questa classe modella il comando guarda.
 * Quando eseguito verrà stampato su schermo tutte le informazione relativa alla stanza corrente
 * @author Luca Gregori
 * @version 1.0
 */
public class ComandoGuarda implements Comando {
	
	private String parametro;
	
	@Override
	public void eseguiComando(Partita partita) {
		
		if(parametro == null) {
			System.out.println("Per favore specifica cosa guardare... stanza o borsa?");
			return;
		}
		switch(this.parametro) {
			case "stanza": 
				Stanza stanzaCorrente = partita.getStanzaCorrente();
				System.out.println("Ti trovi nella seguente stanza: " + stanzaCorrente.getNome());
				System.out.println("Descrizione stanza: " + stanzaCorrente.getDescrizione());
				break;
			case "borsa":
				Giocatore giocatore = partita.getGiocatore();
				System.out.println(giocatore.toStringBorsa());
				break;
			default:
				System.out.println(this.parametro + ", parametro non corretto... "
						+ "Per favore usa stanza o borsa");
				break;
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
