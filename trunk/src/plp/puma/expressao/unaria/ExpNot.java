package plp.puma.expressao.unaria;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorBooleano;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma expressao de Negacao logica.
 */
public class ExpNot extends ExpUnaria {

	/**
	 * Controi uma expressao de negacao logica com expressao especificada.
	 * 
	 * @param exp
	 *            expressao a ser negada. Assume-se que sua avaliacao resulta em
	 *            <code>ValorBooleano</code>.
	 */
	public ExpNot(Expressao expressao) {
		super(expressao, "~");
	}

	/**
	 * Retorna o valor da expressao de negacao logica.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * @return o valor da expressao avaliada.
	 * @excepion VariavelJaDeclaradaException se a vari�vel j� est� declarada no
	 *           ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se a vari�vel n�o est� declarada no ambiente.
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		return obterValorInverso(ambiente);
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao s�o v�lidos;
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
				&& (getExp().getTipo(ambiente)).eBooleano()) {
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
	 *            o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente) {
		return TipoPrimitivo.TIPO_BOOLEANO;
	}

	/**
	 * Retorna o valor inverso de uma express�o booleana
	 * 
	 * @param ambiente
	 *            � o Ambiente de Execu��o
	 * @return o valor inverso da express�o booleana
	 */
	private ValorBooleano obterValorInverso(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		return new ValorBooleano(
				!((ValorBooleano) getExp().avaliar(ambiente)).valor());
	}
}
