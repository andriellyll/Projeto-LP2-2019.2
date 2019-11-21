package pesquisa;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import atividade.Atividade;
import atividade.OrdenaAtividadeDuracao;
import atividade.OrdenaAtividadePendencias;
import atividade.OrdenaAtividadeRisco;
import pesquisador.Pesquisador;
import problemaObjetivo.Objetivo;
import problemaObjetivo.Problema;
import utils.ValidadorDeEntradas;

/**
 * A pesquisa e atividade base para a estruturação e criação de um novo
 * conhecimento. Cada pesquisa possui uma descricao (resumo da pesquisa a ser
 * realizada), um campo de interesse (marcador da área ou tema a ser
 * colocado),um codigo(identificador unico - gerado a partir das tres primeiras
 * letras do ampo de interesse + o numero de vezes que ja foi usado). Toda
 * pesquisa quando iniciada e ativada, caso queira desativar um motivo deve ser
 * adicionado.
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 */
public class Pesquisa implements Comparable<Pesquisa>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7431197778358334391L;

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
	 * Representa o estado de ativacao da pesquisa. Pode assumir o valor true ou
	 * false.
	 */
	private boolean ehAtivada;

	/**
	 * Representa o problema associado a esta pesquisa.
	 */
	private Problema problemaDaPesquisa;

	/**
	 * Representa uma lista dos objetivos associados a esta pesquisa.
	 */
	private Set<Objetivo> objetivosDaPesquisa;

	/**
	 * Conjunto de pesquisadores associados a pesquisa.
	 */
	private Set<Pesquisador> pesquisadoresAssociados;
	
	/**
	 * Conjunto de atividades associadas a pesquisa.
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
		this.objetivosDaPesquisa = new TreeSet<>();
		this.pesquisadoresAssociados = new LinkedHashSet<>();
		this.atividadesAssociadas = new TreeSet<>();
	}

	/**
	 * Desativa a pesquisa (altera a ativacao da pesquisa para false) a partir de um
	 * motivo(uma string livre). Caso o motivo seja vazio ou nulo uma excecao sera
	 * lancada.
	 * 
	 * @param motivo - o motivo de desativacao da pesquisa
	 */
	public void desativaPesquisa() {
		this.ehAtivada = false;
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

//------------------------------------------ Pesquisa (Parte 2) ------------------------------------------

	/**
	 * Metodo responsavel por verificar se esta pesquisa possui um problema
	 * associado a ela.
	 * 
	 * @return um booleano, que true representa sim, e false, nao
	 */
	public boolean temProblema() {
		if (problemaDaPesquisa != null) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo responsavel por associa a esta pesquisa um unico problema, onde caso
	 * esse problema ja esteja associado ele retorna false e caso exista ja um
	 * problema salvo na pesquisa, ele reporta um erro.
	 * 
	 * @param problema - valor que representa problema ao qual a pesquisa foi
	 *                 referenciada
	 * @return um valor booleano correspondente a especificacao que o problema
	 *         pertence
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
	 * Metodo responsavel por desassociar o problema desta pesquisa, onde caso não
	 * tenha nenhum problema associado a esta pesquisa, ele retorna false.
	 * 
	 * @return um booleano referente a situacao do processo
	 */
	public boolean desassociaProblema() {
		if (problemaDaPesquisa != null) {
			problemaDaPesquisa = null;
			return true;
		}
		return false;
	}

	/**
	 * Metodo responsavel por associar um objetivo especifico a pesquisa, onde caso
	 * este objetivo ja pertenca a lista de objetivos da pesquisa, ele retorna
	 * false.
	 * 
	 * @param objetivo - valor do objetivo a ser associado
	 * @return um booleano referente a situacao do processo
	 */
	public boolean associaObjetivo(Objetivo objetivo) {
		if (objetivosDaPesquisa.contains(objetivo)) {
			return false;
		}
		objetivosDaPesquisa.add(objetivo);
		return true;
	}

	/**
	 * Metodo responavel por desassociar um objetivo especifico a pesquisa, onde
	 * caso esse objetivo nao contenha na lista de objetivos da pesquisa, ele
	 * retorna false.
	 * 
	 * @param objetivo - valor do objetivo a ser associado
	 * @return um booleano referente a situacao do processo
	 */
	public boolean desassociaObjetivo(Objetivo objetivo) {
		if (!objetivosDaPesquisa.contains(objetivo)) {
			return false;
		}
		objetivosDaPesquisa.remove(objetivo);
		return true;
	}

	/**
	 * Metodo responsavel por resgatar o codigo da Pesquisa
	 * 
	 * @return em string, o codigo da pesquisa
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo responsavel por comparar duas pesquisas, pelos seus codigos de
	 * identificacao, para realizar uma ordenacao destas pesquisas.
	 * 
	 * @param p pesquisa a ser comparada
	 * @return comparacao das pesquisas
	 */
	@Override
	public int compareTo(Pesquisa p) {
		return p.getCodigo().compareTo(this.codigo);
	}

	/**
	 * Metodo responsavel por resgatar o ID do problema da pesquisa.
	 * 
	 * @return uma string representando o codigo
	 */
	public String getIdProblema() {
		return problemaDaPesquisa.getCodigo();
	}

	/**
	 * Metodo responsavel por verificar se esta pesquisa possui algum objetivo
	 * associado a ela.
	 * 
	 * @return um booleano, onde true representa sim, e false nao
	 */
	public boolean temObjetivos() {
		if (objetivosDaPesquisa.size() >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo responsavel por resgatar a quantidade de objetivos que esta pesquisa
	 * possui.
	 * 
	 * @return um inteiro representando a quantidade
	 */
	public int getNumObjetivos() {
		return this.objetivosDaPesquisa.size();
	}

	/**
	 * Metodo que associa um Pesquisador, recebendo o objeto Pesquisador e retorna
	 * um boolean que representa o sucesso da operacao.
	 * 
	 * @param pesquisador o objeto Pesquisador.
	 * @return um boolean que representa o sucesso da operacao.
	 */
	public boolean associaPesquisador(Pesquisador pesquisador) {
		if (pesquisadoresAssociados.contains(pesquisador)) {
			return false;
		}
		pesquisadoresAssociados.add(pesquisador);
		return true;

	}

	/**
	 * Metodo que desassocia um Pesquisador, recebendo o objeto Pesquisador e
	 * retorna um boolean que representa o sucesso da operacao.
	 * 
	 * @param pesquisador o objeto Pesquisador.
	 * @return um boolean que representa o sucesso da operacao.
	 */
	public boolean desassociaPesquisador(Pesquisador pesquisador) {
		if (!pesquisadoresAssociados.contains(pesquisador)) {
			return false;
		}
		pesquisadoresAssociados.remove(pesquisador);
		return true;
	}

	/**
	 * Associa uma atividade a pesquisa a partir da atividade passasa.
	 * 
	 * @param atividade - a atividade a ser adicionada na pesquisa
	 * @return - o booleano que representa se a atividade foi associada a pesquisa
	 */

	public boolean associaAtividade(Atividade atividade) {
		return atividadesAssociadas.add(atividade);
	}

	/**
	 * Desassocia uma atividade da pesquisa a partir da atividade passada.
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
	 * Procura nos atributos descricao e campo de interesse da pesquisa a
	 * palavra-chave passada como parametro
	 * 
	 * @param palavraChave palavra-chave que sera buscada
	 * @return se a palavra-chave existir na String de descricao ou de campo de
	 *         interesse, uma lista com uma dessas strings (ou as duas) sera
	 *         retornada. Se nao, sera retornada uma lista vazia
	 */
	public List<String> procuraPalavraChave(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Palavra nao pode ser nula ou vazia");
		ArrayList<String> resultadosBusca = new ArrayList<>();
		if (this.descricao.contains(palavraChave)) {
			resultadosBusca.add(this.codigo + ": " + this.descricao);
		}
		if (this.campoDeInteresse.contains(palavraChave)) {
			resultadosBusca.add(this.codigo + ": " + this.campoDeInteresse);
		}
		return resultadosBusca;
	}

//------------------------------------------ Pesquisa (Parte 2) ------------------------------------------

	/**
	 * Metodo que retorna um codigo que sugere uma proxima atividade a ser realizada
	 * de acordo com uma estrategia.
	 * 
	 * @param estrategia a estratagia passada pelo controller, que embasa como voce
	 *                   deve priorizar as atividades a serem realizadas.
	 * @return o codigo da atividade sugerida.
	 */
	public String proximaAtividade(String estrategia) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(estrategia, "Estrategia nao pode ser nula ou vazia.");
		if (!verificaPendencias()) {
			throw new RuntimeException("Pesquisa sem atividades com pendencias.");
		}
		if (estrategia.equalsIgnoreCase("MAIS_ANTIGA")) {
			return estrategiaMaisAntiga();
		} else if (estrategia.equalsIgnoreCase("MENOS_PENDENCIAS")) {
			return estrategiaMenosPendencias();
		} else if (estrategia.equalsIgnoreCase("MAIOR_RISCO")) {
			return estrategiaMaiorRisco();
		} else if (estrategia.equalsIgnoreCase("MAIOR_DURACAO")) {
			return estrategiaMaiorDracao();
		} else {
			throw new IllegalArgumentException("Valor invalido da estrategia");
		}
	}
	
	/**
	 * Metodo privado usado no metodo "proximaAtividade", que ordena as atividades
	 * de acordo com a estrategia de maior duração.
	 * 
	 * @return o codigo da atividade sugerida.
	 */
	private String estrategiaMaiorDracao() {
		ArrayList<Atividade> atividades = new ArrayList<Atividade>(this.atividadesAssociadas);
		Collections.sort(atividades, new OrdenaAtividadeDuracao());
		return atividades.get(atividades.size() - 1).getCodigo();
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	private String estrategiaMaiorRisco() {
		String saida = "";
		ArrayList<Atividade> atividades = new ArrayList<Atividade>(this.atividadesAssociadas);
		Collections.sort(atividades, new OrdenaAtividadeRisco());
		for (Atividade atividade : atividades) {
			if (atividade.ItensPendentes() != 0 && atividade.getRisco().equalsIgnoreCase("ALTO")) {
				saida = atividade.getCodigo();
			}
		}
		return saida;
	}
	
	/**
	 * Metodo privado usado no metodo "proximaAtividade", que ordena as atividades
	 * de acordo com a estrategia de menos pendencias.
	 * 
	 * @return o codigo da atividade sugerida.
	 */
	private String estrategiaMenosPendencias() {
		String saida = "";
		ArrayList<Atividade> atividades = new ArrayList<Atividade>(this.atividadesAssociadas);
		Collections.sort(atividades, new OrdenaAtividadePendencias());
		for (Atividade atividade : atividades) {
			if (atividade.ItensPendentes() != 0) {
				saida = atividade.getCodigo();
				return saida;
			}
		}
		return saida;
	}
	
	/**
	 * Metodo privado usado no metodo "proximaAtividade", que ordena as atividades
	 * de acordo com a estrategia de atividade mais antiga.
	 * 
	 * @return o codigo da atividade sugerida.
	 */
	private String estrategiaMaisAntiga() {
		String saida = "";
		for (Atividade atividade : atividadesAssociadas) {
			if (atividade.ItensPendentes() != 0) {
				return atividade.getCodigo();
			}
		}
		return saida;
	}
	
	/**
	 * Metodo auxiliar que gera a String resumo da pesquisa, contendo suas
	 * informacoes (pesquisadores, objetivos, atividades e problema).
	 * 
	 * @return a String resumo
	 */
	private String gerarResumo() {
		String resumo = "- Pesquisa: " + this.toString() + System.lineSeparator() + "\t- Pesquisadores:";
		for (Pesquisador pesquisador : pesquisadoresAssociados) {
			resumo += System.lineSeparator() + "\t\t- " + pesquisador.toString();
		}
		if (this.problemaDaPesquisa != null) {
			resumo += System.lineSeparator() + "\t- Problema:" + System.lineSeparator() + "\t\t- "
					+ this.problemaDaPesquisa.toString() + System.lineSeparator() + "\t- Objetivos:";
		}
		for (Objetivo objetivo : objetivosDaPesquisa) {
			resumo += System.lineSeparator() + "\t\t- " + objetivo.toString();
		}
		resumo += System.lineSeparator() + "\t- Atividades:";
		for (Atividade atividade : atividadesAssociadas) {
			resumo += System.lineSeparator() + "\t\t- " + atividade.exibeAtividade();
		}
		return resumo + " ";
	}

	/**
	 * Grava em um arquivo de texto um resumo da pesquisa, com as informacoes sobre
	 * os pesquisadores, objetivos, atividades e problema da pesquisa.
	 * 
	 * @throws IOException
	 * 
	 */
	public void gravarResumo() throws IOException {
		OutputStream out = new FileOutputStream(new File("_" + this.codigo + ".txt"));
		String resumo = gerarResumo();
		out.write(resumo.getBytes(), 0, resumo.length());
		out.close();
	}

	/**
	 * Metodo auxiliar que gera uma String que contem os resultados da pesquisa e os
	 * itens realizados.
	 * 
	 * @return String dos resultados
	 */
	private String resultadosPesquisa() {
		String resultados = "- Pesquisa: " + this.toString() + System.lineSeparator() + "\t- Resultados:";
		for (Atividade atividade : atividadesAssociadas) {
			resultados += System.lineSeparator() + "\t\t" + atividade.getResultados();
		}
		return resultados;
	}

	/**
	 * Grava em um arquivo de texto um resumo da pesquisa, com as informacoes sobre
	 * os pesquisadores, objetivos, atividades e problema da pesquisa.
	 * 
	 * @throws IOException
	 * 
	 */
	public void gravarResultados() throws IOException {
		OutputStream out = new FileOutputStream(new File(this.codigo + "-Resultados.txt"));
		String resultados = resultadosPesquisa();
		out.write(resultados.getBytes(), 0, resultados.length());
		out.close();
	}


	/**
	 * Metodo privado que verifica se as atividades possuem pendencias.
	 * 
	 * @return um boolean que indica se ha ou nao pendencias.
	 */
	private boolean verificaPendencias() {
		for (Atividade atividade : atividadesAssociadas) {
			if (atividade.ItensPendentes() != 0) {
				return true;
			}
		}
		return false;
	}
}