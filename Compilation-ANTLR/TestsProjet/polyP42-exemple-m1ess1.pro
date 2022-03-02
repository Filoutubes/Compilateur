module m1ess1:	{compilation separee}

	def init1, ajout1, ecr1;
	var ent n, min, max;

	proc init1
	debut
		n:=0;
	fin;
	
	proc ajout1 fixe (ent y)
	debut
		si n=0 alors min:=y; max:=y
		sinon si y<min alors min:=y sinon si y>max alors max:=y fsi fsi
		fsi;
		n:=n+1;
	fin;

	proc ecr1
	debut
		si n<>0 alors ecrire(n,min,max) sinon ecrire(0) fsi
	fin;
