package utils;

import java.util.Comparator;

import pacote.Atividade;

public class OrdenaAtividadeDuracao implements Comparator<Atividade>{

	@Override
	public int compare(Atividade o1, Atividade o2) {
		return o1.getDuracao() - o2.getDuracao();
	}


}
