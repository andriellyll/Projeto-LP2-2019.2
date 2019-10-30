package pacote;

public class Objetivo {
	
	private String tipo;
	private String descricao;
	private int aderencia;
	private int viabilidade;
	private String codigo;
	
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade,  String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaTipo(tipo);
		ValidadorDeEntradas.validaViabilidadeOuAderencia(aderencia, "Valor invalido de aderencia");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade");
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
