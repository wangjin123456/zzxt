package com.zzxt.util;

import java.util.List;
/**
 * List转换成String
 * @author think
 *
 */

public class ListToStringUtil {

	public static String listToString(List<String> list) {
		if (list == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean first = true;
		// 第一个前面不拼接","
		for (String string : list) {
			if (first) {
				first = false;
			} else {
				result.append(",");
			}
			result.append(string);
		}
		return result.toString();
	}

}
