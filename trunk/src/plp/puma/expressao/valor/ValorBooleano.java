package plp.puma.expressao.valor;

import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Este valor primitivo encapsula um valor booleano.
 */
public class ValorBooleano extends ValorConcreto {

	private boolean valor;

	/**
	 * Cria um objeto encapsulando o valor booleano fornecido.
	 */
	public ValorBooleano(boolean valor) {
		this.valor = valor;
	}

	/**
	 * Retorna o valor deste valor primitivo, i.e, ele mesmo.
	 */
	public Valor avaliar(AmbienteExecucao amb) {
		return this;
	}

	/**
	 * Retorna o valor booleano encapsulado pelo objeto do tipo
	 * <code>ValorBooleano</code>
	 */
	public boolean valor() {
		return valor;
	}

	/**
	 * Determina igualdade entre valores do tipo <code>ValorBooleano</code>
	 */
	public boolean equals(ValorConcreto obj) {
		if (obj instanceof ValorBooleano) {
			return valor == ((ValorBooleano) obj).valor();
		} else {
			return false;
		}
	}

	/**
	 * Retorna texto representando o valor booleano
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
		return TipoPrimitivo.TIPO_BOOLEANO;
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