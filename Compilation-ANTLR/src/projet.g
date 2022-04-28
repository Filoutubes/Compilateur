// Grammaire du langage PROJET
// CMPL L3info 
// Nathalie Girard, Anne Grazon, Veronique Masson
// il convient d'y inserer les appels a {PtGen.pt(k);}
// relancer Antlr apres chaque modification et raffraichir le projet Eclipse le cas echeant

// attention l'analyse est poursuivie apres erreur si l'on supprime la clause rulecatch

grammar projet;

options {
  language=Java; k=1;
 }

@header {           
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;
} 


// partie syntaxique :  description de la grammaire //
// les non-terminaux doivent commencer par une minuscule


@members {

 
// variables globales et methodes utiles a placer ici
  
}
// la directive rulecatch permet d'interrompre l'analyse a la premiere erreur de syntaxe
@rulecatch {
catch (RecognitionException e) {reportError (e) ; throw e ; }}


unite  :   unitprog {PtGen.pt(255);} EOF
      |    unitmodule {PtGen.pt(255);} EOF
  ;
  
unitprog
  : {PtGen.pt(61);} 'programme' {PtGen.pt(59);} ident ':'  
     declarations  
     corps { System.out.println("succes, arret de la compilation "); }
  ;
  
unitmodule
  : {PtGen.pt(61);} 'module' {PtGen.pt(60);} ident ':' 
     declarations {PtGen.pt(62);} 
  ;
  
declarations
  : partiedef? partieref? consts? vars? decprocs? 
  ;
  
partiedef
  : 'def' ident {PtGen.pt(57);} (',' ident {PtGen.pt(57);} )* ptvg
  ;
  
partieref: 'ref'  specif {PtGen.pt(56);} (',' specif {PtGen.pt(56);})* ptvg
  ;
  
specif  : ident  ( 'fixe' '(' type {PtGen.pt(58);} ( ',' type {PtGen.pt(58);} )* ')' )? 
                 ( 'mod'  '(' type {PtGen.pt(58);} ( ',' type {PtGen.pt(58);} )* ')' )? 
  ;
  
consts  : 'const' ( ident  '=' valeur {PtGen.pt(2);} ptvg  )+ 
  ;
  
vars  : 'var' ( type ident {PtGen.pt(7);} ( ','  ident {PtGen.pt(7);} )* ptvg  )+ {PtGen.pt(30);}
  ;
  
type  : 'ent' {PtGen.pt(14);} 
  |     'bool' {PtGen.pt(9);}
  ;
  
decprocs: {PtGen.pt(42);} (decproc ptvg)+ {PtGen.pt(43);}
  ;
  
decproc :  'proc'  ident {PtGen.pt(44);} parfixe? parmod? {PtGen.pt(45);} consts? vars? corps {PtGen.pt(46);}
  ;
  
ptvg  : ';'
  | 
  ;
  
corps : 'debut' instructions 'fin'
  ;
  
parfixe: 'fixe' '(' pf ( ';' pf)* ')'
  ;
  
pf  : type ident {PtGen.pt(47);} ( ',' ident {PtGen.pt(47);} )*  
  ;

parmod  : 'mod' '(' pm ( ';' pm)* ')'
  ;
  
pm  : type ident {PtGen.pt(48);} ( ',' ident {PtGen.pt(48);} )*
  ;
  
instructions
  : instruction ( ';' instruction)*
  ;
  
instruction
  : inssi
  | inscond
  | boucle
  | lecture
  | ecriture
  | affouappel
  |
  ;
  
inssi : 'si' expression {PtGen.pt(8);} 'alors' instructions ('sinon' {PtGen.pt(34);} instructions )? {PtGen.pt(35);} 'fsi' 
  ;
  
inscond : 'cond' {PtGen.pt(37);} expression {PtGen.pt(8);} ':' instructions
          (',' {PtGen.pt(38);} expression {PtGen.pt(8);} ':' instructions )*
          ({PtGen.pt(40);} 'aut' instructions | ) {PtGen.pt(39);}
          'fcond' 
  ;
  
boucle  : 'ttq' {PtGen.pt(41);} expression {PtGen.pt(8);} 'faire' instructions {PtGen.pt(36);} 'fait' 
  ;
  
lecture: 'lire' '(' ident {PtGen.pt(28);} ( ',' ident {PtGen.pt(28);} )* ')' 
  ;
  
ecriture: 'ecrire' '(' expression {PtGen.pt(29);} ( ',' expression {PtGen.pt(29);} )* ')'
   ;
  
