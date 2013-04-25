package plp.puma.declaracao.classe;

import java.util.ArrayList;
import java.util.List;

import plp.puma.expressao.leftExpression.Id;

/**
 * Representa a lista de identificadores declarados para um tipo Enum
 * 
 */

public class ListaId {

	/** Armazena os identificadores */
	List<Id> lista;

	public ListaId(Id id) {
		lista = new ArrayList<Id>();
		lista.add(id);
	}

	public ListaId(Id id, ListaId lista) {
		this.lista = lista.getListaId();
		this.lista.add(id);
	}

	/**
	 * Retorna a lista de identificadores
	 * 
	 * @return a lista de identificadores
	 */
	public List<Id> getListaId() {
		return lista;
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
		for (Id aux : lista) {
			if (aux.equals(id))
				return true;
		}
		return false;
	}
}
