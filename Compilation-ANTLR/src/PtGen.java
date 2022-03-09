
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
	private static int adVar; // adresse de la variable
	
	private static int typeVarLue, iVarLue;

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
		
		adVar = 0; // mise à 0 de l'adresse courante dans la table des symboles

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
			po.produire(ARRET);
			desc.setTailleCode(po.getIpo());
			afftabSymb(); // affichage de la table des symboles en fin de compilation
			
			//on crée les fichiers obj, gen et desc
			po.constObj();
			po.constGen();
			desc.ecrireDesc(UtilLex.nomSource);
			break;

			// TODO
		case 1: 
			break;
		case 2: // constante
			if(presentIdent(1) == 0) {
				placeIdent(UtilLex.numIdCourant, CONSTANTE, tCour, vCour);
			}
			else {
				UtilLex.messErr("Constante déja déclarée.");
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
		case 7: // on a lu un ident de variable
				if(presentIdent(1) == 0) {
					placeIdent(UtilLex.numIdCourant, VARGLOBALE, tCour, adVar);
					adVar++;
				}
				else {
					UtilLex.messErr("Variable déja déclarée.");
				}
				break;
				
		// traitement des expressions
				
		// PARTIE BOOLÉENS
		case 8: // après lecture d'une expression (boucle/si/switch)
				verifBool();
				po.produire(BSIFAUX);
				
				//on ne connaît pas la valeur de l'ipo du branchement pour le moment, on produit -1
				po.produire(-1);
				
				// on empile l'ipo actuel (valeur du bsifaux) dans la pile des reprises, pour pouvoir le modifier par la suite
				pileRep.empiler(po.getIpo());
				break;
		case 9: //lecture d'une déclaration d'un booléen
				tCour = BOOL;
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
		case 13:
				verifBool();
				break;
		
				// PARTIE ENTIERS
		case 14: //lecture d'une déclaration d'un entier
				tCour = ENT;
				break;
		case 15:
				po.produire(DIFF);
				tCour = BOOL;
				break;
		case 16:
				po.produire(EG);
				tCour = BOOL;
				break;
		case 17:
				po.produire(INF);
				tCour = BOOL;
				break;
		case 18:
				po.produire(SUP);
				tCour = BOOL;
				break;
		case 19:
				po.produire(INFEG);
				tCour = BOOL;
				break;
		case 20:
				po.produire(SUPEG);
				tCour = BOOL;
				break;
		case 21:
				po.produire(ADD);
				break;
		case 22:
				po.produire(SOUS);
				break;
		case 23:
				po.produire(DIV);
				break;
		case 24:
				po.produire(MUL);
				break;
		case 25:
				verifEnt();
				break;
		case 26: // TODO
				break;
		case 27:
				po.produire(EMPILER); // on empile une value de type int/long (un bool est représenté par les entiers 1/0)
				po.produire(vCour);
				break;
		case 28: // lecture
				if(presentIdent(1) == 0) {
					UtilLex.messErr("Ident non reconnu.");
				}
				else {
					EltTabSymb symb = tabSymb[presentIdent(1)];
					if(symb.type == BOOL) {
						po.produire(LIREBOOL);
					}
					else if(symb.type == ENT) {
						po.produire(LIRENT);
					}
					else {
						//NEUTRE
						UtilLex.messErr("Erreur: type non reconnu.");
					}
					po.produire(AFFECTERG);
					po.produire(symb.info);
				}
				break;
		case 29: // écriture
				if(presentIdent(1) == 0) {
					UtilLex.messErr("Ident non reconnu.");
				}
				else {
					EltTabSymb symb = tabSymb[presentIdent(1)];
					if(symb.type == BOOL) {
						po.produire(ECRBOOL);
					}
					else if(symb.type == ENT) {
						po.produire(ECRENT);
					}
					else {
						//NEUTRE
						UtilLex.messErr("Erreur: type non reconnu.");
					}
				}
				break;
		case 30: // réserver
				po.produire(RESERVER);
				po.produire(adVar);
				desc.setTailleGlobaux(adVar);
				break;
		case 31: // lecture d'un ident
			// on doit sauvegarder le type courant et l'index courant dans la table des symboles, de la variable à lire
			if(presentIdent(1) == 0) {
				UtilLex.messErr("Ident non reconnu.");
			}
			else {
				iVarLue = presentIdent(1);
				if(tabSymb[iVarLue].categorie == CONSTANTE) { // on EMPILE une constante, on ne la lit pas
					UtilLex.messErr("L'ident n'est pas une variable.");
					break; // return early
				}
				tCour = tabSymb[iVarLue].type;
				vCour = tabSymb[iVarLue].info;
				typeVarLue = tabSymb[iVarLue].type;
			}
			break;
		case 32: // affectation (affecterg)
				// il nous faut réutiliser iVarLue et typeVarlue et vérifier que les types concordent!
				if(typeVarLue == BOOL) {
					verifBool(); // on vérifie que tCour == BOOL pour que la réaffectation puisse se faire
				}
				else if(typeVarLue == ENT) {
					verifEnt(); // on vérifie que tCour == ENT pour que la réaffectation puisse se faire
				}
				else {
					// Neutre
					UtilLex.messErr("Type inconnu.");
				}
				po.produire(AFFECTERG);
				po.produire(tabSymb[iVarLue].info);
				break;
		case 33: // on doit évaluer un ident et pouvoir le retrouver dans la table des ident
				if(presentIdent(1) == 0) {
					UtilLex.messErr("Ident inconnu ou non déclaré.");
					break; // return early
				}
				else {
					tCour = tabSymb[presentIdent(1)].type;
					vCour = tabSymb[presentIdent(1)].info;
					if(tabSymb[presentIdent(1)].categorie == VARGLOBALE) {
						po.produire(CONTENUG);
						po.produire(vCour);
					}
					else if(tabSymb[presentIdent(1)].categorie == CONSTANTE) {
						po.produire(EMPILER);
						po.produire(vCour);
					}
					else {
						UtilLex.messErr("Type inconnu.");
					}
				}
				break;
		case 34: // traitement du else
				int bsifaux = pileRep.depiler(); // on dépile l'ipo de la valeur du bsifaux
				
				po.produire(BINCOND);
				//on ne connaît pas la valeur de l'ipo du branchement pour le moment, on stocke -1 dans la pile
				po.produire(-1);
				// on empile l'ipo actuel dans la pile des reprises, pour pouvoir le modifier par la suite
				pileRep.empiler(po.getIpo());
				
				po.modifier(bsifaux, po.getIpo()+1); // on remplace le -1 par ipo+1 pour passer à la ligne après le bincond
				break;
		case 35: // traitement du FSI
				int bincondOuBsifaux = pileRep.depiler(); // on dépile soit le dernier bincond soit le dernier bsifaux
				po.modifier(bincondOuBsifaux, po.getIpo()+1); // on modifie la valeur -1, qui était après le bincond/bsifaux, par l'ipo de la ligne suivante
				break;
		case 36: // fin tt que
				int bsifaux2 = pileRep.depiler(); // on récupère l'ipo de la valeur du BSIFAUX
				po.produire(BINCOND);
				po.produire(bsifaux2 - 1);
				po.modifier(bsifaux2, po.getIpo()+1); // on remplace le -1 par ipo+1 pour passer à la ligne après le bincond si la cond n'est pas valide
				break;
		case 37: // préparation du premier bincond
				po.produire(BINCOND);
				po.produire(0);
				pileRep.empiler(po.getIpo());
				break;
		case 38: // chaînage des bincond
				
				break;
		default:
			System.out.println("Point de generation non prévu dans votre liste");
			break;

		}
	}
}















