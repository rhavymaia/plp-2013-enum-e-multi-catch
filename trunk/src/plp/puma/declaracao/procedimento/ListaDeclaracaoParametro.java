package plp.puma.declaracao.procedimento;

import plp.imperative1.util.Lista;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;

/**
 * Um conjunto de declara��es de par�metro.
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
	 *            A declara��o contida por esta tail.
	 */
	public ListaDeclaracaoParametro(DecParametro declaracao) {
		super(declaracao, null);
	}

	/**
	 * Construtor.
	 * 
	 * @param declaracao
	 *            A declara��o contida por esta tail.
	 * @param listaDeclaracao
	 *            A tail de declara��es que segue declara��o.
	 */
	public ListaDeclaracaoParametro(DecParametro declaracao,
			ListaDeclaracaoParametro listaDeclaracao) {
		super(declaracao, listaDeclaracao);
	}

	/**
	 * Cria um mapeamento do identificador para esta tail de declara��es de
	 * par�metro.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela declara��o da classe.
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
	 * Verifica se a declara��o e a tail de declara��o est�o bem tipadas, ou
	 * seja, se a express�o de inicializa��o est� bem tipada.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se os tipos da declara��o s�o v�lidos;
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
	 * declara��o no AmbienteCompilacao
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificador e seu
	 *            tipo.
	 * @return o ambiente modificado pela declara��o do parametro.
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
