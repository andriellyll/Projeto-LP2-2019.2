package pacote;

public class Pesquisa {
	private String descricao;
	private String campoDeInteresse;
	private String codigo;
	private String motivoDeDesativacao;
	private boolean ehAtivada;

	public Pesquisa(String codigo, String descricao, String campoDeInteresse) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(campoDeInteresse, "Formato do campo de interesse invalido.");
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
		this.ehAtivada = true;

	}

	public void setDescricao(String novaDescricao) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novaDescricao, "Descricao nao pode ser nula ou vazia.");
		this.descricao = novaDescricao;

	}

	public void setCampo(String novoCampo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoCampo, "Formato do campo de interesse invalido.");
		this.campoDeInteresse = novoCampo;

	}

	public boolean getAtivacao() {

		return this.ehAtivada;
	}

	public void desativaPesquisa(String motivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(motivo, "Motico nao pode ser nulo ou vazio.");
		this.ehAtivada = false;
		this.motivoDeDesativacao = motivo;

	}

	public void ativaPesquisa() {
		this.ehAtivada = true;

	}

	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
	}
}