package pacote;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Para atingir um objetivo, e importante descrever e planejar atividades a
 * serem realizadas para obter resultados. Cada atividade planejada apresenta
 * uma descricao do que deve ser feito, uma duracao planejada, resultados
 * esperados, um risco associado, uma duração em dias, um codigo de identificao
 * unica e seus itens.
 * 
 * @author Henrique Lemos
 */

public class Atividade {

	/**
	 * Cada atividade apresenta uma descricao dela, um nivel de risco apresentado em
	 * ate tres niveis, a descricao deste risco, a quantidade de dias e seus itens
	 * para a conclusao da atividade, para obtencao de um resultado.
	 */
	private String codigo;
	private String descricao;
	private String nivelRisco;
	private String descricaoRisco;
	private List<Item> itens;
	
	/**
	 * A duracao de execucao da atividade
	 */
	private int duracao;
	
	/**
	 * A lista de resultados cadastrados na atividade.
	 */
	private Map<Integer, String> resultados;
	
	private int posicoesCadastradas;

	/**
	 * Contrutor de uma atividade, com sua descricao, nivel de risco, descricao do
	 * risco e o numero de dias.
	 * 
	 * @param descricao      valor que relata como e a atividade
	 * @param nivelRisco     valor que relata a itensidade desta atividade
	 * @param descricaoRisco valor que relata o porque esta atividade possivel
	 *                       determinado nivel de risco
	 */

	public Atividade(String codigo, String descricao, String nivelRisco, String descricaoRisco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.itens = new ArrayList<>();
		this.duracao = 0;
		this.resultados = new LinkedHashMap<>();
		this.posicoesCadastradas = 0;
	}

	/**
	 * Gera um identificador unico da pesquisa que representa o seu lugar na
	 * memoria.
	 * 
	 * @return o valor inteiro que representa o lugar da atividade na memoria
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Verifica se duas atividades sao iguais a partir dos seus codigos. Caso as
	 * atividades forem iguais retornara true, se forem diferentes retornara false.
	 * 
	 * @return - o booleano que representa se as atividades sao iguais ou nao.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Metodo responsavel por adicionar um novo item, na lista de itens, da
	 * atividade.
	 * 
	 * @param item   valor que descreve o novo item
	 * @param codigo valor de identificacao do novo item
	 */

	public void adicionaItem(String item, int codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(item, "Item nao pode ser nulo ou vazio.");
		Item novoItem = new Item(item, codigo);
		if (!itens.contains(novoItem)) {
			itens.add(novoItem);
		}
	}

	/**
	 * Metodo responsavel por contar o numero de itens pendentes.
	 * 
	 * @return um inteiro representando esta quantidade
	 */

	public int ItensPendentes() {
		int numPendentes = 0;

		for (int i = 0; i < itens.size(); i++) {
			if ("PENDENTE".equals(itens.get(i).getSituacao())) {
				numPendentes++;
			}
		}
		return numPendentes;
	}

	/**
	 * Metodo responssavel por contar o numero de itens realizados.
	 * 
	 * @return um inteiro representando esta quantidade
	 */

	public int ItensRealizados() {
		int numRealizados = 0;

		for (int i = 0; i < itens.size(); i++) {
			if ("REALIZADO".equals(itens.get(i).getSituacao())) {
				numRealizados++;
			}
		}
		return numRealizados;
	}

	/**
	 * Metodo resposavel por retornar uma listagem dos itens desta atividade.
	 * 
	 * @return Em formato de String, na representacao "SITUACAO - ITEM | "
	 */

	public String exibeItens() {
		String retorno = "";

		for (int i = 0; i < itens.size(); i++) {
			if (i == itens.size() - 1) {
				retorno += itens.get(i).toString();
			} else {
				retorno += itens.get(i).toString() + " | ";
			}
		}
		return retorno;
	}

	/**
	 * Metodo responsavel por retornar a String que representa a atividade.
	 * 
	 * @return A representacao segue no formato "DESCRICAO (NIVEL DE RISCO -
	 *         DESCRICAO RISCO)"
	 */

	@Override
	public String toString() {
		return descricao + " (" + nivelRisco + " - " + descricaoRisco + ")";
	}

//--------------------------------------------------------- Novas atualizacoes de Atividade ---------------------------------------------------------------------

