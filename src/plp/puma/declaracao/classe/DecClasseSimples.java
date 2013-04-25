package plp.puma.declaracao.classe;

import plp.puma.declaracao.procedimento.DecProcedimento;
import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.DefClasse;
import plp.puma.util.TipoClasse;

/**
 * Classe que representa a declara��o de uma �nica classe.
 */
public class DecClasseSimples implements DecClasse {
	/**
	 * Identificador do nome da classe.
	 */
	protected Id nomeClasse;
	/**
	 * Atributos da classe.
	 */
	protected DecVariavel atributos;
	/**
	 * M�todos da classe.
	 */
	protected DecProcedimento metodos;

	/**
	 * Construtor.
	 * 
	 * @param nomeClasse
	 *            Nome da classe
	 * @param atributos
	 *            Atributos da classe
	 * @param metodos
	 *            M�todos da classe.
	 */
	public DecClasseSimples(Id nomeClasse, DecVariavel atributos,
			DecProcedimento metodos) {
		this.nomeClasse = nomeClasse;
		this.atributos = atributos;
		this.metodos = metodos;
	}

	/**
	 * Cria um mapeamento do identificador para a declara��o desta classe.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela declara��o da classe.
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
		ambiente.mapDefClasse(nomeClasse, new DefClasse(atributos, metodos));
		return ambiente;
	}

	/**
	 * Verifica se a declara��o est� bem tipada, ou seja, se a checagem dos
	 * tipos dos m�todos e atributos est� ok.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se os tipos da declara��o s�o v�lidos;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException {

		ambiente.mapDefClasse(nomeClasse, new DefClasse(atributos, metodos));
		boolean resposta = false;
		ambiente.incrementa();
		if (atributos.checaTipo(ambiente)) {
			ambiente.mapTipo(new Id("this"), new TipoClasse(nomeClasse));
			resposta = metodos.checaTipo(ambiente);
		}
		ambiente.restaura();
		return resposta;
	}
}