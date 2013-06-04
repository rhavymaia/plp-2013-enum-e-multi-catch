package plp.puma.comando;

import plp.puma.declaracao.tipo.ListaCatch;
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
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorString;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Classe que representa um comando TryCatchFinally.
 */
public class TryCatchFinally implements Comando {

	private Comando comandoTry;
	private Comando comandoFinally;
	private ListaCatch listaCatch;

	public TryCatchFinally(Comando comandoTry, ListaCatch listaCatch, Comando comandoFinally) {
		this.comandoTry = comandoTry;
		this.comandoFinally = comandoFinally;
		this.listaCatch = listaCatch;
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
			ambiente.incrementa();
			ambiente = comandoTry.executar(ambiente);
		} catch (TryCatchException e) {
			/*for (Catch c : listaCatch.getCatchs()) {
				for (Tipo t : c.getTiposExcecao().getTipos()) {
					if(e.getExceptionClass().getSimpleName().equals(t.getTipo().toString())){
						ambiente.mapValor(c.getMensagem(), new ValorString(e.getMessage()));
						c.getComandoCatch().executar(ambiente);
						break;
					}
				}
			}*/
		} finally {
			ambiente = comandoFinally.executar(ambiente);
			ambiente.restaura();
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

		return validaTry;
	}

}