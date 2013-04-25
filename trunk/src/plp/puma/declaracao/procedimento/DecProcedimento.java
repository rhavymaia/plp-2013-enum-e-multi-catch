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
 * Interface que representa uma declara��o de Procedimento.
 */
public interface DecProcedimento {

	/**
	 * Retorna o procedimento a ser declarado na Declara��o da Classe
	 * 
	 * @param id
	 *            o identificador da declaracao de procedimento
	 * @return o procedimento da declara��o
	 */
	public Procedimento getProcedimento(Id nomeProcedimento)
			throws ProcedimentoNaoDeclaradoException;

	/**
	 * Verifica se a declara��o est� bem tipada, ou seja, se a express�o de
	 * inicializa��o est� bem tipada.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se os tipos da declara��o s�o v�lidos;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoJaDeclaradoException,
			ProcedimentoNaoDeclaradoException, ClasseNaoDeclaradaException,
			ClasseJaDeclaradaException;
}
