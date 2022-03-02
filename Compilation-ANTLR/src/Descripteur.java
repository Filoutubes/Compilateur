
import java.io.InputStream;
import java.io.OutputStream;

/** classe necessaire a la COMPILATION SEPAREE d'un programme et de modules
 * 		attributs a definir pour une unite compilee
 * 		+ methodes de consulation, de modification, 
 * 		de creation du fichier correspondant suffixe par .desc,
 * 		de lecture d'un fichier descripteur deja cree et suffixe par .desc
 * 
 * @author Girard, Masson, Perraudeau
 *
 */

public class Descripteur {
		
	
	/**  classe interne EltDef definissant 
	 * 	 le type de chaque element de la table des points d'entree tabDef
	 */
	
	class EltDef {
		// nomProc = nom de la procedure definie en DEF
		public String nomProc;
		// adPo = adresse de debut de code de cette procedure
		// nbParam =  nombre de parametres de cette procedure
		public int adPo, nbParam;

		public EltDef(String nomProc, int adPo, int nbParam) {
			this.nomProc = nomProc;
			this.adPo = adPo;
			this.nbParam = nbParam;
		}
	}

	/**  classe interne EltRef definissant 
	 * 	 le type de chaque element de la table des references externes tabRef
	 */
	
	class EltRef {
		// nomProc = nom de la procedure definie en REF
		public String nomProc;
		//  nbParam =  nombre de parametres de cette procedure
		public int nbParam;

		public EltRef(String nomProc, int nbParam) {
			this.nomProc = nomProc;
			this.nbParam = nbParam;
		}
	}

	/** Def des 8 champs d'un descripteur d'une unite compilee
	 * 
	 * unite = "programme" ou bien "module"
	 * */
	private String unite;
	// tailleCode = taille du code objet de l'unite compilee
	// tailleGlobaux = nombre de variables globales de l'unite compilee
	// nbDef = nombre de procedures definies en DEF dans l'unite compilee
	// nbRef = nombre de procedures definies en REF dans l'unite compilee
	// nbTransExt = nombre d'elements du vecteur de translation
	private int tailleCode, tailleGlobaux, 
	nbDef, nbRef, nbTransExt;

	private static final int MAXREF = 10, MAXDEF = 10;
	// tabDef = table des procedures definies en DEF dans l'unite compilee
	private EltDef[] tabDef = new EltDef[MAXDEF + 1];
	// tabRef = table des procedures definies en REF dans l'unite compilee
	private EltRef[] tabRef = new EltRef[MAXREF + 1];

	// constructeur
	public Descripteur() {
		nbDef = 0; nbRef = 0; nbTransExt = 0;
		for (int i = 0; i <= MAXDEF; i++)
			tabDef[i] = new EltDef("inconnu", -2, -2);
		for (int i = 0; i <= MAXREF; i++)
			tabRef[i] = new EltRef("inconnu", -2);
	}
	
	/** Methodes d'acces aux attributs unite, tailleCode, tailleGlobaux
	 * ----------------------------------------------------------------
	*/
	
	/**
	 * initialise le champ unite du descripteur
	 * @param s : "module" ou bien "programme"
	 */
	public void setUnite(String s) {
		unite = s;
	}
	
	/**
	 * recupere le champ unite du descripteur
	 * @return : "module" ou bien "programme"
	 */
	public String getUnite() {
		return unite;
	}
	
	/**
	 * initialise la taille du code dans le descripteur
	 * @param n : taille du code
	 */
	public void setTailleCode (int n) {
		tailleCode = n;
	}

	/**
	 * recupere la taille du code dans le descripteur
	 * @return : taille du code
	 */
	public int getTailleCode() {
		return tailleCode;
	}

	/**
	 * initialise le nombre de variables globales dans le descripteur
	 * @param n : nombre de variables globales
	 */
	public void setTailleGlobaux (int n) {
		tailleGlobaux = n;
	}

	/**
	 * recupere le nombre de variables globales dans le descripteur
	 * @return : nombre de variables globales
	 */
	public int getTailleGlobaux() {
		return tailleGlobaux;
	}
	
