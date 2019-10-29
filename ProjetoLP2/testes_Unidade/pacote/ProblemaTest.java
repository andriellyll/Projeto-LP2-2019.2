package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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

}
