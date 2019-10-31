package pacote;

import easyaccept.EasyAccept;

public class Facade {

	private Psquiza pesquisa;

	public Facade() {
		pesquisa = new Psquiza();
	}

	public static void main(String[] args) {
		args = new String[] { "pacote.Facade", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
				"testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt", "testes_aceitacao/use_case_5.txt",
				"testes_aceitacao/use_case_6.txt" };
		EasyAccept.main(args);
	}

//Pesquisa:

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return this.pesquisa.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		this.pesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		this.pesquisa.encerraPesquisa(codigo, motivo);

	}

	public void ativaPesquisa(String codigo) {
		this.pesquisa.ativaPesquisa(codigo);

	}

	public String exibePesquisa(String codigo) {
		return this.pesquisa.exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return this.pesquisa.pesquisaEhAtiva(codigo);
	}

//Pesquisador:

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoUrl) {
		pesquisa.cadastraPesquisador(nome, funcao, biografia, email, fotoUrl);
	}

	public void alteraPesquisador(String email, String atributo, String novoAtributo) {
		pesquisa.alteraPesquisador(email, atributo, novoAtributo);
	}

	public void desativaPesquisador(String email) {
		pesquisa.desativaPesquisador(email);
	}

	public void ativaPesquisador(String email) {
		pesquisa.ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return pesquisa.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return pesquisa.pesquisadorEhAtivo(email);
	}

//Problema e Objetivo:

	public String cadastraProblema(String descricao, int viabilidade) {
		return pesquisa.cadastraProblema(descricao, viabilidade);
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return pesquisa.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		pesquisa.apagarProblema(codigo);
	}

	public void apagarObjetivo(String codigo) {
		pesquisa.apagarObjetivo(codigo);
	}

	public String exibeProblema(String codigo) {
		return pesquisa.exibeProblema(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return pesquisa.exibeObjetivo(codigo);
	}

//Atividade:

	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return pesquisa.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		pesquisa.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		pesquisa.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return pesquisa.exibeAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return pesquisa.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return pesquisa.contaItensRealizados(codigo);
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
		return false;
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
	
//Busca por Palavra-chave
	
	public String busca(String termo, int numeroDoResultado) {
		return null;
	}
	
	public int contaResultadosBusca(String termo) {
		return 0;
	}
}