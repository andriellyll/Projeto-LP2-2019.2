package system;

import java.util.Comparator;

public class OrdenaResultados implements Comparator<String>{

	@Override
	public int compare(String str1, String str2) {

		String partStr1 = str1.split(":")[0];
		String partStr2 = str2.split(":")[0];
		/*
		String otaPartStr1 = str1.split(":")[1].toLowerCase();
		String otaPartStr2 = str2.split(":")[1].toLowerCase();

		if(partStr2.compareTo(partStr1) != 0) {
			return partStr2.compareTo(partStr1);
		}
		*/
		return partStr2.toLowerCase().compareTo(partStr1.toLowerCase());
	}

}
