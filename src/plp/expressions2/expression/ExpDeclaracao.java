package plp.expressions2.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plp.expressions1.util.Tipo;
import plp.expressions2.declaration.DecVariavel;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpDeclaracao implements Expressao {

	List<DecVariavel> seqdecVariavel;
	Expressao expressao;

	public ExpDeclaracao(List<DecVariavel> declarations, Expressao expressaoArg) {
		seqdecVariavel = declarations;
		System.out.println(seqdecVariavel);
		expressao = expressaoArg;
	}

	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		ambiente.incrementa();
		Map<Id, Valor> resolvedValues = resolveValueBindings(ambiente);
		// Torna a avalia��o sequencial
		// includeValueBindings(ambiente, resolvedValues);
		Valor result = expressao.avaliar(ambiente);
		ambiente.restaura();

		return result;
	}

	private void includeValueBindings(AmbienteExecucao ambiente,
			Map<Id, Valor> resolvedValues) throws VariavelJaDeclaradaException {
		for (Id id : resolvedValues.keySet()) {
			Valor valor = (Valor) resolvedValues.get(id);
			ambiente.map(id, valor);
		}
	}

	private Map<Id, Valor> resolveValueBindings(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Map<Id, Valor> resolvedValues = new HashMap<Id, Valor>();

		for (DecVariavel declaration : this.seqdecVariavel) {
			System.out.println("Declararion " + declaration.getID());
			Valor valor = (Valor) declaration.getExpressao().avaliar(ambiente);
			resolvedValues.put(declaration.getID(), valor);
			// No caso da declara��o sequencial o valor � adicionado ao ambiente
			// depois
			// de avaliado
			ambiente.map(declaration.getID(), valor);

		}
		return resolvedValues;
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param amb
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *         <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ambiente.incrementa();
		Map<Id, Tipo> resolvedTypes;
		boolean result = false;
		try {
			if (this.checkTypeBindings(ambiente)) {
				resolvedTypes = this.resolveTypeBindings(ambiente);
				this.includeTypeBindings(ambiente, resolvedTypes);
				result = expressao.checaTipo(ambiente);
			} else {
				result = false;
			}
		} finally {
			ambiente.restaura();
		}
		return result;
	}

	private void includeTypeBindings(AmbienteCompilacao ambiente,
			Map<Id, Tipo> resolvedTypes) throws VariavelJaDeclaradaException {

		for (Id id : resolvedTypes.keySet()) {
			Tipo type = (Tipo) resolvedTypes.get(id);
			ambiente.map(id, type);
		}
	}

	private Map<Id, Tipo> resolveTypeBindings(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Map<Id, Tipo> resolvedTypes = new HashMap<Id, Tipo>();

		for (DecVariavel declaration : this.seqdecVariavel) {
			if (resolvedTypes.put(declaration.getID(), declaration
					.getExpressao().getTipo(ambiente)) != null) {
				throw new VariavelJaDeclaradaException(declaration.getID());
			}

		}

		return resolvedTypes;
	}

	private boolean checkTypeBindings(AmbienteCompilacao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {

		boolean result = true;
		DecVariavel decVariavel;

		for (DecVariavel declaration : this.seqdecVariavel) {
			if (!declaration.getExpressao().checaTipo(ambiente)) {
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param amb
	 *            o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ambiente.incrementa();

		Map<Id, Tipo> resolvedTypes = this.resolveTypeBindings(ambiente);
		this.includeTypeBindings(ambiente, resolvedTypes);
		Tipo result = expressao.getTipo(ambiente);

		ambiente.restaura();
		return result;
	}

}