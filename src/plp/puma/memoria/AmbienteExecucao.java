package plp.puma.memoria;

import java.util.HashMap;
import java.util.Stack;

import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.excecao.execucao.EntradaInvalidaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorRef;
import plp.puma.memoria.colecao.ListaValor;
import plp.puma.util.Tipo;

/**
 * Classe que representa um ambiente de execu��o, contendo om mapeamento entre
 * identificadores e valores.
 */
public interface AmbienteExecucao extends Ambiente {
	/**
	 * Obt�m a pilha de valores associados a identificadores
	 * 
	 * @return a pilha de valores associados a identificadores.
	 */
	public Stack<HashMap<Id, Valor>> getPilha();

	/**
	 * Obt�m a pilha de tipos generics associados a tipos reais.
	 * 
	 * @return a pilha de tipos generics associados a tipos reas.
	 */
	public Stack<HashMap<Tipo, Tipo>> getPilhaTipoTipo();

	/**
	 * Retorna a pilha com as defini�oes das classes.
	 * 
	 * @return a pilha com as defini�oes das classes.
	 */
	public Stack<HashMap<Id, DefClasse>> getPilhaDefClasse();

	/**
	 * Obt�m a pilha com os objetos e seus valores.
	 * 
	 * @return a pilha com os objetos e seus valores.
	 */
	public Stack<HashMap<ValorRef, Objeto>> getPilhaObjeto();

	/**
	 * Mapeia um identificador a um valor.
	 * 
	 * @param idArg
	 *            Identificador.
	 * @param valorId
	 *            Valor que vai ser associado ao identificador.
	 * @throws VariavelJaDeclaradaException
	 *             Quando a vari�vel j� foi declarada.
	 */
	public void mapValor(Id idArg, Valor valorId)
			throws VariavelJaDeclaradaException;

	/**
	 * Mapeia um tipo generico com seu tipo real.
	 * 
	 * @param tipoGeneric
	 *            tipo generic.
	 * @param tipoReal
	 *            tipo real.
	 * @throws ClasseJaDeclaradaException
	 *             Quando um tipo generico jah foi mapeado com seu tipo real.
	 */
	public void mapTipoReal(Tipo tipoGeneric, Tipo tipoReal)
			throws ClasseJaDeclaradaException;

	/**
	 * Mapeia um identificador a um defini��o de classe.
	 * 
	 * @param idArg
	 *            o nome da classe
	 * @param defClasse
	 *            Defini��o da Classe.
	 * @throws ClasseJaDeclaradaException
	 *             quando a classe j� foi declarada.
	 */
	public void mapDefClasse(Id idArg, DefClasse defClasse)
			throws ClasseJaDeclaradaException;

	/**
	 * Mapeia um valor refer�ncia a um objeto.
	 * 
	 * @param valorRef
	 *            Valor refer�ncia.
	 * @param objeto
	 *            Objeto.
	 * @throws ObjetoJaDeclaradoException
	 *             Quando esse objeto j� foi declarado.
	 */
	public void mapObjeto(ValorRef valorRef, Objeto objeto)
			throws ObjetoJaDeclaradoException;

	/**
	 * Altera o valor associado a um identificador.
	 * 
	 * @param idArg
	 *            Identificador.
	 * @param valorId
	 *            O valor a ser associado ao identificador.
	 * @throws VariavelNaoDeclaradaException
	 *             Quando a vari�vel n�o foi declarada.
	 */
	public void changeValor(Id idArg, Valor valorId)
			throws VariavelNaoDeclaradaException;

	/**
	 * Obt�m o valor associado a um determinado identificador.
	 * 
	 * @param idArg
	 *            Identificador
	 * @return o valor associado a um determinado identificador.
	 * @throws VariavelNaoDeclaradaException
	 *             Quando a vari�vel n�o foi declarada.
	 */
	public Valor getValor(Id idArg) throws VariavelNaoDeclaradaException;

	/**
	 * Obt�m o tipo real associado ao tipo generico.
	 * 
	 * @param tipoGeneric
	 *            tipo
	 * @return tipo tipo associado ao tipo generico.
	 * @throws ClasseNaoDeclaradaException
	 *             Quando o tipo generic nao tiver bind com tipo real.
	 */
	public Tipo getTipoReal(Tipo tipoGeneric)
			throws ClasseNaoDeclaradaException;

	/**
	 * Obt�m a defini��o da classe cujo nome � idArg
	 * 
	 * @param idArg
	 *            Nome da classe.
	 * @return a defini��o da classe.
	 * @throws ClasseNaoDeclaradaException
	 *             quando nao foi declarada nenhuma classe com esse nome.
	 */
	public DefClasse getDefClasse(Id idArg) throws ClasseNaoDeclaradaException;

	/**
	 * Obt�m o objeto associado a um dado valor referencia.
	 * 
	 * @param valorRef
	 *            Valor refer�ncia
	 * @return o objeto associado a um dado valor referencia.
	 * @throws ObjetoNaoDeclaradoException
	 *             Quando o objeto n�o foi declarado.
	 */
	public Objeto getObjeto(ValorRef valorRef)
			throws ObjetoNaoDeclaradoException;

	/**
	 * Obt�m a pr�xima refer�ncia de acordo com o contexto atual de execu��o.
	 * 
	 * @return a pr�xima refer�ncia de acordo com o contexto atual de execu��o.
	 */
	public ValorRef getProxRef();

	/**
	 * Obt�m o valor referencia atual.
	 * 
	 * @return o valor referencia atual.
	 */
	public ValorRef getRef();

	/**
	 * L� da entrada padr�o e associa o conte�do a um determinado identificador.
	 * 
	 * @param tipoIdLido
	 *            Tipo do identificador ao qual ser� associado o valor lido.
	 * @return o valor lido.
	 * @throws EntradaInvalidaException
	 *             Quando a entrada fornecida n�o pode ser atribu�da ao tipo do
	 *             identificador.
	 */
	public Valor read(Tipo tipoIdLido) throws EntradaInvalidaException;

	/**
	 * Escreve um valor 'v' na sa�da.
	 * 
	 * @param v
	 *            O valor a ser escrito.
	 * @return o ambiente de execu��o, que representa o estado atual.
	 */
	public AmbienteExecucao write(Valor v);

	/**
	 * Obt�m a entrada.
	 * 
	 * @return a entrada.
	 */
	public ListaValor getEntrada();

	/**
	 * Obt�m a sa�da.
	 * 
	 * @return a sa�da.
	 */
	public ListaValor getSaida();

	/**
	 * Obt�m um novo contexto de execu��o com a mesma entrada, sa�da e pilha de
	 * mapeamentos id/valor.
	 * 
	 * @return um novo contexto de execu��o com a mesma entrada, sa�da e pilha
	 *         de mapeamentos id/valor.
	 */
	public ContextoExecucao getContextoIdValor();

}
