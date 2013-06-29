package plp.puma.memoria;

import plp.puma.expressao.leftExpression.Id;

public class ObjetoEnum extends Objeto {

	private Id constante;
	
	public ObjetoEnum(Id classeObjeto, AmbienteExecucao estado) {
		super(classeObjeto, estado);
	}
	
	public ObjetoEnum(Id constante, Id classeObjeto, AmbienteExecucao estado) {
		
		super(classeObjeto, estado);
		
		this.constante = constante;
	}

	public Id getConstante() {
		return constante;
	}

	public void setConstante(Id constante) {
		this.constante = constante;
	}

}
