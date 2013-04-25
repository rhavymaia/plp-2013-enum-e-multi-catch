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
	 * Expressão de avaliação.
	 */
	private Expressao expressao;
	/**
	 * Comando executado caso a expressão seja verdadeira.
	 */
	private Comando comandoThen;
	/**
	 * Comando executado caso a expressão seja falsa.
	 */
	private Comando comandoElse;

	/**
	 * Construtor.
	 * 
	 * @param expressao
	 *            Expressão de avaliação.
	 * @param comandoThen
	 *            Comando executado caso a expressão seja verdadeira.
	 * @param comandoElse
	 *            Comando executado caso a expressão seja falsa.
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
	 *            o ambiente de execução.
	 * @return o ambiente depois de modificado pela execução do comando
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
	 * Realiza a verificacao de tipos da expressão e dos comandos do comando
	 * <code>if then else</code>
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se a expressão e os comando são bem tipados;
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
