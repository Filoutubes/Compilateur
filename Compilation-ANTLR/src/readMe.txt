Bahdon - Emma - Demure

**COMPILATION DES PROCÉDURES**

Il y a 2 grandes parties au niveau de la compilation des procédures:



- il nous fallait déjà compiler leur déclaration (decprocs et decproc)

	-> decprocs regroupe un ensemble de déclaration de procédures: il fallait placer le bincond du main avant puis empiler la valeur de l'ipo de ce bincond, et le dépiler à la fin de la déclaration de toutes les 
	   procédures (points de génération 42 et 43).
	-> ensuite, si l'on s'intéresse à decproc, il nous fallait récupérer l'ident (point de génération 44) pour pouvoir rajouter les lignes nécessaires dans la table des symboles. Nous avons ensuite récupéré les params fixes/mods pour pouvoir les placer dans la table des symboles (points de génération 47 et 48). Il ne fallait pas oublier de générer le point de génération 45 dans decproc, après avoir lu tous les paramètres, pour modifier dans la table des symboles, le champ info de la déclaration de procédures et (plus important) décaler l'adressage des variables locales de 2 (par rapport aux paramètres), car les adresses nbParams et nbParams+1 sont utilisées par MAPILE.
	-> à la fin du corps de la fonction, il fallait placer un point de génération pour effectuer le masquage. Comme précisé dans le code, les variables locales ne sont pas effacées, elles seront simplement écrasées en plaçant it au niveau du dernier paramètre de fonction.

- il nous a ensuite fallu gérer les appels de fonction et le passage en paramètres (effixes/effmods)

Nous pensons que notre implémentation actuelle est fonctionnelle, nous avons su régler les problèmes existant (mauvais comptage du nombre de variables locales pour le "réserver", production d'un affecterg/contenug à la place d'un affecterl/contenul, mauvais adressage des variables locales)

Nous avions juste un doute: lors du lirent/lirebool, peut-on produire un affecterl? Ou bien les méthodes lirebool/lirent ne peuvent être appelées que depuis un main? Nous n'avons pas dû gérer cet aspect là


Résumé:

Ce qui fonctionne:
- Compilation des déclarations
- Compilation des expressions
- Compilation des instructions si, ttq, cond
- Compilation des procédures
		