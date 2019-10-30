package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerProblemaObjetivoTest {

	private ControllerProblemaObjetivo controllerProbObj;
	
	@BeforeEach
	public void criaController() {
		controllerProbObj = new ControllerProblemaObjetivo();
	}
	
	@Test
	public void testCadastraProblema() {
		controllerProbObj.cadastraProblema("O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", 2);
	}
	
	@Test
	public void testCadastraProblemaNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerProbObj.cadastraProblema(null, 2);
		});
	}
	
	@Test
	public void testCadastraProblemaVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.cadastraProblema("", 2);
		});
	}
	
	@Test
	public void testCadastraProblemaViabilidadeInvalida() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.cadastraProblema("O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", -1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.cadastraProblema("O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", 6);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.cadastraProblema("O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", 0);
		});
	}
	
	@Test
	public void testCadastraObjetivo() {
		controllerProbObj.cadastraObjetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2);
	}
	
	@Test
	public void testCadastraObjetivoNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerProbObj.cadastraObjetivo(null, "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2);
		});
		assertThrows(NullPointerException.class, () -> {
			controllerProbObj.cadastraObjetivo("GERAL", null, 4, 2);
		});
	}
	
	@Test
	public void testCadastraObjetivoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.cadastraObjetivo("", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.cadastraObjetivo("GERAL", " ", 4, 2);
		});
	}
	
	@Test
	public void testCadastraObjetivoViabilidadeInvalida() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.cadastraObjetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 0, 2);
		});
		assertThrows(IllegalArgumentException.class, () -> {	
			controllerProbObj.cadastraObjetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {	
			controllerProbObj.cadastraObjetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 6, 5);
		});
		assertThrows(IllegalArgumentException.class, () -> {	
			controllerProbObj.cadastraObjetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 5, 7);
		});
		assertThrows(IllegalArgumentException.class, () -> {	
			controllerProbObj.cadastraObjetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", -1, 1);
		});
		assertThrows(IllegalArgumentException.class, () -> {	
			controllerProbObj.cadastraObjetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 1, -1);
		});
	}

	@Test
	public void testApagarProblema() {
		String codigo = controllerProbObj.cadastraProblema("O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", 2);
		controllerProbObj.exibeProblema(codigo);
		controllerProbObj.apagarProblema(codigo);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.exibeProblema(codigo);
		});
	}
	
	@Test
	public void testApagarProblemaNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerProbObj.exibeProblema(null);
		});
	}
	
	@Test
	public void testApagarProblemaVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.exibeProblema("");
		});
	}
	
	@Test
	public void testApagarProblemaInexistente() {
		String codigo = controllerProbObj.cadastraProblema("O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", 2);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.exibeProblema("O2");
		});
	}

	@Test
	public void testApagarObjetivo() {
		String codigo = controllerProbObj.cadastraObjetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2);
		controllerProbObj.exibeObjetivo(codigo);
		controllerProbObj.apagarObjetivo(codigo);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.exibeObjetivo(codigo);	
		});
	}
	
	@Test
	public void testApagarObjetivoNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerProbObj.apagarObjetivo(null);
		});
	}
	
	@Test
	public void testApagarObjetivoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.apagarObjetivo("");
		});
	}
	
	@Test
	public void testApagarObjetivoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.apagarObjetivo("O1");
		});
	}
	@Test
	public void testExibeProblema() {
		String codigo = controllerProbObj.cadastraProblema("O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", 2);
		assertEquals(codigo + " - O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo - 2", controllerProbObj.exibeProblema(codigo));
	}

	

	@Test
	public void testExibeProblemaNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerProbObj.exibeProblema(null);
		});
	}
	
	@Test
	public void testExibeProblemaVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.exibeProblema("");
		});
	}
	
	@Test
	public void testExibeProblemaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.exibeProblema("O1");
		});
	}
	
	@Test
	public void testExibeObjetivo() {
		String codigo = controllerProbObj.cadastraObjetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2);
		assertEquals(codigo + " - GERAL - Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao. - 6", controllerProbObj.exibeObjetivo(codigo));
	}
	
	@Test
	public void testExibeObjetivoNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerProbObj.exibeObjetivo(null);
		});
	}
	
	@Test
	public void testExibeObjetivoVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.exibeObjetivo("");
		});
	}
	
	@Test
	public void testExibeObjetivoInexistente() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerProbObj.exibeObjetivo("O1");
		});
	}
}