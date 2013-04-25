package plp.puma.memoria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import plp.puma.expressao.valor.ValorBooleano;
import plp.puma.expressao.valor.ValorInteiro;
import plp.puma.expressao.valor.ValorNull;
import plp.puma.expressao.valor.ValorRef;
import plp.puma.expressao.valor.ValorString;
import plp.puma.memoria.colecao.ListaValor;
import plp.puma.util.Tipo;
import plp.puma.util.TipoPrimitivo;

public class ContextoExecucao implements AmbienteExecucao {

	/**
	 * A pilha de blocos de contexto.
	 */
	private Stack<HashMap<Id, Valor>> pilha;

	/**
	 * A pilha de tipos genericos para tipos reais.
	 */
	private Stack<HashMap<Tipo, Tipo>> pilhaTipoReal;

	/**
	 * A pilha de classes do contexto.
	 */
	private Stack<HashMap<Id, DefClasse>> pilhaDefClasse;

	/**
	 * A pilha de objetos de contexto.
	 */

	private Stack<HashMap<ValorRef, Objeto>> pilhaObjeto;

	/**
	 * A pilha de blocos de contexto.
	 */
	private ListaValor entrada;

	/**
	 * A pilha de blocos de contexto.
	 */
	private ListaValor saida;

	/**
	 * A refer�ncia do objeto a ser inserido na pilha de objetos
	 */
	private ValorRef proxRef;

	/**
	 * Representa o nome do campo que armazena os valores nos tipos
	 * pr�-definidos
	 */
	private static Id ATRIBUTO_VALOR = new Id("valor");

	/**
	 * Construtor utilizado quando queremos ler do teclado.
	 */
	public ContextoExecucao() {
		pilha = new Stack<HashMap<Id, Valor>>();
		pilhaTipoReal = new Stack<HashMap<Tipo, Tipo>>();

		pilhaObjeto = new Stack<HashMap<ValorRef, Objeto>>(); // esta
																// pilha nao
																// cresce
		pilhaObjeto.push(new HashMap<ValorRef, Objeto>()); // tem de
															// incrementar
															// no come�o

		pilhaDefClasse = new Stack<HashMap<Id, DefClasse>>(); // esta
																// pilha nao
																// cresce
		pilhaDefClasse.push(new HashMap<Id, DefClasse>()); // tem de
															// incrementar
															// no come�o

		this.entrada = null;
		this.saida = new ListaValor();
	}

