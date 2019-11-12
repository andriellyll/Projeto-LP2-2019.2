package pacote;

/**
 * 
 * 
 * @author
 */
public class Psquiza {

	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerProblemaObjetivo controllerProblemaObjetivo;
	private ControllerAtividade controllerAtividade;

	/**
	 * 
	 * 
	 * @param controllerAtividade
	 * @param controllerProblemaObjetivo
	 * @param controllerPesquisador
	 * @param controllerPesquisa
	 */
	public Psquiza(ControllerPesquisa controllerPesquisa, ControllerPesquisador controllerPesquisador,
			ControllerProblemaObjetivo controllerProblemaObjetivo, ControllerAtividade controllerAtividade) {
		this.controllerPesquisa = controllerPesquisa;
		this.controllerPesquisador = controllerPesquisador;
		this.controllerProblemaObjetivo = controllerProblemaObjetivo;
		this.controllerAtividade = controllerAtividade;
	}

//Associacoes de Objetivos e Problema:

	/**
	 * Metodo responsavel por associar um problema a uma pesquisa.
	 * 
	 * @param idPesquisa - valor de identificacao da pesquisa
	 * @param idProblema - valor de identificacao do problema
	 * @return um booleano referente a situacao do processo
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		return controllerPesquisa.associaProblema(idPesquisa, controllerProblemaObjetivo.getProblema(idProblema));
	}

	/**
	 * Metodo responsavel por desassociar o problema de uma pesquisa.
	 * 
	 * @param idPesquisa - valor de identificacao da pesquisa
	 * @return um booleano referente a situacao do processo
	 */
	public boolean desassociaProblema(String idPesquisa) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		return controllerPesquisa.desassociaProblema(idPesquisa);
	}

	/**
	 * Metodo responsavel por associar um determinado objetivo a uma determinada
	 * pesquisa e associar esta pesquisa a este objetivo.
	 * 
	 * @param idPesquisa - valor que identifica a pesquisa desejada
	 * @param idObjetivo - valor que identifica o objetivo desejado
	 * @return um booleano referente a situacao do processo
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		boolean passo1 = controllerProblemaObjetivo.associaPesquisa(idObjetivo,
				controllerPesquisa.getPesquisa(idPesquisa));
		boolean passo2 = controllerPesquisa.associaObjetivo(idPesquisa,
				controllerProblemaObjetivo.getObjetivo(idObjetivo));
		return passo1 && passo2;
	}

	/**
	 * Metodo responsavel por desassociar um determinado objetivo a uma determinada
	 * pesquisa e desassociar esta pesquisa a este objetivo.
	 * 
	 * @param idPesquisa - valor que identifica a pesquisa desejada
	 * @param idObjetivo - valor que identifica o objetivo desejado
	 * @return um booleano referente a situacao do processo
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		boolean passo1 = controllerProblemaObjetivo.desassociaPesquisa(idObjetivo,
				controllerPesquisa.getPesquisa(idPesquisa));
		boolean passo2 = controllerPesquisa.desassociaObjetivo(idPesquisa,
				controllerProblemaObjetivo.getObjetivo(idObjetivo));
		return passo1 && passo2;
	}

	/**
	 * Metodo responsavel por lista pesquisas, referente a ordem especifica desejada
	 * pelo usuario, podendo ser classicada por PROBLEMA, OBJETIVOS e/ou PESQUISA.
	 * 
	 * @param ordem - valor que representa a forma como o usuario quer que sejam
	 *              listados suas pesquisas
	 * @return uma string listando as pesquisa
	 */
	public String listaPesquisas(String ordem) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(ordem, "Valor invalido da ordem");
		if (!"PROBLEMA".equals(ordem) && !"OBJETIVOS".equals(ordem) && !"PESQUISA".equals(ordem)) {
			throw new IllegalArgumentException("Valor invalido da ordem");
		}
		return controllerPesquisa.imprimePesquisas(ordem);
	}

