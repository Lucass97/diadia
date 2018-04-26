package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {
	
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
		if(attrezzo == null)
    		return false;
		
        if (super.numeroAttrezzi < super.NUMERO_MASSIMO_ATTREZZI) {
        	this.contatoreAttrezziPosati++;
        	if(this.contatoreAttrezziPosati > this.sogliaMagica)
    			attrezzo = this.modificaAttrezzo(attrezzo);
        	super.attrezzi[super.numeroAttrezzi] = attrezzo;
        	super.numeroAttrezzi++;
        	return true;
        }
        else
        	return false;
	}
	
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaProtected(String nome,int sogliaMagica) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = sogliaMagica;
	}

}
