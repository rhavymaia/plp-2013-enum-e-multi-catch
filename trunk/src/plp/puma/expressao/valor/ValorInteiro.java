package plp.puma.expressao.valor;

import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Objetos desta classe encapsulam valor inteiro.
 */
public class ValorInteiro extends ValorConcreto {

	private int valor;

	/**
	 * Cria <code>ValorInteiro</code> contendo o valor fornecido.
	 */
	public ValorInteiro(int valor) {
		this.valor = valor;
	}

	/**
	 * Retorna o valor inteiro encapsulado pelo objeto deste tipo.
	 */
	public int valor() {
		return valor;
	}

	/**
	 * Retorna o valor deste valor primitivo, i.e., ele mesmo.
	 */
	public Valor avaliar(AmbienteExecucao amb) {
		return this;
	}

	/**
	 * Determina igualdade entre objetos deste tipo
	 */
	public boolean equals(ValorConcreto obj) {
		if (obj instanceof ValorInteiro) {
			return valor == ((ValorInteiro) obj).valor();
		} else {
			return false;
		}
	}

	/**
	 * Retorna texto representando o valor inteiro do objeto desta classe.
	 */
	public String toString() {
		return String.valueOf(valor);
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se os tipos da expressao são válidos;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao amb) {
		return true;
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.TIPO_INTEIRO;
	}

	/**
	 * Retorna se um tipo valor é um tipo Referencia
	 * 
	 * @return verdadeiro se for referencia, e falso, caso contrário.
	 */
	public boolean eReferencia() {
		return false;
	}
}