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
	
}
/*


	@Test
	void testApagarObjetivo() {
		fail("Not yet implemented");
	}

	@Test
	void testExibeProblema() {
		fail("Not yet implemented");
	}

	@Test
	void testExibeObjetivo() {
		fail("Not yet implemented");
	}

}
*/