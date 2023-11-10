package com.mashreq.validator.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.language.DoubleMetaphone;

public class EntityServices {

	public static boolean firstWordMatchPhonetically(String s1, String s2) {

		DoubleMetaphone doubleMetaphone = new DoubleMetaphone();

		List<String> words1 = Arrays.asList(s1.split(" "));
		List<String> words2 = Arrays.asList(s2.split(" "));

		return words1.stream()
				.flatMap(word1 -> words2.stream().filter(word2 -> doubleMetaphone.isDoubleMetaphoneEqual(word1, word2)))
				.findFirst().isPresent();
	}

	public static boolean firstCharectorMatch(String s1, String s2) {
		List<String> words1 = Arrays.asList(s1.split(" "));
		List<String> words2 = Arrays.asList(s2.split(" "));

		return words1.stream().filter(word1 -> !word1.isEmpty()) // Filter out empty words
				.findFirst().map(firstWord1 -> words2.stream().filter(word2 -> !word2.isEmpty()) // Filter out empty
																									// words
						.findFirst().map(firstWord2 -> firstWord1.charAt(0) == firstWord2.charAt(0)).orElse(false))
				.orElse(false);
	}
	
	public static String cleaningStings(String s) {
		String regex = "[^a-zA-Z0-9]";
		
		return s.replaceAll(regex, " ").toLowerCase().trim().replaceAll("\\s+", " ");
		
	}

}
