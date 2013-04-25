/* Generated By:JavaCC: Do not edit this line. Exp1Parser.java */
package plp.expressions1.parser;

import plp.expressions1.Programa;
import plp.expressions1.expression.ExpAnd;
import plp.expressions1.expression.ExpAscii;
import plp.expressions1.expression.ExpConcat;
import plp.expressions1.expression.ExpEquals;
import plp.expressions1.expression.ExpLength;
import plp.expressions1.expression.ExpMenos;
import plp.expressions1.expression.ExpMult;
import plp.expressions1.expression.ExpNot;
import plp.expressions1.expression.ExpOr;
import plp.expressions1.expression.ExpSoma;
import plp.expressions1.expression.ExpSub;
import plp.expressions1.expression.Expressao;
import plp.expressions1.expression.Valor;
import plp.expressions1.expression.ValorBooleano;
import plp.expressions1.expression.ValorCharacter;
import plp.expressions1.expression.ValorInteiro;
import plp.expressions1.expression.ValorReal;
import plp.expressions1.expression.ValorString;

public class Exp1Parser implements Exp1ParserConstants {

	public static void main(String args[]) {
		Exp1Parser parser;
		if (args.length == 0) {
			System.out
					.println("Expressoes 1 PLP Parser Version 0.0.1:  Reading from standard input . . .");
			parser = new Exp1Parser(System.in);
		} else if (args.length == 1) {
			System.out
					.println("Expressoes 1 PLP Parser Version 0.0.1:  Reading from file "
							+ args[0] + " . . .");
			try {
				parser = new Exp1Parser(new java.io.FileInputStream(args[0]));
			} catch (java.io.FileNotFoundException e) {
				System.out.println("Java Parser Version 1.0.2:  File "
						+ args[0] + " not found.");
				return;
			}
		} else {
			System.out
					.println("Expressoes 1 PLP Parser Version 0.0.1:  Usage is one of:");
			System.out.println("         java Exp1Parser < inputfile");
			System.out.println("OR");
			System.out.println("         java Exp1Parser inputfile");
			return;
		}
		try {
			Programa programa = parser.Input();
			System.out
					.println("Expressoes 1 PLP Parser Version 0.0.1:  Expressoes1 program parsed successfully.");
			if (!programa.checaTipo()) {
				System.out.println("Erro de tipo");
			} else {
				programa.executar();
			}
		} catch (ParseException e) {
			System.out
					.println("Expressoes 1 PLP Parser Version 0.0.1:  Encountered errors during parse.");
		}
	}

	static final public Programa Input() throws ParseException {
		Programa retorno;
		retorno = PPrograma();
		jj_consume_token(0);
		{
			if (true)
				return retorno;
		}
		throw new Error("Missing return statement in function");
	}

	static final public Valor PValorInteiro() throws ParseException {
		Token token;
		token = jj_consume_token(INTEGER_LITERAL);
		{
			if (true)
				return new ValorInteiro(Integer.parseInt(token.toString()));
		}
		throw new Error("Missing return statement in function");
	}

	static final public Valor PValorReal() throws ParseException {
		Token token;
		token = jj_consume_token(REAL_LITERAL);
		{
			if (true)
				return new ValorReal(Double.parseDouble(token.toString()));
		}
		throw new Error("Missing return statement in function");
	}

