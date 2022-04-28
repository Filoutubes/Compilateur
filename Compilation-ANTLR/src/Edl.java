import java.io.*;

import java.util.*;


/***********************************************************************************************
 * @author Bahdon Barkhad, Gabriel Renault et Emma Demure
 ***********************************************************************************************/

public class Edl {

	/***********************************************************************************************
	 * ************************   D�FINITIONS DES VARIABLES ****************************************
	 ***********************************************************************************************/
	
	
	/* VARIABLES FINAL N�CESSAIRES */
	
	//Nombre max de r�f�rences externes et de definitions accept�es/unit�. 
	//Codes pour les vecteurs de translation : TRANSDON, TRANSCODE et REFEXT.
	private static final int MAXREF = 10, MAXDEF = 10, TRANSDON=1, TRANSCODE=2, REFEXT=3;
	
	// Les 2 types d'erreurType � rencontrer
	static final int FATALE = 0, NONFATALE = 1;
	
	// NBOBJMAX = taille maximale du code et NBMODMAX = nombre max de modules
	static final int NBOBJMAX = 1000, NBMODMAX = 5;

	 
	/* TABLEAUX */

	// descriptArray est le tableau qui rassemble tous les descripteurs de l'�diteur de liens.
	static Descripteur[] descriptArray = new Descripteur[NBMODMAX+1];

	// tableau renseignant tous les po
	static int[] po = new int[(NBMODMAX+1) * NBOBJMAX]; 
	static int[][] tabPo = new int[NBMODMAX+1][NBOBJMAX];
	
	// tableau des def
	static Descripteur.EltDef[] dicoDef = new Descripteur.EltDef[(NBMODMAX+1)*MAXDEF];
	
	// addresses finales de chacun des modules
	static int[][] adFinale = new int[NBMODMAX+1][MAXREF+1];
	
	// tableaux des vecteurs de translation
	static int[] transDon = new int[NBMODMAX+1];
	static int[] transCode = new int[NBMODMAX+1];
	
	// tableau des transitions
	static HashMap<Integer, Integer> tabTrans = new HashMap<Integer, Integer>(); 
	
	// tableau renseignant le nom de chaque unit�
	static String[] unitName = new String[NBMODMAX+1];

	/* TABLEAUX */
	
	static int erreurType;
	static int ipo; 
	static int numeroModule; 
    static String progName;
    static int repereDicoDef = 0;

    /***********************************************************************************************
	 * ************************   D�FINITIONS DES M�THODES ****************************************
	 ***********************************************************************************************/
    
    
    /**
	 * Remplissage du dictionnaire de toutes les d�finitions du code et relev� des doubles d�clarations de d�finitions.
	 */
    
	static void remplissageDicoDef() {
		
		int index = 0;
		
		for (int i = 0; i < numeroModule+1; i++) {
			
			for (int j = 1; j <= descriptArray[i].getNbDef(); j++) {

				dicoDef[index] = descriptArray[i].new EltDef(descriptArray[i].getDefNomProc(j), descriptArray[i].getDefAdPo(j) + transCode[i], descriptArray[i].getDefNbParam(j));
				
				//TODO : Jsp si c'est utile de faire �a
				//descriptArray[i].ajoutDef(descriptArray[i].getDefNomProc(j)); //Ajout dans la table des d�finitions du nom de la proc
				//descriptArray[i].modifDefAdPo(j, descriptArray[i].getDefAdPo(j) + transCode[i]); //De son po
				//descriptArray[i].modifDefNbParam(j, descriptArray[i].getDefNbParam(j)); // De son nb de params
				
				// Gestion des erreurs : PLUSIEURS DEFINITIONS DU M�ME NOM.
				for (int k = 0; k < index; k++) {
					
					if (descriptArray[j].getDefNomProc(index).equalsIgnoreCase(descriptArray[j].getDefNomProc(k))) {
						
						fatalError(FATALE, "Erreur : il y a plusieurs occurrences de : " + descriptArray[j].getDefNomProc(index) + " dans votre code.");
					}
				}
				index++;
			}
		}
	}

