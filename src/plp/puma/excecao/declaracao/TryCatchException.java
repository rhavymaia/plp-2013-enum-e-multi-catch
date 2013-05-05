package plp.puma.excecao.declaracao;


/**
 * Excecao utilizada para capturar as execoes durante a execucao do sistema.
 * 
 * @author Leonardo
 */
public class TryCatchException extends Exception {

	public TryCatchException(String e) {
		super(e);
	}
}
