package plp.puma.comando;

import plp.puma.declaracao.procedimento.ListaDeclaracaoParametro;
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
import plp.puma.expressao.ListaExpressao;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.colecao.ListaValor;
import plp.puma.util.ListaTipo;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasse;
import plp.puma.util.TipoGeneric;

/**
 * Classe que representa uma chamada de um procedimento.
 */
public class ChamadaProcedimento implements Comando {

	/**
	 * É o procedimento
	 */
	private Procedimento procedimento;

	/**
	 * São os parametros do procedimento
	 */
	private ListaExpressao parametrosReais;

	/**
	 * Valores que serao atribuídos aos parametros reais
	 */
	private ListaValor valoresParametros;

	/**
	 * Contrutor Default.
	 * 
	 * @param procedimento
	 *            é o procedimento
	 * @param parametrosReais
	 *            sao os parâmetros do procedimento
	 * @param valoresParametros
	 *            sao os valores dos parametros
	 */
	public ChamadaProcedimento(Procedimento procedimento,
			ListaExpressao parametrosReais, ListaValor valoresParametros) {
		this.procedimento = procedimento;
		this.parametrosReais = parametrosReais;
		this.valoresParametros = valoresParametros;
	}

	/**
	 * Contrutor Default.
	 * 
	 * @param procedimento
	 *            é o procedimento
	 * @param parametrosReais
	 *            sao os parâmetros do procedimento
	 */
	public ChamadaProcedimento(Procedimento procedimento,
			ListaExpressao parametrosReais) {
		this.procedimento = procedimento;
		this.parametrosReais = parametrosReais;
		this.valoresParametros = null;
	}

	/**
	 * Executa este comando.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela execução do comando.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ObjetoNaoDeclaradoException,
			ObjetoJaDeclaradoException, ClasseNaoDeclaradaException,
			ClasseJaDeclaradaException, EntradaInvalidaException,
			TryCatchException {

		ambiente.incrementa();
		ambiente = bindParameters(ambiente, procedimento.getParametrosFormais());
		ambiente = procedimento.getComando().executar(ambiente);
		ambiente.restaura();
		return ambiente;
	}

	/**
	 * insere no contexto o resultado da associacao entre cada parametro formal
	 * e seu correspondente parametro atual
	 */
	private AmbienteExecucao bindParameters(AmbienteExecucao ambiente,
			ListaDeclaracaoParametro parametrosFormais)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException {
		ListaValor listaValor = this.valoresParametros;
		if (listaValor == null) {
			listaValor = parametrosReais.avaliar(ambiente);
		}
		while (listaValor.length() > 0) {
			ambiente.mapValor(parametrosFormais.getHead().getId(),
					listaValor.getHead());
			parametrosFormais = ((ListaDeclaracaoParametro) parametrosFormais
					.getTail());
			listaValor = (ListaValor) listaValor.getTail();
		}
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos desta chamada de procedimento, onde os
	 * tipos dos parametros formais devem ser iguais aos tipos dos parametros
	 * reais na ordem em que se apresentam.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * 
	 * @return <code>true</code> se a chamada de procedimeno está bem tipada;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ProcedimentoNaoDeclaradoException, ClasseNaoDeclaradaException {
		boolean resposta;
		ambiente.incrementa();
		ListaDeclaracaoParametro parametrosFormais = procedimento
				.getParametrosFormais();
		ListaTipo listaTipo = parametrosReais.getTipos(ambiente);
		// tem o mesmo numero de parametros formais e reais?
		if (listaTipo.length() == parametrosFormais.length()) {
			// a funcao tem algum parametro?
			if (listaTipo.head() == null || parametrosFormais.getHead() == null) {
				resposta = true;
			} else {
				resposta = true;
				// tem parametros formais de tipos diferentes
				// de parametros reais na ordem em que se apresentam?
				while (listaTipo != null && parametrosFormais != null) {

					// Pegar os parametros do metodo
					Tipo tpf = parametrosFormais.getHead().getTipo();
					Tipo tpr = listaTipo.head();

					// TODO e se for um TipoGenericClasse
					if (tpf instanceof TipoGeneric && tpr instanceof TipoClasse) {
						tpf = ambiente.getTipoReal(tpf);
						tpf = new TipoClasse((TipoGeneric) tpf);
					}

					if (!tpr.equals(tpf)) {
						resposta = false;
						break;
					}
					listaTipo = listaTipo.tail();
					parametrosFormais = ((ListaDeclaracaoParametro) parametrosFormais
							.getTail());
				}
			}
		} else {
			resposta = false;
		}
		ambiente.restaura();
		return resposta;
	}
}