//Associacao Pesquisa e Pesquisador:

	/**
	 * 
	 * 
	 * @param idPesquisa
	 * @param emailPesquisador
	 * @return
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(emailPesquisador,
				"Campo emailPesquisador nao pode ser nulo ou vazio.");
		return controllerPesquisa.associaPesquisador(idPesquisa,
				controllerPesquisador.getPesquisador(emailPesquisador));
	}

	/**
	 * 
	 * 
	 * @param codigoDaPesquisa
	 * @param emailPesquisador
	 * @return
	 */
	public boolean desassociaPesquisador(String codigoDaPesquisa, String emailPesquisador) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoDaPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		return controllerPesquisa.desassociaPesquisador(codigoDaPesquisa,
				controllerPesquisador.getPesquisador(emailPesquisador));
	}

//Associacao e Execucao de Atividades:

	/**
	 * Associa uma atividade a uma pesquisa, a partir do codigo da pesquisa a ser
	 * associada e do codigo da atividade a ser associada. Retornara true se for
	 * associada com sucesso e false caso nao.
	 * 
	 * @param codigoPesquisa  - o codigo da pesquisa a ser associada
	 * @param codigoAtividade - o codigo da atividade a ser associada
	 * @return - o valor booleano e que representa o sucesso ou nao da associacao
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa,
				"Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = controllerPesquisa.getPesquisa(codigoPesquisa);
		return controllerAtividade.associaPesquisa(pesquisa, codigoAtividade);
	}

	/**
	 * Desassocia uma atividade a uma pesquisa, a partir do codigo da pesquisa a ser
	 * desassociada e do codigo da atividade a ser desassociada. Retornara true se
	 * for desassociada com sucesso e false caso nao for.
	 * 
	 * @param codigoPesquisa  - o codigo da pesquisa a ser associada
	 * @param codigoAtividade - o codigo da atividade a ser associada
	 * @return - o valor booleano que representa o sucesso ou nao da desassociacao
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa,
				"Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		controllerPesquisa.validaPesquisa(codigoPesquisa);
		return controllerAtividade.desassociaPesquisa(codigoAtividade);
	}

	/**
	 * Executa um item de uma atividade a partir do codigo da atividade, do inteiro
	 * que representa o item e da duracao de execucao do item.
	 * 
	 * @param codigoAtividade - o codigo da atividade a ser executada.
	 * @param item            - inteiro que representa o item
	 * @param duracao         - a duracao de execucao do item
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaNumeroNegativo(item, "Item nao pode ser nulo ou negativo.");
		ValidadorDeEntradas.verificaNumeroNegativo(duracao, "Duracao nao pode ser nula ou negativa.");
		controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
	}

	/**
	 * Cadastra um resultado (uma String) de uma atividade, a partir do codigo da
	 * atividade e do resultado a ser cadastrado. Retornara o inteiro que representa o cadastro do resultado
	 * 
	 * @param codigoAtividade - o codigo da atividade a ter seu resultado cadastrado
	 * @param resultado - o resultado a ser atribuido
	 * @return - o valor inteiro que representa o cadastro do resultado
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(resultado, "Resultado nao pode ser nulo ou vazio.");
		return controllerAtividade.cadastraResultado(codigoAtividade, resultado);
	}

	/**
	 * Removeum resultado (uma String) de uma atividade, a partir do codigo da
	 * atividade e do inteiro que representa esse resultado.Retornara true se for removido com sucesso e false caso nao for.
	 * 
	 * @param codigoAtividade - o codigo da atividade a ter seu resultado removido
	 * @param numeroResultado -  o valor inteiro que representa o resultado a ser removido
	 * @return
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaNumeroNegativo(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		return controllerAtividade.removeResultado(codigoAtividade, numeroResultado);
	}

	/**
	 * Lista os resultados cadastrados em uma atividade, a partir do codigo da atividade.
	 * 
	 * @param codigoAtividade - codigo da atividade a ter seus resultados listados
	 * @return - a lista de todos os resultados cadastrados
	 */
	public String listaResultados(String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.listaResultados(codigoAtividade);
	}

	/**
	 * Retorna a duracao total em horas de uma atividade.
	 * 
	 * @param codigoAtividade - o codigo da atividade a ter sua duracao exibida
	 * @return - a duracao em horas da atividade.
	 */
	public int getDuracao(String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.getDuracao(codigoAtividade);
	}

}