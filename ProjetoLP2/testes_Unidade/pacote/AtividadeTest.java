package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.Atividade;
import pesquisa.Pesquisa;

/**
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 *
 */
class AtividadeTest {

	private Atividade atividade;

	@BeforeEach
	public void criaAtividade() {
		atividade = new Atividade("A1", "Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		atividade.adicionaItem("Monitoramento facebook/messenger");
		Pesquisa pesquisa = new Pesquisa("PAR1", "Nao acredito que tenho que fazer isso ate aqui.", "Para ninguem");
		atividade.associaPesquisa(pesquisa);
	}

	@Test
	public void criaAtividadeVazia() {
		assertThrows(IllegalArgumentException.class, () -> atividade = new Atividade("", "", "", ""));
		assertThrows(IllegalArgumentException.class, () -> atividade = new Atividade("A1", "", "", ""));
		assertThrows(IllegalArgumentException.class, () -> atividade = new Atividade("A1",
				"Monitoramento de chats dos alunos de computacao do primeiro periodo.", "", ""));
		assertThrows(IllegalArgumentException.class,
				() -> atividade = new Atividade("A1",
						"Monitoramento de chats dos alunos de computacao do primeiro periodo.",
						"Monitoramento de chats dos alunos de computacao do primeiro periodo.", ""));
	}

	@Test
	public void criaAtividadeNula() {
		assertThrows(NullPointerException.class, () -> atividade = new Atividade(null, null, null, null));
		assertThrows(NullPointerException.class, () -> atividade = new Atividade("A1", null, null, null));
		assertThrows(NullPointerException.class, () -> atividade = new Atividade("A1",
				"Monitoramento de chats dos alunos de computacao do primeiro periodo.", null, null));
		assertThrows(IllegalArgumentException.class,
				() -> atividade = new Atividade("A1",
						"Monitoramento de chats dos alunos de computacao do primeiro periodo.",
						"Monitoramento de chats dos alunos de computacao do primeiro periodo.", "hahahahahahahahaha"));
		assertThrows(NullPointerException.class, () -> atividade = new Atividade("A1",
				"Monitoramento de chats dos alunos de computacao do primeiro periodo.", "ALTO", null));
	}

	@Test
	public void testHashCodeIguais() {
		Atividade atividade2 = new Atividade("A1",
				"Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		assertEquals(atividade.hashCode(), atividade2.hashCode());
	}

	@Test
	public void testHashCodeDiferentes() {
		Atividade atividade2 = new Atividade("A2",
				"Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo de fermentacao.",
				"MEDIO",
				"Degustadores podem sofrer com problemas de saude nessa atividade, tal como ser alergico a algum ingrediente da cerveja.");
		assertNotEquals(atividade.hashCode(), atividade2.hashCode());
	}

	@Test
	public void testEqualsObjectIguais() {
		Atividade atividade2 = new Atividade("A1",
				"Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		assertTrue(atividade.equals(atividade2));
	}

	@Test
	public void testEqualsObjectDiferentes() {
		Atividade atividade2 = new Atividade("A2",
				"Degustacao de uma nova remeca de cervejas, criadas a partir de um novo processo de fermentacao.",
				"MEDIO",
				"Degustadores podem sofrer com problemas de saude nessa atividade, tal como ser alergico a algum ingrediente da cerveja.");
		assertFalse(atividade.equals(atividade2));
	}

	@Test
	public void testAdicionaItem() {
		atividade.adicionaItem("Monitoramento facebook/messenger");
		assertEquals(atividade.exibeItens(),
				"PENDENTE - Monitoramento facebook/messenger | PENDENTE - Monitoramento facebook/messenger");
	}

	@Test
	public void testAdicionaItemNulo() {
		assertThrows(NullPointerException.class, () -> atividade.adicionaItem(null));

	}

	@Test
	public void testAdicionaItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> atividade.adicionaItem(""));
	}

	@Test
	public void testItensPendentes() {
		atividade.adicionaItem("Monitoramento facebook/messenger");
		atividade.adicionaItem("Monitoramento slack");
		atividade.adicionaItem("Monitoramento discord");
		assertEquals(atividade.ItensPendentes(), 4);
	}

	@Test
	public void testItensRealizados() {
		atividade.adicionaItem("Monitoramento facebook/messenger");
		atividade.adicionaItem("Monitoramento slack");
		atividade.adicionaItem("Monitoramento discord");
		assertEquals(atividade.ItensRealizados(), 0);
	}

	@Test
	public void testExibeItens() {
		atividade.adicionaItem("Monitoramento facebook/messenger");
		atividade.adicionaItem("Monitoramento slack");
		assertEquals(atividade.exibeItens(),
				"PENDENTE - Monitoramento facebook/messenger | PENDENTE - Monitoramento facebook/messenger | PENDENTE - Monitoramento slack");
	}

	@Test
	public void testToString() {
		assertEquals(atividade.toString(),
				"Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, o risco nao e elevado.)");

	}

//----------------------------------------- Novos testes Atividade ------------------------------------------------------

	@Test
	public void testExecutaAtividade() {
		atividade.executaAtividade(1, 10);
		assertEquals(atividade.getDuracao(), 10);
	}

	@Test
	public void testExecutaAtividadeInvalido() {
		Atividade atividade2 = new Atividade("A2", "Mais um objeto de atividade para testar", "ALTO",
				"Tem muita importancia deste teste");
		atividade.executaAtividade(1, 10);
		assertThrows(IllegalArgumentException.class, () -> atividade.executaAtividade(2, 30), "Item nao encontrado.");
		assertThrows(IllegalArgumentException.class, () -> atividade.executaAtividade(1, 25), "Item ja executado.");
		assertThrows(IllegalArgumentException.class, () -> atividade2.executaAtividade(1, 30),
				"Atividade sem associacoes com pesquisas.");
	}

	@Test
	public void testCadastraResultado() {
		atividade.cadastraResultado("Monitoramento do facebook realizado com sucesso");
		atividade.cadastraResultado("Monitoramento do slack realizado com sucesso");
		assertEquals(atividade.listaResultados(),
				"Monitoramento do facebook realizado com sucesso | Monitoramento do slack realizado com sucesso");
	}

	@Test
	public void testCadastraResultadoInvalido() {
		assertThrows(NullPointerException.class, () -> atividade.cadastraResultado(null),
				"Campo resultado nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> atividade.cadastraResultado("   "),
				"Campo resultado nao pode ser nulo ou vazio.");
	}

	@Test
	public void testRemoveResultado() {
		atividade.cadastraResultado("Monitoramento do facebook realizado com sucesso");
		atividade.cadastraResultado("Monitoramento do slack realizado com sucesso");
		atividade.removeResultado(2);
		assertEquals(atividade.listaResultados(), "Monitoramento do facebook realizado com sucesso");
	}

	@Test
	public void testRemoveResultadoInvalido() {
		atividade.cadastraResultado("Esse grupo e so sucesso");
		atividade.cadastraResultado("Monitoramento do slack realizado com sucesso");
		atividade.removeResultado(2);
		assertFalse(atividade.removeResultado(2));
		assertThrows(IllegalArgumentException.class, () -> atividade.removeResultado(3), "Resultado nao encontrado.");
	}

	@Test
	public void testListaResultados() {
		atividade.cadastraResultado("Esse grupo e so sucesso");
		atividade.cadastraResultado("Ainda mais com o melhor professor e os melhores monitores");
		assertEquals(atividade.listaResultados(),
				"Esse grupo e so sucesso | Ainda mais com o melhor professor e os melhores monitores");
	}

	@Test
	public void testAssociaPesquisa() {
		Pesquisa pesquisa = new Pesquisa("PAR2", "Mas uma pesquisa para ajudar nos testes", "Para testar");
		assertTrue(atividade.associaPesquisa(pesquisa));
	}

	@Test
	public void testAssociaPesquisaInvalido() {
		Pesquisa pesquisa = new Pesquisa("PAR3", "Olha a pesquisa ai gente", "Para ninguem");
		atividade.associaPesquisa(pesquisa);
		assertFalse(atividade.associaPesquisa(pesquisa));
	}

	@Test
	public void testDesassociaPesquisa() {
		assertTrue(atividade.desassociaPesquisa());
	}

	@Test
	public void testDesassociaPesquisaInvalido() {
		Atividade atividade2 = new Atividade("A2", "Mais um objeto de atividade para testar", "ALTO",
				"Tem muita importancia deste teste");
		assertFalse(atividade2.desassociaPesquisa());
	}

//----------------------------------------------------------------------------------------------------------------------------------------	

	@Test
	public void testGetSeguinteNaCadeia() {
		Atividade atividade2 = new Atividade("A2", "Mais um objeto de atividade para testar", "ALTO",
				"Tem muita importancia deste teste");
		atividade.adicionaNaCadeia(atividade2);
		assertEquals(atividade.getSeguinteNaCadeia(), atividade2);

	}

	@Test
	public void testGetSeguinteNaCadeiaSemSeguinte() {
		assertEquals(atividade.getSeguinteNaCadeia(), null);
	}

	@Test
	public void testAdicionaNaCadeia() {
		Atividade atividade2 = new Atividade("A2", "Mais um objeto de atividade para testar", "ALTO",
				"Tem muita importancia deste teste");
		atividade.adicionaNaCadeia(atividade2);
		Atividade atividade3 = new Atividade("A3", "Mais um objeto de atividade3 para testar", "ALTO",
				"Tem muita importancia deste teste");
		atividade2.adicionaNaCadeia(atividade3);
		assertEquals(atividade.getSeguinteNaCadeia(), atividade2);
		assertEquals(atividade2.getSeguinteNaCadeia(), atividade3);
	}

	@Test
	public void testAdicionaNaCadeiaLoop() {
		Atividade atividade2 = new Atividade("A2", "Mais um objeto de atividade para testar", "ALTO",
				"Tem muita importancia deste teste");
		atividade.adicionaNaCadeia(atividade2);
		assertThrows(RuntimeException.class, () -> {
			atividade2.adicionaNaCadeia(atividade);
			;
		});

	}

	@Test
	public void testRemoveDaCadeia() {
		Atividade atividade2 = new Atividade("A2", "Mais um objeto de atividade para testar", "ALTO",
				"Tem muita importancia deste teste");
		atividade.adicionaNaCadeia(atividade2);
		assertEquals(atividade.getSeguinteNaCadeia(), atividade2);
		atividade.removeSeguinteNaCadeia();
		assertEquals(atividade.getSeguinteNaCadeia(), null);

	}

	@Test
	public void testContaSeguintesNaCadeiasSemNenhumaSubsequente() {
		assertEquals(atividade.contaSeguintesNaCadeia(), 0);
	}

	@Test
	public void testContaSeguintesNaCadeia() {
		Atividade atividade2 = new Atividade("A2", "Mais um objeto de atividade para testar", "ALTO",
				"Tem muita importancia deste teste");
		atividade.adicionaNaCadeia(atividade2);
		Atividade atividade3 = new Atividade("A3", "Mais um objeto de atividade3 para testar", "ALTO",
				"Tem muita importancia deste teste");
		atividade2.adicionaNaCadeia(atividade3);
		assertEquals(atividade.contaSeguintesNaCadeia(), 2);
		assertEquals(atividade2.contaSeguintesNaCadeia(), 1);

	}

	@Test
	public void testAtividadeMaiorRisco() {
		Atividade atividade2 = new Atividade("A2", "Mais um objeto de atividade para testar", "ALTO",
				"Tem muita importancia deste teste");
		atividade.adicionaNaCadeia(atividade2);
		Atividade atividade3 = new Atividade("A3", "Mais um objeto de atividade3 para testar", "BAIXO",
				"Tem muita importancia deste teste");
		atividade2.adicionaNaCadeia(atividade3);
		Atividade atividade4 = new Atividade("A4", "Mais um objeto de atividade4 para testar", "MEDIO",
				"Tem muita importancia deste teste");
		atividade3.adicionaNaCadeia(atividade4);
		assertEquals(atividade.atividadeMaiorRisco("ALTO"), "A2");
	}

	@Test
	public void testExibeAtividade() {

		atividade.adicionaItem("To fazendo mais um teste so para testar");
		atividade.executaAtividade(1, 100);
		assertEquals(atividade.exibeAtividade(),
				"Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, o risco nao e elevado.)\n"
						+ "\t\t\t- REALIZADO - ITEM1\n" + "\t\t\t- PENDENTE - ITEM2");
	}

	@Test
	public void testGetResultados() {
		atividade.executaAtividade(1, 100);
		atividade.cadastraResultado("Esse grupo e so sucesso");
		atividade.cadastraResultado("Pegamos o melhor professor e os melhores monitores para nos auxiliar");
		assertEquals(atividade.getResultados(),
				"- Monitoramento de chats dos alunos de computacao do primeiro periodo.\n" + "\t\t\t- ITEM1 - 100\n"
						+ "\t\t\t- Esse grupo e so sucesso\n"
						+ "\t\t\t- Pegamos o melhor professor e os melhores monitores para nos auxiliar");
	}

	@Test
	public void testCompareTo() {
		Atividade atividade2 = new Atividade("A2", "Mais um objeto de atividade para testar", "ALTO",
				"Tem muita importancia deste teste");
		assertEquals(atividade.compareTo(atividade2), -1);
	}

	@Test
	public void testGetCodigo() {
		assertEquals(atividade.getCodigo(), "A1");
	}

	@Test
	public void testGetRisco() {
		assertEquals(atividade.getRisco(), "BAIXO");
	}
}