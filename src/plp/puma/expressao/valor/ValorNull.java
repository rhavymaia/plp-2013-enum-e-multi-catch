package plp.puma.expressao.valor;

import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasse;

/**
 * Este valor primitivo encapsula um valor do tipo Null.
 */
public class ValorNull extends ValorConcreto {

	/**
	 * Retorna texto representando o valor string do objeto desta classe.
	 * 
	 * @return texto representando o valor string do objeto desta classe.
	 */
	public String toString() {
		return "null";
	}

	/**
	 * Determina igualdade entre objetos desta classe
	 */
	public boolean equals(ValorConcreto v) {
		if (v instanceof ValorNull)
			return true;
		else
			return false;
	}

	/**
	 * Retorna ele mesmo.
	 */
	public Valor avaliar(AmbienteExecucao amb) {
		return this;
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao s�o v�lidos;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao amb) {
		return true;
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoClasse.TIPO_NULL;
	}

	/**
	 * Retorna se um tipo da expressao � uma classe Pr�-Definida
	 * 
	 * @return verdadeiro se for pr�-definida, e falso, caso contr�rio.
	 */
	public boolean eTipoClasse() {
		return false;
	}

	/**
	 * Retorna se um tipo da expressao � Primitvo
	 * 
	 * @return verdadeiro se for Primitvo, e falso, caso contr�rio.
	 */
	public boolean eTipoPrimitivo() {
		return true;
	}

	/**
	 * Retorna se um tipo valor � um tipo Referencia
	 * 
	 * @return verdadeiro se for referencia, e falso, caso contr�rio.
	 */
	public boolean eReferencia() {
		return false;
	}

}