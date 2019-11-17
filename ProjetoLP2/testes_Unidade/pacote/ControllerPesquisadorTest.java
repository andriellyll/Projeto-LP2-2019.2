package pacote;

/**
 * @author Anna Beatriz Lucena
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
		controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "FUNCAO", "externo");
		assertEquals(controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"),
				"Andrielly (externo) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed");
	
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
	
	@Test
	void cadastraEspecialidadeAlunoTest() {
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);
		assertEquals("Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed - 2o SEMESTRE - 8.6", controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"));
		
	}
	
	@Test
	void cadastraEspecialidadeAlunoNullTest() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeAluno(null, 2, 8.6);
		});
	}
	
	@Test
	void cadastraEspecialidadeAlunoVazioTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeAluno("", 2, 8.6);
		});
	}
	
	@Test
	void cadastraEspecialidadeInvalidoAlunoTest() {
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", -2, 8.6);
		});
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, -1);
		});
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 10.1);
		});
	}

	@Test
	void cadastraEspecialidadeAlunoPesquisadorIncompativelTest() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeAluno("gauds@computacao", 2, 8.6);
		});
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeAluno("toninho@rodrigues", 2, 10);
		});
	}
	
	@Test
	void cadastraEspecialidadeProfessorTest() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertEquals("gauds (professor) - aaaaaa - gauds@computacao - http://aaaa - Doutorado - UASC - 11/11/2011", controllerPesquisador.exibePesquisador("gauds@computacao"));
		
	}

	@Test
	void cadastraEspecialidadeProfessorNullTest() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor(null, "Doutorado", "UASC", "11/11/2011");
		});
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", null, "UASC", "11/11/2011");
		});
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", null, "11/11/2011");
		});
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", null);
		});
	}
	
	@Test
	void cadastraEspecialidadeProfessorVazioTest() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("", "Doutorado", "UASC", "11/11/2011");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", " ", "UASC", "11/11/2011");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "", "11/11/2011");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "");
		});
	}
	
	@Test
	void cadastraEspecialidadeInvalidoProfessorTest() {
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/112011");
		});
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/011/2011");
		});
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "1/11/2011");
		});
	}

	@Test
	void cadastraEspecialidadeProfessorPesquisadorIncompativelTest() {
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("andrielly11@ccc.ufcg.edu.br", "Doutorado", "UASC", "1/11/2011");
		});
		assertThrows(RuntimeException.class, () -> {
			controllerPesquisador.cadastraEspecialidadeProfessor("toninho@rodrigues", "Doutorado", "UASC", "1/11/2011");
		});
	}
	
	@Test
	void alteraSemestrePesquisador(){
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);
		assertEquals("Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed - 2o SEMESTRE - 8.6", controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"));
		
		controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "semestre", "3");
		assertEquals("Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed - 3o SEMESTRE - 8.6", controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"));
	}

	@Test
	void alteraSemestreNullPesquisador(){
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);
		
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador(null, "semestre", "2");});
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", null, "2");});
	}

	@Test
	void alteraSemestreVazioPesquisador(){
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);
		
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("", "semestre", "2");});
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", "", "2");});
	}

	@Test
	void alteraSemestrePesquisadorIncompativel(){
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);
		
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "semestre", "2");});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("toninho@rodrigues", "semestre", "2");});
	}
	
	@Test
	void alteraSemestreInvalidoPesquisador() {
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);
		
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", "semestre", null);});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", "semestre", "");});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", "semestre", "-1");});
	}

	@Test
	void alteraIEAPesquisador(){
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);
		assertEquals("Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed - 2o SEMESTRE - 8.6", controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"));
		
		controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg.edu.br", "IEA", "8.5555");
		assertEquals("Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed - 2o SEMESTRE - 8.5555", controllerPesquisador.exibePesquisador("andrielly11@ccc.ufcg.edu.br"));
		
	}

	@Test
	void alteraIEANullPesquisador(){
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);

		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador(null, "IEA", "2");});
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", null, "2");});
	}

	@Test
	void alteraIEAVazioPesquisador(){
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);

		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("", "IEA", "2");});
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", "", "2");});
	}

	@Test
	void alteraIEAPesquisadorIncompativel(){
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "IEA", "2");});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("toninho@rodrigues", "IEA", "2");});
	}
	
	@Test
	void alteraIEAInvalidoPesquisador() {
		controllerPesquisador.cadastraEspecialidadeAluno("andrielly11@ccc.ufcg.edu.br", 2, 8.6);
		
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", "IEA", null);});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", "IEA", "");});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", "IEA", "-1");});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("andrielly11@ccc.ufcg,edu.br", "IEA", "12");});
	}
	
	@Test
	void alteraFormacaoPesquisador() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		controllerPesquisador.alteraPesquisador("gauds@computacao", "formacao", "PosDoc");
		
		assertEquals("gauds (professor) - aaaaaa - gauds@computacao - http://aaaa - PosDoc - UASC - 11/11/2011", controllerPesquisador.exibePesquisador("gauds@computacao"));
	}
	
	@Test
	void alteraFormacaoPesquisadorNull() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador(null, "formacao", "PosDoc");});
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", null, "PosDoc");});
	}
	
	@Test
	void alteraFormacaoPesquisadorVazio() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("", "formacao", "PosDoc");});
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "  ", "PosDoc");});
	}
	
	@Test
	void alteraFormacaoInvalidaPesquisador() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "formacao", null);});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "formacao", "");});
	}
	
	@Test
	void alteraUnidadePesquisador() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		controllerPesquisador.alteraPesquisador("gauds@computacao", "unidade", "DSC");
		
		assertEquals("gauds (professor) - aaaaaa - gauds@computacao - http://aaaa - Doutorado - DSC - 11/11/2011", controllerPesquisador.exibePesquisador("gauds@computacao"));
	}
	
	@Test
	void alteraUnidadePesquisadorNull() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador(null, "unidade", "DSC");});
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", null, "DSC");});
	}
	
	@Test
	void alteraUnidadePesquisadorVazio() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("", "unidade", "DSC");});
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "  ", "DSC");});
	}
	
	@Test
	void alteraUnidadeInvalidaPesquisador() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "unidade", null);});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "unidade", "");});
	}

	@Test
	void alteraDataPesquisador() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		controllerPesquisador.alteraPesquisador("gauds@computacao", "data", "10/10/2010");
		
		assertEquals("gauds (professor) - aaaaaa - gauds@computacao - http://aaaa - Doutorado - UASC - 10/10/2010", controllerPesquisador.exibePesquisador("gauds@computacao"));
	}
	
	@Test
	void alteraDataPesquisadorNull() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador(null, "data", "10/10/2010");});
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", null, "10/10/2010");});
	}
	
	@Test
	void alteraDataPesquisadorVazio() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("", "data", "10/10/2010");});
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "  ", "10/10/2010");});
	}
	
	@Test
	void alteraDataInvalidaPesquisador() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraEspecialidadeProfessor("gauds@computacao", "Doutorado", "UASC", "11/11/2011");
		
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "data", null);});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "data", "");});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "data", "0701/1998");});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "data", "7/7/2007");});
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.alteraPesquisador("gauds@computacao", "data", "07/007/2007");});
	}
	
	@Test
	void listaPesquisadores() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		controllerPesquisador.cadastraPesquisador("eren jaeger", "externo", "tita de ataque", "eren@jaeger", "https://eren");
		
		assertEquals("eren jaeger (externo) - tita de ataque - eren@jaeger - https://eren | toninho rodrigues (externo) - toninho a bolonhesa - toninho@rodrigues - https://toninhoabolonhesa", controllerPesquisador.listaPesquisadores("externo"));
		assertEquals("gauds (professor) - aaaaaa - gauds@computacao - http://aaaa", controllerPesquisador.listaPesquisadores("professor"));
		assertEquals("Andrielly (estudante) - Perfeita demais - andrielly11@ccc.ufcg.edu.br - https://godspeed", controllerPesquisador.listaPesquisadores("estudante"));
	}
	
	@Test
	void listaPesquisadoresNull() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		controllerPesquisador.cadastraPesquisador("eren jaeger", "externo", "tita de ataque", "eren@jaeger", "https://eren");
		
		assertThrows(NullPointerException.class, () -> {controllerPesquisador.listaPesquisadores(null);});
		
	}
	
	@Test
	void listaPesquisadoresVazio() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		controllerPesquisador.cadastraPesquisador("eren jaeger", "externo", "tita de ataque", "eren@jaeger", "https://eren");
		
		assertThrows(IllegalArgumentException.class, () -> {controllerPesquisador.listaPesquisadores("");});
		
	}
	
	@Test
	void listaPesquisadoresTipoInvalido() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		controllerPesquisador.cadastraPesquisador("eren jaeger", "externo", "tita de ataque", "eren@jaeger", "https://eren");
		
		assertThrows(RuntimeException.class, () -> {controllerPesquisador.listaPesquisadores("drica linda");});
		
	}
	
	@Test
	void procuraPalavraChave() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "bolonhesa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		
		ArrayList<String> ex = new ArrayList<>();
		ex.add("toninho@rodrigues: toninho a bolonhesa");
		ex.add("gauds@computacao: bolonhesa");
		
		assertEquals(controllerPesquisador.procuraPalavraChave("bolonhesa"), ex);
		
	}
	@Test
	void testprocuraPalavraChaveNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerPesquisador.procuraPalavraChave(null);
		});
	}
	@Test
	void testprocuraPalavraChaveVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerPesquisador.procuraPalavraChave("");
		});
	}
	
	@Test
	void testSalvar() {
		controllerPesquisador.cadastraPesquisador("gauds", "professor", "aaaaaa", "gauds@computacao", "http://aaaa");
		controllerPesquisador.cadastraPesquisador("toninho rodrigues", "externo", "toninho a bolonhesa", "toninho@rodrigues", "https://toninhoabolonhesa");
		controllerPesquisador.cadastraPesquisador("eren jaeger", "externo", "tita de ataque", "eren@jaeger", "https://eren");
		
		controllerPesquisador.salvar();
	}
	
	@Test
	void testCarregar() {
		controllerPesquisador.carregar();
		
		assertEquals("eren jaeger (externo) - tita de ataque - eren@jaeger - https://eren | toninho rodrigues (externo) - toninho a bolonhesa - toninho@rodrigues - https://toninhoabolonhesa", controllerPesquisador.listaPesquisadores("externo"));
	}
}
