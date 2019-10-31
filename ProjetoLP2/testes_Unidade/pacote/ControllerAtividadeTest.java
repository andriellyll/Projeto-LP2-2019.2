package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerAtividadeTest {
	
	private ControllerAtividade controllerAtividade;
	
	@BeforeEach
	public void criaControllerAtividade() {
		controllerAtividade = new ControllerAtividade();
	}


	@Test
	public void testCadastraAtividade() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		
	}
	@Test
	public void testCadastraAtividadeNula() {
		assertThrows(NullPointerException.class, () -> controllerAtividade.cadastraAtividade(null, null, null));
		assertThrows(NullPointerException.class, () -> controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", null, null));
		assertThrows(NullPointerException.class, () -> controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO", null));
		
	}
	@Test
	public void testCadastraAtividadeVazia() {
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.cadastraAtividade("", "", ""));
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "", ""));
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO", ""));
		
	}

	@Test
	public void testApagaAtividade() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.exibeAtividade("A1");
		controllerAtividade.apagaAtividade("A1");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.exibeAtividade("A1"));
		
	}
	@Test
	public void testApagaAtividadeNula() {
		assertThrows(NullPointerException.class, () -> controllerAtividade.apagaAtividade(null));
	}
	@Test
	public void testApagaAtividadeVazia() {
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.apagaAtividade(""));
	}
	@Test
	public void testApagaAtividadeNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.apagaAtividade("A2"));
	}

	@Test
	public void testExibeAtividade() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		assertEquals(controllerAtividade.exibeAtividade("A1"),"Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, o risco nao e elevado.)");
	}
	@Test
	public void testExibeAtividadeNula() {
		assertThrows(NullPointerException.class, () -> controllerAtividade.exibeAtividade(null));
	}
	@Test
	public void testExibeAtividadeVazia() {
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.exibeAtividade(""));
	}
	@Test
	public void testExibeAtividadeNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.apagaAtividade("A2"));
	}

	@Test
	public void testCadastraItem() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A1", "Monitoramento facebook/messenger");
	}
	@Test
	public void testCadastraItemNulo() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		assertThrows(NullPointerException.class, () -> controllerAtividade.cadastraItem("A1",null));
	}
	@Test
	public void testCadastraItemVazio() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.cadastraItem("A1",""));
	}
	@Test
	public void testCadastraItemAtividadeNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.cadastraItem("A1","Monitoramento facebook/messenger"));
	}

	@Test
	public void testContaItensPendentes() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A1", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A1", "Monitoramento slack");
		controllerAtividade.cadastraItem("A1", "Monitoramento discord");
		assertEquals(controllerAtividade.contaItensPendentes("A1"), 3);
	}

	@Test
	public void testContaItensRealizados() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A1", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A1", "Monitoramento slack");
		controllerAtividade.cadastraItem("A1", "Monitoramento discord");
		assertEquals(controllerAtividade.contaItensRealizados("A1"), 0);
	}
	@Test
	public void testContaItensPendentesAtividadeNula() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A1", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A1", "Monitoramento slack");
		controllerAtividade.cadastraItem("A1", "Monitoramento discord");
		assertThrows(NullPointerException.class, () -> controllerAtividade.contaItensPendentes(null));
	}
	@Test
	public void testContaItensPendentesAtividadeVazia() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A1", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A1", "Monitoramento slack");
		controllerAtividade.cadastraItem("A1", "Monitoramento discord");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.contaItensPendentes(""));
	}
	@Test
	public void testContaItensRealizadosAtividadeNula() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A1", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A1", "Monitoramento slack");
		controllerAtividade.cadastraItem("A1", "Monitoramento discord");
		assertThrows(NullPointerException.class, () -> controllerAtividade.contaItensRealizados(null));
	}
	@Test
	public void testContaItensRealizadosAtividadeVazia() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A1", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A1", "Monitoramento slack");
		controllerAtividade.cadastraItem("A1", "Monitoramento discord");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.contaItensRealizados(""));
	}
	

}
