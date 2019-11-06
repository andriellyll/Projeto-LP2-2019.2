package pacote;

import java.util.ArrayList;
import java.util.Collections;

import javax.management.RuntimeErrorException;

import utils.OrdenaResultados;

public class Psquiza {

	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerProblemaObjetivo controllerProblemaObjetivo;
	private ControllerAtividade controllerAtividade;

	public Psquiza() {
		controllerPesquisa = new ControllerPesquisa();
		controllerPesquisador = new ControllerPesquisador();
		controllerProblemaObjetivo = new ControllerProblemaObjetivo();
		controllerAtividade = new ControllerAtividade();
	}

//Pesquisa:

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return this.controllerPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		this.controllerPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		this.controllerPesquisa.encerraPesquisa(codigo, motivo);

	}

	public void ativaPesquisa(String codigo) {
		this.controllerPesquisa.ativaPesquisa(codigo);

	}

	public String exibePesquisa(String codigo) {
		return this.controllerPesquisa.exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return this.controllerPesquisa.pesquisaEhAtiva(codigo);
	}

//Pesquisador:

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoUrl) {
		controllerPesquisador.cadastraPesquisador(nome, funcao, biografia, email, fotoUrl);
	}

	public void alteraPesquisador(String email, String atributo, String novoAtributo) {
		controllerPesquisador.alteraPesquisador(email, atributo, novoAtributo);
	}

	public void desativaPesquisador(String email) {
		controllerPesquisador.desativaPesquisador(email);
	}

	public void ativaPesquisador(String email) {
		controllerPesquisador.ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return controllerPesquisador.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return controllerPesquisador.pesquisadorEhAtivo(email);
	}

//Problema e Objetivo:

	public String cadastraProblema(String descricao, int viabilidade) {
		return controllerProblemaObjetivo.cadastraProblema(descricao, viabilidade);
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return controllerProblemaObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		controllerProblemaObjetivo.apagarProblema(codigo);
	}

	public void apagarObjetivo(String codigo) {
		controllerProblemaObjetivo.apagarObjetivo(codigo);
	}

	public String exibeProblema(String codigo) {
		return controllerProblemaObjetivo.exibeProblema(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return controllerProblemaObjetivo.exibeObjetivo(codigo);
	}

//Atividade:

	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return controllerAtividade.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		controllerAtividade.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		controllerAtividade.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return controllerAtividade.exibeAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return controllerAtividade.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return controllerAtividade.contaItensRealizados(codigo);
	}

//Associacoes de Objetivos e Problema:
	
	/**
	 * Metodo responsavel por associar um a pesquisa a um problema e
	 * associar este mesmo problema a esta pesquisa.
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
		if (passo1 && passo2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo responsavel por desassociar uma pesquisa a um problema e
	 * desassociar este problema a pesquisa.
	 * 
	 * @param idPesquisa - valor de identificacao da pesquisa
	 * @param idProblema - valor de identificacao do problema
	 * @return ujm booleano referente a situacao do processo
	 */

	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		boolean passo1 = controllerPesquisa.getPesquisa(idPesquisa)
				.desassociaProblema(controllerProblemaObjetivo.getProblema(idProblema));
		boolean passo2 = controllerProblemaObjetivo.getProblema(idProblema)
				.desassociaPesquisa(controllerPesquisa.getPesquisa(idPesquisa));
		if (passo1 && passo2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo responsavel por associar um determinado objetivo a uma determinada pesquisa e
	 * associar esta pesquisa a este objetivo.
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
		if (passo1 && passo2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo responsavel por desassociar um determinado objetivo a uma determinada pesquisa e
	 * desassociar esta pesquisa a este objetivo.
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
		if (passo1 && passo2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo responsavel por lista pesquisas, referente a ordem especifica desejada pelo usuario,
	 * podendo ser classicada por PROBLEMA, OBJETIVOS e/ou PESQUISA.
	 * 
	 * @param ordem - valor que representa a forma como o usuario quer que sejam listados suas pesquisas
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

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(emailPesquisador,
				"Campo emailPesquisador nao pode ser nulo ou vazio.");

		controllerPesquisa.associaPesquisador(idPesquisa, controllerPesquisador.getPesquisador(emailPesquisador));
		return true;

	}

	public boolean desassociaPesquisador(String codigoDaPesquisa, String emailPesquisador) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoDaPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		controllerPesquisa.desassociaPesquisador(codigoDaPesquisa,
				controllerPesquisador.getPesquisador(emailPesquisador));
		return true;

	}

//Associacao e Execucao de Atividades:

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa,
				"Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = controllerAtividade.getAtividade(codigoAtividade);
		return controllerPesquisa.associaAtividade(codigoPesquisa, atividade);

	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa,
				"Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = controllerAtividade.getAtividade(codigoAtividade);
		return controllerPesquisa.desassociaAtividade(codigoPesquisa, atividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaNumeroNegativo(item, "Item nao pode ser nulo ou negativo.");
		ValidadorDeEntradas.verificaNumeroNegativo(duracao, "Duracao nao pode ser nula ou negativa.");
	//	Atividade atividade = controllerAtividade.getAtividade(codigoAtividade);
	//	controllerPesquisa.verificaPesquisaEhAssociada(codigoPesquisa, atividade);
		controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(resultado, "Resultado nao pode ser nulo ou vazio.");
		return controllerAtividade.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaNumeroNegativo(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		return controllerAtividade.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		return controllerAtividade.getDuracao(codigoAtividade);
	}

//Busca por Palavra-chave:
	/**
	 * Metodo auxiliar que faz a busca da palavra chave em todos os controllers e, assim, em todas as entidades cadastradas no sistema.
	 * @param palavraChave palavra que sera procurada nas entidades
	 * @return um ArrayList de String de todos os resultados da busca, ja ordenada de acordo com cada classe, em ordem anti-lexicografica.
	 */
	private ArrayList<String> buscarPalavraChave(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();
		resultadosBusca.addAll(controllerPesquisa.procuraPalavraChave(palavraChave));
		resultadosBusca.addAll(controllerPesquisador.procuraPalavraChave(palavraChave));
		resultadosBusca.addAll(controllerProblemaObjetivo.procuraPalavraChaveProblema(palavraChave));
		resultadosBusca.addAll(controllerProblemaObjetivo.procuraPalavraChaveObjetivo(palavraChave));
		resultadosBusca.addAll(controllerAtividade.procuraPalavraChave(palavraChave));

		if (resultadosBusca.isEmpty()) {
			throw new RuntimeException("Nenhum resultado encontrado");
		}

		return resultadosBusca;
	}
	/**
	 * Busca a palavra chave passada como parametro em todas as entidades no sistema e retorna uma String que contem todos os resultados da busca.
	 * @param palavraChave palavra a ser buscada
	 * @return String contendo todos os resultados da busca
	 */
	public String busca(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Campo termo nao pode ser nulo ou vazio.");

		ArrayList<String> resultadosBusca = buscarPalavraChave(palavraChave);

		return String.join(" | ", resultadosBusca);
	}
	/**
	 * Busca a palavra chave passada como parametro e retorna um inteiro que representa a quantidade de resultados obtidos na busca.
	 * @param palavraChave palavra a ser buscada
	 * @return quantidade de resultados
	 */
	public int contaResultadosBusca(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Campo termo nao pode ser nulo ou vazio.");

		int quantidadeTotal = buscarPalavraChave(palavraChave).size();

		return quantidadeTotal;
	}
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