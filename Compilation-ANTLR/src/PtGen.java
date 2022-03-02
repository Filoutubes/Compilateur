
/*********************************************************************************
 * VARIABLES ET METHODES FOURNIES PAR LA CLASSE UtilLex (cf libClass_Projet)     *
 *       complement à l'ANALYSEUR LEXICAL produit par ANTLR                      *
 *                                                                               *
 *                                                                               *
 *   nom du programme compile, sans suffixe : String UtilLex.nomSource           *
 *   ------------------------                                                    *
 *                                                                               *
 *   attributs lexicaux (selon items figurant dans la grammaire):                *
 *   ------------------                                                          *
 *     int UtilLex.valEnt = valeur du dernier nombre entier lu (item nbentier)   *
 *     int UtilLex.numIdCourant = code du dernier identificateur lu (item ident) *
 *                                                                               *
 *                                                                               *
 *   methodes utiles :                                                           *
 *   ---------------                                                             *
 *     void UtilLex.messErr(String m)  affichage de m et arret compilation       *
 *     String UtilLex.chaineIdent(int numId) delivre l'ident de codage numId     *
 *     void afftabSymb()  affiche la table des symboles                          *
 *********************************************************************************/


import java.io.*;

/**
 * classe de mise en oeuvre du compilateur
 * =======================================
 * (verifications semantiques + production du code objet)
 * 
 * @author Girard, Masson, Perraudeau
 *
 */

public class PtGen {


	// constantes manipulees par le compilateur
	// ----------------------------------------

	private static final int 

	// taille max de la table des symboles
	MAXSYMB=300,

	// codes MAPILE :
	RESERVER=1,EMPILER=2,CONTENUG=3,AFFECTERG=4,OU=5,ET=6,NON=7,INF=8,
	INFEG=9,SUP=10,SUPEG=11,EG=12,DIFF=13,ADD=14,SOUS=15,MUL=16,DIV=17,
	BSIFAUX=18,BINCOND=19,LIRENT=20,LIREBOOL=21,ECRENT=22,ECRBOOL=23,
	ARRET=24,EMPILERADG=25,EMPILERADL=26,CONTENUL=27,AFFECTERL=28,
	APPEL=29,RETOUR=30,

	// codes des valeurs vrai/faux
	VRAI=1, FAUX=0,

	// types permis :
	ENT=1,BOOL=2,NEUTRE=3,

	// categories possibles des identificateurs :
	CONSTANTE=1,VARGLOBALE=2,VARLOCALE=3,PARAMFIXE=4,PARAMMOD=5,PROC=6,
	DEF=7,REF=8,PRIVEE=9,

	//valeurs possible du vecteur de translation 
	TRANSDON=1,TRANSCODE=2,REFEXT=3;


	// utilitaires de controle de type
	// -------------------------------
	/**
	 * verification du type entier de l'expression en cours de compilation 
	 * (arret de la compilation sinon)
	 */
	private static void verifEnt() {
		if (tCour != ENT)
			UtilLex.messErr("expression entiere attendue");
	}
	/**
	 * verification du type booleen de l'expression en cours de compilation 
	 * (arret de la compilation sinon)
	 */
	private static void verifBool() {
		if (tCour != BOOL)
			UtilLex.messErr("expression booleenne attendue");
	}

	// pile pour gerer les chaines de reprise et les branchements en avant
	// -------------------------------------------------------------------

	private static TPileRep pileRep;  


	// production du code objet en memoire
	// -----------------------------------

	private static ProgObjet po;


	// COMPILATION SEPAREE 
	// -------------------
	//
	/** 
	 * modification du vecteur de translation associe au code produit 
	 * + incrementation attribut nbTransExt du descripteur
	 *  NB: effectue uniquement si c'est une reference externe ou si on compile un module
	 * @param valeur : TRANSDON, TRANSCODE ou REFEXT
	 */
	private static void modifVecteurTrans(int valeur) {
		if (valeur == REFEXT || desc.getUnite().equals("module")) {
			po.vecteurTrans(valeur);
			desc.incrNbTansExt();
		}
	}    
	// descripteur associe a un programme objet (compilation separee)
	private static Descripteur desc;


	// autres variables fournies
	// -------------------------

	// MERCI de renseigner ici un nom pour le trinome, constitue EXCLUSIVEMENT DE LETTRES
	public static String trinome="BarkhadDemureRenault"; 

	private static int tCour; // type de l'expression compilee
	private static int vCour; // sert uniquement lors de la compilation d'une valeur (entiere ou boolenne)


	// TABLE DES SYMBOLES
	// ------------------
	//
	private static EltTabSymb[] tabSymb = new EltTabSymb[MAXSYMB + 1];

	// it = indice de remplissage de tabSymb
	// bc = bloc courant (=1 si le bloc courant est le programme principal)
	private static int it, bc;

	/** 
	 * utilitaire de recherche de l'ident courant (ayant pour code UtilLex.numIdCourant) dans tabSymb
	 * 
	 * @param borneInf : recherche de l'indice it vers borneInf (=1 si recherche dans tout tabSymb)
	 * @return : indice de l'ident courant (de code UtilLex.numIdCourant) dans tabSymb (O si absence)
	 */
	private static int presentIdent(int borneInf) {
		int i = it;
		while (i >= borneInf && tabSymb[i].code != UtilLex.numIdCourant)
			i--;
		if (i >= borneInf)
			return i;
		else
			return 0;
	}

