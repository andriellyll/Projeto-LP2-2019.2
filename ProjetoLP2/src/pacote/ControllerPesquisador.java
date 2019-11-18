package pacote;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.OrdenaResultados;

/**
 * Classe Controladora de Pesquisadores
 * 
 * @author Andrielly de Lima Lucena - 119110268
 */
public class ControllerPesquisador implements Buscavel {

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
	 * @param email   email do pesquisador
	 * @param message
	 */
	private void verificaPesquisadorExiste(String email, String message) {
		if (!pesquisadores.containsKey(email)) {
			throw new RuntimeException(message);
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

		Pesquisador pesquisador = new Pesquisador(nome, funcao, biografia, email, fotoUrl);
		this.pesquisadores.put(email, pesquisador);
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

		verificaPesquisadorExiste(email, "Pesquisador nao encontrado");
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
		} else if (atributo.equalsIgnoreCase("FORMACAO") || atributo.equalsIgnoreCase("UNIDADE")
				|| atributo.equalsIgnoreCase("DATA") || atributo.equalsIgnoreCase("SEMESTRE")
				|| atributo.equalsIgnoreCase("IEA")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(atributo, "Campo atributo nao pode ser nulo ou vazio.");
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo,
					"Campo novo atributo nao pode ser nulo ou vazio.");

			Pesquisador pesquisador = pesquisadores.get(email);
			pesquisador.setEspecialidade(atributo, novoAtributo);
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

		verificaPesquisadorExiste(email, "Pesquisador nao encontrado");
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

		verificaPesquisadorExiste(email, "Pesquisador nao encontrado");

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
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");

		verificaPesquisadorExiste(email, "Pesquisador nao encontrado");
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
		verificaPesquisadorExiste(email, "Pesquisador nao encontrado");

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
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Palavra nao pode ser nula ou vazia");

		ArrayList<String> resultadosBusca = new ArrayList<>();

		for (Pesquisador pesquisador : this.pesquisadores.values()) {
			if (!pesquisador.procuraPalavraChave(palavraChave).equals("")) {
				resultadosBusca.add(pesquisador.procuraPalavraChave(palavraChave));
			}
		}

		Collections.sort(resultadosBusca, new OrdenaResultados());

		return resultadosBusca;
	}

	/**
	 * Metodo que fornece um Pesquisador recebendo seu email.
	 * 
	 * @param email email do pesquisador a ser retornado.
	 * @return um objeto do tipo Pesquisador.
	 */
	public Pesquisador getPesquisador(String email) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		verificaPesquisadorExiste(email, "Pesquisador nao encontrado");
		return pesquisadores.get(email);
	}

	/**
	 * Metodo que cadastra a especalidade Professor em um determinado Pesquisador,
	 * nao retorna nada e recebe como parametro o email do Pesquisador no qual essa
	 * especialidade deve ser cadastrada e caracteristicas especificas dessa
	 * especialidade, que sao elas: formacao, unidade e data.
	 * 
	 * @param email    o email do Pesquisador no qual essa especialidade deve ser
	 *                 cadastrada.
	 * @param formacao a formacao daquele professor.
	 * @param unidade  a unidade que o professor faz parte.
	 * @param data     a data de contratacao do professor.
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(data, "Campo data nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaData(data, "Atributo data com formato invalido.");
		verificaPesquisadorExiste(email, "Pesquisadora nao encontrada.");

		pesquisadores.get(email).cadastraEspecialidadeProfessor(formacao, unidade, data);
	}

	/**
	 * Metodo que cadastra a especalidade Aluno em um determinado Pesquisador, nao
	 * retorna nada e recebe como parametro o email do Pesquisador no qual essa
	 * especialidade deve ser cadastrada e caracteristicas especificas dessa
	 * especialidade, que sao elas: semestre e IEA.
	 * 
	 * @param email    email o email do Pesquisador no qual essa especialidade deve
	 *                 ser cadastrada.
	 * @param semestre o semestre que o aluno se encontra.
	 * @param IEA      o indice de eficiencia academica do aluno.
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaSemestre(semestre, "Atributo semestre com formato invalido.");
		ValidadorDeEntradas.verificaIEA(IEA, "Atributo IEA com formato invalido.");
		verificaPesquisadorExiste(email, "Pesquisadora nao encontrada.");
		pesquisadores.get(email).cadastraEspecialidadeAluno(semestre, IEA);

	}

	/**
	 * Metodo que retorna uma String com pesquisadores de uma mesma funcao, seja
	 * Externo, Professor ou Aluno.
	 * 
	 * @param tipo a String com o tipo a ser considerado,seja Externo, Professor ou
	 *             Aluno.
	 * @return uma String que agrupa a representacao textual de todos os
	 *         pesquisadores daquele tipo.
	 */
	public String listaPesquisadores(String tipo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		ArrayList<String> saida = new ArrayList<>();

		if (!(tipo.equalsIgnoreCase("Estudante") || tipo.equalsIgnoreCase("Professor")
				|| tipo.equalsIgnoreCase("Externo"))) {
			throw new RuntimeException("Tipo " + tipo + " inexistente.");
		}
		for (Pesquisador pesquisador : pesquisadores.values()) {
			if (pesquisador.getFuncao().equalsIgnoreCase(tipo)) {
				saida.add(pesquisador.toString());
			}
		}
		return String.join(" | ", saida);
	}

	// -----------------------------------------------------------Novas Atualizacoes
	// (Parte 3)----------------------------------------------

	/**
	 * Metodo responsavel por salvar os pesquisadores
	 */
	public void salvar() {

		try {
			FileOutputStream saveFile = new FileOutputStream("pesquisador.dat");
			ObjectOutputStream stream = new ObjectOutputStream(saveFile);
			stream.writeObject(pesquisadores);

			stream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * Metodo responsavel por recuperar o que foi salvo previamente
	 */
	@SuppressWarnings("unchecked")
	public void carregar() {
		try {
			FileInputStream restFile = new FileInputStream("pesquisador.dat");
			ObjectInputStream stream = new ObjectInputStream(restFile);
			Map<String, Pesquisador> pesquisadoresCadastrados = (Map<String, Pesquisador>) stream.readObject();

			stream.close();
			this.pesquisadores = pesquisadoresCadastrados;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}