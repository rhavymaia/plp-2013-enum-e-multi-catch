package plp.puma.expressao.leftExpression;

import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorConcreto;
import plp.puma.expressao.valor.ValorRef;
import plp.puma.expressao.valor.ValorString;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.Objeto;
import plp.puma.memoria.ObjetoEnum;
import plp.puma.util.Tipo;

/**
 * Classe que representa um identificador.
 */
public class Id implements LeftExpression { // ,IDominio{
	/**
	 * Nome do identificador.
	 */
	private String idName;

	/**
	 * Construtor.
	 * 
	 * @param strName
	 *            Nome do identificador.
	 */
	public Id(String strName) {
		idName = strName;
	}

	/**
	 * Verifica se o objeto que entra como parâmetro é igual a este Id.
	 * 
	 * @param obj
	 *            Objeto a ser comparado.
	 * @return se obj é igual a este objeto, false, caso contrário.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Id) {
			return ((Id) obj).idName.equals(this.idName);
		}
		return false;
	}

	/**
	 * Retorna o nome desse identificador.
	 * 
	 * @return o nome do identificador.
	 */
	public String toString() {
		return idName;
	}

	/**
	 * Retorna o valor deste identificador.
	 * 
	 * @param ambiente
	 *            o ambiente de execução, com o mapeamento de identificadores a
	 *            valores.
	 * @return o valor deste identificador
	 * @exception VariavelNaoDeclaradaException
	 *                se este identificador nao estiver no ambiente.
	 * @throws ObjetoNaoDeclaradoException
	 * @throws VariavelJaDeclaradaException
	 */
	public Valor avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return obterValorDeIdNoAmbiente(ambiente);
	}

	/**
	 * Avalia a expressao retornando seu Valor. Se o Valor for um referencia
	 * retorna o valor da referencia.
	 */
	public Valor avaliarRef(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		Valor valor = avaliar(ambiente);
		if (valor.eReferencia()) {
			Objeto obj = ambiente.getObjeto((ValorRef) valor);
			// recuperando o ambiente do objeto
			AmbienteExecucao aux = obj.getEstado();
			// alterando o ambiente do objeto
			if (obj instanceof ObjetoEnum) {
				String id = ((ObjetoEnum) obj).getConstante().toString();
				ValorString constante = new ValorString(id);
				valor = (ValorConcreto) constante;
			} else {
				valor = (ValorConcreto) aux.getValor(new Id("valor"));
			}			
		}
		return valor;
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return <code>true</code> se os tipos da expressao são válidos;
	 *         <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException
	 *                se este identificador nao estiver no ambiente.
	 */
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException {
		boolean result = true;
		amb.getTipo(this); // verifica se está no ambiente
		return result;
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param ambiente
	 *            o ambiente de compilação.
	 * @return os tipos possiveis desta expressao.
	 * @exception VariavelNaoDeclaradaException
	 *                se este identificador nao estiver no ambiente.
	 */
	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException {
		return amb.getTipo(this);
	}

	/**
	 * Obtém este identificador.
	 * 
	 * @return este Id.
	 */
	public Id getId() {
		return this;
	}

	/**
	 * Retorna o código hash, utilizado para indexação deste identificador no
	 * momento de armazená-lo em alguma estrutura de dados.
	 * 
	 * @return o código hash do nome do identificador.
	 */
	public int hashCode() {
		return idName.hashCode();
	}

	/**
	 * Retorna o valor do Objeto representado por um certo id
	 * 
	 * @param ambiente
	 *            é o Ambiente de Execução
	 * @return o valor do Objeto representado por um certo id
	 * @throws ObjetoNaoDeclaradoException
	 * @throws VariavelJaDeclaradaException
	 */
	private Valor obterValorDeIdNoAmbiente(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		return (Valor) ambiente.getValor(this);
	}
}