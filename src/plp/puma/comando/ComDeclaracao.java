package plp.puma.comando;

import plp.puma.declaracao.Declaracao;
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
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;

/**
 * Classe que representa um comando de declara��o.
 */
public class ComDeclaracao implements Comando {
	/**
	 * A declara��o em si.
	 */
	private Declaracao declaracao;
	/**
	 * O comando executado ap�s a declara��o.
	 */
	private Comando comando;

	/**
	 * Construtor.
	 */
	public ComDeclaracao(Declaracao declaracao, Comando comando) {
		this.declaracao = declaracao;
		this.comando = comando;
	}

	/**
	 * Declara a(s) vari�vel(is) e executa o comando.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela execu��o da declara��o e do comando.
	 * 
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseNaoDeclaradaException,
			ClasseJaDeclaradaException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, EntradaInvalidaException,
			TryCatchException {
		ambiente.incrementa();
		ambiente = comando.executar(declaracao.elabora(ambiente));
		ambiente.restaura();
		return ambiente;
	}

	/**
	 * Verifica se o tipo do comando esta correto, levando em conta que o tipo
	 * de uma variavel � o tipo do valor da sua primeira atribuicao.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseNaoDeclaradaException,
			ClasseJaDeclaradaException {
		boolean resposta;
		ambiente.incrementa();
		resposta = declaracao.checaTipo(ambiente)
				&& comando.checaTipo(ambiente);
		ambiente.restaura();
		return resposta;
	}
}
