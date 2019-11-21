package system;

import java.util.List;

/**
 * Interface criada para abstração de um comportamento de busca de palavra-chave, que é comum a todos os controllers.
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 */
public interface Buscavel {

	/**
	 * Método que faz a busca de palavra-chave e retorna um List de String, os resultados. 
	 * @param palavraChave palavra a ser buscada 
	 * @return ArrayList de String
	 */
	public List<String> procuraPalavraChave(String palavraChave);

}
