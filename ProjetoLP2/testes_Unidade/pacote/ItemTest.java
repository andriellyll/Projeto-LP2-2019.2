package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.Item;

/**
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 *
 */
class ItemTest {

	private Item item;

	@BeforeEach
	public void criaItem() {
		item = new Item("Monitoramento slack", 1);
	}

	@Test
	public void testItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Item("", 1));
	}

	@Test
	public void testItemNulo() {
		assertThrows(NullPointerException.class, () -> new Item(null, 1));
	}

	@Test
	public void testGetSituacao() {
		assertEquals(item.getSituacao(), "PENDENTE");
	}

	@Test
	public void testGetCodigo() {
		assertEquals(item.getCodigo(), 1);
	}

	@Test
	public void testToString() {
		assertEquals(item.toString(), "PENDENTE - Monitoramento slack");
	}

	@Test
	public void testBusca() {
		assertEquals("Monitoramento slack", item.procuraPalavraChave("slack"));
		assertEquals("", item.procuraPalavraChave("Aumentar"));
	}

	@Test
	public void testBuscaNull() {
		assertThrows(NullPointerException.class, () -> {
			item.procuraPalavraChave(null);
		});
	}

	@Test
	public void testBuscaVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			item.procuraPalavraChave("");
		});
	}

}
