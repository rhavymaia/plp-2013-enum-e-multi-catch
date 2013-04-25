package plp.puma.expressao;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.valor.Valor;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;

/**
 * Uma expressão é a unidade basica na Linguagem de Expressoes.
 */
public interface Expressao {

	/**
	 * Avalia a expressao retornando seu Valor.
	 */
	Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException;

	/**
	 * Avalia a expressao retornando seu Valor. Se o Valor for um referencia
	 * retorna o valor da referencia.
	 */
	Valor avaliarRef(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException;

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return <code>true</code> se os tipos da expressao são válidos;
	 *         <code>false</code> caso contrario.
	 */
	boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException;

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException;

}
