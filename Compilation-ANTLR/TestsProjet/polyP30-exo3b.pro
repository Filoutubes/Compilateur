programme exo3b : 
{comptage des occurrences d'une valeur dans une suite d entiers 
 terminée par -1
}
   const vconnue=12 ; marqueur=-1 ;
   var ent vlue, nbv, nbautre ;
debut
   nbv:=0 ; nbautre:=0 ;
   lire (vlue) ;
   ttq vlue<>marqueur faire
      si vlue=vconnue alors nbv:=nbv+1 sinon nbautre:=nbautre+1 fsi ;
      lire (vlue)
   fait ;
   ecrire (nbv, nbautre) ;
fin {exo3b}
