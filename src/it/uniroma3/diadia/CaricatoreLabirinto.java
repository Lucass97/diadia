package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";
	
	private static final String BLOCCHI_MARKER = "Blocchi:";
	
	private static final String BUIO_MARKER = "Buie:";
	
	private static final String MAGICHE_MARKER = "Magiche:";

	private static final String PERSONAGGI_MARKER = "Personaggi:";

	private static final String MAGHI_MARKER = "Maghi:";
	
	private static final String CANI_MARKER = "Cani:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		try {
			this.carica();
		} catch (FormatoFileNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiEBloccaUscite();
			this.leggiEImpostaStanzeBuie();
			this.leggiEImpostaStanzeMagiche();
			this.leggiECreaPersonaggi();
			this.leggiEImpostaMaghi();
			this.leggiEImpostaCani();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+ marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String parametriStanza : separaStringheAlleVirgole(nomiStanze)) {
			
			Stanza stanza = this.creaStanzaRiflessiva(parametriStanza);
			this.nome2stanza.put(stanza.getNome(), stanza);
		}
	}
	
	private Stanza creaStanzaRiflessiva(String parametriStanza) throws FormatoFileNonValidoException {
		
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.ambienti.Stanza"); 
		try (Scanner scannerLinea = new Scanner(parametriStanza)) {
			Stanza stanza;
			String nomeStanza;
			
			check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
			nomeStanza = scannerLinea.next();
			
			if(scannerLinea.hasNext()) { //tipo stanza
				String tipoStanza = scannerLinea.next();
				nomeClasse.append( Character.toUpperCase(tipoStanza.charAt(0)));
				nomeClasse.append(tipoStanza.substring(1));
			}
			stanza = (Stanza) Class.forName(nomeClasse.toString()).newInstance();
			stanza.setNome(nomeStanza);
			
			return stanza;
				
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			check(false, "tipo di stanza non valido: " + nomeClasse.toString());
		}
		return null;
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		while(scanner.hasNext())
			result.add(scanner.next());
		/*try (Scanner scannerDiParole = scanner) {
			result.add(scannerDiParole.next());
		}*/
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}
	
	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificheUscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificheUscita)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza) + ".");
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();
					
					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+ msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(Direzione.convertString(dir), arrivoA);
	}
	
	/**
	 * Questo metodo permette di leggere da file le specifiche che riguardano le stanze bloccate e
	 * di assegnarle alla stanza di tipo {@link StanzaBloccata}
	 * @throws FormatoFileNonValidoException nel caso di terminazione precoce oppure nei
	 * sottocasi specificati dal metodo {@link #impostaBloccoStanza()}.
	 */
	private void leggiEBloccaUscite() throws FormatoFileNonValidoException{
		String specificheBlocchi = this.leggiRigaCheCominciaPer(BLOCCHI_MARKER);
		for(String specificheBlocco : separaStringheAlleVirgole(specificheBlocchi)) {
			try (Scanner scannerDiLinea = new Scanner(specificheBlocco)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza da bloccare"));
					String nomeStanzaDaBloccare = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione da bloccare"+ nomeStanzaDaBloccare + "."));
					String dirDaBloccare = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo per sbloccare " + dirDaBloccare + "."));
					String attrezzoPerSbloccare = scannerDiLinea.next();
					
					impostaBloccoStanza(nomeStanzaDaBloccare, dirDaBloccare, attrezzoPerSbloccare);
				}
			} 
		}
	}
	
	/**
	 * Questo metodo fornisce supporto a {@link #leggiEBloccaUscite()} e
	 * si occupa di impostare i vari parametri nella stanza presa in esame.
	 * @param nomeStanzaDaBloccare
	 * @param dirDaBloccare
	 * @param attrezzoPerSbloccare
	 * @throws FormatoFileNonValidoException nel caso la stanza presa in esame non esista,
	 * oppure non sia di tipo StanzaBloccata.
	 */
	private void impostaBloccoStanza(String nomeStanzaDaBloccare, String dirDaBloccare, String attrezzoPerSbloccare) throws FormatoFileNonValidoException {
		/* Possibili fallimenti */
		check(isStanzaValida(nomeStanzaDaBloccare),"Stanza da bloccare sconosciuta " + nomeStanzaDaBloccare);
		Stanza stanzaDaBloccare = this.nome2stanza.get(nomeStanzaDaBloccare);
		if(!stanzaDaBloccare.getClass().equals(StanzaBloccata.class))
			check(false, "impossibile bloccare la stanza " + stanzaDaBloccare.getNome() + ", non è di tipo " + StanzaBloccata.class);
		
		/* Se tutto va bene e non si sollevano eccezzioni arrivo fino a qui */
		((StanzaBloccata) stanzaDaBloccare).setDirezioneBloccata(Direzione.convertString(dirDaBloccare));
		((StanzaBloccata) stanzaDaBloccare).setAttrezzoPerSbloccare(attrezzoPerSbloccare);
	}
	

	private void leggiEImpostaStanzeBuie() throws FormatoFileNonValidoException{
        String specificheBuie = this.leggiRigaCheCominciaPer(BUIO_MARKER);
        for(String specificheBuia : separaStringheAlleVirgole(specificheBuie)) {
            try (Scanner scannerDiLinea = new Scanner(specificheBuia)) {
                while (scannerDiLinea.hasNext()) {
                    check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza da oscurare"));
                    String nomeStanzaDaOscurare = scannerDiLinea.next();
                    check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo per illuminare la stanza."));
                    String attrezzoPerIlluminare = scannerDiLinea.next();

                    impostaBuioStanza(nomeStanzaDaOscurare, attrezzoPerIlluminare);
                }
            } 
        }
    }
	
	/**
     * Questo metodo fornisce supporto a {@link #leggiEImpostaStanzeBuie()} e
     * si occupa di impostare i vari parametri nella stanza presa in esame.
     * @param nomeStanzaDaOscurare
     * @param attrezzoPerIlluminare
     * @throws FormatoFileNonValidoException nel caso la stanza presa in esame non esista,
     * oppure non sia di tipo StanzaBuia.
     */
    private void impostaBuioStanza(String nomeStanzaDaOscurare, String attrezzoPerIlluminare) throws FormatoFileNonValidoException {

        /* Possibili fallimenti */
  
        check(isStanzaValida(nomeStanzaDaOscurare),"Stanza da oscurare sconosciuta " + nomeStanzaDaOscurare);
        Stanza stanzaDaOscurare = this.nome2stanza.get(nomeStanzaDaOscurare);
        if(!stanzaDaOscurare.getClass().equals(StanzaBuia.class))
            check(false, "impossibile Oscurare la stanza " + stanzaDaOscurare.getNome() + ", non è di tipo " + StanzaBuia.class);

        /* Se tutto va bene e non si sollevano eccezzioni arrivo fino a qui */
        ((StanzaBuia) stanzaDaOscurare).setAttrezzoPerVedere(attrezzoPerIlluminare);
    }
	
	private void leggiEImpostaStanzeMagiche() throws FormatoFileNonValidoException{
		String specificheStanzeMagiche = this.leggiRigaCheCominciaPer(MAGICHE_MARKER);
		for(String specificheStanzaMagica : separaStringheAlleVirgole(specificheStanzeMagiche)) {
			try (Scanner scannerDiLinea = new Scanner(specificheStanzaMagica)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza da impostare come magica"));
					String nomeStanzaMagica = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la soglia magica "+ nomeStanzaMagica + "."));
					int sogliaMagica = Integer.parseInt(scannerDiLinea.next());
					
					impostaStanzaMagica(nomeStanzaMagica, sogliaMagica);
				}
			} 
		}
	}
	
	private void impostaStanzaMagica(String nomeStanzaMagica, int sogliaMagica) throws FormatoFileNonValidoException {
		/* Possibili fallimenti */
		check(isStanzaValida(nomeStanzaMagica),"Stanza magica sconosciuta " + nomeStanzaMagica);
		Stanza stanzaMagica = this.nome2stanza.get(nomeStanzaMagica);
		if(!stanzaMagica.getClass().equals(StanzaMagica.class))
			check(false, "impossibile impostare la stanza magica " + stanzaMagica.getNome() + ", non è di tipo " + StanzaMagica.class);
		
		/* Se tutto va bene e non si sollevano eccezzioni arrivo fino a qui */
		((StanzaMagica) stanzaMagica).setSogliaMagica(sogliaMagica);
	}
	
	private void leggiECreaPersonaggi() throws FormatoFileNonValidoException  {
		String parametriPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		for(String parametriPersonaggio : separaStringheAlleVirgole(parametriPersonaggi)) {
			
			AbstractPersonaggio personaggio = this.creaPersonaggioRiflessiva(parametriPersonaggio);
			//this.nome2stanza.put(stanza.getNome(), stanza);
			//System.out.println(stanza.getNome());
		}
	}
	
	private AbstractPersonaggio creaPersonaggioRiflessiva(String parametriPersonaggio) throws FormatoFileNonValidoException {
		
		/*Esempio: nomePersonaggio presentazionePersonaggio tipoPersonaggio stanzaPersonaggio */
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.personaggi.");
		try (Scanner scannerLinea = new Scanner(parametriPersonaggio)) { 
			AbstractPersonaggio personaggio;
			String nomePersonaggio;
			String presentazionePersonaggio;
			String tipoPersonaggio;
			String stanzaPersonaggio;
			
			//nome del personaggio
			check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
			nomePersonaggio = scannerLinea.next();
			
			//presentazione del personaggio
			check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione di un personaggio. " + nomePersonaggio));
			presentazionePersonaggio = scannerLinea.next();
			
			//tipo del personaggio
			check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo di personaggio. " + nomePersonaggio + " " + presentazionePersonaggio));
			tipoPersonaggio = scannerLinea.next();
			nomeClasse.append( Character.toUpperCase(tipoPersonaggio.charAt(0)));
			nomeClasse.append(tipoPersonaggio.substring(1));
			
			//stanza del personaggio
			check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza di un personaggio. " + nomePersonaggio + " " + presentazionePersonaggio + " " + tipoPersonaggio));
			stanzaPersonaggio = scannerLinea.next();
			check(isStanzaValida(stanzaPersonaggio),"stanza sconosciuta, impossibile assegnare al personaggio " + nomePersonaggio + " la stanza " + stanzaPersonaggio);
	
			personaggio = (AbstractPersonaggio) Class.forName(nomeClasse.toString()).newInstance();
			personaggio.setNome(nomePersonaggio);
			personaggio.setPresentazione(presentazionePersonaggio);
			
			this.nome2stanza.get(stanzaPersonaggio).setPersonaggio(personaggio); //aggiungo il personaggio nella stanza
			return personaggio;
				
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			check(false, "tipo di personaggio non valido: " + nomeClasse.toString());
		}
		return null;
	}
	
	private void leggiEImpostaMaghi() throws FormatoFileNonValidoException{
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		for(String specificheMago : separaStringheAlleVirgole(specificheMaghi)) {
			try (Scanner scannerDiLinea = new Scanner(specificheMago)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza dove risiede il mago"));
					String nomeStanzaMago = scannerDiLinea.next();
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo. " + nomeStanzaMago));
					String nomeAttrezzo = scannerDiLinea.next();
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+ nomeStanzaMago + " " + nomeAttrezzo + "."));
					int pesoAttrezzo = Integer.parseInt(scannerDiLinea.next());
					
					this.impostaMago(nomeStanzaMago,new Attrezzo(nomeAttrezzo,pesoAttrezzo));
				}
			} 
		}
	}
	
	private void impostaMago(String nomeStanzaMago, Attrezzo attrezzo) throws FormatoFileNonValidoException {
		/* Possibili fallimenti */
		check(isStanzaValida(nomeStanzaMago),"Stanza sconosciuta " + nomeStanzaMago);
		Stanza stanzaMago = this.nome2stanza.get(nomeStanzaMago);
		AbstractPersonaggio personaggio = stanzaMago.getPersonaggio();
		if(personaggio == null)
			check(false, "non esiste alcun personaggio dentro la stanza " + nomeStanzaMago);
		if(!personaggio.getClass().equals(Mago.class))
			check(false, "impossibile impostare il personaggio " + personaggio.getNome() + ", non è di tipo " + Mago.class);
		
		/* Se tutto va bene e non si sollevano eccezzioni arrivo fino a qui */
		((Mago) personaggio).setAttrezzo(attrezzo);
	}
	
	private void leggiEImpostaCani() throws FormatoFileNonValidoException{
        String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);
        for(String specificheCane : separaStringheAlleVirgole(specificheCani)) {
            try (Scanner scannerDiLinea = new Scanner(specificheCane)) {
                while (scannerDiLinea.hasNext()) {
                    check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Il cane"));
                    String nomeStanzaDelCane = scannerDiLinea.next();
                    check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il cibo preferito del cane"));
                    String ciboPreferitoDelCane = scannerDiLinea.next();
                    check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo che il cane ti regala"));
                    String attrezzoRegalatoDalCane = scannerDiLinea.next();
                    check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Il peso dell'attrezzo che il cane ti regala"));
                    int pesoAttrezzoRegalatoDalCane = Integer.parseInt(scannerDiLinea.next());

                    impostaCaneStanza(nomeStanzaDelCane, ciboPreferitoDelCane, attrezzoRegalatoDalCane, pesoAttrezzoRegalatoDalCane);
                }
            } 
        }
    }
	
	private void impostaCaneStanza(String nomeStanzaDelCane, String ciboPreferitoDelCane, String attrezzoRegalatoDalCane, int pesoAttrezzoRegalatoDalCane) throws FormatoFileNonValidoException {

        /* Possibili fallimenti */
        check(isStanzaValida(nomeStanzaDelCane),"Stanza sconosciuta " + nomeStanzaDelCane);
        Stanza stanzaCane = this.nome2stanza.get(nomeStanzaDelCane);
        AbstractPersonaggio personaggio = stanzaCane.getPersonaggio();
        if(personaggio == null)
            check(false, "non esiste alcun personaggio dentro la stanza " + nomeStanzaDelCane);
        if(!personaggio.getClass().equals(Cane.class))
            check(false, "impossibile impostare il personaggio " + personaggio.getNome() + ", non è di tipo " + Cane.class);

       // Se tutto va bene e non si sollevano eccezzioni arrivo fino a qui
        ((Cane) personaggio).setCiboPreferito(ciboPreferitoDelCane);
        ((Cane) personaggio).setAttrezzoDelCane(new Attrezzo(attrezzoRegalatoDalCane, pesoAttrezzoRegalatoDalCane));


    }

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
