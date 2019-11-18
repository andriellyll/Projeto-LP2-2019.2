package pacote;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pacote.ControllerPesquisa;

class ControllerPesquisaTest {

	private ControllerPesquisa controle;
	private ControllerPesquisador controllerPesquisador;
	private ControllerProblemaObjetivo controllerProblemaObjetivo;
	private ControllerAtividade controllerAtividade;

	@BeforeEach
	void setUp() throws Exception {
		controllerPesquisador = new ControllerPesquisador();
		controllerProblemaObjetivo = new ControllerProblemaObjetivo();
		controllerAtividade = new ControllerAtividade(controle);

		controle = new ControllerPesquisa(controllerPesquisador, controllerProblemaObjetivo);
		controle.cadastraPesquisa("Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto",
				"Economia, Bolsa de Valores");
		controle.cadastraPesquisa("Equador na encruzilhada regional", "Geopolitica, America Latina, Intenacional");
		controle.cadastraPesquisa("Oleo em Boipeba leva turistas a evitar mar e mudar programacao",
				"Ecologia, Desastre Ambiental, Meio Ambiente, Natureza");
		controllerProblemaObjetivo.cadastraProblema("um grande problema na minha vida", 2);
		controllerProblemaObjetivo.cadastraObjetivo("GERAL", "nao sei", 2, 1);
		controllerPesquisador.cadastraPesquisador("bia", "estudante", "linda pfta", "bia@pfta", "http://bia");
	}

	@Test
	void testCadastraPesquisa() {
		assertEquals(controle.cadastraPesquisa("Brasil assina oito acordos bilaterais com Emirados Arabes",
				"Geografia Politica, Bolsonaro em viagem ao Oriente"), "GEO2");
		assertEquals(controle.cadastraPesquisa(
				"Jesus e denunciado por atraso e tem presenca ameacada no Fla contra o River", "Libertadores"), "LIB1");
		assertEquals(controle.cadastraPesquisa("Entenda as regras de transicao da reforma da Previdencia",
				"Economia, Brasil, Reforma da Previdencia"), "ECO3");
		assertEquals(
				controle.cadastraPesquisa("Dona do Google amplia receita com publicidade, mas custos impactam lucro",
						"Tecnologia, Google, Alphabet, Financas"),
				"TEC1");
	}

	@Test
	void testCadastraPesquisaInvalido() {
		assertThrows(NullPointerException.class, () -> controle.cadastraPesquisa(null, "Computacao"),
				"Descricao nao pode ser nula ou vazia.");
		assertThrows(
				NullPointerException.class, () -> controle
						.cadastraPesquisa("Eddie Murphy volta a boa forma no papel de comediante boca suja", null),
				"Formato do campo de interesse invalido.");
		assertThrows(IllegalArgumentException.class, () -> controle.cadastraPesquisa("  ", "Computacao"),
				"Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class,
				() -> controle.cadastraPesquisa("Em audio, Queiroz xinga promotores e diz que investigacao", "       "),
				"Formato do campo de interesse invalido.");
		assertThrows(IllegalArgumentException.class,
				() -> controle.cadastraPesquisa("A seis dias do Enem, 1,2 milhão de inscritos não sabem local da prova",
						"TOP Noticias, Educacao, ENEM, ENEM 2019, Universidades"),
				"Formato do campo de interesse invalido.");
		assertThrows(IllegalArgumentException.class, () -> controle.cadastraPesquisa(
				"Auxilio-doenca e aposentadoria por invalidez mudam na nova Previdencia",
				"Reforma da Previdencia, Beneficios pagos quando trabalhador fica incapacitado para o trabalho terao valor menor, Os beneficios pagos a trabalhadores que ficarem incapacitados para o trabalhoserao calculados com base em todos os salarios de contribuicao desde julho de 1994"),
				"Formato do campo de interesse invalido.");
		assertThrows(IllegalArgumentException.class,
				() -> controle.cadastraPesquisa("Teste para dar erro hahahahahahahahha", "ab"),
				"Formato do campo de interesse invalido.");
	}

