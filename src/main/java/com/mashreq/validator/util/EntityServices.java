package com.mashreq.validator.util;

import org.apache.commons.codec.language.DoubleMetaphone;

public class EntityServices {

	public static boolean firstWordMatchPhonetically(String s1, String s2) {

		DoubleMetaphone doubleMetaphone = new DoubleMetaphone();

		String[] words1= s1.split(" ");
		String[] words2= s2.split(" ");
	
		if(words1[0].length()>1 || words2[0].length()>1) {
			return doubleMetaphone.isDoubleMetaphoneEqual(words1[0], words2[0]);
		}else {
			return false;
		}
		

		
	}

	public static boolean firstCharectorMatch(String s1, String s2) {
		String[] words1= s1.split(" ");
		String[] words2= s2.split(" ");
		
//		System.out.println("fir:"+words1[0].length());
//		System.out.println("sec:"+words2[0].length());
		
		if(words1[0].length()==1 || words2[0].length()==1) {
			return words1[0].charAt(0)==words2[0].charAt(0);
		}else {
			return false;
		}

		
	}
	
	public static String cleaningStings(String s) {
		String regex = "[^a-zA-Z0-9]";
		
		return s.replaceAll(regex, " ").toLowerCase().trim().replaceAll("\\s+", " ");
		
	}

}
