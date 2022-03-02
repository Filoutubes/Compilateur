programme exo8:	

	var ent res; bool op;

proc verifdrapeau fixe (ent v) mod (ent r; bool b)
	var bool drap;
debut
	drap:=faux;
	cond
		v=0: drap:=vrai; r:=0; op:=vrai, {addition}
		v=-1: drap:=vrai; r:=1; op:=faux  {multiplication}
	fcond;
	b:=drap;
fin;

proc recurs mod (ent r)
	var ent x; bool marq;
debut
	lire(x);
	verifdrapeau(x)(r, marq);
	si non marq alors
		recurs()(r); 
		si op alors r:=x+r sinon r:=x*r fsi
	fsi
fin;

debut
	recurs()(res); ecrire(res);
fin
