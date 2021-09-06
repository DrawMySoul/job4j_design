package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
	private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

	public static void main(String[] args) {
		byte b = 127;
		short s = 32767;
		int i = Integer.MAX_VALUE;
		long l = 2147483648L;
		char c = 'a';
		float f = 1.1f;
		double d = 2.2;
		boolean bb = true;

		LOG.debug("byte {}, short {}, int {}, long {}, char {}, float {}, double {}, boolean {}", b, s, i, l, c, f, d, bb);
	}
}
