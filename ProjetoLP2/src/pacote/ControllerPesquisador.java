package pacote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe Controladora de Pesquisadores
 * 
 * @author Andrielly de Lima Lucena - 119110268
 */
public class ControllerPesquisador {
	/**
	 * Colecao que armazena objetos Pesquisador
	 */
	private Map<String, Pesquisador> pesquisadores;

	/**
	 * Constroi um Controller
	 */
	public ControllerPesquisador() {
		this.pesquisadores = new HashMap<>();
	}

	/**
	 * Verifica se o pesquisador esta cadastrado no sistema, a partir de seu email.
	 * 
	 * @param email email do pesquisador
	 */
	private void verificaPesquisadorExiste(String email) {
		if (!pesquisadores.containsKey(email)) {
			throw new RuntimeException("Pesquisador nao encontrado");
		}
	}

	/**
	 * Verifica se um pesquisador esta ativo ou nao.
	 * 
	 * @param email email do pesquisador
	 */
	private void verificaPesquisadorInativo(String email) {
		if (!pesquisadores.get(email).ehAtivo()) {
			throw new RuntimeException("Pesquisador inativo.");
		}
	}

	/**
	 * Cadastra um pesquisador no sistema, a partir de seu nome, funcao, biografia,
	 * email e URL da foto
	 * 
	 * @param nome      nome do pesquisador
	 * @param funcao    funcao do pesquisador
	 * @param biografia biografia do pesquisador
	 * @param email     email do pesquisador
	 * @param fotoUrl   URL da foto do pesquisador
	 */
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

	/**
	 * Altera algum atributo do pesquisador.
	 * 
	 * @param email        email do pesquisador que sera alterado
	 * @param atributo     atributo a ser alterado
	 * @param novoAtributo novo valor do atributo
	 */
	public void alteraPesquisador(String email, String atributo, String novoAtributo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(atributo, "Atributo nao pode ser vazio ou nulo.");

		verificaPesquisadorExiste(email);
		verificaPesquisadorInativo(email);

		if (atributo.equals("NOME")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo nome nao pode ser nulo ou vazio.");

			this.pesquisadores.get(email).setNome(novoAtributo);
		} else if (atributo.equals("FUNCAO")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo funcao nao pode ser nulo ou vazio.");

			this.pesquisadores.get(email).setFuncao(novoAtributo);
		} else if (atributo.equals("BIOGRAFIA")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo biografia nao pode ser nulo ou vazio.");

			this.pesquisadores.get(email).setBiografia(novoAtributo);
		} else if (atributo.equals("EMAIL")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo email nao pode ser nulo ou vazio.");
			ValidadorDeEntradas.verificaEmail(novoAtributo);

			Pesquisador pesquisador = pesquisadores.get(email);
			pesquisador.setEmail(novoAtributo);
			pesquisadores.remove(email);
			pesquisadores.put(novoAtributo, pesquisador);
		} else if (atributo.equalsIgnoreCase("FOTO")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo fotoURL nao pode ser nulo ou vazio.");
			ValidadorDeEntradas.verificaURL(novoAtributo);

			this.pesquisadores.get(email).setFoto(novoAtributo);
		} else {
			throw new RuntimeException("Atributo invalido.");
		}
	}

	/**
	 * Desativa um pesquisador, que tem que estar ativo.
	 * 
	 * @param email email do pesquisador
	 */
	public void desativaPesquisador(String email) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");

		verificaPesquisadorExiste(email);
		verificaPesquisadorInativo(email);

		this.pesquisadores.get(email).desativaPesquisador();
	}

	/**
	 * Ativa um pesquisador, que tem que estar inativo.
	 * 
	 * @param email email do pesquisador
	 */
	public void ativaPesquisador(String email) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");

		verificaPesquisadorExiste(email);

		if (pesquisadores.get(email).ehAtivo())
			throw new RuntimeException("Pesquisador ja ativado.");

		this.pesquisadores.get(email).ativaPesquisador();
	}

	/**
	 * Exibe informacoes sobre um pesquisador
	 * 
	 * @param email email do pesquisador que sera exibido
	 * @return a representacao textual do pesquisador
	 */
	public String exibePesquisador(String email) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Email nao pode ser nulo ou vazio.");

		verificaPesquisadorExiste(email);
		verificaPesquisadorInativo(email);

		return this.pesquisadores.get(email).toString();
	}

	/**
	 * Retorna o valor booleano que representa se o pesquisador eh ativo ou nao
	 * 
	 * @param email email do pesquisador
	 * @return o valor booleano
	 */
	public boolean pesquisadorEhAtivo(String email) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Email nao pode ser vazio ou nulo.");
		verificaPesquisadorExiste(email);

		return this.pesquisadores.get(email).ehAtivo();
	}

	/**
	 * Procura em todos os pesquisadores do mapa a palavra-chave passada como
	 * parametro
	 * 
	 * @param palavraChave palavra-chave que sera procurada
	 * @return Lista de Strings com as biografias que contiverem a palavra-chave
	 */
	public List<String> procuraPalavraChave(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();

		for (Pesquisador pesquisador : this.pesquisadores.values()) {
			if (!pesquisador.procuraPalavraChave(palavraChave).equals("")) {
				resultadosBusca.add(pesquisador.procuraPalavraChave(palavraChave));
			}
		}

		return resultadosBusca;
	}
	
	public Pesquisador getPesquisador(String email) {
		verificaPesquisadorExiste(email);
		return pesquisadores.get(email);
	}

}