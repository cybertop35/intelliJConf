package com.fantagame.be.util;

import java.util.Random;

public class StringUtils {

	public static int[] getSubStrCoor(String param, String value) {
		if (param == null)
			throw new IllegalArgumentException();

		if (value == null)
			return getSubStrCoor(param);

		String splited[] = removeFirstandLastChar(param).split(",");

		int coo[] = new int[2];

		if (splited[1].equalsIgnoreCase("Max") && value != null)
			coo = getSubStrCoor(param.replace("MAX",
					String.valueOf(value.length())));

		return coo;

	}

	public static int[] getSubStrCoor(String param) {

		if (param == null)
			throw new IllegalArgumentException();

		String splited[] = removeFirstandLastChar(param).split(",");

		int coo[] = new int[2];

		try {
			coo[0] = Integer.parseInt(splited[0]);
			coo[1] = Integer.parseInt(splited[1]);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		return coo;
	}

	public static int firstNumberInString(String n) {
		int count = n.length();
		int value = 0;

		boolean firstCooIsNumber = false;
		while (count >= 0 && !firstCooIsNumber) {
			try {
				value = Integer.parseInt(n.substring(0, count));
				firstCooIsNumber = true;
			} catch (Exception e) {
				count--;
			}

		}

		return value;
	}

	public static String removeFirstandLastChar(String param) {
		String tmp = param.substring(1, param.length() - 1);

		return tmp;
	}

	public static String[] copy(String[] state) {
		String[] copy = new String[state.length];
		System.arraycopy(state, 0, copy, 0, state.length);
		return copy;
	}

	// private static final char[] kDigits = { '0', '1', '2', '3', '4', '5',
	// '6', '7', '8', '9', 'a',
	// 'b', 'c', 'd', 'e', 'f' };

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	public static byte[] hexToBytes(char[] hex) {
		int length = hex.length / 2;
		byte[] raw = new byte[length];
		for (int i = 0; i < length; i++) {
			int high = Character.digit(hex[i * 2], 16);
			int low = Character.digit(hex[i * 2 + 1], 16);
			int value = (high << 4) | low;
			if (value > 127)
				value -= 256;
			raw[i] = (byte) value;
		}
		return raw;
	}

	public static byte[] hexToBytes(String hex) {
		return hexToBytes(hex.toCharArray());
	}

	public static synchronized String generateUniqueToken(Integer length) {

		byte random[] = new byte[length];
		Random randomGenerator = new Random();
		StringBuffer buffer = new StringBuffer();

		randomGenerator.nextBytes(random);

		for (int j = 0; j < random.length; j++) {
			byte b1 = (byte) ((random[j] & 0xf0) >> 4);
			byte b2 = (byte) (random[j] & 0x0f);
			if (b1 < 10)
				buffer.append((char) ('0' + b1));
			else
				buffer.append((char) ('A' + (b1 - 10)));
			if (b2 < 10)
				buffer.append((char) ('0' + b2));
			else
				buffer.append((char) ('A' + (b2 - 10)));
		}

		return (buffer.toString());
	}

}