	@Test
	void testAlteraPesquisa() {
		controle.alteraPesquisa("ECO2", "CAMPO", "Ecossistemas, Desastre Ambiental");
		controle.alteraPesquisa("GEO1", "DESCRICAO",
				"Parlamentares pro-democracia interrompem discurso de lider de Hong Kong");

		assertEquals(controle.exibePesquisa("ECO2"),
				"ECO2 - Oleo em Boipeba leva turistas a evitar mar e mudar programacao - Ecossistemas, Desastre Ambiental");
		assertEquals(controle.exibePesquisa("GEO1"),
				"GEO1 - Parlamentares pro-democracia interrompem discurso de lider de Hong Kong - Geopolitica, America Latina, Intenacional");
	}

	@Test
	void testAlteraPesquisaInvalido() {

		assertThrows(NullPointerException.class, () -> controle.alteraPesquisa(null, "CAMPO", "Programacao 2, UFCG"),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(NullPointerException.class, () -> controle.alteraPesquisa("ECO2", null, "Bulhunfas"),
				"Conteudo a ser alterado nao pode ser nulo ou vazio.");
		assertThrows(NullPointerException.class, () -> controle.alteraPesquisa("GEO1", "DESCRICAO", null),
				"Descricao nao pode ser nula ou vazia.");
		assertThrows(NullPointerException.class, () -> controle.alteraPesquisa("GEO1", "CAMPO", null),
				"Formato do campo de interesse invalido.");

		assertThrows(IllegalArgumentException.class,
				() -> controle.alteraPesquisa("  ", "DESCRICAO", "Ba ba ba, babanana, Ba ba ba, Potetotaaaaa"),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class,
				() -> controle.alteraPesquisa("ECO1", "     ", "Como o Gaudencio e ecologico"),
				"Conteudo a ser alterado nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.alteraPesquisa("GEO1", "descricao", ""),
				"Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> controle.alteraPesquisa("ECO2", "campo", " "),
				"Formato do campo de interesse invalido.");

		assertThrows(IllegalArgumentException.class,
				() -> controle.alteraPesquisa("ECO1", "SEGUNDO CAMPO", "Vai dar erro seu burro!!!"),
				"Nao e possivel alterar esse valor de pesquisa.");
		controle.encerraPesquisa("GEO1", "Anderson disse que e falso");
		assertThrows(IllegalArgumentException.class,
				() -> controle.alteraPesquisa("GEO1", "Campo", "A vida e complicada porem tudo tem solucao"),
				"Pesquisa desativada.");

	}

	@Test
	void testEncerraPesquisa() {
		controle.encerraPesquisa("ECO1", "Fake news");
		assertFalse(controle.pesquisaEhAtiva("ECO1"));
	}

	@Test
	void testEncerraPesquisaInvalido() {
		controle.encerraPesquisa("ECO1", "Fake news");
		assertThrows(NullPointerException.class, () -> controle.encerraPesquisa(null, "Gaudencio disse que e falso"),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.encerraPesquisa(" ", "A vida e problematica"),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.encerraPesquisa("ECO1", "Noticia falsa"),
				"Pesquisa desativada.");
	}

	@Test
	void testAtivaPesquisa() {
		controle.encerraPesquisa("ECO1", "Fake news");
		controle.ativaPesquisa("ECO1");
		assertTrue(controle.pesquisaEhAtiva("ECO1"));
	}

	@Test
	void validaPesquisa() {
		controle.encerraPesquisa("GEO1", "NAO QUERO MAIS");
		assertThrows(RuntimeException.class, () -> controle.validaPesquisa("GEO1"));
	}

	@Test
	void testAtivaPesquisaInvalido() {
		assertThrows(NullPointerException.class, () -> controle.ativaPesquisa(null),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.ativaPesquisa(""),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.ativaPesquisa("ECO2"), "Pesquisa ja ativada.");
	}

	@Test
	void testExibePesquisa() {
		assertEquals(controle.exibePesquisa("GEO1"),
				"GEO1 - Equador na encruzilhada regional - Geopolitica, America Latina, Intenacional");
	}

	@Test
	void testExibePesquisaInvalido() {
		assertThrows(NullPointerException.class, () -> controle.exibePesquisa(null),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.exibePesquisa("     "),
				"Codigo nao pode ser nulo ou vazio.");
	}

	@Test
	void testPesquisaEhAtiva() {
		controle.encerraPesquisa("GEO1", "Fake News");
		assertTrue(controle.pesquisaEhAtiva("ECO2"));
		assertFalse(controle.pesquisaEhAtiva("GEO1"));
	}

	@Test
	void testPesquisaEhAtivaInvalida() {
		assertThrows(NullPointerException.class, () -> controle.pesquisaEhAtiva(null),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaEhAtiva("            "),
				"Codigo nao pode ser nulo ou vazio.");
		assertThrows(RuntimeException.class, () -> controle.pesquisaEhAtiva("GEO2"), "Pesquisa nao encontrada.");
	}

//------------------------------------ Novos testes ControllerPesquisa (Parte 2) ---------------------------------------------

	@Test
	public void testAssociaPesquisador() {
		assertTrue(controle.associaPesquisador("ECO1", "bia@pfta"));
	}

	@Test
	public void testAssociaPesquisadorJaAssociado() {
		assertTrue(controle.associaPesquisador("ECO1", "bia@pfta"));
		assertFalse(controle.associaPesquisador("ECO1", "bia@pfta"));
	}

	@Test
	public void testAssociaPesquisadorPesquisaInexistente() {
		assertThrows(RuntimeException.class, () -> controle.associaPesquisador("ECO3", "bia@pfta"));
	}

	@Test
	public void testAssociaPesquisadorPesquisaInativa() {
		controle.encerraPesquisa("ECO1", "pipipi popopo");
		assertThrows(RuntimeException.class, () -> controle.associaPesquisador("ECO1", "bia@pfta"));
	}

	@Test
	public void testAssociaPesquisadorInexistente() {
		assertThrows(RuntimeException.class, () -> controle.associaPesquisador("ECO1", "drica@aaa"));
	}

	@Test
	public void testAssociaPesquisadorNull() {
		assertThrows(NullPointerException.class, () -> controle.associaPesquisador(null, "bia@pfta"));
		assertThrows(NullPointerException.class, () -> controle.associaPesquisador("ECO1", null));
	}

	@Test
	public void testAssociaPesquisadorVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.associaPesquisador("", "bia@pfta"));
		assertThrows(IllegalArgumentException.class, () -> controle.associaPesquisador("ECO1", ""));
	}

	@Test
	public void testDesassociaPesquisador() {
		controle.associaPesquisador("ECO1", "bia@pfta");
		assertTrue(controle.desassociaPesquisador("ECO1", "bia@pfta"));
	}

	@Test
	public void testDesassociaPesquisadorJaDesassociado() {
		assertTrue(controle.associaPesquisador("ECO1", "bia@pfta"));
		assertTrue(controle.desassociaPesquisador("ECO1", "bia@pfta"));
		assertFalse(controle.desassociaPesquisador("ECO1", "bia@pfta"));
	}

	@Test
	public void testDesassociaPesquisadorNull() {
		assertThrows(NullPointerException.class, () -> controle.desassociaPesquisador(null, "bia@pfta"));
		assertThrows(NullPointerException.class, () -> controle.desassociaPesquisador("ECO1", null));
	}

	@Test
	public void testDesassociaPesquisadorVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.desassociaPesquisador("", "bia@pfta"));
		assertThrows(IllegalArgumentException.class, () -> controle.desassociaPesquisador("ECO1", ""));
	}

	@Test
	public void testDesassociaPesquisadorPesquisaInexistente() {
		controle.associaPesquisador("ECO1", "bia@pfta");
		assertThrows(RuntimeException.class, () -> controle.desassociaPesquisador("ECO3", "bia@pfta"));
	}

	@Test
	public void testDesassociaPesquisadorPesquisaInativa() {
		controle.associaPesquisador("ECO1", "bia@pfta");
		controle.encerraPesquisa("ECO1", "pipipi popopo");
		assertThrows(RuntimeException.class, () -> controle.desassociaPesquisador("ECO1", "bia@pfta"));
	}

	@Test
	public void testDesassociaPesquisadorInexistente() {
		assertThrows(RuntimeException.class, () -> controle.desassociaPesquisador("ECO1", "drica@aaa"));
	}

	@Test
	public void testImprimePesquisasOrdemProblema() {
		assertEquals(
				"GEO1 - Equador na encruzilhada regional - Geopolitica, America Latina, Intenacional | ECO2 - Oleo em Boipeba leva turistas a evitar mar e mudar programacao - Ecologia, Desastre Ambiental, Meio Ambiente, Natureza | ECO1 - Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto - Economia, Bolsa de Valores",
				controle.imprimePesquisas("PROBLEMA"));
	}

	@Test
	public void testImprimePesquisasOrdemObjetivos() {
		assertEquals(
				"GEO1 - Equador na encruzilhada regional - Geopolitica, America Latina, Intenacional | ECO2 - Oleo em Boipeba leva turistas a evitar mar e mudar programacao - Ecologia, Desastre Ambiental, Meio Ambiente, Natureza | ECO1 - Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto - Economia, Bolsa de Valores",
				controle.imprimePesquisas("OBJETIVOS"));
	}

	@Test
	public void testImprimePesquisasOrdemPesquisa() {
		assertEquals(
				"GEO1 - Equador na encruzilhada regional - Geopolitica, America Latina, Intenacional | ECO2 - Oleo em Boipeba leva turistas a evitar mar e mudar programacao - Ecologia, Desastre Ambiental, Meio Ambiente, Natureza | ECO1 - Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto - Economia, Bolsa de Valores",
				controle.imprimePesquisas("PESQUISA"));
	}

	@Test
	public void ImprimePesquisasOrdem() {
		controle.cadastraPesquisa("lalalalala", "nao sei");
		controle.associaProblema("NAO1", "P1");
		controle.associaObjetivo("NAO1", "O1");
		assertEquals(
				"NAO1 - lalalalala - nao sei | GEO1 - Equador na encruzilhada regional - Geopolitica, America Latina, Intenacional | ECO2 - Oleo em Boipeba leva turistas a evitar mar e mudar programacao - Ecologia, Desastre Ambiental, Meio Ambiente, Natureza | ECO1 - Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto - Economia, Bolsa de Valores",
				controle.imprimePesquisas("PROBLEMA"));
	}

	@Test
	public void ImprimePesquisasOrdemProblema() {
		controle.cadastraPesquisa("lalalalala", "nao sei");
		controle.associaProblema("ECO1", "P1");
		controle.associaObjetivo("NAO1", "O1");
		assertEquals(
				"NAO1 - lalalalala - nao sei | GEO1 - Equador na encruzilhada regional - Geopolitica, America Latina, Intenacional | ECO2 - Oleo em Boipeba leva turistas a evitar mar e mudar programacao - Ecologia, Desastre Ambiental, Meio Ambiente, Natureza | ECO1 - Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto - Economia, Bolsa de Valores",
				controle.imprimePesquisas("OBJETIVOS"));
	}

	@Test
	public void testImprimePesquisasOrdemInvalida() {
		assertThrows(IllegalArgumentException.class, () -> controle.imprimePesquisas("Qualquer"));
	}

	@Test
	public void associaProblema() {
		assertTrue(controle.associaProblema("GEO1", "P1"));
	}

	@Test
	public void associaProblemaIdPesquisaNulo() {
		assertThrows(NullPointerException.class, () -> controle.associaProblema(null, "P1"));
	}

	@Test
	public void associaProblemaIdPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.associaProblema("", "P1"));
	}

	@Test
	public void associaProblemaNulo() {
		assertThrows(NullPointerException.class,
				() -> controle.associaProblema("um grande problema na minha vida", null));
	}

	@Test
	public void associaProblemaVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> controle.associaProblema("um grande problema na minha vida", ""));
	}

	@Test
	public void associaProblemaJaAssociado() {
		controle.associaProblema("GEO1", "P1");
		assertFalse(controle.associaProblema("GEO1", "P1"));
	}

	@Test
	public void associaProblemaComOutroProblemaJaAssociado() {
		controle.associaProblema("GEO1", "P1");
		controllerProblemaObjetivo.cadastraProblema("lalalalalala", 3);
		assertThrows(RuntimeException.class, () -> controle.associaProblema("GEO1", "P2"));

	}

	@Test
	public void desassociaProblema() {
		controle.associaProblema("GEO1", "P1");
		assertTrue(controle.desassociaProblema("GEO1"));
	}

	@Test
	public void desassociaProblemaIdPesquisaNulo() {
		controle.associaProblema("GEO1", "P1");
		assertThrows(NullPointerException.class, () -> controle.desassociaProblema(null));

	}

	@Test
	public void desassociaProblemaIdPesquisaVazio() {
		controle.associaProblema("GEO1", "P1");
		assertThrows(IllegalArgumentException.class, () -> controle.desassociaProblema(""));
	}

	@Test
	public void associaObjetivo() {
		controle.associaObjetivo("GEO1", "O1");
	}

	@Test
	public void associaObjetivoIdPesquisaNulo() {
		assertThrows(NullPointerException.class, () -> controle.associaObjetivo(null, "O1"));
	}

	@Test
	public void associaObjetivoIdPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.associaObjetivo("", "O1"));
	}

	@Test
	public void associaObjetivoNulo() {
		assertThrows(NullPointerException.class,
				() -> controle.associaObjetivo("um grande problema na minha vida", null));
	}

	@Test
	public void associaObjetivoVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> controle.associaObjetivo("um grande problema na minha vida", ""));
	}

	@Test
	public void desassociaObjetivo() {
		controle.associaObjetivo("GEO1", "O1");
		assertTrue(controle.desassociaObjetivo("GEO1", "O1"));
	}

	@Test
	public void desassociaObjetivoIdPesquisaNulo() {
		controle.associaObjetivo("GEO1", "O1");
		assertThrows(NullPointerException.class, () -> controle.desassociaObjetivo(null, "O1"));

	}

	@Test
	public void desassociaObjetivoIdPesquisaVazio() {
		controle.associaObjetivo("GEO1", "O1");
		assertThrows(IllegalArgumentException.class, () -> controle.desassociaObjetivo("", "O1"));
	}

	@Test
	public void desassociaObjetivoNulo() {
		controle.associaObjetivo("GEO1", "O1");
		assertThrows(NullPointerException.class, () -> controle.desassociaObjetivo("GEO1", null));

	}

	@Test
	public void desassociaObjetivoVazio() {
		controle.associaObjetivo("GEO1", "O1");
		assertThrows(IllegalArgumentException.class, () -> controle.desassociaObjetivo("GEO1", ""));
	}

	@Test
	void procuraPalavraChave() {
		controle.cadastraPesquisa(
				"Chefes da Seguranca da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		controle.cadastraPesquisa("Seguranca", "Ufcg");
		ArrayList<String> ex = new ArrayList<>();
		ex.add("UFC1: Seguranca");
		ex.add("SEG1: Chefes da Seguranca da mafia italiana aguardam extradicao no mesmo presidio que Marcola");
		ex.add("SEG1: Seguranca publica");
		assertEquals(controle.procuraPalavraChave("Seguranca"), ex);
	}

	@Test
	void testprocuraPalavraChaveNull() {
		controle.cadastraPesquisa(
				"Chefes da Seguranca da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		controle.cadastraPesquisa("Seguranca", "Ufcg");
		ArrayList<String> ex = new ArrayList<>();
		ex.add("UFC1: Seguranca");
		ex.add("SEG1: Chefes da Seguranca da mafia italiana aguardam extradicao no mesmo presidio que Marcola");
		ex.add("SEG1: Seguranca publica");
		assertThrows(NullPointerException.class, () -> {
			controle.procuraPalavraChave(null);
		});
	}

	@Test
	void testprocuraPalavraChaveVazio() {
		controle.cadastraPesquisa(
				"Chefes da Seguranca da mafia italiana aguardam extradicao no mesmo presidio que Marcola",
				"Seguranca publica");
		controle.cadastraPesquisa("Seguranca", "Ufcg");
		ArrayList<String> ex = new ArrayList<>();
		ex.add("UFC1: Seguranca");
		ex.add("SEG1: Chefes da Seguranca da mafia italiana aguardam extradicao no mesmo presidio que Marcola");
		ex.add("SEG1: Seguranca publica");
		assertThrows(IllegalArgumentException.class, () -> {
			controle.procuraPalavraChave("");
		});
	}

//------------------------------------ Novos testes ControllerPesquisa (Parte 3) ---------------------------------------------

	@Test
	private static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}
	
	
	@Test
	void testGravarResumo() throws IOException {
		controle.associaPesquisador("ECO1", "bia@pfta");
		controle.associaProblema("ECO1", "P1");
		controle.associaObjetivo("ECO1", "O1");
		controllerAtividade.associaPesquisa("ECO1", controllerAtividade.cadastraAtividade("Mais um objeto de atividade para testar", "ALTO", "Tem muita importancia deste teste"));
		controllerAtividade.cadastraItem("A1", "Tem que fazer senao Anderson briga tambem.");
		controle.gravarResumo("ECO1");
//		assertEquals(readFileAsString(/**Caminho do arquivo**/), "- Pesquisa: ECO1 - Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto - Economia, Bolsa de Valores\n" + "\n" + 
//				"\t- Pesquisadores:\n" + "\n" + 
//				"\t\t- bia (estudante) - linda pfta - bia@pfta - FOTO - http://bia\n" + "\n" + 
//				"\t- Problema:\n" + "\n" + 
//				"\t\t- P1 - um grande problema na minha vida - 2\n" + "\n" + 
//				"\t- Objetivos:\n" + "\n" + 
//				"\t\t- O1 - GERAL - nao sei - 3\n" + "\n" + 
//				"\t- Atividades:\n" + "\n" + 
//				"\t\t- Mais um objeto de atividade para testar (ALTO - Tem muita importancia deste teste)\n" + "\n" + 
//				"\t\t\t- PENDENTE - Tem que fazer senao Anderson briga tambem.");
	}

	@Test
	void testGravarResumoInvalido() {
		assertThrows(NullPointerException.class, () -> controle.gravarResumo(null),
				"Pesquisa nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> controle.gravarResumo(""),
				"Pesquisa nao pode ser nula ou vazia.");
		assertThrows(RuntimeException.class, () -> controle.gravarResumo("BQW1"), "Pesquisa nao encontrada.");
	}

	@Test
	void testGravarResultados() throws IOException {
		controllerAtividade.cadastraItem("A1", "Tem que fazer senao Anderson briga tambem.");
		controllerAtividade.executaAtividade("ECO1", 1, 100);
		controllerAtividade.cadastraResultado("ECO1", "Realizado com sucesso");
		controle.gravarResultados("ECO1");
//		assertEquals(readFileAsString(), "- Pesquisa: ECO1 - Dolar fecha abaixo de R$ 4 pela primeira vez desde agosto - Economia, Bolsa de Valores\n" + "\n" +
//	    "\t- Resultados:\n" + "\n" + 
//	    "\t\t- DESCRIÇÃO\n" + "\n" + 
//	    "\t\t\t- ITEM1 - 100 - DESCRIÇÃO_RESULTADO");

	}
	
	@Test
	void testGravarResultadosInvalido() {
		assertThrows(NullPointerException.class, () -> controle.gravarResultados(null),
				"Pesquisa nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> controle.gravarResultados(""),
				"Pesquisa nao pode ser nula ou vazia.");
		assertThrows(RuntimeException.class, () -> controle.gravarResumo("BMW10"), "Pesquisa nao encontrada.");
	}

	@Test
	void configuraEstrategiaVaziaTest() {
		assertThrows(IllegalArgumentException.class, () -> controle.configuraEstrategia(""));

	}

	@Test
	void configuraEstrategiaNulaTest() {
		assertThrows(NullPointerException.class, () -> controle.configuraEstrategia(null));

	}

	@Test
	void configuraEstrategiaInvalidaTest() {
		assertThrows(IllegalArgumentException.class, () -> controle.configuraEstrategia("invalida"));
	}

	@Test
	void configuraEstrategiaMaisAntiga() {
		controle.configuraEstrategia("MAIS_ANTIGA");
	}

	@Test
	void configuraEstrategiaMenosPendencias() {
		controle.configuraEstrategia("MENOS_PENDENCIAS");
	}

	@Test
	void configuraEstrategiaMaiorRisco() {
		controle.configuraEstrategia("MAIOR_RISCO");
	}

	@Test
	void configuraEstrategiaMaiorDuracao() {
		controle.configuraEstrategia("MAIOR_DURACAO");
	}

	@Test
	void proximaAtividadeTest() {
		assertThrows(RuntimeException.class, () -> controle.proximaAtividade("ECO1"));
	}

}