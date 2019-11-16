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
		
		if(riscos.get(o2.getRisco()) - riscos.get(o1.getRisco()) == 0) {
			return o2.compareTo(o1);
		}
		
		return riscos.get(o2.getRisco()) - riscos.get(o1.getRisco());
	}

}
