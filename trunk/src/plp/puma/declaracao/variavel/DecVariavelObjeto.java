package plp.puma.declaracao.variavel;

import plp.puma.comando.New;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.valor.ValorNull;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasse;
import plp.puma.util.TipoGeneric;

/**
 * Classe que representa a declaraçao de uma variável do tipo objeto.
 */
public class DecVariavelObjeto implements DecVariavel {
	/**
	 * Tipo da variável declarado.
	 */
	protected Tipo tipo;
	/**
	 * Identificador representando o objeto.
	 */
	protected Id identificador;
	/**
	 * Tipo da classe da qual objeto é uma instância.
	 */
	protected Tipo tipoInstancia;

	/**
	 * Construtor.
	 * 
	 * @param tipoVariavel
	 *            Tipo declarado da variável.
	 * @param identificador
	 *            Identificador do objeto.
	 * @param identificador
	 *            Classe da qual objeto é uma instância.
	 */
	public DecVariavelObjeto(Tipo tipoVariavel, Id identificador,
			Tipo tipoInstancia) {
		this.tipo = tipoVariavel;
		this.identificador = identificador;
		this.tipoInstancia = tipoInstancia;
	}

	/**
	 * Retorna o tipo do identificador a ser declarado no AmbienteCompilacao
	 * 
	 * @param id
	 *            o identificador da declaracao
	 * @return o tipo do identificador
	 */
	public Tipo getTipo(Id id) throws VariavelNaoDeclaradaException {
		if (this.identificador.equals(id)) {
			return tipo;
		} else {
			throw new VariavelNaoDeclaradaException(id);
		}
	}

	/**
	 * Cria um mapeamento do identificador para o objeto no ambiente de
	 * execução.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela inicialização da variável.
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ObjetoJaDeclaradoException, ObjetoNaoDeclaradoException,
			TryCatchException {
		Tipo tipoVariavelReal = tipo;
		Tipo tipoInstanciaReal = tipoInstancia;
		if (tipo instanceof TipoGeneric && tipoInstancia instanceof TipoGeneric) {
			tipoVariavelReal = ambiente.getTipoReal(tipo);
			tipoInstanciaReal = ambiente.getTipoReal(tipoInstancia);
		}

		AmbienteExecucao aux = new SimplesDecVariavel(tipoVariavelReal, identificador,
				new ValorNull()).elabora(ambiente);
		if (!this.tipoInstancia.equals(TipoClasse.TIPO_NULL)) {
			aux = new New(identificador, tipoInstanciaReal).executar(aux);
		}
		return aux;
	}

	/**
	 * 
	 * Verifica se o tipo da classe associada é válido (se existe).
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre objetos e suas
	 *            classes.
	 * @return <code>true</code> a classe existe <code>false</code> caso
	 *         contrario.
	 * 
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
		boolean resposta = false;
		if (tipoInstancia.eValido(ambiente) && tipo.eValido(ambiente)) {
			resposta = true;
			if (!tipoInstancia.equals(TipoClasse.TIPO_NULL)) {
				resposta = resposta && tipoInstancia.equals(tipo);
			}
			ambiente.mapTipo(identificador, tipo);
		}
		return resposta;
	}
}