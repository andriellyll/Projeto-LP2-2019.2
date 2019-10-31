package pacote;

public class Problema {

	private String descricao;
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
		Problema other = (Problema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Problema(String descricao, int viabilidade, String codigo) {
		
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade.");
		
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}
	
	public String toString(){
		return codigo + " - " + descricao + " - " + viabilidade;
	}

}
