package edu.smcm.util;

public class Util {

	private Util() {
	}

	public static int maxInt(int a, int b) {
		return (a > b) ? a : b;
	}
	
	public static boolean isBetween(int low, int value, int high) {
		return low <= value && value <= high;
	}
	
}
