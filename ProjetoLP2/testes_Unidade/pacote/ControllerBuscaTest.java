package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerBuscaTest {
	

	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerProblemaObjetivo controllerProblemaObjetivo;
	private ControllerAtividadeTest controllerAtividadeTest;

	@BeforeEach
	void setUp() throws Exception {
		controllerPesquisador = new ControllerPesquisador();
		controllerProblemaObjetivo = new ControllerProblemaObjetivo();
		controllerAtividadeTest = new ControllerAtividadeTest();
		controllerPesquisa = new ControllerPesquisa(controllerPesquisador, controllerProblemaObjetivo);
	}	
	@Test
	public void testControllerBusca() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscaString() {
		fail("Not yet implemented");
	}

	@Test
	public void testContaResultadosBusca() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscaStringInt() {
		fail("Not yet implemented");
	}

}
