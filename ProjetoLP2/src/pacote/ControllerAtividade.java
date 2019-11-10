package pacote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.OrdenaResultados;

/**
 * Para atingir um objetivo, especialmente os objetivos especificos, e
 * importante descrever e planejar atividades a serem realizadas para obter
 * resultados, e para se obter resultados destas atividades, e importante
 * elaborar itens para cumprimir estas atividades.
 * 
 * @author Henrique Lemos
 */

public class ControllerAtividade {

	/**
	 * Conjunto de Atividades reunidas em um mapa onde cada atividade e identificada
	 * pelo codigo A + valor começando a partir de 1 (ex: A13), gerado
	 * automaticamente pelo sistema. O idVago auxilia o gerador de codigo qual o
	 * proximo número disponivel para gerar um codigo.
	 * 
	 */

	private Map<String, Atividade> atividades;
	private int idVago = 1;
	private int idVagoItem = 1;

	/**
	 * Contrutor da Classe responsavel por controlar as informacoes das atividades.
	 * Ele inicializa o mapa de atividades.
	 */

	public ControllerAtividade() {
		this.atividades = new HashMap<>();
	}

	/**
	 * Metodo responsavel por gerar um codigo para cada atividade criada, a partir
	 * da uniao de uma string (A) e um inteiro (a partir de 1).
	 * 
	 * @return O codigo, no formato A + valor
	 */

	private String criadorCodigo() {
		String codigo = "A" + idVago;
		idVago += 1;
		return codigo;
	}

	/**
	 * Metodo responsavel por gerar um codigo para cada item criado, representado
	 * apenas por um inteiro (a partir de 1).
	 * 
	 * @return Um inteiro representando o codigo
	 */

	private int criadorCodigoItem() {
		int codigo = idVagoItem;
		idVagoItem += 1;
		return codigo;
	}

	/**
	 * Metodo responsavel por verificar se a partir de um codigo oferecido pelo
	 * usuario, ja existe, caso nao exista, ele retornara uma excessao dizendo
	 * "Atividade nao encontrada".
	 * 
	 * @param codigo valor ao qual vai ser utilizado para verificar se ja existe uma
	 *               atividade com este codigo
	 */

