package plp.expressions1.expression;

import plp.expressions1.util.Tipo;

/**
 * Objetos desta classe encapsulam valor Real.
 */

public class ValorReal extends ValorConcreto<Double> {

	public ValorReal(Double valor) {
		super(valor);
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo() {
		return Tipo.TIPO_REAL;
	}

}
