package plp.puma.memoria;

import java.util.HashMap;
import java.util.Stack;

import plp.puma.declaracao.procedimento.ListaDeclaracaoParametro;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.colecao.ListaValor;
import plp.puma.util.Tipo;

/**
 * Representa o contexto de compilaçao.
 */
public class ContextoCompilacao implements AmbienteCompilacao {

	/**
	 * A pilha de tipos do contexto. Onde o tipo do id pode ser um tipo
	 * primitivo ou uma classe.
	 */
	private Stack<HashMap<Id, Tipo>> pilha;

	/**
	 * A pilha de tipos genericos para tipos reais.
	 */
	private Stack<HashMap<Tipo, Tipo>> pilhaTipoReal;

	/**
	 * A pilha de procedimentos do contexto.
	 */
	private Stack<HashMap<Id, ListaDeclaracaoParametro>> pilhaProcedimento;

	/**
	 * A pilha de classes do contexto.
	 */
	private Stack<HashMap<Id, DefClasse>> pilhaDefClasse;

	/**
	 * A pilha de declaracoes Generic.
	 */
	private Stack<HashMap<Id, Tipo>> pilhaGeneric;

	/**
	 * A tail de valores inicias do contexto.
	 */
	private ListaValor entrada;

	/**
	 * O Construtor da classe.
	 */
	public ContextoCompilacao(ListaValor entrada) {

		pilha = new Stack<HashMap<Id, Tipo>>();
		pilhaTipoReal = new Stack<HashMap<Tipo, Tipo>>();

		pilhaGeneric = new Stack<HashMap<Id, Tipo>>();
		pilhaProcedimento = new Stack<HashMap<Id, ListaDeclaracaoParametro>>();

		pilhaDefClasse = new Stack<HashMap<Id, DefClasse>>(); // esta pilha nao
																// cresce
		pilhaDefClasse.push(new HashMap<Id, DefClasse>()); // tem de incrementar
															// no começo
		this.entrada = entrada;
	}

	/**
	 * Incrementa a pilha do ambiente, passando para o próximo estado.
	 */
	public void incrementa() {
		pilha.push(new HashMap<Id, Tipo>());
		pilhaProcedimento.push(new HashMap<Id, ListaDeclaracaoParametro>());
		pilhaGeneric.push(new HashMap<Id, Tipo>());
		pilhaTipoReal.push(new HashMap<Tipo, Tipo>());
		// pilhaDefClasse.push(new HashIdDefClasse()); // so incrementa no
		// comeco (vide construtor)
	}

	/**
	 * Restaura o estado do ambiente.
	 */
	public void restaura() {
		pilha.pop();
		pilhaProcedimento.pop();
		pilhaGeneric.pop();
		pilhaTipoReal.pop();
		// pilhaDefClasse.pop(); // nao restaura
	}

	/**
	 * Mapeia um identificador a um tipo.
	 * 
	 * @param idArg
	 *            Identificador
	 * @param tipoId
	 *            Tipo que deve ser associado ao identificador.
	 * @throws VariavelJaDeclaradaException
	 *             quando o id já foi declarado.
	 */
	public void mapTipo(Id idArg, Tipo tipoId)
			throws VariavelJaDeclaradaException {
		HashMap<Id, Tipo> aux = pilha.peek();
		if (aux.put(idArg, tipoId) != null) {
			throw new VariavelJaDeclaradaException(idArg);
		}
	}

	/**
	 * Mapeia um identificador a um tipo generico.
	 * 
	 * @param idArg
	 *            Identificador
	 * @param tipoId
	 *            Tipo que deve ser associado ao identificador.
	 * @throws VariavelJaDeclaradaException
	 */
	public void mapGeneric(Id idArg, Tipo tipoId)
			throws ClasseJaDeclaradaException {
		HashMap<Id, Tipo> aux = pilhaGeneric.peek();
		if (aux.put(idArg, tipoId) != null) {
			throw new ClasseJaDeclaradaException(idArg);
		}
	}

	/**
	 * Mapeia um identificador representando um método aos seus parâmetros.
	 * 
	 * @param idArg
	 *            identificador do método.
	 * @param parametrosId
	 *            Parâmetros do método
	 * @throws ProcedimentoJaDeclaradoException
	 *             quando o procedimento já foi declarado.
	 */
	public void mapParametrosProcedimento(Id idArg,
			ListaDeclaracaoParametro parametrosId)
			throws ProcedimentoJaDeclaradoException {
		HashMap<Id, ListaDeclaracaoParametro> aux = pilhaProcedimento.peek();
		if (aux.put(idArg, parametrosId) != null) {
			throw new ProcedimentoJaDeclaradoException(idArg);
		}
	}

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
			throws ClasseJaDeclaradaException {
		HashMap<Id, DefClasse> aux = pilhaDefClasse.peek();
		if (aux.put(idArg, defClasse) != null) {
			throw new ClasseJaDeclaradaException(idArg);
		}
	}

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
			throws ClasseJaDeclaradaException {
		HashMap<Tipo, Tipo> aux = pilhaTipoReal.peek();
		if (aux.put(tipoGeneric, tipoReal) != null) {
			throw new ClasseJaDeclaradaException(tipoGeneric.getTipo());
		}
	}

