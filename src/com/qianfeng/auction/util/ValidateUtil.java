package com.qianfeng.auction.util;

import java.util.Random;

public class ValidateUtil {

	public static String createValidateCode() {
		String temp = "1234567890qwertyuioplkjhgfdsazxcvbnm";
		StringBuilder validateCode = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			validateCode.append(temp.charAt(new Random().nextInt(temp
					.length())));
		}
		return validateCode.toString();
	}

	public static void main(String[] args) {
		System.out.println(createValidateCode());
	}
}
