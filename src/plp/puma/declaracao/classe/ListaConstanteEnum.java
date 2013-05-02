package plp.puma.declaracao.classe;

import java.util.ArrayList;
import java.util.List;

import plp.puma.declaracao.constante.ConstanteEnum;
import plp.puma.expressao.leftExpression.Id;

/**
 * Representa a lista de identificadores declarados para um tipo Enum
 * 
 */

public class ListaConstanteEnum {
	
	/** Armazenar constantes e suas variáveis */
	public List<ConstanteEnum> constantesEnum;

	public ListaConstanteEnum(ConstanteEnum constanteEnum) {
		this.constantesEnum = new ArrayList<ConstanteEnum>();
		this.constantesEnum.add(constanteEnum);
	}
	
	public ListaConstanteEnum(ConstanteEnum constante, ListaConstanteEnum constantes) {
		this.constantesEnum = constantes.getConstantesEnum();
		this.constantesEnum.add(constante);
	}

	/**
	 * Verifica se um dado Id existe na lista
	 * 
	 * @param id
	 *            que será verficada existência na lista
	 * @return <code>true</code> se o id está presente na lista;
	 *         <code>false</code> caso contrario.
	 */
	public boolean existeId(Id id) {
		
		for(ConstanteEnum constanteEnum: this.constantesEnum){	
			if (constanteEnum.getId().equals(id)) {
				return true;
			}
		}
		
		return false;
	}

	public List<ConstanteEnum> getConstantesEnum() {
		return constantesEnum;
	}
}