	/**
	 * Fonction qui remplit le tableau de vecteurs TRANSDON => UTILE POUR LES REFERENCES EXTERNES AUX VARGLOB
	 */
	
	static void remplissageTransDon() {
		
		//On commence � l'indice 1
		transDon[0] = 0;
		
		for (int i = 1; i < numeroModule+1; i++) {
			transDon[i] = transDon[i-1] + descriptArray[i-1].getTailleGlobaux(); //M�j de transdon
		}
	}
	
	/**
	 * Fonction qui remplit le tableau de vecteurs TRANSCODE => UTILE POUR LES BRANCHEMENTS DE TOUS TYPES
	 */
	
	static void remplissageTransCode() {
		
		//On commence � l'indice 1
		transCode[0] = 0;
		
		for (int i = 1; i < numeroModule+1; i++) {
			transCode[i] = transCode[i-1] + descriptArray[i-1].getTailleCode(); //M�j de transcode
		}
	}
	


	/**
	 * Fonction qui remplit le tableau 2D adFinale et qui relie les d�fintion � leur r�f�rences externes.
	 */
	
	static void remplissageRefExt() {
		
		int isPresent = 0;
		
		for (int i = 0; i < numeroModule + 1; i++) {
		
				for (int j = 0; i < dicoDef.length; j++) {
					
					isPresent = descriptArray[i].presentDef(dicoDef[j].nomProc);
					
					if(isPresent != 0) {
						
						if (descriptArray[i].getRefNbParam(isPresent) != dicoDef[j].nbParam) {
							
							fatalError(FATALE, "Erreur : " + dicoDef[j].nomProc + " devrait avoir " + descriptArray[i].getRefNbParam(isPresent) + "param�tres.");
						}
						
						adFinale[i][isPresent] = dicoDef[j].adPo;
						
					}	
					else { fatalError(FATALE, "Erreur : Aucune r�f�rence pour cette proc�dure"); }
			}
		}
	}
	
	
	/**
	 * Cr�ation du fichier .obj (ex�cutable MaPile)
	 */

	static void createMapObj() {

		// Ouverture du fichier .map et modification de celui-ci

		OutputStream input = Ecriture.ouvrir(progName + ".map");
		
		//Tableau des po
		int[] po = new int[(numeroModule+1) * NBOBJMAX+1];

		if (input == null) {
			fatalError(FATALE, "Erreur : EDL ne peut cr�er le fichier " + progName + ".map car on tente d'ouvir un programme qui n'existe pas.");
		}

		

		//On parcourt toutes les unit�s comme pr�c�demment -- COLLECTE DES TRANSEXT
		for (int i = 0; i <= numeroModule; i++) {

			InputStream current = Lecture.ouvrir(unitName[i] + ".obj");
			Map<Integer, Integer> tabTrans = new HashMap<Integer, Integer>();// HashMap servant � stocker les vecteurs de translation rencontr�s

			int vTransitionType, ad; //variables stockant le code du vecteur de translation voulu et son adresse


			if (current == null) {
				fatalError(FATALE, "Erreur : " + unitName[i] + " vous tentez d'ouvrir un fichier .obj inexistant ou endommag�.");
			}

			//On commence � collecter tous les vTrans
			for (int j = 0; j < descriptArray[i].getNbTransExt(); j++) {

				vTransitionType = Lecture.lireIntln(current); //cf lireDesc Descripteur.java
				ad = Lecture.lireInt(current) + transCode[i];

				tabTrans.put(ad, vTransitionType);
			}

			//Nombre de r�f�rence externes, result = resultat de la requ�te � la hashmap (adresse - type transition) tabTrans, lastInstruction = adresse de la derniere instrcution
			int nbRefExt = 1, result = 0, lastInstruction = (i == numeroModule) ? descriptArray[i].getTailleCode()-1 : descriptArray[i].getTailleCode()-1;


			//R�solution des REFEXT, TRANSCODE ET TRANSDON sur tout po[] 
			
			for (int k = 0; k < lastInstruction; k++) {

				po[ipo] = Lecture.lireIntln(current);

				result = tabTrans.get(ipo);

				switch (result) {

				case TRANSCODE:

					po[ipo] += transCode[i]; //ipo = addresse
					break;

				case TRANSDON:

					po[ipo] += transDon[i];
					break;

				case REFEXT:

					po[ipo] = adFinale[i][nbRefExt];
					nbRefExt++;
					break;
				}

				ipo++;

			}  

			Lecture.fermer(current);

		} 


		// R�SERVATION DE PLACE POUR LES VARGLOB DANS PO[] ET ECRITURE DANS UN FICHIER EPONYME

		po[2] = transDon[numeroModule]+descriptArray[numeroModule].getTailleGlobaux();
		for (int l = 0; l < ipo; l++) Ecriture.ecrireStringln(input, "" + po[l]);

		Ecriture.fermer(input);

		//CREATION DU FICHIER FINAL
		Mnemo.creerFichier(ipo, po, progName + ".ima");

	}

	

