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
import plp.puma.expressao.valor.ValorBooleano;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.TipoPrimitivo;

/**
 * Classe que representa o comando If Then Else
 */
public class IfThenElse implements Comando {
	/**
	 * Express�o de avalia��o.
	 */
	private Expressao expressao;
	/**
	 * Comando executado caso a express�o seja verdadeira.
	 */
	private Comando comandoThen;
	/**
	 * Comando executado caso a express�o seja falsa.
	 */
	private Comando comandoElse;

	/**
	 * Construtor.
	 * 
	 * @param expressao
	 *            Express�o de avalia��o.
	 * @param comandoThen
	 *            Comando executado caso a express�o seja verdadeira.
	 * @param comandoElse
	 *            Comando executado caso a express�o seja falsa.
	 */
	public IfThenElse(Expressao expressao, Comando comandoThen,
			Comando comandoElse) {
		this.expressao = expressao;
		this.comandoThen = comandoThen;
		this.comandoElse = comandoElse;
	}

	/**
	 * Implementa o comando <code>if then else</code>.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * @return o ambiente depois de modificado pela execu��o do comando
	 *         <code>if then else</code>.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, EntradaInvalidaException,
			TryCatchException {
		if (((ValorBooleano) expressao.avaliar(ambiente)).valor())
			return comandoThen.executar(ambiente);
		else
			return comandoElse.executar(ambiente);
	}

	/**
	 * Realiza a verificacao de tipos da express�o e dos comandos do comando
	 * <code>if then else</code>
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se a express�o e os comando s�o bem tipados;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException {
		return expressao.checaTipo(ambiente)
				&& ((TipoPrimitivo) expressao.getTipo(ambiente)).eBooleano()
				&& comandoThen.checaTipo(ambiente)
				&& comandoElse.checaTipo(ambiente);
	}

}
