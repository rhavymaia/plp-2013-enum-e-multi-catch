package plp.puma.declaracao.constante;

import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;


public class DecConstanteEnum implements DecVariavel{

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
	
	
}
