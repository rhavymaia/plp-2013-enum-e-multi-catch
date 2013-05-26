package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exce��o lan�ada quando uma constante que est� sendo referenciada n�o foi
 * declarada anteriormente.
 */
public class ConstanteEnumNaoDeclaradaException extends Exception {
	/**
	 * Construtor.
	 * 
	 * @param id
	 *            Identificador representando a vari�vel.
	 */
	public ConstanteEnumNaoDeclaradaException(Id id) {
		super("Constante " + id + " nao declarada.");
	}

}
