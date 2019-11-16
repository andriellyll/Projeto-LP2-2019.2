package utils;

import java.util.Comparator;

import pacote.Atividade;

public class OrdenaAtividadePendencias implements Comparator<Atividade>{

	@Override
	public int compare(Atividade o1, Atividade o2) {
		return o1.ItensPendentes() - o2.ItensPendentes();
	}

}
