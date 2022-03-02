programme exo2a :

{on lit une suite de valeurs entieres terminee par -1 et on denombre les valeurs comprises dans l'intervalle 0..9 ainsi que les valeurs comprises dans l'intervalle 10..20}

	const minx=0; maxx=20; moyx=10;
	var ent n1, n2, x;
debut
	n1:=0;  {nbre de valeurs < moyx }
	n2:=0;  {nbre de valeurs >=moyx }
	lire(x);
	ttq x<>-1 faire
 		si x>=minx et x<=maxx alors
      			si x<moyx alors n1:=n1+1 sinon n2:=n2+1 fsi
 		fsi;
 		lire(x);
	fait;
	ecrire(n1, n2);
fin
