package pacote;

public class Objetivo {

	private String tipo;
	private String descricao;
	private int aderencia;
	private int viabilidade;
	private String codigo;

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
		Objetivo other = (Objetivo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String codigo) {
		
		ValidadorDeEntradas.validaEntradaNulaOuVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaTipo(tipo);
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(aderencia, "Valor invalido de aderencia");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade.");
		
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}

	public String toString() {

		return codigo + " - " + tipo + " - " + descricao + " - " + (aderencia + viabilidade);
	}

}
