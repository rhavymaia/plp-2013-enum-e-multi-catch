package plp.puma.comando;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

import plp.puma.declaracao.tipo.ListaCatch;
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
import plp.puma.expressao.Expressao;
import plp.puma.expressao.This;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorString;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.DefClasse;
import plp.puma.memoria.colecao.ListaValor;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

/**
 * Classe que representa um comando TryCatch.
 */
public class TryCatch implements Comando {

	private Comando comandoTry;
	private ListaCatch listaCatch;

	public TryCatch(Comando comandoTry, ListaCatch listaCatch) {
		this.listaCatch = listaCatch;
		this.comandoTry = comandoTry;
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

		try {
			ambiente.incrementa();
			ambiente = comandoTry.executar(ambiente);
		} catch (TryCatchException e) {
			boolean exceptionFound = false;
			for (Catch c : listaCatch.getCatchs()) {
				for (Tipo t : c.getTiposExcecao().getTipos()) {
					if(e.getClassName().equals(t.getTipo().toString())){
						ambiente.mapValor(c.getId(), e.getComando().getValor(ambiente));
						c.getComandoCatch().executar(ambiente);
						exceptionFound = true;
						break;
					}
				}
			}
		} finally {
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

		boolean validaTry = this.comandoTry.checaTipo(ambiente);

		return validaTry;
	}

}