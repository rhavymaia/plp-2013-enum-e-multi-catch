package plp.puma.util;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;

/**
 * Esta classe representa o Tipo gen�rico usado na declara��o de uma classe
 * gen�rica
 * 
 * Exemplo:
 * 
 * public class classeGenerica<T> {
 * 
 * }
 * 
 * T � do tipo TipoGeneric. Esse tipo � foi criado para fazer checagem de
 * declaracao do tipo gen�rico dentro da classe. No exemplo de c�digo abaixo �
 * preciso ocorrer um erro de compila��o pois o identificador TipoGenetico T2
 * nao foi declarado.
 * 
 * public class classeGenerica<T> { ArrayList<T2> a; }
 * 
 * 
 * @author Heitor Vital
 * 
 */
public class TipoGeneric implements Tipo {

	/**
	 * Define o tipo generico da classe
	 */
	private Id tipoGeneric;

	/**
	 * Construtor
	 * 
	 * @param tipoGeneric
	 *            Identificador do tipo gen�rico
	 */
	public TipoGeneric(Id tipoGeneric) {
		this.tipoGeneric = tipoGeneric;
	}

	/**
	 * M�todo de acesso do identificador
	 */
	public Id getTipo() {
		return this.tipoGeneric;
	}

	/**
	 * M�todo para avaliar de o identificador � v�lido. Simplementes esse m�todo
	 * verifica se o identificador j� foi declarado no ambiente de compila��o.
	 */
	public boolean eValido(AmbienteCompilacao ambiente)
			throws ClasseNaoDeclaradaException {
		return (ambiente.getGeneric(this.tipoGeneric) != null);
	}

	/**
	 * Compara este tipo com o tipo dado.
	 * 
	 * @return <code>true</code> se se tratarem do mesmo tipo;
	 *         <code>false</code> caso contrario.
	 */
	public boolean equals(Object obj) {
		boolean respota = false;
		if (obj instanceof TipoGeneric) {

			TipoGeneric tg = (TipoGeneric) obj;
			respota = tg.tipoGeneric.equals(this.tipoGeneric);
		}
		return respota;
	}

	/**
	 * Retorna a descri��o textual do tipo.
	 * 
	 * @return a descri��o textual do tipo.
	 */
	public String toString() {
		return "<" + this.tipoGeneric + ">";
	}

	public boolean eBooleano() {
		return false;
	}

	public boolean eString() {
		return false;
	}

	public boolean eInteiro() {
		return false;
	}
}
