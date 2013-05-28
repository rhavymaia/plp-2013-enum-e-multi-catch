package plp.puma.declaracao.variavel;

import plp.puma.comando.ChamadaMetodo;
import plp.puma.comando.New;
import plp.puma.declaracao.constante.SimplesDecConstanteEnum;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ConstanteEnumNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.excecao.execucao.EntradaInvalidaException;
import plp.puma.expressao.ListaExpressao;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.ValorEnum;
import plp.puma.expressao.valor.ValorNull;
import plp.puma.expressao.valor.ValorRef;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.DefEnum;
import plp.puma.memoria.Objeto;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasse;

/**
 * Classe que representa a declara�ao de uma vari�vel do tipo enum.
 */
public class DecVariavelEnum extends DecVariavelObjeto {
	/**
	 * Valor do vari�vel do tipo enumerado
	 */
	private Id valor;

	/**
	 * Construtor.
	 * 
	 * @param tipo
	 *            Tipo declarado da vari�vel.
	 * @param objeto
	 *            Identificador do objeto.
	 * @param classe
	 *            Classe da qual objeto � uma inst�ncia.
	 */
	public DecVariavelEnum(Tipo tipo, Id objeto, Id classe, Id valor) {
		super(tipo, objeto, new TipoClasse(classe));
		this.valor = valor;
	}

	/**
	 * Retorna o tipo do identificador a ser declarado no AmbienteCompilacao
	 * 
	 * @param id
	 *            o identificador da declaracao
	 * @return o tipo do identificador
	 */
	public Tipo getTipo(Id id) throws VariavelNaoDeclaradaException {
		if (this.objeto.equals(id)) {
			return tipo;
		} else {
			throw new VariavelNaoDeclaradaException(id);
		}
	}

	/**
	 * Cria um mapeamento do identificador para o objeto no ambiente de
	 * execu��o.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela inicializa��o da vari�vel.
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ObjetoJaDeclaradoException, ObjetoNaoDeclaradoException,
			TryCatchException {

		// Cria o ambiente para armazenar os atributos do tipo Enum
		Id tipoEnum = tipoInstancia.getTipo();

		// Recuperar a defini��o do enumerador.
		DefEnum defEnum = (DefEnum) ambiente.getDefClasse(tipoEnum);		

		// Tipo defenido para o enum.
		Tipo tipoInstanciaReal = tipoInstancia;

		// Inserir vari�veis do Enumerador.
		SimplesDecVariavel decVariavel = new SimplesDecVariavel(
				tipoInstanciaReal, objeto, new ValorNull());
		AmbienteExecucao contextoPrincipal = decVariavel.elabora(ambiente);

		// Recupera a constante definida na declara��o..
		SimplesDecConstanteEnum constanteEnum = defEnum.getDecConstanteEnum()
				.getConstanteEnum(valor);
		constanteEnum.elabora(ambiente);

		// Construir refer�ncia do Enumerador.
		New objetoReferencia = new New(objeto, tipoInstanciaReal);
		contextoPrincipal = objetoReferencia.executar(contextoPrincipal);
		
		// Inserir o valor da constante no contexto de execu��o do objeto
		Objeto objetoInstacia = contextoPrincipal.getObjeto(
				(ValorRef) contextoPrincipal.getValor(objeto.getId()));

		ListaExpressao variaveis = constanteEnum.getExpressoes();
		ValorEnum valorEnum = new ValorEnum(valor, variaveis, 
				objetoInstacia);
		
		AmbienteExecucao contextoObjeto = new SimplesDecVariavel(tipo, objeto,
				valorEnum).elabora(objetoInstacia.getEstado());

		ChamadaMetodo chamada = new ChamadaMetodo(objeto, tipoEnum, variaveis);
		try {
			chamada.executar(contextoPrincipal);
		} catch (ProcedimentoNaoDeclaradoException e) {
			e.printStackTrace();
		} catch (ProcedimentoJaDeclaradoException e) {
			e.printStackTrace();
		} catch (EntradaInvalidaException e) {
			e.printStackTrace();
		}		
		
		contextoPrincipal.changeValor(objeto, valorEnum);
		return contextoPrincipal;
	}

	/**
	 * 
	 * Verifica se o tipo da classe associada � v�lido (se existe).
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre objetos e suas
	 *            classes.
	 * @return <code>true</code> a classe existe <code>false</code> caso
	 *         contrario.
	 * 
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
		boolean resposta = false;
		if (tipoInstancia.eValido(ambiente) && tipo.eValido(ambiente)) {
			resposta = tipoInstancia.equals(tipo);
			ambiente.mapTipo(objeto, tipoInstancia);
		}
		return resposta;
	}
}