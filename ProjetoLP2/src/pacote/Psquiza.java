package pacote;

import java.util.ArrayList;
import java.util.Collections;

import javax.management.RuntimeErrorException;

import utils.OrdenaResultados;

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
	 * @param controllerAtividade 
	 * @param controllerProblemaObjetivo 
	 * @param controllerPesquisador 
	 * @param controllerPesquisa 
	 * 
	 */
	public Psquiza(ControllerPesquisa controllerPesquisa, ControllerPesquisador controllerPesquisador, ControllerProblemaObjetivo controllerProblemaObjetivo, ControllerAtividade controllerAtividade) {
		this.controllerPesquisa = controllerPesquisa;
		this.controllerPesquisador = controllerPesquisador;
		this.controllerProblemaObjetivo = controllerProblemaObjetivo;
		this.controllerAtividade = controllerAtividade;
	}

//Associacoes de Objetivos e Problema:

	/**
	 * Metodo responsavel por associar um a pesquisa a um problema e associar este
	 * mesmo problema a esta pesquisa.
	 * 
	 * @param idPesquisa - valor de identificacao da pesquisa
	 * @param idProblema - valor de identificacao do problema
	 * @return um booleano referente a situacao do processo
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		boolean passo1 = controllerPesquisa.getPesquisa(idPesquisa)
				.associaProblema(controllerProblemaObjetivo.getProblema(idProblema));
		boolean passo2 = controllerProblemaObjetivo.getProblema(idProblema)
				.associaPesquisa(controllerPesquisa.getPesquisa(idPesquisa));
		return (passo1 && passo2);
	}

	/**
	 * Metodo responsavel por desassociar uma pesquisa a um problema e desassociar
	 * este problema a pesquisa.
	 * 
	 * @param idPesquisa - valor de identificacao da pesquisa
	 * @param idProblema - valor de identificacao do problema
	 * @return um booleano referente a situacao do processo
	 */
	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		boolean passo1 = controllerPesquisa.getPesquisa(idPesquisa)
				.desassociaProblema(controllerProblemaObjetivo.getProblema(idProblema));
		boolean passo2 = controllerProblemaObjetivo.getProblema(idProblema)
				.desassociaPesquisa(controllerPesquisa.getPesquisa(idPesquisa));
		return (passo1 && passo2);

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
		boolean passo1 = controllerProblemaObjetivo.getObjetivo(idObjetivo)
				.associaPesquisa(controllerPesquisa.getPesquisa(idPesquisa));
		boolean passo2 = controllerPesquisa.getPesquisa(idPesquisa)
				.associaObjetivo(controllerProblemaObjetivo.getObjetivo(idObjetivo));
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
		boolean passo1 = controllerProblemaObjetivo.getObjetivo(idObjetivo)
				.desassociaPesquisa(controllerPesquisa.getPesquisa(idPesquisa));
		boolean passo2 = controllerPesquisa.getPesquisa(idPesquisa)
				.desassociaObjetivo(controllerProblemaObjetivo.getObjetivo(idObjetivo));
		return (passo1 && passo2);

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
		return controllerPesquisa.associaPesquisador(idPesquisa, controllerPesquisador.getPesquisador(emailPesquisador));
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
		return controllerPesquisa.desassociaPesquisador(codigoDaPesquisa,controllerPesquisador.getPesquisador(emailPesquisador));
	}

//Associacao e Execucao de Atividades:

	/**
	 * 
	 * 
	 * @param codigoPesquisa
	 * @param codigoAtividade
	 * @return
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
	 * 
	 * 
	 * @param codigoPesquisa
	 * @param codigoAtividade
	 * @return
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa,
				"Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.desassociaPesquisa(codigoAtividade);
	}

	/**
	 * 
	 * 
	 * @param codigoAtividade
	 * @param item
	 * @param duracao
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaNumeroNegativo(item, "Item nao pode ser nulo ou negativo.");
		ValidadorDeEntradas.verificaNumeroNegativo(duracao, "Duracao nao pode ser nula ou negativa.");
		controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
	}

	/**
	 * 
	 * 
	 * @param codigoAtividade
	 * @param resultado
	 * @return
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(resultado, "Resultado nao pode ser nulo ou vazio.");
		return controllerAtividade.cadastraResultado(codigoAtividade, resultado);
	}

	/**
	 * 
	 * 
	 * @param codigoAtividade
	 * @param numeroResultado
	 * @return
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaNumeroNegativo(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		return controllerAtividade.removeResultado(codigoAtividade, numeroResultado);
	}

	/**
	 * 
	 * 
	 * @param codigoAtividade
	 * @return
	 */
	public String listaResultados(String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.listaResultados(codigoAtividade);
	}

	/**
	 * 
	 * 
	 * @param codigoAtividade
	 * @return
	 */
	public int getDuracao(String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.getDuracao(codigoAtividade);
	}

//Busca por Palavra-chave:

	/**
	 * Metodo auxiliar que faz a busca da palavra chave em todos os controllers e,
	 * assim, em todas as entidades cadastradas no sistema.
	 * 
	 * @param palavraChave palavra que sera procurada nas entidades
	 * @return um ArrayList de String de todos os resultados da busca, ja ordenada
	 *         de acordo com cada classe, em ordem anti-lexicografica.
	 */
	private ArrayList<String> buscarPalavraChave(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();
		resultadosBusca.addAll(controllerPesquisa.procuraPalavraChave(palavraChave));
		resultadosBusca.addAll(controllerPesquisador.procuraPalavraChave(palavraChave));
		resultadosBusca.addAll(controllerProblemaObjetivo.procuraPalavraChaveProblema(palavraChave));
		resultadosBusca.addAll(controllerProblemaObjetivo.procuraPalavraChaveObjetivo(palavraChave));
		resultadosBusca.addAll(controllerAtividade.procuraPalavraChave(palavraChave));

		return resultadosBusca;
	}

	/**
	 * Busca a palavra chave passada como parametro em todas as entidades no sistema
	 * e retorna uma String que contem todos os resultados da busca.
	 * 
	 * @param palavraChave palavra a ser buscada
	 * @return String contendo todos os resultados da busca
	 */
	public String busca(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Campo termo nao pode ser nulo ou vazio.");

		ArrayList<String> resultadosBusca = buscarPalavraChave(palavraChave);

		return String.join(" | ", resultadosBusca);
	}

	/**
	 * Busca a palavra chave passada como parametro e retorna um inteiro que
	 * representa a quantidade de resultados obtidos na busca.
	 * 
	 * @param palavraChave palavra a ser buscada
	 * @return quantidade de resultados
	 */
	public int contaResultadosBusca(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Campo termo nao pode ser nulo ou vazio.");

		if (buscarPalavraChave(palavraChave).isEmpty()) {
			throw new RuntimeException("Nenhum resultado encontrado");
		}

		int quantidadeTotal = buscarPalavraChave(palavraChave).size();

		return quantidadeTotal;
	}

	/**
	 * 
	 * 
	 * @param palavraChave
	 * @param numeroResultado
	 * @return
	 */
	public String busca(String palavraChave, int numeroResultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Campo termo nao pode ser nulo ou vazio.");

		if (numeroResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}

		ArrayList<String> resultadosBusca = buscarPalavraChave(palavraChave);

		if (numeroResultado >= resultadosBusca.size()) {
			throw new RuntimeException("Entidade nao encontrada.");
		}

		return resultadosBusca.get(numeroResultado - 1);
	}
}