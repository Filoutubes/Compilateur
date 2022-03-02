programme deuxprocs:	{exemple d'execution dans poly}

	const n=10; var ent x;

	proc p1 mod (ent n)
		const m=3; var ent x, i;
	debut
		i:=m; n:=0;
		ttq i>0 faire lire(x); n:=n+x; i:=i-1 fait
	fin;

	proc p2 fixe (ent nbf, barre)
		var ent y, i, res;
	debut
		i:=nbf; res:=0;
		ttq i>0 faire 
			p1()(y); 
			si y>=barre alors res:=res+1 fsi; 
			i:=i-1 
		fait;
		x:=res
	fin;

debut
	lire(x);
	p2(x+1, n)();
	ecrire(x)
fin
