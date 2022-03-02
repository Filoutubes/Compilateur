programme exempcond:

var bool b1, b2, b3, b4, b5, b6;	{adresses variables dans la pile d'exécution: 0, 1, 2, 3, 4, 5}
debut
	lire(b1,b2,b3,b4,b5,b6);
	cond
		b1: ecrire(1),
		b2: cond
			b3: ecrire(2),
				b4: ecrire(3),
				b5: ecrire(4)
			aut ecrire(5)
			fcond, 
		b6: ecrire(6)
	fcond ;
fin
