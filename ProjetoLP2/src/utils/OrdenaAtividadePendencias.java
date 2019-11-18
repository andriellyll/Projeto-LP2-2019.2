package utils;

import java.util.Comparator;

import pacote.Atividade;

/**
 * Classe que tem como funcao ser usada na ordenacao de atividades de acordo com
 * o numero de pendencias.
 * 
 * 
 * @author Helen Bento Cavalcanti.
 *
 */
public class OrdenaAtividadePendencias implements Comparator<Atividade> {
	/**
	 * Metodo que recebe duas atividades a serem comparadas de acordo o numero de
	 * pendencias e retorna um inteiro que dira se um elemento e maior, igual ou
	 * menor que outro nesse quesito.
	 * 
	 * @param Dois objetos do tipo Atividade.
	 * @return um inteiro que dira se um elemento e maior, igual ou menor que outro
	 *         no quesito pendencias.
	 */
	@Override
	public int compare(Atividade o1, Atividade o2) {
		if (o1.ItensPendentes() - o2.ItensPendentes() == 0) {
			return o1.compareTo(o2);
		}
		return o1.ItensPendentes() - o2.ItensPendentes();
	}

}
