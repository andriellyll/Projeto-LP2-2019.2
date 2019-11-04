package pacote;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	
	/**
	 * 
	 */
	
	private Problema problemaDaPesquisa;
	
	private Set<Pesquisador> pesquisadoresAssociados;
	
	/**
	 * 
	 */
	
	private Set<Objetivo> objetivosDaPesquisa;
	
	/**
	 * 
	 */
	
	private Set<Atividade> atividadesAssociadas;
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
		this.problemaDaPesquisa = null;
		this.objetivosDaPesquisa = new HashSet<>();
		this.atividadesAssociadas = new HashSet<>();
		this.pesquisadoresAssociados = new HashSet<>();
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
	 * Retorna o status de ativacao da pesquisa. O valor e um booleano true ou
	 * false.
	 * 
	 * @return - o status de ativacao da pesquisa
	 */

	public boolean getAtivacao() {
		return this.ehAtivada;
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
	 * Retorna a representacao em string da pesquisa. A representacao segue o padrao
	 * "CODIGO - Descricao - Campo de interesse".
	 * 
	 * @return - a representacao em string da pesquisa
	 */

	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
	}

//------------------------------------------------ Novas atualizacoes de Pesquisa --------------------------------------------------------------------------

	/**
	 * 
	 * 
	 * @param problema
	 * @return
	 */

	public boolean associaProblema(Problema problema) {
		if (problemaDaPesquisa == problema) {
			return false;
		} else if (problemaDaPesquisa != null) {
			throw new RuntimeException("Pesquisa ja associada a um problema.");
		}
		problemaDaPesquisa = problema;
		return true;
	}
	
	/**
	 * 
	 * 
	 * @param problema
	 * @return
	 */
	
	public boolean desassociaProblema(Problema problema) {
		if (problemaDaPesquisa != problema) {
			return false;
		}
		problemaDaPesquisa = null;
		return true;
	}
	
	/**
	 * 
	 * 
	 * @param objetivo
	 * @return
	 */
	
	public boolean associaObjetivo(Objetivo objetivo) {
		if (objetivosDaPesquisa.contains(objetivo)) {
			return false;
		}
		objetivosDaPesquisa.add(objetivo);
		return true;
	}
	
	/**
	 * 
	 * 
	 * @param objetivo
	 * @return
	 */
	
	public boolean desassociaObjetivo(Objetivo objetivo) {
		//tem que fazer excessao neste
		return true;
	}

	/**
	 * Associa uma atividade a pesquisa.
	 * 
	 * @param atividade - a atividade a ser adicionada na pesquisa
	 * @return - o booleano que representa se a atividade foi associada a pesquisa
	 */

	public boolean associaAtividade(Atividade atividade) {
			return atividadesAssociadas.add(atividade);
	}

	/**
	 * Desassocia uma atividade da pesquisa.
	 * 
	 * @param atividade - a atividade a ser desassociada
	 * @return - o booleano que representa se a atividade foi desassociada a
	 *         pesquisa
	 */

	public boolean desassociaAtividade(Atividade atividade) {
		if (getAtivacao()) {
			return atividadesAssociadas.remove(atividade);
		}
		throw new IllegalArgumentException("Pesquisa desativada.");
	}
	/**
	 * Procura nos atributos descricao e campo de interesse da pesquisa a palavra-chave passada como
	 * parametro
	 * 
	 * @param palavraChave palavra-chave que sera buscada 
	 * @return se a palavra-chave existir na String de descricao ou de campo de interesse, uma  lista com uma dessas  
	 *         strings (ou as duas) sera retornada. Se nao, sera retornada uma lista vazia
	 */
	public List<String> procuraPalavraChave(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();
		if (this.descricao.contains(palavraChave) || this.campoDeInteresse.contains(palavraChave)) {
			resultadosBusca.add(this.codigo + ": " + this.descricao);
		}
		return resultadosBusca;
	}
	
	public boolean associaPesquisador(Pesquisador pesquisador) {
		pesquisadoresAssociados.add(pesquisador);
		return true;
		
	}
	public boolean desassociaPesquisador(Pesquisador pesquisador) {
		pesquisadoresAssociados.remove(pesquisador);
		return true;
		
	}
}