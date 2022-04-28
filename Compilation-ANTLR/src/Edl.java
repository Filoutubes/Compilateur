import java.io.*;

import java.util.*;


/***********************************************************************************************
 * @author Bahdon Barkhad, Gabriel Renault et Emma Demure
 ***********************************************************************************************/

public class Edl {

	/***********************************************************************************************
	 * ************************   DÉFINITIONS DES VARIABLES ****************************************
	 ***********************************************************************************************/
	
	
	/* VARIABLES FINAL NÉCESSAIRES */
	
	//Nombre max de références externes et de definitions acceptées/unité. 
	//Codes pour les vecteurs de translation : TRANSDON, TRANSCODE et REFEXT.
	private static final int MAXREF = 10, MAXDEF = 10, TRANSDON=1, TRANSCODE=2, REFEXT=3;
	
	// Les 2 types d'erreurType à rencontrer
	static final int FATALE = 0, NONFATALE = 1;
	
	// NBOBJMAX = taille maximale du code et NBMODMAX = nombre max de modules
	static final int NBOBJMAX = 1000, NBMODMAX = 5;

	 
	/* TABLEAUX */

	// descriptArray est le tableau qui rassemble tous les descripteurs de l'éditeur de liens.
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
	
	// tableau renseignant le nom de chaque unité
	static String[] unitName = new String[NBMODMAX+1];

	/* TABLEAUX */
	
	static int erreurType;
	static int ipo; 
	static int numeroModule; 
    static String progName;
    static int repereDicoDef = 0;

    /***********************************************************************************************
	 * ************************   DÉFINITIONS DES MÉTHODES ****************************************
	 ***********************************************************************************************/
    
    
    /**
	 * Remplissage du dictionnaire de toutes les définitions du code et relevé des doubles déclarations de définitions.
	 */
    
