package pacote;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller responsavel por gerenciar as pesquisas no sistema. As
 * funcionalidades basicas sao: cadastrar pesquisa, alterar pesquisa, encerrar
 * pesquisa, ativar pesquisa, exibir pesquisa verificar se a pesquisa e ativa.
 * 
 * @author Anna Beatriz Lucena
 *
 */
public class ControllerPesquisa {

	/**
	 * Armazena todas as pesquisas cadastradas no sistema.
	 */
	private Map<String, Pesquisa> pesquisas;

	/**
	 * Armazena os codigos(identificador da pesquisa) gerados pelo sistema, com o
	 * ojetivo de gerenciar os mesmos.
	 */
	private Map<String, Integer> codigos;

	/**
	 * Cria um novo controller de pesquisa (gerenciador), inicalizando os hashMaps
	 * pesquisas e codigos.
	 */
	public ControllerPesquisa() {
		this.pesquisas = new HashMap<>();
		this.codigos = new HashMap<>();
	}

	/**
	 * Cadastra uma pesquisa no sistema a partir de uma descricao e de um campo de
	 * interesse. Caso os paramentros passados forem nulos ou vazios uma excecao
	 * sera lancada. Caso o campo de interesse for invalido (mais de 250 caracteres
	 * na totalidade ou menos de 3 caracteres cada interesse ou mais de 4
	 * interesses) uma excecao sera lancada. Caso os paramentros forem validos, o
	 * sistema gera um codigo(que sera o id da pesquisa) com as tres primeiras
	 * letras do campo de interesse mais um numero(1 se nao tiver uma pesquisa ja
	 * cadastrada com as mesmas letras iniciais ou incrementa 1 a cada nova pesquisa
	 * com as mesmas 3 letras iniciais. O formato do codigo segue o padrao "ANA1".
	 * 
	 * @param descricao        - a descricao da pesquisa
	 * @param campoDeInteresse - o campo de interesse da pesquisa
	 * @return - o codigo(identificador unico) gerado
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(campoDeInteresse, "Formato do campo de interesse invalido.");
		ValidadorDeEntradas.validaCampoDeInteresse(campoDeInteresse);
		String codigoChave = campoDeInteresse.substring(0, 3).toUpperCase();
		String codigoPesquisa;

		if (this.codigos.containsKey(codigoChave)) {
			int valor = this.codigos.get(codigoChave) + 1;
			this.codigos.put(codigoChave, valor);
			codigoPesquisa = codigoChave + Integer.toString(valor);
			this.pesquisas.put(codigoPesquisa, new Pesquisa(codigoPesquisa, descricao, campoDeInteresse));
		} else {
			this.codigos.put(codigoChave, 1);
			codigoPesquisa = codigoChave + "1";
			this.pesquisas.put(codigoPesquisa, new Pesquisa(codigoPesquisa, descricao, campoDeInteresse));
		}
		return codigoPesquisa;

	}

	/**
	 * Altera uma pesquisa a partir do codigo(identificador unico), de um parametro
	 * a ser alterado e do novo conteudo a ser atribuido ao paramentro. Caso os
	 * parametros passados pelo ususario forem vazios ou nulos uma excecao sera
	 * lancada. Se o atributo a ser alteradoo for "descricao"/"DESCRICAO" ou
	 * "campo"/"CAMPO" a atribuicao do novo valor sera feita com sucesso. Caso for
	 * outra string diferente uma excecao sera lancada.
	 * 
	 * @param codigo               - o identificador da pesquisa a ser alterada
	 * @param conteudoASerAlterado - o paramentro a ser alterado
	 * @param novoConteudo         - o novo conteudo a ser atribuido
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(conteudoASerAlterado,
				"Conteudo a ser alterado nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);

		Pesquisa pesquisa = this.pesquisas.get(codigo);
		if (!pesquisa.getAtivacao()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (conteudoASerAlterado.equals("descricao") || conteudoASerAlterado.equals("DESCRICAO")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			pesquisa.setDescricao(novoConteudo);
		} else if (conteudoASerAlterado.equals("campo") || conteudoASerAlterado.equals("CAMPO")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoConteudo, "Formato do campo de interesse invalido.");
			pesquisa.setCampo(novoConteudo);
		} else {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoConteudo, "Formato do campo de interesse invalido.");
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}

	}

	/**
	 * Desativa uma pesquisa a parir do seu identificador e de um motivo. Caso o
	 * motivo seja vazio ou nulo uma excecao sera lacada. Caso o status da pesquisa
	 * ja seja desativado uma excecao sera lancada, ja que so pode ser destivada se
	 * seu status for ativo. Caso o codigo passado for nulo ou vazio uma excecao
	 * sera lancada.
	 * 
	 * @param codigo - o identificador da pesquisa a ser desativada
	 * @param motivo - o motivo de desativacao da pesquisa
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);

		Pesquisa pesquisa = this.pesquisas.get(codigo);
		if (pesquisa.getAtivacao()) {
			pesquisa.desativaPesquisa(motivo);
		} else {
			throw new IllegalArgumentException("Pesquisa desativada.");

		}

	}

	/**
	 * Ativa uma pesquisa a partir do seu codigo identificador. Se a pesquisa ja
	 * tiver o status de ativada uma excecao sera lancada, ja que so e possivel
	 * ativar uma pesquisa com status de sesativada. Caso o codigo passado for nulo
	 * ou vazio uma excecao sera lancada.
	 * 
	 * @param codigo - o identificador da pesquisa a ser ativada
	 */
	public void ativaPesquisa(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);

		Pesquisa pesquisa = this.pesquisas.get(codigo);

		if (pesquisa.getAtivacao() == false) {
			pesquisa.ativaPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		}

	}

	/**
	 * Exibe uma pesquisa (retorna a representacao em string da pesquisa) a partir
	 * do seu codigo identificador. Caso o codigo passado for nulo ou vazio uma
	 * excecao sera lancada.
	 * 
	 * @param codigo - o codigo da pesquisa a ser exibida
	 * @return - a representacao em string da pesquisa
	 */
	public String exibePesquisa(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);

		Pesquisa pesquisa = this.pesquisas.get(codigo);

		return pesquisa.toString();

	}

	/**
	 * Verifica se uma pesquisa e ativa a partir do seu codigo identificador. Caso o
	 * codigo passado for nulo ou vazio uma excecao sera lancada. Retonara true se o
	 * status da pesquisa for ativado ou false se o status for desativado.
	 * 
	 * @param codigo - o codigo da pesquisa a ser verificada
	 * @return - o valor booleano que representa o status de ativacao da pesquisa
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);
		Pesquisa pesquisa = this.pesquisas.get(codigo);
		return pesquisa.getAtivacao();

	}

	/**
	 * Verifica se uma pesquisa existe cadastrada no sistema a partir do seu codigo
	 * identificador. Caso a pesquisa nao esteja cadastrada no sistema uma excecao
	 * sera lancada.
	 * 
	 * @param codigo - o codigo da pesquisa a ser verificada
	 */
	private void verificaPesquisaExiste(String codigo) {
		if (!pesquisas.containsKey(codigo)) {
			throw new RuntimeException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Verifica se a pesquisa existe, a partir de um codigo, e caso ela exista sera retornada.
	 * 
	 * @param codigo - o codigo da pesquisa a ser retornada
	 * @return - a pesquisa solicitada a partir do codigo
	 */
	public Pesquisa getPesquisa(String codigo) {
		verificaPesquisaExiste(codigo);
		return this.pesquisas.get(codigo);
	}

}