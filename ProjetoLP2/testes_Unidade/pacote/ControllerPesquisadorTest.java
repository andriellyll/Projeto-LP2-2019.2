package pacote;

/**
 * @author Anna Beatriz Lucena
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerPesquisadorTest {

	ControllerPesquisador controllerPesquisador = new ControllerPesquisador();

	@BeforeEach
	void cadastraPesquisador() {
		controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais",
				"andrielly11@ccc.ufcg.edu.br", "https://godspeed");
	}

	@Test
	void cadastraPequisadorNomeNulo() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraPesquisador(null, "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "https://godspeed");
		});

	}

	@Test
	void cadastraPequisadorNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("", "estudante", "Perfeita demais", "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}

	@Test
	void cadastraPequisadorFuncaoNula() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", null, "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "https://godspeed");
		});

	}

	@Test
	void cadastraPequisadorFuncaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "", "Perfeita demais", "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}

	@Test
	void cadastraPequisadorBiografiaNula() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", null, "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}

	@Test
	void cadastraPequisadorBiografiaVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "", "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}

	@Test
	void cadastraPequisadorEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais", null,
					"https://godspeed");
		});

	}

	@Test
	void cadastraPequisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais", "",
					"https://godspeed");
		});

	}

	@Test
	void cadastraPequisadorUrlNula() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", null);
		});

	}

	@Test
	void cadastraPequisadorUrlVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "");
		});

	}

	@Test
	void cadastraPequisadorEmailInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais", "drica@",
					"https://teste");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais", "@drica",
					"https://teste");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais", "a",
					"https://teste");
		});

	}

	@Test
	void cadastraPequisadorUrlInvalida() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "https");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "http");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "h t t p s //");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraPesquisador("Andrielly", "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "semurl");
		});

	}

	@Test
	void exibePesquisadorCadastrado() {
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed");

	}

	@Test
	void alteraPesquisadorNaoCadastrado() {
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.alteraPesquisador("anna.lira@ccc.ufcg.edu.br", "NOME", "Anna Beatriz Lucena");
			;
		});
	}

	@Test
	void desativaPesquisadorNaoCadastrado() {
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.desativaPesquisador("anna.lira@ccc.ufcg.edu.br");
		});
	}

	@Test
	void ativaPesquisadorNaoCadastrado() {
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.ativaPesquisador("anna.lira@ccc.ufcg.edu.br");
		});
	}

	@Test
	void exibePesquisadorNaoCadastrado() {
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.exibePesquisador("anna.lira@ccc.ufcg.edu.br");
		});
	}

	@Test
	void verificaPesquisadorEhAtivoPesquisadorNaoCadastrado() {
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.pesquisadorEhAtivo("anna.lira@ccc.ufcg.edu.br");
		});
	}

	@Test
	void exibePesquisadorInativo() {
		controllerPesquisador.desativaPesquisador("andrielly11@ccc.ufcg.edu.br");
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br");
		});
	}

	@Test
	void alteraPesquisadorInativo() {
		controllerPesquisador.desativaPesquisador("andrielly11@ccc.ufcg.edu.br");
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "NOME", "Drica");
		});
	}

	@Test
	void desativaPesquisadorJaDesativado() {
		controllerPesquisador.desativaPesquisador("andrielly11@ccc.ufcg.edu.br");
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.desativaPesquisador("andrielly11@ccc.ufcg.edu.br");
		});

	}

	@Test
	void ativaPesquisadorJaAtivo() {
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.ativaPesquisador("andrielly11@ccc.ufcg.edu.br");
		});
	}

	@Test
	void ativaPesquisadorInativo() {
		controllerPesquisador.desativaPesquisador("andrielly11@ccc.ufcg.edu.br");
		assertFalse(controllerPesquisador.pesquisadorEhAtivo("andrielly11@ccc.ufcg.edu.br"));
		controllerPesquisador.ativaPesquisador("andrielly11@ccc.ufcg.edu.br");
		assertTrue(controllerPesquisador.pesquisadorEhAtivo("andrielly11@ccc.ufcg.edu.br"));
	}

	@Test
	void verificaPesquisadorEhAtivo() {
		assertTrue(controllerPesquisador.pesquisadorEhAtivo("andrielly11@ccc.ufcg.edu.br"));
	}

	@Test
	void alteraPesquisadorEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.alteraPesquisador(null, "NOME", "nao sei");

		});
	}

	@Test
	void alteraPesquisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.alteraPesquisador("", "NOME", "nao sei");

		});
	}

	@Test
	void alteraNomePesquisador() {
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed");
		controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "NOME", "Drica");
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Drica (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed");

	}

	@Test
	void alteraFuncaoPesquisador() {
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed");
		controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "FUNCAO", "professor");
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (professor) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed");

	}
	@Test
	void alteraBiografiaPesquisador() {
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed");
		controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "BIOGRAFIA", "nao sei");
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (estudante) - nao sei - andrielly11@ccc.ufcg.edu.br - https://godspeed");

	}
	@Test
	void alteraEmailPesquisador() {
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed");
		controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "EMAIL", "dricahelen@ccc.ufcg.edu.br");
		assertEquals(controllerPesquisador.exibePesquisador("dricahelen@ccc.ufcg.edu.br"),
				"Andrielly (estudante) - Perfeita demais - dricahelen@ccc.ufcg.edu.br - https://godspeed");

	}
	@Test
	void alteraFotoPesquisador() {
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed");
		controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "FOTO", "https://projetolp2.com");
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://projetolp2.com");

	}
	@Test
	void alteraPesquisadorAtributoInvalido() {
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "aleatorio", "nao sei");
		});
	}

}
