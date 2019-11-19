package pacote;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pacote.Pesquisa;

class PesquisaTest {

	private Pesquisa test1;
	private Pesquisa test2;
	private ControllerAtividade controllerAtividade;

	@BeforeEach
	void setUp() throws Exception {
		test1 = new Pesquisa("SEG1", "Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		test2 = new Pesquisa("BLA1", "Nao espere a Black Friday chegar. Sua pesquisa de precos deve começar ja",
				"Black Friday, Casos do mes");
		ControllerPesquisa controllerPesquisa = null;
		controllerAtividade = new ControllerAtividade(controllerPesquisa);
	}

	@Test
	void testPesquisa() {
		new Pesquisa("BLA2", "Cuidado com os golpes nesta black frida, saiba com prevenir-se",
				"Black Friday, Golpes, Seguranca na internet");
		new Pesquisa("SMA1", "Vale a pena investir em um smartphone top de linha de dois anos atrás?",
				"Smartphone, Tecnologia, Android, IOS");
	}

	@Test
	void testPesquisaInvalido() {
		assertThrows(NullPointerException.class,
				() -> new Pesquisa(null, "Cuidado com os golpes nesta black frida, saiba com prevenir-se",
						"Black Friday, Golpes, Seguranca na internet"),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(NullPointerException.class, () -> new Pesquisa("BLA2", null, "Black Friday, Casos do mes"),
				"Descricao nao pode ser nula ou vazia.");
		assertThrows(NullPointerException.class,
				() -> new Pesquisa("BLA2", "Cuidado com os golpes nesta black frida, saiba com prevenir-se", null),
				"Formato do campo de interesse invalido.");

		assertThrows(IllegalArgumentException.class,
				() -> new Pesquisa(" ", "Vale a pena investir em um smartphone top de linha de dois anos atrás?",
						"Smartphone, Tecnologia, Android, IOS"),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class,
				() -> new Pesquisa("SMA1", "        ", "Smartphone, Tecnologia, Android, IOS"),
				"Descricao nao pode ser nula ou vazia.");
		assertThrows(
				IllegalArgumentException.class, () -> new Pesquisa("SMA1",
						"Vale a pena investir em um smartphone top de linha de dois anos atrás?", ""),
				"Formato do campo de interesse invalido.");
	}

	@Test
	void testSetDescricao() {
		test2.setDescricao("Sua pesquisa de precos deve comecar ja");
		assertEquals(test2.toString(), "BLA1 - Sua pesquisa de precos deve comecar ja - Black Friday, Casos do mes");
	}

	@Test
	void testSetDescricaoInvalido() {
		assertThrows(NullPointerException.class, () -> test2.setDescricao(null),
				"Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> test2.setDescricao("  "),
				"Descricao nao pode ser nula ou vazia.");
	}

	@Test
	void testSetCampo() {
		test1.setCampo("Seguranca publica, Internacional");
		assertEquals(test1.toString(),
				"SEG1 - Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola - Seguranca publica, Internacional");
	}

	@Test
	void testSetCampoInvalido() {
		assertThrows(NullPointerException.class, () -> test1.setDescricao(null),
				"Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> test1.setDescricao("  "),
				"Descricao nao pode ser nula ou vazia.");
	}

	@Test
	void testGetAtivacao() {
		test2.desativaPesquisa();
		assertTrue(test1.getAtivacao()); 
		assertFalse(test2.getAtivacao());
	}

	@Test
	void testDesativaPesquisa() {
		test1.desativaPesquisa();
		assertFalse(test1.getAtivacao());
	}

	@Test
	void testAtivaPesquisa() {
		test2.ativaPesquisa();
		assertTrue(test2.getAtivacao());
	}

	@Test
	void testHashCode() {
		assertEquals(new Pesquisa("SEG1", "Sem vigias, segurança noturna de escolas é feita pela GCM",
				"Seguranca Publica, Sao Paulo").hashCode(), test1.hashCode());
		assertNotEquals(test1.hashCode(), test2.hashCode());
	}

	@Test
	void testEqualsObject() {
		assertEquals(
				new Pesquisa("MER1", "Imposto unico elevara mensalidade escolar e plano de saude, dizem empresas",
						"Mercado, Reforma tributaria"),
				new Pesquisa("MER1",
						"Governo vai enviar no dia 9 primeira proposta de reforma tributaria, diz secretario da Previdencia",
						"Mercado, Rforma tributaria"));
		assertNotEquals(
				new Pesquisa("MER1", "Imposto unico elevara mensalidade escolar e plano de saude, dizem empresas",
						"Mercado, Reforma tributaria"),
				new Pesquisa("MER2",
						"Governo vai enviar no dia 9 primeira proposta de reforma tributaria, diz secretario da Previdencia",
						"Mercado, Rforma tributaria"));
	}

	@Test
	void testToString() {
		assertEquals(test1.toString(),
				"SEG1 - Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola - Seguranca publica");
		assertEquals(test2.toString(),
				"BLA1 - Nao espere a Black Friday chegar. Sua pesquisa de precos deve começar ja - Black Friday, Casos do mes");
	}

	// US5

	@Test
	void associaProblema() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		assertTrue(test1.associaProblema(problema));
	}

