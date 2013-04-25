package plp.puma.expressao.binaria;

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
 * Um objeto desta classe representa uma Disjuncao Logica.
 */
public class ExpOr extends ExpBinaria {

	/**
	 * Controi uma expressao de disjuncao logica com as sub-expressoes
	 * especificadas.Estas devem ser tais que sua avaliacao resulta em
	 * <code>ValorBooleano</code>
	 * 
	 * @param esq
	 *            expressao da esquerda
	 * @param dir
	 *            expressao da direita
	 */
	public ExpOr(Expressao esq, Expressao dir) {
		super(esq, dir, "or");
	}

	/**
	 * Retorna o valor da expressao de disjuncao logica
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return obterComparacaoOR(ambiente);
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
				&& (getEsq().getTipo(ambiente)).eBooleano()
				&& (getDir().getTipo(ambiente)).eBooleano()) {
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
		return TipoPrimitivo.TIPO_BOOLEANO;
	}

	/**
	 * Retorna o resultado de uma comparacao booleana OR
	 * 
	 * @param ambiente
	 *            é o Ambiente de Execução
	 * @return o resultado de uma comparacao booleana OR
	 */
	private ValorBooleano obterComparacaoOR(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		return new ValorBooleano(
				((ValorBooleano) getEsq().avaliarRef(ambiente)).valor()
						|| ((ValorBooleano) getDir().avaliarRef(ambiente))
								.valor());
	}
}