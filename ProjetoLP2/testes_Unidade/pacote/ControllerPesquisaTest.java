package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pacote.ControllerPesquisa;

class ControllerPesquisaTest {

	private ControllerPesquisa controle;
	
	@BeforeEach
	void setUp() throws Exception {
		controle = new ControllerPesquisa();
		controle.cadastraPesquisa("Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto", "Economia, Bolsa de Valores");
		controle.cadastraPesquisa("Equador na encruzilhada regional", "Geopolitica, America Latina, Intenacional");
		controle.cadastraPesquisa("Oleo em Boipeba leva turistas a evitar mar e mudar programacao", "Ecologia, Desastre Ambiental, Meio Ambiente, Natureza");
	}

	@Test
	void testCadastraPesquisa() {
		assertEquals(controle.cadastraPesquisa("Brasil assina oito acordos bilaterais com Emirados Arabes", "Geografia Politica, Bolsonaro em viagem ao Oriente"), "GEO2");
		assertEquals(controle.cadastraPesquisa("Jesus e denunciado por atraso e tem presenca ameacada no Fla contra o River", "Libertadores"), "LIB1");
		assertEquals(controle.cadastraPesquisa("Entenda as regras de transicao da reforma da Previdencia", "Economia, Brasil, Reforma da Previdencia"), "ECO3");
		assertEquals(controle.cadastraPesquisa("Dona do Google amplia receita com publicidade, mas custos impactam lucro", "Tecnologia, Google, Alphabet, Financas"), "TEC1");
	}
	
