package pacote;

public class Objetivo {
	
	private String tipo;
	private String descricao;
	private int aderencia;
	private int viabilidade;
	private String codigo;
	
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade,  String codigo) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}
	
	public String toString() {
	
		return codigo + " - " + tipo  + " - " + descricao  + " - " + (aderencia + viabilidade);
	}
	
}
