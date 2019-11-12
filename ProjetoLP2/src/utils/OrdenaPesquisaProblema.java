package utils;

import java.util.Comparator;

import pacote.Pesquisa;

/**
 * Ordenador de pesquisas, que ordena para o usuario pesquisas associadas a um problema,
 * e que a partir do ID desses problemas, ele ordena de forma decrescente as pesquisas desejadas.
 * 
 * @author Henrique Lemos
 */

public class OrdenaPesquisaProblema implements Comparator<Pesquisa>{

	/**
	 * Metodo responsavel por odernar as pesquisas, de forma decrescente,
	 * a partir do codigo de identificacao do problema associado a cada uma delas.
	 */
	
	@Override
	public int compare(Pesquisa p1, Pesquisa p2) {
		return p2.getIdProblema().compareTo(p1.getIdProblema());
	}
}
