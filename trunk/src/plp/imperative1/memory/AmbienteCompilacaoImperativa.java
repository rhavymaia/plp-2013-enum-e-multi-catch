package plp.imperative1.memory;

import plp.expressions1.util.Tipo;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public interface AmbienteCompilacaoImperativa extends AmbienteCompilacao {

	public Tipo getTipoEntrada() throws VariavelNaoDeclaradaException,
			VariavelJaDeclaradaException, EntradaVaziaException;

}
