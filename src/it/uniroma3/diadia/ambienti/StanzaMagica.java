package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella una stanza magica.
 * Una stanza è magica se quando il numero di attrezzi posati al suo
 * interno supera una determinata soglia (soglia magica), gli attrezzi aggiunti
 * da quel momento in poi verrano modificati.
 * La modifica di un attrezzo consiste nel invertire il suo nome e duplicare il suo peso.
 * @author Luca Gregori
 * @version 1.0
 * @see Stanza
 */
public class StanzaMagica extends Stanza {
	
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	private static final int SOGLIA_MAGICA_DEFAULT = 2;
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito.reverse();
		int pesoX2 = attrezzo.getPeso() * 2;
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		System.out.println("bidibi bodibi bu!");
		return attrezzo;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo){
		this.contatoreAttrezziPosati++;
		System.out.println(this.contatoreAttrezziPosati);
		if(this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}
	
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagica(String nome,int sogliaMagica) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = sogliaMagica;
	}

}
