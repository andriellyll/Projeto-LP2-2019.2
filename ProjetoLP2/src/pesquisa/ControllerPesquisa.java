package pesquisa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pesquisador.ControllerPesquisador;
import problemaObjetivo.ControllerProblemaObjetivo;
import system.Buscavel;
import system.OrdenaResultados;
import utils.ValidadorDeEntradas;

/**
 * Controller responsavel por gerenciar as pesquisas no sistema. As
 * funcionalidades basicas sao: cadastrar pesquisa, alterar pesquisa, encerrar
 * pesquisa, ativar pesquisa, exibir pesquisa verificar se a pesquisa e ativa.
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 */
public class ControllerPesquisa implements Buscavel {

	/**
	 * Armazena todas as pesquisas cadastradas no sistema.
	 */
	private Map<String, Pesquisa> pesquisas;

	/**
	 * Armazena os codigos(identificador da pesquisa) gerados pelo sistema, com o
	 * ojetivo de gerenciar os mesmos.
	 */
	private Map<String, Integer> codigos;
	
	/**
	 * Atributo que guarda o controlador de Pesquisador.
	 */
	private ControllerPesquisador controllerPesquisador;
	
	/**
	 * Atributo que guarda o controlador de Problema e Objetivo.
	 */
	private ControllerProblemaObjetivo controllerProblemaObjetivo;
	
	/**
	 * Atributo que indica qual estrategia de ordenacao deve ser usada ao sugerir
	 * uma proxima atividade a ser realizada.
	 */
	private String estrategia;

	/**
	 * Cria um novo controller de pesquisa (gerenciador), inicalizando os hashMaps
	 * pesquisas, controllers e codigos.
	 * 
	 * @param controllerProblemaObjetivo o controlador de Pesquisador.
	 * @param controllerPesquisador      o controlador de Problema e Objetivo.
	 */
	public ControllerPesquisa(ControllerPesquisador controllerPesquisador,
		ControllerProblemaObjetivo controllerProblemaObjetivo) {
		this.controllerPesquisador = controllerPesquisador;
		this.controllerProblemaObjetivo = controllerProblemaObjetivo;
		this.pesquisas = new HashMap<>();
		this.codigos = new HashMap<>();
		this.estrategia = "MAIS_ANTIGA";
	}

