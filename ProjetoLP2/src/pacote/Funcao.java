package pacote;

/**
 * Interface que deve coergir as classes que a implementarem a possuir os
 * metodos toString e setEspecialidade.
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 *
 */
public interface Funcao {

	/**
	 * 
	 * 
	 * @param nome
	 * @param funcao
	 * @param biografia
	 * @param email
	 * @param foto
	 * @return
	 */
	public String toString(String nome, String funcao, String biografia, String email, String foto);

	/**
	 * 
	 * 
	 * @param atributo
	 * @param novoAtributo
	 */
	public void setEspecialidade(String atributo, String novoAtributo);

}