affouappel
  : ident  ( {PtGen.pt(31);}   ':=' expression {PtGen.pt(32);}
            |  {PtGen.pt(50);} (effixes {PtGen.pt(54);} (effmods {PtGen.pt(55);} )?)? {PtGen.pt(49);}
           )
  ;
  
effixes : '(' {PtGen.pt(52);} (expression {PtGen.pt(53);} (',' expression {PtGen.pt(53);} )*)?  ')'
  ;
  
effmods :'(' (ident {PtGen.pt(51);} (',' ident {PtGen.pt(51);} )*)? ')'
  ; 
  
expression: (exp1) ('ou' {PtGen.pt(13);} exp1 {PtGen.pt(13);} {PtGen.pt(11);} )*
  ;
  
exp1  : exp2 ('et' {PtGen.pt(13);} exp2 {PtGen.pt(13);} {PtGen.pt(10);} )*
  ;
  
exp2  : 'non' exp2 {PtGen.pt(13);} {PtGen.pt(12);}
  | exp3  
  ;
  
exp3  : exp4 
  ( '=' {PtGen.pt(25);}  exp4 {PtGen.pt(25);} {PtGen.pt(16);}
  | '<>' {PtGen.pt(25);}  exp4 {PtGen.pt(25);} {PtGen.pt(15);}
  | '>' {PtGen.pt(25);}  exp4 {PtGen.pt(25);} {PtGen.pt(18);}
  | '>=' {PtGen.pt(25);} exp4 {PtGen.pt(25);} {PtGen.pt(20);}
  | '<' {PtGen.pt(25);}  exp4 {PtGen.pt(25);} {PtGen.pt(17);}
  | '<=' {PtGen.pt(25);} exp4  {PtGen.pt(25);} {PtGen.pt(19);}
  ) ?
  ;
  
exp4  : exp5 
        ('+' {PtGen.pt(25);} exp5 {PtGen.pt(25);} {PtGen.pt(21);}
        |'-' {PtGen.pt(25);} exp5 {PtGen.pt(25);} {PtGen.pt(22);}
        )*
  ;
  
exp5  : primaire 
        (    '*' {PtGen.pt(25);}  primaire {PtGen.pt(25);} {PtGen.pt(24);}
          | 'div' {PtGen.pt(25);} primaire {PtGen.pt(25);} {PtGen.pt(23);}
        )*
  ;
  
primaire: valeur {PtGen.pt(27);}
  | ident {PtGen.pt(33);}
  | '(' expression ')'
  ;
  
valeur  : nbentier {PtGen.pt(3);}
  | '+' nbentier {PtGen.pt(3);}
  | '-' nbentier {PtGen.pt(4);}
  | 'vrai' {PtGen.pt(5);}
  | 'faux' {PtGen.pt(6);}
  ;

// partie lexicale  : cette partie ne doit pas etre modifiee  //
// les unites lexicales de ANTLR doivent commencer par une majuscule
// Attention : ANTLR n'autorise pas certains traitements sur les unites lexicales, 
// il est alors ncessaire de passer par un non-terminal intermediaire 
// exemple : pour l'unit lexicale INT, le non-terminal nbentier a du etre introduit
 
      
nbentier  :   INT { UtilLex.valEnt = Integer.parseInt($INT.text);}; // mise a jour de valEnt

ident : ID  { UtilLex.traiterId($ID.text); } ; // mise a jour de numIdCourant
     // tous les identificateurs seront places dans la table des identificateurs, y compris le nom du programme ou module
     // (NB: la table des symboles n'est pas geree au niveau lexical mais au niveau du compilateur)
        
  
ID  :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ; 
     
// zone purement lexicale //

INT :   '0'..'9'+ ;
WS  :   (' '|'\t' |'\r')+ {skip();} ; // definition des "blocs d'espaces"
RC  :   ('\n') {UtilLex.incrementeLigne(); skip() ;} ; // definition d'un unique "passage a la ligne" et comptage des numeros de lignes

COMMENT
  :  '\{' (.)* '\}' {skip();}   // toute suite de caracteres entouree d'accolades est un commentaire
  |  '#' ~( '\r' | '\n' )* {skip();}  // tout ce qui suit un caractere diese sur une ligne est un commentaire
  ;

// commentaires sur plusieurs lignes
ML_COMMENT    :   '/*' (options {greedy=false;} : .)* '*/' {$channel=HIDDEN;}
    ;	   



	   
