package pacote;

import java.util.ArrayList;

/**
 * Controller responsavel pelos metodos de busca de palavra-chave.
 * 
 * @author Andrielly de Lima Lucena - 119110268
 */
public class ControllerBusca {

	/**
	 * Colecao de entidades que implementam a interface buscavel, ou seja, que tem o
	 * metodo de busca.
	 */
	private ArrayList<Buscavel> buscaveis;

	/**
	 * Constroi um controller de Busca recebendo os outros controllers, para que
	 * seja feita a busca em cada um
	 * 
	 * @param controllerPesquisa         controller de Pesquisa
	 * @param controllerPesquisador      controller de Pesquisador
	 * @param controllerProblemaObjetivo controller de Problema e Objetivo
	 * @param controllerAtividade        controller de Atividade
	 */
	public ControllerBusca(ControllerPesquisa controllerPesquisa, ControllerPesquisador controllerPesquisador,
			ControllerProblemaObjetivo controllerProblemaObjetivo, ControllerAtividade controllerAtividade) {
		this.buscaveis = new ArrayList<>();
		this.buscaveis.add(controllerPesquisa);
		this.buscaveis.add(controllerPesquisador);
		this.buscaveis.add(controllerProblemaObjetivo);
		this.buscaveis.add(controllerAtividade);
	}

	/**
	 * Metodo auxiliar que faz a busca da palavra chave em todos os controllers e,
	 * assim, em todas as entidades cadastradas no sistema.
	 * 
	 * @param palavraChave palavra que sera procurada nas entidades
	 * @return um ArrayList de String de todos os resultados da busca, ja ordenada
	 *         de acordo com cada classe, em ordem anti-lexicografica.
	 */
	private ArrayList<String> buscarPalavraChave(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Campo termo nao pode ser nulo ou vazio.");
		ArrayList<String> resultadosBusca = new ArrayList<>();

		for (Buscavel buscavel : buscaveis) {
			resultadosBusca.addAll(buscavel.procuraPalavraChave(palavraChave));
		}

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
	 * Faz a busca da palavra-chave em todas as entidades e retorna um resultado
	 * especifico, identificado pelo numero que representa a ordem em que ele
	 * apareceu
	 * 
	 * @param palavraChave    palavra a ser buscada
	 * @param numeroResultado numero do resultado (primeiro, segundo...)
	 * @return o resultado especifico pedido
	 */
	public String busca(String palavraChave, int numeroResultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Campo termo nao pode ser nulo ou vazio.");

		if (numeroResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}

		ArrayList<String> resultadosBusca = buscarPalavraChave(palavraChave);

		if (numeroResultado > resultadosBusca.size()) {
			throw new RuntimeException("Entidade nao encontrada.");
		}

		return resultadosBusca.get(numeroResultado - 1);
	}
}
