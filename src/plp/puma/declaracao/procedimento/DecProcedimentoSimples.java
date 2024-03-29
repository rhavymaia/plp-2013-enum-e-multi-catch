package plp.puma.declaracao.procedimento;

import plp.puma.comando.Comando;
import plp.puma.comando.Procedimento;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;

/**
 * Representa uma declara��o de procedimento simples.
 */
public class DecProcedimentoSimples implements DecProcedimento {
	/**
	 * Identificador do procedimento.
	 */
	private Id nome;
	/**
	 * Par�metros do procedimento.
	 */
	private ListaDeclaracaoParametro parametrosFormais;
	/**
	 * Comando, que pode ser simples ou composto, executado pelo procedimento.
	 */
	private Comando comando;

	/**
	 * Construtor.
	 * 
	 * @param nome
	 *            Nome do procedimento.
	 * @param parametrosFormais
	 *            Par�metros do procedimento.
	 * @param comando
	 *            Comando(s) executado(s) pelo procedimento.
	 */
	public DecProcedimentoSimples(Id nome,
			ListaDeclaracaoParametro parametrosFormais, Comando comando) {
		this.nome = nome;
		this.parametrosFormais = parametrosFormais;
		this.comando = comando;
	}

	/**
	 * Obt�m o procedimento representado por nome.
	 * 
	 * @param nome
	 *            O nome do procedimento procurado.
	 * @return o procedimento identificado por nome.
	 * @throws ProcedimentoNaoDeclaradoException
	 *             quando n�o existe nenhum procedimento declarado com esse
	 *             nome.
	 */
	public Procedimento getProcedimento(Id nome)
			throws ProcedimentoNaoDeclaradoException {
		if (this.nome.equals(nome)) {
			return new Procedimento(parametrosFormais, comando);
		} else {
			throw new ProcedimentoNaoDeclaradoException(nome);
		}
	}

	/**
	 * Verifica se a declara��o est� bem tipada, ou seja, se os comandos est�o
	 * bem tipados.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se os tipos dos comandos s�o v�lidos;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoJaDeclaradoException,
			ProcedimentoNaoDeclaradoException, ClasseNaoDeclaradaException,
			ClasseJaDeclaradaException {
		boolean resposta;
		if (parametrosFormais.checaTipo(ambiente)) {
			ambiente.mapParametrosProcedimento(nome, parametrosFormais);
			ambiente.incrementa();
			ambiente = parametrosFormais.declaraParametro(ambiente);
			resposta = comando.checaTipo(ambiente);
			ambiente.restaura();
		} else {
			resposta = false;
		}
		return resposta;
	}
}
