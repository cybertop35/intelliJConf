package com.music.youtubemp3;

import java.util.HashMap;
import java.util.Map;

public class Sign {

	private static String x3[] = { "a", "c", "e", "i", "h", "m", "l", "o", "n",
			"s", "t", "." };
	private static int G3[] = { 6, 7, 1, 0, 10, 3, 7, 8, 11, 4, 7, 9, 10, 8, 0, 5, 2 };
	private static String M[] = { "a", "c", "b", "e", "d", "g", "m", "-", "s", "o",
			".", "p", "3", "r", "u", "t", "v", "y", "n" };
	private static int X[][] = {
			{ 17, 9, 14, 15, 14, 2, 3, 7, 6, 11, 12, 10, 9, 13, 5 },
			{ 11, 6, 4, 1, 9, 18, 16, 10, 0, 11, 11, 8, 11, 9, 15, 10, 1, 9, 6 } };
	private static String r3[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	private static Map<String, Integer> A = new HashMap<String, Integer>() {
		{
			put("a", 870);
			put("b", 906);
			put("c", 167);
			put("d", 119);
			put("e", 130);
			put("f", 899);
			put("g", 248);
			put("h", 123);
			put("i", 627);
			put("j", 706);
			put("k", 694);
			put("l", 421);
			put("m", 214);
			put("n", 561);
			put("o", 819);
			put("p", 925);
			put("q", 857);
			put("r", 539);
			put("s", 898);
			put("t", 866);
			put("u", 433);
			put("v", 299);
			put("w", 137);
			put("x", 285);
			put("y", 613);
			put("z", 635);
			put("_", 638);
			put("&", 639);
			put("-", 880);
			put("/", 687);
			put("=", 721);
		}
	};

	private static double L[] = { 1.23413, 1.51214, 1.9141741, 1.5123114, 1.51214,1.2651 };
	private int F = 1;

	private static String gh() {
		String i = gs(G3, x3);
		return i;
	}

	private static String gs(int[] I, String[] B) {
		String j = "";

		for (int i = 0; i < I.length; i++) {
			j += B[I[i]];
		}
		return j;
	}

	public static String sign(String h) {
		double F = L[1 % 2];
		String W ="";
		String S = gs(X[0], M);
		String T = gs(X[1], M);
		F = L[1];
//		if (ew(W, S) || ew(W, T)) {
//			F = L[1];
//		} else {
//			F = L[5 % 3];
//		}
		double N = 3219;
		for (int i = 0; i < h.length(); i++) {
			char c =  h.charAt(i);
			String Q = String.valueOf(c).toLowerCase();
			if (fn(r3, Q) > -1) {
				N = (N + (Integer.parseInt(Q) * 121 * F));
			} else if (A.containsKey(Q)) {
				N = N + (A.get(Q) * F);
			}

			N = N * 0.1;
		}

		return String.valueOf(Math.round(N * 1000));
	}

	private static int fn(String[] r32, String q) {
		for (int i = 0; i < r32.length; i++)
			if (r32[i].equals(q))
				return i;
		return -1;
	}

	private static boolean ew(String w, String t) {
		return w.indexOf(t, (w.length() - t.length())) != -1;
	}
 
}
