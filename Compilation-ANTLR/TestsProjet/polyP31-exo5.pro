programme exo5:	{valeur d'un polynome par la methode de Horner}

	var ent n, i, x, px, a;

debut
	lire(n, x); px:=0; i:=n;
	ttq i>=0 faire
		lire(a); px:=a+px*x; i:=i-1;
	fait;
	ecrire(x, px);
fin
