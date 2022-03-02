programme p :
ref init mod (ent),
	ajout fixe (ent);
var ent x;
debut
     init()(x);
     ajout(0); 
     x:=3;
     ajout(x)
fin
