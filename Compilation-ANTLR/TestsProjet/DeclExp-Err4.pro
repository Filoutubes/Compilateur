programme premiertest:	{erreur testee: type incorrect dans une expression}

const min=7; max=+77; marq=-1; oui=vrai; nenni=faux;
var ent i,j;
    bool b1,b2,b3;

debut

	i:= (max-min) div 2;
	b1:= i et nenni;	{i: valeur booleenne attendue}
	b2:= non b1 et (oui ou nenni);
	j:= (i+5)*10;
	b3:= (i<=j) ou (i<>10);
	ecrire(i,j);
	ecrire(b1,b2,b3);
fin
