package pacote;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
public class ControllerAtividade implements Buscavel {

	/**
	 * Conjunto de Atividades reunidas em um mapa onde cada atividade e identificada
	 * pelo codigo A + valor começando a partir de 1 (ex: A13), gerado
	 * automaticamente pelo sistema.
	 */
	private Map<String, Atividade> atividades;

	private int numeroAtividades;

	private ControllerPesquisa controllerPesquisa;

	/**
	 * Contrutor da Classe responsavel por controlar as informacoes das atividades.
	 * Ele inicializa o mapa de atividades.
	 * 
	 * @param controllerPesquisa
	 */
	public ControllerAtividade(ControllerPesquisa controllerPesquisa) {
		this.controllerPesquisa = controllerPesquisa;
		this.atividades = new HashMap<>();
	}

	/**
	 * Metodo responsavel por gerar um codigo para cada atividade criada, a partir
	 * da uniao de uma string (A) e um inteiro (a partir de 1).
	 * 
	 * @return O codigo, no formato A + valor
	 */
	private String criadorCodigo() {
		String codigo = "A" + (numeroAtividades + 1);
		numeroAtividades++;
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
	private void verificaAtividadeExiste(String codigo, String mensagem) {
		if (atividades.containsKey(codigo) == false) {
			throw new IllegalArgumentException(mensagem);
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
		verificaAtividadeExiste(codigo, "Atividade nao encontrada");
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
		verificaAtividadeExiste(codigo, "Atividade nao encontrada");
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
		verificaAtividadeExiste(codigo, "Atividade nao encontrada");
		atividades.get(codigo).adicionaItem(item);
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
		verificaAtividadeExiste(codigo, "Atividade nao encontrada");
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
		verificaAtividadeExiste(codigo, "Atividade nao encontrada");
		return atividades.get(codigo).ItensRealizados();
	}

//--------------------------------- ControllerAtividade (Parte 2) -------------------------------------------

	/**
	 * Retorna uma atividade a partir do seu codigo identificador
	 * 
	 * @param codigo - o codigo identificador da atividade a ser retornada.
	 * @return a atividade solicitada atraves do codigo
	 */
	public Atividade getAtividade(String codigo) {
		verificaAtividadeExiste(codigo, "Atividade nao encontrada");
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
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaNumeroNegativo(item, "Item nao pode ser nulo ou negativo.");
		ValidadorDeEntradas.verificaNumeroNegativo(duracao, "Duracao nao pode ser nula ou negativa.");

		verificaAtividadeExiste(codigoAtividade, "Atividade nao encontrada");
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
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Palavra nao pode ser nula ou vazia");
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
	 * representa a atividade e da String que representa o resultado.
	 * 
	 * @param codigoAtividade - o codigo que representa a atividade a ter um
	 *                        resultado cadastrado
	 * @param resultado       - a String que representa o resultado obtido pela
	 *                        atividade
	 * @return - o numero que representa a ordem de cadastro do resultado
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(resultado, "Resultado nao pode ser nulo ou vazio.");

		verificaAtividadeExiste(codigoAtividade, "Atividade nao encontrada");
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
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaNumeroNegativo(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");

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
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");

		verificaAtividadeExiste(codigoAtividade, "Atividade nao encontrada");
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
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");

		verificaAtividadeExiste(codigoAtividade, "Atividade nao encontrada");
		return atividades.get(codigoAtividade).getDuracao();
	}

	/**
	 * Associa uma pesquisa a uma atividade a partir do codigo da pesquisa a ser
	 * associada e do codigo da atividade.
	 * 
	 * @param codigoPesquisa  - o codigo da pesquisa a ser associada a uma atividade
	 * @param codigoAtividade - o codigo da atividade a ter uma pesquisa associada
	 * @return - valor booleano true (se a associacao obtiver sucesso) e false (caso
	 *         a associacao nao obtenha sucesso)
	 */
	public boolean associaPesquisa(String codigoPesquisa, String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa,
				"Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(codigoAtividade, "Atividade nao encontrada");
		controllerPesquisa.getPesquisa(codigoPesquisa).associaAtividade(atividades.get(codigoAtividade));
		return atividades.get(codigoAtividade).associaPesquisa(controllerPesquisa.getPesquisa(codigoPesquisa));
	}

	/**
	 * Desassocia uma pesquisa de uma atividade a partir do codigo da pesquisa a ser
	 * desassociada e do codigo da atividade.
	 * 
	 * @param codigoPesquisa  - o codigo da pesquisa a ser desassociada
	 * @param codigoAtividade - o codigo da atividade a ter uma pesquisa
	 *                        desassociada
	 * @return - valor booleano true (se a desassociacao obtiver sucesso) e false
	 *         (caso a desassociacao nao obtenha sucesso)
	 */
	public boolean desassociaPesquisa(String codigoPesquisa, String codigoAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa,
				"Campo codigoPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoAtividade,
				"Campo codigoAtividade nao pode ser nulo ou vazio.");
		controllerPesquisa.validaPesquisa(codigoPesquisa);

		verificaAtividadeExiste(codigoAtividade, "Atividade nao encontrada");
		controllerPesquisa.getPesquisa(codigoPesquisa).desassociaAtividade(atividades.get(codigoAtividade));
		return atividades.get(codigoAtividade).desassociaPesquisa();
	}

//----------------------------- ControllerAtividade (Parte 3) ------------------------------------------

	/**
	 * 
	 * 
	 * @param idPrecedente
	 * @param idSubsequente
	 */
	public void defineProximaAtividade(String idPrecedente, String idSubsequente) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idSubsequente, "Atividade nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(idPrecedente, "Atividade nao encontrada.");
		verificaAtividadeExiste(idSubsequente, "Atividade nao encontrada.");
		if (atividades.get(idPrecedente).existeProximo()) {
			throw new RuntimeException("Atividade ja possui uma subsequente.");
		}
		atividades.get(idPrecedente).adicionaNaCadeia(atividades.get(idSubsequente));
	}

	/**
	 * 
	 * 
	 * @param idPrecedente
	 */
	public void tiraProximaAtividade(String idPrecedente) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(idPrecedente, "Atividade nao encontrada.");
		atividades.get(idPrecedente).removeSeguinteNaCadeia();
	}

