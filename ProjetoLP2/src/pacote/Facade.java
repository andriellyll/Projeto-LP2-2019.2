package pacote;

import easyaccept.EasyAccept;

public class Facade {

	ControllerPesquisador controllerPesquisador;
	
	public Facade() {
		controllerPesquisador = new ControllerPesquisador();
	}
	
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return "";
	}
	
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		
	}
	
	public void encerraPesquisa(String codigo, String motivo) {
		
	}
	
	public void ativaPesquisa(String codigo) {
		
	}
	
	public String exibePesquisa(String codigo) {
		return "";
	}
	
	public boolean pesquisaEhAtiva(String codigo) {
		return false;
	}
	
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
	
	public String cadastraProblema(String descricao, int viabilidade) {
		return "";
	}
	
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return "";
	}
	
	public void apagarProblema(String codigo) {
		
	}
	
	public void apagarObjetivo(String codigo) {
		
	}
	
	public String exibeProblema(String codigo) {
		return "";
	}
	
	public String exibeObjetivo(String codigo) {
		return "";
	}
	
	public String cadastraAtividade(String Descricao, String nivelRisco, String descricaoRisco) {
		return "";
	}

	public void apagaAtividade(String codigo) {
		
	}

	public void cadastraItem(String codigo, String item) {
		
	}
	
	public String exibeAtividade(String codigo) {
		return "";
	}
	
	public int contaItensPendentes(String codigo) {
		return 0;
	}
	
	public int contaItensRealizados(String codigo) {
		return 0;
	}
	
	public static void main(String[] args) {
		args = new String[] { "pacote.Facade", "testesAceitacao/use_case_1.txt"};
		EasyAccept.main(args);
	}
}