package pacote;

import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisador {

	private Map<String, Pesquisador> pesquisadores;

	public ControllerPesquisador(){
		this.pesquisadores = new HashMap<>();
	}

	private void verificaPesquisadorExiste(String email) {
		if (!pesquisadores.containsKey(email)) {
			throw new RuntimeException("Pesquisador nao encontrado");
		}
	}
	
	private void verificaPesquisadorInativo(String email) {
		if (!pesquisadores.get(email).ehAtivo()) {
			throw new RuntimeException("Pesquisador inativo.");
		}
	}
	
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoUrl) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(nome, "Campo nome nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(fotoUrl, "Campo fotoURL nao pode ser nulo ou vazio.");
	    
		ValidadorDeEntradas.verificaEmail(email);
		ValidadorDeEntradas.verificaURL(fotoUrl);
		
		this.pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoUrl));
	}
	
	public void alteraPesquisador(String email, String atributo, String novoAtributo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(atributo, "Atributo nao pode ser vazio ou nulo.");
		
		verificaPesquisadorExiste(email);
		verificaPesquisadorInativo(email);
		
		if (atributo.equals("NOME")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo nome nao pode ser nulo ou vazio.");
	
			this.pesquisadores.get(email).setNome(novoAtributo);
		}
		else if (atributo.equals("FUNCAO")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo funcao nao pode ser nulo ou vazio.");
	
			this.pesquisadores.get(email).setFuncao(novoAtributo);
		}
		else if (atributo.equals("BIOGRAFIA")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo biografia nao pode ser nulo ou vazio.");
			
			this.pesquisadores.get(email).setBiografia(novoAtributo);
		}
		else if (atributo.equals("EMAIL")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo email nao pode ser nulo ou vazio.");
			ValidadorDeEntradas.verificaEmail(novoAtributo);
			
			Pesquisador pesquisador = pesquisadores.get(email);
			pesquisador.setEmail(novoAtributo);
			pesquisadores.remove(email);
			pesquisadores.put(novoAtributo, pesquisador);
		}
		else if (atributo.equalsIgnoreCase("FOTO")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo fotoURL nao pode ser nulo ou vazio.");
			ValidadorDeEntradas.verificaURL(novoAtributo);
			
			this.pesquisadores.get(email).setFoto(novoAtributo);
		}
		else {
			throw new RuntimeException("Atributo invalido.");
		}
	}
	
	public void desativaPesquisador(String email) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");
		
		verificaPesquisadorExiste(email);
		verificaPesquisadorInativo(email);
		
		this.pesquisadores.get(email).desativaPesquisador();
	}
	
	public void ativaPesquisador(String email) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");
		
		verificaPesquisadorExiste(email);
		
		if (pesquisadores.get(email).ehAtivo())
			throw new RuntimeException("Pesquisador ja ativado.");

		this.pesquisadores.get(email).ativaPesquisador();
	}
	
	public String exibePesquisador(String email) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Email nao pode ser nulo ou vazio.");
		
		verificaPesquisadorExiste(email);
		verificaPesquisadorInativo(email);

		return this.pesquisadores.get(email).toString();
	}
	
	public boolean pesquisadorEhAtivo(String email) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Email nao pode ser vazio ou nulo.");
		verificaPesquisadorExiste(email);
		
		return this.pesquisadores.get(email).ehAtivo();
	}
}