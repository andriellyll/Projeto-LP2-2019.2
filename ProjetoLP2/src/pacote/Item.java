package pacote;

/**
 * Para que uma atividade apresente um resultado ela recebe algum(ns) item(ns) para serem completados.
 * Cada item apresenta um codigo e uma descricao.
 * Quando um item e criado, ele automaticamente apresenta a situacao de PENDENTE e quando realizado,
 * muda sua situacao para REALIZADO.
 * 
 * @author Henrique Lemos
 */

public class Item {

/**
 * Cada item apresenta um codigo que identifica este item, uma descricao deste item e sua situacao (PENDENTE ou REALIZADO).
 */

	private int codigo;
	private String item;
	private String situacao;

/**
 * Construtor que cria um novo item a partir de uma descricao do item e seu codigo unico.
 * 
 * @param item valor que descreve o item a ser realizado
 * @param codigo valor que identifica o item na lista de itens.
 */

	public Item(String item, int codigo) {
		this.item = item;
		this.codigo = codigo;
		this.situacao = "PENDENTE";
	}

/**
 * Metodo responsavel por mostrar a situacao do item, sendo PENDENTE ou REALIZADO.
 * 
 * @return Uma String mostrando a situacao do item
 */

	public String getSituacao() {
		return situacao;
	}

/**
 * Método responsavel por mostrar o codigo da posicao do item na lista de itens.
 * 
 * @return Um inteiro mostrando o codigo do item
 */

	public int getCodigo() {
		return codigo;
	}

/**
 * Metodo responsavel por retornar a String que representa o item.
 * 
 * @return A representacao que segue no formato "SITUACAO - ITEM"
 */

	@Override
	public String toString() {
		return situacao + " - " + item;
	}
}