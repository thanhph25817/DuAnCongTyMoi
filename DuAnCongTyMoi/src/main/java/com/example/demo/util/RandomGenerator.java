package com.example.demo.util;

import org.apache.commons.text.RandomStringGenerator;

import java.security.SecureRandom;

public class RandomGenerator {

	private static SecureRandom random = new SecureRandom();

	public static String randomWithNDigits(int n) {
		n = Math.abs(n);
		
		return String.format("%s",
				(int) Math.pow(10.0D, (double) (n - 1)) + random.nextInt(9 * (int) Math.pow(10.0D, (double) (n - 1))));
	}
	
    public static String generateRandomNumbers(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(48, 57)
            .build();
        
        return pwdGenerator.generate(length);
    }
    
    public static String generateRandomAlphabet(int length, boolean lowerCase) {
        int low = lowerCase ? 97 : 65;
        int hi = lowerCase ? 122 : 90;

        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(low, hi)
            .build();
        
        return pwdGenerator.generate(length);
    }
    
    public static String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
            .build();
        
        return pwdGenerator.generate(length);
    }
}
