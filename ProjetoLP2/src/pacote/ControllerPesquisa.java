package pacote;

import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisa {

	private Map<String, Pesquisa> pesquisas;
	private Map<String, Integer> codigos;

	public ControllerPesquisa() {
		this.pesquisas = new HashMap<>();
		this.codigos = new HashMap<>();
	}

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

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(conteudoASerAlterado,
				"Conteudo a ser alterado nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);

		Pesquisa pesquisa = this.pesquisas.get(codigo);
		if (pesquisa.getAtivacao() == false) {
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

	public void encerraPesquisa(String codigo, String motivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);

		Pesquisa pesquisa = this.pesquisas.get(codigo);
		if (pesquisa.getAtivacao() == true) {
			pesquisa.desativaPesquisa(motivo);
		} else {
			throw new IllegalArgumentException("Pesquisa desativada.");

		}

	}

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

	public String exibePesquisa(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);

		Pesquisa pesquisa = this.pesquisas.get(codigo);

		return pesquisa.toString();

	}

	public boolean pesquisaEhAtiva(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);
		Pesquisa pesquisa = this.pesquisas.get(codigo);
		return pesquisa.getAtivacao();

	}

	private void verificaPesquisaExiste(String codigo) {
		if (!pesquisas.containsKey(codigo)) {
			throw new RuntimeException("Pesquisa nao encontrada.");
		}
	}

}