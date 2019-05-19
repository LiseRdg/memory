package memory.om;

import java.util.ArrayList;
import java.util.Collections;

public class Jeu {
	private int              nbPaires;
	private int              nbPairesTrouvees;
	private int              nbCoupsJoues;
	private ArrayList<Carte> cartes;
	private Carte            premiereCarte;
	
	public Jeu(int nbPaires) {
		this (nbPaires, false);
	}
	public Jeu(int nbPaires, boolean modeTriche) {
		this.premiereCarte    = null;
		this.nbPairesTrouvees = 0;
		this.nbCoupsJoues     = 0;
		this.nbPaires         = nbPaires;
		this.cartes = new ArrayList<>();
		for (int i=0; i<this.nbPaires; i++) {
			this.cartes.add(new Carte(i));
			this.cartes.add(new Carte(i));
		}
		if ( ! modeTriche ) {
			Collections.shuffle(this.cartes);
		}
	}
	
	public Reponse jouer( int numCarte) {
		if (numCarte<0 || numCarte>=this.nbPaires*2) {
			// err: numero de carte non valide.
			System.out.println("ERR: numinvalide");
			return Reponse.ERREUR;
		}
		Carte carteChoisie = this.cartes.get(numCarte);
		if (carteChoisie.isTrouvee()) {
			// err: carte d�j� trouv�e
			System.out.println("ERR: carte deja trouv�e");
			return Reponse.ERREUR;
		}
		if (this.premiereCarte == carteChoisie) {
			// err: carte d�j� choisie
			System.out.println("ERR: carte deja choisie");
			return Reponse.ERREUR;
		}
		// le coup maintenant donc forcement valide
		this.nbCoupsJoues ++;
		// Il y a 3 possibilit�s :
		if (this.premiereCarte == null) {
			// c'est la premi�re carte
			this.premiereCarte = carteChoisie;
			return Reponse.PREMIERE;
		} else if (this.premiereCarte.equals(carteChoisie)) {
			// c'est une carte identique � la premi�re
			this.premiereCarte.setTrouvee(true);
			carteChoisie.setTrouvee(true);
			this.premiereCarte = null;
			this.nbPairesTrouvees ++;
			return Reponse.GAGNE;
		} else {
			// c'est une carte diff�rente de la premi�re
			this.premiereCarte = null;
			return Reponse.PERDU;
		}
	}
	
	public int getCarteValeur( int numCarte ) {
		return this.cartes.get(numCarte).getValeur();
	}
	
	public boolean isCarteTrouvee( int numCarte ) {
		return this.cartes.get(numCarte).isTrouvee();
	}
	
	public boolean isPartieTerminee() {
		return this.nbPairesTrouvees>=this.nbPaires;
	}
	
	public int getNbPaires() {
		return this.nbPaires;
	}
	
	public int getNbCartes() {
		return this.nbPaires * 2 ;
	}
	
	public int getNbPairesTrouvees() {
		return this.nbPairesTrouvees;
	}
	
	public int getNbCartesTrouvees() {
		return this.nbPairesTrouvees * 2;
	}
	
	public int getNbCoupsJoues() {
		return this.nbCoupsJoues;
	}
	
}
