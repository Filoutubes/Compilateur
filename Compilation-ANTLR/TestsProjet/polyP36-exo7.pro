programme exo7:

	var ent res; bool op;

proc recurs mod (ent r)
	var ent x, rx;
debut
	lire(x);
	cond
		x=0: r:=0; op:=vrai, {addition}
		x=-1: r:=1; op:=faux  {multiplication}
	aut
		recurs()(rx); 
		si op alors r:=x+rx sinon r:=x*rx fsi
	fcond
fin;

debut
	recurs()(res); ecrire(res);
fin
