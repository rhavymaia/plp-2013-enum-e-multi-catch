package plp.puma.declaracao.variavel;

import plp.puma.declaracao.Declaracao;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;

/**
 * Interface representando uma declaração de variável.
 */
public interface DecVariavel extends Declaracao {

	/**
	 * Retorna o tipo do identificador a ser declarado no AmbienteCompilacao
	 * 
	 * @param id
	 *            o identificador da declaracao
	 * @return o tipo do identificador
	 */
	public Tipo getTipo(Id id) throws VariavelNaoDeclaradaException;

	/**
	 * Cria um mapeamento do identificador para o valor da expressão desta
	 * declaração no AmbienteExecucao
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela inicialização da variável.
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ObjetoNaoDeclaradoException, ObjetoJaDeclaradoException,
			TryCatchException;

	/**
	 * Verifica se a declaração está bem tipada, ou seja, se a expressão de
	 * inicialização está bem tipada.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se os tipos da declaração são válidos;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException;
}
