package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exceção lançada quando um procedimento está sendo declarado novamente.
 */
public class ProcedimentoJaDeclaradoException extends Exception {
	/**
	 * Construtor
	 * 
	 * @param id
	 *            Identificador representando um procedimento.
	 */
	public ProcedimentoJaDeclaradoException(Id id) {
		super("Procedimento " + id + " já declarado.");
	}
}
