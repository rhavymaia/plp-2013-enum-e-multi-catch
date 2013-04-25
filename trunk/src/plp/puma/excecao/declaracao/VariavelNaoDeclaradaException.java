package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exceção lançada quando uma variável que está sendo referenciada não foi
 * declarada anteriormente.
 */
public class VariavelNaoDeclaradaException extends Exception {
	/**
	 * Construtor.
	 * 
	 * @param id
	 *            Identificador representando a variável.
	 */
	public VariavelNaoDeclaradaException(Id id) {
		super("Variavel " + id + " nao declarada.");
	}

}
