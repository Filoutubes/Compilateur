
/*********************************************************************************
 * VARIABLES ET METHODES FOURNIES PAR LA CLASSE UtilLex (cf libClass_Projet)     *
 *       complement � l'ANALYSEUR LEXICAL produit par ANTLR                      *
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
	private static int adVar; // adresse de la variable (son indice dans la table des symboles)
	private static int adProc; // adresse de la proc�dure (son indice dans la table des symboles)
	
	private static int typeIdentLu, iIdentLu, nbParams; // type et index de l'ident lu + nombre de param�tres d'une fonction
	private static int nbDefs, nbRefs; // compteur associ� aux d�cl de proc�dures (d�f) et aux r�fs

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
	 * @param info : valeur pour une constante, ad d'ex�cution pour une variable, etc.
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
		
		adVar = 0; // mise � 0 de l'adresse courante dans la table des symboles
		nbParams = 0;

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
			if(desc.getUnite().equals("programme")) {
				po.produire(ARRET);
			}
			desc.setTailleCode(po.getIpo());
			afftabSymb(); // affichage de la table des symboles en fin de compilation
			
			//on cr�e les fichiers obj, gen et desc
			po.constObj();
			po.constGen();
			desc.ecrireDesc(UtilLex.nomSource);
			break;
		case 2: // constante
			if(presentIdent(1) == 0) {
				placeIdent(UtilLex.numIdCourant, CONSTANTE, tCour, vCour);
			}
			else {
				UtilLex.messErr("Constante d�ja d�clar�e.");
			}
			break;
		case 3: // lecture entier positif
				tCour = ENT;
				vCour = UtilLex.valEnt;
			break;
		case 4: // lecture entier n�gatif
				tCour = ENT;
				vCour = UtilLex.valEnt * (-1);
			break;
		case 5: // lecture bool�en vrai
				tCour = BOOL;
				vCour = VRAI;
				break;
		case 6: // lecture bool�en faux
				tCour = BOOL;
				vCour = FAUX;
				break;
		case 7: // on a lu un ident de variable
				if(presentIdent(bc) == 0) {
					if(bc != 1) {
						placeIdent(UtilLex.numIdCourant, VARLOCALE, tCour, adVar);
					}
					else {
						placeIdent(UtilLex.numIdCourant, VARGLOBALE, tCour, adVar);
					}
					adVar++;
				}
				else {
					UtilLex.messErr("Variable d�ja d�clar�e.");
				}
				break;
				
		// traitement des expressions
				
		// PARTIE BOOL�ENS
		case 8: // apr�s lecture d'une expression (boucle/si/switch)
				verifBool();
				po.produire(BSIFAUX);
				
				//on ne conna�t pas la valeur de l'ipo du branchement pour le moment, on produit -1
				po.produire(-1);
				
				// on ajoute une ligne au vecteur de translation
				modifVecteurTrans(TRANSCODE);
				
				// on empile l'ipo actuel (valeur du bsifaux) dans la pile des reprises, pour pouvoir le modifier par la suite
				pileRep.empiler(po.getIpo());
				break;
		case 9: //lecture d'une d�claration d'un bool�en
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
		case 14: //lecture d'une d�claration d'un entier
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
		case 27:
				po.produire(EMPILER); // on empile une value de type int/long (un bool est repr�sent� par les entiers 1/0)
				po.produire(vCour);
				break;
		case 28: // lecture (la lecture implique aussi une affectation: lire(x) = on affecte � x la valeur lue)
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
					switch(symb.categorie) {
						case VARGLOBALE: 
							po.produire(AFFECTERG);
							po.produire(symb.info);
							
							//on ajoute une ligne au vecteur de translation
							modifVecteurTrans(TRANSDON);
							break;
						case VARLOCALE:
							po.produire(AFFECTERL);
							po.produire(symb.info);
							po.produire(0);
							break;
						case PARAMMOD:
							po.produire(AFFECTERL);
							po.produire(symb.info);
							po.produire(1);
						default: UtilLex.messErr("Le type de l'ident � lire n'est pas correct: il n'est pas (r�)affectable.");
					}
				}
				break;
		case 29: // �criture
					if(tCour == BOOL) {
						po.produire(ECRBOOL);
					}
					else if(tCour == ENT) {
						po.produire(ECRENT);
					}
					else {
						//NEUTRE
						UtilLex.messErr("Erreur: type non reconnu.");
					}
				break;
		case 30: // r�server
				po.produire(RESERVER);
				if(bc == 1) {
					po.produire(adVar);
					desc.setTailleGlobaux(adVar);
				}
				else {
					po.produire(adVar - nbParams - 2);
				}
				break;
		case 31: // lecture d'un ident
			// on doit sauvegarder le type courant et l'index courant dans la table des symboles, de la variable � lire
			if(presentIdent(1) == 0) {
				UtilLex.messErr("Ident non reconnu.");
			}
			else {
				iIdentLu = presentIdent(1);
				if(tabSymb[iIdentLu].categorie == CONSTANTE || tabSymb[iIdentLu].categorie == PARAMFIXE) { // on EMPILE une constante, on ne la lit pas
					UtilLex.messErr("L'ident n'est pas une variable.");
					break; // return early
				}
				tCour = tabSymb[iIdentLu].type;
				vCour = tabSymb[iIdentLu].info;
				typeIdentLu= tabSymb[iIdentLu].type;
			}
			break;
		case 32: // affectation (affecterg)
				// il nous faut r�utiliser iIdentLu et typeIdentLu et v�rifier que les types concordent!
				if(typeIdentLu == BOOL) {
					verifBool(); // on v�rifie que tCour == BOOL pour que la r�affectation puisse se faire
				}
				else if(typeIdentLu == ENT) {
					verifEnt(); // on v�rifie que tCour == ENT pour que la r�affectation puisse se faire
				}
				else {
					// Neutre
					UtilLex.messErr("Type inconnu.");
				}
				switch(tabSymb[iIdentLu].categorie) {
					case VARGLOBALE:
						po.produire(AFFECTERG);
						po.produire(tabSymb[iIdentLu].info);
						
						//on ajoute une ligne au vecteur de translation
						modifVecteurTrans(TRANSDON);
						break;
					case VARLOCALE:
						po.produire(AFFECTERL);
						po.produire(tabSymb[iIdentLu].info);
						po.produire(0);
						break;
					case PARAMMOD:
						po.produire(AFFECTERL);
						po.produire(tabSymb[iIdentLu].info);
						po.produire(1);
						break;
					default: UtilLex.messErr("Le type de l'ident n'est pas correct: il n'est pas (r�)affectable.");
				}
				break;
		case 33: // on doit �valuer un ident et pouvoir le retrouver dans la table des ident
				if(presentIdent(1) == 0) {
					UtilLex.messErr("Ident inconnu ou non d�clar�.");
					break; // return early
				}
				else {
					tCour = tabSymb[presentIdent(1)].type;
					vCour = tabSymb[presentIdent(1)].info;
					if(tabSymb[presentIdent(1)].categorie == VARGLOBALE) {
						po.produire(CONTENUG);
						po.produire(vCour);
						
						//on ajoute une ligne au vecteur de translation
						modifVecteurTrans(TRANSDON);
					}
					else if(tabSymb[presentIdent(1)].categorie == CONSTANTE) {
						po.produire(EMPILER);
						po.produire(vCour);
					}
					else if(tabSymb[presentIdent(bc)].categorie == PARAMFIXE) {
						po.produire(CONTENUL);
						po.produire(vCour);
						po.produire(0);
					}
					else if(tabSymb[presentIdent(bc)].categorie == PARAMMOD) {
						po.produire(CONTENUL);
						po.produire(vCour);
						po.produire(1);
					}
					else if(tabSymb[presentIdent(bc)].categorie == VARLOCALE) {
						po.produire(CONTENUL);
						po.produire(vCour);
						po.produire(0);
					}
					else {
						UtilLex.messErr("Type inconnu: erreur lors de la lecture de l'ident.");
					}
				}
				break;
		case 34: // traitement du else
				int bsifaux = pileRep.depiler(); // on d�pile l'ipo de la valeur du bsifaux
				
				po.produire(BINCOND);
				//on ne conna�t pas la valeur de l'ipo du branchement pour le moment, on stocke -1 dans la pile
				po.produire(-1);
				
				// on ajoute une ligne au vecteur de translation
				modifVecteurTrans(TRANSCODE);
				
				// on empile l'ipo actuel dans la pile des reprises, pour pouvoir le modifier par la suite
				pileRep.empiler(po.getIpo());
				
				po.modifier(bsifaux, po.getIpo()+1); // on remplace le -1 par ipo+1 pour passer � la ligne apr�s le bincond
				break;
		case 35: // traitement du FSI
				int bincondOuBsifaux = pileRep.depiler(); // on d�pile soit le dernier bincond soit le dernier bsifaux
				po.modifier(bincondOuBsifaux, po.getIpo()+1); // on modifie la valeur -1, qui �tait apr�s le bincond/bsifaux, par l'ipo de la ligne suivante
				break;
		case 36: // fin tt que
				int bsifaux2 = pileRep.depiler(); // on r�cup�re l'ipo de la valeur du BSIFAUX
				int ipoBoucle = pileRep.depiler(); // on r�cup�re l'ipo du d�but de l'expression du tt que
				po.produire(BINCOND);
				po.produire(ipoBoucle);
				
				// on ajoute une ligne au vecteur de translation
				modifVecteurTrans(TRANSCODE);
				
				po.modifier(bsifaux2, po.getIpo()+1); // on remplace le -1 par ipo+1 pour passer � la ligne apr�s le bincond si la cond n'est pas valide
				break;
		//COND
		case 37: // pr�paration du premier bincond
				pileRep.empiler(0);
				break;
		case 38: // on sauvegarde le bincond dans la pile des reprises et on d�pile le bsifaux
				int ipoBsifaux = pileRep.depiler();
				po.produire(BINCOND);
				po.produire(pileRep.depiler()); // on d�pile le bincond
				
				//on ajoute une ligne au vecteur de translation
				modifVecteurTrans(TRANSCODE);
				
				po.modifier(ipoBsifaux, po.getIpo()+1); // si on ex�cute pas le corps de l'instruction, on �value la prochaine cond
				pileRep.empiler(po.getIpo());
			break;
		case 39: // on remonte la cha�ne
				int last_bsifaux = pileRep.depiler(); // on d�pile le dernier bsifaux (on a su qu'il y en avait un car il restait un dernier bsifaux non modifi�: d'o� l'error "out of bounds, index -1")
				int ipoValeurBinC = pileRep.depiler(); // dernier bincond de la cha�ne
				int valeurNextBinC;
				po.modifier(last_bsifaux, po.getIpo()+1); // si on ex�cute pas le corps de la derni�re instruction (cond), on jump directement vers le dernier bincond (la fin du switch)
				do {
					valeurNextBinC = po.getElt(ipoValeurBinC);
					po.modifier(ipoValeurBinC, po.getIpo()+1);
					ipoValeurBinC = valeurNextBinC;
				}
				while(ipoValeurBinC != 0);
				break;
		case 40: // on ex�cute l'instruction autre
				int last_bsifaux1 = pileRep.depiler(); // on d�pile le "dernier" bsifaux (il nous faudra remonter les autres ensuite) et on produit bincond 0
				po.produire(BINCOND); po.produire(0); // on a ici le dernier bincond, on ne doit SURTOUT PAS d�piler le pr�c�dent, pour pouvoir faire le cha�nage
				
				//on ajoute une ligne au vecteur de translation
				modifVecteurTrans(TRANSCODE);
				
				po.modifier(last_bsifaux1, po.getIpo()+1);
				pileRep.empiler(po.getIpo());
				break;
		case 41: // on doit enregistrer l'index du d�but de l'expression du tt que...
				pileRep.empiler(po.getIpo()+1);
				break;
		
		/* D�BUT COMPILATION DES PROC�DURES */
		
		case 42: // on doit produire le premier bincond (d�but du programme), pour arriver (� la fin de toutes les d�cl) sur le main
				if(desc.getUnite().equals("programme")) {
					po.produire(BINCOND);
					po.produire(0);
					
					//on ajoute une ligne au vecteur de translation
					modifVecteurTrans(TRANSCODE);
					
					pileRep.empiler(po.getIpo()); // on empile l'ipo de la valeur du bincond pour pouvoir le modifier par la suite
				}
				break;
		case 43:
				if(desc.getUnite().equals("programme")) {
					po.modifier(pileRep.depiler(), po.getIpo()+1); // on modifie la valeur du bincond (on s'assure qu'on est bien dans un programme)
				}
				break;
		case 44: 
				if(presentIdent(1) == 0) {
					placeIdent(UtilLex.numIdCourant, PROC , NEUTRE , po.getIpo()+1);
					placeIdent(-1, PRIVEE, NEUTRE, -1); // on ne conna�t pas encore le nombre de param�tres
					adVar = 0; // les var globales sont d�j� d�clar�es
					bc = it+1;
				}
				else {
					UtilLex.messErr("Proc�dure d�ja d�clar�e.");
				}
				break;
		case 45: // fin de d�claration des param�tres
				tabSymb[bc-1].info = nbParams;
				adVar = nbParams+2; // on fait + 2, pour �viter les adresses nbParams et nbParams+1 (utilis�es par MAPILE)
				break;
		case 46: // fin de la proc�dure
				po.produire(RETOUR);
				po.produire(nbParams);
				it = bc + nbParams - 1; // on arrive sur le dernier param d�clar�: au prochain place ident, on placera notre donn�e sur la ligne suivante (on incr�mente it d'abord)
				// on doit supprimer les variables locales et mettre � - 1 les identifiants des params (fixes ou mods)
				for(int i = bc; i<= it; i++) {
					tabSymb[i].code = -1;
				}
				// on remet � 1 bc et � 0 le nombre de param�tres
				bc = 1;
				nbParams = 0;
				break;
		case 47: // d�claration param fixe
				if(presentIdent(bc) == 0) {
					placeIdent(UtilLex.numIdCourant, PARAMFIXE, tCour, nbParams);
					nbParams++;
				}
				else {
					UtilLex.messErr("Param�tre fixe d�j� d�clar�.");
				}
				break;
		case 48: // d�claration param mod
				if(presentIdent(bc) == 0) {
					placeIdent(UtilLex.numIdCourant, PARAMMOD, tCour, nbParams);
					nbParams++;
				}
				else {
					UtilLex.messErr("Param�tre modifiable d�j� d�clar�.");
				}
				break;
		case 49: // gestion de l'appel de fonction
				po.produire(APPEL);
				po.produire(tabSymb[iIdentLu].info);
				
				if(tabSymb[iIdentLu+1].categorie == REF) { // r�f�rence de proc�dure ext�rieure
					modifVecteurTrans(REFEXT);
				}
				else { // la proc�dure est locale
					modifVecteurTrans(TRANSCODE);
				}
				po.produire(tabSymb[iIdentLu+1].info);
				break;
		case 50: // gestion de l'appel de fonction: lecture de l'ident
				if(presentIdent(1) == 0) {
					UtilLex.messErr("L'ident n'existe pas.");
				}
				else {
					iIdentLu = presentIdent(1);
					if(tabSymb[iIdentLu].categorie != PROC) { // l'ident mis en param n'est pas celui d'une proc�dure
						UtilLex.messErr("La proc�dure appel�e n'existe pas.");
						break; // return early
					}
					tCour = tabSymb[iIdentLu].type;
					vCour = tabSymb[iIdentLu].info;
				}
				break;
		case 51: // gestion de la mise en passage en param�tres mod de fonction
				int identParam = presentIdent(1);
				if(identParam == 0) {
					UtilLex.messErr("Ce param�tre modifiable n'existe pas.");
				}
				
				else {
					if(tabSymb[adProc].categorie != PARAMMOD) { 
						UtilLex.messErr("Mauvais appel de fonction: un ou des param�tres modifiables sont en trop.");
					}
					else if(tabSymb[identParam].type != tabSymb[adProc].type) {
						UtilLex.messErr("Le type demand� en param�tre modifiable par la proc�dure ne correspond pas.");
					}
					else {
						switch(tabSymb[identParam].categorie) {
						case VARGLOBALE: 
							po.produire(EMPILERADG); 
							po.produire(tabSymb[identParam].info); 
							
							//on ajoute une ligne au vecteur de translation
							modifVecteurTrans(TRANSDON);
							break;

						case VARLOCALE:
							po.produire(EMPILERADL);
							po.produire(tabSymb[identParam].info);
							po.produire(0);
							break;
						case PARAMMOD:
							po.produire(EMPILERADL);
							po.produire(tabSymb[identParam].info);
							po.produire(1);
							break;
						default:
							UtilLex.messErr("L'identifiant n'est pas passable en param�tre mod (ce n'est ni une VARLOC, ni une VARGLOB, ni un PARAMMOD).");
							break;
						}
						adProc++;
					}
				}
				break;
		case 52:
				adProc = iIdentLu + 2; // on est sur le premier param�tre
				break;
		case 53:
				if(tabSymb[adProc].categorie != PARAMFIXE) { 
					UtilLex.messErr("Mauvais appel de fonction: un ou des param�tres fixes sont en trop.");
				}
				else if(tCour != tabSymb[adProc].type) {
					UtilLex.messErr("Le type demand� en param�tre fixe par la proc�dure ne correspond pas.");
				}
				else {
					adProc++;
				}
				break;
		case 54: // on v�rifie que lors de l'appel de fonction, il n'y avait pas d'arguments (en param fixe) manquants
				if(tabSymb[adProc].categorie == PARAMFIXE) {
					UtilLex.messErr("Mauvais appel de fonction: un ou des param�tres fixes sont manquants.");
				}
				break;
		case 55: // on v�rifie que lors de l'appel de fonction, il n'y avait pas d'arguments (en param fixe) manquants
				if(tabSymb[adProc].categorie == PARAMMOD) {
					UtilLex.messErr("Mauvais appel de fonction: un ou des param�tres modifiables sont manquants.");
				}
				break;
		case 56:
				desc.ajoutRef(UtilLex.chaineIdent(UtilLex.numIdCourant));
				desc.modifRefNbParam(desc.getNbRef(), nbParams);
				placeIdent(UtilLex.numIdCourant, PROC, NEUTRE, desc.getNbRef());
				placeIdent(-1, REF, NEUTRE, nbParams);
				nbParams = 0;
				break;
		case 57:
				desc.ajoutDef(UtilLex.chaineIdent(UtilLex.numIdCourant)); // on ne modifie rien d'autre car on ne conna�t pas encore adPo et nbParams
				break;
		case 58:
				nbParams++; // param mod ou fixe sur une r�f
				break;
		case 59:
				desc.setUnite("programme");
				break;
		case 60:
				desc.setUnite("module");
				break;
		case 61:
				desc = new Descripteur(); // reset de nbRef/nbDef/nbTransExt
				break;
		case 62: // on doit mettre � jour tabDef gr�ce � tabSymb
				for(int i = 1; i <= desc.getNbDef(); i++) { // on va de 1 � nbDef car dans le descripteur, on incr�mente d'abord nbDef
					// il faut maintenant rechercher dans tabSymb
					for(int j = 1; j < it; j++) {
						if(tabSymb[j].categorie == PROC && desc.getDefNomProc(i).equals(UtilLex.chaineIdent(tabSymb[j].code))) {
							// on met � jour nbParam et adPo de la proc dans tabDef
							desc.modifDefAdPo(i, tabSymb[j].info);
							desc.modifDefNbParam(i, tabSymb[j+1].info);
							
							// on met � jour la cat�gorie dans tabSymb
							tabSymb[j+1].categorie = DEF;
							break; // fin for
						}
						//sinon on continue de chercher
					}
				}
				break;
		default:
			System.out.println("Point de generation non pr�vu dans votre liste");;
			break;

		}
	}
}















