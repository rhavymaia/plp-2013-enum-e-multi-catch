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
import plp.puma.expressao.Expressao;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorBooleano;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.TipoPrimitivo;

/**
 * Classe que representa um comando While.
 */
public class While implements Comando {
	/**
	 * Expressão booleana a ser avaliada.
	 */
	private Expressao expressao;
	/**
	 * Comando que será executado caso a expressão seja avaliada como
	 * verdadeira.
	 */
	private Comando comando;

	/**
	 * Construtor.
	 * 
	 * @param expressao
	 *            A expressão booleana a ser avaliada.
	 * @param o
	 *            comando a ser executado caso a expressão seja verdadeira.
	 */
	public While(Expressao expressao, Comando comando) {
		this.expressao = expressao;
		this.comando = comando;
	}

	/**
	 * Implementa o comando <code>while</code>.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * @return o ambiente depois de modificado pela execução do comando
	 *         <code>while</code>.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException, EntradaInvalidaException,
			TryCatchException {
		while (((ValorBooleano) expressao.avaliar(ambiente)).valor()) {
			ambiente = comando.executar(ambiente);
		}
		return ambiente;
	}
	
	public Valor getValor(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException, TryCatchException {
		return null;
	}
	
	/**
	 * Realiza a verificacao de tipos da expressão e dos comandos do comando
	 * <code>while</code>
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se os comando são bem tipados;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseNaoDeclaradaException,
			ClasseJaDeclaradaException {
		return expressao.checaTipo(ambiente)
				&& ((TipoPrimitivo) expressao.getTipo(ambiente)).eBooleano()
				&& comando.checaTipo(ambiente);
	}

}
