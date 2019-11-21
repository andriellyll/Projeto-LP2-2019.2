package pesquisa;

import java.util.Comparator;

/**
 * Ordenador de pesquisas, que ordena para o usuario pesquisas associadas a um objetivo,
 * e que a partir do numero de objetivos desses problemas, ele ordena de forma decrescente as pesquisas desejadas.
 * 
 * @author Henrique Lemos
 */

public class OrdenaPesquisasObjetivadas implements Comparator<Pesquisa> {

	/**
	 * Metodo responsavel por ordenar as pesquisas, de forma decrescente,
	 * a partir do numeros de objetivos que cada pesquisa possui, caso elas
	 * possuam a mesma quantidade de objetivos, ele deve realizar a comparacao
	 * a partir do ID das pesquisas.
	 */
	
	@Override
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (p2.getNumObjetivos() - p1.getNumObjetivos() == 0) {
			return p2.compareTo(p1);
		}
		return p2.getNumObjetivos() - p1.getNumObjetivos();
	}
}
