Bahdon - Emma - Demure

**COMPILATION DES PROC�DURES**

Il y a 2 grandes parties au niveau de la compilation des proc�dures:



- il nous fallait d�j� compiler leur d�claration (decprocs et decproc)

	-> decprocs regroupe un ensemble de d�claration de proc�dures: il fallait placer le bincond du main avant puis empiler la valeur de l'ipo de ce bincond, et le d�piler � la fin de la d�claration de toutes les 
	   proc�dures (points de g�n�ration 42 et 43).
	-> ensuite, si l'on s'int�resse � decproc, il nous fallait r�cup�rer l'ident (point de g�n�ration 44) pour pouvoir rajouter les lignes n�cessaires dans la table des symboles. Nous avons ensuite r�cup�r� les params fixes/mods pour pouvoir les placer dans la table des symboles (points de g�n�ration 47 et 48). Il ne fallait pas oublier de g�n�rer le point de g�n�ration 45 dans decproc, apr�s avoir lu tous les param�tres, pour modifier dans la table des symboles, le champ info de la d�claration de proc�dures et (plus important) d�caler l'adressage des variables locales de 2 (par rapport aux param�tres), car les adresses nbParams et nbParams+1 sont utilis�es par MAPILE.
	-> � la fin du corps de la fonction, il fallait placer un point de g�n�ration pour effectuer le masquage. Comme pr�cis� dans le code, les variables locales ne sont pas effac�es, elles seront simplement �cras�es en pla�ant it au niveau du dernier param�tre de fonction.

- il nous a ensuite fallu g�rer les appels de fonction et le passage en param�tres (effixes/effmods)

Nous pensons que notre impl�mentation actuelle est fonctionnelle, nous avons su r�gler les probl�mes existant (mauvais comptage du nombre de variables locales pour le "r�server", production d'un affecterg/contenug � la place d'un affecterl/contenul, mauvais adressage des variables locales)

Nous avions juste un doute: lors du lirent/lirebool, peut-on produire un affecterl? Ou bien les m�thodes lirebool/lirent ne peuvent �tre appel�es que depuis un main? Nous n'avons pas d� g�rer cet aspect l�


R�sum�:

Ce qui fonctionne:
- Compilation des d�clarations
- Compilation des expressions
- Compilation des instructions si, ttq, cond
- Compilation des proc�dures
		