	/**
	 * Executa um item de uma atividade a partir de um numero inteiro que representa a ordem de
	 * cadastro do item na atividade e da duracao da execucao do item.
	 * 
	 * @param item - o valor que representa a ordem de cadastro de um item na atividade
	 * @param duracao - a duracao em horas da execucao do item
	 */
	public void executaAtividade(int item, int duracao) {
		verificaItemExiste(item);
		if (itens.get((item - 1)).getSituacao().equals("REALIZADO")) {
			throw new IllegalArgumentException("Item ja executado.");
		}
		setDuracao(duracao);
		itens.get((item - 1)).executa();
	}
	
	/**
	 * Retorna a duracao em horas do tempo de execucao de uma atividade.
	 * 
	 * @return - a quantidade de horas de execucao de uma atividade
	 */
	public int getDuracao() {
		return this.duracao;
	}
	
	/**
	 * Altera a quantidade de horas que representa a duracao de execucao da
	 * atividade.
	 * 
	 * @param duracao - a quantidade de horas a ser adicionada na duracao da execuca
	 *                da atividade
	 */
	private void setDuracao(int duracao) {
		this.duracao += duracao;
	}
	
	/**
	 * Cadastra um resultado obtido pela atividade, a partir da String que
	 * representa o resultado
	 * 
	 * @param resultado - a String que representa o resultado obtido pela atividade
	 * @return - o numero que representa a ordem de cadastro do resultado
	 */
	public int cadastraResultado(String resultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(resultado, "Resultado nao pode ser nulo ou vazio.");
		posicoesCadastradas++;
		resultados.put(posicoesCadastradas, resultado);
		return posicoesCadastradas;
	}

	/**
	 * Remove um resultado obtido pela atividade a partir do numero que representa a
	 * ordem de cadastro do resultado. Caso a remocao for feita com sucesso sera
	 * retornado true, caso nao retornara false
	 * 
	 * @param numeroResultado - o numero que representa a ordem de cadastro do
	 *                        resultado
	 * 
	 * @return - o booleano que representa se a remocao obteve sucesso (true) ou nao
	 *         (false)
	 */
	public boolean removeResultado(int numeroResultado) {
		if (resultados.containsKey(numeroResultado)) {
			resultados.remove(numeroResultado);
			return true;
		}
		if(numeroResultado > posicoesCadastradas) {
			throw new IllegalArgumentException("Resultado nao encontrado.");
		}
		return false;
	}

	/**
	 * Gera e retorna a listagem dos resultados cadastrados na atividade
	 * 
	 * @return - a representacao em string de todos os resultados obtidos
	 */
	public String listaResultados() {
		String saida = "";
		for (String resultado : resultados.values()) {
			saida += resultado;
			saida += " | ";
		}
		return saida.substring(0, saida.length() - 3);
	}

	/**
	 * Verifica se um item existe a partir do numero inteiro que representa a ordem de
	 * cadastro do mesmo na atividade
	 * 
	 * @param item -  o valor que representa a ordem de cadastro de um item na atividade
	 */
	private void verificaItemExiste(int item) {
		if (itens.size() < item) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}
	}
	
	/**
	 * 
	 * 
	 * @param palavraChave
	 * @return
	 */
	private List<String> procuraPalavraItem(String palavraChave) {
		ArrayList<String> resultadosItens = new ArrayList<>();
		for (Item item : itens) {
			if (!item.procuraPalavraChave(palavraChave).isEmpty()) {
				resultadosItens.add(this.codigo + ": " + item.procuraPalavraChave(palavraChave));
			}
		}
		return resultadosItens;
	}

	/**
	 * Procura nos atributos descricao, itens e descricao do risco da atividade a
	 * palavra-chave passada como parametro
	 * 
	 * @param palavraChave palavra-chave que sera buscada
	 * @return se a palavra-chave existir em algum dos campos, uma lista com uma ou
	 *         mais dessas strings sera retornada. Se nao, sera retornada uma lista
	 *         vazia
	 */
	public List<String> procuraPalavraChave(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();
		if (this.descricao.contains(palavraChave)) {
			resultadosBusca.add(this.codigo + ": " + this.descricao);
		}
		if (this.descricaoRisco.contains(palavraChave)) {
			resultadosBusca.add(this.codigo + ": " + this.descricaoRisco);
		}
		if (!procuraPalavraItem(palavraChave).isEmpty()) {
			resultadosBusca.addAll(procuraPalavraItem(palavraChave));
		}
		return resultadosBusca;
	}
}