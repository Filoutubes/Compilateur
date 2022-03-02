programme ess1:	{compilation separee}

	ref init1, init2, ajout1 fixe (ent), ajout2 fixe (ent), ecr1, ecr2;
	const minx=0; maxx=20; moyx=10;
	var ent x;

debut
	init1; init2; lire(x);
	ttq x<>-1 faire
		si x>=minx et x<=maxx alors
			si x<moyx alors ajout1(x) sinon ajout2(x) fsi;
		fsi;
		lire(x);
	fait;
	ecr1; ecr2;
fin
