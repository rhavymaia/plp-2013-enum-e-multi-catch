package plp.puma.expressao.valor;

import plp.imperative1.util.Lista;
import plp.puma.declaracao.variavel.SimplesDecVariavel;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.DefEnum;
import plp.puma.memoria.Objeto;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasse;

/**
 * Este valor primitivo encapsula um String.
 */
public class ValorEnum extends ValorConcreto {

	// Constante
	private Id valor;
	
	// Lista de vari�veis das constantes
	private Lista<SimplesDecVariavel> variaveis;
	
	// Comandos
	private Objeto objeto;

	/**
	 * cria um objeto encapsulando o String fornecido
	 */
	public ValorEnum(Id valor, Objeto objeto) {
		this.valor = valor;
		this.objeto = objeto;
	}

	/**
	 * Retorna texto representando o valor string do objeto desta classe.
	 * 
	 * @return texto representando o valor string do objeto desta classe.
	 */
	public String toString() {
		return valor.toString();
	}

	/**
	 * Determina igualdade entre objetos desta classe
	 */
	public boolean equals(ValorConcreto obj) {
		if (obj instanceof ValorEnum) {
			return objeto.getClasse().equals(
					((ValorEnum) obj).getObjeto().getClasse())
					&& valor.equals(((ValorEnum) obj).valor());
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
	public Id valor() {
		return valor;
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
		try {
			// Retorna a defini��o do tipo enumerado
			DefEnum defEnum = (DefEnum) amb.getDefClasse(objeto.getClasse());
			// Verifica se o valor foi definido na lista de identificadores
			return defEnum.existeId(valor);
		} catch (ClasseNaoDeclaradaException clas) {
			return false;
		} catch (VariavelNaoDeclaradaException vne) {
			return false;
		}
	}

	/**
	 * Verifica em tempo de execu��o se um determinado Id existe na defini��o do
	 * tipo Enum.
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @param valor
	 *            o identificador que ser� buscado
	 * @return <code>true</code> se o identificador existe; <code>false</code>
	 *         caso contrario.
	 */
	public boolean existeId(AmbienteExecucao amb, Id valor) {
		try {
			// Retorna a defini��o do tipo enumerado
			DefEnum defEnum = (DefEnum) amb.getDefClasse(objeto.getClasse());
			// Verifica se o valor foi definido na lista de identificadores
			return defEnum.existeId(valor);
		} catch (ClasseNaoDeclaradaException clas) {
			return false;
		} catch (VariavelNaoDeclaradaException vne) {
			return false;
		}
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoClasse.TIPO_ENUM;
	}

	/**
	 * Retorna se um tipo valor � um tipo Referencia
	 * 
	 * @return verdadeiro se for referencia, e falso, caso contr�rio.
	 */
	public boolean eReferencia() {
		return false;
	}

	/**
	 * Retorna o objeto
	 * 
	 * @return
	 */
	public Objeto getObjeto() {
		return objeto;
	}

	/**
	 * Modifica o objeto
	 * 
	 * @return
	 */
	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	/**
	 * Retorna o id atual da lista dos tipos enumerados
	 * 
	 * @return
	 */
	public Id getValor() {
		return valor;
	}

	public void setValor(Id valor) {
		this.valor = valor;
	}
}
