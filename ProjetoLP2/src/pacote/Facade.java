package pacote;

public class Facade {

	ControllerPesquisador controllerPesquisador;
	
	public Facade() {
		controllerPesquisador = new ControllerPesquisador();
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
}