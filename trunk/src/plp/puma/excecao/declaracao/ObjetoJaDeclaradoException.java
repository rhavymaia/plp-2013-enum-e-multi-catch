package plp.puma.excecao.declaracao;

import plp.puma.expressao.leftExpression.Id;

/**
 * Exceção lançada qunado o objeto que está sendo declarado já o foi
 * anteriormente.
 */
public class ObjetoJaDeclaradoException extends Exception {
	/**
	 * Construtor
	 * 
	 * @param id
	 *            Identificador representando o objeto.
	 */
	public ObjetoJaDeclaradoException(Id id) {
		super("Objeto" + id + " já declarado.");
	}

}
