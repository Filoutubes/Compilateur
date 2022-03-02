module m1 :
def ajout;
ref init mod (ent);
var ent n;
proc ajout fixe (ent z)
debut
	si z=0 alors init()(n)
	sinon n:=n+z
	fsi
fin;