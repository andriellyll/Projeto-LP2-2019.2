package pacote;

import easyaccept.EasyAccept;

public class Facade {

	private Psquiza psquiza;
	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerProblemaObjetivo controllerProblemaObjetivo;
	private ControllerAtividade controllerAtividade;

	public Facade() {
		controllerPesquisa = new ControllerPesquisa();
		controllerPesquisador = new ControllerPesquisador();
		controllerProblemaObjetivo = new ControllerProblemaObjetivo();
		controllerAtividade = new ControllerAtividade();
		psquiza = new Psquiza(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo,
				controllerAtividade);
	}

	public static void main(String[] args) {
		args = new String[] { "pacote.Facade", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
				"testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt", "testes_aceitacao/use_case_5.txt",
				"testes_aceitacao/use_case_6.txt", "testes_aceitacao/use_case_7.txt",
				"testes_aceitacao/use_case_8.txt" };
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
		this.controllerPesquisador.cadastraPesquisador(nome, funcao, biografia, email, fotoUrl);
	}

	public void alteraPesquisador(String email, String atributo, String novoAtributo) {
		this.controllerPesquisador.alteraPesquisador(email, atributo, novoAtributo);
	}

	public void desativaPesquisador(String email) {
		this.controllerPesquisador.desativaPesquisador(email);
	}

	public void ativaPesquisador(String email) {
		this.controllerPesquisador.ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return this.controllerPesquisador.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return this.controllerPesquisador.pesquisadorEhAtivo(email);
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		this.controllerPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		this.controllerPesquisador.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	public String listaPesquisadores(String tipo) {
		return controllerPesquisador.listaPesquisadores(tipo);
	}

//Problema e Objetivo:

	public String cadastraProblema(String descricao, int viabilidade) {
		return this.controllerProblemaObjetivo.cadastraProblema(descricao, viabilidade);
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return this.controllerProblemaObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		this.controllerProblemaObjetivo.apagarProblema(codigo);
	}

	public void apagarObjetivo(String codigo) {
		this.controllerProblemaObjetivo.apagarObjetivo(codigo);
	}

	public String exibeProblema(String codigo) {
		return this.controllerProblemaObjetivo.exibeProblema(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return this.controllerProblemaObjetivo.exibeObjetivo(codigo);
	}

//Atividade:

	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return this.controllerAtividade.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		this.controllerAtividade.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		this.controllerAtividade.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return this.controllerAtividade.exibeAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return this.controllerAtividade.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return this.controllerAtividade.contaItensRealizados(codigo);
	}

//Associacoes de Objetivos e Problema:

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return psquiza.associaProblema(idPesquisa, idProblema);
	}

	public boolean desassociaProblema(String idPesquisa) {
		return psquiza.desassociaProblema(idPesquisa);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return psquiza.associaObjetivo(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return psquiza.desassociaObjetivo(idPesquisa, idObjetivo);
	}

	public String listaPesquisas(String ordem) {
		return psquiza.listaPesquisas(ordem);
	}

//Associacao e Especializacao da Pesquisadora:

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return psquiza.associaPesquisador(idPesquisa, emailPesquisador);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return psquiza.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

//Associacao e Execucao de Atividades:

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return psquiza.associaAtividade(codigoPesquisa, codigoAtividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return psquiza.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		psquiza.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return psquiza.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return psquiza.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return psquiza.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return psquiza.getDuracao(codigoAtividade);
	}

//Busca por Palavra-chave:

	public String busca(String termo) {
		return psquiza.busca(termo);
	}

	public String busca(String termo, int numeroDoResultado) {
		return psquiza.busca(termo, numeroDoResultado);
	}

	public int contaResultadosBusca(String termo) {
		return psquiza.contaResultadosBusca(termo);
	}
}