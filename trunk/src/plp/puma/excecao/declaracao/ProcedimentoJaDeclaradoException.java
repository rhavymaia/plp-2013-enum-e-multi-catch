package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exce��o lan�ada quando um procedimento est� sendo declarado novamente.
 */
public class ProcedimentoJaDeclaradoException extends Exception {
	/**
	 * Construtor
	 * 
	 * @param id
	 *            Identificador representando um procedimento.
	 */
	public ProcedimentoJaDeclaradoException(Id id) {
		super("Procedimento " + id + " j� declarado.");
	}
}
