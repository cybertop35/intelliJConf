package com.music.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	private static final Pattern PATTERN = Pattern.compile("[^A-Za-z0-9_]");

	private static final int MAX_LENGTH = 127;

	public static String escapeStringAsFilename(String in){

		 return in.replaceAll("[:\\\\/*\"?|<>']", " ");
	}

	public static String decode(String s) throws UnsupportedEncodingException {
		return URLDecoder.decode(s, "UTF-8");
	}

	public static String encodeParameter(String s)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(s, "UTF-8");
	}

	public static String buildQueryString(Map<String, String> params)
			throws UnsupportedEncodingException {
		StringBuffer query = new StringBuffer();

		if (params.size() > 0) {
			query.append("?");

			for (String key : params.keySet()) {
				query.append(key);
				query.append("=");
				query.append(Utils.encodeParameter(params.get(key)));
				query.append("&");
			}

			if (query.charAt(query.length() - 1) == '&') {
				query.deleteCharAt(query.length() - 1);
			}
		}

		return query.toString();
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				String[] cls = new String[] {"cmd.exe", "/c", "cls"};
				Runtime.getRuntime().exec(cls); 
			} else {
				Runtime.getRuntime().exec("clear");
			}			
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
