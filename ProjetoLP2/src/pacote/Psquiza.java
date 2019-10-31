package pacote;

import easyaccept.EasyAccept;

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

	public static void main(String[] args) {
		args = new String[] { "pacote.Facade", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
				"testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt", "testes_aceitacao/use_case_5.txt",
				"testes_aceitacao/use_case_6.txt" };
		EasyAccept.main(args);
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

// US7 Associacao e Execucao de Atividades
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controllerPesquisa.getPesquisa(codigoPesquisa).associaAtividade(controllerAtividade.getAtividade(codigoAtividade));
		
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return false;
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return 0;
	}

	public boolean removeResultado(String codigoAtividade) {
		return false;
	}

	public String listaResultados(String codigoAtividade) {
		return null;
	}

	public int getDuracao(String codigoAtividade) {
		return 0;
	}
}
