package plp.puma.expressao.valor;

import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Este valor primitivo encapsula um String.
 */
public class ValorString extends ValorConcreto {

	private String valor;

	/**
	 * cria um objeto encapsulando o String fornecido
	 */
	public ValorString(String valor) {
		this.valor = valor;
	}

	/**
	 * Retorna texto representando o valor string do objeto desta classe.
	 * 
	 * @return texto representando o valor string do objeto desta classe.
	 */
	public String toString() {
		return valor;
	}

	/**
	 * Determina igualdade entre objetos desta classe
	 */
	public boolean equals(ValorConcreto obj) {
		if (obj instanceof ValorString) {
			return valor.equals(((ValorString) obj).valor());
		} else {
			return false;
		}
	}

	/**
	 * Retorna o valor deste valor primitivo, i.e, ele mesmo.
	 */
	public Valor avaliar(AmbienteExecucao amb) {
		return this;
	}

	/**
	 * Retorna o string encapsulado pelo objeto desta classe
	 */
	public String valor() {
		return valor;
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
		return TipoPrimitivo.TIPO_STRING;
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
