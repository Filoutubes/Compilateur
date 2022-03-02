programme exo4:

{min et max d'une suite d'entiers terminee par -1}

	const marqueur=-1;
	var ent nblu, min, max;

debut
	lire(nblu);
	si nblu=marqueur alors min:=0; max:=0 sinon min:=nblu; max:=nblu fsi;
	ttq nblu<>marqueur faire
		cond
			nblu>max: max:=nblu,
			nblu<min: min:=nblu
		fcond;
		lire(nblu);
	fait;
	ecrire(min, max)
fin
