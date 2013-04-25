package plp.puma.memoria;

import plp.puma.declaracao.procedimento.ListaDeclaracaoParametro;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.util.Tipo;

/**
 * Classe que representa o ambiente de compilação, contendo o mapeamento entre
 * identificadores e tipos.
 */
public interface AmbienteCompilacao extends Ambiente {
	/**
	 * Mapeia um identificador a um tipo.
	 * 
	 * @param idArg
	 *            Identificador
	 * @param tipoId
	 *            Tipo que deve ser associado ao identificador.
	 * @throws VariavelJaDeclaradaException
	 *             quando o id já foi declarado.
	 */
	public void mapTipo(Id idArg, Tipo tipoId)
			throws VariavelJaDeclaradaException;

	/**
	 * Obtém o tipo associado a um dado identificador
	 * 
	 * @param idArg
	 *            Identificador
	 * @return Tipo associado a um dado identificador
	 * @throws VariavelNaoDeclaradaException
	 *             quando id não foi declarado.
	 */
	public Tipo getTipo(Id idArg) throws VariavelNaoDeclaradaException;

	/**
	 * Mapeia um identificador representando um método aos seus parâmetros.
	 * 
	 * @param idArg
	 *            identificador do método.
	 * @param parametrosId
	 *            Parâmetros do método
	 * @throws ProcedimentoJaDeclaradoException
	 *             quando o procedimento já foi declarado.
	 */
	public void mapParametrosProcedimento(Id idArg,
			ListaDeclaracaoParametro parametrosId)
			throws ProcedimentoJaDeclaradoException;

	/**
	 * Obtém a tail de parâmetros associada a um identificador que representa
	 * nome do método.
	 * 
	 * @param idArg
	 *            Identificador que representa o nome do método.
	 * @return Lista de parâmetros Lista de parâmetros associada a um
	 *         identificador que representa nome do método.
	 * @throws ProcedimentoNaoDeclaradoException
	 *             quando não foi declarado nenhum método com esse id.
	 */
	public ListaDeclaracaoParametro getParametrosProcedimento(Id idArg)
			throws ProcedimentoNaoDeclaradoException;

	/**
	 * Mapeia um identificador a um definição de classe.
	 * 
	 * @param idArg
	 *            o nome da classe
	 * @param defClasse
	 *            Definição da Classe.
	 * @throws ClasseJaDeclaradaException
	 *             quando a classe já foi declarada.
	 */
	public void mapDefClasse(Id idArg, DefClasse defClasse)
			throws ClasseJaDeclaradaException;

	/**
	 * Obtém a definição da classe cujo nome é idArg
	 * 
	 * @param idArg
	 *            Nome da classe.
	 * @return a definição da classe.
	 * @throws ClasseNaoDeclaradaException
	 *             quando nao foi declarada nenhuma classe com esse nome.
	 */
	public DefClasse getDefClasse(Id idArg) throws ClasseNaoDeclaradaException;

	/**
	 * Mapeia um identificador a um tipo generic.
	 * 
	 * @param idArg
	 *            o nome da classe
	 * @param tipoGeneric
	 *            Tipo Generic da Classe.
	 * @throws ClasseJaDeclaradaException
	 *             quando a classe já foi declarada.
	 */
	public void mapGeneric(Id idArg, Tipo tipoGeneric)
			throws ClasseJaDeclaradaException;

	/**
	 * Mapeia um tipo generico com seu tipo real.
	 * 
	 * @param tipoGeneric
	 *            tipo generic.
	 * @param tipoReal
	 *            tipo real.
	 * @throws ClasseJaDeclaradaException
	 *             Quando um tipo generico jah foi mapeado com seu tipo real.
	 */
	public void mapTipoReal(Tipo tipoGeneric, Tipo tipoReal)
			throws ClasseJaDeclaradaException;

	/**
	 * Obtém o tipo real associado ao tipo generico.
	 * 
	 * @param tipoGeneric
	 *            tipo
	 * @return tipo tipo associado ao tipo generico.
	 * @throws ClasseNaoDeclaradaException
	 *             Quando o tipo generic nao tiver bind com tipo real.
	 */
	public Tipo getTipoReal(Tipo tipoGeneric)
			throws ClasseNaoDeclaradaException;

	/**
	 * Obtém o tipo generico da classe.
	 * 
	 * @param idArg
	 *            Nome do tipo generico.
	 * @return tipoGeneric o tipo generico da classe.
	 * @throws ClasseNaoDeclaradaException
	 *             quando nao foi declarada nenhuma classe com esse nome.
	 */
	public Tipo getGeneric(Id idArg) throws ClasseNaoDeclaradaException;

	/**
	 * Obtém o tipo da entrada atual para este ambiente.
	 * 
	 * @return o tipo da entrada.
	 * @throws VariavelNaoDeclaradaException
	 *             quando a entrada atual é uma variável não declarada.
	 */
	public Tipo getTipoEntrada() throws VariavelNaoDeclaradaException;

}
