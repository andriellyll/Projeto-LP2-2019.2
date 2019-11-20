package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 */
class AlunoTest {

	@Test
	void testProfessor() {
		@SuppressWarnings("unused")
		Aluno aluno = new Aluno(2, 8.6);
	}

	@Test
	void testProfessorAtributoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Aluno(-3, 8.9));
		assertThrows(IllegalArgumentException.class, () -> new Aluno(3, -1));
		assertThrows(IllegalArgumentException.class, () -> new Aluno(3, 11));
	}

	@Test
	void testToString() {
		Aluno aluno = new Aluno(3, 9.0);
		assertEquals("henrique (estudante) - henrique pfto - henrique@lemos - https://henrique - 3o SEMESTRE - 9.0",
				aluno.toString("henrique", "estudante", "henrique pfto", "henrique@lemos", "https://henrique"));
	}

	@Test
	void testSetSemestre() {
		Aluno aluno = new Aluno(3, 9.0);
		aluno.setEspecialidade("semestre", "4");
		assertEquals("henrique (estudante) - henrique pfto - henrique@lemos - https://henrique - 4o SEMESTRE - 9.0",
				aluno.toString("henrique", "estudante", "henrique pfto", "henrique@lemos", "https://henrique"));
	}

	@Test
	void testSetIEA() {
		Aluno aluno = new Aluno(3, 9.0);
		aluno.setEspecialidade("IEA", "9.7");
		assertEquals("henrique (estudante) - henrique pfto - henrique@lemos - https://henrique - 3o SEMESTRE - 9.7",
				aluno.toString("henrique", "estudante", "henrique pfto", "henrique@lemos", "https://henrique"));
	}

	@Test
	void testSetIEAInvalido() {
		Aluno aluno = new Aluno(3, 9.0);

		assertThrows(IllegalArgumentException.class, () -> aluno.setEspecialidade("IEA", "-1"));
		assertThrows(IllegalArgumentException.class, () -> aluno.setEspecialidade("IEA", "11"));
	}

	@Test
	void testSetSemestreInvalido() {
		Aluno aluno = new Aluno(3, 9.0);

		assertThrows(IllegalArgumentException.class, () -> aluno.setEspecialidade("semestre", "-1"));
	}

	@Test
	void setEspecialidadeNull() {
		Aluno aluno = new Aluno(5, 8.0);
		assertThrows(NullPointerException.class, () -> aluno.setEspecialidade(null, "8"));
		assertThrows(NullPointerException.class, () -> aluno.setEspecialidade("IEA", null));
	}

	@Test
	void setEspecialidadeVazio() {
		Aluno aluno = new Aluno(5, 8.0);
		assertThrows(IllegalArgumentException.class, () -> aluno.setEspecialidade("", "8"));
		assertThrows(IllegalArgumentException.class, () -> aluno.setEspecialidade("IEA", ""));
	}

	@Test
	void setEspecialidadeInvalida() {
		Aluno aluno = new Aluno(5, 8.0);
		assertThrows(RuntimeException.class, () -> aluno.setEspecialidade("drica", "8"));
		assertThrows(RuntimeException.class, () -> aluno.setEspecialidade("celtinha rebaixado", "9.0"));
	}

}