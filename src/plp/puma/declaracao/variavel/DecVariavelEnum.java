package plp.puma.declaracao.variavel;

import plp.puma.comando.ChamadaMetodo;
import plp.puma.comando.New;
import plp.puma.declaracao.comando.AtribuicaoEnum;
import plp.puma.declaracao.constante.SimplesDecConstanteEnum;
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
 * Classe que representa a declaraçao de uma variável do tipo enum.
 */
public class DecVariavelEnum extends DecVariavelObjeto {
	/**
	 * Valor do variável do tipo enumerado
	 */
	private Id valor;

	/**
	 * Construtor.
	 * 
	 * @param tipo
	 *            Tipo declarado da variável.
	 * @param identificador
	 *            Identificador do objeto.
	 * @param classe
	 *            Classe da qual objeto é uma instância.
	 */
	public DecVariavelEnum(Tipo tipo, Id identificador, Id classe, Id valor) {
		super(tipo, identificador, new TipoClasse(classe));
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
		if (this.identificador.equals(id)) {
			return tipo;
		} else {
			throw new VariavelNaoDeclaradaException(id);
		}
	}

	/**
	 * Cria um mapeamento do identificador para o objeto no ambiente de
	 * execução.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela inicialização da variável.
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ObjetoJaDeclaradoException, ObjetoNaoDeclaradoException,
			TryCatchException {

		// Cria o ambiente para armazenar os atributos do tipo Enum
		Id tipoEnum = tipoInstancia.getTipo();

		// Recuperar a definição do enumerador.
		DefEnum defEnum = (DefEnum) ambiente.getDefClasse(tipoEnum);		

		// Tipo defenido para o enum.
		Tipo tipoInstanciaReal = tipoInstancia;

		// Inserir variáveis do Enumerador.
		SimplesDecVariavel decVariavel = new SimplesDecVariavel(
				tipoInstanciaReal, identificador, new ValorNull());
		AmbienteExecucao contextoPrincipal = decVariavel.elabora(ambiente);

		// Construir referência do Enumerador.
		AtribuicaoEnum objetoReferencia = new AtribuicaoEnum(valor, identificador, tipoInstanciaReal);
		contextoPrincipal = objetoReferencia.executar(contextoPrincipal);
		
		// Inserir o valor da constante no contexto de execução do objeto
		ValorRef valorRef = (ValorRef) contextoPrincipal.getValor(identificador.getId());
		Objeto objetoInstacia = contextoPrincipal.getObjeto(valorRef);

		// Recupera a constante definida na declaração..
		SimplesDecConstanteEnum constanteEnum = defEnum.getDecConstanteEnum()
				.getConstanteEnum(valor);
		
		// Atualizar valor das variável através do cons
		ListaExpressao variaveis = constanteEnum.getExpressoes();
		ValorEnum valorEnum = new ValorEnum(valor, variaveis, 
				objetoInstacia);
		
		AmbienteExecucao contextoObjeto = new SimplesDecVariavel(tipo, identificador,
				valorEnum).elabora(objetoInstacia.getEstado());

		ChamadaMetodo chamada = new ChamadaMetodo(identificador, tipoEnum, variaveis);
		try {
			chamada.executar(contextoPrincipal);
		} catch (ProcedimentoNaoDeclaradoException e) {
			e.printStackTrace();
		} catch (ProcedimentoJaDeclaradoException e) {
			e.printStackTrace();
		} catch (EntradaInvalidaException e) {
			e.printStackTrace();
		}
		
		return contextoPrincipal;
	}

	/**
	 * 
	 * Verifica se o tipo da classe associada é válido (se existe).
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
			ambiente.mapTipo(identificador, tipoInstancia);
		}
		return resposta;
	}
}