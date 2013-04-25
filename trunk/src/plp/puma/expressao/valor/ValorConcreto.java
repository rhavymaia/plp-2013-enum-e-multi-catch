package plp.puma.expressao.valor;

import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.memoria.AmbienteExecucao;

/**
 * Classe que representa um valor concreto
 * 
 */
public abstract class ValorConcreto implements Valor {
	/**
	 * Determina igualdade entre objetos do tipo <code>Valor</code>. baseando-se
	 * no conteudo armazenado em tais objetos.
	 * 
	 * @param o
	 *            Objeto com o qual eh feita comparacao.
	 */
	public abstract boolean equals(ValorConcreto valor);

	/**
	 * Avalia a expressao retornando seu Valor. Se o Valor for um referencia
	 * retorna o valor da referencia.
	 */
	public Valor avaliarRef(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return avaliar(ambiente);
	}
}
