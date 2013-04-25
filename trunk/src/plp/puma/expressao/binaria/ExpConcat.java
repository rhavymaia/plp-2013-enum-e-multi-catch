package plp.puma.expressao.binaria;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorString;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma expressao de Concatenacao entre objetos
 * <code>ValorString</code>
 */
public class ExpConcat extends ExpBinaria {

	/**
	 * Controi uma expressao de Concatenacao com as sub-expressoes
	 * especificadas. Estas sub-expressoes devem ser tais que a avaliacao das
	 * mesmas resulta em <code>ValorString</code>
	 * 
	 * @param esq
	 *            expressao da esquerda
	 * @param dir
	 *            expressao da direita
	 */
	public ExpConcat(Expressao esq, Expressao dir) {
		super(esq, dir, "++");
	}

	/**
	 * Retorna o valor da expressao de Concatenacao
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return obterResultadoDaConcatenacao(ambiente);
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se os tipos da expressao são válidos;
	 *         <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
		boolean result;
		if (super.checaTipo(ambiente) && (getEsq().getTipo(ambiente)).eString()
				|| // we changed && to ||
				(getDir().getTipo(ambiente)).eString()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente) {
		return TipoPrimitivo.TIPO_STRING;
	}

	/**
	 * Retorna o valor inteiro que representa o resultado da concatenacao de
	 * dois Strings
	 * 
	 * @param ambiente
	 *            é o Ambiente de Execução
	 * @return o valor inteiro que representa o resultado da concatenacao de
	 *         dois Strings
	 */
	private ValorString obterResultadoDaConcatenacao(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		return new ValorString(
				(((Valor) getEsq().avaliarRef(ambiente))).toString()
						+ (((Valor) getDir().avaliarRef(ambiente))).toString());
	}
}