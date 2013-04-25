package plp.puma.expressao.leftExpression;

import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
import plp.puma.expressao.valor.Valor;
import plp.puma.memoria.AmbienteExecucao;

/**
 * Classe que representa um acesso de atributo.
 */
public abstract class AcessoAtributo implements LeftExpression {

	/**
	 * Identificador.
	 */
	private Id id;

	/**
	 * Construtor
	 * 
	 * @param id
	 *            Identificador
	 */
	public AcessoAtributo(Id id) {
		this.id = id;
	}

	/**
	 * Obtém o identificador.
	 * 
	 * @return o identificador.
	 */
	public Id getId() {
		return id;
	}

	/**
	 * Obtém uma expressao
	 * 
	 * @return uma expressão.
	 */
	public abstract Expressao getExpressaoObjeto();

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