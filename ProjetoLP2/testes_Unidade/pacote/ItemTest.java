package pacote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		assertThrows(IllegalArgumentException.class, () -> new Item(null, 1));
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

}
