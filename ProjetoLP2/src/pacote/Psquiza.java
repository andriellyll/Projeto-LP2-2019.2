package pacote;

import java.util.ArrayList;
import java.util.Collections;

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

	public String associaProblema(String idPesquisa, String idProblema) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		boolean passo1 = controllerPesquisa.getPesquisa(idPesquisa).associaProblema(controllerProblemaObjetivo.getProblema(idProblema));
		boolean passo2 = controllerProblemaObjetivo.getProblema(idProblema).associaPesquisa(controllerPesquisa.getPesquisa(idPesquisa));
		if (passo1 == true && passo2 == true) {
			return "sucesso";
		}
		return "false";
	}
	
	public String desassociaProblema(String idPesquisa, String idProblema) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		boolean passo1 = controllerPesquisa.getPesquisa(idPesquisa).desassociaProblema(controllerProblemaObjetivo.getProblema(idProblema));
		boolean passo2 = controllerProblemaObjetivo.getProblema(idProblema).desassociaPesquisa(controllerPesquisa.getPesquisa(idPesquisa));
		if (passo1 == true && passo2 == true) {
			return "sucesso";
		}
		return "false";
	}
	
	public String associaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idObjetivo, "");
		boolean passo1 = controllerProblemaObjetivo.getObjetivo(idObjetivo).associaPesquisa(controllerPesquisa.getPesquisa(idPesquisa));
		boolean passo2 = controllerPesquisa.getPesquisa(idPesquisa).associaObjetivo(controllerProblemaObjetivo.getObjetivo(idObjetivo));
		if (passo1 == true && passo2 == true) {
			return "sucesso";
		}
		return "false";
	}

//Associacao e Execucao de Atividades:

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		Atividade atividade = controllerAtividade.getAtividade(codigoAtividade);
		return controllerPesquisa.associaAtividade(codigoPesquisa, atividade);

	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		controllerAtividade.associaPesquisaAtividade(controllerPesquisa.getPesquisa(codigoPesquisa), codigoAtividade);
		return controllerPesquisa.getPesquisa(codigoPesquisa)
				.desassociaAtividade(controllerAtividade.getAtividade(codigoAtividade));
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		controllerAtividade.getAtividade(codigoAtividade).verificaEhAssociada();
		controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return controllerAtividade.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return controllerAtividade.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return null;
	}

	public int getDuracao(String codigoAtividade) {
		return 0;
	}

//Busca por Palavra-chave:

	public String busca(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();
		resultadosBusca.addAll(controllerAtividade.procuraPalavraChave(palavraChave));
		resultadosBusca.addAll(controllerPesquisa.procuraPalavraChave(palavraChave));
		resultadosBusca.addAll(controllerPesquisador.procuraPalavraChave(palavraChave));
		resultadosBusca.addAll(controllerProblemaObjetivo.procuraPalavraChaveObjetivo(palavraChave));
		resultadosBusca.addAll(controllerProblemaObjetivo.procuraPalavraChaveProblema(palavraChave));
		
		Collections.sort(resultadosBusca, new OrdenaResultados());
		
		return String.join(System.lineSeparator(), resultadosBusca);
	}
	
	public int contaResultadosBusca(String palavraChave) {
	
		int qtdResultados1 = (controllerAtividade.procuraPalavraChave(palavraChave)).size();
		int qtdResultados2 = (controllerPesquisa.procuraPalavraChave(palavraChave)).size();
		int qtdResultados3 = (controllerPesquisador.procuraPalavraChave(palavraChave)).size();
		int qtdResultados4 = (controllerProblemaObjetivo.procuraPalavraChaveObjetivo(palavraChave)).size();
		int qtdResultados5 = (controllerProblemaObjetivo.procuraPalavraChaveProblema(palavraChave)).size();
		
		int quantidadeTotal = qtdResultados1 + qtdResultados2 + qtdResultados3 + qtdResultados4 + qtdResultados5;
		
		return quantidadeTotal;
	}
}