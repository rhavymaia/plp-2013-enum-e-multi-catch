package plp.puma.util;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;

/**
 * Esta classe representa o Tipo genérico usado na declaração de uma classe
 * genérica
 * 
 * Exemplo:
 * 
 * public class classeGenerica<T> {
 * 
 * }
 * 
 * T é do tipo TipoGeneric. Esse tipo é foi criado para fazer checagem de
 * declaracao do tipo genérico dentro da classe. No exemplo de código abaixo é
 * preciso ocorrer um erro de compilação pois o identificador TipoGenetico T2
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
	 *            Identificador do tipo genérico
	 */
	public TipoGeneric(Id tipoGeneric) {
		this.tipoGeneric = tipoGeneric;
	}

	/**
	 * Método de acesso do identificador
	 */
	public Id getTipo() {
		return this.tipoGeneric;
	}

	/**
	 * Método para avaliar de o identificador é válido. Simplementes esse método
	 * verifica se o identificador já foi declarado no ambiente de compilação.
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
	 * Retorna a descrição textual do tipo.
	 * 
	 * @return a descrição textual do tipo.
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
