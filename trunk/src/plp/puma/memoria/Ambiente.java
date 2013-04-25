package plp.puma.memoria;

/**
 * Interface que representa um ambiente.
 */
public interface Ambiente {
	/**
	 * Incrementa a pilha do ambiente, passando para o próximo estado.
	 */
	public void incrementa();

	/**
	 * Restaura o estado do ambiente.
	 */
	public void restaura();

}