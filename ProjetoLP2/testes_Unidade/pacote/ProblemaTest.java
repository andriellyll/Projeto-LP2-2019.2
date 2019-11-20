package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 *
 */
public class ProblemaTest {

	@Test
	public void testProblemaNull() {

	}

	@Test
	public void testProblemaVazio() {

	}

	@Test
	public void testProblemaViabilidadeInvalida() {

	}

	@Test
	public void testToString() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		assertEquals("P1 - A dificuldade da predicao do sistema eleitoral brasileiro - 1", problema.toString());
	}

	@Test
	void testBusca() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		assertEquals("P1: A dificuldade da predicao do sistema eleitoral brasileiro",
				problema.procuraPalavraChave("dificuldade"));
		assertEquals("", problema.procuraPalavraChave("Aumentar"));
	}

	@Test
	void testBuscaNull() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		assertThrows(NullPointerException.class, () -> {
			problema.procuraPalavraChave(null);
		});
	}

	@Test
	void testBuscaVazio() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		assertThrows(IllegalArgumentException.class, () -> {
			problema.procuraPalavraChave("");
		});
	}
}
