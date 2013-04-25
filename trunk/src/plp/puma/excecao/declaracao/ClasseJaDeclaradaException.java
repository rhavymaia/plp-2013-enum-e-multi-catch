package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exceção lançada quando a classe que está sendo declarada, já o foi
 * anteriormente.
 */
public class ClasseJaDeclaradaException extends Exception {
	/**
	 * Construtor
	 * 
	 * @param id
	 *            Identificador representando a classe.
	 */
	public ClasseJaDeclaradaException(Id id) {
		super("Classe " + id + " já declarada.");
	}
}
