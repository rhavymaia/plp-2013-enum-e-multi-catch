package plp.puma.declaracao.constante;

import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.ListaExpressao;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.ValorEnum;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;


/**
 * Representa a lista de identificadores declarados para um tipo Enum
 * 
 */
public class SimplesDecConstanteEnum implements DecConstanteEnum {

	private Id idConstante;
	
	private ListaExpressao expressoes;
	
	public SimplesDecConstanteEnum(Id idConstante, ListaExpressao expressoes) {
		this.idConstante = idConstante;
		this.expressoes = expressoes;
	}
	
	/**
	 * Verificar se existe constante declarada com o Id.
	 */
	public boolean existeId(Id constante) {
		
		if (this.getIdConstante().equals(constante)) {
			return true;
		}	
	
		return false;
	}
	
	/**
	 * Recuperar a constante e suas variáveis do Id passado como parâmetro.
	 */
	public SimplesDecConstanteEnum getConstanteEnum(Id idConstante)
			throws VariavelNaoDeclaradaException {
		if (this.getIdConstante().equals(idConstante)) {
			return this;
		} else {
			throw new VariavelNaoDeclaradaException(idConstante);
		}
	}
	
	public Tipo getTipo(Id id) throws VariavelNaoDeclaradaException {
		// TODO Auto-generated method stub
		Tipo tipo = null;
		return tipo;
	}

	/**
	 * 	Retornar o valor da constante.
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ObjetoNaoDeclaradoException, ObjetoJaDeclaradoException,
			TryCatchException {
		/*ValorEnum valorEnum = new ValorEnum(idConstante, null);
		ambiente.mapValor(idConstante, valorEnum);*/
		return ambiente;
	}

	/**
	 * Verificar tipo da constante para o Enumerador.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
		// TODO Auto-generated method stub
		boolean resultado = true;
		return resultado;
	}

	public Id getIdConstante() {
		return idConstante;
	}

	public void setIdConstante(Id idConstante) {
		this.idConstante = idConstante;
	}

	public ListaExpressao getExpressoes() {
		return expressoes;
	}

	public void setExpressoes(ListaExpressao expressoes) {
		this.expressoes = expressoes;
	}		
}
