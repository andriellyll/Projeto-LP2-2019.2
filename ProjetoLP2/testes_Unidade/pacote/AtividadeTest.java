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
		atividade = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","Monitoramento de chats dos alunos de computacao do primeiro periodo.", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1", data);
	}
	
//	@Test
//	public void criaAtividadeVazia() {
//		
//		assertThrows(IllegalArgumentException.class, () -> atividade = new Atividade("", "", "", data));
//		assertThrows(IllegalArgumentException.class, () -> atividade = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "", "", data));
//		assertThrows(IllegalArgumentException.class, () -> atividade = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", "", data));
//		
//	}
//	@Test
//	public void criaAtividadeNula() {
//		
//		assertThrows(NullPointerException.class, () -> atividade = new Atividade(null, null, null, data));
//		assertThrows(NullPointerException.class, () -> atividade = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", null, null, data));
//		assertThrows(NullPointerException.class, () -> atividade = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", null, data));
//		
//	}
//
//	@Test
//	public void testHashCodeIguais() {
//		Atividade atividade2 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","Monitoramento de chats dos alunos de computacao do primeiro periodo.", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", data);
//		assertEquals(atividade.hashCode(), atividade2.hashCode());
//	}
//
//	@Test
//	public void testHashCodeDiferentes() {
//		Atividade atividade2 = new Atividade("Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo de fermentacao.", "MEDIO", "Degustadores podem sofrer com problemas de saude nessa atividade, tal como ser alergico a algum ingrediente da cerveja.", data);
//		assertNotEquals(atividade.hashCode(), atividade2.hashCode());
//	}
//
//	@Test
//	public void testEqualsObjectIguais() {
//		Atividade atividade2 = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.","Monitoramento de chats dos alunos de computacao do primeiro periodo.", "Por se tratar de apenas um monitoramento, o risco nao e elevado.", data);
//		assertTrue(atividade.equals(atividade2));
//	}
//
//	@Test
//	public void testEqualsObjectDiferentes() {
//		Atividade atividade2 = new Atividade("Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo de fermentacao.", "MEDIO", "Degustadores podem sofrer com problemas de saude nessa atividade, tal como ser alergico a algum ingrediente da cerveja.", data);
//		assertFalse(atividade.equals(atividade2));
//	}
//
//	@Test
//	void testAdicionaItem() {
//		Item item = new Item("Monitoramento facebook/messenger",1);
//		
//	}

	@Test
	void testItensPendentes() {
		fail("Not yet implemented");
	}

	@Test
	void testItensRealizados() {
		fail("Not yet implemented");
	}

	@Test
	void testExibeItens() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
