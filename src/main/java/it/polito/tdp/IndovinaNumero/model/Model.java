package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private int NMAX = 100;
	private int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	private Set<Integer> tentativi;
	
	
	public void nuovaPartita() {
		double i = Math.random();
		this.segreto = (int) (i * NMAX) +1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	tentativi = new HashSet<Integer>();
    	// tira a caso il numero, imposta i tentativi a zero e imposta quella variabile inGioco a true
	}

	public int tentativo(int tentativo) {
		// scelgo un valore diverso di ritorno per identificare se il tentativo è corretto o troppo alto o troppo basso
		// controlla se la partita è in corso
		
		if(!inGioco) { // stiamo facendo un tentativo anche se la partita è terminata
			throw new IllegalStateException("HAI PERSO: il segreto era "+this.segreto);
		}
		// dobbiamo verificare il controllo dell'input
		// dato ceh il controllo è relativo principalemnte all'interfaccia, si può lasciare nel controller
		// qui si possono aggiungere dei controlli aggiuntivi
		
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero tra 1 e NMAX e non puoi inserire due volte lo stesso numero");
		}
		
		// se siamo qui il tentativo è valido
		this.tentativiFatti++;
		this.tentativi.add(tentativo);
		if(this.tentativiFatti == TMAX-1) {
			this.inGioco = false;
		}
		
		if(tentativo == this.segreto) { // abbiamo indovinato--> la partita finisce
			this.inGioco = false;
			return 0;
		}
		else if(tentativo < this.segreto) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>NMAX) 
			return false;
		
		if(tentativi.contains(tentativo))
			return false;
		
		return true;
	}
		
	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	public void impostaFacile() {
		NMAX = 50;
		
	}
	
	public void impostaMedio() {
		NMAX = 75;
		
	}
	
	public void impostaDifficile() {
		NMAX = 100;
		
	}
	
}