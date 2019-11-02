package utils;

import java.util.Comparator;

public class OrdenaResultados implements Comparator<String>{

	@Override
	public int compare(String str1, String str2) {
		str1 = str1.split(":")[0];
		str2 = str2.split(":")[0];
		return str1.compareTo(str2);
	}

}
