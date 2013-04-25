package plp.puma.util;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.memoria.AmbienteCompilacao;

/**
 * Classe usada na declara��o de classes gen�ricas. A �nica diferen�a pra uma
 * classe normal � que a classe gen�rica tem um tipo associado a ela.
 * 
 * Ex: ArraList<String> temp.
 * 
 * temp � do tipo TipoClasseGeneric e seus atributos ID - identificador da
 * classe gen�rica = ArrayList tipoGeneric - E o tipo associado = String
 * 
 * @author Administrador
 * 
 */
public class TipoClasseGeneric extends TipoClasse {

	/**
	 * Define o tipo generico da classe
	 */
	private Tipo tipoGeneric;

	public TipoClasseGeneric(Id tipoClasse, Tipo tipoGeneric) {
		super(tipoClasse);
		this.tipoGeneric = tipoGeneric;
	}

	public boolean eValido(AmbienteCompilacao ambiente)
			throws ClasseNaoDeclaradaException {
		boolean resposta = false;
		Id idTipoClasse = super.getTipo();
		boolean respostaDefClasse = (ambiente.getDefClasse(idTipoClasse) != null);

		Id idTipoGeneric = this.tipoGeneric.getTipo();
		try {
			resposta = (respostaDefClasse && ambiente.getGeneric(idTipoGeneric) != null);
		} catch (ClasseNaoDeclaradaException e) {
			resposta = (respostaDefClasse && ambiente
					.getDefClasse(idTipoGeneric) != null);
		}
		return resposta;
	}

	/**
	 * Compara este tipo com o tipo dado.
	 * 
	 * @return <code>true</code> se se tratarem do mesmo tipo;
	 *         <code>false</code> caso contrario.
	 */
	public boolean equals(Object obj) {
		boolean resposta = false;
		if (obj instanceof TipoClasseGeneric) {

			TipoClasseGeneric tcg = (TipoClasseGeneric) obj;
			resposta = tcg.getTipo().equals(this.getTipo())
					&& tcg.getTipoGeneric().equals(this.tipoGeneric);
		}
		return resposta;
	}

	public Tipo getTipoGeneric() {
		return this.tipoGeneric;
	}

	/**
	 * Retorna a descri��o textual do tipo.
	 * 
	 * @return a descri��o textual do tipo.
	 */
	public String toString() {
		return this.getTipo() + "<" + this.tipoGeneric.toString() + ">";
	}

}
