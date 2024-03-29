package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exce��o lan�ada quando a classe que est� sendo declarada, j� o foi
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
		super("Classe " + id + " j� declarada.");
	}
}
