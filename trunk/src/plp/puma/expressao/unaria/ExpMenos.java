package plp.puma.expressao.unaria;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorInteiro;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma expressao de menos unario.
 */
public class ExpMenos extends ExpUnaria {

	/**
	 * Controi uma expressao de menos unario com expressao especificada
	 * 
	 * @param exp
	 *            expressao cuja avaliacao resulta <code>ValorInteiro</code>.
	 */
	public ExpMenos(Expressao expressao) {
		super(expressao, "-");
	}

	/**
	 * Retorna o valor da expressao de menos unario.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * @return o valor da expressao avaliada.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		return obterValorNegativo(ambiente);
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
		if (super.checaTipo(ambiente)
				&& (getExp().getTipo(ambiente)).eInteiro()) {
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
		return TipoPrimitivo.TIPO_INTEIRO;
	}

	/**
	 * Retorna o valor negativo de uma expressão
	 * 
	 * @param ambiente
	 *            é o Ambiente de Execução
	 * @return o valor negativo da expressão inteira
	 */
	private ValorInteiro obterValorNegativo(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		return new ValorInteiro(
				-((ValorInteiro) getExp().avaliar(ambiente)).valor());
	}
}
