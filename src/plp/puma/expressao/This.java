package plp.puma.expressao;

import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.Valor;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;

/**
 * Representa uma expressao utilizando o token "this"
 */
public class This implements Expressao {

	/**
	 * Obt�m o valor do objeto this no ambiente e o avalia.
	 * 
	 * @param ambiente
	 *            o ambiente de execu�ao, que apresenta o mapeamento entre
	 *            identificadores e valores.
	 * @return o valor do objeto this no escopo do ambiente atual.
	 * @throws VariavelNaoDeclaradaException
	 *             Quando no escopo atual nao pode ser acessado o this.
	 * @throws VariavelJaDeclaradaException
	 *             Se por acaso se tentasse inserir no ambiente corrente mais de
	 *             um "this" com o mesmo escopo.
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return obterValorDoObjetoThisNoAmbiente(ambiente);
	}

	/**
	 * Avalia a expressao retornando seu Valor. Se o Valor for um referencia
	 * retorna o valor da referencia.
	 */
	public Valor avaliarRef(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return avaliar(ambiente);
	}

	/**
	 * Checa o tipo do objeto this.
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o, contendo o mapeamento entre
	 *            identificadores e tipos.
	 * @return true, se o tipo do objeto this pode ser checado sem problemas,
	 *         false, caso contr�rio.
	 * @throws VariavelNaoDeclaradaException
	 *             Se nao houver nenhum objeto this no contexto corrente.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException {
		// Como sempre h� uma classe instanciada em orientacao a objeto
		// o checaTipo() de this retorna true
		return true;
	}

	/**
	 * Obt�m o tipo do objeto this
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o, contendo o mapeamento entre
	 *            identificadoes e tipos.
	 * @return o tipo do objeto this no ambiente.
	 * @throws VariavelNaoDeclaradaException
	 *             Se nao houver nenhum objeto this no contexto atual.
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException {
		return ambiente.getTipo(new Id("this"));
	}

	/**
	 * Retorna o valor do Objeto this no ambiente
	 * 
	 * @param ambiente
	 *            � o Ambiente de Execu��o
	 * @return o valor do Objeto this no ambiente
	 */
	private Valor obterValorDoObjetoThisNoAmbiente(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return ambiente.getValor(new Id("this"));
	}
}