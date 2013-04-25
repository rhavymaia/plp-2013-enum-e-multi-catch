package plp.expressions1.expression;

import plp.expressions1.util.Tipo;

public class ExpAscii extends ExpUnaria {

	public ExpAscii(Expressao exp) {
		super(exp, "ascii");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean checaTipoElementoTerminal() {
		return (getExp().getTipo().eCharacter());
	}

	public Valor avaliar() {
		return new ValorInteiro(((ValorCharacter) getExp().avaliar()).valor()
				.charValue());
	}

	public Tipo getTipo() {
		return Tipo.TIPO_INTEIRO;
	}

}
