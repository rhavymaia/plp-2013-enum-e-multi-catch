package plp.puma.memoria;

import plp.puma.declaracao.constante.DecConstanteEnum;
import plp.puma.declaracao.procedimento.DecProcedimento;
import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;

/**
 * Uma defini�ao de classe � uma declara�ao de vari�vel e uma declara��o de
 * procedimento. Ambos podem ser simples ou compostos.
 */
public class DefEnum extends DefClasse {

	/**
	 * Declara��o de Constantes e respectivas vari�veis
	 */
	private DecConstanteEnum decConstanteEnum;

	/**
	 * Construtor
	 * 
	 * @param listaId
	 *            Identificadores do Tipo enumerado
	 * @param decVariavel
	 *            Declara��o de Vari�veis
	 * @param decProcedimento
	 *            Declara�ao dos Procedimentos.
	 */
	public DefEnum(DecConstanteEnum constanteEnum, DecVariavel decVariavel,
			DecProcedimento decProcedimento) {
		super(decVariavel, decProcedimento);
		this.decConstanteEnum = constanteEnum;
	}
	

	/**
	 * O m�todo abaixo deve verificar se existe algum atributo, identificado por
	 * idAtributo na definicao da classe
	 * 
	 * @param idAtributo
	 *            Um identificador de atributo.
	 * @param Tipo
	 *            O tipo do atributo do identificador.
	 */
	public boolean existeId(Id id) throws VariavelNaoDeclaradaException {
		return decConstanteEnum.existeId(id);
	}


	public DecConstanteEnum getDecConstanteEnum() {
		return decConstanteEnum;
	}


	public void setDecConstanteEnum(DecConstanteEnum decConstanteEnum) {
		this.decConstanteEnum = decConstanteEnum;
	}
}
