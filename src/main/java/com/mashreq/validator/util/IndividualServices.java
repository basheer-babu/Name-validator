package com.mashreq.validator.util;

import org.apache.commons.codec.language.DoubleMetaphone;

public class IndividualServices {
	
	public static boolean areJumbledWordsPhoneticallyMatching(String str1, String str2) {
        String[] words1 = str1.split(" ");
        String[] words2 = str2.split(" ");

        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        String nullCheck=doubleMetaphone.encode(str1);
        if(nullCheck==null || nullCheck=="") {
        	return false;
        }
        for (String word1 : words1) {
            boolean foundMatch = false;
            for (String word2 : words2) {
                if (doubleMetaphone.isDoubleMetaphoneEqual(word1, word2)) {
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                return false;
            }
        }

        return true;
    }


}
