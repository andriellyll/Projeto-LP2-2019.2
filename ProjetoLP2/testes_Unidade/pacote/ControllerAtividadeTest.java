package pacote;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerAtividadeTest {
	
	private ControllerAtividade controllerAtividade;
	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerProblemaObjetivo controllerProblemaObjetivo;
	
	@BeforeEach
	public void criaControllerAtividade() {
		controllerPesquisa = new ControllerPesquisa(controllerPesquisador, controllerProblemaObjetivo);
		controllerAtividade = new ControllerAtividade(controllerPesquisa);
		controllerPesquisa.cadastraPesquisa("Olha um teste de pesquisa ai gente", "Bulhunfas");
		controllerAtividade.cadastraAtividade("E so um teste hahahahahahahha", "ALTO", "Se eu nao fizer, titio Gaudencio briga.");
		controllerAtividade.cadastraItem("A1", "Tem que fazer senao Anderson briga tambem.");
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
		controllerAtividade.exibeAtividade("A2");
		controllerAtividade.apagaAtividade("A2");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.exibeAtividade("A2"));
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
		assertEquals(controllerAtividade.exibeAtividade("A2"),"Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, o risco nao e elevado.)");
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
		controllerAtividade.cadastraItem("A2", "Monitoramento facebook/messenger");
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
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.cadastraItem("A2","Monitoramento facebook/messenger"));
	}

	@Test
	public void testContaItensPendentes() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A2", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A2", "Monitoramento slack");
		controllerAtividade.cadastraItem("A2", "Monitoramento discord");
		assertEquals(controllerAtividade.contaItensPendentes("A2"), 3);
	}

	@Test
	public void testContaItensRealizados() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A2", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A2", "Monitoramento slack");
		controllerAtividade.cadastraItem("A2", "Monitoramento discord");
		assertEquals(controllerAtividade.contaItensRealizados("A1"), 0);
	}
	
	@Test
	public void testContaItensPendentesAtividadeNula() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A2", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A2", "Monitoramento slack");
		controllerAtividade.cadastraItem("A2", "Monitoramento discord");
		assertThrows(NullPointerException.class, () -> controllerAtividade.contaItensPendentes(null));
	}
	
	@Test
	public void testContaItensPendentesAtividadeVazia() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A2", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A2", "Monitoramento slack");
		controllerAtividade.cadastraItem("A2", "Monitoramento discord");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.contaItensPendentes(""));
	}
	
	@Test
	public void testContaItensRealizadosAtividadeNula() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A2", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A2", "Monitoramento slack");
		controllerAtividade.cadastraItem("A2", "Monitoramento discord");
		assertThrows(NullPointerException.class, () -> controllerAtividade.contaItensRealizados(null));
	}
	
	@Test
	public void testContaItensRealizadosAtividadeVazia() {
		controllerAtividade.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO","Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		controllerAtividade.cadastraItem("A2", "Monitoramento facebook/messenger");
		controllerAtividade.cadastraItem("A2", "Monitoramento slack");
		controllerAtividade.cadastraItem("A2", "Monitoramento discord");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.contaItensRealizados(""));
	}
	
