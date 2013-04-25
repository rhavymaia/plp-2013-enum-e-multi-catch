package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exceção lançada quando uma classe que está sendo referenciada não foi
 * declarada anteriormente.
 */
public class ClasseNaoDeclaradaException extends Exception {
	/**
	 * Construtor
	 * 
	 * @param id
	 *            Identificador representando a classe.
	 */
	public ClasseNaoDeclaradaException(Id id) {
		super("Classe " + id + " não declarada.");
	}
}