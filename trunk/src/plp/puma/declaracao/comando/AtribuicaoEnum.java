package plp.puma.declaracao.comando;

import plp.puma.comando.Atribuicao;
import plp.puma.comando.Comando;
import plp.puma.declaracao.variavel.DecVariavel;
import plp.puma.excecao.declaracao.ClasseJaDeclaradaException;
import plp.puma.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.puma.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.puma.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.puma.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.puma.excecao.declaracao.TryCatchException;
import plp.puma.excecao.declaracao.VariavelJaDeclaradaException;
import plp.puma.excecao.declaracao.VariavelNaoDeclaradaException;
import plp.puma.excecao.execucao.EntradaInvalidaException;
import plp.puma.expressao.leftExpression.Id;
import plp.puma.expressao.leftExpression.LeftExpression;
import plp.puma.expressao.valor.Valor;
import plp.puma.expressao.valor.ValorRef;
import plp.puma.memoria.AmbienteCompilacao;
import plp.puma.memoria.AmbienteExecucao;
import plp.puma.memoria.ContextoExecucao;
import plp.puma.memoria.DefClasse;
import plp.puma.memoria.DefClasseGenerica;
import plp.puma.memoria.ObjetoEnum;
import plp.puma.util.Tipo;
import plp.puma.util.TipoClasseGeneric;

public class AtribuicaoEnum implements Comando{

	/**
	 * Constante que representa a Enum.
	 */
	private Id constante;
	
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
	public AtribuicaoEnum(Id constante, LeftExpression av, Tipo tipoInstancia) {
		this.constante = constante;
		this.av = av;
		this.tipoInstancia = tipoInstancia;
	}
	
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

		// Definir o objeto da Enum
		ObjetoEnum objeto = new ObjetoEnum(constante, tipoInstancia.getTipo(),
				aux.getContextoIdValor());
		
		// Criar a referência para objeto. Recuperar próximo Contador
		// do Contexto de Execução.
		ValorRef valorRef = ambiente.getProxRef();
		ambiente.mapObjeto(valorRef, objeto);
		
		// Ligar variável a referência 
		ambiente = new Atribuicao(av, valorRef).executar(ambiente);
		
		return ambiente;
	}

	public Valor getValor(AmbienteExecucao ambiente)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
			ObjetoJaDeclaradoException, ObjetoNaoDeclaradoException,
			ProcedimentoNaoDeclaradoException,
			ProcedimentoJaDeclaradoException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException, EntradaInvalidaException,
			TryCatchException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, ClasseJaDeclaradaException,
			ClasseNaoDeclaradaException {
		return av.checaTipo(ambiente) && tipoInstancia.eValido(ambiente)
				&& tipoInstancia.equals(av.getTipo(ambiente));
	}

}
