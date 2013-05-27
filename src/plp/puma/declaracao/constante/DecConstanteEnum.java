package plp.puma.declaracao.constante;

import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.excecao.declaracao.ConstanteEnumNaoDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;

public interface DecConstanteEnum extends DecVariavel {

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean existeId(Id id);

	/**
	 * 
	 * @param idConstante
	 * @return
	 */
	public SimplesDecConstanteEnum getConstanteEnum(Id idConstante)
			throws VariavelNaoDeclaradaException;

}
