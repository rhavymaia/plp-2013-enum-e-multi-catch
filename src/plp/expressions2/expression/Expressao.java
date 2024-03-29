package plp.expressions2.expression;

import plp.expressions1.util.Tipo;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

/**
 * Uma expressao &eacute; a unidade basica na Linguagem de Expressoes.
 */
public interface Expressao {

	/**
	 * Avalia a expressao retornando seu Valor.
	 */
	Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException,
			VariavelJaDeclaradaException;

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param amb
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *         <code>false</code> caso contrario.
	 */
	boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException;

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param amb
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return os tipos possiveis desta expressao.
	 */
	Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException,
			VariavelJaDeclaradaException;

}
