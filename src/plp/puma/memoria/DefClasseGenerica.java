package plp.puma.memoria;

import plp.puma.declaracao.procedimento.DecProcedimento;
import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.util.Tipo;

/**
 * Uma defini�ao de classe � uma declara�ao de vari�vel e uma declara��o de
 * procedimento. Ambos podem ser simples ou compostos.
 */
public class DefClasseGenerica extends DefClasse {

	private Tipo tipoGeneric;

	/**
	 * Construtor
	 * 
	 * @param decVariavel
	 *            Declara��o de Vari�veis
	 * @param decProcedimento
	 *            Declara�ao dos Procedimentos.
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
