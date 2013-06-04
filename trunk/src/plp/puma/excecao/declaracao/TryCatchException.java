package plp.puma.excecao.declaracao;

import com.sun.xml.internal.bind.v2.model.core.ID;

import plp.puma.comando.Comando;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.util.Tipo;


/**
 * Excecao utilizada para capturar as execoes durante a execucao do sistema.
 * 
 */
public class TryCatchException extends Exception {
	private String className;
	private Comando cmd;
	
	public TryCatchException(Comando cmd, String className){
		this.className = className;
		this.cmd = cmd;
	}

	public String getClassName() {
		return className;
	}
	
	public Comando getComando(){
		return cmd;
	}
}