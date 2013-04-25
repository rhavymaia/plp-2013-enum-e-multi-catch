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
	 * Declaraçao de classe
	 */
	private DecClasse decClasse;
	/**
	 * Comando executado após a declaraçao de classes
	 */
	private Comando comando;

	/**
	 * Construtor.
	 * 
	 * @param decClasse
	 *            A declaraçao de classe(s)
	 * @param comando
	 *            O comando executado após a declaraçao.
	 */
	public Programa(DecClasse decClasse, Comando comando) {
		this.decClasse = decClasse;
		this.comando = comando;
	}

	/**
	 * Executa o programa.
	 * 
	 * @param ambiente
	 *            o ambiente de execução.
	 * 
	 * @return o ambiente depois de modificado pela execução do programa.
	 * 
	 * @exception EntradaNaoFornecidaException
	 *                se não for fornecida a tail de valores de entrada do
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
	 *            o ambiente de compilação.
	 * @return <code>true</code> se o programa está bem tipado;
	 *         <code>false</code> caso contrario.
	 * 
	 * @exception EntradaNaoFornecidaException
	 *                se não for fornecida a tail de valores de entrada do
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