package plp.puma.comando;

import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;

public class Skip implements Comando {

	/**
	 * Não realiza nenhuma alteração no ambiente.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * @return o ambiente inalterado.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws TryCatchException {
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos do comando
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se o comando é bem tipado; <code>false</code>
	 *         caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente) {
		return true;
	}
}
