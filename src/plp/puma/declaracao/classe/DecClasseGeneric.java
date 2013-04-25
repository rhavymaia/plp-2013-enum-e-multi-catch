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
import plp.puma.memoria.DefClasseGenerica;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasseGeneric;

public class DecClasseGeneric extends DecClasseSimples {

	private Tipo tipoGenerico;

	public DecClasseGeneric(Id nomeClasse, DecVariavel atributos,
			DecProcedimento metodos, Tipo tipoGenerico) {
		super(nomeClasse, atributos, metodos);
		this.tipoGenerico = tipoGenerico;
	}

	/**
	 * Cria um mapeamento do identificador para a declaração desta classe.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela declaração da classe.
	 */
	public AmbienteExecucao elabora(AmbienteExecucao ambiente)
			throws ClasseJaDeclaradaException, ClasseNaoDeclaradaException {

		ambiente.mapDefClasse(nomeClasse, new DefClasseGenerica(atributos,
				metodos, tipoGenerico));
		return ambiente;
	}

	/**
	 * Verifica se a declaração está bem tipada, ou seja, se a checagem dos
	 * tipos dos métodos e atributos está ok.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            seus tipos.
	 * @return <code>true</code> se os tipos da declaração são válidos;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException {

		// Mapeia o Tipo da classe
		ambiente.mapDefClasse(nomeClasse, new DefClasseGenerica(atributos,
				metodos, tipoGenerico));
		boolean resposta = false;

		// Mapeia o Tipo generico
		ambiente.incrementa();
		Id idGenerico = this.tipoGenerico.getTipo();
		ambiente.mapGeneric(idGenerico, this.tipoGenerico);

		if (atributos.checaTipo(ambiente)) {
			ambiente.mapTipo(new Id("this"), new TipoClasseGeneric(nomeClasse,
					this.tipoGenerico));
			resposta = metodos.checaTipo(ambiente);
		}
		ambiente.restaura();
		return resposta;

	}

}
