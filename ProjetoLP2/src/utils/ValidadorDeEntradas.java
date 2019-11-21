package utils;

/**
 * Responsavel por validar todas as entradas do sistema.
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 *
 */
public class ValidadorDeEntradas {

	/**
	 * Verifica se o parametro entrada e nulo, caso for lança uma excecao do tipo
	 * NullPointerException.
	 * 
	 * @param entrada  - a entrada a ser verificada
	 * @param mensagem - a mensagem de erro a ser exibida no lancamento da excecao
	 */
	private static void validaEntradaNula(String entrada, String mensagem) {
		if (entrada == null) {
			throw new NullPointerException(mensagem);
		}
	}

	/**
	 * Verifica se o parametro entrada e vazio, caso for lança uma excecao do tipo
	 * IllegalArgumentException.
	 * 
	 * @param entrada  - a entrada a ser verificada
	 * @param mensagem - a mensagem de erro a ser exibida no lancamento da excecao
	 */
	private static void validaEntradaVazia(String entrada, String mensagem) {
		if (entrada.trim().isEmpty()) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Valida a String de entrada, a partir dos metodos validaEntradaNula e
	 * validaEntradaVazia. Caso a entrada for nula ou vazia excecao sera lançada.
	 * 
	 * @param entrada  - a entrada a ser verificada
	 * @param mensagem - a mensagem de erro a ser exibida no lancamento da excecao
	 */
	public static void validaEntradaNulaOuVazia(String entrada, String mensagem) {
		validaEntradaNula(entrada, mensagem);
		validaEntradaVazia(entrada, mensagem);
	}

	/**
	 * Verifica se o email passado e valido. Um email e valido se tem pelo menos uma
	 * letra ou um número antes e depois de uma arroba. Caso for invalido, uma
	 * exececao do tipo IllegalArgumentException sera lancada.
	 * 
	 * @param email - o email a ser validado
	 */
	public static void verificaEmail(String email) {
		if (email.startsWith("@") || email.endsWith("@") || (email.indexOf("@") == -1)) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
	}

	/**
	 * Verifca se a url passada e valida. Uma url e valida se começa com http ou
	 * https seguido de :// e um endereço. Caso a url for invalida, uma excecao do
	 * tipo IllegalArgumentException sera lancada.
	 * 
	 * @param url - a url a ser validada
	 */
	public static void verificaURL(String url) {
		if (url.length() < 8 || (!url.substring(0, 7).equals("http://") && !url.substring(0, 8).equals("https://"))) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

	/**
	 * Verifica se o campo de interesse passado e valido. Um campo de interesse
	 * valido não pode ser vazio, ter mais que 255 caracteres, ter tópicos em
	 * branco, ou tópicos com menos de 3 caracteres, ter mais de 4 topicos. Caso o
	 * campo seja invalido, uma excecao sera lancada.
	 * 
	 * @param campoDeInteresse - o campo de interesse a ser validado
	 */
	public static void validaCampoDeInteresse(String campoDeInteresse) {
		if (campoDeInteresse.length() > 250 || campoDeInteresse.length() < 3) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}
		String[] interesseSeparado = campoDeInteresse.split(",");
		if (interesseSeparado.length > 4) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}
		for (String campo : interesseSeparado) {
			validaEntradaNulaOuVazia(campo, "Formato do campo de interesse invalido.");
		}
	}

	/**
	 * Verifica se o valor inteiro de viabilidade e de 1 a 5. Caso nao for, uma
	 * excecao do tipo IllegalArgumentException sera lancada.
	 * 
	 * @param inteiro  - o inteiro que representa a viabilidade
	 * @param mensagem - a mensagem de erro a ser exibida no lancamento da excecao
	 */
	public static void validaViabilidadeOuAderencia(int inteiro, String mensagem) {
		if (inteiro < 1 || inteiro > 5) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Verifica se o tipo do objetivo e geral ou especifico. Caso nao for, uma
	 * excecao do tipo IllegalArgumentException sera lancada.
	 * 
	 * @param tipo - o tipo do objetivo
	 */
	public static void validaTipo(String tipo) {
		if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}
	}

	/**
	 * Verifica se a entrada(nivel de risco) e igual a baixo, medio ou alto. Caso
	 * nao for, uma excecao do tipo IllegalArgumentException sera lancada.
	 * 
	 * @param entrada  - a entrada a ser validada
	 * @param mensagem - a mensagem de erro a ser exibida no lancamento da excecao.
	 */
	public static void validaNivelRisco(String entrada, String mensagem) {
		if (!entrada.equalsIgnoreCase("BAIXO") && !entrada.equalsIgnoreCase("MEDIO")
				&& !entrada.equalsIgnoreCase("ALTO")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Verifica se a entrada(um inteiro) e igual e menor que 0. Caso for, uma
	 * excecao do tipo IllegalArgumentException sera lancada.
	 * 
	 * @param item     - o valor inteiro a ser verificado
	 * @param mensagem - a mensagem de erro a ser exibida no lancamento da excecao.
	 */
	public static void verificaNumeroNegativo(int item, String mensagem) {
		if (item <= 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * 
	 * 
	 * @param semestre
	 * @param mensagem
	 */
	public static void verificaSemestre(int semestre, String mensagem) {
		if (semestre < 1) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * 
	 * 
	 * @param IEA
	 * @param mensagem
	 */
	public static void verificaIEA(double IEA, String mensagem) {
		if (IEA < 0 || IEA > 10) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * 
	 * 
	 * @param data
	 * @param mensagem
	 */
	public static void verificaData(String data, String mensagem) {
		String[] lista = data.split("/");
		if (lista.length != 3) {
			throw new IllegalArgumentException(mensagem);
		}
		if (lista[0].length() != 2 || lista[1].length() != 2 || lista[2].length() != 4) {
			throw new IllegalArgumentException(mensagem);
		}
		if (Integer.parseInt(lista[0]) > 31 || Integer.parseInt(lista[1]) > 12 || Integer.parseInt(lista[2]) > 2019) {
			throw new IllegalArgumentException(mensagem);
		}
	}
}