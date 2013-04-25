package plp.puma.memoria;

/**
 * Interface que representa um ambiente.
 */
public interface Ambiente {
	/**
	 * Incrementa a pilha do ambiente, passando para o pr�ximo estado.
	 */
	public void incrementa();

	/**
	 * Restaura o estado do ambiente.
	 */
	public void restaura();

}