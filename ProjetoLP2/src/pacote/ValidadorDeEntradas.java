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
}