	/**
	 * Cadastra uma pesquisa no sistema a partir de uma descricao e de um campo de
	 * interesse. Caso os paramentros passados forem nulos ou vazios uma excecao
	 * sera lancada. Caso o campo de interesse for invalido (mais de 250 caracteres
	 * na totalidade ou menos de 3 caracteres cada interesse ou mais de 4
	 * interesses) uma excecao sera lancada. Caso os paramentros forem validos, o
	 * sistema gera um codigo(que sera o id da pesquisa) com as tres primeiras
	 * letras do campo de interesse mais um numero(1 se nao tiver uma pesquisa ja
	 * cadastrada com as mesmas letras iniciais ou incrementa 1 a cada nova pesquisa
	 * com as mesmas 3 letras iniciais. O formato do codigo segue o padrao "ANA1".
	 * 
	 * @param descricao        - a descricao da pesquisa
	 * @param campoDeInteresse - o campo de interesse da pesquisa
	 * @return - o codigo(identificador unico) gerado
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(campoDeInteresse, "Formato do campo de interesse invalido.");
		ValidadorDeEntradas.validaCampoDeInteresse(campoDeInteresse);
		String codigoChave = campoDeInteresse.substring(0, 3).toUpperCase();
		String codigoPesquisa;
		if (this.codigos.containsKey(codigoChave)) {
			int valor = this.codigos.get(codigoChave) + 1;
			this.codigos.put(codigoChave, valor);
			codigoPesquisa = codigoChave + Integer.toString(valor);
			this.pesquisas.put(codigoPesquisa, new Pesquisa(codigoPesquisa, descricao, campoDeInteresse));
		} else {
			this.codigos.put(codigoChave, 1);
			codigoPesquisa = codigoChave + "1";
			this.pesquisas.put(codigoPesquisa, new Pesquisa(codigoPesquisa, descricao, campoDeInteresse));
		}
		return codigoPesquisa;
	}

	/**
	 * Altera uma pesquisa a partir do codigo(identificador unico), de um parametro
	 * a ser alterado e do novo conteudo a ser atribuido ao paramentro. Caso os
	 * parametros passados pelo ususario forem vazios ou nulos uma excecao sera
	 * lancada. Se o atributo a ser alteradoo for "descricao"/"DESCRICAO" ou
	 * "campo"/"CAMPO" a atribuicao do novo valor sera feita com sucesso. Caso for
	 * outra string diferente uma excecao sera lancada.
	 * 
	 * @param codigo               - o identificador da pesquisa a ser alterada
	 * @param conteudoASerAlterado - o paramentro a ser alterado
	 * @param novoConteudo         - o novo conteudo a ser atribuido
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(conteudoASerAlterado,
				"Conteudo a ser alterado nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);
		Pesquisa pesquisa = this.pesquisas.get(codigo);
		if (!pesquisa.getAtivacao()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (conteudoASerAlterado.equals("descricao") || conteudoASerAlterado.equals("DESCRICAO")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			pesquisa.setDescricao(novoConteudo);
		} else if (conteudoASerAlterado.equals("campo") || conteudoASerAlterado.equals("CAMPO")) {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoConteudo, "Formato do campo de interesse invalido.");
			pesquisa.setCampo(novoConteudo);
		} else {
			ValidadorDeEntradas.validaEntradaNulaOuVazia(novoConteudo, "Formato do campo de interesse invalido.");
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}

	/**
	 * Desativa uma pesquisa a parir do seu identificador e de um motivo. Caso o
	 * motivo seja vazio ou nulo uma excecao sera lacada. Caso o status da pesquisa
	 * ja seja desativado uma excecao sera lancada, ja que so pode ser destivada se
	 * seu status for ativo. Caso o codigo passado for nulo ou vazio uma excecao
	 * sera lancada.
	 * 
	 * @param codigo - o identificador da pesquisa a ser desativada
	 * @param motivo - o motivo de desativacao da pesquisa
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(motivo, "Motivo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);
		Pesquisa pesquisa = this.pesquisas.get(codigo);
		if (pesquisa.getAtivacao()) {
			pesquisa.desativaPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

	/**
	 * Ativa uma pesquisa a partir do seu codigo identificador. Se a pesquisa ja
	 * tiver o status de ativada uma excecao sera lancada, ja que so e possivel
	 * ativar uma pesquisa com status de sesativada. Caso o codigo passado for nulo
	 * ou vazio uma excecao sera lancada.
	 * 
	 * @param codigo - o identificador da pesquisa a ser ativada
	 */
	public void ativaPesquisa(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);
		Pesquisa pesquisa = this.pesquisas.get(codigo);
		if (!pesquisa.getAtivacao()) {
			pesquisa.ativaPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		}
	}

	/**
	 * Exibe uma pesquisa (retorna a representacao em string da pesquisa) a partir
	 * do seu codigo identificador. Caso o codigo passado for nulo ou vazio uma
	 * excecao sera lancada.
	 * 
	 * @param codigo - o codigo da pesquisa a ser exibida
	 * @return - a representacao em string da pesquisa
	 */
	public String exibePesquisa(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);
		Pesquisa pesquisa = this.pesquisas.get(codigo);
		return pesquisa.toString();
	}

	/**
	 * Verifica se uma pesquisa e ativa a partir do seu codigo identificador. Caso o
	 * codigo passado for nulo ou vazio uma excecao sera lancada. Retonara true se o
	 * status da pesquisa for ativado ou false se o status for desativado.
	 * 
	 * @param codigo - o codigo da pesquisa a ser verificada
	 * @return - o valor booleano que representa o status de ativacao da pesquisa
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigo);
		Pesquisa pesquisa = this.pesquisas.get(codigo);
		return pesquisa.getAtivacao();
	}

//------------------------------------------ ControllerPesquisa (Parte 2) ------------------------------------------

	/**
	 * Metodo responsavel por associar um problema a uma pesquisa.
	 * 
	 * @param idPesquisa - valor de identificacao da pesquisa
	 * @param problema   objeto do problema a ser associado
	 * @return um booleano referente a situacao do processo
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		validaPesquisa(idPesquisa);
		return pesquisas.get(idPesquisa).associaProblema(controllerProblemaObjetivo.getProblema(idProblema));
	}

	/**
	 * Metodo responsavel por desassociar o problema de uma pesquisa.
	 * 
	 * @param idPesquisa - valor de identificacao da pesquisa
	 * @return um booleano referente a situacao do processo
	 */
	public boolean desassociaProblema(String idPesquisa) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validaPesquisa(idPesquisa);
		return pesquisas.get(idPesquisa).desassociaProblema();
	}

	/**
	 * Metodo responsavel por associar um objetivo a uma pesquisa.
	 * 
	 * @param idPesquisa - valor de identificacao da pesquisa
	 * @param objetivo   objeto do objetivo a ser associado
	 * @return um booleano referente a situacao do processo
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		boolean passo1 = controllerProblemaObjetivo.associaPesquisa(idObjetivo, this.getPesquisa(idPesquisa));
		return passo1 && pesquisas.get(idPesquisa).associaObjetivo(controllerProblemaObjetivo.getObjetivo(idObjetivo));
	}

	/**
	 * Metodo responsavel por desassociar um objetivo de uma pesquisa.
	 * 
	 * @param idPesquisa - valor de identificacao da pesquisa
	 * @param objetivo   objeto do objetivo a ser desassociado
	 * @return um booleano referente a situacao do processo
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		validaPesquisa(idPesquisa);
		boolean passo1 = controllerProblemaObjetivo.desassociaPesquisa(idObjetivo, pesquisas.get(idPesquisa));
		return passo1
				&& pesquisas.get(idPesquisa).desassociaObjetivo(controllerProblemaObjetivo.getObjetivo(idObjetivo));
	}

	/**
	 * Metodo responsavel por ordenar as pesquisas de acordo com seu codigo de
	 * identificacao, ordenando-os de forma decrescente.
	 * 
	 * @param listaDePesquisas - Lista das pesquisas a serem ordenadas
	 * @return lista ordenada das pesquisas
	 */
	private List<Pesquisa> ordenaPesquisas(Map<String, Pesquisa> listaDePesquisas) {
		List<Pesquisa> listaOrdenada = new ArrayList<>();
		for (Pesquisa estudo : listaDePesquisas.values()) {
			listaOrdenada.add(estudo);
		}
		Collections.sort(listaOrdenada);
		return listaOrdenada;
	}

	/**
	 * Metodo responsavel por imprimir as pesquisas, de acordo com a especificacao
	 * do usuario.
	 * 
	 * @param ordem - valor que representa a forma como o usuario quer que sejam
	 *              listados suas pesquisas
	 * @return uma string que imprime as pesquisas listadas, desejadas pelo usuario
	 */
	public String imprimePesquisas(String ordem) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(ordem, "Valor invalido da ordem");

		if (!"PROBLEMA".equals(ordem) && !"OBJETIVOS".equals(ordem) && !"PESQUISA".equals(ordem)) {
			throw new IllegalArgumentException("Valor invalido da ordem");
		}

		String todasPesquisas = "";
		List<Pesquisa> pesquisasOrdenadas = new ArrayList<>();

		if ("PROBLEMA".equals(ordem)) {
			List<Pesquisa> temProblema = new ArrayList<>();
			List<Pesquisa> naoTemProblema = new ArrayList<>();

			for (Pesquisa estudo : pesquisas.values()) {
				if (estudo.temProblema()) {
					temProblema.add(estudo);
				} else {
					naoTemProblema.add(estudo);
				}
			}
			Collections.sort(temProblema, new OrdenaPesquisaProblema());
			Collections.sort(naoTemProblema);
			pesquisasOrdenadas.addAll(temProblema);
			pesquisasOrdenadas.addAll(naoTemProblema);
		} else if ("OBJETIVOS".equals(ordem)) {
			List<Pesquisa> temObjetivos = new ArrayList<>();
			List<Pesquisa> naoTemObjetivos = new ArrayList<>();

			for (Pesquisa estudo : pesquisas.values()) {
				if (estudo.temObjetivos()) {
					temObjetivos.add(estudo);
				} else {
					naoTemObjetivos.add(estudo);
				}
			}
			Collections.sort(temObjetivos, new OrdenaPesquisasObjetivadas());
			Collections.sort(naoTemObjetivos);
			pesquisasOrdenadas.addAll(temObjetivos);
			pesquisasOrdenadas.addAll(naoTemObjetivos);
		} else if ("PESQUISA".equals(ordem)) {
			pesquisasOrdenadas = ordenaPesquisas(pesquisas);
		}

		for (int i = 0; i < pesquisasOrdenadas.size(); i++) {
			if (i == pesquisasOrdenadas.size() - 1) {
				todasPesquisas += pesquisasOrdenadas.get(i).toString();
			} else {
				todasPesquisas += pesquisasOrdenadas.get(i).toString() + " | ";
			}
		}
		return todasPesquisas;
	}

	/**
	 * Verifica se uma pesquisa esta ativada para uso, caso contrario ele apresenta
	 * um erro.
	 * 
	 * @param codigo - valor que vai ser verificado a ativacao ou nao
	 */
	private void verificaPesquisaAtivada(String codigo) {
		if (!pesquisas.get(codigo).getAtivacao()) {
			throw new RuntimeException("Pesquisa desativada.");
		}
	}

	/**
	 * Verifica se uma pesquisa existe cadastrada no sistema a partir do seu codigo
	 * identificador. Caso a pesquisa nao esteja cadastrada no sistema uma excecao
	 * sera lancada.
	 * 
	 * @param codigo - o codigo da pesquisa a ser verificada
	 */
	private void verificaPesquisaExiste(String codigo) {
		if (!pesquisas.containsKey(codigo)) {
			throw new RuntimeException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Verifica se a pesquisa existe e se ela esta ativada, a partir de um codigo, e
	 * caso ela exista sera retornada.
	 * 
	 * @param codigo - o codigo da pesquisa a ser retornada
	 * @return - a pesquisa solicitada a partir do codigo
	 */
	public Pesquisa getPesquisa(String codigo) {
		validaPesquisa(codigo);
		return this.pesquisas.get(codigo);
	}

	/**
	 * Valida uma pesquisa a partir do codigo da pesquisa. A validacao verifica se a
	 * pesquisa e cadastrada no sistema e se a mesma e ativada. Caso isso for falso,
	 * excecoes serao lancadas.
	 * 
	 * @param codigo - o codigo da pesquisa a ser validada
	 */
	public void validaPesquisa(String codigo) {
		verificaPesquisaExiste(codigo);
		verificaPesquisaAtivada(codigo);
	}

	/**
	 * Metodo que associa um Pesquisador a uma Pesquisa, recebendo o id da pesquisa
	 * e o email do pesquisador, e retorna um boolean que representa o sucesso da
	 * operacao.
	 * 
	 * @param codigoDaPesquisa a string com o codigo da Pesquisa.
	 * @param emailPesquisador string com o email do pesquisador.
	 * @return retorna um boolean que representa o sucesso da operacao.
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(emailPesquisador,
				"Campo emailPesquisador nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(idPesquisa);
		verificaPesquisaAtivada(idPesquisa);
		return this.pesquisas.get(idPesquisa)
				.associaPesquisador(controllerPesquisador.getPesquisador(emailPesquisador));
	}
	
	/**
	 * Metodo que desassocia um Pesquisador a uma Pesquisa, recebendo o id da
	 * pesquisa e o email do pesquisador, e retorna um boolean que representa o
	 * sucesso da operacao.
	 * 
	 * @param codigoDaPesquisa codigoDaPesquisa a string com o codigo da Pesquisa.
	 * @param emailPesquisador string com o email do pesquisador.
	 * @return retorna um boolean que representa o sucesso da operacao.
	 */
	public boolean desassociaPesquisador(String codigoDaPesquisa, String emailPesquisador) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoDaPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(emailPesquisador,
				"Campo emailPesquisador nao pode ser nulo ou vazio.");
		verificaPesquisaExiste(codigoDaPesquisa);
		verificaPesquisaAtivada(codigoDaPesquisa);
		return this.pesquisas.get(codigoDaPesquisa)
				.desassociaPesquisador(controllerPesquisador.getPesquisador(emailPesquisador));
	}
	
	/**
	 * Procura em todos as pesquisas do mapa a palavra-chave passada como parametro
	 * 
	 * @param palavraChave palavra-chave que sera procurada
	 * @return Lista de Strings com os campos dos atributos de pesquisa que
	 *         contiverem a palavra-chave
	 */
	public List<String> procuraPalavraChave(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Palavra nao pode ser nula ou vazia");
		ArrayList<String> resultadosBusca = new ArrayList<>();
		for (Pesquisa pesquisa : this.pesquisas.values()) {
			if (!pesquisa.procuraPalavraChave(palavraChave).isEmpty()) {
				resultadosBusca.addAll(pesquisa.procuraPalavraChave(palavraChave));
			}
		}
		Collections.sort(resultadosBusca, new OrdenaResultados());
		return resultadosBusca;
	}

//------------------------------------------ ControllerPesquisa (Parte 3) ------------------------------------------

	/**
	 * Metodo que valida a String estrategia, nao retorna nada e lança uma excecao
	 * caso aquela String seja invalida.
	 * 
	 * @param estrategia String estrategia a ser validada.
	 */
	private void validaEstrategia(String estrategia) {
		if (!(estrategia.equalsIgnoreCase("MAIS_ANTIGA") || estrategia.equalsIgnoreCase("MENOS_PENDENCIAS")
				|| estrategia.equalsIgnoreCase("MAIOR_RISCO") || estrategia.equalsIgnoreCase("MAIOR_DURACAO"))) {
			throw new IllegalArgumentException("Valor invalido da estrategia");
		}
	}
	
	/**
	 * Configura qual estrategia de ordenacao deve ser usada na hora de sugerir uma
	 * proxima atividade a ser realizada, recebe como parametro uma String com a
	 * estrategia e nao retorna nada.
	 * 
	 * @param estrategia String com a estrategia.
	 */
	public void configuraEstrategia(String estrategia) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(estrategia, "Estrategia nao pode ser nula ou vazia.");
		validaEstrategia(estrategia);
		this.estrategia = estrategia;
	}
	
	/**
	 * Metodo que recebe o codigo de uma pesquisa e retorna um codigo de uma
	 * sugestao de proxima atividade a ser realizada.
	 * 
	 * @param codigoPesquisa codigo da pesquisa a ser verificada.
	 * @return o codigo de uma sugestao de proxima atividade a ser realizada.
	 */
	public String proximaAtividade(String codigoPesquisa) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		validaPesquisa(codigoPesquisa);
		pesquisaEhAtiva(codigoPesquisa);
		return pesquisas.get(codigoPesquisa).proximaAtividade(this.estrategia);
	}
	
	/**
	 * Grava em um arquivo de texto um resumo da pesquisa, com as informacoes sobre
	 * os pesquisadores, objetivos, atividades e problema da pesquisa.
	 * @param codigoPesquisa ID da pesquisa cujo resumo será gravado
	 * @throws IOException
	 */
	public void gravarResumo(String codigoPesquisa) throws IOException {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		verificaPesquisaExiste(codigoPesquisa);
		pesquisas.get(codigoPesquisa).gravarResumo();
	}
	
	/**
	 * Grava em um arquivo de texto um resumo da pesquisa, com as informacoes sobre
	 * os pesquisadores, objetivos, atividades e problema da pesquisa.
	 * 
	 * @param codigoPesquisa ID da pesquisa cujos resultados serão gravados
	 * @throws IOException
	 */
	public void gravarResultados(String codigoPesquisa) throws IOException {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		verificaPesquisaExiste(codigoPesquisa);
		pesquisas.get(codigoPesquisa).gravarResultados();
	}
	
	/**
	 * Metodo responsavel por salvar as pesquisas
	 */
	public void salvar() {
		try {
			FileOutputStream saveFile = new FileOutputStream("pesquisa.dat");
			ObjectOutputStream stream = new ObjectOutputStream(saveFile);
			stream.writeObject(pesquisas);
			stream.close();
			FileOutputStream saveFile2 = new FileOutputStream("codigos.dat");
			ObjectOutputStream stream2 = new ObjectOutputStream(saveFile2);
			stream2.writeObject(codigos);
			stream2.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Metodo responsavel por recuperar o que foi salvo previamente
	 */
	@SuppressWarnings({ "unchecked" })
	public void carregar() {
		try {
			FileInputStream restFile = new FileInputStream("pesquisa.dat");
			ObjectInputStream stream = new ObjectInputStream(restFile);
			Map<String, Pesquisa> pesquisasCadastradas = (Map<String, Pesquisa>) stream.readObject();
			stream.close();
			this.pesquisas = pesquisasCadastradas;

			FileInputStream restFile2 = new FileInputStream("codigos.dat");
			ObjectInputStream stream2 = new ObjectInputStream(restFile2);
			Map<String, Integer> codigos = (Map<String, Integer>) stream2.readObject();
			stream2.close();
			this.codigos = codigos;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}