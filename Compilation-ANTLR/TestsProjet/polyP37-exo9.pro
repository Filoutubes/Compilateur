programme exo9:	

	var ent x1, y1, x2, y2, d;

proc carre fixe (ent v) mod (ent v2)
debut
	v2:=v*v 
fin;

proc dist fixe (ent x1, y1, x2, y2) mod (ent d)
	var ent d1, d2;
debut
	carre(x2-x1)(d1);
	carre(y2-y1)(d2);
	d:=d1+d2;
fin

debut
	lire(x1, y1, x2, y2);
	dist(x1, y1, x2, y2)(d); 
	ecrire(d);
fin
