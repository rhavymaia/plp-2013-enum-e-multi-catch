package plp.puma.declaracao.variavel;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasse;

/**
 * Classe que representa uma declara�ao de vari�vel simples.
 */
public class SimplesDecVariavel implements DecVariavel {
	/**
	 * Tipo da vari�vel declada.
	 */
	private Tipo tipo;
	/**
	 * Vari�vel declarada.
	 */
	private Id id;
	/**
	 * Express�o cujo valor ser� atribu�do � vari�vel.
	 */
	private Expressao expressao;

	/**
	 * Construtor.
	 * 
	 * @param tipo
	 *            Tipo da vari�vel declarada.
	 * @param id
	 *            Vari�vel declarada.
	 * @param expressao
	 *            Express�o cujo valor ser� atribu�do � vari�vel.
	 */
	public SimplesDecVariavel(Tipo tipo, Id id, Expressao expressao) {
		this.tipo = tipo;
		this.id = id;
		this.expressao = expressao;
	}

	/**
	 * Retorna o tipo do identificador a ser declarado no AmbienteCompilacao
	 * 
	 * @param id
	 *            o identificador da declaracao
	 * @return o tipo do identificador
	 */
	public Tipo getTipo(Id id) throws VariavelNaoDeclaradaException {
		if (this.id.equals(id)) {
			return tipo;
		} else {
			throw new VariavelNaoDeclaradaException(id);
		}
	}

	/**
	 * Cria um mapeamento do identificador para o valor da express�o desta
	 * declara��o no AmbienteExecucao
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela inicializa��o da vari�vel.
	 * 
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException, ObjetoJaDeclaradoException,
			TryCatchException {
		ambiente.mapValor(id, expressao.avaliar(ambiente));
		return ambiente;
	}

	/**
	 * Verifica se a declara��o est� bem tipada, ou seja, se a express�o de
	 * inicializa��o est� bem tipada.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se a express�o � bem tipada; <code>false</code>
	 *         caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ClasseNaoDeclaradaException {
		boolean resposta = false;
		if (expressao.checaTipo(ambiente)) {
			if (tipo instanceof TipoClasse) {
				resposta = expressao.getTipo(ambiente).equals(
						TipoClasse.TIPO_NULL)
						|| expressao.getTipo(ambiente).equals(tipo);
			} else {
				resposta = expressao.getTipo(ambiente).equals(tipo);
			}
		}
		if (resposta) {
			ambiente.mapTipo(id, tipo);
		}
		return resposta;
	}
}