//---------------------------------------- Novos testes ControllerAtividade ---------------------------------------------
	
	@Test
	public void testExecutaAtividade() {
		controllerAtividade.associaPesquisa("BUL1", "A1");
		controllerAtividade.executaAtividade("A1", 1, 10);
		assertEquals(controllerAtividade.getDuracao("A1"), 10);
	}
	
	@Test
	public void testExecutaAtividadeInvalido() {
		assertThrows(NullPointerException.class, () -> controllerAtividade.executaAtividade(null, 1, 100), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.executaAtividade("A1", 0, 101), "Item nao pode ser nulo ou negativo.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.executaAtividade("A1", -1, 102), "Item nao pode ser nulo ou negativo.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.executaAtividade("A1", 1, 0), "Duracao nao pode ser nula ou negativa.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.executaAtividade("A1", 1, -1), "Duracao nao pode ser nula ou negativa.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.executaAtividade("A2", 1, 103), "Atividade nao encontrada");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.executaAtividade("A2", 2, 103), "Item nao encontrado.");
	}
	
	@Test
	public void testCadastraResultado() {
		controllerAtividade.cadastraResultado("A1", "So sucesso");
		assertEquals(controllerAtividade.listaResultados("A1"), "So sucesso");
	}
	
	@Test
	public void testCadastraResultadoInvalido() {
		assertThrows(NullPointerException.class, () -> controllerAtividade.cadastraResultado(null, "hahahahahahahahhaa"), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.cadastraResultado("", "ahahhahahahahahahahaha"), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(NullPointerException.class, () -> controllerAtividade.cadastraResultado("A1", null), "Resultado nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.cadastraResultado("A1", ""), "Resultado nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.cadastraResultado("A2", "hahahahhahahahahahah"), "Atividade nao encontrada");
	}
	
	@Test
	public void testRemoveResultado() {
		controllerAtividade.cadastraResultado("A1", "So sucesso");
		controllerAtividade.cadastraResultado("A1", "Este grupo e um arraso no projeto");
		controllerAtividade.cadastraResultado("A1", "Gaudencio, Anderson e Jaciane são os melhores");
		controllerAtividade.removeResultado("A1", 1);
		assertEquals(controllerAtividade.listaResultados("A1"), "Este grupo e um arraso no projeto | Gaudencio, Anderson e Jaciane são os melhores");
	}
	
	@Test
	public void testRemoveResultadoInvalido() {
		assertThrows(NullPointerException.class, () -> controllerAtividade.removeResultado(null, 1), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.removeResultado("A1", 0), "numeroResultado nao pode ser nulo ou negativo.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.removeResultado("A1", -1), "numeroResultado nao pode ser nulo ou negativo.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.removeResultado("", 1), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.removeResultado("A2", 1), "Atividade nao encontrada");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.removeResultado("A2", 10), "Resultado nao encontrado.");
	}
	
	@Test
	public void testListaResultados() {
		controllerAtividade.cadastraResultado("A1", "So sucesso");
		controllerAtividade.cadastraResultado("A1", "Este grupo e um arraso no projeto");
		controllerAtividade.cadastraResultado("A1", "Gaudencio, Anderson e Jaciane são os melhores");
		assertEquals(controllerAtividade.listaResultados("A1"), "So sucesso | Este grupo e um arraso no projeto | Gaudencio, Anderson e Jaciane são os melhores");
	}
	
	@Test
	public void testListaResultadosInvalido() {
		assertThrows(NullPointerException.class, () -> controllerAtividade.listaResultados(null), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.listaResultados(""), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.listaResultados("A2"), "Atividade nao encontrada");
	}
	
	@Test
	public void testAssociaPesquisa() {
		controllerAtividade.cadastraAtividade("Um teste para complementar o outro teste", "BAIXO", "Esse não é importante ;P");
		assertTrue(controllerAtividade.associaPesquisa("BUL1", "A2"));
	}
	
	@Test
	public void testAssociaPesquisaInvalido() {
		controllerPesquisa.cadastraPesquisa("To cansado de fazer testes desta classe", "Bulhunfas");
		controllerPesquisa.encerraPesquisa("BUL2", "E so um teste lindo");
		
		assertThrows(NullPointerException.class, () -> controllerAtividade.associaPesquisa(null, "A1"), "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.associaPesquisa("", "A1"), "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		assertThrows(NullPointerException.class, () -> controllerAtividade.associaPesquisa("BUL1", null), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.associaPesquisa("BUL1", ""), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.associaPesquisa("BUL1", "A2"), "Atividade nao encontrada");
		assertThrows(RuntimeException.class, () -> controllerAtividade.associaPesquisa("BUL3", "A1"), "Pesquisa nao encontrada.");
		assertThrows(RuntimeException.class, () -> controllerAtividade.associaPesquisa("BUL3", "A1"), "Pesquisa desativada.");
	}
	
	@Test
	public void testDesassociaPesquisa() {
		controllerAtividade.associaPesquisa("BUL1", "A1");
		controllerAtividade.cadastraAtividade("Um teste para complementar o outro teste", "BAIXO", "Esse não é importante ;P");
		controllerAtividade.associaPesquisa("BUL1", "A2");
		assertTrue(controllerAtividade.desassociaPesquisa("BUL1", "A1"));
	}
	
	@Test
	public void testDesassociaPesquisaInvalido() {
		controllerAtividade.associaPesquisa("BUL1", "A1");
		controllerPesquisa.cadastraPesquisa("To cansado de fazer testes desta classe", "Bulhunfas");
		controllerPesquisa.encerraPesquisa("BUL2", "E so um teste lindo");
		assertThrows(NullPointerException.class, () -> controllerAtividade.desassociaPesquisa(null, "A1"), "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.desassociaPesquisa("", "A1"), "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		assertThrows(NullPointerException.class, () -> controllerAtividade.desassociaPesquisa("BUL1", null), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.desassociaPesquisa("BUL1", ""), "Campo codigoAtividade nao pode ser nulo ou vazio.");
		assertThrows(RuntimeException.class, () -> controllerAtividade.desassociaPesquisa("BUL3", "A1"), "Pesquisa nao encontrada.");
		assertThrows(RuntimeException.class, () -> controllerAtividade.desassociaPesquisa("BUL3", "A1"), "Pesquisa desativada.");
		assertThrows(IllegalArgumentException.class, () -> controllerAtividade.desassociaPesquisa("BUL1", "A2"), "Atividade nao encontrada");
	}
	@Test
	public void procuraPalavraChaveDescricoes() {
		controllerAtividade.cadastraAtividade("teste", "ALTO", "eh teste");
		ArrayList<String> ex = new ArrayList<>();
		ex.add("A2: teste");
		ex.add("A2: eh teste");
		ex.add("A1: E so um teste hahahahahahahha");
		
		assertEquals(controllerAtividade.procuraPalavraChave("teste"), ex);
		
	}
	
	@Test
	public void testprocuraPalavraChaveNull() {
		assertThrows(NullPointerException.class, () -> {
			controllerAtividade.procuraPalavraChave(null);
		});
	}
	@Test
	public void testprocuraPalavraChaveVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			controllerAtividade.procuraPalavraChave("");
		});
	}
	
}
