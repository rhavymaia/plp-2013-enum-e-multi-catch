package plp.puma.declaracao.constante;

import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.ListaExpressao;

public class ConstanteEnum {

	private Id id;

	private ListaExpressao expressoes;
	
	public ConstanteEnum(Id nomeMetodo, ListaExpressao expressoes) {
		this.id = nomeMetodo;
		this.expressoes = expressoes;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public ListaExpressao getExpressoes() {
		return expressoes;
	}

	public void setExpressoes(ListaExpressao expressoes) {
		this.expressoes = expressoes;
	}	
	
	@Override
	public boolean equals(Object contanteEnum) {		
		
		return this.id.equals(((ConstanteEnum) contanteEnum).getId());
	}
}
