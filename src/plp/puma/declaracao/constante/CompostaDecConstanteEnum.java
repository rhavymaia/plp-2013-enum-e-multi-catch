package plp.puma.declaracao.constante;

import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ConstanteEnumNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;

public class CompostaDecConstanteEnum implements DecConstanteEnum{
	
	private DecConstanteEnum constante1;
	
	private DecConstanteEnum constante2;
	
	public CompostaDecConstanteEnum(DecConstanteEnum constante1, DecConstanteEnum constante2) {
		
		this.constante1 = constante1;
		this.constante2 = constante2;
	}

	public SimplesDecConstanteEnum getConstanteEnum(Id idConstante)
			throws ConstanteEnumNaoDeclaradaException {
		SimplesDecConstanteEnum procedimento;
		try {
			procedimento = this.constante1.getConstanteEnum(idConstante);
		} catch (ConstanteEnumNaoDeclaradaException e) {
			procedimento = this.constante2.getConstanteEnum(idConstante);
		}
		return procedimento;
	}
	
	public Tipo getTipo(Id id) throws VariavelNaoDeclaradaException {
		// TODO Auto-generated method stub
		return null;
	}

	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ObjetoNaoDeclaradoException, ObjetoJaDeclaradoException,
			TryCatchException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean existeId(Id constante) {
		
		if (this.getConstante1().equals(constante) 
				|| this.getConstante2().equals(constante)) {
			return true;
		}	
		return false;
	}

	public DecConstanteEnum getConstante1() {
		return constante1;
	}

	public void setConstante1(DecConstanteEnum constante1) {
		this.constante1 = constante1;
	}

	public DecConstanteEnum getConstante2() {
		return constante2;
	}

	public void setConstante2(DecConstanteEnum constante2) {
		this.constante2 = constante2;
	}
}
