package it.uniroma3.diadia.ambienti;

/**
 * Questa classe modella una stanza bloccata.
 * Una stanza è bloccata se non è possibile accedere alla stanza adiacente di una determinata
 * direzione senza l'ausilio di uno specifico attrezzo.
 * @author Luca Gregori
 * @version 1.0
 * @see Stanza
 */
public class StanzaBloccata extends Stanza {
	
	private String direzioneBloccata;
	private String attrezzoPerSbloccare;
	
	private static final String DIREZIONE_BLOCCATA_DEFAULT = "nord";
	private static final String ATTREZZO_PER_SBLOCCARE_DEFAULT = "piediporco";
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		
		if(this.direzioneBloccata.equals(direzione)) {
			if(super.hasAttrezzo(this.attrezzoPerSbloccare))
				System.out.println("Crack! Ho usato " + this.attrezzoPerSbloccare + " per aprire la porta!");
			else {
				System.out.println("La porta sembra bloccata... Forse da qualche parte c'è un attrezzo per sbloccarla");
				return this;
			}
		}
		
		return super.getStanzaAdiacente(direzione);
	}
	
	public StanzaBloccata(String nome) {
		this(nome,DIREZIONE_BLOCCATA_DEFAULT,ATTREZZO_PER_SBLOCCARE_DEFAULT);
	}
	public StanzaBloccata(String nome,String direzioneBloccata) {
		this(nome,direzioneBloccata,ATTREZZO_PER_SBLOCCARE_DEFAULT);
	}
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoPerSbloccare) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoPerSbloccare = attrezzoPerSbloccare;
	}

}
