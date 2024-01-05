package translate.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
	public static String unicode2UTF8(String unicodeString) {
		List<String> listUnicode = extractUnicodeString(unicodeString); // 提取Unicode 字符
		// 将 Unicode 字符转换为UTF-8, 并形成 <Unicode,Character>的格式
		Map<String, Character> map = convertUnicode2Utf8(listUnicode);
		Iterator<Entry<String, Character>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Character> entry = iterator.next();
			String key = entry.getKey();
			Character value = entry.getValue();
			unicodeString = unicodeString.replace(key, value.toString());
		}
		return unicodeString;
	}

	private static List<String> extractUnicodeString(String string) {
		String pattern = "\\\\u[0-9a-fA-F]{4}";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(string);

		List<String> unicodeArray = new ArrayList<String>();
		while (matcher.find()) {
			unicodeArray.add(matcher.group());
		}
		return unicodeArray;
	}

	private static Map<String, Character> convertUnicode2Utf8(List<String> unicodeString) {
		Map<String, Character> map = new HashMap<String, Character>();
		for (String string : unicodeString) {
			String hexString = string.substring(2);
			int codePoint = Integer.parseInt(hexString, 16);
			if (!map.containsKey(string)) {
				map.put(string, Character.toChars(codePoint)[0]);
			}
		}
		return map;
	}
}
