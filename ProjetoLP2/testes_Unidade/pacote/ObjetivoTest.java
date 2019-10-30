package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ObjetivoTest {

	@Test
	public void testObjetivoNull() {
		assertThrows(NullPointerException.class, () -> {
			Objetivo objetivo = new Objetivo(null, "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2, "O1");
		});
		assertThrows(NullPointerException.class, () -> {
			Objetivo objetivo = new Objetivo("GERAL", null, 4, 2, "O1");
		});
	}
	
	@Test
	public void testObjetivoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("GERAL", " ", 4, 2, "O1");
		});
	}
	
	@Test 
	public void testTipoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2, "O1");
		});
	}
	
	@Test
	public void testAderenciaouViabilidadeInvalida() {
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 0, 2, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 0, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", -1, 2, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, -1, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 6, 2, "O1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Objetivo objetivo = new Objetivo("tipo", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 6, "O1");
		});
	}
	
	@Test
	public void testToString() {
		Objetivo objetivo = new Objetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2, "O1");
		assertEquals("O1 - GERAL - Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao. - 6", objetivo.toString());
	}
}