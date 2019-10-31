package pacote;

/**
 * Representa um problema, a ser resolvido em um contexto de uma pesquisa.
 * 
 * @author Helen Bento Cavalcanti
 *
 */
public class Problema {
	/**
	 * Atributo em string que representa a descricao desse problema.
	 */
	private String descricao;
	/**
	 * Atributo inteiro que representa a viabilidade que um problema tem de ser
	 * resolvido.
	 */
	private int viabilidade;
	/**
	 * Atributo em string que representa o id daquele problema.
	 */
	private String codigo;

	/**
	 * Metodo privado que gera e retorna um codigo inteiro unico para o problema.
	 * 
	 * @return o codigo inteiro.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Metodo privado que verifica, a partir do hashCode, se um objetivo eh igual.
	 * 
	 * @return um booleano que expressa se os objetos sao iguais.
	 */
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

	/**
	 * Metodo construtor de Problema que recebe como parametro sua descricao,
	 * viabilidae e seu codigo.
	 * 
	 * @param descricao   atributo em string que representa a descricao desse
	 *                    problema.
	 * @param viabilidade atributo inteiro que representa a viabilidade que um
	 *                    problema tem de ser resolvido.
	 * @param codigo      atributo em string que representa o id daquele problema.
	 */
	public Problema(String descricao, int viabilidade, String codigo) {

		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade.");

		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}

	/**
	 * Metodo que representa textualmente um prolema, com seu codigo, descricao e
	 * sua viabilidade.
	 */
	public String toString() {
		return codigo + " - " + descricao + " - " + viabilidade;
	}

}
