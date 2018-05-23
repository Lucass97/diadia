package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

/**
 * Questa classe modella una borsa
 * @author docente di poo, Luca Gregori
 * @version 1.3
 * @see Attrezzo
 */
public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}
	 /**
     * Mette un attrezzo nella stanza. Possono essere aggiunti attrezzi finché non si
     * supera la capienza (espressa in kg), della borsa.
     * @param attrezzo - l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null)
			return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
	}
	
	 /**
	 * Restituisce il peso massimo trasportabile dalla porsa.
	 * @return un intero che rappresenta il peso massimo (kg).
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
     * Restituisce l'attrezzo se presente nella stanza.
	 * @param nomeAttrezzo - parametro di ricerca.
	 * @return l'attrezzo presente nella stanza.
     * null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzoCercato : this.attrezzi) {
			if (attrezzoCercato.getNome().equals(nomeAttrezzo))
				return attrezzoCercato;
		}
		return null;
	}
	
	/**
	 * Restituisce il peso totale degli attrezzi attualmente in borsa
	 * @return un numero intero che rappresenta il peso (kg) degli attrezzi
	 * presenti attualmente in borsa.
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzoCercato : this.attrezzi) {
			peso += attrezzoCercato.getPeso();
		}
		return peso;
	}
	
	/**
	 * Indica se la borsa è vuota oppure no.
	 * @return true se la borsa è vuota, false altrimenti.
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	/**
	* Controlla se un attrezzo esiste nella borsa (uguaglianza sul nome).
	* @param nomeAttrezzo - chiave di ricerca.
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo - parametro di ricerca.
	 * @return l'attrezzo rimosso, altrimenti null.
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoDaEliminare = this.getAttrezzo(nomeAttrezzo);
		if(this.attrezzi.remove(attrezzoDaEliminare))
			return attrezzoDaEliminare;
		return attrezzoDaEliminare;
	}

	public String toString() {
		if(!this.isEmpty()) {
			System.out.println("Contenuto borsa: ");
			return this.getContenutoOrdinatoPerPeso().toString();
		}
		else return "Borsa vuota";
		/*StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+ this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo : this.attrezzi)
				s.append(attrezzo.toString() + " ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();*/
	}
	
	/**
	 * Restituisce la lista degli attrezzi nella borsa ordinati per peso e
	 * quindi, a parità di peso, per nome.
	 * @return una lista di attrezzi ordinata per peso.
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> listaAttrezzi = new ArrayList<Attrezzo>(this.attrezzi);
		ComparatoreAttrezziPerPeso comp = new ComparatoreAttrezziPerPeso();
		Collections.sort(listaAttrezzi,comp);
		return listaAttrezzi;
	}
	
	/**
	 * Restituisce l'insieme degli attrezzi ordinati per nome.
	 * @return un insieme di attrezzi ordinato per nome.
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		ComparatoreAttrezziPerNome comp = new ComparatoreAttrezziPerNome();
		SortedSet<Attrezzo> listaAttrezzi = new TreeSet<Attrezzo>(comp);
		listaAttrezzi.addAll(this.attrezzi);
		return listaAttrezzi;
	}
	
	/**
	 * Restituisce un insieme degli attrezzi nella borsa ordinati per peso e
	 * quindi, a parità di peso, per nome.
	 * @return un insieme di attrezzi ordinato per peso.
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatoreAttrezziPerPeso comp = new ComparatoreAttrezziPerPeso();
		SortedSet<Attrezzo> listaAttrezzi = new TreeSet<Attrezzo>(comp);
		listaAttrezzi.addAll(this.attrezzi);
		return listaAttrezzi;
	}
	
	/**
	 * Restituisce una mappa che associa un intero (rappresentante un
	 * peso) con l’insieme (comunque non vuoto) degli attrezzi di tale
     * peso: tutti gli attrezzi dell'insieme che figura come valore hanno
     * lo stesso peso pari all'intero che figura come chiave.
	 * @return una mappa ordinata.
	 */
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> raggruppamento = new HashMap<Integer,Set<Attrezzo>>();
		List<Attrezzo> listaAttrezziOrdinata = this.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> iteratoreAttrezzi = listaAttrezziOrdinata.iterator();
		
		Set<Attrezzo> listaAttrezziConStessoPeso = new HashSet<Attrezzo>();
		int pesoRiferimento = -1;
		Attrezzo attrezzoAttuale = null;
		
		/* Primo elemento */
		if(iteratoreAttrezzi.hasNext()) {
			attrezzoAttuale = iteratoreAttrezzi.next();
			pesoRiferimento = attrezzoAttuale.getPeso();
			listaAttrezziConStessoPeso.add(attrezzoAttuale);
		}
		
		while(iteratoreAttrezzi.hasNext()) {
			attrezzoAttuale = iteratoreAttrezzi.next(); //nuovo elemento
			if(attrezzoAttuale.getPeso() != pesoRiferimento) { //peso diverso
				raggruppamento.put(pesoRiferimento, listaAttrezziConStessoPeso);
				pesoRiferimento = attrezzoAttuale.getPeso();
				listaAttrezziConStessoPeso = new HashSet<Attrezzo>();
			}
			listaAttrezziConStessoPeso.add(attrezzoAttuale);
		}
		/* Inserimento ultimo elemento */
		if(attrezzoAttuale != null) { //se fosse = a null significherebbe che la lista è vuota
			raggruppamento.put(pesoRiferimento, listaAttrezziConStessoPeso);
		}
		return raggruppamento;
	}
}
