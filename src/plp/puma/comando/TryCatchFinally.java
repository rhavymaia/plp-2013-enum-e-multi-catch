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
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.ValorString;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Classe que representa um comando TryCatchFinally.
 */
public class TryCatchFinally implements Comando {

	private Tipo tipoExcecao;

	/**
	 * Comando do corpo do try.
	 */
	private Comando comandoTry;

	/**
	 * Comando do corpo do catch.
	 */
	private Comando comandoCatch;

	/**
	 * Comando do corpo do finally.
	 */
	private Comando comandoFinally;

	private Id mensagem;

	/**
	 * Construtor.
	 */
	public TryCatchFinally(Tipo tipoExcecao, Comando comandoTry, Comando comandoCatch,
			Comando comandoFinally, Id id) {
		this.tipoExcecao = tipoExcecao;
		this.comandoTry = comandoTry;
		this.comandoCatch = comandoCatch;
		this.comandoFinally = comandoFinally;
		this.mensagem = id;
	}

	/**
	 * Implementa o comando <code>TryCatchFinally</code>.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * @return o ambiente depois de modificado pela execução do comando
	 *         <code>TryCatchFinally</code>.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException, EntradaInvalidaException,
			TryCatchException {

		try {
			// Criar escopo Try
			ambiente.incrementa();
			ambiente = comandoTry.executar(ambiente);

		} catch (TryCatchException e) {
			try {
				Exception ex = (Exception) Exception.class.getConstructor(
						tipoExcecao.getTipo().getClass()).newInstance(mensagem);
				ambiente.mapValor(this.mensagem.getId(),
						new ValorString(ex.getMessage()));
			} catch (Exception e2) {
			}

			// Destruir escopo try se houver excecao!
			ambiente.restaura();

			// Criar e destruir escopo do catch
			ambiente.incrementa();
			if (this.mensagem != null)
				ambiente.mapValor(this.mensagem.getId(),
						new ValorString(e.getMessage()));
			ambiente = comandoCatch.executar(ambiente);
		} finally {
			ambiente = comandoFinally.executar(ambiente);
			ambiente.restaura();
		}
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos da expressão e dos comandos do comando
	 * <code>TryCatchFinally</code>
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

		boolean validaTry = this.comandoTry.checaTipo(ambiente);

		ambiente.incrementa();
		if (this.mensagem != null)
			ambiente.mapTipo(this.mensagem.getId(), TipoPrimitivo.TIPO_STRING);
		boolean validaCatch = this.comandoCatch.checaTipo(ambiente);
		boolean validaFinally = this.comandoFinally.checaTipo(ambiente);
		ambiente.restaura();

		return validaTry && validaCatch && validaFinally;
	}

}