	/**
	 * utilitaire de placement des caracteristiques d'un nouvel ident dans tabSymb
	 * 
	 * @param code : UtilLex.numIdCourant de l'ident
	 * @param cat : categorie de l'ident parmi CONSTANTE, VARGLOBALE, PROC, etc.
	 * @param type : ENT, BOOL ou NEUTRE
	 * @param info : valeur pour une constante, ad d'exécution pour une variable, etc.
	 */
	private static void placeIdent(int code, int cat, int type, int info) {
		if (it == MAXSYMB)
			UtilLex.messErr("debordement de la table des symboles");
		it = it + 1;
		tabSymb[it] = new EltTabSymb(code, cat, type, info);
	}

	/**
	 *  utilitaire d'affichage de la table des symboles
	 */
	private static void afftabSymb() { 
		System.out.println("       code           categorie      type    info");
		System.out.println("      |--------------|--------------|-------|----");
		for (int i = 1; i <= it; i++) {
			if (i == bc) {
				System.out.print("bc=");
				Ecriture.ecrireInt(i, 3);
			} else if (i == it) {
				System.out.print("it=");
				Ecriture.ecrireInt(i, 3);
			} else
				Ecriture.ecrireInt(i, 6);
			if (tabSymb[i] == null)
				System.out.println(" reference NULL");
			else
				System.out.println(" " + tabSymb[i]);
		}
		System.out.println();
	}


	/**
	 *  initialisations A COMPLETER SI BESOIN
	 *  -------------------------------------
	 */
	public static void initialisations() {

		// indices de gestion de la table des symboles
		it = 0;
		bc = 1;

		// pile des reprises pour compilation des branchements en avant
		pileRep = new TPileRep(); 
		// programme objet = code Mapile de l'unite en cours de compilation
		po = new ProgObjet();
		// COMPILATION SEPAREE: descripteur de l'unite en cours de compilation
		desc = new Descripteur();

		// initialisation necessaire aux attributs lexicaux
		UtilLex.initialisation();

		// initialisation du type de l'expression courante
		tCour = NEUTRE;

	} // initialisations

	/**
	 *  code des points de generation A COMPLETER
	 *  -----------------------------------------
	 * @param numGen : numero du point de generation a executer
	 */
	public static void pt(int numGen) {

		switch (numGen) {
		case 0:
			initialisations();
			break;

		case 255 : 
			afftabSymb(); // affichage de la table des symboles en fin de compilation
			po.constObj();
			po.constGen();
			break;

			// TODO
		case 1: // id/code lu
			break;
		case 2: // constante
			if(presentIdent(1) == 0) {
				placeIdent(UtilLex.numIdCourant, CONSTANTE, tCour, vCour);
			}
			else {
				UtilLex.messErr("Constante déja déclarée");
			}
			break;
		case 3: // lecture entier positif
				tCour = ENT;
				vCour = UtilLex.valEnt;
			break;
		case 4: // lecture entier négatif
				tCour = ENT;
				vCour = UtilLex.valEnt * (-1);
			break;
		case 5: // lecture booléen vrai
				tCour = BOOL;
				vCour = VRAI;
				break;
		case 6: // lecture booléen faux
				tCour = BOOL;
				vCour = FAUX;
				break;
		case 7: // déclaration d'un entier
				if(presentIdent(1) == 0) {
					tCour = ENT;
					placeIdent(UtilLex.numIdCourant, VARGLOBALE, tCour, vCour);
				}
				else {
					UtilLex.messErr("Variable déja déclarée");
				}
				break;
		case 8: // déclaration d'un booléen
				if(presentIdent(1) == 0) {
					tCour = ENT;
					placeIdent(UtilLex.numIdCourant, VARGLOBALE, tCour, vCour);
				}
				else {
					UtilLex.messErr("Variable déja déclarée");
				}
				break;
				
		// traitement des expressions
				
		// PARTIE BOOLÉENS
		case 9: 
				verifBool();
				break;
		case 10:
				po.produire(ET);
				break;
		case 11:
				po.produire(OU);
				break;
		case 12:
				po.produire(NON);
				break;
		
		// PARTIE ENTIERS
		case 13:
				verifEnt();
				break;
		case 14:
				po.produire(DIFF);
				break;
		case 15:
				po.produire(EG);
				break;
		case 16:
				po.produire(INF);
				break;
		case 17:
				po.produire(SUP);
				break;
		case 18:
				po.produire(INFEG);
				break;
		case 19:
				po.produire(SUPEG);
				break;
		case 20:
				po.produire(ADD);
				break;
		case 21:
				po.produire(SOUS);
				break;
		case 22:
				po.produire(DIV);
				break;
		case 23:
				po.produire(MUL);
				break;
		case 24:
				po.produire(EMPILER); // on empile une value de type int/long
				break;
		default:
			System.out.println("Point de generation non prévu dans votre liste");
			break;

		}
	}
}















