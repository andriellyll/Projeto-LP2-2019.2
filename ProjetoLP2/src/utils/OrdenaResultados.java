package utils;

import java.util.Comparator;

public class OrdenaResultados implements Comparator<String>{

	@Override
	public int compare(String str1, String str2) {
		String partStr1 = str1.toLowerCase();
		String partStr2 = str2.toLowerCase();
		return partStr2.compareTo(partStr1);
	}

}
