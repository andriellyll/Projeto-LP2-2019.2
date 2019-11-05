package utils;

import java.util.Comparator;

import pacote.Pesquisa;

public class OrdenaPesquisaProblema implements Comparator<Pesquisa>{

	@Override
	public int compare(Pesquisa p1, Pesquisa p2) {
		return p1.getIdProblema().compareTo(p2.getIdProblema());
	}
	
	
}
