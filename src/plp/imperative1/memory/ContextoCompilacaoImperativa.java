package plp.imperative1.memory;

import plp.expressions1.util.Tipo;
import plp.expressions2.memory.ContextoCompilacao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ContextoCompilacaoImperativa extends ContextoCompilacao implements
		AmbienteCompilacaoImperativa {

	/**
	 * A pilha de blocos de contexto.
	 */
	private ListaValor entrada;

	/**
	 * Construtor da classe.
	 */
	public ContextoCompilacaoImperativa(ListaValor entrada) {
		super();
		this.entrada = entrada;
	}

	public Tipo getTipoEntrada() throws VariavelNaoDeclaradaException,
			VariavelJaDeclaradaException, EntradaVaziaException {
		if (entrada == null || entrada.getHead() == null) {
			throw new EntradaVaziaException();
		}
		Tipo aux = entrada.getHead().getTipo(this);
		entrada = (ListaValor) entrada.getTail();
		return aux;
	}

}
