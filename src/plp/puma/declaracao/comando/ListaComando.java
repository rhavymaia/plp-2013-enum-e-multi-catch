package plp.puma.declaracao.comando;

import java.util.ArrayList;
import java.util.List;

import plp.puma.comando.Comando;
import plp.puma.expressao.leftExpression.Id;

/**
 * Representa a lista de comandos
 * 
 */

public class ListaComando {

	/** Armazena os comandos */
	List<Comando> lista;

	public ListaComando(Comando cmd) {
		lista = new ArrayList<Comando>();
		lista.add(cmd);
	}

	public ListaComando(Comando cmd, ListaComando lista) {
		this.lista = lista.getListaComando();
		this.lista.add(cmd);
	}

	/**
	 * Retorna a lista de identificadores
	 * 
	 * @return a lista de identificadores
	 */
	public List<Comando> getListaComando() {
		return lista;
	}

	/**
	 * Verifica se um dado Comando existe na lista
	 * 
	 * @param cmd
	 *            que será verficada existência na lista
	 * @return <code>true</code> se o comando está presente na lista;
	 *         <code>false</code> caso contrario.
	 */
	public boolean existeComando(Comando cmd) {
		for (Comando aux : lista) {
			if (aux.equals(cmd))
				return true;
		}
		return false;
	}
}