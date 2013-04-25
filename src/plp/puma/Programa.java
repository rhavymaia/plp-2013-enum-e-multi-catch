package plp.puma;

import plp.puma.comando.Comando;
import plp.puma.declaracao.classe.DecClasse;
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
import plp.puma.excecao.execucao.EntradaNaoFornecidaException;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.colecao.ListaValor;

/**
 * Classe que representa um programa na linguagem OO.
 */
public class Programa {

	/**
	 * Declara�ao de classe
	 */
	private DecClasse decClasse;
	/**
	 * Comando executado ap�s a declara�ao de classes
	 */
	private Comando comando;

	/**
	 * Construtor.
	 * 
	 * @param decClasse
	 *            A declara�ao de classe(s)
	 * @param comando
	 *            O comando executado ap�s a declara�ao.
	 */
	public Programa(DecClasse decClasse, Comando comando) {
		this.decClasse = decClasse;
		this.comando = comando;
	}

	/**
	 * Executa o programa.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * 
	 * @return o ambiente depois de modificado pela execu��o do programa.
	 * 
	 * @exception EntradaNaoFornecidaException
	 *                se n�o for fornecida a tail de valores de entrada do
	 *                programa.
	 * 
	 */
	public ListaValor executar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException, ObjetoJaDeclaradoException,
			ProcedimentoJaDeclaradoException,
			ProcedimentoNaoDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException, EntradaNaoFornecidaException,
			EntradaInvalidaException, TryCatchException {
		if (ambiente == null)
			throw new EntradaNaoFornecidaException();
		ambiente.incrementa();
		ambiente = comando.executar(decClasse.elabora(ambiente));
		ambiente.restaura();
		return ambiente.getSaida();
	}

	/**
	 * Realiza a verificacao de tipos do programa
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se o programa est� bem tipado;
	 *         <code>false</code> caso contrario.
	 * 
	 * @exception EntradaNaoFornecidaException
	 *                se n�o for fornecida a tail de valores de entrada do
	 *                programa.
	 * 
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException, EntradaNaoFornecidaException {
		boolean resposta;
		if (ambiente == null)
			throw new EntradaNaoFornecidaException();
		ambiente.incrementa();
		resposta = decClasse.checaTipo(ambiente) && comando.checaTipo(ambiente);
		ambiente.restaura();
		return resposta;
	}
}