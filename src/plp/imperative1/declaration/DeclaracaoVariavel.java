package plp.imperative1.declaration;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;

public class DeclaracaoVariavel extends Declaracao<Id, Expressao> {

	public DeclaracaoVariavel(Id id, Expressao expressao) {
		super(id, expressao);
	}

	/**
	 * Cria um mapeamento do identificador para o valor da expressão desta
	 * declaração no AmbienteExecucao
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * 
	 * @return o ambiente modificado pela inicialização da variável.
	 */
	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException {
		ambiente.map(parametro1, parametro2.avaliar(ambiente));
		return ambiente;
	}

	/**
	 * Verifica se a declaração está bem tipada, ou seja, se a expressão de
	 * inicialização está bem tipada, e cria o mapeamento da variavel para o seu
	 * tipo correspondente
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * 
	 * @return <code>true</code> se os tipos da declaração são válidos;
	 *         <code>false</code> caso contrario.
	 * 
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException {
		boolean result = parametro2.checaTipo(ambiente);
		if (result) {
			ambiente.map(parametro1, parametro2.getTipo(ambiente));
		}
		return result;
	}
}
