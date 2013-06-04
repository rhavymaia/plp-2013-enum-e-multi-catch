package plp.puma.comando;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
import plp.puma.expressao.valor.Valor;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;

/**
 * Comando de escrita.
 */
public class Write implements IO {
	/**
	 * Expressão a ser escrita.
	 */
	public Expressao expressao;

	/**
	 * Construtor.
	 * 
	 * @param expressão
	 *            Expressão a ser escrita.
	 */
	public Write(Expressao expressao) {
		this.expressao = expressao;
	}

	/**
	 * Escreve na saida padrão.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * @return o ambiente depois de modificado pela execução do comando
	 *         <code>write</code>.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException, TryCatchException {
		Valor valor = expressao.avaliar(ambiente);
		System.out.println(valor);
		return ambiente.write(valor);
	}
	
	public Valor getValor(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException, TryCatchException {
		return expressao.avaliar(ambiente);
	}
	
	/**
	 * Realiza a verificacao de tipos da expressão a ser escrita na pelo comando
	 * <code>write</code>
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se a expressão a ser escrita está bem tipada;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseNaoDeclaradaException {
		return expressao.checaTipo(ambiente);
	}
}
