
import java.io.*;

/** classe permettant de passer d'un code objet MAPILE (represente par des entiers) 
 * au fichier contenant le code en mnemonique (lisible)
 * 
 * @author Girard, Masson, Perraudeau
 *
 */
public class Mnemo {

	// suite des mnemoniques des codes MAPILE (dans l'ordre croissant des codes)
	public static final String[] inst = { "", "reserver  ", "empiler   ",
			"contenug  ", "affecterg ", "ou        ", "et        ",
			"non       ", "inf       ", "infeg     ", "sup       ",
			"supeg     ", "eg        ", "diff      ", "add       ",
			"sous      ", "mul       ", "div       ", "bsifaux   ",
			"bincond   ", "lirent    ", "lirebool  ", "ecrent    ",
			"ecrbool   ", "arret     ", "empileradg", "empileradl",
			"contenul  ", "affecterl ", "appel     ", "retour    " };

	// suite du nombre d'arguments des codes MAPILE
	public static final int[] nbp = { 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 1 };

	/**
	 * creation d'un fichier de codes MAPILE sous forme mnemonique 
	 * a partir de codes MAPILES entiers
	 * 
	 * @param ipo : indice du dernier code MAPILE entier a prendre en compte
	 * @param po : tableau d'entiers contenant les codes MAPILE entiers d'une unite
	 * @param nomComplet : nom du fichier qui sera cree (il doit etre suffixe par .gen)
	 */
	public static void creerFichier(int ipo, int[] po, String nomComplet) {
		int code, i = 1;
		OutputStream f = Ecriture.ouvrir(nomComplet);
		if (f == null) {
			System.out.println("ouverture de " + nomComplet + " impossible");
			System.exit(1);
		}
		Ecriture.ecrireStringln(f, "FICHIER " + nomComplet + " : ");
		Ecriture.ecrireStringln(f, "");
		while (i <= ipo) {
			code = po[i];
			Ecriture.ecrireInt(f, i, 4);
			if (code < 1 || code >= inst.length) {
				Ecriture.ecrireStringln(f, " code instruction incorrect : "
						+ code);
				Ecriture.fermer(f);
				System.out.println("code mal genere, consultez le fichier "
						+ nomComplet);
				System.exit(1);
			}
			Ecriture.ecrireString(f, "  " + inst[code]);
			switch (nbp[code]) {
			case 1:
				i++;
				Ecriture.ecrireInt(f, po[i], 7);
				break;
			case 2:
				i++;
				Ecriture.ecrireInt(f, po[i], 7);
				i++;
				Ecriture.ecrireInt(f, po[i], 4);
				break;
			}
			i++;
			Ecriture.ecrireStringln(f, "");
		}
		Ecriture.fermer(f);
	}

}
