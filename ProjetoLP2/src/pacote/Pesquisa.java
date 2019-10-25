package pacote;

public class Pesquisa {
	private String descricao;
	private String campoDeInteresse;
	private String codigo;
	private boolean ativada;
	
	public Pesquisa(String codigo , String descricao, String campoDeInteresse) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
		this.ativada = true;
		
	}

}