	/** Methodes d'acces aux attributs nbDef, nbRef, nbTransExt
	 *  -------------------------------------------------------
	*/
	
	/**
	 * recupere le nombre de procedures points d'entree dans le descripteur
	 * @return : nombre de procedures points d'entree
	 */
	public int getNbDef() {
		return nbDef;
	}
	
	/**
	 * recupere le nombre de procedures references externes dans le descripteur
	 * @return : nombre de procedures references externes
	 */
	public int getNbRef() {
		return nbRef;
	}
	
	/**
	 * incremente le nombre d'elements de la liste transExt dans le descripteur
	 */
	public void incrNbTansExt() {
		nbTransExt += 1;
	}
	
	/**
	 * recupere le nombre d'elements de la liste transExt dans le descripteur
	 * @return : nombre d'elements de la liste transExt
	 */
	public int getNbTransExt() {
		return nbTransExt;
	}
	
	/** Methodes d'acces au tableau tabDef
	 * ----------------------------------
	 */

	
	/** recherche une procedure dans tabDef
	 * 
	 * @param idLu : le nom de la procedure recherchee
	 * @return : indice i dans tabDef si presente, 0 sinon
	 */
	public  int presentDef(String idLu) { 
		int i = nbDef;
		while (i > 0 && !tabDef[i].nomProc.equals(idLu))
			i--;
		return i;
	}

	/** ajoute une procedure dans tabDef
	 * 
	 * @param idLu : nom de la procedure a ajouter
	 */
	public void ajoutDef(String idLu) { 
		if (nbDef == Descripteur.MAXDEF)
			UtilLex.messErr("trop de points d'entree");
		nbDef += 1;
		tabDef[nbDef] = new EltDef(idLu, -1, -1);
	}

	/** recupere le nom de la ieme proc. de tabDef
	 * 
	 * @param i : indice de la procedure cherchee
	 * @return : nom de la ieme procedure
	 */
	public String getDefNomProc(int i) {
		return tabDef[i].nomProc;
	}

	/** modifie le nombre de parametres de la ieme proc. de tabDef
	 * 
	 * @param i : indice de la  procedure concernee
	 * @param nb : nombre des parametres a mettre a jour
	 */
	public void modifDefNbParam(int i, int nb) {
		tabDef[i].nbParam = nb;
	}
	
	/** recupere le nombre de parametres de la ieme proc. de tabDef
	 * 
	 * @param i : indice de la  procedure concernee
	 * @return : nombre des parametres de la ieme procedure
	 */
	public int getDefNbParam(int i) {
		return tabDef[i].nbParam;
	}

	/** modifie l'adresse de debut de code de la ieme proc. de tabDef
	 * 
	 * @param i : indice de la  procedure concernee
	 * @param ad : ad. de debut de code a mettre a jour
	 */
	public void modifDefAdPo(int i, int ad) {
		tabDef[i].adPo = ad;
	}
	
	/** recupere l'adresse de debut de code de la ieme proc. de tabDef
	 * 
	 * @param i : indice de la  procedure concernee
	 * @return : ad. de debut de code de la ieme procedure
	 */
	public int getDefAdPo(int i) {
		return tabDef[i].adPo;
	}
	
	/** Methodes d'acces au tableau tabRef
	 * -----------------------------------
	 */
	
	/** recherche une procedure dans tabRef
	 * 
	 * @param idLu : le nom de la procedure recherchee
	 * @return : indice i dans tabDef si presente, 0 sinon
	 */
	public int presentRef(String idLu) { 
		int i = nbRef;
		while (i > 0 && !tabRef[i].nomProc.equals(idLu))
			i--;
		return i;
	}

	/** ajoute une procedure dans taRDef
	 * 
	 * @param idLu : nom de la procedure a ajouter
	 */
	public void ajoutRef(String idLu) { 
		if (nbRef == Descripteur.MAXREF)
			UtilLex.messErr("trop de references externes");
		nbRef += 1;
		tabRef[nbRef] = new EltRef(idLu, -1);
	}

