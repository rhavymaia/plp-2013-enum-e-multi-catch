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
 * Classe que representa um ambiente de execução, contendo om mapeamento entre
 * identificadores e valores.
 */
public interface AmbienteExecucao extends Ambiente {
	/**
	 * Obtém a pilha de valores associados a identificadores
	 * 
	 * @return a pilha de valores associados a identificadores.
	 */
	public Stack<HashMap<Id, Valor>> getPilha();

	/**
	 * Obtém a pilha de tipos generics associados a tipos reais.
	 * 
	 * @return a pilha de tipos generics associados a tipos reas.
	 */
	public Stack<HashMap<Tipo, Tipo>> getPilhaTipoTipo();

	/**
	 * Retorna a pilha com as definiçoes das classes.
	 * 
	 * @return a pilha com as definiçoes das classes.
	 */
	public Stack<HashMap<Id, DefClasse>> getPilhaDefClasse();

	/**
	 * Obtém a pilha com os objetos e seus valores.
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
	 *             Quando a variável já foi declarada.
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
	 * Mapeia um identificador a um definição de classe.
	 * 
	 * @param idArg
	 *            o nome da classe
	 * @param defClasse
	 *            Definição da Classe.
	 * @throws ClasseJaDeclaradaException
	 *             quando a classe já foi declarada.
	 */
	public void mapDefClasse(Id idArg, DefClasse defClasse)
			throws ClasseJaDeclaradaException;

	/**
	 * Mapeia um valor referência a um objeto.
	 * 
	 * @param valorRef
	 *            Valor referência.
	 * @param objeto
	 *            Objeto.
	 * @throws ObjetoJaDeclaradoException
	 *             Quando esse objeto já foi declarado.
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
	 *             Quando a variável não foi declarada.
	 */
	public void changeValor(Id idArg, Valor valorId)
			throws VariavelNaoDeclaradaException;

	/**
	 * Obtém o valor associado a um determinado identificador.
	 * 
	 * @param idArg
	 *            Identificador
	 * @return o valor associado a um determinado identificador.
	 * @throws VariavelNaoDeclaradaException
	 *             Quando a variável não foi declarada.
	 */
	public Valor getValor(Id idArg) throws VariavelNaoDeclaradaException;

	/**
	 * Obtém o tipo real associado ao tipo generico.
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
	 * Obtém a definição da classe cujo nome é idArg
	 * 
	 * @param idArg
	 *            Nome da classe.
	 * @return a definição da classe.
	 * @throws ClasseNaoDeclaradaException
	 *             quando nao foi declarada nenhuma classe com esse nome.
	 */
	public DefClasse getDefClasse(Id idArg) throws ClasseNaoDeclaradaException;

	/**
	 * Obtém o objeto associado a um dado valor referencia.
	 * 
	 * @param valorRef
	 *            Valor referência
	 * @return o objeto associado a um dado valor referencia.
	 * @throws ObjetoNaoDeclaradoException
	 *             Quando o objeto não foi declarado.
	 */
	public Objeto getObjeto(ValorRef valorRef)
			throws ObjetoNaoDeclaradoException;

	/**
	 * Obtém a próxima referência de acordo com o contexto atual de execução.
	 * 
	 * @return a próxima referência de acordo com o contexto atual de execução.
	 */
	public ValorRef getProxRef();

	/**
	 * Obtém o valor referencia atual.
	 * 
	 * @return o valor referencia atual.
	 */
	public ValorRef getRef();

	/**
	 * Lê da entrada padrão e associa o conteúdo a um determinado identificador.
	 * 
	 * @param tipoIdLido
	 *            Tipo do identificador ao qual será associado o valor lido.
	 * @return o valor lido.
	 * @throws EntradaInvalidaException
	 *             Quando a entrada fornecida não pode ser atribuída ao tipo do
	 *             identificador.
	 */
	public Valor read(Tipo tipoIdLido) throws EntradaInvalidaException;

	/**
	 * Escreve um valor 'v' na saída.
	 * 
	 * @param v
	 *            O valor a ser escrito.
	 * @return o ambiente de execução, que representa o estado atual.
	 */
	public AmbienteExecucao write(Valor v);

	/**
	 * Obtém a entrada.
	 * 
	 * @return a entrada.
	 */
	public ListaValor getEntrada();

	/**
	 * Obtém a saída.
	 * 
	 * @return a saída.
	 */
	public ListaValor getSaida();

	/**
	 * Obtém um novo contexto de execução com a mesma entrada, saída e pilha de
	 * mapeamentos id/valor.
	 * 
	 * @return um novo contexto de execução com a mesma entrada, saída e pilha
	 *         de mapeamentos id/valor.
	 */
	public ContextoExecucao getContextoIdValor();

}
