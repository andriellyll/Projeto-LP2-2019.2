package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ObjetivoTest {

	Objetivo objetivo = new Objetivo("GERAL","Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4,2, "O1");
	
	@Test
	public void testObjetivoNull() {
		assertThrows(NullPointerException.class, () -> {
			new Objetivo(null, "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2, "O1");
		});
		assertThrows(NullPointerException.class, () -> {
			new Objetivo("GERAL", null, 4, 2, "O1");
		});
	}
	
	@Test
	public void testObjetivoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("GERAL", " ", 4, 2, "O1");
		});
	}
	
	@Test 
	public void testTipoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2, "O1");
		});
	}
	
	@Test
	public void testAderenciaouViabilidadeInvalida() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 0, 2, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 0, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", -1, 2, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, -1, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 6, 2, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 6, "O1");
		});
	}
	
	@Test
	public void testToString() {
		new Objetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2, "O1");
		assertEquals("O1 - GERAL - Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao. - 6", objetivo.toString());
	}
	
	@Test
	public void associaPesquisa() {
		Pesquisa pesquisa = new Pesquisa("SEG1", "Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		assertTrue(objetivo.associaPesquisa(pesquisa));
	}
	
	@Test
	public void associaPesquisaJaAssociada() {
		Pesquisa pesquisa = new Pesquisa("SEG1", "Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		objetivo.associaPesquisa(pesquisa);
		assertFalse(objetivo.associaPesquisa(pesquisa));
	}
	
	@Test
	public void associaComOutraPesquisaJaAssociada() {
		Pesquisa pesquisa = new Pesquisa("SEG1", "Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		objetivo.associaPesquisa(pesquisa);
		Pesquisa pesquisa2 = new Pesquisa("SEG2", "extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		assertThrows(RuntimeException.class, () -> {
			objetivo.associaPesquisa(pesquisa2);
		});
	}
	
	@Test
	public void desassociaPesquisaAssociada() {
		Pesquisa pesquisa = new Pesquisa("SEG1", "Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		objetivo.associaPesquisa(pesquisa);
		assertTrue(objetivo.desassociaPesquisa(pesquisa));
	}
	
	@Test
	public void desassociaPesquisaNaoAssociada() {
		Pesquisa pesquisa = new Pesquisa("SEG1", "Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		objetivo.associaPesquisa(pesquisa);
		Pesquisa pesquisa2 = new Pesquisa("SEG2", "extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		assertFalse(objetivo.desassociaPesquisa(pesquisa2));
		
	}
	@Test
	void testBusca() {
		assertEquals("O1: Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao." , objetivo.procuraPalavraChave("Diminuir"));
		assertEquals("", objetivo.procuraPalavraChave("Aumentar"));
	}
	@Test
	void testBuscaNull() {
		assertThrows(NullPointerException.class, () -> {
			objetivo.procuraPalavraChave(null);
		});
	}
	@Test
	void testBuscaVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			objetivo.procuraPalavraChave("");
		});
	}
}