package pacote;

public class Problema {
	
	private String descricao;
	private int viabilidade;
	private String codigo;
	
	//Verificar se parâmetros são válidos
	public Problema(String descricao, int viabilidade, String codigo) {
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}
	
	public String toString(){
		return codigo + " - " + descricao + " - " + viabilidade;
	}
	

}

