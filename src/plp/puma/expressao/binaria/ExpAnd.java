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
 * Um objeto desta classe representa uma expressao de Conjuncao logica.
 */
public class ExpAnd extends ExpBinaria {

	/**
	 * Controi uma expressao de Conjuncao logica com as sub-expressoes
	 * especificadas. Estas devem ser tais que sua avaliacao retorna
	 * <code>ValorBooleano</code>
	 * 
	 * @param esq
	 *            expressao da esquerda
	 * @param dir
	 *            expressao da direita
	 */
	public ExpAnd(Expressao esq, Expressao dir) {
		super(esq, dir, "and");
	}

	/**
	 * Retorna o valor da expressao de Conjuncao Logica
	 * 
	 * @ambiente O ambiente de execuçao, contendo o mapeamento de
	 *           identificadores a valores.
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return obterComparacaoAND(ambiente);
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
	 * Retorna o resultado de uma comparacao booleana AND
	 * 
	 * @param ambiente
	 *            é o Ambiente de Execução
	 * @return o resultado de uma comparacao booleana AND
	 */
	private ValorBooleano obterComparacaoAND(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		return new ValorBooleano(
				((ValorBooleano) getEsq().avaliarRef(ambiente)).valor()
						&& ((ValorBooleano) getDir().avaliarRef(ambiente))
								.valor());
	}
}