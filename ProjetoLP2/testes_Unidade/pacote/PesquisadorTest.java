package pacote;
/**
 * @author Anna Beatriz Lucena
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PesquisadorTest {


		Pesquisador pesquisador1 = new Pesquisador("Anna", new Aluno(), "gosta de matematica", "anna.lira@ccc.ufcg.edu.br", "https://annabeatrizlucena.com");
	
	@Test
	void criaPesquisNomeNulo() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador(null, new Aluno(), "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "https://godspeed");
		});
	}
	@Test
	void criaPequisadorNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("", new Aluno(), "Perfeita demais", "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}

	@Test
	void criaPequisadorFuncaoNula() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Andrielly", null, "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "https://godspeed");
		});

	}

	/*
	@Test
	void criaPequisadorFuncaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", new Estudante(), "Perfeita demais", "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}
	*/
	@Test
	void criaPequisadorBiografiaNula() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Andrielly", new Aluno(), null, "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}

	@Test
	void criaPequisadorBiografiaVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", new Aluno(), "", "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}

	@Test
	void criaPequisadorEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Andrielly", new Aluno(), "Perfeita demais", null,
					"https://godspeed");
		});

	}

	@Test
	void criaPequisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", new Aluno(), "Perfeita demais", "",
					"https://godspeed");
		});

	}

	@Test
	void criaPequisadorUrlNula() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Andrielly", new Aluno(), "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", null);
		});

	}

	@Test
	void criaPequisadorUrlVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", new Aluno(), "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "");
		});

	}

	@Test
	void criaPequisadorEmailInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", new Aluno(), "Perfeita demais", "drica@",
					"https://teste");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", new Aluno(), "Perfeita demais", "@drica",
					"https://teste");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", new Aluno(), "Perfeita demais", "a",
					"https://teste");
		});

	}
	@Test
	void testSetNome() {
		pesquisador1.setNome("Bia");
		assertEquals(pesquisador1.toString(),"Bia (estudante) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com");
	}
	@Test
	void testSetNomeNulo() {
	
		assertThrows(NullPointerException.class, () -> {
		pesquisador1.setNome(null);
		});
		}
	@Test
	void testSetNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
		pesquisador1.setNome("");
		});
		}
	@Test
	void testSetBiografia() {
		pesquisador1.setBiografia("gosta de ler");
		assertEquals(pesquisador1.toString(),"Anna (estudante) - gosta de ler - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com");
	}
	@Test
	void testSetBiografiaNula() {
	
		assertThrows(NullPointerException.class, () -> {
		pesquisador1.setBiografia(null);
		});
		}
	@Test
	void testSetBiografiaVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
		pesquisador1.setBiografia("");
		});
		}
	@Test
	void testSetEmail() {
		pesquisador1.setEmail("beatriz.lucena@ccc.ufcg.edu.br");
		assertEquals(pesquisador1.toString(),"Anna (estudante) - gosta de matematica - beatriz.lucena@ccc.ufcg.edu.br - https://annabeatrizlucena.com");
	}
	@Test
	void testSetEmailNulo() {
	
		assertThrows(NullPointerException.class, () -> {
		pesquisador1.setEmail(null);
		});
		}
	@Test
	void testSetEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
		pesquisador1.setEmail("");
		});
		}
	@Test
	void testSetFoto() {
		pesquisador1.setFoto("https://beatrizlucena.com");
		assertEquals(pesquisador1.toString(),"Anna (estudante) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://beatrizlucena.com");
	}
	@Test
	void testSetFotoNula() {
	
		assertThrows(NullPointerException.class, () -> {
		pesquisador1.setFoto(null);
		});
		}
	@Test
	void testSetFotoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
		pesquisador1.setFoto("");
		});
		}
	@Test
	void testSetFuncao() {
		pesquisador1.setFuncao(new Professor());
		assertEquals(pesquisador1.toString(),"Anna (professor) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com");
	}
	@Test
	void testSetFuncaoNula() {
	
		assertThrows(NullPointerException.class, () -> {
		pesquisador1.setFuncao(null);
		});
		}
	/*
	@Test
	void testSetFuncaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
		pesquisador1.setFuncao("");
		});
		}
	 */
	@Test
	void verificarPesquisadorAtivo() {
		assertTrue(pesquisador1.ehAtivo());
	}
	@Test
	void desativaPesquisadorAtivo() {
		pesquisador1.desativaPesquisador();
		assertFalse(pesquisador1.ehAtivo());
	}
	@Test
	void ativaPesquisadorDesativado() {
		pesquisador1.ativaPesquisador();
		assertTrue(pesquisador1.ehAtivo());
	}
	@Test
	void testHashCode() {
		Pesquisador pesquisador2 = new Pesquisador("Anna", new Aluno(), "gosta de matematica", "anna.lira@ccc.ufcg.edu.br", "https://annabeatrizlucena.com");
	assertEquals(pesquisador1.hashCode(), pesquisador2.hashCode());
	}
	@Test
	void testEquals() {
		Pesquisador pesquisador2 = new Pesquisador("Anna", new Aluno(), "gosta de matematica", "anna.lira@ccc.ufcg.edu.br", "https://annabeatrizlucena.com");
		assertTrue(pesquisador1.equals(pesquisador2));
		assertTrue(pesquisador1.equals(pesquisador1));
		assertFalse(pesquisador1.equals(null));
		
	}
	@Test
	void testToString() {
		Pesquisador pesquisador2 = new Pesquisador("Anna", new Aluno(), "gosta de matematica", "anna.lira@ccc.ufcg.edu.br", "https://annabeatrizlucena.com");
		assertEquals(pesquisador2.toString(), "Anna (estudante) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com");
	}
}