	/**
	 * Obtém o tipo associado a um dado identificador
	 * 
	 * @param idArg
	 *            Identificador
	 * @return Tipo associado a um dado identificador
	 * @throws VariavelNaoDeclaradaException
	 *             quando id não foi declarado.
	 */
	public Tipo getTipo(Id idArg) throws VariavelNaoDeclaradaException {
		Tipo result = null;
		Stack<HashMap<Id, Tipo>> auxStack = new Stack<HashMap<Id, Tipo>>();
		while (result == null && !pilha.empty()) {
			HashMap<Id, Tipo> aux = pilha.pop();
			auxStack.push(aux);
			result = aux.get(idArg);
		}
		while (!auxStack.empty()) {
			pilha.push(auxStack.pop());
		}
		if (result == null) {
			throw new VariavelNaoDeclaradaException(idArg);
		} else {
			return result;
		}
	}

	/**
	 * Obtém a tail de parâmetros associada a um identificador que representa
	 * nome do método.
	 * 
	 * @param idArg
	 *            Identificador que representa o nome do método.
	 * @return Lista de parâmetros Lista de parâmetros associada a um
	 *         identificador que representa nome do método.
	 * @throws ProcedimentoNaoDeclaradoException
	 *             quando não foi declarado nenhum método com esse id.
	 */
	public ListaDeclaracaoParametro getParametrosProcedimento(Id idArg)
			throws ProcedimentoNaoDeclaradoException {
		ListaDeclaracaoParametro result = null;
		Stack<HashMap<Id, ListaDeclaracaoParametro>> auxStack = new Stack<HashMap<Id, ListaDeclaracaoParametro>>();
		while (result == null && !pilhaProcedimento.empty()) {
			HashMap<Id, ListaDeclaracaoParametro> aux = pilhaProcedimento.pop();
			auxStack.push(aux);
			result = aux.get(idArg);
		}
		while (!auxStack.empty()) {
			pilhaProcedimento.push(auxStack.pop());
		}
		if (result == null) {
			throw new ProcedimentoNaoDeclaradoException(idArg);
		} else {
			return result;
		}
	}

	/**
	 * Obtém a definição da classe cujo nome é idArg
	 * 
	 * @param idArg
	 *            Nome da classe.
	 * @return a definição da classe.
	 * @throws ClasseNaoDeclaradaException
	 *             quando nao foi declarada nenhuma classe com esse nome.
	 */
	public DefClasse getDefClasse(Id idArg) throws ClasseNaoDeclaradaException {
		DefClasse result = null;
		Stack<HashMap<Id, DefClasse>> auxStack = new Stack<HashMap<Id, DefClasse>>();
		while (result == null && !pilhaDefClasse.empty()) {
			HashMap<Id, DefClasse> aux = pilhaDefClasse.pop();
			auxStack.push(aux);
			result = aux.get(idArg);
		}
		while (!auxStack.empty()) {
			pilhaDefClasse.push(auxStack.pop());
		}
		if (result == null) {
			throw new ClasseNaoDeclaradaException(idArg);
		} else {
			return result;
		}
	}

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
			throws ClasseNaoDeclaradaException {
		Tipo result = null;
		Stack<HashMap<Tipo, Tipo>> auxStack = new Stack<HashMap<Tipo, Tipo>>();
		while (result == null && !pilhaTipoReal.empty()) {
			HashMap<Tipo, Tipo> aux = pilhaTipoReal.pop();
			auxStack.push(aux);
			result = aux.get(tipoGeneric);
		}
		while (!auxStack.empty()) {
			pilhaTipoReal.push(auxStack.pop());
		}
		if (result == null) {
			throw new ClasseNaoDeclaradaException(tipoGeneric.getTipo());
		} else {
			return result;
		}
	}

	/**
	 * Obtém o tipo associado a um dado identificador
	 * 
	 * @param idArg
	 *            Identificador
	 * @return Tipo associado a um dado identificador
	 * @throws VariavelNaoDeclaradaException
	 *             quando id não foi declarado.
	 */
	public Tipo getGeneric(Id idArg) throws ClasseNaoDeclaradaException {
		Tipo result = null;
		Stack<HashMap<Id, Tipo>> auxStack = new Stack<HashMap<Id, Tipo>>();
		while (result == null && !pilhaGeneric.empty()) {
			HashMap<Id, Tipo> aux = pilhaGeneric.pop();
			auxStack.push(aux);
			result = aux.get(idArg);
		}
		while (!auxStack.empty()) {
			pilhaGeneric.push(auxStack.pop());
		}
		if (result == null) {
			throw new ClasseNaoDeclaradaException(idArg);
		} else {
			return result;
		}
	}

	/**
	 * Obtém o tipo da entrada atual para este ambiente.
	 * 
	 * @return o tipo da entrada.
	 * @throws VariavelNaoDeclaradaException
	 *             quando a entrada atual é uma variável não declarada.
	 */
	public Tipo getTipoEntrada() throws VariavelNaoDeclaradaException {
		Tipo aux = entrada.getHead().getTipo(this);
		entrada = (ListaValor) entrada.getTail();
		return aux;
	}

	/**
	 * Obtém a pilha de valores associados a identificadores
	 * 
	 * @return a pilha de valores associados a identificadores.
	 */
	public Stack<HashMap<Tipo, Tipo>> getPilhaTipoTipo() {
		return this.pilhaTipoReal;
	}

	public void write() {
		pilhaDefClasse.toString();
	}

}
