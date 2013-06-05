package plp.puma.comando;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Stack;
import java.util.Map.Entry;

import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.Valor;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.DefClasse;
import plp.puma.util.Tipo;

public class Throw implements Comando {

	private Tipo tipoExcecao;
	private Id mensagem;
	
	public Throw(Tipo tipoExcecao, Id mensagem) {
		this.tipoExcecao = tipoExcecao;
		this.mensagem = mensagem;
	}

	/**
	 * Não realiza nenhuma alteração no ambiente.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * @return o ambiente inalterado.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws TryCatchException {
		Comando cmd = null;
		String className = null;
		try {
			boolean exceptionFound = false;
			Stack<HashMap<Id, DefClasse>> classes = ambiente.getPilhaDefClasse();
			for (HashMap<Id, DefClasse> hashMap : classes) {
				for (Entry<Id, DefClasse> entry : hashMap.entrySet()) {
					if(tipoExcecao.getTipo().toString().equals(entry.getKey().toString())){
						cmd = entry.getValue().getMetodo(new Id("getMensagem")).getComando();
						className = entry.getKey().toString();
						exceptionFound = true;
						break;
					}
				}
				if(exceptionFound)
					break;
			}
			throw new Exception();
		} catch (ClassNotFoundException e){
			System.out.println("Exceção "+tipoExcecao.getTipo().toString()+" não encontrada.");
		} catch (Exception e) {
			throw new TryCatchException(cmd, className);
		}
		return ambiente;
	}
	
	public Valor getValor(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException, TryCatchException {
		return null;
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
