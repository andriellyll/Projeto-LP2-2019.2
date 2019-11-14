package pacote;

import easyaccept.EasyAccept;

public class Facade {

	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerProblemaObjetivo controllerProblemaObjetivo;
	private ControllerAtividade controllerAtividade;
	private ControllerBusca controllerBusca;

	public Facade() {
		controllerPesquisador = new ControllerPesquisador();
		controllerProblemaObjetivo = new ControllerProblemaObjetivo();
		controllerPesquisa = new ControllerPesquisa(controllerPesquisador, controllerProblemaObjetivo);
		controllerAtividade = new ControllerAtividade(controllerPesquisa);
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
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
		return controllerPesquisa.associaProblema(idPesquisa, idProblema);
	}

	public boolean desassociaProblema(String idPesquisa) {
		return controllerPesquisa.desassociaProblema(idPesquisa);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return controllerPesquisa.associaObjetivo(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return controllerPesquisa.desassociaObjetivo(idPesquisa, idObjetivo);
	}

	public String listaPesquisas(String ordem) {
		return controllerPesquisa.imprimePesquisas(ordem);
	}

//Associacao e Especializacao da Pesquisadora:

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return controllerPesquisa.associaPesquisador(idPesquisa, emailPesquisador);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return controllerPesquisa.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

//Associacao e Execucao de Atividades:

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controllerAtividade.associaPesquisa(codigoPesquisa, codigoAtividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controllerAtividade.desassociaPesquisa(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		controllerAtividade.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return controllerAtividade.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return controllerAtividade.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return controllerAtividade.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return controllerAtividade.getDuracao(codigoAtividade);
	}

//Busca por Palavra-chave:

	public String busca(String termo) {
		return controllerBusca.busca(termo);
	}

	public String busca(String termo, int numeroDoResultado) {
		return controllerBusca.busca(termo, numeroDoResultado);
	}

	public int contaResultadosBusca(String termo) {
		return controllerBusca.contaResultadosBusca(termo);
	}
	
//Ordem das Atividades:
	
	public void defineProximaAtividade(String idPrecedente, String idSubsequente) {
		this.controllerAtividade.defineProximaAtividade(idPrecedente, idSubsequente);
	}
	
	public void tiraProximaAtividade(String idPrecedente) {
		
	}
	
	public int contaProximos(String idPrecedente) {
		return 0;
	}
	
	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return null;
	}
	
	public String pegaMaiorRiscoAtividades(String idAtividade) {
		return null;
	}
	
//Proxima Atividade:
	
	
	
//Resultados:
	
	
	
//Persistencia:
	
	public void salva() {
		
	}
	
	public void carrega() {
		
	}
}