package plp.puma.expressao.binaria;

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
 * Um objeto desta classe representa uma expressao de Soma.
 */
public class ExpSoma extends ExpBinaria {

	/**
	 * Controi uma expressao de Soma com as sub-expressoes especificadas.
	 * Assume-se que estas sub-expressoes resultam em <code>ValorInteiro</code>
	 * quando avaliadas.
	 * 
	 * @param esq
	 *            expressao da esquerda
	 * @param dir
	 *            expressao da direita
	 */
	public ExpSoma(Expressao esq, Expressao dir) {
		super(esq, dir, "+");
	}

	/**
	 * Retorna o valor da expressao de Soma
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return obterResultadoDaSoma(ambiente);
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
				&& (getEsq().getTipo(ambiente)).eInteiro()
				&& (getDir().getTipo(ambiente)).eInteiro()) {
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
	 * Retorna o valor inteiro que representa o resultado da soma das duas
	 * expressões
	 * 
	 * @param ambiente
	 *            é o Ambiente de Execução
	 * @return o valor inteiro que representa o resultado da soma das duas
	 *         expressões
	 */
	private ValorInteiro obterResultadoDaSoma(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		return new ValorInteiro(
				((ValorInteiro) getEsq().avaliarRef(ambiente)).valor()
						+ ((ValorInteiro) getDir().avaliarRef(ambiente))
								.valor());
	}
}