	/** recupere le nom de la ieme proc. de tabRef
	 * 
	 * @param i : indice de la procedure cherchee
	 * @return : nom de la ieme procedure
	 */
	public String getRefNomProc(int i) {
		return tabRef[i].nomProc;
	}

	/** modifie le nombre de parametres de la ieme proc. de tabRef
	 * 
	 * @param i : indice de la  procedure concernee
	 * @param nb : nombre des parametres a mettre a jour
	 */
	public void modifRefNbParam(int i, int nb) {
		tabRef[i].nbParam = nb;
	}
	
	/** recupere le nombre de parametres de la ieme proc. de tabRef
	 * 
	 * @param i : indice de la  procedure concernee
	 * @return : nombre des parametres de la ieme procedure
	 */
	public int getRefNbParam(int i) {
		return tabRef[i].nbParam;
	}
	
	/** pour affichage d'un descripteur
	 */
	
	public String toString() {
		String s = "unite          " + unite + "\n" 
				+ "tailleCode     " + tailleCode + "\n" 
				+ "tailleGlobaux  " + tailleGlobaux + "\n"
				+ "nbDef          " + nbDef + "\n" 
				+ "nbRef          " + nbRef + "\n" 
				+ "nbTransExt     " + nbTransExt + "\n"
				+ "tabDef         " + " \n";
		for (int i = 1; i <= nbDef; i++)
			s = s 	+ "    " 
					+ tabDef[i].nomProc + "  " + tabDef[i].adPo + "  "
					+ tabDef[i].nbParam + "\n";
		s = s + "tabRef         " + " \n";
		for (int i = 1; i <= nbRef; i++)
			s = s 	+ "    " + tabRef[i].nomProc + "  " 
					+ tabRef[i].nbParam + "\n";
		return s;
	}
	
	/** Ecriture du descripteur dans un fichier .desc
	 * 
	 * @param nomFichier : nom du fichier destinataire SANS suffixe
	 */
	
	public void ecrireDesc(String nomFichier) {
		OutputStream f = Ecriture.ouvrir(nomFichier + ".desc");
		if (f == null) {
			System.out
					.println("creation de " + nomFichier + ".desc impossible");
			System.exit(1);
		}
		Ecriture.ecrireStringln(f, "FICHIER " + nomFichier + ".desc :");
		Ecriture.ecrireStringln(f, "");
		Ecriture.ecrireString(f, "" + this);
		Ecriture.fermer(f);
	}

	/** Initialisation d'un descripteur par lecture d'un fichier .desc
	 * 
	 * @param nomFichier : nom du fichier a lire SANS suffixe
	 */
	public void lireDesc(String nomFichier) {
		InputStream f = Lecture.ouvrir(nomFichier + ".desc");
		String nomProc;
		int adPo, nbParam;
		if (f == null) {
			System.out.println("fichier " + nomFichier + ".desc inexistant");
			System.exit(1);
		}
		Lecture.lireString(f);
		Lecture.lireString(f);
		Lecture.lireUnite(f, false);
		unite = Lecture.lireUnite(f, true);
		Lecture.lireUnite(f, false);
		tailleCode = Lecture.lireIntln(f);
		Lecture.lireUnite(f, false);
		tailleGlobaux = Lecture.lireIntln(f);
		Lecture.lireUnite(f, false);
		nbDef = Lecture.lireIntln(f);
		Lecture.lireUnite(f, false);
		nbRef = Lecture.lireIntln(f);
		Lecture.lireUnite(f, false);
		nbTransExt = Lecture.lireIntln(f);
		Lecture.lireString(f);
		for (int i = 1; i <= nbDef; i++) {
			nomProc = Lecture.lireUnite(f, false);
			adPo = Lecture.lireInt(f);
			nbParam = Lecture.lireIntln(f);
			tabDef[i] = new EltDef(nomProc, adPo, nbParam);
		}
		Lecture.lireString(f);
		for (int i = 1; i <= nbRef; i++) {
			nomProc = Lecture.lireUnite(f, false);
			nbParam = Lecture.lireIntln(f);
			tabRef[i] = new EltRef(nomProc, nbParam);
		}
		Lecture.fermer(f);
	}

}