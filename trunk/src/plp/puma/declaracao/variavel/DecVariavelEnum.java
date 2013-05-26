package plp.puma.declaracao.variavel;

import plp.puma.comando.New;
import plp.puma.declaracao.constante.SimplesDecConstanteEnum;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ConstanteEnumNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.ValorNull;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.DefEnum;
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
	 * @param objeto
	 *            Identificador do objeto.
	 * @param classe
	 *            Classe da qual objeto é uma instância.
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
	 * execução.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela inicialização da variável.
	 */
	//TODO: Verificar alocação na memória: DecConstanteEnum e atualização de variáveis
	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ObjetoJaDeclaradoException, ObjetoNaoDeclaradoException,
			TryCatchException {
		
		// Cria o ambiente para armazenar os atributos do tipo Enum		
		Id tipoEnum = tipoInstancia.getTipo();	
		
		// Recuperar a definição do enumerador.
		DefEnum defEnum = (DefEnum) ambiente.getDefClasse(tipoEnum);
		
		SimplesDecConstanteEnum constanteEnum = null;
		
		try {
			constanteEnum = defEnum.getDecConstanteEnum().getConstanteEnum(valor);
		} catch (ConstanteEnumNaoDeclaradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Cria o ValorEnum contendo o valor instanciado e o Objeto contendo o
		// ambiente com os atributos
		Tipo tipoInstanciaReal = tipoInstancia;
		
		SimplesDecVariavel decVariavel = new SimplesDecVariavel(tipoInstanciaReal, objeto,
				new ValorNull());
		AmbienteExecucao aux = decVariavel.elabora(ambiente);
		constanteEnum.elabora(ambiente);
		
		New newObjeto = new New(objeto, tipoInstanciaReal);
		aux = newObjeto.executar(aux);
		
		return aux;
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
			ambiente.mapTipo(objeto, tipoInstancia);
		}
		return resposta;
	}
}