package plp.puma.expressao.valor;

import plp.puma.expressao.Expressao;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.util.Tipo;

/**
 * <code>Valor</code> agrupa objetos dos diferentes valores primitivos
 */
public interface Valor extends Expressao {

	/**
	 * Retorna o tipo do valor.
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return o tipo do valor.
	 */
	public abstract Tipo getTipo(AmbienteCompilacao ambiente);

	/**
	 * Retorna se um tipo valor é um tipo Referencia
	 * 
	 * @return verdadeiro se for referencia, e falso, caso contrário.
	 */
	public abstract boolean eReferencia();
}
