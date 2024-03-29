package plp.expressions2.memory;

import java.util.HashMap;
import java.util.Stack;

import plp.expressions2.expression.Id;

/**
 * Classe abstrata que representa um contexto
 * 
 * @author eagt
 * 
 */
public abstract class Contexto<T> {
	/**
	 * A pilhaValor de blocos de contexto.
	 */
	private Stack<HashMap<Id, T>> pilha;

	/**
	 * Construtor da classe.
	 */
	public Contexto() {
		pilha = new Stack<HashMap<Id, T>>();
	}

	public void incrementa() {
		pilha.push(new HashMap<Id, T>());
	}

	public void restaura() {
		pilha.pop();
	}

	/**
	 * Mapeia o id no valor dado.
	 * 
	 * @exception VariavelJaDeclaradaException
	 *                se j� existir um mapeamento do identificador nesta tabela.
	 */
	public void map(Id idArg, T valorId) throws VariavelJaDeclaradaException {
		// try {
		HashMap<Id, T> aux = pilha.peek();
		if (aux.put(idArg, valorId) != null) {
			// throw new IdentificadorJaDeclaradoException();
			throw new VariavelJaDeclaradaException(idArg);
		}
		// }
		// catch (IdentificadorJaDeclaradoException e) {
		// throw new VariavelJaDeclaradaException (idArg);
		// }
	}

	/**
	 * Retorna o valor mapeado ao id dado.
	 * 
	 * @exception VariavelNaoDeclaradaException
	 *                se n�o existir nenhum valor mapeado ao id dado nesta
	 *                tabela.
	 */
	public T get(Id idArg) throws VariavelNaoDeclaradaException {
		try {
			T result = null;
			Stack<HashMap<Id, T>> auxStack = new Stack<HashMap<Id, T>>();
			while (result == null && !pilha.empty()) {
				HashMap<Id, T> aux = pilha.pop();
				auxStack.push(aux);
				result = (T) aux.get(idArg);
			}
			while (!auxStack.empty()) {
				pilha.push(auxStack.pop());
			}
			if (result == null) {
				throw new IdentificadorNaoDeclaradoException();
			}

			return result;
		} catch (IdentificadorNaoDeclaradoException e) {
			throw new VariavelNaoDeclaradaException(idArg);
		}
	}

	/**
	 * Returns the pilhaValor.
	 * 
	 * @return Stack
	 */
	protected Stack<HashMap<Id, T>> getPilha() {
		return pilha;
	}

	/**
	 * Sets the pilhaValor.
	 * 
	 * @param pilha
	 *            The pilhaValor to set
	 */
	protected void setPilha(Stack<HashMap<Id, T>> pilha) {
		this.pilha = pilha;
	}

}
