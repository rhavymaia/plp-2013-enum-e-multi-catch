package plp.puma.excecao.declaracao;

import com.sun.xml.internal.bind.v2.model.core.ID;

import plp.puma.expressao.leftExpression.Id;
import plp.puma.util.Tipo;


/**
 * Excecao utilizada para capturar as execoes durante a execucao do sistema.
 * 
 */
public class TryCatchException extends Exception {
	private Class exceptionClass;
	private String msg;
	
	public TryCatchException(String msg, Class t){
		this.exceptionClass = t;
		this.msg = msg;
	}

	public Class getExceptionClass() {
		return exceptionClass;
	}
	
	public String getMessage(){
		return msg;
	}
}