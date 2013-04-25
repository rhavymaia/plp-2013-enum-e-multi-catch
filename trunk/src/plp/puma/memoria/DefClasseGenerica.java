package plp.puma.memoria;

import plp.puma.declaracao.procedimento.DecProcedimento;
import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.util.Tipo;

/**
 * Uma definiçao de classe é uma declaraçao de variável e uma declaração de
 * procedimento. Ambos podem ser simples ou compostos.
 */
public class DefClasseGenerica extends DefClasse {

	private Tipo tipoGeneric;

	/**
	 * Construtor
	 * 
	 * @param decVariavel
	 *            Declaração de Variáveis
	 * @param decProcedimento
	 *            Declaraçao dos Procedimentos.
	 * @param tipoGeneric
	 *            Tipo generic
	 */
	public DefClasseGenerica(DecVariavel decVariavel,
			DecProcedimento decProcedimento, Tipo tipoGeneric) {
		super(decVariavel, decProcedimento);
		this.tipoGeneric = tipoGeneric;
	}

	/**
	 * Return Tipo Generic
	 * 
	 * @return tipoGeneric
	 */
	public Tipo getTipoGeneric() {
		return tipoGeneric;
	}
}
