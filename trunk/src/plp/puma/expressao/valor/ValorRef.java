package plp.puma.expressao.valor;

import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Classe que representa a refer�ncia a um objeto.
 */
public class ValorRef implements Valor {
	/**
	 * Primeira referencia valida na memoria
	 */
	public static final int VALOR_INICIAL = 0;
	/**
	 * Valor que representa a refer�ncia.
	 */
	private int valor;

	/**
	 * Construtor.
	 * 
	 * @param valor
	 *            O valor que representa a refer�ncia.
	 */
	public ValorRef(int valor) {
		if (valor >= ValorRef.VALOR_INICIAL) {
			this.valor = valor;
		} else
			this.valor = VALOR_INICIAL;
	}

	/**
	 * Obt�m o valor.
	 * 
	 * @return o valor refer�ncia.
	 */
	public int valor() {
		return valor;
	}

	/**
	 * Avalia um certo valor refer�ncia.
	 * 
	 * @param ambiente
	 *            o ambiente de execu�ao
	 * @return o valor associado a uma dada refer�ncia.
	 */
	public Valor avaliar(AmbienteExecucao ambiente) {
		return this;
	}

	/**
	 * Avalia a expressao retornando seu Valor. Se o Valor for um referencia
	 * retorna o valor da referencia.
	 */
	public Valor avaliarRef(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return avaliar(ambiente);
	}

	// Os m�todos getTipo e checaTipo de ValorRef
	// n�o foram utilizados nessa linguagem.
	public int hashCode() {
		return valor;
	}

	/**
	 * Obt�m o tipo associado a este valor refer�ncia no ambiente de compila�ao.
	 * 
	 * @param amb
	 *            o ambiente de compila��o.
	 * @return o tipo deste valor refer�ncia.
	 */
	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.TIPO_INTEIRO;
	}

	/**
	 * Checa o tipo deste valor referencia no ambiente de compila��o.
	 * 
	 * @param amb
	 *            o ambiente de compila��o
	 * @return true em todos os casos.
	 */
	public boolean checaTipo(AmbienteCompilacao amb) {
		return true;
	}

	/**
	 * Compara dois valores
	 * 
	 * @param val
	 *            o valor que vai ser comparado com este.
	 * @return true se os valores forem iguais e false, caso contr�rio.
	 */
	public boolean equals(Valor val) {
		if (val instanceof ValorRef)
			return valor == ((ValorRef) val).valor();
		else
			return false;
	}

	/**
	 * Incrementa este valor refer�ncia.
	 * 
	 * @return um novo valor refer�ncia.
	 */
	public ValorRef incrementa() {
		valor = valor + 1;
		return this;
	}

	/**
	 * Retorna se um tipo valor � um tipo Referencia
	 * 
	 * @return verdadeiro se for referencia, e falso, caso contr�rio.
	 */
	public boolean eReferencia() {
		return true;
	}

}
