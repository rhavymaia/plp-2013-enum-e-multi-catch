package plp.puma.declaracao.procedimento;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;

/**
 * Classe que representa uma declara��o de par�metros.
 */
public class DecParametro {
	/**
	 * Identificador declarado.
	 */
	private Id id;
	/**
	 * Tipo do identificador declarado.
	 */
	private Tipo tipo;

	/**
	 * Construtor.
	 * 
	 * @param id
	 *            Identificador declarado.
	 * @param tipo
	 *            Tipo do identificador declarado.
	 */
	public DecParametro(Id id, Tipo tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	/**
	 * Obt�m o identificador declarado.
	 * 
	 * @return o identificador.
	 */
	public Id getId() {
		return id;
	}

	/**
	 * Obt�m o tipo do identifador declarado.
	 * 
	 * @return o tipo do identifador declarado.
	 */
	public Tipo getTipo() {
		return tipo;
	}

	/**
	 * Cria um mapeamento do identificador para o valor da express�o desta
	 * declara��o no AmbienteExecucao
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela inicializa��o da vari�vel.
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente) {
		return ambiente;
	}

	/**
	 * Verifica se a declara��o est� bem tipada, ou seja, se a express�o de
	 * inicializa��o est� bem tipada.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se os tipos da declara��o s�o v�lidos;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws ClasseNaoDeclaradaException {
		return tipo.eValido(ambiente);
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

		ambiente.mapTipo(id, tipo);
		return ambiente;
	}
}
