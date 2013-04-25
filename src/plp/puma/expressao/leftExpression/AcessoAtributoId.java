package plp.puma.expressao.leftExpression;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
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
 * Classe que representa um acesso de atributo a partir de uma expressao.
 */
public class AcessoAtributoId extends AcessoAtributo {
	/**
	 * Expressao que acessa o atributo.
	 */
	private LeftExpression av;

	/**
	 * Construtor.
	 * 
	 * @param av
	 *            Expressao do lado esquerdo, que acessa o atributo.
	 * @param id
	 *            O atributo sendo acessado.
	 */
	public AcessoAtributoId(LeftExpression av, Id id) {
		super(id);
		this.av = av;
	}

	/**
	 * Avalia esse acesso de atributo obtendo o valor do atributo no ambiente.
	 * 
	 * @param ambiente
	 *            o ambiente de execução, que apresenta o mapeamento de
	 *            identificadores a valores.
	 * @return o valor do atributo acessado no ambiente.
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
	 * Obtém a expressão acessadora do atributo.
	 * 
	 * @return a expressão acessadora do atributo.
	 */
	public Expressao getExpressaoObjeto() {
		return av;
	}

	/**
	 * Verifica se os atributos associados foram declarados e se seus tipos
	 * existem no ambiente.
	 * 
	 * @param ambiente
	 *            o ambiente de compilação, com o mapeamento de identificadores
	 *            a tipos.
	 * @return true, se as variáveis acessadas já foram declaradas e seus tipos
	 *         existem.
	 * @throws VariavelNaoDeclaradaException
	 * @throws ClasseNaoDeclaradaException
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
		boolean resposta = false;
		if (av.checaTipo(ambiente)) {
			try {
				Tipo t = av.getTipo(ambiente);
				DefClasse defClasse = ambiente.getDefClasse(((TipoClasse) t)
						.getTipo());
				// Verifica se existe o identificador
				if (defClasse instanceof DefEnum)
					resposta = ((DefEnum) defClasse).existeId(super.getId());
				// Se não existe identificador ou se não é enumerado então busca
				// atributo
				if (resposta == false)
					defClasse.getTipoAtributo(super.getId());
				resposta = true;
			} catch (VariavelNaoDeclaradaException atrib) {
				resposta = false;
			} catch (ClasseNaoDeclaradaException clas) {
				resposta = false;
			}

		}
		return resposta;
	}

	/**
	 * Obtém o tipo do atributo no ambiente ou retorna o tipo da classe no caso
	 * de enumerados.
	 * 
	 * @param ambiente
	 *            que apresenta o mapeamento de identificadores a tipos.
	 * @return o tipo do atributo acessado.
	 * @throws VariavelNaoDeclaradaException
	 * @throws ClasseNaoDeclaradaException
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
		// Logo abaixo obtenho a definicao da Classe (seus métodos e atributos).
		// av.getTipo devera retornar uma instancia de TipoClasse e assim,
		// TipoClasse.getTipo()
		// retorna o id (contendo o nome da classe) associado ao tipo dela
		TipoClasse tpClasse = (TipoClasse) av.getTipo(ambiente);
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
	 * Obtém a expressao que acessa o atributo.
	 * 
	 * @return a LeftExpression que representa a expressão que acessa o
	 *         atributo.
	 */
	public LeftExpression getAv() {
		return av;
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
		Valor valor = av.avaliar(ambiente);
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
		// Recuperando o valor do atributo "id"
		return aux.getValor(super.getId());

	}
}