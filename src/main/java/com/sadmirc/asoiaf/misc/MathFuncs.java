package com.sadmirc.asoiaf.misc;

public class MathFuncs {
	public static int randomInt(int min, int max) {
		int range = (max - min) + 1;
		int random = (int) (Math.random() * range) + min;
		return random;
	}
}
