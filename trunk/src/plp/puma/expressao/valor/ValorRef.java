package plp.puma.expressao.valor;

import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Classe que representa a referência a um objeto.
 */
public class ValorRef implements Valor {
	/**
	 * Primeira referencia valida na memoria
	 */
	public static final int VALOR_INICIAL = 0;
	/**
	 * Valor que representa a referência.
	 */
	private int valor;

	/**
	 * Construtor.
	 * 
	 * @param valor
	 *            O valor que representa a referência.
	 */
	public ValorRef(int valor) {
		if (valor >= ValorRef.VALOR_INICIAL) {
			this.valor = valor;
		} else
			this.valor = VALOR_INICIAL;
	}

	/**
	 * Obtém o valor.
	 * 
	 * @return o valor referência.
	 */
	public int valor() {
		return valor;
	}

	/**
	 * Avalia um certo valor referência.
	 * 
	 * @param ambiente
	 *            o ambiente de execuçao
	 * @return o valor associado a uma dada referência.
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

	// Os métodos getTipo e checaTipo de ValorRef
	// não foram utilizados nessa linguagem.
	public int hashCode() {
		return valor;
	}

	/**
	 * Obtém o tipo associado a este valor referência no ambiente de compilaçao.
	 * 
	 * @param amb
	 *            o ambiente de compilação.
	 * @return o tipo deste valor referência.
	 */
	public Tipo getTipo(AmbienteCompilacao amb) {
		return TipoPrimitivo.TIPO_INTEIRO;
	}

	/**
	 * Checa o tipo deste valor referencia no ambiente de compilação.
	 * 
	 * @param amb
	 *            o ambiente de compilação
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
	 * @return true se os valores forem iguais e false, caso contrário.
	 */
	public boolean equals(Valor val) {
		if (val instanceof ValorRef)
			return valor == ((ValorRef) val).valor();
		else
			return false;
	}

	/**
	 * Incrementa este valor referência.
	 * 
	 * @return um novo valor referência.
	 */
	public ValorRef incrementa() {
		valor = valor + 1;
		return this;
	}

	/**
	 * Retorna se um tipo valor é um tipo Referencia
	 * 
	 * @return verdadeiro se for referencia, e falso, caso contrário.
	 */
	public boolean eReferencia() {
		return true;
	}

}
