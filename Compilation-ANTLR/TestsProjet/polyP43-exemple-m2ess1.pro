module m2ess1:	{compilation separee}

	def init2, ajout2, ecr2;
	var ent n, min, max;

	proc init2
	debut
		n:=0;
	fin;
	
	proc ajout2 fixe (ent y)
	debut
		si n=0 alors min:=y; max:=y
		sinon si y<min alors min:=y sinon si y>max alors max:=y fsi fsi
		fsi;
		n:=n+1;
	fin;

	proc ecr2
	debut
		si n<>0 alors ecrire(n,min,max) sinon ecrire(0) fsi
	fin;
