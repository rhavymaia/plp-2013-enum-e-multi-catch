package plp.expressions1.expression;

import plp.expressions1.util.Tipo;

/**
 * Um objeto desta classe representa uma Expressao de Multiplicação.
 */
public class ExpMult extends ExpBinaria {

	/**
	 * Controi uma Expressao de Soma com as sub-expressoes especificadas.
	 * Assume-se que estas sub-expressoes resultam em <code>ValorInteiro</code>
	 * quando avaliadas.
	 * 
	 * @param esq
	 *            Expressao da esquerda
	 * @param dir
	 *            Expressao da direita
	 */
	public ExpMult(Expressao esq, Expressao dir) {
		super(esq, dir, "*");
	}

	/**
	 * Retorna o valor da Expressao de Soma
	 */
	public Valor avaliar() {
		if (getEsq().getTipo().eReal() || getDir().getTipo().eReal())
			return new ValorReal(((ValorReal) getEsq().avaliar()).valor()
					* ((ValorReal) getDir().avaliar()).valor());
		else
			return new ValorInteiro(((ValorInteiro) getEsq().avaliar()).valor()
					* ((ValorInteiro) getDir().avaliar()).valor());
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *         <code>false</code> caso contrario.
	 */
	protected boolean checaTipoElementoTerminal() {
		return (getEsq().getTipo().eInteiro() && getEsq().getTipo().eInteiro())
				|| (getDir().getTipo().eReal() && getDir().getTipo().eReal());
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo() {
		return (getEsq().getTipo().eReal() && getEsq().getTipo().eReal()) ? Tipo.TIPO_REAL
				: Tipo.TIPO_INTEIRO;
	}

}
