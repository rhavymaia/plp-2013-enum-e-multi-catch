package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exceçao lançada quando um procedimento que está sendo referenciado não foi
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
