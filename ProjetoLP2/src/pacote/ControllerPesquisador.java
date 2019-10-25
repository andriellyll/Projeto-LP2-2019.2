package pacote;

import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisador {

	private Map<String, Pesquisador> pesquisadores;

	public ControllerPesquisador(){
		this.pesquisadores = new HashMap<>();
	}
	
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoUrl) {
		this.pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoUrl));
	}
	
	public void alteraPesquisador(String email, String atributo, String novoAtributo) {
		if (atributo.equalsIgnoreCase("nome")) {
			this.pesquisadores.get(email).setNome(novoAtributo);
		}
		else if (atributo.equalsIgnoreCase("funcao")) {
			this.pesquisadores.get(email).setFuncao(novoAtributo);
		}
		else if (atributo.equalsIgnoreCase("biografia")) {
			this.pesquisadores.get(email).setBiografia(novoAtributo);
		}
		else if (atributo.equalsIgnoreCase("email")) {
			this.pesquisadores.get(email).setEmail(novoAtributo);
		}
		else if (atributo.equalsIgnoreCase("foto")) {
			this.pesquisadores.get(email).setFoto(novoAtributo);
		}
	}
	
	public void desativaPesquisador(String email) {
		this.pesquisadores.get(email).desativaPesquisador();
	}
	
	public void ativaPesquisador(String email) {
		this.pesquisadores.get(email).ativaPesquisador();
	}
	
	public String exibePesquisador(String email) {
		return this.pesquisadores.get(email).toString();
	}
	
	public boolean pesquisadorEhAtivo(String email) {
		return this.pesquisadores.get(email).ehAtivo();
	}
}