programme simple:	{exemple d'execution dans poly}

	const moinscinq=-5;
	var ent i, n, x, s; bool b;

debut
	lire(n); i:=n; s:=0; b:=faux;
	ttq i>0 faire
		lire(x); s:=s+x; 
		si x=moinscinq alors b:=vrai fsi;
		i:=i-1;
	fait;
	ecrire(s, b);
fin
