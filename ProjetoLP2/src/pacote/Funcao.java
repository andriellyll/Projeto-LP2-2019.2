package pacote;

/**
 * Interface que deve coergir as classes que a implementarem a possuir os
 * metodos toString e setEspecialidade.
 * 
 * @author Helen Bento CAvalcanti
 *
 */
public interface Funcao {

	public String toString(String nome, String funcao, String biografia, String email, String foto);

	public void setEspecialidade(String atributo, String novoAtributo);

}
