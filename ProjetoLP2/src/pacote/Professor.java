package pacote;

/**
 * Classe que representa no sistema um Professor, uma especialidade de
 * Pesquisador.
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 *
 */
public class Professor implements Funcao {
	
	/**
	 * Atributo que representa a formacao do Professor.
	 */
	private String formacao;
	
	/**
	 * Atributo que representa qual unidade que o Professor faz parte.
	 */
	private String unidade;
	
	/**
	 * Atributo que representa a data de contratacao do Professor.
	 */
	private String data;

	/**
	 * Contrutor de Professor que recebe sua formacao, unidade e data de
	 * contratacao.
	 * 
	 * @param formacao a formacao do Professor.
	 * @param unidade  a unidade que o Professor faz parte.
	 * @param data     a data de contratacao do Professor.
	 */
	public Professor(String formacao, String unidade, String data) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(data, "Campo data nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaData(data, "Atributo data com formato invalido.");
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}

	/**
	 * Metodo que gera a representacao textual do professor, recebendo
	 * caracteristicas que sao do Pesquisador,
	 * 
	 * @param nome      o nome do pesquisador.
	 * @param funcao    a funcao do pesquisador.
	 * @param biografia a biografia do pesquisador.
	 * @param email     o email do pesquisador.
	 * @param foto      a foto do pesquisador.
	 * @return uma String com a representacao textual do Professor.
	 */
	@Override
	public String toString(String nome, String funcao, String biografia, String email, String foto) {
		return nome + " (" + funcao + ")" + " - " + biografia + " - " + email + " - " + foto + " - " + this.formacao
				+ " - " + this.unidade + " - " + this.data;
	}
	
	/**
	 * Metodo que altera caracteristicas especificas do Professor, recebendo qual
	 * atributo quer alterar e a nova versao desse atributo e nao retorna nada.
	 * 
	 * @param atributo     atributo que deve alterar.
	 * @param novoAtributo nova versao desse atributo.
	 */
	@Override
	public void setEspecialidade(String atributo, String novoAtributo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(atributo, "Campo atributo nao pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo novoAtributo nao pode ser nulo ou vazio");

		if (atributo.equalsIgnoreCase("formacao")) {
			this.formacao = novoAtributo;
		} else if (atributo.equalsIgnoreCase("unidade")) {
			this.unidade = novoAtributo;
		} else if (atributo.equalsIgnoreCase("data")) {
			ValidadorDeEntradas.verificaData(novoAtributo, "Atributo data com formato invalido.");
			this.data = novoAtributo;
		} else {
			throw new RuntimeException("Atributo invalido.");
		}
	}
}