	/**
	 * 
	 * 
	 * @param idPrecedente
	 * @return
	 */
	public int contaProximos(String idPrecedente) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(idPrecedente, "Atividade nao encontrada.");
		return atividades.get(idPrecedente).contaSeguintesNaCadeia();
	}

	/**
	 * 
	 * 
	 * @param idAtividade
	 * @return
	 */
	public String pegaMaiorRiscoAtividades(String idAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(idAtividade, "Atividade nao encontrada.");
		return atividades.get(idAtividade).atividadeMaiorRisco(null);
	}

	/**
	 * 
	 * 
	 * @param idAtividade
	 * @param enesimaAtividade
	 * @return
	 */
	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		verificaAtividadeExiste(idAtividade, "Atividade nao encontrada.");
		return atividades.get(idAtividade).pegaProximo(enesimaAtividade);
	}

	/**
	 * Metodo responsavel por salvar as atividades
	 */
	public void salvar() {

		try {
			FileOutputStream saveFile = new FileOutputStream("atividade.dat");
			ObjectOutputStream stream = new ObjectOutputStream(saveFile);
			stream.writeObject(atividades);

			OutputStream out = new FileOutputStream(new File("numeroAtividades.txt"));

			out.write(numeroAtividades);

			out.close();

			stream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * Metodo responsavel por recuperar o que foi salvo previamente
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void carregar() {

		try {
			FileInputStream restFile = new FileInputStream("atividade.dat");
			ObjectInputStream stream = new ObjectInputStream(restFile);
			Map<String, Atividade> atividadesCadastradas = (Map<String, Atividade>) stream.readObject();
			stream.close();
			this.atividades = atividadesCadastradas;

			File file = new File("numeroAtividades.txt");
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			this.numeroAtividades = fis.read();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}