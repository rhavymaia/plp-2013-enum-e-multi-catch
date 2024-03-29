package plp.imperative1.command;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.ValorBooleano;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;

public class While implements Comando {

	private Expressao expressao;

	private Comando comando;

	public While(Expressao expressao, Comando comando) {
		this.expressao = expressao;
		this.comando = comando;
	}

	/**
	 * Implementa o comando <code>while</code>.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * 
	 * @return o ambiente depois de modificado pela execu��o do comando
	 *         <code>while</code>.
	 * 
	 */
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		while (((ValorBooleano) expressao.avaliar(ambiente)).valor()) {
			ambiente = comando.executar(ambiente);
		}
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos da express�o e dos comandos do comando
	 * <code>while</code>
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se os comando s�o bem tipados;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return expressao.checaTipo(ambiente)
				&& expressao.getTipo(ambiente).eBooleano()
				&& comando.checaTipo(ambiente);
	}

}
