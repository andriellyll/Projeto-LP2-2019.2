package pacote;
/**
 * 
 * @author Anna Beatriz Lucena
 *
 */
public class Pesquisa {

	private String descricao;
	private String campoDeInteresse;
	private String codigo;
	private String motivoDeDesativacao;
	private boolean ehAtivada;
	
	public Pesquisa(String codigo, String descricao, String campoDeInteresse) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(campoDeInteresse, "Formato do campo de interesse invalido.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Codigo nao pode ser nulo ou vazio.");

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}