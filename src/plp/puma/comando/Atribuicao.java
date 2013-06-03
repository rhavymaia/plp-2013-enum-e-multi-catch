package plp.puma.comando;

import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.Expressao;
import plp.puma.expressao.leftExpression.AcessoAtributo;
import plp.puma.expressao.leftExpression.AcessoAtributoId;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.leftExpression.LeftExpression;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorEnum;
import plp.puma.expressao.valor.ValorRef;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.Objeto;
import plp.puma.util.TipoClasse;

/**
 * Classe que representa um comando de atribui��o.
 */
public class Atribuicao implements Comando {
	/**
	 * Lado esquerdo do comando de atribui��o.
	 */
	private LeftExpression av;
	/**
	 * Express�o cujo valor ser� atribu�do ao lado esquerdo.
	 */
	private Expressao expressao;

	/**
	 * Construtor.
	 * 
	 * @param av
	 *            Lado esquerdo
	 * @param expressao
	 *            Express�o cujo valor ser� atribu�do ao lado esquerdo.
	 */
	public Atribuicao(LeftExpression av, Expressao expressao) {
		this.av = av;
		this.expressao = expressao;
	}

	/**
	 * Executa a atribui��o.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela execu��o da atribui��o.
	 * 
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException, TryCatchException {
		Valor valorAv = av.avaliar(ambiente);
		Valor valorExp = expressao.avaliar(ambiente);
		// atribui��o do tipo id.valor := expressao;
		if (av instanceof AcessoAtributo)
			mudarValorDeIdNoAmbiente(ambiente, ((AcessoAtributo) av), expressao);
		// atribui��o do tipo idClasse := idPimitivo
		else if (valorAv.eReferencia() && !valorExp.eReferencia())
			mudarValorDeIdNoAmbiente(ambiente, new AcessoAtributoId(av, new Id(
					"valor")), expressao);
		// atribui��o do tipo idPrimitivo := idClasse
		else if (!valorAv.eReferencia() && valorExp.eReferencia())
			ambiente.changeValor(av.getId(), expressao.avaliarRef(ambiente));
		else
			ambiente.changeValor(av.getId(), expressao.avaliar(ambiente));
		return ambiente;
	}

	/**
	 * Um comando de atribui��o est� bem tipado, se o tipo do identificador � o
	 * mesmo da express�o. O tipo de um identificador � determinado pelo tipo da
	 * express�o que o inicializou (na declara��o).
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return <code>true</code> se os tipos da atribui��o s�o v�lidos;
	 *         <code>false</code> caso contrario.
	 * 
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseNaoDeclaradaException {
		return expressao.checaTipo(ambiente)
				&& (av.getTipo(ambiente).equals(expressao.getTipo(ambiente)) || expressao
						.getTipo(ambiente).equals(TipoClasse.TIPO_NULL));
	}

	/**
	 * Muda o valor de um certo Id no ambiente
	 * 
	 * @param acessoAtributo
	 *            � o Id que ser� alterado
	 * @param expressao
	 *            � o novo valor que ser� atribu�do
	 */
	public void mudarValorDeIdNoAmbiente(AmbienteExecucao ambiente,
			AcessoAtributo acessoAtributo, Expressao expressao)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
			ObjetoNaoDeclaradoException {
		// se for acesso a atributo, tem de alterar o ambiente do objeto!
		Expressao expAV = acessoAtributo.getExpressaoObjeto();
		// Pegando o objeto no ambiente
		Valor valor = expAV.avaliar(ambiente);
		
		Objeto obj = null;
		
		// Verifica��o para acesso do objeto do valor
		if (valor instanceof ValorEnum) {
			
			// Acesso do objeto instanciado da Enum.
			obj = ((ValorEnum) valor).getObjeto();
		} else {
			
			ValorRef referencia = (ValorRef) valor;
			obj = ambiente.getObjeto(referencia);
		}	
		
		// recuperando o ambiente do objeto
		AmbienteExecucao aux = obj.getEstado();
		// alterando o ambiente do objeto
		aux.changeValor(acessoAtributo.getId(), expressao.avaliar(ambiente));
	}
}