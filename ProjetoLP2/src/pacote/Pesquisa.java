package pacote;

import java.util.HashSet;
import java.util.Set;

/**
 * A pesquisa e atividade base para a estruturação e criação de um novo
 * conhecimento. Cada pesquisa possui uma descricao (resumo da pesquisa a ser
 * realizada), um campo de interesse (marcador da área ou tema a ser
 * colocado),um codigo(identificador unico - gerado a partir das tres primeiras
 * letras do ampo de interesse + o numero de vezes que ja foi usado). Toda
 * pesquisa quando iniciada e ativada, caso queira desativar um motivo deve ser
 * adicionado.
 * 
 * @author Anna Beatriz Lucena
 *
 */
public class Pesquisa {

	/**
	 * Representa a decricao da pesquisa.
	 */
	private String descricao;

	/**
	 * Representa o campo de interesse da pesquisa.
	 */
	private String campoDeInteresse;

	/**
	 * Representa o codigo da pesquisa.
	 */
	private String codigo;

	/**
	 * Representa o motivo de desativacao da pesquisa.
	 */
	private String motivoDeDesativacao;

	/**
	 * Representa o estado de ativacao da pesquisa. Pode assumir o valor true ou
	 * false.
	 */
	private boolean ehAtivada;

	private Set <Atividade> atividadesAssociadas;
	
	/**
	 * Cria uma nova pesquisa a partir do codigo(identificador unico), da descricao
	 * e do campo de interesse. Caso os parametros forem nulos ou vazios excecoes
	 * serao lancadas. Caso o formato do campo de interesse for invalido, uma
	 * excecao sera lancada.
	 * 
	 * @param codigo           - o codigo da pesquisa
	 * @param descricao        - a descricao da pesquisa
	 * @param campoDeInteresse - o campo de interesse da pesquisa
	 */
	public Pesquisa(String codigo, String descricao, String campoDeInteresse) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(campoDeInteresse, "Formato do campo de interesse invalido.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaCampoDeInteresse(campoDeInteresse);

		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
		this.ehAtivada = true;
		this.atividadesAssociadas = new HashSet<>();

	}

	/**
	 * Altera a descricao da pesquisa, atribuindo o novo valor passado como
	 * parametro. Caso a nova descricao passada seja vazia ou nula uma excecao sera
	 * lancada.
	 * 
	 * @param novaDescricao - a nova descricao da pesquisa
	 */
	public void setDescricao(String novaDescricao) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novaDescricao, "Descricao nao pode ser nula ou vazia.");
		this.descricao = novaDescricao;

	}

	/**
	 * Altera o campo de interesse da pesquisa, atribuindo o novo valor passado como
	 * parametro. Caso o novo campo de interesse da pesquisa seja vazio ou nulo ou
	 * tenha seu formato invalido uma excecao sera lancada.
	 * 
	 * @param novoCampo - o novo campo de interesse da pesquisa
	 */
	public void setCampo(String novoCampo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoCampo, "Formato do campo de interesse invalido.");
		ValidadorDeEntradas.validaCampoDeInteresse(novoCampo);
		this.campoDeInteresse = novoCampo;

	}

	/**
	 * Retorna o status de ativacao da pesquisa. O valor e um booleano true ou
	 * false.
	 * 
	 * @return - o status de ativacao da pesquisa
	 */
	public boolean getAtivacao() {

		return this.ehAtivada;
	}

	/**
	 * Desativa a pesquisa (altera a ativacao da pesquisa para false) a partir de um
	 * motivo(uma string livre). Caso o motivo seja vazio ou nulo uma excecao sera
	 * lancada.
	 * 
	 * @param motivo - o motivo de desativacao da pesquisa
	 */
	public void desativaPesquisa(String motivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(motivo, "Motivo nao pode ser nulo ou vazio.");
		this.ehAtivada = false;
		this.motivoDeDesativacao = motivo;

	}

	/**
	 * Ativa a pesquisa (altera a ativacao da pesquisa para true)
	 */
	public void ativaPesquisa() {
		this.ehAtivada = true;

	}

	/**
	 * Retorna a representacao em string da pesquisa. A representacao segue o padrao
	 * "CODIGO - Descricao - Campo de interesse".
	 * 
	 * @return - a representacao em string da pesquisa
	 */
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
	}

	/**
	 * Gera um identificador unico da pesquisa que representa o seu lugar na
	 * memoria.
	 * 
	 * @return - o valor inteiro que representa a posicao da pesquisa na memoria
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Verifica se duas pesquisas sao iguais a partir dos seus codigos. Caso as
	 * pesquisas forem iguais retornara true, se forem diferentes retornara false.
	 * 
	 * @return - o booleano que representa se as pesquisas sao iguais ou nao.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Associa uma atividade a pesquisa.
	 * 
	 * @param atividade - a atividade a ser adicionada na pesquisa
	 * @return - o booleano que representa se a atividade foi associada a pesquisa
	 */
	public boolean associaAtividade(Atividade atividade) {
		if(getAtivacao()) {
		return atividadesAssociadas.add(atividade);
		}
		throw new IllegalArgumentException("Pesquisa desativada.");
	}

}