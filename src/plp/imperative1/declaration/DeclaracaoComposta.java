package plp.imperative1.declaration;

import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;

public class DeclaracaoComposta extends Declaracao<Declaracao, Declaracao> {

	public DeclaracaoComposta(Declaracao declaracao1, Declaracao declaracao2) {
		super(declaracao1, declaracao2);
	}

	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return parametro2.elabora(parametro1.elabora(ambiente));
	}

	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return parametro1.checaTipo(ambiente) && parametro2.checaTipo(ambiente);
	}
}
