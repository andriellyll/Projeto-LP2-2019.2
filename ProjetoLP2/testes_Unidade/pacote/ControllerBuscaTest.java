package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerBuscaTest {
	
	private ControllerBusca controllerBusca;
	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerProblemaObjetivo controllerProblemaObjetivo;
	private ControllerAtividade controllerAtividade;

	@BeforeEach
	void setUp() throws Exception {
		controllerPesquisador = new ControllerPesquisador();
		controllerProblemaObjetivo = new ControllerProblemaObjetivo();
		controllerAtividade = new ControllerAtividade(controllerPesquisa);
		controllerPesquisa = new ControllerPesquisa(controllerPesquisador, controllerProblemaObjetivo);
		controllerPesquisa.cadastraPesquisa("Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto",
				"Economia, Bolsa de Valores");
		controllerPesquisador.cadastraPesquisador("Andrielly", "primeira estudante", "Perfeita demais",
				"andrielly11@ccc.ufcg.edu.br", "https://godspeed");
		controllerProblemaObjetivo.cadastraProblema("primeira O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", 2);
		controllerProblemaObjetivo.cadastraObjetivo("GERAL", "primeira Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2);
		controllerAtividade.cadastraAtividade("primeira E so um teste hahahahahahahha", "ALTO", "Se eu nao fizer, titio Gaudencio briga.");
	}	
	@Test
	public void testControllerBusca() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
	}

	@Test
	public void testBuscaString() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertEquals(controllerBusca.busca("primeira"), "ECO1: Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto | P1: primeira O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo | O1: primeira Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao. | A1: primeira E so um teste hahahahahahahha");
	}
	@Test
	public void testBuscaStringNula() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertThrows(NullPointerException.class, () -> {
			controllerBusca.busca(null);
		});
	}
	@Test
	public void testBuscaStringVazia() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerBusca.busca("");
		});
	}

	@Test
	public void testContaResultadosBusca() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertEquals(controllerBusca.contaResultadosBusca("primeira"), 4);
	}
	@Test
	public void testContaResultadosBuscaNula() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertThrows(NullPointerException.class, () -> {
			controllerBusca.contaResultadosBusca(null);
		});
	}
	@Test
	public void testContaResultadosBuscaVazia() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerBusca.contaResultadosBusca("");
		});
	}

	@Test
	public void testBuscaStringInt() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertEquals(controllerBusca.busca("primeira", 3), "O1: primeira Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.");
	}
	@Test
	public void testBuscaStringIntNula() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertThrows(NullPointerException.class, () -> {
			controllerBusca.busca(null, 1);
		});
	}
	@Test
	public void testBuscaStringIntVazia() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerBusca.busca("", 1);
		});
	}
	@Test
	public void testBuscaStringIntMenorQueZero() {
		controllerBusca = new ControllerBusca(controllerPesquisa, controllerPesquisador, controllerProblemaObjetivo, controllerAtividade);
		assertThrows(IllegalArgumentException.class, () -> {
			controllerBusca.busca("primeira", -1);
		});
	}

}
