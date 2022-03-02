programme exo3a:

{recherche d'une valeur dans une suite d'entiers terminee par -1}

	const vconnue=12; marqueur=-1;
	var ent vlue; bool res;

debut
   	lire(vlue);
   	ttq vlue<>marqueur et vlue<>vconnue faire
		lire(vlue)
	fait;
	res:=vlue=vconnue;
	ecrire(res);
fin
