package atividade;

import java.util.Comparator;
/**
 * Classe que tem como funcao ser usada na ordenacao de atividades de acordo com
 * o nivel do risco.
 * 
 * @author Helen Bento Cavalcanti.
 *
 */
import java.util.HashMap;

public class OrdenaAtividadeRisco implements Comparator<Atividade> {

	private HashMap<String, Integer> riscos;

	/**
	 * Metodo que recebe duas atividades a serem comparadas de acordo com seu risco
	 * e retorna um inteiro que dira se um elemento e maior, igual ou menor que
	 * outro nesse quesito.
	 * 
	 * @param Dois objetos do tipo Atividade.
	 * @return um inteiro que dira se um elemento e maior, igual ou menor que outro
	 *         no quesito risco.
	 */
	@Override
	public int compare(Atividade o1, Atividade o2) {

		riscos = new HashMap<>();
		riscos.put("ALTO", 3);
		riscos.put("MEDIO", 2);
		riscos.put("BAIXO", 1);

		if (riscos.get(o2.getRisco()) - riscos.get(o1.getRisco()) == 0) {
			return o2.compareTo(o1);
		}

		return riscos.get(o2.getRisco()) - riscos.get(o1.getRisco());
	}

}
