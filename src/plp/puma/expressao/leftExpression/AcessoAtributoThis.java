package plp.puma.expressao.leftExpression;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
import plp.puma.expressao.This;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorEnum;
import plp.puma.expressao.valor.ValorRef;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.DefClasse;
import plp.puma.memoria.DefEnum;
import plp.puma.memoria.Objeto;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasse;

/**
 * Representa um acesso de atributo a partir de um objeto this.
 */
public class AcessoAtributoThis extends AcessoAtributo {
	/**
	 * O objeto this.
	 */
	private This varThis;

	/**
	 * Construtor.
	 * 
	 * @param varThis
	 *            O objeto this.
	 * @param id
	 *            O identificador sendo acessado.
	 */
	public AcessoAtributoThis(This varThis, Id id) {
		super(id);
		this.varThis = varThis;
	}

	/**
	 * O valor do atributo acessado no ambiente.
	 * 
	 * @param ambiente
	 *            o ambiente contendoo mapeamento de identificadores a valores.
	 * @return o valor do atributo acessado.
	 * @throws VariavelNaoDeclaradaException
	 * @throws VariavelJaDeclaradaException
	 * @throws ObjetoNaoDeclaradoException
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return obterValorDeIdNoAmbiente(ambiente);
	}

	/**
	 * Obtém a expressão que acessa o identificador.
	 * 
	 * @return a expressao que acessa o identificador.
	 */
	public Expressao getExpressaoObjeto() {
		return varThis;
	}

	/**
	 * Verifica se o this está associado a um objeto e se o atributo existe.
	 * 
	 * @param ambiente
	 *            o ambiente com o mapeamento de identificadores a tipos.
	 * @return true, se o this está associado a um objeto e se o atributo
	 *         existe, ou false, caso contrário.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente) {
		boolean resposta = false;
		try {
			resposta = varThis.checaTipo(ambiente);
			if (resposta) {
				Tipo tipo = varThis.getTipo(ambiente);
				DefClasse defClasse = ambiente.getDefClasse(tipo.getTipo());
				// Verifica se existe o identificador
				if (defClasse instanceof DefEnum)
					resposta = ((DefEnum) defClasse).existeId(super.getId());
				// Se não existe identificador ou se não é enumerado então busca
				// atributo
				if (resposta == false)
					defClasse.getTipoAtributo(super.getId());
				resposta = true;
			}
		} catch (VariavelNaoDeclaradaException atrib) {
			resposta = false;
		} catch (ClasseNaoDeclaradaException clas) {
			resposta = false;
		}
		return resposta;
	}

	/**
	 * Obtém o tipo do atributo acessado.
	 * 
	 * @param ambiente
	 *            o ambiente com o mapeamento de identificadores a tipos.
	 * @return true, se foi associado um tipo a esse identificador acessado no
	 *         escopo corrente.
	 * @throws VariavelNaoDeclaradaException
	 * @throws ClasseNaoDeclaradaException
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
		// Logo abaixo obtenho a definicao da Classe (seus métodos e atributos).
		// this.getTipo() devera retornar uma instancia de TipoClasse e assim,
		// TipoClasse.getTipo()
		// retorna o id (contendo o nome da classe) associado ao tipo dela
		TipoClasse tpClasse = (TipoClasse) varThis.getTipo(ambiente);
		Id nomeClasse = tpClasse.getTipo();
		DefClasse defClasse = ambiente.getDefClasse(nomeClasse);
		// Caso enumerado verifica se o .id é um identificador
		if ((defClasse instanceof DefEnum)
				&& ((DefEnum) defClasse).existeId(super.getId()))
			return tpClasse;
		// Em seguida retorno o tipo do atributo, caso ele esteja definido na
		// classe.
		// caso não esteja, uma exceção será lançada
		return defClasse.getTipoAtributo(super.getId());
	}

	/**
	 * Retorna o valor do Objeto representado por um certo id
	 * 
	 * @param ambiente
	 *            é o Ambiente de Execução
	 * @return o valor do Objeto representado por um certo id
	 */
	private Valor obterValorDeIdNoAmbiente(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		Valor valor = varThis.avaliar(ambiente); // recupera o id do objeto
		Objeto objeto = null;
		if (valor.eReferencia())
			objeto = ambiente.getObjeto((ValorRef) valor);
		else if (((ValorEnum) valor).existeId(ambiente, super.getId())) // Verifica
																		// se
																		// existe
																		// o
																		// identificador
																		// no
																		// tipo
																		// enumerado
			return valor;
		else
			objeto = ((ValorEnum) valor).getObjeto(); // Senão o .id é um
														// atributo cujo valor é
														// recuperado
		// Recuperando o mapeamento de valores do objeto (atributos do objeto)
		AmbienteExecucao aux = objeto.getEstado();
		// Recuperando o valor do atributo "id" do objeto
		return aux.getValor(super.getId());
	}
}