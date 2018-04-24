package it.uniroma3.diadia.ambienti;

/**
 * Questa classe modella una stanza buia.
 * Una stanza è buia quando non è possibile vedere il suo contenuto (descrizione),
 * se al suo interno non è prensente l'attrezzo per fare luce (esempio: lanterna).
 * @author Luca Gregori
 * @versione 1.0
 * @see Stanza
 */
public class StanzaBuia extends Stanza {
	private String attrezzoPerVedere;
	private static final String ATTREZZO_PER_VEDERE_DEFAULT = "lanterna";
	
	@Override
	public String getDescrizione() {
		if(super.hasAttrezzo(this.attrezzoPerVedere))
			return super.getDescrizione();
		else
			return "Qui c'è buio pesto!";
	}
	
	public StanzaBuia(String nome) {
		this(nome,ATTREZZO_PER_VEDERE_DEFAULT);
	}
	
	public StanzaBuia(String nome, String attrezzoPerVedere) {
		super(nome);
		this.attrezzoPerVedere = attrezzoPerVedere;
	}

}
