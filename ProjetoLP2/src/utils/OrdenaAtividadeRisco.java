package utils;

import java.util.Comparator;
import java.util.HashMap;

import pacote.Atividade;

public class OrdenaAtividadeRisco  implements Comparator<Atividade>{

	private HashMap<String, Integer> riscos;
	
	@Override
	public int compare(Atividade o1, Atividade o2) {
	
		riscos = new HashMap<>();
		riscos.put("ALTO",3);
		riscos.put("MEDIO",2);
		riscos.put("BAIXO",1);
		
		
		return riscos.get(o1.getRisco()) - riscos.get(o2.getRisco());
	}

}