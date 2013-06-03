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
 * Classe que representa um comando de atribuição.
 */
public class Atribuicao implements Comando {
	/**
	 * Lado esquerdo do comando de atribuição.
	 */
	private LeftExpression av;
	/**
	 * Expressão cujo valor será atribuído ao lado esquerdo.
	 */
	private Expressao expressao;

	/**
	 * Construtor.
	 * 
	 * @param av
	 *            Lado esquerdo
	 * @param expressao
	 *            Expressão cujo valor será atribuído ao lado esquerdo.
	 */
	public Atribuicao(LeftExpression av, Expressao expressao) {
		this.av = av;
		this.expressao = expressao;
	}

	/**
	 * Executa a atribuição.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente modificado pela execução da atribuição.
	 * 
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoNaoDeclaradoException, TryCatchException {
		Valor valorAv = av.avaliar(ambiente);
		Valor valorExp = expressao.avaliar(ambiente);
		// atribuição do tipo id.valor := expressao;
		if (av instanceof AcessoAtributo)
			mudarValorDeIdNoAmbiente(ambiente, ((AcessoAtributo) av), expressao);
		// atribuição do tipo idClasse := idPimitivo
		else if (valorAv.eReferencia() && !valorExp.eReferencia())
			mudarValorDeIdNoAmbiente(ambiente, new AcessoAtributoId(av, new Id(
					"valor")), expressao);
		// atribuição do tipo idPrimitivo := idClasse
		else if (!valorAv.eReferencia() && valorExp.eReferencia())
			ambiente.changeValor(av.getId(), expressao.avaliarRef(ambiente));
		else
			ambiente.changeValor(av.getId(), expressao.avaliar(ambiente));
		return ambiente;
	}

	/**
	 * Um comando de atribuição está bem tipado, se o tipo do identificador é o
	 * mesmo da expressão. O tipo de um identificador é determinado pelo tipo da
	 * expressão que o inicializou (na declaração).
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            valores.
	 * @return <code>true</code> se os tipos da atribuição são válidos;
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
	 *            é o Id que será alterado
	 * @param expressao
	 *            é o novo valor que será atribuído
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
		
		// Verificação para acesso do objeto do valor
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