	static final public Valor PValorBooleano() throws ParseException {
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case FALSE:
			jj_consume_token(FALSE);
			{
				if (true)
					return new ValorBooleano(false);
			}
			break;
		case TRUE:
			jj_consume_token(TRUE);
			{
				if (true)
					return new ValorBooleano(true);
			}
			break;
		default:
			jj_la1[0] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	static final public Valor PValorString() throws ParseException {
		Token token;
		token = jj_consume_token(STRING_LITERAL);
		String tokenStr = token.toString();
		tokenStr = tokenStr.substring(1, tokenStr.length() - 1);
		{
			if (true)
				return new ValorString(tokenStr);
		}
		throw new Error("Missing return statement in function");
	}

	static final public Valor PValorCharacter() throws ParseException {
		Token token;
		token = jj_consume_token(CHARACTER_LITERAL);
		String tokenChar = token.toString();
		{
			if (true)
				return new ValorCharacter(tokenChar.toString().charAt(1));
		}
		throw new Error("Missing return statement in function");
	}

	static final public Valor PValor() throws ParseException {
		Valor retorno;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case INTEGER_LITERAL:
			retorno = PValorInteiro();
			break;
		case REAL_LITERAL:
			retorno = PValorReal();
			break;
		case TRUE:
		case FALSE:
			retorno = PValorBooleano();
			break;
		case STRING_LITERAL:
			retorno = PValorString();
			break;
		case CHARACTER_LITERAL:
			retorno = PValorCharacter();
			break;
		default:
			jj_la1[1] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true)
				return retorno;
		}
		throw new Error("Missing return statement in function");
	}

	static final public Expressao PExpMenos() throws ParseException {
		Expressao retorno;
		jj_consume_token(MINUS);
		retorno = PExpPrimaria();
		{
			if (true)
				return new ExpMenos(retorno);
		}
		throw new Error("Missing return statement in function");
	}

	static final public Expressao PExpNot() throws ParseException {
		Expressao retorno;
		jj_consume_token(NOT);
		retorno = PExpPrimaria();
		{
			if (true)
				return new ExpNot(retorno);
		}
		throw new Error("Missing return statement in function");
	}

	static final public Expressao PExpLength() throws ParseException {
		Expressao retorno;
		jj_consume_token(LENGTH);
		retorno = PExpPrimaria();
		if (retorno instanceof ValorString) {
			ValorString val = (ValorString) retorno;
		}
		{
			if (true)
				return new ExpLength(retorno);
		}
		throw new Error("Missing return statement in function");
	}

	static final public Expressao PExpAscii() throws ParseException {
		Expressao retorno;
		jj_consume_token(ASCII);
		retorno = PExpPrimaria();
		if (retorno instanceof ValorCharacter) {
			ValorCharacter val = (ValorCharacter) retorno;
		}
		{
			if (true)
				return new ExpAscii(retorno);
		}
		throw new Error("Missing return statement in function");
	}

	static final public Expressao PExpPrimaria() throws ParseException {
		Expressao retorno;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case TRUE:
		case FALSE:
		case INTEGER_LITERAL:
		case REAL_LITERAL:
		case STRING_LITERAL:
		case CHARACTER_LITERAL:
			retorno = PValor();
			break;
		case LPAREN:
			jj_consume_token(LPAREN);
			retorno = PExpressao();
			jj_consume_token(RPAREN);
			break;
		default:
			jj_la1[2] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true)
				return retorno;
		}
		throw new Error("Missing return statement in function");
	}

	static final public Expressao PExpUnaria() throws ParseException {
		Expressao retorno;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case MINUS:
			retorno = PExpMenos();
			break;
		case NOT:
			retorno = PExpNot();
			break;
		case LENGTH:
			retorno = PExpLength();
			break;
		case TRUE:
		case FALSE:
		case INTEGER_LITERAL:
		case REAL_LITERAL:
		case STRING_LITERAL:
		case CHARACTER_LITERAL:
		case LPAREN:
			retorno = PExpPrimaria();
			break;
		case ASCII:
			retorno = PExpAscii();
			break;
		default:
			jj_la1[3] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true)
				return retorno;
		}
		throw new Error("Missing return statement in function");
	}

	static final public Expressao PExpBinaria() throws ParseException {
		Expressao retorno, param2;
		retorno = PExpUnaria();
		label_1: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case AND:
			case OR:
			case EQ:
			case CONCAT:
			case PLUS:
			case MINUS:
			case STAR:
				;
				break;
			default:
				jj_la1[4] = jj_gen;
				break label_1;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case CONCAT:
				jj_consume_token(CONCAT);
				param2 = PExpUnaria();
				retorno = new ExpConcat(retorno, param2);
				break;
			case MINUS:
				jj_consume_token(MINUS);
				param2 = PExpUnaria();
				retorno = new ExpSub(retorno, param2);
				break;
			case PLUS:
				jj_consume_token(PLUS);
				param2 = PExpUnaria();
				retorno = new ExpSoma(retorno, param2);
				break;
			case STAR:
				jj_consume_token(STAR);
				param2 = PExpUnaria();
				retorno = new ExpMult(retorno, param2);
				break;
			case AND:
				jj_consume_token(AND);
				param2 = PExpUnaria();
				retorno = new ExpAnd(retorno, param2);
				break;
			case OR:
				jj_consume_token(OR);
				param2 = PExpUnaria();
				retorno = new ExpOr(retorno, param2);
				break;
			case EQ:
				jj_consume_token(EQ);
				param2 = PExpUnaria();
				retorno = new ExpEquals(retorno, param2);
				break;
			default:
				jj_la1[5] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true)
				return retorno;
		}
		throw new Error("Missing return statement in function");
	}

	static final public Expressao PExpressao() throws ParseException {
		Expressao retorno;
		retorno = PExpBinaria();
		{
			if (true)
				return retorno;
		}
		throw new Error("Missing return statement in function");
	}

	static final public Programa PPrograma() throws ParseException {
		Expressao retorno;
		retorno = PExpressao();
		{
			if (true)
				return new Programa(retorno);
		}
		throw new Error("Missing return statement in function");
	}

	static private boolean jj_initialized_once = false;
	/** Generated Token Manager. */
	static public Exp1ParserTokenManager token_source;
	static JavaCharStream jj_input_stream;
	/** Current token. */
	static public Token token;
	/** Next token. */
	static public Token jj_nt;
	static private int jj_ntk;
	static private int jj_gen;
	static final private int[] jj_la1 = new int[6];
	static private int[] jj_la1_0;
	static private int[] jj_la1_1;
	static {
		jj_la1_init_0();
		jj_la1_init_1();
	}

	private static void jj_la1_init_0() {
		jj_la1_0 = new int[] { 0xc000, 0x63c000, 0x463c000, 0x463f800, 0x600,
				0x600, };
	}

	private static void jj_la1_init_1() {
		jj_la1_1 = new int[] { 0x0, 0x0, 0x0, 0x40000, 0xf0400, 0xf0400, };
	}

	/** Constructor with InputStream. */
	public Exp1Parser(java.io.InputStream stream) {
		this(stream, null);
	}

	/** Constructor with InputStream and supplied encoding */
	public Exp1Parser(java.io.InputStream stream, String encoding) {
		if (jj_initialized_once) {
			System.out
					.println("ERROR: Second call to constructor of static parser.  ");
			System.out
					.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		try {
			jj_input_stream = new JavaCharStream(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source = new Exp1ParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	static public void ReInit(java.io.InputStream stream) {
		ReInit(stream, null);
	}

	/** Reinitialise. */
	static public void ReInit(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream.ReInit(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Constructor. */
	public Exp1Parser(java.io.Reader stream) {
		if (jj_initialized_once) {
			System.out
					.println("ERROR: Second call to constructor of static parser. ");
			System.out
					.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		jj_input_stream = new JavaCharStream(stream, 1, 1);
		token_source = new Exp1ParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	static public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Constructor with generated Token Manager. */
	public Exp1Parser(Exp1ParserTokenManager tm) {
		if (jj_initialized_once) {
			System.out
					.println("ERROR: Second call to constructor of static parser. ");
			System.out
					.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	public void ReInit(Exp1ParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 6; i++)
			jj_la1[i] = -1;
	}

	static private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	/** Get the next Token. */
	static final public Token getNextToken() {
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	/** Get the specific Token. */
	static final public Token getToken(int index) {
		Token t = token;
		for (int i = 0; i < index; i++) {
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	static private int jj_ntk() {
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
	static private int[] jj_expentry;
	static private int jj_kind = -1;

	/** Generate ParseException. */
	static public ParseException generateParseException() {
		jj_expentries.clear();
		boolean[] la1tokens = new boolean[57];
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 6; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
					if ((jj_la1_1[i] & (1 << j)) != 0) {
						la1tokens[32 + j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 57; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.add(jj_expentry);
			}
		}
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = jj_expentries.get(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	/** Enable tracing. */
	static final public void enable_tracing() {
	}

	/** Disable tracing. */
	static final public void disable_tracing() {
	}

}
