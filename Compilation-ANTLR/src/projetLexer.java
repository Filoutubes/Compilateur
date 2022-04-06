// $ANTLR 3.5.2 projet.g 2022-04-06 16:33:18

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class projetLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int COMMENT=4;
	public static final int ID=5;
	public static final int INT=6;
	public static final int ML_COMMENT=7;
	public static final int RC=8;
	public static final int WS=9;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public projetLexer() {} 
	public projetLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public projetLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "projet.g"; }

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:7:7: ( '(' )
			// projet.g:7:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:8:7: ( ')' )
			// projet.g:8:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:9:7: ( '*' )
			// projet.g:9:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:10:7: ( '+' )
			// projet.g:10:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:11:7: ( ',' )
			// projet.g:11:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:12:7: ( '-' )
			// projet.g:12:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:13:7: ( ':' )
			// projet.g:13:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:14:7: ( ':=' )
			// projet.g:14:9: ':='
			{
			match(":="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:15:7: ( ';' )
			// projet.g:15:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:16:7: ( '<' )
			// projet.g:16:9: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:17:7: ( '<=' )
			// projet.g:17:9: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:18:7: ( '<>' )
			// projet.g:18:9: '<>'
			{
			match("<>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:19:7: ( '=' )
			// projet.g:19:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:20:7: ( '>' )
			// projet.g:20:9: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:21:7: ( '>=' )
			// projet.g:21:9: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:22:7: ( 'alors' )
			// projet.g:22:9: 'alors'
			{
			match("alors"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:23:7: ( 'aut' )
			// projet.g:23:9: 'aut'
			{
			match("aut"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:24:7: ( 'bool' )
			// projet.g:24:9: 'bool'
			{
			match("bool"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:25:7: ( 'cond' )
			// projet.g:25:9: 'cond'
			{
			match("cond"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:26:7: ( 'const' )
			// projet.g:26:9: 'const'
			{
			match("const"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:27:7: ( 'debut' )
			// projet.g:27:9: 'debut'
			{
			match("debut"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:28:7: ( 'def' )
			// projet.g:28:9: 'def'
			{
			match("def"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:29:7: ( 'div' )
			// projet.g:29:9: 'div'
			{
			match("div"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:30:7: ( 'ecrire' )
			// projet.g:30:9: 'ecrire'
			{
			match("ecrire"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:31:7: ( 'ent' )
			// projet.g:31:9: 'ent'
			{
			match("ent"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:32:7: ( 'et' )
			// projet.g:32:9: 'et'
			{
			match("et"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:33:7: ( 'faire' )
			// projet.g:33:9: 'faire'
			{
			match("faire"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:34:7: ( 'fait' )
			// projet.g:34:9: 'fait'
			{
			match("fait"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:35:7: ( 'faux' )
			// projet.g:35:9: 'faux'
			{
			match("faux"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:36:7: ( 'fcond' )
			// projet.g:36:9: 'fcond'
			{
			match("fcond"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:37:7: ( 'fin' )
			// projet.g:37:9: 'fin'
			{
			match("fin"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:38:7: ( 'fixe' )
			// projet.g:38:9: 'fixe'
			{
			match("fixe"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:39:7: ( 'fsi' )
			// projet.g:39:9: 'fsi'
			{
			match("fsi"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:40:7: ( 'lire' )
			// projet.g:40:9: 'lire'
			{
			match("lire"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:41:7: ( 'mod' )
			// projet.g:41:9: 'mod'
			{
			match("mod"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:42:7: ( 'module' )
			// projet.g:42:9: 'module'
			{
			match("module"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:43:7: ( 'non' )
			// projet.g:43:9: 'non'
			{
			match("non"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:44:7: ( 'ou' )
			// projet.g:44:9: 'ou'
			{
			match("ou"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:45:7: ( 'proc' )
			// projet.g:45:9: 'proc'
			{
			match("proc"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__48"

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:46:7: ( 'programme' )
			// projet.g:46:9: 'programme'
			{
			match("programme"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:47:7: ( 'ref' )
			// projet.g:47:9: 'ref'
			{
			match("ref"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:48:7: ( 'si' )
			// projet.g:48:9: 'si'
			{
			match("si"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:49:7: ( 'sinon' )
			// projet.g:49:9: 'sinon'
			{
			match("sinon"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:50:7: ( 'ttq' )
			// projet.g:50:9: 'ttq'
			{
			match("ttq"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:51:7: ( 'var' )
			// projet.g:51:9: 'var'
			{
			match("var"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:52:7: ( 'vrai' )
			// projet.g:52:9: 'vrai'
			{
			match("vrai"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__55"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:204:5: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// projet.g:204:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// projet.g:204:28: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// projet.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:208:5: ( ( '0' .. '9' )+ )
			// projet.g:208:9: ( '0' .. '9' )+
			{
			// projet.g:208:9: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// projet.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:209:5: ( ( ' ' | '\\t' | '\\r' )+ )
			// projet.g:209:9: ( ' ' | '\\t' | '\\r' )+
			{
			// projet.g:209:9: ( ' ' | '\\t' | '\\r' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0=='\t'||LA3_0=='\r'||LA3_0==' ') ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// projet.g:
					{
					if ( input.LA(1)=='\t'||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "RC"
	public final void mRC() throws RecognitionException {
		try {
			int _type = RC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:210:5: ( ( '\\n' ) )
			// projet.g:210:9: ( '\\n' )
			{
			// projet.g:210:9: ( '\\n' )
			// projet.g:210:10: '\\n'
			{
			match('\n'); 
			}

			UtilLex.incrementeLigne(); skip() ;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RC"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:213:3: ( '\\{' ( . )* '\\}' | '#' (~ ( '\\r' | '\\n' ) )* )
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0=='{') ) {
				alt6=1;
			}
			else if ( (LA6_0=='#') ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// projet.g:213:6: '\\{' ( . )* '\\}'
					{
					match('{'); 
					// projet.g:213:11: ( . )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0=='}') ) {
							alt4=2;
						}
						else if ( ((LA4_0 >= '\u0000' && LA4_0 <= '|')||(LA4_0 >= '~' && LA4_0 <= '\uFFFF')) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// projet.g:213:12: .
							{
							matchAny(); 
							}
							break;

						default :
							break loop4;
						}
					}

					match('}'); 
					skip();
					}
					break;
				case 2 :
					// projet.g:214:6: '#' (~ ( '\\r' | '\\n' ) )*
					{
					match('#'); 
					// projet.g:214:10: (~ ( '\\r' | '\\n' ) )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// projet.g:
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop5;
						}
					}

					skip();
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "ML_COMMENT"
	public final void mML_COMMENT() throws RecognitionException {
		try {
			int _type = ML_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// projet.g:218:15: ( '/*' ( options {greedy=false; } : . )* '*/' )
			// projet.g:218:19: '/*' ( options {greedy=false; } : . )* '*/'
			{
			match("/*"); 

			// projet.g:218:24: ( options {greedy=false; } : . )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0=='*') ) {
					int LA7_1 = input.LA(2);
					if ( (LA7_1=='/') ) {
						alt7=2;
					}
					else if ( ((LA7_1 >= '\u0000' && LA7_1 <= '.')||(LA7_1 >= '0' && LA7_1 <= '\uFFFF')) ) {
						alt7=1;
					}

				}
				else if ( ((LA7_0 >= '\u0000' && LA7_0 <= ')')||(LA7_0 >= '+' && LA7_0 <= '\uFFFF')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// projet.g:218:51: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop7;
				}
			}

			match("*/"); 

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ML_COMMENT"

	@Override
	public void mTokens() throws RecognitionException {
		// projet.g:1:8: ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | ID | INT | WS | RC | COMMENT | ML_COMMENT )
		int alt8=52;
		alt8 = dfa8.predict(input);
		switch (alt8) {
			case 1 :
				// projet.g:1:10: T__10
				{
				mT__10(); 

				}
				break;
			case 2 :
				// projet.g:1:16: T__11
				{
				mT__11(); 

				}
				break;
			case 3 :
				// projet.g:1:22: T__12
				{
				mT__12(); 

				}
				break;
			case 4 :
				// projet.g:1:28: T__13
				{
				mT__13(); 

				}
				break;
			case 5 :
				// projet.g:1:34: T__14
				{
				mT__14(); 

				}
				break;
			case 6 :
				// projet.g:1:40: T__15
				{
				mT__15(); 

				}
				break;
			case 7 :
				// projet.g:1:46: T__16
				{
				mT__16(); 

				}
				break;
			case 8 :
				// projet.g:1:52: T__17
				{
				mT__17(); 

				}
				break;
			case 9 :
				// projet.g:1:58: T__18
				{
				mT__18(); 

				}
				break;
			case 10 :
				// projet.g:1:64: T__19
				{
				mT__19(); 

				}
				break;
			case 11 :
				// projet.g:1:70: T__20
				{
				mT__20(); 

				}
				break;
			case 12 :
				// projet.g:1:76: T__21
				{
				mT__21(); 

				}
				break;
			case 13 :
				// projet.g:1:82: T__22
				{
				mT__22(); 

				}
				break;
			case 14 :
				// projet.g:1:88: T__23
				{
				mT__23(); 

				}
				break;
			case 15 :
				// projet.g:1:94: T__24
				{
				mT__24(); 

				}
				break;
			case 16 :
				// projet.g:1:100: T__25
				{
				mT__25(); 

				}
				break;
			case 17 :
				// projet.g:1:106: T__26
				{
				mT__26(); 

				}
				break;
			case 18 :
				// projet.g:1:112: T__27
				{
				mT__27(); 

				}
				break;
			case 19 :
				// projet.g:1:118: T__28
				{
				mT__28(); 

				}
				break;
			case 20 :
				// projet.g:1:124: T__29
				{
				mT__29(); 

				}
				break;
			case 21 :
				// projet.g:1:130: T__30
				{
				mT__30(); 

				}
				break;
			case 22 :
				// projet.g:1:136: T__31
				{
				mT__31(); 

				}
				break;
			case 23 :
				// projet.g:1:142: T__32
				{
				mT__32(); 

				}
				break;
			case 24 :
				// projet.g:1:148: T__33
				{
				mT__33(); 

				}
				break;
			case 25 :
				// projet.g:1:154: T__34
				{
				mT__34(); 

				}
				break;
			case 26 :
				// projet.g:1:160: T__35
				{
				mT__35(); 

				}
				break;
			case 27 :
				// projet.g:1:166: T__36
				{
				mT__36(); 

				}
				break;
			case 28 :
				// projet.g:1:172: T__37
				{
				mT__37(); 

				}
				break;
			case 29 :
				// projet.g:1:178: T__38
				{
				mT__38(); 

				}
				break;
			case 30 :
				// projet.g:1:184: T__39
				{
				mT__39(); 

				}
				break;
			case 31 :
				// projet.g:1:190: T__40
				{
				mT__40(); 

				}
				break;
			case 32 :
				// projet.g:1:196: T__41
				{
				mT__41(); 

				}
				break;
			case 33 :
				// projet.g:1:202: T__42
				{
				mT__42(); 

				}
				break;
			case 34 :
				// projet.g:1:208: T__43
				{
				mT__43(); 

				}
				break;
			case 35 :
				// projet.g:1:214: T__44
				{
				mT__44(); 

				}
				break;
			case 36 :
				// projet.g:1:220: T__45
				{
				mT__45(); 

				}
				break;
			case 37 :
				// projet.g:1:226: T__46
				{
				mT__46(); 

				}
				break;
			case 38 :
				// projet.g:1:232: T__47
				{
				mT__47(); 

				}
				break;
			case 39 :
				// projet.g:1:238: T__48
				{
				mT__48(); 

				}
				break;
			case 40 :
				// projet.g:1:244: T__49
				{
				mT__49(); 

				}
				break;
			case 41 :
				// projet.g:1:250: T__50
				{
				mT__50(); 

				}
				break;
			case 42 :
				// projet.g:1:256: T__51
				{
				mT__51(); 

				}
				break;
			case 43 :
				// projet.g:1:262: T__52
				{
				mT__52(); 

				}
				break;
			case 44 :
				// projet.g:1:268: T__53
				{
				mT__53(); 

				}
				break;
			case 45 :
				// projet.g:1:274: T__54
				{
				mT__54(); 

				}
				break;
			case 46 :
				// projet.g:1:280: T__55
				{
				mT__55(); 

				}
				break;
			case 47 :
				// projet.g:1:286: ID
				{
				mID(); 

				}
				break;
			case 48 :
				// projet.g:1:289: INT
				{
				mINT(); 

				}
				break;
			case 49 :
				// projet.g:1:293: WS
				{
				mWS(); 

				}
				break;
			case 50 :
				// projet.g:1:296: RC
				{
				mRC(); 

				}
				break;
			case 51 :
				// projet.g:1:299: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 52 :
				// projet.g:1:307: ML_COMMENT
				{
				mML_COMMENT(); 

				}
				break;

		}
	}


	protected DFA8 dfa8 = new DFA8(this);
	static final String DFA8_eotS =
		"\7\uffff\1\42\1\uffff\1\45\1\uffff\1\47\17\33\15\uffff\10\33\1\110\7\33"+
		"\1\122\2\33\1\126\4\33\1\133\3\33\1\140\1\141\1\33\1\143\1\uffff\3\33"+
		"\1\150\1\33\1\152\1\33\1\155\1\156\1\uffff\1\33\1\161\1\33\1\uffff\1\163"+
		"\1\164\2\33\1\uffff\1\167\1\170\2\33\2\uffff\1\33\1\uffff\1\33\1\175\1"+
		"\176\1\33\1\uffff\1\u0080\1\uffff\1\u0081\1\33\2\uffff\1\u0083\1\33\1"+
		"\uffff\1\33\2\uffff\1\u0086\1\u0087\2\uffff\1\u0088\1\u0089\1\33\1\u008b"+
		"\2\uffff\1\u008c\2\uffff\1\33\1\uffff\1\33\1\u008f\4\uffff\1\u0090\2\uffff"+
		"\1\u0091\1\33\3\uffff\2\33\1\u0095\1\uffff";
	static final String DFA8_eofS =
		"\u0096\uffff";
	static final String DFA8_minS =
		"\1\11\6\uffff\1\75\1\uffff\1\75\1\uffff\1\75\1\154\2\157\1\145\1\143\1"+
		"\141\1\151\2\157\1\165\1\162\1\145\1\151\1\164\1\141\15\uffff\1\157\1"+
		"\164\1\157\1\156\1\142\1\166\1\162\1\164\1\60\1\151\1\157\1\156\1\151"+
		"\1\162\1\144\1\156\1\60\1\157\1\146\1\60\1\161\1\162\1\141\1\162\1\60"+
		"\1\154\1\144\1\165\2\60\1\151\1\60\1\uffff\1\162\1\170\1\156\1\60\1\145"+
		"\1\60\1\145\2\60\1\uffff\1\143\1\60\1\157\1\uffff\2\60\1\151\1\163\1\uffff"+
		"\2\60\2\164\2\uffff\1\162\1\uffff\1\145\2\60\1\144\1\uffff\1\60\1\uffff"+
		"\1\60\1\154\2\uffff\1\60\1\162\1\uffff\1\156\2\uffff\2\60\2\uffff\2\60"+
		"\1\145\1\60\2\uffff\1\60\2\uffff\1\145\1\uffff\1\141\1\60\4\uffff\1\60"+
		"\2\uffff\1\60\1\155\3\uffff\1\155\1\145\1\60\1\uffff";
	static final String DFA8_maxS =
		"\1\173\6\uffff\1\75\1\uffff\1\76\1\uffff\1\75\1\165\2\157\1\151\1\164"+
		"\1\163\1\151\2\157\1\165\1\162\1\145\1\151\1\164\1\162\15\uffff\1\157"+
		"\1\164\1\157\1\156\1\146\1\166\1\162\1\164\1\172\1\165\1\157\1\170\1\151"+
		"\1\162\1\144\1\156\1\172\1\157\1\146\1\172\1\161\1\162\1\141\1\162\1\172"+
		"\1\154\1\163\1\165\2\172\1\151\1\172\1\uffff\1\164\1\170\1\156\1\172\1"+
		"\145\1\172\1\145\2\172\1\uffff\1\147\1\172\1\157\1\uffff\2\172\1\151\1"+
		"\163\1\uffff\2\172\2\164\2\uffff\1\162\1\uffff\1\145\2\172\1\144\1\uffff"+
		"\1\172\1\uffff\1\172\1\154\2\uffff\1\172\1\162\1\uffff\1\156\2\uffff\2"+
		"\172\2\uffff\2\172\1\145\1\172\2\uffff\1\172\2\uffff\1\145\1\uffff\1\141"+
		"\1\172\4\uffff\1\172\2\uffff\1\172\1\155\3\uffff\1\155\1\145\1\172\1\uffff";
	static final String DFA8_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\11\1\uffff\1\15\20\uffff\1"+
		"\57\1\60\1\61\1\62\1\63\1\64\1\10\1\7\1\13\1\14\1\12\1\17\1\16\40\uffff"+
		"\1\32\11\uffff\1\46\3\uffff\1\52\4\uffff\1\21\4\uffff\1\26\1\27\1\uffff"+
		"\1\31\4\uffff\1\37\1\uffff\1\41\2\uffff\1\43\1\45\2\uffff\1\51\1\uffff"+
		"\1\54\1\55\2\uffff\1\22\1\23\4\uffff\1\34\1\35\1\uffff\1\40\1\42\1\uffff"+
		"\1\47\2\uffff\1\56\1\20\1\24\1\25\1\uffff\1\33\1\36\2\uffff\1\53\1\30"+
		"\1\44\3\uffff\1\50";
	static final String DFA8_specialS =
		"\u0096\uffff}>";
	static final String[] DFA8_transitionS = {
			"\1\35\1\36\2\uffff\1\35\22\uffff\1\35\2\uffff\1\37\4\uffff\1\1\1\2\1"+
			"\3\1\4\1\5\1\6\1\uffff\1\40\12\34\1\7\1\10\1\11\1\12\1\13\2\uffff\32"+
			"\33\6\uffff\1\14\1\15\1\16\1\17\1\20\1\21\5\33\1\22\1\23\1\24\1\25\1"+
			"\26\1\33\1\27\1\30\1\31\1\33\1\32\4\33\1\37",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\41",
			"",
			"\1\43\1\44",
			"",
			"\1\46",
			"\1\50\10\uffff\1\51",
			"\1\52",
			"\1\53",
			"\1\54\3\uffff\1\55",
			"\1\56\12\uffff\1\57\5\uffff\1\60",
			"\1\61\1\uffff\1\62\5\uffff\1\63\11\uffff\1\64",
			"\1\65",
			"\1\66",
			"\1\67",
			"\1\70",
			"\1\71",
			"\1\72",
			"\1\73",
			"\1\74",
			"\1\75\20\uffff\1\76",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103\3\uffff\1\104",
			"\1\105",
			"\1\106",
			"\1\107",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\111\13\uffff\1\112",
			"\1\113",
			"\1\114\11\uffff\1\115",
			"\1\116",
			"\1\117",
			"\1\120",
			"\1\121",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\123",
			"\1\124",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\15\33\1\125\14\33",
			"\1\127",
			"\1\130",
			"\1\131",
			"\1\132",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\134",
			"\1\135\16\uffff\1\136",
			"\1\137",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\142",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"",
			"\1\144\1\uffff\1\145",
			"\1\146",
			"\1\147",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\151",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\153",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\24\33\1\154\5\33",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"",
			"\1\157\3\uffff\1\160",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\162",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\165",
			"\1\166",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\171",
			"\1\172",
			"",
			"",
			"\1\173",
			"",
			"\1\174",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\177",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\u0082",
			"",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\u0084",
			"",
			"\1\u0085",
			"",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\u008a",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"",
			"",
			"\1\u008d",
			"",
			"\1\u008e",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"",
			"",
			"",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"",
			"",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			"\1\u0092",
			"",
			"",
			"",
			"\1\u0093",
			"\1\u0094",
			"\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
			""
	};

	static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
	static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
	static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
	static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
	static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
	static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
	static final short[][] DFA8_transition;

	static {
		int numStates = DFA8_transitionS.length;
		DFA8_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
		}
	}

	protected class DFA8 extends DFA {

		public DFA8(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 8;
			this.eot = DFA8_eot;
			this.eof = DFA8_eof;
			this.min = DFA8_min;
			this.max = DFA8_max;
			this.accept = DFA8_accept;
			this.special = DFA8_special;
			this.transition = DFA8_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | ID | INT | WS | RC | COMMENT | ML_COMMENT );";
		}
	}

}
