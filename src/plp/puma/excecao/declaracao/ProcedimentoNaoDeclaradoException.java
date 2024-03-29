package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exce�ao lan�ada quando um procedimento que est� sendo referenciado n�o foi
 * declarado.
 */
public class ProcedimentoNaoDeclaradoException extends Exception {
	/**
	 * Construtor
	 * 
	 * @param id
	 *            Identificador representando o procedimento.
	 */
	public ProcedimentoNaoDeclaradoException(Id id) {
		super("Procedimento " + id + " nao declarado.");
	}
}
