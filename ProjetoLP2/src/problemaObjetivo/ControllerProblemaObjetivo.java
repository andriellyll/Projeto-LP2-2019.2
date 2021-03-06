package problemaObjetivo;

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
/**
 * Classe controladora das classes Problema e Objetivo.
 * @author Helen Bento Cavalcanti
 */
import java.util.Map;

import pesquisa.Pesquisa;
import system.Buscavel;
import system.OrdenaResultados;
import utils.ValidadorDeEntradas;

/**
 * Responsavel por manipular as informacoes relacionadas com problema e objetivo,
 * realizando operacoes com estes dados.
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 *
 */
public class ControllerProblemaObjetivo implements Buscavel {

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

//------------------------------------------ ControllerProblemaObjetivo (Parte 2) ------------------------------------------

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
	 * Metodo responsavel por associar uma pesquisa a um objetivo.
	 * 
	 * @param idObjetivo - valor de identificacao do objetivo
	 * @param pesquisa   objeto pesquisa a ser associado
	 * @return um booleano referente a situacao do processo
	 */
	public boolean associaPesquisa(String idObjetivo, Pesquisa pesquisa) {
		return objetivos.get(idObjetivo).associaPesquisa(pesquisa);
	}

	/**
	 * Metodo responsavel por desassociar uma pesquisa de um objetivo.
	 * 
	 * @param idObjetivo - valor de identificacao do objetivo
	 * @param pesquisa   objeto pesquisa a ser desassociado
	 * @return um booleano referente a situacao do processo
	 */
	public boolean desassociaPesquisa(String idObjetivo, Pesquisa pesquisa) {
		return objetivos.get(idObjetivo).desassociaPesquisa(pesquisa);
	}

	/**
	 * Procura em todos os problemas do mapa a palavra-chave passada como parametro
	 * 
	 * @param palavraChave palavra-chave que sera procurada
	 * @return Lista de Strings com as descricoes dos problemas que contiverem a
	 *         palavra-chave
	 */
	private List<String> procuraPalavraChaveProblema(String palavraChave) {
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
	 * Procura em todos os objetivos do mapa a palavra-chave passada como parametro
	 * 
	 * @param palavraChave palavra-chave que sera procurada
	 * @return Lista de Strings com as descricoes dos objetivos que contiverem a
	 *         palavra-chave
	 */
	private List<String> procuraPalavraChaveObjetivo(String palavraChave) {
		ArrayList<String> resultadosBusca = new ArrayList<>();
		for (Objetivo objetivo : this.objetivos.values()) {
			if (!objetivo.procuraPalavraChave(palavraChave).equals("")) {
				resultadosBusca.add(objetivo.procuraPalavraChave(palavraChave));
			}
		}
		Collections.sort(resultadosBusca, new OrdenaResultados());
		return resultadosBusca;
	}

	/**
	 * Busca a palavra nas entidades de problema e objetivo, ordena os resultados em ordem anti-lexicografica e retorna a lista.
	 * 
	 * @param palavraChave palavra a ser buscada
	 * @return ArrayList de resultados
	 */
	@Override
	public List<String> procuraPalavraChave(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Palavra nao pode ser nula ou vazia");
		ArrayList<String> resultados = (ArrayList<String>) procuraPalavraChaveProblema(palavraChave);
		resultados.addAll(procuraPalavraChaveObjetivo(palavraChave));
		return resultados;
	}

//------------------------------------------ ControllerProblemaObjetivo (Parte 3) ------------------------------------------

	/**
	 * Metodo responsavel por salvar os problemas e os objetivos
	 */
	public void salvar() {
		try {
			FileOutputStream saveFile = new FileOutputStream("problema.dat");
			ObjectOutputStream stream = new ObjectOutputStream(saveFile);
			stream.writeObject(problemas);
			stream.close();

			FileOutputStream saveFile2 = new FileOutputStream("objetivo.dat");
			ObjectOutputStream stream2 = new ObjectOutputStream(saveFile2);
			stream2.writeObject(objetivos);
			stream2.close();

			OutputStream out = new FileOutputStream(new File("numeroProblemas.txt"));
			out.write(controlaNumeroProblemas);
			out.close();

			OutputStream out2 = new FileOutputStream(new File("numeroObjetivos.txt"));
			out2.write(controlaNumeroObjetivos);
			out2.close();

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
			FileInputStream restFile = new FileInputStream("problema.dat");
			ObjectInputStream stream = new ObjectInputStream(restFile);
			Map<String, Problema> problemasCadastrados = (Map<String, Problema>) stream.readObject();
			stream.close();
			this.problemas = problemasCadastrados;

			FileInputStream restFile2 = new FileInputStream("objetivo.dat");
			ObjectInputStream stream2 = new ObjectInputStream(restFile2);
			Map<String, Objetivo> objetivosCadastrados = (Map<String, Objetivo>) stream2.readObject();
			stream2.close();
			this.objetivos = objetivosCadastrados;

			File file = new File("numeroProblemas.txt");
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			this.controlaNumeroProblemas = fis.read();

			File file2 = new File("numeroObjetivos.txt");
			FileInputStream fis2 = null;
			fis2 = new FileInputStream(file2);
			this.controlaNumeroObjetivos = fis2.read();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}