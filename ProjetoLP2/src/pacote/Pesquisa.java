package pacote;

public class Pesquisa {
	private String descricao;
	private String campoDeInteresse;
	private String codigo;
	private boolean ehAtivada;
	private String motivoDeDesativacao;

	public Pesquisa(String codigo, String descricao, String campoDeInteresse) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
		this.ehAtivada = true;

	}

	public void setDescricao(String novoConteudo) {
		this.descricao = novoConteudo;

	}

	public void setCampo(String novoConteudo) {
		this.campoDeInteresse = novoConteudo;

	}

	public boolean getAtivacao() {

		return this.ehAtivada;
	}

	public void desativaPesquisa(String motivo) {
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