package atividade;

import java.util.Comparator;

/**
 * Classe que tem como funcao ser usada na ordenacao de atividades de acordo com
 * sua duracao.
 * 
 * @author Helen Bento Cavalcanti.
 *
 */

public class OrdenaAtividadeDuracao implements Comparator<Atividade> {

	/**
	 * Metodo que recebe duas atividades a serem comparadas de acordo com sua
	 * duracao e retorna um inteiro que dira se um elemento e maior, igual ou menor
	 * que outro nesse quesito.
	 * 
	 * @param o1 primeira atividade
	 * @param o2 segunda atividade
	 * @return um inteiro que dira se um elemento e maior, igual ou menor que outro
	 *         no quesito duracao.
	 */
	@Override
	public int compare(Atividade o1, Atividade o2) {
		if (o1.getDuracao() - o2.getDuracao() == 0) {
			return o1.compareTo(o2);
		}
		return o1.getDuracao() - o2.getDuracao();
	}

}
