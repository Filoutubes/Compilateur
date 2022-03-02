programme exo2b :

{on lit une suite de valeurs entieres terminee par -1 et on denombre les valeurs comprises dans l'intervalle 0..9 ainsi que les valeurs comprises dans l'intervalle 10..20}

	const minx=0; maxx=20; moyx=10;
	var ent n1, n2, x;
debut
	n1:=0;  {nbre de valeurs < moyx }
	n2:=0;  {nbre de valeurs >=moyx }
	lire(x);
	ttq x<>-1 faire
		cond
			x<minx ou x>maxx:,
			x<moyx: n1:=n1+1,
			x>=moyx: n2:=n2+1
		fcond;
		lire(x);
	fait;
	ecrire(n1); ecrire(n2);
fin
