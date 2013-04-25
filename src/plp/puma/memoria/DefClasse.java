package plp.puma.memoria;

import plp.puma.comando.Procedimento;
import plp.puma.declaracao.procedimento.DecProcedimento;
import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.util.Tipo;

/**
 * Uma definiçao de classe é uma declaraçao de variável e uma declaração de
 * procedimento. Ambos podem ser simples ou compostos.
 */
public class DefClasse {

	/**
	 * Declaração de variável
	 */
	private DecVariavel decVariavel;
	/**
	 * Declaraçao do Procedimento
	 */
	private DecProcedimento decProcedimento;

	/**
	 * Construtor
	 * 
	 * @param decVariavel
	 *            Declaração de Variáveis
	 * @param decProcedimento
	 *            Declaraçao dos Procedimentos.
	 */
	public DefClasse(DecVariavel decVariavel, DecProcedimento decProcedimento) {
		this.decVariavel = decVariavel;
		this.decProcedimento = decProcedimento;
	}

	/**
	 * Obtém as declaraçoes das variáveis.
	 * 
	 * @return as declaraçoes das variáveis.
	 */
	public DecVariavel getDecVariavel() {
		return decVariavel;
	}

	/**
	 * Retorna um método da classe a partir de seu identificador.
	 * 
	 * @param idMetodo
	 *            Identificador do método
	 * @return o método desejado
	 * @throws ProcedimentoNaoDeclaradoException
	 */
	public Procedimento getMetodo(Id idMetodo)
			throws ProcedimentoNaoDeclaradoException {
		return decProcedimento.getProcedimento(idMetodo);
	}

	/**
	 * O método abaixo deve verificar se existe algum atributo, identificado por
	 * idAtributo na definicao da classe
	 * 
	 * @param idAtributo
	 *            Um identificador de atributo.
	 * @param Tipo
	 *            O tipo do atributo do identificador.
	 */
	public Tipo getTipoAtributo(Id idAtributo)
			throws VariavelNaoDeclaradaException {
		return decVariavel.getTipo(idAtributo);
	}
}
