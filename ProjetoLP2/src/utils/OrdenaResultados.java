package utils;

import java.util.Comparator;

public class OrdenaResultados implements Comparator<String>{

	@Override
	public int compare(String str1, String str2) {
		String partStr1 = str1.split(":")[1].toLowerCase();
		String partStr2 = str2.split(":")[1].toLowerCase();
		return partStr1.compareTo(partStr2);
	}

}
