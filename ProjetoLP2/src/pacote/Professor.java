package pacote;

public class Professor implements Funcao {

	private String formacao;
	private String unidade;
	private String data;

	public Professor(String formacao, String unidade, String data) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(data, "Campo data nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaData(data, "Atributo data com formato invalido.");

		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}

	@Override
	public String toString(String nome, String funcao, String biografia, String email, String foto) {
		return nome + " (" + funcao + ")" + " - " + biografia + " - " + email + " - " + foto + " - " + this.formacao
				+ " - " + this.unidade + " - " + this.data;
	}

	@Override
	public void setEspecialidade(String atributo, String novoAtributo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(atributo, "Campo atributo nao pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo novoAtributo nao pode ser nulo ou vazio");

		if (atributo.equalsIgnoreCase("formacao")) {
			this.formacao = novoAtributo;
		} else if (atributo.equalsIgnoreCase("unidade")) {
			this.unidade = novoAtributo;
		} else if (atributo.equalsIgnoreCase("data")) {
			ValidadorDeEntradas.verificaData(novoAtributo, "Atributo data com formato invalido.");
			this.data = novoAtributo;
		} else {
			throw new RuntimeException("Atributo invalido.");
		}

	}

}
