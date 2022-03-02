programme exo1: 	{somme de n valeurs}

	var ent n, som, i, x;
debut
	lire(n);
	som:=0; i:=1;
	ttq i<=n faire
		lire(x);
		som:=som+x; i:=i+1;
	fait;
	ecrire(som)
fin
