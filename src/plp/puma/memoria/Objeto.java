package plp.puma.memoria;

import plp.puma.expressao.leftExpression.Id;

/**
 * Conjunto formado pelo nome da classe de um objeto e o seu estado representado
 * pelo ambiente de execu��o.
 */
public class Objeto {
	/**
	 * Identificador da classe a que pertence o objeto.
	 */
	private Id classeObjeto;

	/**
	 * Estado do objeto no ambiente de execu��o.
	 */
	private AmbienteExecucao estado;

	/**
	 * Construtor.
	 * 
	 * @param classeObjeto
	 *            Classe a que pertence este objeto.
	 * @param estado
	 *            Estado do objeto no ambiente de execu��o.
	 */
	public Objeto(Id classeObjeto, AmbienteExecucao estado) {
		this.classeObjeto = classeObjeto;
		this.estado = estado;
	}

	/**
	 * Obtem o identificador da classe do objeto.
	 * 
	 * @return o identificador da classe do objeto.
	 */
	public Id getClasse() {
		return classeObjeto;
	}

	/**
	 * Obt�m o atual estado do objeto, conforme o ambiente de execu��o.
	 * 
	 * @return o atual estado do objeto, conforme o ambiente de execu��o.
	 */
	public AmbienteExecucao getEstado() {
		return estado;
	}

	/**
	 * Altera o ambiente de Execu��o, que representa o novo estado do objeto.
	 * 
	 * @param novoEstado
	 *            o novo estado do objeto.
	 */
	public void setEstado(AmbienteExecucao novoEstado) {
		this.estado = novoEstado;
	}

}
