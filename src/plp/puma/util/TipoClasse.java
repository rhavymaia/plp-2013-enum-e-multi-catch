package plp.puma.util;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;

/**
 * Classe que representa os possiveis tipos de uma expressao.
 */
public class TipoClasse implements Tipo {

	/** Indica que a expressao associada &eacute; uma classe. */
	private Id tipoClasse;

	/** Indica que a expressao associada &eacute; nula. */
	public static final Id NULL = new Id("NULL");
	/** Indica que a expressao é um objeto String */
	public static final Id STRING = new Id("String");
	/** Indica que a expressao é um objeto Integer */
	public static final Id INTEGER = new Id("Integer");
	/** Indica que a expressao é um objeto Boolean */
	public static final Id BOOLEANO = new Id("Boolean");
	/** Indica que a expressao é um objeto Enum */
	public static final Id ENUM = new Id("enum");

	/** Constante de tipo nulo. */
	public static final Tipo TIPO_NULL = new TipoClasse(NULL);
	/** Constante de tipo String. */
	public static final Tipo TIPO_STRING = new TipoClasse(STRING);
	/** Constante de tipo Inteiro. */
	public static final Tipo TIPO_INTEGER = new TipoClasse(INTEGER);
	/** Constante de tipo Booleano. */
	public static final Tipo TIPO_BOOLEANO = new TipoClasse(BOOLEANO);
	/** Constante de tipo Enum. */
	public static final Tipo TIPO_ENUM = new TipoClasse(ENUM);

	/**
	 * Construtor da classe.
	 * 
	 * @param tipo
	 *            o tipo da expressao associada.
	 */

	public TipoClasse(Id tipoClasse) {
		this.tipoClasse = tipoClasse;
	}

	/**
	 * Na verificacao de tipos o tipo parametro na declaracao do objeto generic
	 * é criado como tipo generica. Para verificarmos a tipagem na chamada de
	 * predimentos temos que criar um tipo classe a partir do tipo generic.
	 * 
	 * @param generic
	 */
	public TipoClasse(TipoGeneric generic) {
		this.tipoClasse = generic.getTipo();
	}

	/**
	 * Retorna o tipo da expressao associada.
	 * 
	 * @return o tipo da expressao associada.
	 */
	public Id getTipo() {
		return tipoClasse;
	}

	/**
	 * Indica se esta classe &eacute; um tipo válido, ou seja, se já tiver sido
	 * declarada.
	 * 
	 * @return <code>true</code> se esta classe for um tipo válido (já declarada
	 *         ou tipo pré-definido); <code>false</code> caso contrario.
	 */

	public boolean eValido(AmbienteCompilacao ambiente)
			throws ClasseNaoDeclaradaException {
		boolean resposta = false;
		try {
			resposta = tipoClasse.equals(NULL)
					|| (ambiente.getDefClasse(tipoClasse) != null);
		} catch (ClasseNaoDeclaradaException c) {
			resposta = false;
		}

		return resposta;
	}

	/**
	 * Retorna a descrição textual do tipo.
	 * 
	 * @return a descrição textual do tipo.
	 */
	public String toString() {
		return tipoClasse.toString();
	}

	/**
	 * Indica se esta expressao &eacute; inteira.
	 * 
	 * @return <code>true</code> se esta expressao for inteira;
	 *         <code>false</code> caso contrario.
	 */
	public boolean eInteiro() {
		return tipoClasse.equals(INTEGER);
	}

	/**
	 * Indica se esta expressao &eacute; booleana.
	 * 
	 * @return <code>true</code> se esta expressao for booleana;
	 *         <code>false</code> caso contrario.
	 */
	public boolean eBooleano() {
		return tipoClasse.equals(BOOLEANO);
	}

	/**
	 * Indica se esta expressao &eacute; string.
	 * 
	 * @return <code>true</code> se esta expressao for string;
	 *         <code>false</code> caso contrario.
	 */
	public boolean eString() {
		return tipoClasse.equals(STRING);
	}

	/**
	 * Verifica se o tipo é equivalente a um dado Tipo
	 * 
	 * @param tipo
	 *            que será comparado
	 * @return true, se o tipo for equivalente, false, caso contrário.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof TipoClasse)
			return tipoClasse.equals(((TipoClasse) obj).getTipo());
		else if (obj instanceof TipoPrimitivo) {
			boolean resposta = false;
			if (tipoClasse.equals(INTEGER) && ((TipoPrimitivo) obj).eInteiro())
				resposta = true;
			else if (tipoClasse.equals(BOOLEANO)
					&& ((TipoPrimitivo) obj).eBooleano())
				resposta = true;
			else if (tipoClasse.equals(STRING)
					&& ((TipoPrimitivo) obj).eString())
				resposta = true;
			return resposta;
		}
		return false;
	}
}
