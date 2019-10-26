package pacote;

public class ValidadorDeEntradas {
	private static void validaEntradaNula(String entrada, String mensagem) {
		if (entrada == null) {
			throw new NullPointerException(mensagem);
		}
	}

	private static void validaEntradaVazia(String entrada, String mensagem) {
		if (entrada.trim().isEmpty()) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public static void validaEntradaNulaOuVazia(String entrada, String mensagem) {
		validaEntradaNula(entrada, mensagem);
		validaEntradaVazia(entrada, mensagem);

	}

	public static void validaTamanhoString(String entrada, String mensagem, int tamanho) {
		if (entrada.length() > tamanho) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public static void verificaEmail(String email) {

		if (email.startsWith("@") || email.endsWith("@") || (email.indexOf("@") == -1)) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}

		if (email.split("@")[0].isEmpty() || email.split("@")[1].isEmpty()) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
	}

	public static void verificaURL(String url) {
		if (url.length() < 8 || (!url.substring(0, 7).equals("http://") && !url.substring(0, 8).equals("https://"))) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

	public static void validaCampoDeInteresse(String campoDeInteresse) {
		if (campoDeInteresse.length() < 3) {
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

}