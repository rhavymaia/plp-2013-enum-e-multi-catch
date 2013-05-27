package plp.puma.comando;

/*
 * A execucao de um comando ocorre em um determinado ambiente. O
 * resultado de tal execucao é a modificação deste ambiente, i.e., comandos
 * tem efeito colateral.
 */
import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.expressao.leftExpression.LeftExpression;
import plp.puma.expressao.valor.ValorRef;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.ContextoExecucao;
import plp.puma.memoria.DefClasse;
import plp.puma.memoria.DefClasseGenerica;
import plp.puma.memoria.Objeto;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasseGeneric;

/**
 * Comando de criação de objeto e atribuição deste a uma expressão esquerda.
 */
public class New implements Comando {
	/**
	 * Lado esquerdo da atribuição.
	 */
	private LeftExpression av;

	/**
	 * Tipo da instancia da variavel.
	 */
	private Tipo tipoInstancia;

	/**
	 * Construtor.
	 * 
	 * @param av
	 *            Lado esquerdo da atribuição.
	 * @param tipoInstancia
	 *            Identificador com o nome da tipoInstancia.
	 */
	public New(LeftExpression av, Tipo tipoInstancia) {
		this.av = av;
		this.tipoInstancia = tipoInstancia;
	}

	/**
	 * Execução da atribuição de um novo objeto criado a uma left expression.
	 * 
	 * @param ambiente
	 *            O ambiente contendo o mapeamento entre identificadores e
	 *            valores.
	 * @return o ambiente de execução atualizado.
	 */
	public AmbienteExecucao executar(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
			ObjetoJaDeclaradoException, ObjetoNaoDeclaradoException,
			TryCatchException {

		AmbienteExecucao aux = null;
		DecVariavel decVariavel = null;
		DefClasse defClasse = ambiente.getDefClasse(tipoInstancia.getTipo());
		if (defClasse instanceof DefClasseGenerica) {
			// Pegar o tipo Generic atraves da definicao da classe generica
			Tipo tipoGeneric = ((DefClasseGenerica) defClasse).getTipoGeneric();

			// Pegar o tipo real atraves do tipoInstancia
			Tipo tipoReal = ((TipoClasseGeneric) this.tipoInstancia)
					.getTipoGeneric();

			ambiente.incrementa();
			ambiente.mapTipoReal(tipoGeneric, tipoReal);
			decVariavel = defClasse.getDecVariavel();
			aux = decVariavel.elabora(new ContextoExecucao(ambiente));
			ambiente.restaura();
		} else {
			
			// Eleborar Variáveis.
			decVariavel = defClasse.getDecVariavel();
			aux = decVariavel.elabora(new ContextoExecucao(ambiente));
		}

		// Definir o objeto
		Objeto objeto = new Objeto(tipoInstancia.getTipo(),
				aux.getContextoIdValor());
		
		// Criar a referência para objeto. Recuperar próximo Contador
		// do Contexto de Execução.
		ValorRef valorRef = ambiente.getProxRef();
		ambiente.mapObjeto(valorRef, objeto);
		
		// Ligar variável a referência 
		ambiente = new Atribuicao(av, valorRef).executar(ambiente);
		
		return ambiente;
	}

	/**
	 * Verifica se a atribuição é possível comparando os tipos do objeto e da
	 * left expression.
	 * 
	 * @param ambiente
	 *            O ambiente de compilação, com o mapeamento entre
	 *            identificadores e tipos.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException {
		return av.checaTipo(ambiente) && tipoInstancia.eValido(ambiente)
				&& tipoInstancia.equals(av.getTipo(ambiente));
	}
}