package memory.om;

public enum Reponse {
	ERREUR,		// Erreur : il n'est pas possible de jouer la carte propos�e
	PREMIERE, 	// La carte propos�e est la premi�re
	GAGNE,      // La carte propos�e est identique � la premi�re (c'est un coup gagnant)
	PERDU		// La carte propos�e est diff�rente de la premi�re (c'est un coup perdant)
}
