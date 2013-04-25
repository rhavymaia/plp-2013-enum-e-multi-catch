package plp.puma.memoria;

import plp.puma.declaracao.classe.ListaId;
import plp.puma.declaracao.procedimento.DecProcedimento;
import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;

/**
 * Uma definiçao de classe é uma declaraçao de variável e uma declaração de
 * procedimento. Ambos podem ser simples ou compostos.
 */
public class DefEnum extends DefClasse {

	/**
	 * Declaração de Identificadores
	 */
	private ListaId listaId;

	/**
	 * Construtor
	 * 
	 * @param listaId
	 *            Identificadores do Tipo enumerado
	 * @param decVariavel
	 *            Declaração de Variáveis
	 * @param decProcedimento
	 *            Declaraçao dos Procedimentos.
	 */
	public DefEnum(ListaId listaId, DecVariavel decVariavel,
			DecProcedimento decProcedimento) {
		super(decVariavel, decProcedimento);
		this.listaId = listaId;
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
	public boolean existeId(Id id) throws VariavelNaoDeclaradaException {
		return listaId.existeId(id);
	}
}
