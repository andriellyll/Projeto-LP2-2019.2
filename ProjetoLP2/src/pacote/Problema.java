package pacote;

import java.util.HashSet;
import java.util.Set;

/**
 * Representa um problema, a ser resolvido em um contexto de uma pesquisa.
 * 
 * @author Helen Bento Cavalcanti
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
	 * 
	 */

	private Set<Pesquisa> pesquisas;

	/**
	 * Metodo construtor de Problema que recebe como parametro sua descricao,
	 * viabilidade e seu codigo.
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
		this.pesquisas = new HashSet<>();
	}

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
	 * Metodo que representa textualmente um prolema, com seu codigo, descricao e
	 * sua viabilidade.
	 */

	public String toString() {
		return codigo + " - " + descricao + " - " + viabilidade;
	}

//---------------------------------------------------- Novas atualizacoes de Problema ----------------------------------------------------------------------

	/**
	 * 
	 * 
	 * @param pesquisa
	 * @return
	 */

	public boolean associaPesquisa(Pesquisa pesquisa) {
		if (pesquisas.contains(pesquisa)) {
			return false;
		}
		pesquisas.add(pesquisa);
		return true;
	}
	
	/**
	 * 
	 * 
	 * @param pesquisa
	 * @return
	 */
	
	public boolean desassociaPesquisa(Pesquisa pesquisa) {
		if (!pesquisas.contains(pesquisa)) {
			return false;
		}
		pesquisas.remove(pesquisa);
		return true;
	}
	
	/**
	 * Procura no atributo descricao do problema a palavra-chave passada como
	 * parametro
	 * 
	 * @param palavraChave palavra-chave que sera pesquisada na descricao do problema
	 * @return se a palavra-chave existir na String de descricao, essa string sera
	 *         retornada. Se nao, sera retornada uma String vazia
	 */
	
	public String procuraPalavraChave(String palavraChave) {
		if (this.descricao.contains(palavraChave)) {
			return this.codigo + ": " + this.descricao;
		}
		return "";
	}
	
	public String getCodigo() {
		return this.codigo;
	}
}