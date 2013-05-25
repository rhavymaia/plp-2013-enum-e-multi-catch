package plp.puma.comando;

import plp.puma.declaracao.tipo.ListaTipoExcecao;
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
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.ValorString;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Classe que representa um comando TryCatch.
 */
public class Catch implements Comando {

	private ListaTipoExcecao tiposExcecao;

	private Comando comandoCatch;

	private Id mensagem;

	/**
	 * Construtor.
	 */
	public Catch(ListaTipoExcecao tiposExcecao, Comando comandoCatch, Id id) {
		this.tiposExcecao = tiposExcecao;
		this.comandoCatch = comandoCatch;
		this.mensagem = id;
	}

	/**
	 * Implementa o comando <code>TryCatch</code>.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * @return o ambiente depois de modificado pela execução do comando
	 *         <code>TryCatch</code>.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException, EntradaInvalidaException,
			TryCatchException {

		return null;
	}

	/**
	 * Realiza a verificacao de tipos da expressão e dos comandos do comando
	 * <code>TryCatch</code>
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

		return true;
	}

	public ListaTipoExcecao getTiposExcecao() {
		return tiposExcecao;
	}

	public Comando getComandoCatch() {
		return comandoCatch;
	}

	public Id getMensagem() {
		return mensagem;
	}
}