	@Test
	void associaProblemaJaExistenteNaPesquisa() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		test1.associaProblema(problema);
		assertFalse(test1.associaProblema(problema));
	}

	@Test
	void associaProblemaNaPesquisaComOutroProblemaAssociado() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		test1.associaProblema(problema);
		Problema problema2 = new Problema("Dificuldade em geral", 1, "P1");
		assertThrows(RuntimeException.class, () -> {
			test1.associaProblema(problema2);
		});
	}

	@Test
	void desassociaProblema() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		test1.associaProblema(problema);
		assertTrue(test1.desassociaProblema());
	}

	@Test
	void desassociaProblemaSemProblemaAssociado() {
		assertFalse(test1.desassociaProblema());
	}

	@Test
	void associaObjetivo() {
		Objetivo objetivo = new Objetivo("GERAL",
				"Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.",
				4, 2, "O1");
		assertTrue(test1.associaObjetivo(objetivo));
	}

	@Test
	void associaObjetivoComObjetivoJaExistente() {
		Objetivo objetivo = new Objetivo("GERAL",
				"Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.",
				4, 2, "O1");
		test1.associaObjetivo(objetivo);
		assertFalse(test1.associaObjetivo(objetivo));
	}

	@Test
	void desassociaObjetivo() {
		Objetivo objetivo = new Objetivo("GERAL",
				"Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.",
				4, 2, "O1");
		test1.associaObjetivo(objetivo);
		assertTrue(test1.desassociaObjetivo(objetivo));
	}

	@Test
	void desassociaObjetivoSemOObjetivoAssociado() {
		Objetivo objetivo = new Objetivo("GERAL",
				"Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.",
				4, 2, "O1");
		test1.desassociaObjetivo(objetivo);
	}

	@Test
	void getCodigoPesquisa() {
		assertEquals("SEG1", test1.getCodigo());
	}

	@Test
	void getIdProblema() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		test1.associaProblema(problema);
		assertEquals("P1", test1.getIdProblema());
	}

	@Test
	void verificaPesquisaTemObjetivos() {
		Objetivo objetivo = new Objetivo("GERAL",
				"Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.",
				4, 2, "O1");
		test1.associaObjetivo(objetivo);
		assertTrue(test1.temObjetivos());

	}

	@Test
	void verificaQuantidadeDeObjetivos() {
		Objetivo objetivo = new Objetivo("GERAL",
				"Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.",
				4, 2, "O1");
		Objetivo objetivo2 = new Objetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas.", 4, 2, "O2");
		test1.associaObjetivo(objetivo);
		test1.associaObjetivo(objetivo2);
		assertEquals(2, test1.getNumObjetivos());

	}

	@Test
	void verificaPesquisaTemObjetivoSemObjetivos() {
		assertFalse(test1.temObjetivos());
	}

	@Test
	void verificaPesquisaTemProblema() {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		test1.associaProblema(problema);
		assertTrue(test1.temProblema());
	}

	@Test
	void verificaPesquisaTemProblemaSemProblemaAssociado() {
		assertFalse(test1.temProblema());
	}

	@Test
	void procuraPalavraChaveDescricao() {
		ArrayList<String> ex = new ArrayList<>();
		ex.add("SEG1: Chefes da mafia italiana aguardam extradicao no mesmo presidio que Marcola");
		assertEquals(test1.procuraPalavraChave("Chefes"), ex);
	}

	@Test
	void procuraPalavraChaveDescricaoListaVazia() {
		ArrayList<String> ex = new ArrayList<>();
		assertEquals(test1.procuraPalavraChave("Chefa"), ex);

	}

	@Test
	void procuraPalavraChaveCampoDeInteresse() {
		ArrayList<String> ex = new ArrayList<>();
		ex.add("SEG1: Seguranca publica");
		assertEquals(test1.procuraPalavraChave("Seguranca"), ex);
	}

	@Test
	void procuraPalavraChaveCampoDeInteresseListaVazia() {
		ArrayList<String> ex = new ArrayList<>();
		assertEquals(test1.procuraPalavraChave("Seguro"), ex);
	}

	@Test
	void procuraPalavraChaveDescricaoECampoDeInteresse() {
		Pesquisa test3 = new Pesquisa("SEG2",
				"Chefes da Seguranca da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		ArrayList<String> ex = new ArrayList<>();
		ex.add("SEG2: Chefes da Seguranca da mafia italiana aguardam extradicao no mesmo presidio que Marcola");
		assertEquals(test3.procuraPalavraChave("Chefes"), ex);
	}

	@Test
	void testprocuraPalavraChaveNull() {
		assertThrows(NullPointerException.class, () -> {
			test1.procuraPalavraChave(null);
		});
	}

	@Test
	void testprocuraPalavraChaveVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			test1.procuraPalavraChave("");
		});
	}

	@Test
	void testCompareTo() {
		assertEquals(test1.compareTo(test2), -17);
	}

	@Test
	void testAssociaPesquisador() {
		Pesquisador p = new Pesquisador("helen", "estudante", "linda", "helen@linda", "http://helen");
		assertTrue(test1.associaPesquisador(p));
	}

	@Test
	void testAssociaPesquisadorJaAssociado() {
		Pesquisador p = new Pesquisador("helen", "estudante", "linda", "helen@linda", "http://helen");
		assertTrue(test1.associaPesquisador(p));
		assertFalse(test1.associaPesquisador(p));
	}

	@Test
	void testDesassociaPesquisador() {
		Pesquisador p = new Pesquisador("helen", "estudante", "linda", "helen@linda", "http://helen");
		assertTrue(test1.associaPesquisador(p));
		assertTrue(test1.desassociaPesquisador(p));
	}

	@Test
	void testDesassociaPesquisadorJaAssociado() {
		Pesquisador p = new Pesquisador("helen", "estudante", "linda", "helen@linda", "http://helen");
		assertTrue(test1.associaPesquisador(p));
		assertTrue(test1.desassociaPesquisador(p));
		assertFalse(test1.desassociaPesquisador(p));
	}

	@Test
	void proximaAtividadeMaisAntiga() {
		Atividade a1 = new Atividade("A1", "uma descricao", "ALTO", "risco");
		Atividade a2 = new Atividade("A2", "outra descricao", "MEDIO", "risco");
		Atividade a3 = new Atividade("A3", "outra da outra descricao", "BAIXO", "risco");
		a1.adicionaItem("descricao");
		a1.adicionaItem("lala");
		a2.adicionaItem("num");
		test1.associaAtividade(a1);
		test1.associaAtividade(a2);
		test1.associaAtividade(a3);
		assertEquals(test1.proximaAtividade("MAIS_ANTIGA"), "A1");
	}

	@Test
	void proximaAtividadeMenosPendencias() {
		Atividade a1 = new Atividade("A1", "uma descricao", "ALTO", "risco");
		Atividade a2 = new Atividade("A2", "outra descricao", "MEDIO", "risco");
		a1.adicionaItem("descricao");
		a1.adicionaItem("lala");
		a2.adicionaItem("num");
		test1.associaAtividade(a1);
		test1.associaAtividade(a2);
		assertEquals(test1.proximaAtividade("MENOS_PENDENCIAS"), "A2");
		

	}
	
	@Test
	void proximaAtividadeMaiorRisco() {
		Atividade a1 = new Atividade("A1", "uma descricao", "ALTO", "risco");
		Atividade a2 = new Atividade("A2", "outra descricao", "MEDIO", "risco");
		a1.adicionaItem("descricao");
		a1.adicionaItem("lala");
		a2.adicionaItem("num");
		test1.associaAtividade(a1);
		test1.associaAtividade(a2);
		assertEquals(test1.proximaAtividade("MAIOR_RISCO"), "A1");
		

	}
	
	@Test
	void proximaAtividadeMaiorDuracao() {
		Atividade a1 = new Atividade("A1", "uma descricao", "ALTO", "risco");
		Atividade a2 = new Atividade("A2", "outra descricao", "MEDIO", "risco");
		a1.adicionaItem("descricao");
		a1.adicionaItem("lala");
		a2.adicionaItem("num");
		test1.associaAtividade(a1);
		test1.associaAtividade(a2);
		a1.associaPesquisa(test1);
		a2.associaPesquisa(test1);
		a2.executaAtividade(1, 10);
		a1.executaAtividade(1, 2);
		assertEquals(test1.proximaAtividade("MAIOR_DURACAO"), "A2");
		

	}
	
	@Test
	private static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}
	
	@Test
	void testGravarResumo() throws Exception {
		Problema problema = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P1");
		Objetivo objetivo = new Objetivo("GERAL",
				"Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.",
				4, 2, "O1");
		controllerAtividade.cadastraAtividade("uma descricao", "ALTO", "risco");
		controllerAtividade.cadastraItem("A1", "Tem que fazer senao Anderson briga tambem.");
		test2.associaProblema(problema);
		test2.associaObjetivo(objetivo);
		test2.associaAtividade(controllerAtividade.getAtividade("A1"));
//		controllerAtividade.associaPesquisa("BLA1", "A1");
		controllerAtividade.executaAtividade("A1", 1, 100);
		test1.gravarResumo();
		assertEquals(readFileAsString("./_BLA1.txt"), "\"- Pesquisa: BLA1 - Nao espere a Black Friday chegar. Sua pesquisa de precos deve começar ja - Black Friday, Casos do mes\n" +
		"\t- Pesquisadores:\n" +
		"\t\t- Anderson (Monitor) - O melhor monitor de P2 - Anderson@theBest.com - https://qGtDY.popt\n" +
		"\t- Problema:\n" +
		"\t\t- P1 - A dificuldade da predicao do sistema eleitoral brasileiro - 1\n" +
		"\t- Objetivos:\n" +
		"\t\t- O1 - GERAL - Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao. - (6)\n" +
		"\t- Atividades:\n" +
		"\t\t- uma descricao (ALTO - risco)\n" +
		"\t\t\t- REALIZADO - ITEM1\" ");
	}
	
	@Test
	void testResultadosPesquisa() {
		
	}
	
	@Test
	void testGravarResultados() throws IOException {
		
	}
}