	@Test
	void testCadastraPesquisaInvalido() {
		assertThrows(NullPointerException.class, () -> controle.cadastraPesquisa(null, "Computacao"), "Descricao nao pode ser nula ou vazia.");
		assertThrows(NullPointerException.class, () -> controle.cadastraPesquisa("Eddie Murphy volta a boa forma no papel de comediante boca suja", null), "Formato do campo de interesse invalido.");
		assertThrows(IllegalArgumentException.class, () -> controle.cadastraPesquisa("  ", "Computacao"), "Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> controle.cadastraPesquisa("Em audio, Queiroz xinga promotores e diz que investigacao", "       "), "Formato do campo de interesse invalido.");
		assertThrows(IllegalArgumentException.class, () -> controle.cadastraPesquisa("A seis dias do Enem, 1,2 milhão de inscritos não sabem local da prova", "TOP Noticias, Educacao, ENEM, ENEM 2019, Universidades"), "Formato do campo de interesse invalido.");
		assertThrows(IllegalArgumentException.class, () -> controle.cadastraPesquisa("Auxilio-doenca e aposentadoria por invalidez mudam na nova Previdencia", "Reforma da Previdencia, Beneficios pagos quando trabalhador fica incapacitado para o trabalho terao valor menor, Os beneficios pagos a trabalhadores que ficarem incapacitados para o trabalhoserao calculados com base em todos os salarios de contribuicao desde julho de 1994"), "Formato do campo de interesse invalido.");
		assertThrows(IllegalArgumentException.class, () -> controle.cadastraPesquisa("Teste para dar erro hahahahahahahahha", "ab"), "Formato do campo de interesse invalido.");
	}

	@Test
	void testAlteraPesquisa() {
		controle.alteraPesquisa("ECO2", "CAMPO", "Ecossistemas, Desastre Ambiental");
		controle.alteraPesquisa("GEO1", "DESCRICAO", "Parlamentares pro-democracia interrompem discurso de lider de Hong Kong");
		
		assertEquals(controle.exibePesquisa("ECO2"), "ECO2 - Oleo em Boipeba leva turistas a evitar mar e mudar programacao - Ecossistemas, Desastre Ambiental");
		assertEquals(controle.exibePesquisa("GEO1"), "GEO1 - Parlamentares pro-democracia interrompem discurso de lider de Hong Kong - Geopolitica, America Latina, Intenacional");
	}
	
	@Test
	void testAlteraPesquisaInvalido() {
		
		assertThrows(NullPointerException.class, () -> controle.alteraPesquisa(null, "CAMPO", "Programacao 2, UFCG"), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(NullPointerException.class, () -> controle.alteraPesquisa("ECO2", null, "Bulhunfas"), "Conteudo a ser alterado nao pode ser nulo ou vazio.");
		assertThrows(NullPointerException.class, () -> controle.alteraPesquisa("GEO1", "DESCRICAO", null), "Descricao nao pode ser nula ou vazia.");
		assertThrows(NullPointerException.class, () -> controle.alteraPesquisa("GEO1", "CAMPO", null), "Formato do campo de interesse invalido.");
		
		assertThrows(IllegalArgumentException.class, () -> controle.alteraPesquisa("  ", "DESCRICAO", "Ba ba ba, babanana, Ba ba ba, Potetotaaaaa"), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.alteraPesquisa("ECO1", "     ", "Como o Gaudencio e ecologico"), "Conteudo a ser alterado nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.alteraPesquisa("GEO1", "descricao", ""), "Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> controle.alteraPesquisa("ECO2", "campo", " "), "Formato do campo de interesse invalido.");
	
		assertThrows(IllegalArgumentException.class, () -> controle.alteraPesquisa("ECO1", "SEGUNDO CAMPO", "Vai dar erro seu burro!!!"), "Nao e possivel alterar esse valor de pesquisa.");
		controle.encerraPesquisa("GEO1", "Anderson disse que e falso");
		assertThrows(IllegalArgumentException.class, () -> controle.alteraPesquisa("GEO1", "Campo", "A vida e complicada porem tudo tem solucao"), "Pesquisa desativada.");
		
	}

	@Test
	void testEncerraPesquisa() {
		controle.encerraPesquisa("ECO1", "Fake news");
		assertFalse(controle.pesquisaEhAtiva("ECO1"));
	}
	
	@Test
	void testEncerraPesquisaInvalido() {
		controle.encerraPesquisa("ECO1", "Fake news");
		assertThrows(NullPointerException.class, () -> controle.encerraPesquisa(null, "Gaudencio disse que e falso"), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.encerraPesquisa(" ", "A vida e problematica"), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.encerraPesquisa("ECO1", "Noticia falsa"), "Pesquisa desativada.");
	}
	
	@Test
	void testAtivaPesquisa() {
		controle.encerraPesquisa("ECO1", "Fake news");
		controle.ativaPesquisa("ECO1");
		assertTrue(controle.pesquisaEhAtiva("ECO1"));
	}
	
	@Test
	void testAtivaPesquisaInvalido() {
		assertThrows(NullPointerException.class, () -> controle.ativaPesquisa(null), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.ativaPesquisa(""), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.ativaPesquisa("ECO2"), "Pesquisa ja ativada.");
	}

	@Test
	void testExibePesquisa() {
		assertEquals(controle.exibePesquisa("GEO1"), "GEO1 - Equador na encruzilhada regional - Geopolitica, America Latina, Intenacional");
	}
	
	@Test
	void testExibePesquisaInvalido() {
		assertThrows(NullPointerException.class, () -> controle.exibePesquisa(null), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.exibePesquisa("     "), "Codigo nao pode ser nulo ou vazio.");
	}

	@Test
	void testPesquisaEhAtiva() {
		controle.encerraPesquisa("GEO1", "Fake News");
		assertTrue(controle.pesquisaEhAtiva("ECO2"));
		assertFalse(controle.pesquisaEhAtiva("GEO1"));
	}
	
	@Test
	void testPesquisaEhAtivaInvalida() {
		assertThrows(NullPointerException.class, () -> controle.pesquisaEhAtiva(null), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaEhAtiva("            "), "Codigo nao pode ser nulo ou vazio.");
		assertThrows(RuntimeException.class, () -> controle.pesquisaEhAtiva("GEO2"), "Pesquisa nao encontrada.");
	}
	
	@Test
	public void testAssociaPesquisador() {
		assertTrue(controle.associaPesquisador("ECO1", new Pesquisador("Helen", null, "linda", "helen@linda.com", "https://aaaaaa")));
	}
	
	@Test
	public void testAssociaPesquisadorPesquisaInexistente() {
		assertThrows(RuntimeException.class, () -> controle.associaPesquisador("ECO3", new Pesquisador("Helen", null, "linda", "helen@linda.com", "https://aaaaaa")));		
	}
	
	@Test
	public void testAssociaPesquisadorPesquisaInativa() {
		controle.encerraPesquisa("ECO1", "pipipi popopo");
		assertThrows(RuntimeException.class, () -> controle.associaPesquisador("ECO1", new Pesquisador("Helen", null, "linda", "helen@linda.com", "https://aaaaaa")));		
	}

	@Test
	public void testDesassociaPesquisador() {
		Pesquisador pesquisador =  new Pesquisador("Helen", null, "linda", "helen@linda.com", "https://aaaaaa");
		controle.associaPesquisador("ECO1", pesquisador);
		assertTrue(controle.desassociaPesquisador("ECO1", pesquisador));
	}
	
	@Test
	public void testDesassociaPesquisadorPesquisaInexistente() {
		Pesquisador pesquisador =  new Pesquisador("Helen", null, "linda", "helen@linda.com", "https://aaaaaa");
		controle.associaPesquisador("ECO1", pesquisador);
		assertThrows(RuntimeException.class, () -> controle.desassociaPesquisador("ECO3", pesquisador));		
	}
	
	@Test
	public void testDesassociaPesquisadorPesquisaInativa() {
		Pesquisador pesquisador =  new Pesquisador("Helen", null, "linda", "helen@linda.com", "https://aaaaaa");
		controle.associaPesquisador("ECO1", pesquisador);
		controle.encerraPesquisa("ECO1", "pipipi popopo");
		assertThrows(RuntimeException.class, () -> controle.desassociaPesquisador("ECO1", pesquisador));		
	}

}