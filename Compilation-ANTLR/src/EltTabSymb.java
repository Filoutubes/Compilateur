
/**
 *  classe de definition d'un element d'une table des symboles
 *  
 *  4 champs pour chaque identificateur:
 *  
 *  code = numIdCourant, fourni par l'analyseur lexical, de l'identificateur 
 *       (-1 si non significatif)
 *       
 *  categorie = CONSTANTE ou VARGLOBALE ou VARLOCALE ou PARAMFIXE
 *             ou PARAMMMOD ou PROC ou DEF ou REF ou PRIVEE
 *             
 *  type = type de l'ident (ENT ou BOOL),NEUTRE si non significatif
 *  
 *  info = info necessaire a la compilation de l'ident (valeur si constante,
 *        ad d'execution si variable, etc.)
 */
import java.io.*;

	public class EltTabSymb {
		public int code, categorie, type, info;

		public EltTabSymb(int code, int categorie, int type, int info) {
			this.code = code;
			this.categorie = categorie;
			this.type = type;
			this.info = info;
		}
	
		public String toString() {
			final String[] chcat = { "", "CONSTANTE      ", "VARGLOBALE     ",
					"VARLOCALE      ", "PARAMFIXE      ", "PARAMMOD       ",
					"PROC           ", "DEF            ", "REF            ",
					"PRIVEE         " };
			final String[] chtype = { "", "ENT     ", "BOOL    ", "NEUTRE  " };
			String ch = "";
			if (code == -1)
				ch += "-1";
			else
				ch += "@" + UtilLex.chaineIdent(code);
			while (ch.length() < 15)
				ch += ' ';
			return ch + chcat[categorie] + chtype[type] + info;
		} // toString
	} // EltTabSymb