	/**
	 * Construtor da classe.
	 */
	public ContextoExecucao(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException {
		proxRef = ambiente.getRef();
		this.pilhaObjeto = ambiente.getPilhaObjeto();
		this.pilhaDefClasse = ambiente.getPilhaDefClasse();
		this.entrada = ambiente.getEntrada();
		this.saida = ambiente.getSaida();
		pilhaTipoReal = ambiente.getPilhaTipoTipo();
		pilha = new Stack<HashMap<Id, Valor>>();
		HashMap<Id, Valor> aux = new HashMap<Id, Valor>();
		aux.put(new Id("this"), new ValorNull());
		pilha.push(aux);

	}

	/**
	 * Construtor.
	 * 
	 * @param entrada
	 *            Entrada para o contexto de execu��o.
	 */
	public ContextoExecucao(ListaValor entrada) {
		pilha = new Stack<HashMap<Id, Valor>>();
		pilhaTipoReal = new Stack<HashMap<Tipo, Tipo>>();

		pilhaObjeto = new Stack<HashMap<ValorRef, Objeto>>(); // esta
																// pilha nao
																// cresce
		pilhaObjeto.push(new HashMap<ValorRef, Objeto>()); // tem de
															// incrementar
															// no come�o

		pilhaDefClasse = new Stack<HashMap<Id, DefClasse>>(); // esta
																// pilha nao
																// cresce
		pilhaDefClasse.push(new HashMap<Id, DefClasse>()); // tem de
															// incrementar
															// no come�o

		this.entrada = entrada;
		this.saida = new ListaValor();
	}

	/**
	 * Obt�m a pilha de valores associados a identificadores
	 * 
	 * @return a pilha de valores associados a identificadores.
	 */
	public Stack<HashMap<Id, Valor>> getPilha() {
		return this.pilha;
	}

	/**
	 * Obt�m a pilha de valores associados a identificadores
	 * 
	 * @return a pilha de valores associados a identificadores.
	 */
	public Stack<HashMap<Tipo, Tipo>> getPilhaTipoTipo() {
		return this.pilhaTipoReal;
	}

	/**
	 * Retorna a pilha com as defini�oes das classes.
	 * 
	 * @return a pilha com as defini�oes das classes.
	 */
	public Stack<HashMap<Id, DefClasse>> getPilhaDefClasse() {
		return this.pilhaDefClasse;
	}

	/**
	 * Obt�m a pilha com os objetos e seus valores.
	 * 
	 * @return a pilha com os objetos e seus valores.
	 */
	public Stack<HashMap<ValorRef, Objeto>> getPilhaObjeto() {
		return this.pilhaObjeto;
	}

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
	public Valor read(Tipo tipoIdLido) throws EntradaInvalidaException {
		String valorLido = leEntrada();
		if (valorLido != null) {
			valorLido = valorLido.trim();
			if (tipoIdLido instanceof TipoPrimitivo) {
				TipoPrimitivo tipo = (TipoPrimitivo) tipoIdLido;
				try {
					if (tipo.eBooleano()) {
						return new ValorBooleano(Boolean.getBoolean(valorLido));
					} else if (tipo.eInteiro()) {
						return new ValorInteiro(Integer.parseInt(valorLido));
					} else if (tipo.eString()) {
						return new ValorString(valorLido);
					}
				} catch (NumberFormatException e) {
					throw new EntradaInvalidaException(
							"O tipo da entrada e o da vari�vel"
									+ " a ser lida s�o diferentes!");
				}
			}
		}
		throw new EntradaInvalidaException(
				"O tipo da vari�vel a ser lida n�o � um tipo Primitivo!");
	}

	/**
	 * Este m�todo l� uma entrada que pode ser de uma tail ou do teclado
	 * 
	 * @return Obt�m uma entrada que pode ser de uma tail ou do teclado
	 * @exception Lan�a
	 *                uma exce��o se a tail com os valores nao tiver mais
	 *                elementos.
	 */
	private String leEntrada() throws EntradaInvalidaException {
		if (this.entrada == null) {
			return leDaEntradaPadrao();
		} else {
			// Se nao tivermos mais nada na tail de valores
			if (entrada.length() == 0) {
				throw new EntradaInvalidaException(
						"N�mero de argumentos menor do que o n�mero de reads!");
			}
			return leDaListaValor();
		}
	}

	/**
	 * Este m�todo l� da entrada padr�o
	 * 
	 * @return o que o usu�rio digitou na entrada padr�o
	 */
	private String leDaEntradaPadrao() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			return in.readLine();
		} catch (IOException e) {
			System.out.println("Erro no valor lido da entrada padr�o");
		}
		return "";
	}

	/**
	 * Este m�todo l� da entrada padr�o
	 * 
	 * @return o que o usu�rio digitou na entrada padr�o
	 */
	private String leDaListaValor() {
		String retorno = entrada.getHead().toString();
		entrada = (ListaValor) entrada.getTail();
		return retorno;
	}

	/**
	 * Obt�m a sa�da.
	 * 
	 * @return a sa�da.
	 */
	public ListaValor getSaida() {
		return saida;
	}

	/**
	 * Obt�m a entrada.
	 * 
	 * @return a entrada.
	 */
	public ListaValor getEntrada() {
		return entrada;
	}

	/**
	 * Escreve um valor 'v' na sa�da.
	 * 
	 * @param v
	 *            O valor a ser escrito.
	 * @return o ambiente de execu��o, que representa o estado atual.
	 */
	public AmbienteExecucao write(Valor v) {
		saida.write(v);
		return this;
	}

	/**
	 * Incrementa a pilha do ambiente, passando para o pr�ximo estado.
	 */
	public void incrementa() {
		pilha.push(new HashMap<Id, Valor>());
		pilhaTipoReal.push(new HashMap<Tipo, Tipo>());
		// pilhaDefClasse.push(new HashIdDefClasse()); // s� incrementa no
		// construtor
		// pilhaObjeto.push(new HashValorObjeto()); // s� incrementa no
		// construtor
	}

	/**
	 * Restaura o estado do ambiente.
	 */
	public void restaura() {
		pilha.pop();
		pilhaTipoReal.pop();
		// pilhaDefClasse.pop(); // n�o restaura
		// pilhaObjeto.pop(); // n�o restaura
	}

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
			throws VariavelJaDeclaradaException {
		HashMap<Id, Valor> aux = pilha.peek();
		if (aux.put(idArg, valorId) != null) {
			throw new VariavelJaDeclaradaException(idArg);
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
			throws ClasseJaDeclaradaException {
		HashMap<Id, DefClasse> aux = pilhaDefClasse.peek();
		if (aux.put(idArg, defClasse) != null) {
			throw new ClasseJaDeclaradaException(idArg);
		}
	}

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
			throws ObjetoJaDeclaradoException {
		HashMap<ValorRef, Objeto> aux = pilhaObjeto.peek();
		if (aux.put(valorRef, objeto) != null) {
			throw new ObjetoJaDeclaradoException(objeto.getClasse());
		}
	}

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
			throws VariavelNaoDeclaradaException {
		Valor result = null;
		Stack<HashMap<Id, Valor>> auxStack = new Stack<HashMap<Id, Valor>>();
		while (result == null && !pilha.empty()) {
			HashMap<Id, Valor> aux = pilha.pop();
			auxStack.push(aux);
			result = aux.get(idArg);
			if (result != null) {
				aux.put(idArg, valorId);
				break;
			}
		}
		while (!auxStack.empty()) {
			pilha.push(auxStack.pop());
		}
		if (result == null) {
			throw new VariavelNaoDeclaradaException(idArg);
		}
	}

	/**
	 * Obt�m o valor associado a um determinado identificador.
	 * 
	 * @param idArg
	 *            Identificador
	 * @return o valor associado a um determinado identificador.
	 * @throws VariavelNaoDeclaradaException
	 *             Quando a vari�vel n�o foi declarada.
	 */
	public Valor getValor(Id idArg) throws VariavelNaoDeclaradaException {
		Valor result = null;
		Stack<HashMap<Id, Valor>> auxStack = new Stack<HashMap<Id, Valor>>();
		while (result == null && !pilha.empty()) {
			HashMap<Id, Valor> aux = pilha.pop();
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
	 * Obt�m o tipo real associado ao tipo generico.
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
	 * Obt�m a defini��o da classe cujo nome � idArg
	 * 
	 * @param idArg
	 *            Nome da classe.
	 * @return a defini��o da classe.
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
	 * Obt�m o objeto associado a um dado valor referencia.
	 * 
	 * @param valorRef
	 *            Valor refer�ncia
	 * @return o objeto associado a um dado valor referencia.
	 * @throws ObjetoNaoDeclaradoException
	 *             Quando o objeto n�o foi declarado.
	 */
	public Objeto getObjeto(ValorRef valorRef)
			throws ObjetoNaoDeclaradoException {
		Objeto result = null;
		Stack<HashMap<ValorRef, Objeto>> auxStack = new Stack<HashMap<ValorRef, Objeto>>();
		while (result == null && !pilhaObjeto.empty()) {
			HashMap<ValorRef, Objeto> aux = pilhaObjeto.pop();
			auxStack.push(aux);
			result = aux.get(valorRef);
		}
		while (!auxStack.empty()) {
			pilhaObjeto.push(auxStack.pop());
		}
		if (result == null) {
			throw new ObjetoNaoDeclaradoException(new Id(valorRef.toString()));
		} else {
			return result;
		}
	}

	/**
	 * Obt�m a pr�xima refer�ncia de acordo com o contexto atual de execu��o.
	 * 
	 * @return a pr�xima refer�ncia de acordo com o contexto atual de execu��o.
	 */
	public ValorRef getProxRef() {
		ValorRef aux = new ValorRef(proxRef.valor());
		proxRef = proxRef.incrementa();
		return aux;
	}

	/**
	 * Obt�m o valor referencia atual.
	 * 
	 * @return o valor referencia atual.
	 */
	public ValorRef getRef() {
		if (proxRef == null)
			proxRef = new ValorRef(ValorRef.VALOR_INICIAL);
		return proxRef;
	}

	/**
	 * Retorna a representa��o textual do contexto de execu��o.
	 * 
	 * @return a representa��o textual do contexto de execu��o.
	 */
	public String toString() {
		String resposta = null;
		Valor valor = null;
		Objeto objeto = null;
		Stack<HashMap<Id, Valor>> auxStack = new Stack<HashMap<Id, Valor>>();
		Stack<HashMap<ValorRef, Objeto>> auxStackObjeto = new Stack<HashMap<ValorRef, Objeto>>();

		while (!pilha.empty()) {
			HashMap<Id, Valor> aux = pilha.pop();
			auxStack.push(aux);
			for (Id id : aux.keySet()) {
				valor = aux.get(id);
				resposta = id + " " + valor + "\n";
			}
		}
		while (!auxStack.empty()) {
			pilha.push(auxStack.pop());
		}
		while (!pilhaObjeto.empty()) {
			HashMap<ValorRef, Objeto> aux = pilhaObjeto.pop();
			auxStackObjeto.push(aux);
			for (ValorRef valorRef : aux.keySet()) {
				objeto = aux.get(valorRef);
				resposta = valorRef + " " + objeto + "\n";
			}
		}
		while (!auxStackObjeto.empty()) {
			pilhaObjeto.push(auxStackObjeto.pop());
		}
		return resposta;
	}

	/**
	 * Obt�m um novo contexto de execu��o com a mesma entrada, sa�da e pilha de
	 * mapeamentos id/valor.
	 * 
	 * @return um novo contexto de execu��o com a mesma entrada, sa�da e pilha
	 *         de mapeamentos id/valor.
	 */
	public ContextoExecucao getContextoIdValor() {
		ContextoExecucao ambiente = new ContextoExecucao(this.getEntrada());
		ambiente.pilha = this.pilha;
		ambiente.saida = this.saida;
		return ambiente;
	}
}