package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exce��o lan�ada quando uma vari�vel que est� sendo referenciada n�o foi
 * declarada anteriormente.
 */
public class VariavelNaoDeclaradaException extends Exception {
	/**
	 * Construtor.
	 * 
	 * @param id
	 *            Identificador representando a vari�vel.
	 */
	public VariavelNaoDeclaradaException(Id id) {
		super("Variavel " + id + " nao declarada.");
	}

}
