package pacote;

public class Professor implements Funcao{
	
	private String formacao;
	private String unidade;
	private String data;
	
	public Professor() {
		this.formacao = "";
		this.unidade = "";
		this.data = "";
	}
	public void cadastraEspecialidade(String formacao, String unidade, String data) {
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;	
	}

}
