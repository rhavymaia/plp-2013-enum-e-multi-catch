package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exceção lançada quando uma constante que está sendo referenciada não foi
 * declarada anteriormente.
 */
public class ConstanteEnumNaoDeclaradaException extends Exception {
	/**
	 * Construtor.
	 * 
	 * @param id
	 *            Identificador representando a variável.
	 */
	public ConstanteEnumNaoDeclaradaException(Id id) {
		super("Constante " + id + " nao declarada.");
	}

}