	static void remplissageDicoDef() {
		
		int index = 0;
		
		for (int i = 0; i < numeroModule+1; i++) {
			
			for (int j = 1; j <= descriptArray[i].getNbDef(); j++) {

				dicoDef[index] = descriptArray[i].new EltDef(descriptArray[i].getDefNomProc(j), descriptArray[i].getDefAdPo(j) + transCode[i], descriptArray[i].getDefNbParam(j));
				
				//TODO : Jsp si c'est utile de faire ça
				//descriptArray[i].ajoutDef(descriptArray[i].getDefNomProc(j)); //Ajout dans la table des définitions du nom de la proc
				//descriptArray[i].modifDefAdPo(j, descriptArray[i].getDefAdPo(j) + transCode[i]); //De son po
				//descriptArray[i].modifDefNbParam(j, descriptArray[i].getDefNbParam(j)); // De son nb de params
				
				// Gestion des erreurs : PLUSIEURS DEFINITIONS DU MÊME NOM.
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
		
		//On commence à l'indice 1
		transDon[0] = 0;
		
		for (int i = 1; i < numeroModule+1; i++) {
			transDon[i] = transDon[i-1] + descriptArray[i-1].getTailleGlobaux(); //Màj de transdon
		}
	}
	
	/**
	 * Fonction qui remplit le tableau de vecteurs TRANSCODE => UTILE POUR LES BRANCHEMENTS DE TOUS TYPES
	 */
	
	static void remplissageTransCode() {
		
		//On commence à l'indice 1
		transCode[0] = 0;
		
		for (int i = 1; i < numeroModule+1; i++) {
			transCode[i] = transCode[i-1] + descriptArray[i-1].getTailleCode(); //Màj de transcode
		}
	}
	


	/**
	 * Fonction qui remplit le tableau 2D adFinale et qui relie les défintion à leur références externes.
	 */
	
	static void remplissageRefExt() {
		
		int isPresent = 0;
		
		for (int i = 0; i < numeroModule + 1; i++) {
		
				for (int j = 0; i < dicoDef.length; j++) {
					
					isPresent = descriptArray[i].presentDef(dicoDef[j].nomProc);
					
					if(isPresent != 0) {
						
						if (descriptArray[i].getRefNbParam(isPresent) != dicoDef[j].nbParam) {
							
							fatalError(FATALE, "Erreur : " + dicoDef[j].nomProc + " devrait avoir " + descriptArray[i].getRefNbParam(isPresent) + "paramètres.");
						}
						
						adFinale[i][isPresent] = dicoDef[j].adPo;
						
					}	
					else { fatalError(FATALE, "Erreur : Aucune référence pour cette procédure"); }
			}
		}
	}
	
	
	/**
	 * Création du fichier .obj (exécutable MaPile)
	 */

	static void createMapObj() {

		// Ouverture du fichier .map et modification de celui-ci

		OutputStream input = Ecriture.ouvrir(progName + ".map");
		
		//Tableau des po
		int[] po = new int[(numeroModule+1) * NBOBJMAX+1];

		if (input == null) {
			fatalError(FATALE, "Erreur : EDL ne peut créer le fichier " + progName + ".map car on tente d'ouvir un programme qui n'existe pas.");
		}

		

		//On parcourt toutes les unités comme précédemment -- COLLECTE DES TRANSEXT
		for (int i = 0; i <= numeroModule; i++) {

			InputStream current = Lecture.ouvrir(unitName[i] + ".obj");
			Map<Integer, Integer> tabTrans = new HashMap<Integer, Integer>();// HashMap servant à stocker les vecteurs de translation rencontrés

			int vTransitionType, ad; //variables stockant le code du vecteur de translation voulu et son adresse


			if (current == null) {
				fatalError(FATALE, "Erreur : " + unitName[i] + " vous tentez d'ouvrir un fichier .obj inexistant ou endommagé.");
			}

			//On commence à collecter tous les vTrans
			for (int j = 0; j < descriptArray[i].getNbTransExt(); j++) {

				vTransitionType = Lecture.lireIntln(current); //cf lireDesc Descripteur.java
				ad = Lecture.lireInt(current) + transCode[i];

				tabTrans.put(ad, vTransitionType);
			}

			//Nombre de référence externes, result = resultat de la requête à la hashmap (adresse - type transition) tabTrans, lastInstruction = adresse de la derniere instrcution
			int nbRefExt = 1, result = 0, lastInstruction = (i == numeroModule) ? descriptArray[i].getTailleCode()-1 : descriptArray[i].getTailleCode()-1;


			//Résolution des REFEXT, TRANSCODE ET TRANSDON sur tout po[] 
			
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


		// RÉSERVATION DE PLACE POUR LES VARGLOB DANS PO[] ET ECRITURE DANS UN FICHIER EPONYME

		po[2] = transDon[numeroModule]+descriptArray[numeroModule].getTailleGlobaux();
		for (int l = 0; l < ipo; l++) Ecriture.ecrireStringln(input, "" + po[l]);

		Ecriture.fermer(input);

		//CREATION DU FICHIER FINAL
		Mnemo.creerFichier(ipo, po, progName + ".ima");

	}

	

	/**
	 * Fonction qui récupère la totalité des descripteurs et s'occupe du remplissage des variables.
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

		 System.out.print("Nom du prochain module " + (numeroModule+1) + " (PRESS RETURN si terminé) ");
		 input = Lecture.lireString();

		 // Si module non vide
		 if (!input.equals("")) {

			 numeroModule++;

			 // Enregistre le nom du module (champ unite)
			 descriptArray[numeroModule].setUnite(input);
			 unitName[numeroModule] = input;

			 // Création du descripteur du module
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
	 * @param errorMessage le message d'erreur à afficher 
	 */

	static void fatalError(int codeErreur, String errorMessage) {

		System.out.println("----------------------------------------");
		System.out.println(errorMessage);
		System.out.println("----------------------------------------");

		if (codeErreur == FATALE) {
			System.out.println("ERREUR FATALE RENCONTRÉE - ABANDON DE L'ÉDITION DE LIENS ");
			System.exit(1);
		}
		// Réinitialisation de erreurType à NONFATALE
		erreurType = erreurType + 1;
	}
	
	public static void main (String argv[]) {


		System.out.println("EDITEUR DE LIENS (EDL) / Badhon Barkhad - Gabriel Renault - Emma Demure");

		System.out.println("____________________________________________________________________________________ \n");

		erreurType = 0;
		ipo = 1;


		// Lecture des descripteurs et remplissage du dictionnaire des définitions

		lireDescripteurs();


		remplissageTransDon();
		remplissageTransCode();
		
		remplissageDicoDef();
		remplissageRefExt();


		if(erreurType != 0) { System.out.println("Une ou plusieurs erreurs ont été rencontrées, AUCUN EXÉCUTABLE PRODUIT."); System.exit(1); }

		// Si des erreurs ont été rencontrées

		if (erreurType > 0) {

			System.out.println("programme executable non produit");
			System.exit(1);

		}
		

		// Création de l'exécutable
		createMapObj();

		System.out.println("EDL a terminé");

	}

}

