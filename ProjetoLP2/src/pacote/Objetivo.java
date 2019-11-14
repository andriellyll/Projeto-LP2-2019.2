package pacote;

/**
 * Representa um objetivo, seja ele geral ou especifico, que esta inserido em um
 * contexto de uma pesquisa.
 * 
 * @author Helen Bento Cavalcanti
 *
 */
public class Objetivo {

	/**
	 * Atributo em string que representa o tipo da pesquisa, que pode ser geral ou
	 * especifico.
	 * 
	 */
	private String tipo;

	/**
	 * Atributo em string que fornece uma curta descricao desse objetivo.
	 */
	private String descricao;

	/**
	 * Atributo inteiro que representa a aderencia(de 1 a 5) desse objetivo.
	 */
	private int aderencia;

	/**
	 * Atributo inteiro que representa a viabilidade(de 1 a 5) desse objetivo.
	 */
	private int viabilidade;

	/**
	 * Atributo inteiro que representa o id desse objetivo.
	 */
	private String codigo;

	/**
	 * Atributo Pesquisa que guarda a pesquisa associada a este objetivo.
	 */
	private Pesquisa pesquisaDoObjetivo;

	/**
	 * Metodo construtor de Objetivo que recebe como parametro suas caracteristicas,
	 * tais como tipo, descricao, aderencia, viabilidade e seu codigo.
	 * 
	 * @param tipo        representa o tipo da pesquisa, que pode ser geral ou
	 *                    especifico.
	 * @param descricao   que fornece uma curta descricao desse objetivo.
	 * @param aderencia   representa a aderencia(de 1 a 5) desse objetivo.
	 * @param viabilidade inteiro que representa a viabilidade(de 1 a 5) desse
	 *                    objetivo.
	 * @param codigo      id que representa tal objetivo.
	 */
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String codigo) {
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
		this.pesquisaDoObjetivo = null;
	}

	/**
	 * Procura no atributo descricao do objetivo a palavra-chave passada como
	 * parametro
	 * 
	 * @param palavraChave palavra-chave que sera pesquisada na descricao do
	 *                     objetivo
	 * @return se a palavra-chave existir na String de descricao, essa string sera
	 *         retornada. Se nao, sera retornada uma String vazia
	 */
	public String procuraPalavraChave(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Palavra nao pode ser nula ou vazia");
		if (this.descricao.contains(palavraChave)) {
			return this.codigo + ": " + this.descricao;
		}
		return "";
	}

	/**
	 * Metodo privado que gera e retorna um codigo inteiro unico para o objetivo.
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
	 * Metodo que verifica, a partir do hashCode, se um objetivo eh igual.
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
		Objetivo other = (Objetivo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Metodo que representa textualmente um objetivo, com seu codigo, tipo,
	 * descricao e a soma da aderencia com a viabilidade.
	 */
	public String toString() {

		return codigo + " - " + tipo + " - " + descricao + " - " + (aderencia + viabilidade);
	}

//------------------------------ Novas atualizacoes de Objetivo ---------------------------------------------------------

	/**
	 * Metodo responsavel por associar uma pesqquisa a um objetivo, caso este
	 * objetivo ja tenha sido salvo, ele retorna false e caso o objetivo ja tenha em
	 * pesquisa associada, ele reporta uma erro.
	 * 
	 * @param pesquisa - valor da pesquisa a ser associada
	 * @return um booleano referente a situacao do processo
	 */
	public boolean associaPesquisa(Pesquisa pesquisa) {
		if (pesquisaDoObjetivo == pesquisa) {
			return false;
		} else if (pesquisaDoObjetivo != null) {
			throw new RuntimeException("Objetivo ja associado a uma pesquisa.");
		}
		pesquisaDoObjetivo = pesquisa;
		return true;
	}

	/**
	 * Metodo responsavel por desassociar uma pesqquisa a um objetivo, caso esta
	 * pesquisa nao seja a mesma salva, ele retorna false.
	 * 
	 * @param pesquisa - valor da pesquisa a ser associada
	 * @return um booleano referente a situacao do processo
	 */
	public boolean desassociaPesquisa(Pesquisa pesquisa) {
		if (pesquisaDoObjetivo != pesquisa) {
			return false;
		}
		pesquisaDoObjetivo = null;
		return true;
	}
}
