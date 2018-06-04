package it.uniroma3.diadia;

import java.util.Scanner;

public class InterfacciaUtenteConsole implements InterfacciaUtente {

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	@Override
	public String prendiIstruzione() {
		Scanner scannerDiLinee  = new Scanner(System.in);		
		String istruzione = scannerDiLinee.nextLine();
		return istruzione;
	}

}
