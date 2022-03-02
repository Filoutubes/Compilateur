programme exemple2ChapIII :
var ent n;

proc p fixe (ent x) mod (ent y)
	var ent j;
debut
	j:=0;
	y:=x;
fin;

proc q mod (ent z)
	var ent i;
debut
	p(2)(i);
	p(i)(z);
	p(z)(i);
fin;

debut
     q()(n);
     ecrire(n)
fin