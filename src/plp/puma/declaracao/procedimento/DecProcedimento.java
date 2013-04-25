package plp.puma.declaracao.procedimento;

import plp.puma.comando.Procedimento;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;

/**
 * Interface que representa uma declaração de Procedimento.
 */
public interface DecProcedimento {

	/**
	 * Retorna o procedimento a ser declarado na Declaração da Classe
	 * 
	 * @param id
	 *            o identificador da declaracao de procedimento
	 * @return o procedimento da declaração
	 */
	public Procedimento getProcedimento(Id nomeProcedimento)
			throws ProcedimentoNaoDeclaradoException;

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
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoJaDeclaradoException,
			ProcedimentoNaoDeclaradoException, ClasseNaoDeclaradaException,
			ClasseJaDeclaradaException;
}