	private void verificaAtividadeExiste(String codigo) {
		if (atividades.containsKey(codigo) == false) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Metodo responsavel por cadastrar cada nova atividade, cada atividade
	 * planejada apresenta uma descricao do que deve ser feito, uma duracao
	 * planejada, resultados esperados e um risco associado.
	 * 
	 * @param descricao      valor que descreve a atividade
	 * @param nivelRisco     valor que classifica o nível de risco que pode ser em
	 *                       tres niveis: BAIXO, MEDIO, ALTO.
	 * @param descricaoRisco valor que descreve o que ocasiona o risco e o que deve
	 *                       ser feito para mitigar o risco.
	 * @return Uma String do codigo da atividade criada
	 */

	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricaoRisco,
				"Campo descricaoRisco nao pode ser nulo ou vazio.");
		String codigo = criadorCodigo();
		Atividade novaAtividade = new Atividade(codigo, descricao, nivelRisco, descricaoRisco);
		if (!atividades.containsKey(codigo)) {
			atividades.put(codigo, novaAtividade);
		}
		return codigo;
	}

	/**
	 * Metodo responsavel por apagar uma atividade.
	 * 
	 * @param codigo valor que identifica a atividade que deve ser apagada
	 */

	public void apagaAtividade(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(codigo);
		atividades.remove(codigo);
	}

	/**
	 * Metodo responsavel por exibir a atividade e seus itens selecionado pelo
	 * usuario.
	 * 
	 * @param codigo valor que identifica a atividade que deve ser exibida.
	 * @return impressao no formato "DESCRICAO (NIVEL_RISCO - DESCRICAO_RISCO) |
	 *         SITUACAO - ITEM".
	 */

	public String exibeAtividade(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(codigo);
		String retornoItens = atividades.get(codigo).exibeItens();
		if (retornoItens == null || "".equals(retornoItens.trim())) {
			return atividades.get(codigo).toString();
		}
		return atividades.get(codigo).toString() + " | " + retornoItens;
	}

	/**
	 * Metodo responsavel por cadastrar um novo item, em que os resultados esperados
	 * sao compostos de uma lista de itens que podem ser marcados como feitos ou
	 * nao.
	 * 
	 * @param codigo valor para referenciar a qual atividade, o novo item deve ser
	 *               adicionado
	 * @param item   valor que descreve o item a ser criado
	 */

	public void cadastraItem(String codigo, String item) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(item, "Item nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(codigo);
		int codigoItem = criadorCodigoItem();
		atividades.get(codigo).adicionaItem(item, codigoItem);
	}

	/**
	 * Metodo responsavel por apresentar a quantidade de itens pendentes na
	 * atividade selecionada pelo usuario.
	 * 
	 * @param codigo valor para referenciar a qual atividade, os itens pendentes
	 *               devem ser contados
	 * @return um inteiro identificando o numero de itens pendentes
	 */

	public int contaItensPendentes(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(codigo);
		return atividades.get(codigo).ItensPendentes();
	}

	/**
	 * Metodo responsavel por apresentar a quantidade de itens realizados na
	 * atividade selecionada pelo usuario.
	 * 
	 * @param codigo valor para referenciar a qual atividade, os itens pendentes
	 *               devem ser contados
	 * @return um inteiro identificando o numero de itens pendentes
	 */

	public int contaItensRealizados(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(codigo);
		return atividades.get(codigo).ItensRealizados();
	}

	/**
	 * Retorna uma atividade a partir do seu codigo identificador
	 * 
	 * @param codigo - o codigo identificador da atividade a ser retornada.
	 * @return a atividade solicitada atraves do codigo
	 */
	public Atividade getAtividade(String codigo) {
		verificaAtividadeExiste(codigo);
		return this.atividades.get(codigo);
	}

	/**
	 * Verifica se a atividade a ser executada existe, caso exista executa-a
	 * 
	 * @param codigoAtividade - o codigo da atividade a ser executada
	 * @param item            - o valor que representa a ordem de cadastro de um
	 *                        item na atividade
	 * @param duracao         - a duracao em horas da execucao do item
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		verificaAtividadeExiste(codigoAtividade);
		atividades.get(codigoAtividade).executaAtividade(item, duracao);
	}

	/**
	 * Procura em todos as atividades do mapa a palavra-chave passada como parametro
	 * 
	 * @param palavraChave palavra-chave que sera procurada
	 * @return Lista de Strings com os campos dos atributos de atividade que
	 *         contiverem a palavra-chave
	 */
	public List<String> procuraPalavraChave(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();

		for (Atividade atividade : this.atividades.values()) {
			if (!atividade.procuraPalavraChave(palavraChave).isEmpty()) {
				resultadosBusca.addAll(atividade.procuraPalavraChave(palavraChave));
			}
		}

		Collections.sort(resultadosBusca, new OrdenaResultados());

		return resultadosBusca;
	}

	/**
	 * Cadastra um resultado obtido pela atividade, a partir do codigo que
	 * represenrta a atividade e da String que representa o resultado.
	 * 
	 * @param codigoAtividade - o codigo que representa a atividade a ter um
	 *                        resultado cadastrado
	 * @param resultado       - a String que representa o resultado obtido pela
	 *                        atividade
	 * @return - o numero que representa a ordem de cadastro do resultado
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		verificaAtividadeExiste(codigoAtividade);
		return atividades.get(codigoAtividade).cadastraResultado(resultado);

	}

	/**
	 * Remove um resultado obtido pela atividade a partir do codigo da atividade e
	 * do numero que representa a ordem de cadastro do resultado. Caso a remocao for
	 * feita com sucesso sera retornado true, caso nao retornara false
	 * 
	 * @param codigoAtividade - o codigo que representa a atividade a ter o
	 *                        resultado removido
	 * @param numeroResultado - o numero que representa a ordem de cadastro do
	 *                        resultado
	 * @return - o booleano que representa se a remocao obteve sucesso (true) ou nao
	 *         (false)
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		if (atividades.containsKey(codigoAtividade)) {
			return atividades.get(codigoAtividade).removeResultado(numeroResultado);
		}
		throw new IllegalArgumentException("Atividade nao encontrada");
	}

	/**
	 * Retorna a listagem dos resultados cadastrados na atividade a partir do codigo
	 * da atividade
	 * 
	 * @param codigoAtividade - o codigo que representa a atividade a ter seus
	 *                        resultados listados
	 * @return - a representacao em string de todos os resultados obtidos na
	 *         atividade
	 */
	public String listaResultados(String codigoAtividade) {
		verificaAtividadeExiste(codigoAtividade);
		return atividades.get(codigoAtividade).listaResultados();
	}

	/**
	 * Retorna a duracao de execucao de uma atividade a partir do codigo da
	 * atividade
	 * 
	 * @param codigoAtividade - o codigo que representa a atividade que ira retornar
	 *                        sua duracao
	 * @return - o inteiro que representa a duracao (em horas) da execucao da
	 *         atividade
	 */
	public int getDuracao(String codigoAtividade) {
		verificaAtividadeExiste(codigoAtividade);
		return atividades.get(codigoAtividade).getDuracao();
	}

	public boolean associaPesquisa(Pesquisa pesquisa, String codigoAtividade) {
		verificaAtividadeExiste(codigoAtividade);
		return atividades.get(codigoAtividade).associaPesquisa(pesquisa);	
	}

	public boolean desassociaPesquisa(String codigoAtividade) {
		verificaAtividadeExiste(codigoAtividade);
		return atividades.get(codigoAtividade).desassociaPesquisa();
		
	}
}