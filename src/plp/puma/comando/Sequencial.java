package plp.puma.comando;

import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.excecao.execucao.EntradaInvalidaException;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;

/**
 * Representa um comando sequencial, ou seja, um comando seguido de outro
 * comando.
 */
public class Sequencial implements Comando {
	/**
	 * O primeiro comando.
	 */
	private Comando comando1;
	/**
	 * O segundo comando.
	 */
	private Comando comando2;

	/**
	 * Construtor.
	 * 
	 * @param comando1
	 *            O primeiro comando
	 * @param comando2
	 *            O segundo comando.
	 */
	public Sequencial(Comando comando1, Comando comando2) {
		this.comando1 = comando1;
		this.comando2 = comando2;
	}

	/**
	 * Executa os comandos sequencialmente.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * @return o ambiente depois de modificado pela execução dos comandos.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException, EntradaInvalidaException,
			TryCatchException {

		ambiente = comando1.executar(ambiente);
		ambiente = comando2.executar(ambiente);
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos dos comandos
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se os comandos são bem tipados;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException {
		boolean resposta1 = comando1.checaTipo(ambiente);
		boolean resposta2 = comando2.checaTipo(ambiente);
		return resposta1 && resposta2;
	}
}