	/**
	 * Fonction qui r�cup�re la totalit� des descripteurs et s'occupe du remplissage des variables.
	 */

	static void lireDescripteurs() {

		System.out.println("--- MERCI DE FOURNIR LES NOM DE FICHIERS SANS SUFFIXES ---");
		System.out.print(" -> Entrez le nom du programme : ");

		String input = Lecture.lireString();
        progName = input;


		descriptArray[0] = new Descripteur();
		descriptArray[0].lireDesc(input);

		if (!descriptArray[0].getUnite().equals("programme")) {
			fatalError(FATALE, "Erreur : veuillez entrer un nom de programme.");
		}

		unitName[0] = input;
		numeroModule = 0;
		
		// ENREGISTREMENT DES MODULES
		while ((!input.equals("")) && (numeroModule < NBMODMAX)) {

		 System.out.print("Nom du prochain module " + (numeroModule+1) + " (PRESS RETURN si termin�) ");
		 input = Lecture.lireString();

		 // Si module non vide
		 if (!input.equals("")) {

			 numeroModule++;

			 // Enregistre le nom du module (champ unite)
			 descriptArray[numeroModule].setUnite(input);
			 unitName[numeroModule] = input;

			 // Cr�ation du descripteur du module
			 descriptArray[numeroModule] = new Descripteur();
			 descriptArray[numeroModule].lireDesc(input);

			 
			 if (!descriptArray[numeroModule].getUnite().equals("module")) {
				 fatalError(FATALE, "Erreur : ce n'est pas un module.");
			 }
			
		}
	}
}
	

	/**
	 * Fonction erreur
	 * @param codeErreur un entier correspondant au type de l'erreur.
	 * @param errorMessage le message d'erreur � afficher 
	 */

	static void fatalError(int codeErreur, String errorMessage) {

		System.out.println("----------------------------------------");
		System.out.println(errorMessage);
		System.out.println("----------------------------------------");

		if (codeErreur == FATALE) {
			System.out.println("ERREUR FATALE RENCONTR�E - ABANDON DE L'�DITION DE LIENS ");
			System.exit(1);
		}
		// R�initialisation de erreurType � NONFATALE
		erreurType = erreurType + 1;
	}
	
	public static void main (String argv[]) {


		System.out.println("EDITEUR DE LIENS (EDL) / Badhon Barkhad - Gabriel Renault - Emma Demure");

		System.out.println("____________________________________________________________________________________ \n");

		erreurType = 0;
		ipo = 1;


		// Lecture des descripteurs et remplissage du dictionnaire des d�finitions

		lireDescripteurs();


		remplissageTransDon();
		remplissageTransCode();
		
		remplissageDicoDef();
		remplissageRefExt();


		if(erreurType != 0) { System.out.println("Une ou plusieurs erreurs ont �t� rencontr�es, AUCUN EX�CUTABLE PRODUIT."); System.exit(1); }

		// Si des erreurs ont �t� rencontr�es

		if (erreurType > 0) {

			System.out.println("programme executable non produit");
			System.exit(1);

		}
		

		// Cr�ation de l'ex�cutable
		createMapObj();

		System.out.println("EDL a termin�");

	}

}

