package com.mashreq.validator.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.slf4j.LoggerFactory;

import com.mashreq.validator.config.PropertiesToMapExample;
import com.mashreq.validator.controller.NameController;
import com.mashreq.validator.model.TitleValidatorOutput;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class CommonService {

	public static final String pote_match = "Potentially Matched";
	public static final String matched = "Matched";
	public static final String error = "error";
	public static final String not_match = "Not Matched";
	private static double potentialPoint = 79.0;
	
	PropertiesToMapExample propertiesToMapExample=new PropertiesToMapExample(); 
	
	private static final org.slf4j.Logger log=LoggerFactory.getLogger(CommonService.class);
//	@Value("${potential.point}")
//	private double potentialPoint=79.0;

	// Plan Comparison ignoring case
	public boolean compareStrings(String s1, String s2) {

		if (s1.equalsIgnoreCase(s2)) {
			return true;
		} else {

			return false;

		}
	}

	// other than eng
	public TitleValidatorOutput diffLanguageFuzzyJar(String s1, String s2) {

		int ratio = FuzzySearch.ratio(s1, s2);

		if (ratio == 0) {

			return makeDesition(0.0, not_match, "Fuzzy Percentage not matched");
		} else if (ratio == 100) {

			return makeDesition(100.0, matched, "Fuzzy Percentage is matched");
		} else {
			log.info("ratio" + ratio + "::" + potentialPoint + "::");
			if (ratio < potentialPoint) {
				return makeDesition((double) ratio, not_match, "Fuzzy Percentage");
			} else {
				return makeDesition((double) ratio, pote_match, "Fuzzy Percentage");
			}

		}

	}
	
	public String cleanString(String s) {
		String regex = "[^a-zA-Z0-9]";
		String s1=removeDuplicateWordsAndLowerCase(s).replaceAll(regex, " ").toLowerCase().trim().replaceAll("\\s+", " ");
		
		return s1;
		
	}
	
	 public static String removeDuplicateWordsAndLowerCase(String input) {
	        // Split the input string into words using space as a delimiter
	        String[] words = input.split(" ");

	        // Create a list to store unique lowercase words
	        List<String> uniqueWords = new ArrayList();

	        for (String word : words) {
	            // Convert the word to lowercase
	            String lowercaseWord = word.toLowerCase();

	            // Check if the lowercase word is not already in the list
	            if (!uniqueWords.contains(lowercaseWord)) {
	                // Add the lowercase word to the list to mark it as seen
	                uniqueWords.add(lowercaseWord);
	            }
	        }

	        // Sort the unique words alphabetically
	        Collections.sort(uniqueWords);

	        // Join the sorted unique words into a single string
	        String result = String.join(" ", uniqueWords);

	        return result.trim();
	    }
	 
	 public static boolean comparePhonetically(String word1, String word2) {
			
			DoubleMetaphone metaphone = new DoubleMetaphone();
			metaphone.setMaxCodeLen(100);
	        String phonetic1 = metaphone.encode(word1);
	        String phonetic2 = metaphone.encode(word2);
	        log.info("comparePhonetically"+phonetic2+":"+phonetic1);
	        if(!phonetic1.equals("") ) {
	        	return phonetic1.equals(phonetic2);
	        }
	        return false;
	    }
	 
	 public static String replaceAbbreviations(String input) {
	        // Define a mapping of abbreviations to their full forms
//	        Map<String, String> abbreviationMap = new HashMap();
//	        abbreviationMap.put("ltd", "limited");
//	        abbreviationMap.put("pvt", "private");
		 
		 Properties properties= PropertiesToMapExample.GetPropertice();

	        // Split the input string into words
	        String[] words = input.split(" ");

	        // Create a StringBuilder to construct the result
	        StringBuilder resultBuilder = new StringBuilder();

	        for (String word : words) {
	            // Check if the word is an abbreviation and if its expansion is available
	            if (properties.containsKey(word.toLowerCase())) {
	                // Replace the abbreviation with its expansion
	                resultBuilder.append(properties.getProperty(word.toLowerCase())).append(" ");
	            } else {
	                // Keep the original word
	                resultBuilder.append(word).append(" ");
	            }
	        }

	        // Remove the trailing space and return the result
	        String result = resultBuilder.toString().trim();
	        
	        log.info("Text after replace Abbreviations::"+result);

	        return result;
	    }
		

	public TitleValidatorOutput makeDesition(Double score, String Status, String Comment) {
		TitleValidatorOutput titleValidatorOutput = new TitleValidatorOutput();
		titleValidatorOutput.setScore(score);
		titleValidatorOutput.setStatus(Status);
		titleValidatorOutput.setComment(Comment);

		return titleValidatorOutput;

	}

}
