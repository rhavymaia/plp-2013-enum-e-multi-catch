package plp.puma.util;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;

/**
 * Interface representando um tipo.
 */
public interface Tipo {
	/**
	 * Obtém o tipo, representado por um identficador.
	 * 
	 * @return o tipo
	 */
	public Id getTipo();

	/**
	 * Compara dois tipos.
	 * 
	 * @param obj
	 *            O objeto a ser comparado com este tipo.
	 * @return true, se o obj é igual a este tipo, false, caso contrário.
	 */
	public boolean equals(Object obj);

	/**
	 * Verifica se o tipo é válido no ambiente.
	 * 
	 * @param ambiente
	 *            o ambiente de compilaçao, que apresenta o mapeamento entre
	 *            identificadores e tipos.
	 * @return true, se o tipo for válido no ambiente, false, caso contrário.
	 * @throws ClasseNaoDeclaradaException
	 */
	public boolean eValido(AmbienteCompilacao ambiente)
			throws ClasseNaoDeclaradaException;

	/**
	 * Indica se esta expressao &eacute; inteira.
	 * 
	 * @return <code>true</code> se esta expressao for inteira;
	 *         <code>false</code> caso contrario.
	 */
	public boolean eInteiro();

	/**
	 * Indica se esta expressao &eacute; booleana.
	 * 
	 * @return <code>true</code> se esta expressao for booleana;
	 *         <code>false</code> caso contrario.
	 */
	public boolean eString();

	/**
	 * Indica se esta expressao &eacute; string.
	 * 
	 * @return <code>true</code> se esta expressao for string;
	 *         <code>false</code> caso contrario.
	 */
	public boolean eBooleano();

}
