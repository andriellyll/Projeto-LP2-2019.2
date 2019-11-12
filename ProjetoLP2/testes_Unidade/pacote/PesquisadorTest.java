package pacote;
/**
 * @author Anna Beatriz Lucena
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PesquisadorTest {
	
	Pesquisador pesquisador1;
	
	@BeforeEach
	public void criaPesquisador() {
		pesquisador1 = new Pesquisador("Anna", "estudante", "gosta de matematica", "anna.lira@ccc.ufcg.edu.br", "https://annabeatrizlucena.com");		
		
	}
	@Test
	void criaPesquisNomeNulo() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador(null, "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "https://godspeed");
		});
	}
	@Test
	void criaPequisadorNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("", "estudante", "Perfeita demais", "andrielly11@ccc.ufcg.edu.br",
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
	@Test
	void criaPequisadorFuncaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", "", "Perfeita demais", "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}
	@Test
	void criaPequisadorBiografiaNula() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Andrielly", "estudante", null, "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}

	@Test
	void criaPequisadorBiografiaVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", "estudante", "", "andrielly11@ccc.ufcg.edu.br",
					"https://godspeed");
		});

	}

	@Test
	void criaPequisadorEmailNulo() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Andrielly", "estudante", "Perfeita demais", null,
					"https://godspeed");
		});

	}

	@Test
	void criaPequisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", "estudante", "Perfeita demais", "",
					"https://godspeed");
		});

	}

	@Test
	void criaPequisadorUrlNula() {
		assertThrows(NullPointerException.class, () -> {
			new Pesquisador("Andrielly", "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", null);
		});

	}

	@Test
	void criaPequisadorUrlVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", "estudante", "Perfeita demais",
					"andrielly11@ccc.ufcg.edu.br", "");
		});

	}

	@Test
	void criaPequisadorEmailInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", "estudante", "Perfeita demais", "drica@",
					"https://teste");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", "estudante", "Perfeita demais", "@drica",
					"https://teste");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Pesquisador("Andrielly", "estudante", "Perfeita demais", "a",
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
		pesquisador1.setFuncao("professor");
		assertEquals(pesquisador1.toString(),"Anna (professor) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com");
	}
	@Test
	void testSetFuncaoNula() {
	
		assertThrows(NullPointerException.class, () -> {
		pesquisador1.setFuncao(null);
		});
		}
	@Test
	void testSetFuncaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> {
		pesquisador1.setFuncao("");
		});
		}
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
		Pesquisador pesquisador2 = new Pesquisador("Anna", "estudante", "gosta de matematica", "anna.lira@ccc.ufcg.edu.br", "https://annabeatrizlucena.com");
	assertEquals(pesquisador1.hashCode(), pesquisador2.hashCode());
	}
	@Test
	void testEquals() {
		Pesquisador pesquisador2 = new Pesquisador("Anna", "estudante", "gosta de matematica", "anna.lira@ccc.ufcg.edu.br", "https://annabeatrizlucena.com");
		assertTrue(pesquisador1.equals(pesquisador2));
		assertTrue(pesquisador1.equals(pesquisador1));
		assertFalse(pesquisador1.equals(null));
		
	}
	@Test
	void testToString() {
		Pesquisador pesquisador2 = new Pesquisador("Anna", "estudante", "gosta de matematica", "anna.lira@ccc.ufcg.edu.br", "https://annabeatrizlucena.com");
		assertEquals(pesquisador2.toString(), "Anna (estudante) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com");
	}
	@Test
	void testBusca() {
		assertEquals("anna.lira@ccc.ufcg.edu.br: gosta de matematica", pesquisador1.procuraPalavraChave("matematica"));
		assertEquals("", pesquisador1.procuraPalavraChave("fisica"));
	}
	@Test
	void testBuscaNull() {
		assertThrows(NullPointerException.class, () -> {
			pesquisador1.procuraPalavraChave(null);
		});
	}
	@Test
	void testBuscaVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador1.procuraPalavraChave("");
		});
	}
	@Test 
	void cadastraEspecialidadeProfessor() {
		Pesquisador pesquisador2 = new Pesquisador("eliane", "professor", "linda pfta", "eliane@computacao", "http://eliane");
		pesquisador2.cadastraEspecialidadeProfessor("Doutorado", "UASC", "07/07/2007");
		assertEquals("eliane (professor) - linda pfta - eliane@computacao - http://eliane - Doutorado - UASC - 07/07/2007", pesquisador2.toString());
	}
	@Test 
	void cadastraEspecialidadeProfessorNull() {
		Pesquisador pesquisador2 = new Pesquisador("eliane", "professor", "linda pfta", "eliane@computacao", "http://eliane");
		assertThrows(NullPointerException.class, () -> {
			pesquisador2.cadastraEspecialidadeProfessor(null, "UASC", "07/07/2007");		
		});
		assertThrows(NullPointerException.class, () -> {
			pesquisador2.cadastraEspecialidadeProfessor("Doutorado", null, "07/07/2007");		
		});
		assertThrows(NullPointerException.class, () -> {
			pesquisador2.cadastraEspecialidadeProfessor("Doutorado", "UASC", null);		
		});
	}
	@Test 
	void cadastraEspecialidadeProfessorVazio() {
		Pesquisador pesquisador2 = new Pesquisador("eliane", "professor", "linda pfta", "eliane@computacao", "http://eliane");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador2.cadastraEspecialidadeProfessor("", "UASC", "07/07/2007");		
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador2.cadastraEspecialidadeProfessor("Doutorado", "  ", "07/07/2007");		
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador2.cadastraEspecialidadeProfessor("Doutorado", "UASC", "   ");		
		});
	}
	@Test 
	void cadastraEspecialidadeProfessorDataInvalida() {
		Pesquisador pesquisador2 = new Pesquisador("eliane", "professor", "linda pfta", "eliane@computacao", "http://eliane");
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador2.cadastraEspecialidadeProfessor("Doutorado", "UASC", "0707/2007");		
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador2.cadastraEspecialidadeProfessor("Doutorado", "UASC", "77/07/2007");		
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador2.cadastraEspecialidadeProfessor("Doutorado", "UASC", "07/777/2007");		
		});
	}
	@Test 
	void cadastraEspecialidadeProfessorPesquisadorIncompativel() {
		Pesquisador pesquisador3 = new Pesquisador("nishinoya", "externo", "melhor libero pfto quem discorda eh nazista", "nishinoya@lindo", "https://rollingthunder");
		assertThrows(RuntimeException.class, () -> {
			pesquisador1.cadastraEspecialidadeProfessor("Doutorado", "UASC", "0707/2007");		
		});
		assertThrows(RuntimeException.class, () -> {
			pesquisador3.cadastraEspecialidadeProfessor("Doutorado", "UASC", "77/07/2007");		
		});
	}
	@Test 
	void cadastraEspecialidadeAluno() {
		pesquisador1.cadastraEspecialidadeAluno(1, 9.9);
		assertEquals("Anna (estudante) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com - 1o SEMESTRE - 9.9", pesquisador1.toString());
	}
	@Test 
	void cadastraEspecialidadeAlunoAtributosInvalidos() {
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador1.cadastraEspecialidadeAluno(0, 9.9);		
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador1.cadastraEspecialidadeAluno(1, 15);		
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador1.cadastraEspecialidadeAluno(2, -1.0);		
		});
	}
	@Test 
	void cadastraEspecialidadeAlunoPesquisadorIncompativel() {
		Pesquisador pesquisador3 = new Pesquisador("nishinoya", "externo", "melhor libero pfto quem discorda eh nazista", "kageyama@tobio", "https://rollingthunder");
		assertThrows(RuntimeException.class, () -> {
			pesquisador1.cadastraEspecialidadeProfessor("Doutorado", "UASC", "0707/2007");		
		});
		assertThrows(RuntimeException.class, () -> {
			pesquisador3.cadastraEspecialidadeProfessor("Doutorado", "UASC", "77/07/2007");		
		});
	}
	@Test
	void setEspecialidadeProfessor() {
		Pesquisador pesquisador2 = new Pesquisador("gauds", "professor", "gosta de cha de cha", "matheus@computacao", "http://matheus");
		pesquisador2.cadastraEspecialidadeProfessor("Doutorado", "UASC", "07/07/2007");
		assertEquals("gauds (professor) - gosta de cha de cha - matheus@computacao - http://matheus - Doutorado - UASC - 07/07/2007", pesquisador2.toString());
		
		pesquisador2.setEspecialidade("formacao", "Pos-Doutorado");
		assertEquals("gauds (professor) - gosta de cha de cha - matheus@computacao - http://matheus - Pos-Doutorado - UASC - 07/07/2007", pesquisador2.toString());
		
		pesquisador2.setEspecialidade("unidade", "DSC");
		assertEquals("gauds (professor) - gosta de cha de cha - matheus@computacao - http://matheus - Pos-Doutorado - DSC - 07/07/2007", pesquisador2.toString());
		
		pesquisador2.setEspecialidade("data", "12/12/2012");
		assertEquals("gauds (professor) - gosta de cha de cha - matheus@computacao - http://matheus - Pos-Doutorado - DSC - 12/12/2012", pesquisador2.toString());
		
	}
	@Test
	void setEspecialidadeALuno() {
		pesquisador1.cadastraEspecialidadeAluno(1, 8.9);
		assertEquals("Anna (estudante) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com - 1o SEMESTRE - 8.9", pesquisador1.toString());
		
		pesquisador1.setEspecialidade("semestre", "2");
		assertEquals("Anna (estudante) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com - 2o SEMESTRE - 8.9", pesquisador1.toString());
		
		pesquisador1.setEspecialidade("IEA", "8.6");
		assertEquals("Anna (estudante) - gosta de matematica - anna.lira@ccc.ufcg.edu.br - https://annabeatrizlucena.com - 2o SEMESTRE - 8.6", pesquisador1.toString());
		
	}
	@Test
	void setEspecialidadeNull() {
		pesquisador1.cadastraEspecialidadeAluno(1, 8.9);
		assertThrows(NullPointerException.class, () -> {
			pesquisador1.setEspecialidade(null, "3");		
		});
		assertThrows(NullPointerException.class, () -> {
			pesquisador1.setEspecialidade("semestre", null);		
		});
	}
	@Test
	void setEspecialidadeVazio() {
		pesquisador1.cadastraEspecialidadeAluno(1, 8.9);
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador1.setEspecialidade("", "3");		
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisador1.setEspecialidade("semestre", "");		
		});
	}
	@Test 
	void setEspecialidadePesquisadorIncompativel() {
		Pesquisador pesquisador2 = new Pesquisador("eliane", "professor", "linda pfta", "eliane@computacao", "http://eliane");
		Pesquisador pesquisador3 = new Pesquisador("nishinoya", "externo", "melhor libero pfto quem discorda eh nazista", "kageyama@tobio", "https://rollingthunder");
		
		assertThrows(RuntimeException.class, () -> {
			pesquisador1.setEspecialidade("formacao", "Doutorado");		
		});
		assertThrows(RuntimeException.class, () -> {
			pesquisador1.setEspecialidade("unidade", "UASC");		
		});
		assertThrows(RuntimeException.class, () -> {
			pesquisador1.setEspecialidade("data", "11/11/2011");		
		});
		
		assertThrows(RuntimeException.class, () -> {
			pesquisador2.setEspecialidade("semestre", "4");		
		});
		assertThrows(RuntimeException.class, () -> {
			pesquisador2.setEspecialidade("IEA", "9.9");		
		});
		
		assertThrows(RuntimeException.class, () -> {
			pesquisador3.setEspecialidade("formacao", "Doutorado");		
		});
		assertThrows(RuntimeException.class, () -> {
			pesquisador3.setEspecialidade("unidade", "UASC");		
		});
		assertThrows(RuntimeException.class, () -> {
			pesquisador3.setEspecialidade("data", "11/11/2011");		
		});
		assertThrows(RuntimeException.class, () -> {
			pesquisador3.setEspecialidade("semestre", "4");		
		});
		assertThrows(RuntimeException.class, () -> {
			pesquisador3.setEspecialidade("IEA", "9.9");		
		});
		
	}
}
