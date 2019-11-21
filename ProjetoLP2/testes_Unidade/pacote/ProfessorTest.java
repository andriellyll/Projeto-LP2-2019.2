package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pesquisador.Professor;

/**
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 *
 */
class ProfessorTest {

	@Test
	void testProfessor() {
		@SuppressWarnings("unused")
		Professor prof = new Professor("Doutorado", "UASC", "01/11/2009");
	}

	@Test
	void testProfessorNull() {
		assertThrows(NullPointerException.class, () -> new Professor(null, "UASC", "01/11/2009"));
		assertThrows(NullPointerException.class, () -> new Professor("Doutorado", null, "01/11/2009"));
		assertThrows(NullPointerException.class, () -> new Professor("Doutorado", "UASC", null));
	}

	@Test
	void testProfessorVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Professor("", "UASC", "01/11/2009"));
		assertThrows(IllegalArgumentException.class, () -> new Professor("Doutorado", "", "01/11/2009"));
		assertThrows(IllegalArgumentException.class, () -> new Professor("Doutorado", "UASC", "  "));
	}

	@Test
	void testProfessorDataInvalida() {
		assertThrows(IllegalArgumentException.class, () -> new Professor("Doutorado", "UASC", "0111/2009"));
		assertThrows(IllegalArgumentException.class, () -> new Professor("Doutorado", "UASC", "01/11/20009"));
		assertThrows(IllegalArgumentException.class, () -> new Professor("Doutorado", "UASC", "011/11/2009"));
		assertThrows(IllegalArgumentException.class, () -> new Professor("Doutorado", "UASC", "01/101/2009"));

	}

	@Test
	void testToString() {
		Professor prof = new Professor("Doutorado", "UASC", "09/09/2009");
		assertEquals(
				"henrique (professor) - henrique pfto - henrique@lemos - https://henrique - Doutorado - UASC - 09/09/2009",
				prof.toString("henrique", "professor", "henrique pfto", "henrique@lemos", "https://henrique"));
	}

	@Test
	void testSetFormacao() {
		Professor prof = new Professor("Doutorado", "UASC", "09/09/2009");
		prof.setEspecialidade("formacao", "Mestrado");
		assertEquals(
				"henrique (professor) - henrique pfto - henrique@lemos - https://henrique - Mestrado - UASC - 09/09/2009",
				prof.toString("henrique", "professor", "henrique pfto", "henrique@lemos", "https://henrique"));
	}

	@Test
	void testSetData() {
		Professor prof = new Professor("Doutorado", "UASC", "09/09/2009");
		prof.setEspecialidade("data", "07/07/2007");
		assertEquals(
				"henrique (professor) - henrique pfto - henrique@lemos - https://henrique - Doutorado - UASC - 07/07/2007",
				prof.toString("henrique", "professor", "henrique pfto", "henrique@lemos", "https://henrique"));
	}

	@Test
	void testSetUnidade() {
		Professor prof = new Professor("Doutorado", "UASC", "09/09/2009");
		prof.setEspecialidade("unidade", "DSC");
		assertEquals(
				"henrique (professor) - henrique pfto - henrique@lemos - https://henrique - Doutorado - DSC - 09/09/2009",
				prof.toString("henrique", "professor", "henrique pfto", "henrique@lemos", "https://henrique"));
	}

	@Test
	void setEspecialidadeNull() {
		Professor prof = new Professor("Doutorado", "UASC", "09/09/2009");
		assertThrows(NullPointerException.class, () -> prof.setEspecialidade(null, "UASC"));
		assertThrows(NullPointerException.class, () -> prof.setEspecialidade("formacao", null));
	}

	@Test
	void setEspecialidadeVazio() {
		Professor prof = new Professor("Doutorado", "UASC", "09/09/2009");
		assertThrows(IllegalArgumentException.class, () -> prof.setEspecialidade("", "UASC"));
		assertThrows(IllegalArgumentException.class, () -> prof.setEspecialidade("formacao", ""));
	}

	@Test
	void setEspecialidadeDataInvalida() {
		Professor prof = new Professor("Doutorado", "UASC", "09/09/2009");
		assertThrows(IllegalArgumentException.class, () -> prof.setEspecialidade("data", "07/120/2008"));
		assertThrows(IllegalArgumentException.class, () -> prof.setEspecialidade("data", "07/12/20008"));
		assertThrows(IllegalArgumentException.class, () -> prof.setEspecialidade("data", "07/122008"));
		assertThrows(IllegalArgumentException.class, () -> prof.setEspecialidade("data", "7/12/2008"));

	}

	@Test
	void setEspecialidadeInvalida() {
		Professor prof = new Professor("Doutorado", "UASC", "09/09/2009");
		assertThrows(RuntimeException.class, () -> prof.setEspecialidade("bomdia", "UASC"));
		assertThrows(RuntimeException.class, () -> prof.setEspecialidade("drica", "aaa"));
	}
}