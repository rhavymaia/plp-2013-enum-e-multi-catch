package plp.puma.expressao.leftExpression;

import plp.puma.expressao.Expressao;

/**
 * Representa uma expressão que fica do lado esquerdo de uma atribuição ou antes
 * de uma chamada de método.
 */
public interface LeftExpression extends Expressao {
	/**
	 * Obtém o identificador dessa expressao.
	 * 
	 * @return o identificador dessa expressao. No caso de um acesso de
	 *         atributo, é o próprio atributo acessado. No caso de um Id, é ele
	 *         mesmo.
	 */
	abstract public Id getId();

}