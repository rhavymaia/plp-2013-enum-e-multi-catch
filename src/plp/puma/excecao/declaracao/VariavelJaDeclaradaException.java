package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exce��o lan�ada quando uma vari�vel est� sendo declarada mais de uma vez num
 * mesmo escopo.
 */
public class VariavelJaDeclaradaException extends Exception {
	/**
	 * Construtor.
	 * 
	 * @param id
	 *            Identificador representando uma vari�vel.
	 */
	public VariavelJaDeclaradaException(Id id) {
		super("Variavel " + id + " ja declarada.");
	}
}
