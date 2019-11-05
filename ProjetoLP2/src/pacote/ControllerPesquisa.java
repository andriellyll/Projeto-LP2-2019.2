package pacote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.OrdenaResultados;

/**
 * Controller responsavel por gerenciar as pesquisas no sistema. As
 * funcionalidades basicas sao: cadastrar pesquisa, alterar pesquisa, encerrar
 * pesquisa, ativar pesquisa, exibir pesquisa verificar se a pesquisa e ativa.
 * 
 * @author Anna Beatriz Lucena
 */

public class ControllerPesquisa {

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
	 * Cria um novo controller de pesquisa (gerenciador), inicalizando os hashMaps
	 * pesquisas e codigos.
	 */
	
	public ControllerPesquisa() {
		this.pesquisas = new HashMap<>();
		this.codigos = new HashMap<>();
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
		verificaPesquisaExiste(codigo);

		Pesquisa pesquisa = this.pesquisas.get(codigo);
		if (pesquisa.getAtivacao()) {
			pesquisa.desativaPesquisa(motivo);
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

//------------------------------------------------ Novas atualizacoes de ControllerPesquisa ----------------------------------------------------------------

	/**
	 * 
	 * 
	 * @param codigo
	 */

	private void verificaPesquisaAtivada(String codigo) {
		if (pesquisas.get(codigo).getAtivacao() == false) {
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
	 * Verifica se a pesquisa existe, a partir de um codigo, e caso ela exista sera
	 * retornada.
	 * 
	 * @param codigo - o codigo da pesquisa a ser retornada
	 * @return - a pesquisa solicitada a partir do codigo
	 */

	public Pesquisa getPesquisa(String codigo) {
		verificaPesquisaExiste(codigo);
		verificaPesquisaAtivada(codigo);
		return this.pesquisas.get(codigo);
	}
	
	/**
	 * 
	 * 
	 * @param codigoPesquisa
	 * @param atividade
	 * @return
	 */

	public boolean associaAtividade(String codigoPesquisa, Atividade atividade) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		if(pesquisas.containsKey(codigoPesquisa)) {
			if (pesquisas.get(codigoPesquisa).getAtivacao()) {
				return pesquisas.get(codigoPesquisa).associaAtividade(atividade);
			}
			throw new IllegalArgumentException("Pesquisa desativada.");
		} 
		throw new IllegalArgumentException("Pesquisa nao encontrada.");
	}
	
	/**
	 * Procura em todos as pesquisas do mapa a palavra-chave passada como
	 * parametro
	 * 
	 * @param palavraChave palavra-chave que sera procurada
	 * @return Lista de Strings com os campos dos atributos de pesquisa que contiverem a palavra-chave
	 */
	
	public List<String> procuraPalavraChave(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();

		for (Pesquisa pesquisa: this.pesquisas.values()) {
			if (!pesquisa.procuraPalavraChave(palavraChave).isEmpty()) {
				resultadosBusca.addAll(pesquisa.procuraPalavraChave(palavraChave));	
			}
		}
		
		Collections.sort(resultadosBusca, new OrdenaResultados());
		
		return resultadosBusca;
	}
	
	/**
	 * 
	 * 
	 * @param codigoDaPesquisa
	 * @param pesquisador
	 * @return
	 */
	
	public boolean associaPesquisador(String codigoDaPesquisa, Pesquisador pesquisador) {
		verificaPesquisaExiste(codigoDaPesquisa);
		verificaPesquisaAtivada(codigoDaPesquisa);
		pesquisas.get(codigoDaPesquisa).associaPesquisador(pesquisador);
		return true;
		
	}
	
	/**
	 * 
	 * 
	 * @param codigoDaPesquisa
	 * @param pesquisador
	 * @return
	 */
	
	public boolean desassociaPesquisador(String codigoDaPesquisa, Pesquisador pesquisador) {
		verificaPesquisaExiste(codigoDaPesquisa);
		verificaPesquisaAtivada(codigoDaPesquisa);
		pesquisas.get(codigoDaPesquisa).desassociaPesquisador(pesquisador);
		return true;
	}
	
	/**
	 * 
	 * 
	 * @param listaDePesquisas
	 * @return
	 */
	
	private List<Pesquisa> ordenaPesquisasProblemas(Map<String, Pesquisa> listaDePesquisas) {
		List<Pesquisa> listaOrdenada = new ArrayList<>();
		List<Pesquisa> temProblema = new ArrayList<>();
		List<Pesquisa> naoTemProblema = new ArrayList<>();
		
		for (Pesquisa estudo : pesquisas.values()) {
			if (estudo.temProblema()) {
				temProblema.add(estudo);
			} else {
				naoTemProblema.add(estudo);
			}
		}
		Collections.sort(temProblema);
		Collections.sort(naoTemProblema);
		
		for (int i = 0; i < temProblema.size(); i++) {
			listaOrdenada.add(temProblema.get(i));
		}
		for(int i = 0; i < naoTemProblema.size(); i++) {
			listaOrdenada.add(temProblema.get(i));
		}
		
		return listaOrdenada;
	}
	
	/**
	 * 
	 * 
	 * @param listaDePesquisas
	 * @return
	 */
	
	private List<Pesquisa> ordenaPesquisasObjetivadas(Map<String, Pesquisa> listaDePesquisas) {
		List<Pesquisa> listaOrdenada = new ArrayList<>();
		
		return listaOrdenada;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	
	public String imprimePesquisas(String ordem) {
		String todasPesquisas = "";
		List<Pesquisa> pesquisasOrdenadas = new ArrayList<>();
		
		if ("PROBLEMA".equals(ordem)) {
			pesquisasOrdenadas = ordenaPesquisasProblemas(pesquisas);
		} else if ("OBJETIVOS".equals(ordem)) {
			pesquisasOrdenadas = ordenaPesquisasObjetivadas(pesquisas);
		} else if ("PESQUISA".equals(ordem)) {
			
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
}