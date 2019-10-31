package pacote;

import easyaccept.EasyAccept;

public class Facade {

	private Psquiza psquiza;

	public Facade() {
		psquiza = new Psquiza();
	}

	public static void main(String[] args) {
		args = new String[] { "pacote.Facade", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
				"testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt", "testes_aceitacao/use_case_5.txt",
				"testes_aceitacao/use_case_6.txt" };
		EasyAccept.main(args);
	}

//Pesquisa:

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return this.psquiza.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		this.psquiza.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		this.psquiza.encerraPesquisa(codigo, motivo);

	}

	public void ativaPesquisa(String codigo) {
		this.psquiza.ativaPesquisa(codigo);

	}

	public String exibePesquisa(String codigo) {
		return this.psquiza.exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return this.psquiza.pesquisaEhAtiva(codigo);
	}

//Pesquisador:

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoUrl) {
		psquiza.cadastraPesquisador(nome, funcao, biografia, email, fotoUrl);
	}

	public void alteraPesquisador(String email, String atributo, String novoAtributo) {
		psquiza.alteraPesquisador(email, atributo, novoAtributo);
	}

	public void desativaPesquisador(String email) {
		psquiza.desativaPesquisador(email);
	}

	public void ativaPesquisador(String email) {
		psquiza.ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return psquiza.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return psquiza.pesquisadorEhAtivo(email);
	}

//Problema e Objetivo:

	public String cadastraProblema(String descricao, int viabilidade) {
		return psquiza.cadastraProblema(descricao, viabilidade);
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return psquiza.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		psquiza.apagarProblema(codigo);
	}

	public void apagarObjetivo(String codigo) {
		psquiza.apagarObjetivo(codigo);
	}

	public String exibeProblema(String codigo) {
		return psquiza.exibeProblema(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return psquiza.exibeObjetivo(codigo);
	}

//Atividade:

	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return psquiza.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		psquiza.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		psquiza.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return psquiza.exibeAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return psquiza.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return psquiza.contaItensRealizados(codigo);
	}
	
//Associacoes de Objetivos e Problema:
	
	public boolean associaProblema(String idPesquisa, String idProblema) {
		return false;
	}
	
	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		return false;
	}
	
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return false;
	}
	
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return false;
	}
	
	public String listaPesquisas(String ordem) {
		return null;
	}
	
//Associacao e Especializacao da Pesquisadora
	
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return false;
	}
	
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return false;
	}
	
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		
	}
	
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		
	}
	
	public String listaPesquisadores(String tipo) {
		return null;
	}
	
//Associacao e Execucao de Atividades

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
	
	public boolean removeResultado(String codigoAtividade) {
		return psquiza.removeResultado(codigoAtividade);
	}
	
	public String listaResultados(String codigoAtividade) {
		return psquiza.listaResultados(codigoAtividade);
	}
	
	public int getDuracao(String codigoAtividade) {
		return psquiza.getDuracao(codigoAtividade);
	}
	
//Busca por Palavra-chave
	
	public String busca(String termo, int numeroDoResultado) {
		return null;
	}
	
	public int contaResultadosBusca(String termo) {
		return 0;
	}
}