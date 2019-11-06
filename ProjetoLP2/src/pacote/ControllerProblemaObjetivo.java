package pacote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/**
 * Classe controladora das classes Problema e Objetivo.
 * @author Helen Bento Cavalcanti
 */
import java.util.Map;

import utils.OrdenaResultados;

public class ControllerProblemaObjetivo {

	/**
	 * Coleção que armazena os problemas, tendo como chave uma string e como valor
	 * um objeto Problema.
	 */

	private Map<String, Problema> problemas;
	
	/**
	 * Coleção que armazena os objetivos, tendo como chave uma string e como valor
	 * um objeto Objetivo.
	 */
	
	private Map<String, Objetivo> objetivos;
	
	/**
	 * Atributo que controla o numero de problemas que ja foram cadastrados.
	 */
	
	private int controlaNumeroProblemas;
	
	/**
	 * Atributo que controla o numero de objetivos que ja foram cadastrados.
	 */
	
	private int controlaNumeroObjetivos;

	/**
	 * Construtor do ControllerProblemaObjetivo .
	 */
	
	public ControllerProblemaObjetivo() {
		this.problemas = new HashMap<>();
		this.objetivos = new HashMap<>();
		this.controlaNumeroProblemas = 0;
		this.controlaNumeroObjetivos = 0;
	}

	/**
	 * Metodo privado que verifica se um problema existe dentro do mapa de
	 * problemas.
	 * 
	 * @param codigo, recebe como parametro o codigo do problema.
	 * @return um booleano que vai informar se o problema existe ou nao.
	 */
	
	private boolean problemaExiste(String codigo) {
		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
		return true;
	}

	/**
	 * Metodo privado que verifica se um objetivo existe dentro do mapa de
	 * problemas.
	 * 
	 * @param codigo, recebe como parametro o codigo do objetivo.
	 * @return um booleano que vai informar se o objetivo existe ou nao.
	 */
	
	private boolean objetivoExiste(String codigo) {
		if (!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
		return true;
	}

	/**
	 * Metodo que cadastra um problema recebendo a descricao e a viabilidade do
	 * mesmo.
	 * 
	 * @param descricao,   representa a descricao do problema.
	 * @param viabilidade, representa a viabilidade do problema.
	 * @return retorna uma string que representa o codigo do problema.
	 */
	
	public String cadastraProblema(String descricao, int viabilidade) {
		
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade.");

		controlaNumeroProblemas += 1;
		String codigo = "P" + controlaNumeroProblemas;

		this.problemas.put(codigo, new Problema(descricao, viabilidade, codigo));

		return codigo;
	}

	/**
	 * Metodo que cadastra um objetivo recebendo o tipo, a descricao, a aderencia e
	 * a viabilidade do mesmo.
	 * 
	 * @param tipo        representa o tip o objetivo, seja ele geral ou especifico.
	 * @param descricao   representa a descriao do objetivo.
	 * @param aderencia   representa a aderencia do objetivo.
	 * @param viabilidade representa a viabilidade do objetivo.
	 * @return retorna uma string que representa o codigo do objetivo.
	 */
	
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {

		ValidadorDeEntradas.validaEntradaNulaOuVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaTipo(tipo);
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(aderencia, "Valor invalido de aderencia");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade.");

		controlaNumeroObjetivos += 1;
		String codigo = "O" + controlaNumeroObjetivos;

		this.objetivos.put(codigo, new Objetivo(tipo, descricao, aderencia, viabilidade, codigo));
		return codigo;
	}

	/**
	 * Metodo que apaga um problema, reecbendo seu codigo e nao retornando nada.
	 * 
	 * @param codigo representa o codigo do problema.
	 */
	
	public void apagarProblema(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (problemaExiste(codigo)) {
			problemas.remove(codigo);
		}
	}

	/**
	 * Metodo que apaga um objetivo, reecbendo seu codigo e nao retornando nada.
	 * 
	 * @param codigo representa o codigo do objetivo.
	 */

	public void apagarObjetivo(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (objetivoExiste(codigo)) {
			objetivos.remove(codigo);
		}
	}

	/**
	 * Metodo que gera e retorna um representacao textual de um problema, recebendo
	 * seu codigo.
	 * 
	 * @param codigo representa o codigo do prolema.
	 * @return uma string com a representacao textual do problema.
	 */
	
	public String exibeProblema(String codigo) {
		String saida = "";
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (problemaExiste(codigo)) {
			saida = problemas.get(codigo).toString();
		}
		return saida;
	}

	/**
	 * Metodo que gera e retorna um representacao textual de um objetivo, recebendo
	 * seu codigo.
	 * 
	 * @param codigo representa o codigo do objetivo.
	 * @return uma string com a representacao textual do objetivo.
	 */
	
	public String exibeObjetivo(String codigo) {
		String saida = "";
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (objetivoExiste(codigo)) {
			saida = objetivos.get(codigo).toString();
		}
		return saida;
	}

//---------------------------------- Novas atualizacoes de ControllerProblemaObjetivo -----------------------------------
	
	/**
	 * Metodo responsavel por resgatar um determinado objetivo, existente.
	 * 
	 * @param idObjetivo - valor de identificacao do objetivo
	 * @return o objetivo desejado pelo usuario
	 */
	
	public Objetivo getObjetivo(String idObjetivo) {
		objetivoExiste(idObjetivo);
		return this.objetivos.get(idObjetivo);
	}
	
	/**
	 * Metodo responsavel por pegar o problema pertencente a determinada pesquisa.
	 * 
	 * @param idProblema - valor que identifica o problema desejado
	 * @return O problema desejado
	 */
	
	public Problema getProblema(String idProblema) {
		problemaExiste(idProblema);
		return this.problemas.get(idProblema);
	}
	
	/**
	 * Procura em todos os problemas do mapa a palavra-chave passada como
	 * parametro
	 * 
	 * @param palavraChave palavra-chave que sera procurada
	 * @return Lista de Strings com as descricoes dos problemas que contiverem a palavra-chave
	 */
	
	public List<String> procuraPalavraChaveProblema(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();

		for (Problema problema : this.problemas.values()) {
			if (!problema.procuraPalavraChave(palavraChave).equals("")) {
				resultadosBusca.add(problema.procuraPalavraChave(palavraChave));	
			}
		}
		
		Collections.sort(resultadosBusca, new OrdenaResultados());
		
		return resultadosBusca;
	}
	
	/**
	 * Procura em todos os objetivos do mapa a palavra-chave passada como
	 * parametro
	 * 
	 * @param palavraChave palavra-chave que sera procurada
	 * @return Lista de Strings com as descricoes dos objetivos que contiverem a palavra-chave
	 */
	
	public List<String> procuraPalavraChaveObjetivo(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();

		for (Objetivo objetivo: this.objetivos.values()) {
			if (!objetivo.procuraPalavraChave(palavraChave).equals("")) {
				resultadosBusca.add(objetivo.procuraPalavraChave(palavraChave));	
			}
		}
		
		Collections.sort(resultadosBusca, new OrdenaResultados());
		
		return resultadosBusca;
	}
}