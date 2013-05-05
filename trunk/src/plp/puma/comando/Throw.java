package plp.puma.comando;

import java.lang.reflect.Constructor;

import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.Valor;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;

public class Throw implements Comando {

	private Tipo tipoExcecao;
	private Valor mensagem;
	
	public Throw(Tipo tipoExcecao, Valor mensagem) {
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
		try {
			Class classDefinition = Class.forName("plp.puma.excecao.declaracao." + tipoExcecao.getTipo().toString()); 
			Constructor cons = classDefinition.getConstructor(Id.class);
			Exception ex = (Exception) cons.newInstance(new Id(mensagem.toString()));
			
			throw ex;
		} catch (ClassNotFoundException e){
			throw new TryCatchException("Classe " + tipoExcecao.getTipo().toString() + " não é um tipo de exceção!");
		} catch (Exception e) {
			throw new TryCatchException(e.getMessage());
		}
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
