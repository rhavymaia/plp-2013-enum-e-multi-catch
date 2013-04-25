package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exceção lançada quando uma variável está sendo declarada mais de uma vez num
 * mesmo escopo.
 */
public class VariavelJaDeclaradaException extends Exception {
	/**
	 * Construtor.
	 * 
	 * @param id
	 *            Identificador representando uma variável.
	 */
	public VariavelJaDeclaradaException(Id id) {
		super("Variavel " + id + " ja declarada.");
	}
}
