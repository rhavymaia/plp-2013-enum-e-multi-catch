package plp.puma.comando;

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
import plp.puma.expressao.Expressao;
import plp.puma.expressao.ListaExpressao;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorEnum;
import plp.puma.expressao.valor.ValorRef;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.ContextoExecucao;
import plp.puma.memoria.DefClasse;
import plp.puma.memoria.DefClasseGenerica;
import plp.puma.memoria.Objeto;
import plp.puma.memoria.colecao.ListaValor;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasseGeneric;

/**
 * Classe que representa a chamada de um método.
 */
public class ChamadaMetodo implements Comando {
	/**
	 * A expressão que chama o método.
	 */
	private Expressao expressao;
	/**
	 * O identificador que representa o nome do método.
	 */
	private Id nomeMetodo;
	/**
	 * Parâmetros passados para o método.
	 */
	private ListaExpressao parametrosReais;

	/**
	 * Construtor.
	 * 
	 * @param expressao
	 *            A expressao chamadora do método.
	 * @param nomeMetodo
	 *            O nome do método.
	 * @param parametrosReais
	 *            Os parâmetros passados para a execução do método.
	 * @param
	 */
	public ChamadaMetodo(Expressao expressao, Id nomeMetodo,
			ListaExpressao parametrosReais) {
		this.expressao = expressao;
		this.nomeMetodo = nomeMetodo;
		this.parametrosReais = parametrosReais;
	}

	/**
	 * Executa uma chamada de método.
	 * 
	 * @param ambiente
	 *            O ambiente de execução, que guarda o mapeamento de
	 *            identificadores a valores.
	 * @return o Ambiente de Execução atualizado.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ObjetoJaDeclaradoException,
			ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException,
			ClasseJaDeclaradaException, EntradaInvalidaException,
			TryCatchException {

		Valor valor = expressao.avaliar(ambiente); // recupera o id do objeto
		Objeto objeto = null;
		if (valor.eReferencia())
			objeto = ambiente.getObjeto((ValorRef) valor); // recupera o objeto
		else
			objeto = ((ValorEnum) valor).getObjeto();
		Id idClasse = objeto.getClasse(); // recupera o tipo do objeto
		DefClasse defClasse = ambiente.getDefClasse(idClasse); // recupera a
																// definição da
																// classe
		Procedimento metodo = defClasse.getMetodo(nomeMetodo); // recupera o
																// procedimento
		// cria um novo ambiente para a execucao, pois
		// não deve levar em conta as variáveis definidas na main
		AmbienteExecucao aux = new ContextoExecucao(ambiente);
		// é change pois no construtor do ambiente
		aux.changeValor(new Id("this"), valor); // invocado na linha anterior ja
												// é feito
												// um mapeamento

		ListaValor valoresDosParametros = parametrosReais.avaliar(ambiente);
		new ChamadaProcedimento(metodo, parametrosReais, valoresDosParametros)
				.executar(aux);
		return ambiente;
	}

	/**
	 * Realiza a verificação de tipos desta chamada de método, onde o tipo do
	 * método deve estar na definição da classe obtida a partir de expressão.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return <code>true</code> se a chamada de método está bem tipada;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ClasseNaoDeclaradaException {
		boolean resposta;
		// Antes de incrementar o ambiente, verifico se o método
		// é válido para a definicao de classe obtida a partir de expressao.
		// Se não for válido, a exceção ProcedimentoNaoDeclaradoException será
		// lançada e checaTipo retornará false.
		Tipo tipoClasse = expressao.getTipo(ambiente);
		DefClasse defClasse = ambiente.getDefClasse(tipoClasse.getTipo());
		try {
			Procedimento metodo = defClasse.getMetodo(nomeMetodo);
			ambiente.incrementa();
			ambiente.mapTipo(new Id("this"), tipoClasse);

			if (defClasse instanceof DefClasseGenerica) {
				DefClasseGenerica dcg = (DefClasseGenerica) defClasse;
				Tipo tg = dcg.getTipoGeneric();
				Tipo tr = ((TipoClasseGeneric) tipoClasse).getTipoGeneric();
				try {
					ambiente.mapTipoReal(tg, tr);
				} catch (ClasseJaDeclaradaException e) {
					// TODO verificar se isso soh acontece com chamada
					// recursiva.
				}
			}

			resposta = new ChamadaProcedimento(metodo, parametrosReais)
					.checaTipo(ambiente);
			ambiente.restaura();
		} catch (ProcedimentoNaoDeclaradoException e) {
			resposta = false;
		}
		return resposta;
	}
}
