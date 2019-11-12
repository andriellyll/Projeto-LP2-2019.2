package pacote;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Period;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AtividadeTest {
	
	private Atividade atividade;
	private Period data;
	
	@BeforeEach
	public void criaAtividade() {
		
		Period data = Period.ofDays(8);
		atividade = new Atividade("A1", "Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.");
	}
	
	@Test
	public void criaAtividadeVazia() {
		
		assertThrows(IllegalArgumentException.class, () -> atividade = new Atividade("A1", "", "", ""));
		assertThrows(IllegalArgumentException.class, () -> atividade = new Atividade("A1", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", "", ""));
		assertThrows(IllegalArgumentException.class, () -> atividade = new Atividade("A1", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", ""));
		
	}
	@Test
	public void criaAtividadeNula() {
		
		assertThrows(NullPointerException.class, () -> atividade = new Atividade("A1", null, null, null));
		assertThrows(NullPointerException.class, () -> atividade = new Atividade("A1", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", null, null));
		assertThrows(NullPointerException.class, () -> atividade = new Atividade("A1", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", null));
		
	}

	@Test
	public void testHashCodeIguais() {
		Atividade atividade2 = new Atividade("A1", "Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		assertEquals(atividade.hashCode(), atividade2.hashCode());
	}

	@Test
	public void testHashCodeDiferentes() {
		Atividade atividade2 = new Atividade("A2", "Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo de fermentacao.", "MEDIO", "Degustadores podem sofrer com problemas de saude nessa atividade, tal como ser alergico a algum ingrediente da cerveja.");
		assertNotEquals(atividade.hashCode(), atividade2.hashCode()); 
	}

	@Test
	public void testEqualsObjectIguais() {
		Atividade atividade2 = new Atividade("A1", "Monitoramento de chats dos alunos de computacao do primeiro periodo.","BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		assertTrue(atividade.equals(atividade2));
	}

	@Test
	public void testEqualsObjectDiferentes() {
		Atividade atividade2 = new Atividade("A2", "Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo de fermentacao.", "MEDIO", "Degustadores podem sofrer com problemas de saude nessa atividade, tal como ser alergico a algum ingrediente da cerveja.");
		assertFalse(atividade.equals(atividade2));
	}

	@Test
	public void testAdicionaItem() {
		assertEquals(atividade.exibeItens(), "");
		atividade.adicionaItem("Monitoramento facebook/messenger",1);
		assertEquals(atividade.exibeItens(), "PENDENTE - Monitoramento facebook/messenger");
	}
	@Test
	public void testAdicionaItemNulo() {
		assertThrows(NullPointerException.class, () -> atividade.adicionaItem(null,1));

	}
	@Test
	public void testAdicionaItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> atividade.adicionaItem("",1));
	}

	@Test
	public void testItensPendentes() {
		atividade.adicionaItem("Monitoramento facebook/messenger",1);
		atividade.adicionaItem("Monitoramento slack",2);
		atividade.adicionaItem("Monitoramento discord",3);
		assertEquals(atividade.ItensPendentes(), 3);
		
		
	}

	@Test
	public void testItensRealizados() {
		atividade.adicionaItem("Monitoramento facebook/messenger",1);
		atividade.adicionaItem("Monitoramento slack",2);
		atividade.adicionaItem("Monitoramento discord",3);
		assertEquals(atividade.ItensRealizados(), 0);
	}

	@Test
	public void testExibeItens() {
		atividade.adicionaItem("Monitoramento facebook/messenger",1);
		atividade.adicionaItem("Monitoramento slack",2);
		assertEquals(atividade.exibeItens(), "PENDENTE - Monitoramento facebook/messenger | PENDENTE - Monitoramento slack");
	}

	@Test
	public void testToString() {
		assertEquals(atividade.toString(), "Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, o risco nao e elevado.)");
		 
	}
	
	@Test
	public void testExecutaAtividade() {
		atividade.adicionaItem("Monitoramento facebook/messenger", 1);
		atividade.executaAtividade(1, 10);
		assertEquals(atividade.getDuracao(), 10);
	}
	
	@Test
	public void testExecutaAtividadeInvalido() {
		assertThrows(IllegalArgumentException.class, () -> atividade.executaAtividade(2, 0), "Item nao encontrado.");
	}
	
	@Test
	public void testCadastraResultado() {
		atividade.cadastraResultado("Monitoramento do facebook realizado com sucesso");
		atividade.cadastraResultado("Monitoramento do slack realizado com sucesso");
		assertEquals(atividade.listaResultados(), "Monitoramento do facebook realizado com sucesso | Monitoramento do slack realizado com sucesso");
	}
	
	@Test
	public void testCadastraResultadoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> atividade.cadastraResultado(null), "Campo resultado nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> atividade.cadastraResultado("   "), "Campo resultado nao pode ser nulo ou vazio.");
	}
	
	@Test
	public void testRemoveResultado() {
		atividade.cadastraResultado("Monitoramento do facebook realizado com sucesso");
		atividade.cadastraResultado("Monitoramento do slack realizado com sucesso");
	}
	
	@Test
	public void testListaResultados() {
		
	}
}
