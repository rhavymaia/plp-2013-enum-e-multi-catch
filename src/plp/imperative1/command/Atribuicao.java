package plp.imperative1.command;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;

public class Atribuicao implements Comando {

	private Id id;

	private Expressao expressao;

	public Atribuicao(Id id, Expressao expressao) {
		this.id = id;
		this.expressao = expressao;
	}

	/**
	 * Executa a atribui��o.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * 
	 * @return o ambiente modificado pela execu��o da atribui��o.
	 * 
	 */
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		ambiente.changeValor(id, expressao.avaliar(ambiente));
		return ambiente;
	}

	/**
	 * Um comando de atribui��o est� bem tipado, se o tipo do identificador � o
	 * mesmo da express�o. O tipo de um identificador � determinado pelo tipo da
	 * express�o que o inicializou (na declara��o).
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * 
	 * @return <code>true</code> se os tipos da atribui��o s�o v�lidos;
	 *         <code>false</code> caso contrario.
	 * 
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return expressao.checaTipo(ambiente)
				&& id.getTipo(ambiente).equals(expressao.getTipo(ambiente));
	}

}
