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
	
	private Direzione direzioneBloccata;
	
	public Direzione getDirezioneBloccata() {
		return direzioneBloccata;
	}

	public void setDirezioneBloccata(Direzione direzioneBloccata) {
		this.direzioneBloccata = direzioneBloccata;
	}

	public String getAttrezzoPerSbloccare() {
		return attrezzoPerSbloccare;
	}

	public void setAttrezzoPerSbloccare(String attrezzoPerSbloccare) {
		this.attrezzoPerSbloccare = attrezzoPerSbloccare;
	}
	private String attrezzoPerSbloccare;
	
	private static final String NOME_DEFAULT = "bloccata";
	private static final Direzione DIREZIONE_BLOCCATA_DEFAULT = Direzione.NORD;
	private static final String ATTREZZO_PER_SBLOCCARE_DEFAULT = "piediporco";
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		
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
	
	public StanzaBloccata() {
		this(NOME_DEFAULT);
	}
	public StanzaBloccata(String nome) {
		this(nome,DIREZIONE_BLOCCATA_DEFAULT,ATTREZZO_PER_SBLOCCARE_DEFAULT);
	}
	public StanzaBloccata(String nome,Direzione direzioneBloccata) {
		this(nome,direzioneBloccata,ATTREZZO_PER_SBLOCCARE_DEFAULT);
	}
	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoPerSbloccare) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoPerSbloccare = attrezzoPerSbloccare;
	}

}
