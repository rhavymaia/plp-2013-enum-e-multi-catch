package plp.puma.comando;

import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.excecao.execucao.EntradaInvalidaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.Valor;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;

/**
 * Representa um comando de leitura.
 */
public class Read implements IO {
	/**
	 * O identificador ao qual será atribuído o valor lido.
	 */
	private Id id;
	/**
	 * O tipo do identificador cujo novo valor será lido.
	 */
	private Tipo tipoId;

	/**
	 * Construtor.
	 * 
	 * @param id
	 *            O identificador ao qual será a atribuído o valor lido.
	 */
	public Read(Id id) {
		this.id = id;
	}

	/**
	 * Lê da entrada padrão.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * @return o ambiente depois de modificado pela execução do comando read.
	 * 
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			EntradaInvalidaException, TryCatchException {
		ambiente.changeValor(id, ambiente.read(this.tipoId));
		return ambiente;
	}
	
	public Valor getValor(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException, TryCatchException {
		return null;
	}
	
	/**
	 * Realiza a verificacao de tipos da entrada
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se a expressão da entrada está bem tipada;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException {
		// Alteramos a implementação, pois em tempo de compilação não se pode
		// saber
		// o tipo da entrada que será lida.

		// Tipo tipo = ambiente.getTipoEntrada();
		// Tipo tipo2 = id.getTipo(ambiente);

		this.tipoId = id.getTipo(ambiente);
		return id.checaTipo(ambiente);

		// return id.getTipo(ambiente).equals(ambiente.getTipoEntrada());
	}

}
