package plp.puma.declaracao.procedimento;

import plp.imperative1.util.Lista;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;

/**
 * Um conjunto de declarações de parâmetro.
 */
public class ListaDeclaracaoParametro extends Lista<DecParametro> {
	/**
	 * Construtor.
	 */
	public ListaDeclaracaoParametro() {

	}

	/**
	 * Construtor
	 * 
	 * @param declaracao
	 *            A declaração contida por esta tail.
	 */
	public ListaDeclaracaoParametro(DecParametro declaracao) {
		super(declaracao, null);
	}

	/**
	 * Construtor.
	 * 
	 * @param declaracao
	 *            A declaração contida por esta tail.
	 * @param listaDeclaracao
	 *            A tail de declarações que segue declaração.
	 */
	public ListaDeclaracaoParametro(DecParametro declaracao,
			ListaDeclaracaoParametro listaDeclaracao) {
		super(declaracao, listaDeclaracao);
	}

	/**
	 * Cria um mapeamento do identificador para esta tail de declarações de
	 * parâmetro.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela declaração da classe.
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente) {
		AmbienteExecucao resposta;
		if (getHead() != null) {
			if (getTail() != null) {
				resposta = ((ListaDeclaracaoParametro) getTail())
						.elabora(getHead().elabora(ambiente));
			} else {
				resposta = getHead().elabora(ambiente);
			}
		} else {
			resposta = ambiente;
		}
		return resposta;
	}

	/**
	 * Verifica se a declaração e a tail de declaração estão bem tipadas, ou
	 * seja, se a expressão de inicialização está bem tipada.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se os tipos da declaração são válidos;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
		boolean resposta;
		if (getHead() != null) {
			if (getTail() != null) {
				resposta = getHead().checaTipo(ambiente)
						&& ((ListaDeclaracaoParametro) getTail())
								.checaTipo(ambiente);
			} else {
				resposta = getHead().checaTipo(ambiente);
			}
		} else {
			resposta = true;
		}
		return resposta;
	}

	/**
	 * Cria um mapeamento do identificador para o tipo do parametro desta
	 * declaração no AmbienteCompilacao
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificador e seu
	 *            tipo.
	 * @return o ambiente modificado pela declaração do parametro.
	 */
	public AmbienteCompilacao declaraParametro(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		AmbienteCompilacao resposta;
		if (getHead() != null) {
			if (getTail() != null) {
				resposta = ((ListaDeclaracaoParametro) getTail())
						.declaraParametro(getHead().declaraParametro(ambiente));
			} else {
				resposta = getHead().declaraParametro(ambiente);
			}
		} else {
			resposta = ambiente;
		}
		return resposta;
	}

}
