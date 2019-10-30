package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pacote.Pesquisa;

class PesquisaTest {

	private Pesquisa test1;
	private Pesquisa test2;
	
	@BeforeEach
	void setUp() throws Exception {
		test1 = new Pesquisa("SEG1", "Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola", "Seguranca publica");
		test2 = new Pesquisa("BLA1", "Nao espere a Black Friday chegar. Sua pesquisa de precos deve começar ja", "Black Friday, Casos do mes");
	}

	@Test
	void testPesquisa() {
		new Pesquisa("BLA2", "Cuidado com os golpes nesta black frida, saiba com prevenir-se", "Black Friday, Golpes, Seguranca na internet");
		new Pesquisa("SMA1", "Vale a pena investir em um smartphone top de linha de dois anos atrás?", "Smartphone, Tecnologia, Android, IOS");
	}
	
	@Test
	void testPesquisaInvalido() {
		assertThrows(NullPointerException.class, () -> new Pesquisa(null, "Cuidado com os golpes nesta black frida, saiba com prevenir-se", "Black Friday, Golpes, Seguranca na internet"), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(NullPointerException.class, () -> new Pesquisa("BLA2", null, "Black Friday, Casos do mes"), "Descricao nao pode ser nula ou vazia.");
		assertThrows(NullPointerException.class, () -> new Pesquisa("BLA2", "Cuidado com os golpes nesta black frida, saiba com prevenir-se", null), "Formato do campo de interesse invalido.");
		
		assertThrows(IllegalArgumentException.class, () -> new Pesquisa(" ", "Vale a pena investir em um smartphone top de linha de dois anos atrás?", "Smartphone, Tecnologia, Android, IOS"), "Codigo nao pode ser nulo ou vazio."); 
		assertThrows(IllegalArgumentException.class, () -> new Pesquisa("SMA1", "        ", "Smartphone, Tecnologia, Android, IOS"), "Descricao nao pode ser nula ou vazia."); 
		assertThrows(IllegalArgumentException.class, () -> new Pesquisa("SMA1", "Vale a pena investir em um smartphone top de linha de dois anos atrás?", ""), "Formato do campo de interesse invalido."); 
	}

	@Test
	void testSetDescricao() {
		test2.setDescricao("Sua pesquisa de precos deve comecar ja");
		assertEquals(test2.toString(), "BLA1 - Sua pesquisa de precos deve comecar ja - Black Friday, Casos do mes");
	}
	
	@Test
	void testSetDescricaoInvalido() {
		assertThrows(NullPointerException.class, () -> test2.setDescricao(null), "Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> test2.setDescricao("  "), "Descricao nao pode ser nula ou vazia.");
	}

	@Test
	void testSetCampo() {
		test1.setCampo("Seguranca publica, Internacional");
		assertEquals(test1.toString(), "SEG1 - Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola - Seguranca publica, Internacional");
	}
	
	@Test
	void testSetCampoInvalido() {
		assertThrows(NullPointerException.class, () -> test1.setDescricao(null), "Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> test1.setDescricao("  "), "Descricao nao pode ser nula ou vazia.");
	}

	@Test
	void testGetAtivacao() {
		test2.desativaPesquisa("Essa merda e so um teste");
		assertTrue(test1.getAtivacao());
		assertFalse(test2.getAtivacao());
	}

	@Test
	void testDesativaPesquisa() {
		test1.desativaPesquisa("Muito doido o assunto");
		assertFalse(test1.getAtivacao());
	}
	
	@Test
	void testDesativaPesquisaInvalido() {
		assertThrows(NullPointerException.class, () -> test1.desativaPesquisa(null), "Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> test1.desativaPesquisa("  "), "Descricao nao pode ser nula ou vazia.");
	}

	@Test
	void testAtivaPesquisa() {
		test2.ativaPesquisa();
		assertTrue(test2.getAtivacao());
	}
	
	@Test
	void testHashCode() {
		assertEquals(new Pesquisa("SEG1", "Sem vigias, segurança noturna de escolas é feita pela GCM", "Seguranca Publica, Sao Paulo").hashCode(), test1.hashCode());
		assertNotEquals(test1.hashCode(), test2.hashCode());
	}
	
	@Test
	void testEqualsObject() {
		assertEquals(new Pesquisa("MER1", "Imposto unico elevara mensalidade escolar e plano de saude, dizem empresas", "Mercado, Reforma tributaria"), new Pesquisa("MER1", "Governo vai enviar no dia 9 primeira proposta de reforma tributaria, diz secretario da Previdencia", "Mercado, Rforma tributaria"));
		assertNotEquals(new Pesquisa("MER1", "Imposto unico elevara mensalidade escolar e plano de saude, dizem empresas", "Mercado, Reforma tributaria"), new Pesquisa("MER2", "Governo vai enviar no dia 9 primeira proposta de reforma tributaria, diz secretario da Previdencia", "Mercado, Rforma tributaria"));
	}

	@Test
	void testToString() {
		assertEquals(test1.toString(), "SEG1 - Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola - Seguranca publica");
		assertEquals(test2.toString(), "BLA1 - Nao espere a Black Friday chegar. Sua pesquisa de precos deve começar ja - Black Friday, Casos do mes");
	}

}
