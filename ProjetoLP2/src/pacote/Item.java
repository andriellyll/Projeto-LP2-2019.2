package pacote;

import java.io.Serializable;

/**
 * Para que uma atividade apresente um resultado ela recebe algum(ns) item(ns)
 * para serem completados. Cada item apresenta um codigo e uma descricao. Quando
 * um item e criado, ele automaticamente apresenta a situacao de PENDENTE e
 * quando realizado, muda sua situacao para REALIZADO.
 * 
 * @author Henrique Lemos
 */
public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2848582857353973575L;

	/**
	 * Cada item apresenta um codigo que identifica este item.
	 */
	private int codigo;

	/**
	 * Uma descricao deste item.
	 */
	private String item;

	/**
	 * Sua situacao (PENDENTE ou REALIZADO).
	 */
	private String situacao;

	private int duracao;

	/**
	 * Construtor que cria um novo item a partir de uma descricao do item e seu
	 * codigo unico.
	 * 
	 * @param item   valor que descreve o item a ser realizado
	 * @param codigo valor que identifica o item na lista de itens.
	 */
	public Item(String item, int codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(item, "Item nao pode ser nulo ou vazio.");
		this.item = item;
		this.codigo = codigo;
		this.situacao = "PENDENTE";
	}

	/**
	 * Metodo responsavel por mostrar a situacao do item, sendo PENDENTE ou
	 * REALIZADO.
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

//------------------------------------- Novas atualizacoes de Item -------------------------------------------------------

	/**
	 * 
	 * @param duracao 
	 * @return
	 */
	public void executa(int duracao) {
		this.situacao = "REALIZADO";
		this.duracao = duracao;
	}
	
	public String exibeItemSituacao() {
		return this.situacao + " - ITEM" + this.codigo;
	}
	
	public String exibeItemDuracao() {
		return "ITEM" + this.codigo + " - " + this.duracao;
	}

	/**
	 * 
	 * 
	 * @param palavraChave
	 * @return
	 */
	public String procuraPalavraChave(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Palavra nao pode ser nula ou vazia");
		if (item.contains(palavraChave)) {
			return item;
		}
		return "";
	}
}