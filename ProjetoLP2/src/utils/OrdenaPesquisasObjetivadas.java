package utils;

import java.util.Comparator;

import pacote.Pesquisa;

public class OrdenaPesquisasObjetivadas implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (p2.getNumObjetivos() - p1.getNumObjetivos() == 0) {
			return p2.compareTo(p1);
		}
		return p2.getNumObjetivos() - p1.getNumObjetivos();
	}

}
