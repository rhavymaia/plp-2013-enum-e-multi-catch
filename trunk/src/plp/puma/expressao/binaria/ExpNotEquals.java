package plp.puma.expressao.binaria;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorBooleano;
import plp.puma.expressao.valor.ValorConcreto;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasse;
import plp.puma.util.TipoPrimitivo;

/**
 * Um objeto desta classe representa uma expressao de Igualdade entre Expressoes
 * cuja avaliacao resulta num mesmo valor primitivo.
 */
public class ExpNotEquals extends ExpBinaria {

	/**
	 * Controi uma expressao de Igualdade com as sub-expressoes especificadas.
	 * Assume-se que estas sub-expressoes resultam num mesmo valor primitivo
	 * quando avaliadas.
	 * 
	 * @param esq
	 *            expressao da esquerda
	 * @param dir
	 *            expressao da direita
	 */
	public ExpNotEquals(Expressao esq, Expressao dir) {
		super(esq, dir, "!=");
	}

	/**
	 * Retorna o valor da expressao de Igualdade
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return verificarIgualdade(ambiente);
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
		boolean result = false;
		if (super.checaTipo(ambiente)) {
			if (getEsq().getTipo(ambiente) instanceof TipoClasse) {
				result = getDir().getTipo(ambiente)
						.equals(TipoClasse.TIPO_NULL)
						|| getEsq().getTipo(ambiente).equals(
								getDir().getTipo(ambiente));
			} else {
				result = getEsq().getTipo(ambiente).equals(
						getDir().getTipo(ambiente));
			}
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
	 * Retorna o valor booleano que representa o resultado da comparacao de
	 * igualdade de duas expressoes
	 * 
	 * @param ambiente
	 *            é o Ambiente de Execução
	 * @return o valor inteiro que representa o resultado da concatenacao de
	 *         dois Strings
	 */
	private ValorBooleano verificarIgualdade(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		Valor v1 = getEsq().avaliarRef(ambiente);
		Valor v2 = getDir().avaliarRef(ambiente);
		boolean compara;
		if (v1 instanceof ValorConcreto && v2 instanceof ValorConcreto) {
			compara = !((ValorConcreto) v1).equals((ValorConcreto) (v2));
		} else {
			compara = !v1.equals(v2);
		}
		return new ValorBooleano(compara);
	}
}