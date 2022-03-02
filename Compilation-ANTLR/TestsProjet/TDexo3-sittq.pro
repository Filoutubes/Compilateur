programme sittq:

const marqueur = -1;
var ent nblu, min, max;		{adresses variables dans la pile d'exécution de MAPILE: 0, 1, 2}
debut
	lire(min); max := min; lire(nblu);
	ttq nblu <> marqueur faire
		si nblu > max alors max := nblu
		sinon si nblu < min alors min := nblu fsi
		fsi;
		lire(nblu);
	fait;
	ecrire(min, max);
fin
