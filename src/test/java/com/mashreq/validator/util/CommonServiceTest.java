package com.mashreq.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.mashreq.validator.model.TitleValidatorOutput;

class CommonServiceTest {


    private CommonService commonService;

   
      CommonServiceTest() {
        commonService = new CommonService();
    }

    @Test
     void testCompareStrings() {
        assertTrue(commonService.compareStrings("abc", "ABC"));
        assertFalse(commonService.compareStrings("abc", "xyz"));
    }

    

    @Test
     void testCleanString() {
        assertEquals("hello world", commonService.cleanString("Hello, World!"));
    }

   
    @Test
     void testComparePhonetically() {
        assertTrue(CommonService.comparePhonetically("hello", "hola"));
        assertFalse(CommonService.comparePhonetically("hello", "world"));
        assertFalse(CommonService.comparePhonetically("hello", "world"));
        assertFalse(CommonService.comparePhonetically("عبدالرحمن", "عبدالرحمن"));
    }

    @Test
     void testReplaceAbbreviations() {
        assertEquals("limited private company", CommonService.replaceAbbreviations("ltd pvt company"));
        assertEquals("company", CommonService.replaceAbbreviations("company"));
        assertEquals("private", CommonService.replaceAbbreviations("pvt"));
    }

    @Test
     void testMakeDecision() {
        TitleValidatorOutput output = commonService.makeDesition(80.0, "Matched", "Test comment");
        assertEquals(80.0, output.getScore(), 0.01);
        assertEquals("Matched", output.getStatus());
        assertEquals("Test comment", output.getComment());
    }
    @Test
     void testRemoveDuplicateWordsAndLowerCase() {
        // Test case with duplicate words and mixed case
        String input1 = "Hello world World hello";
        String expected1 = "hello world";
        assertEquals(expected1, commonService.removeDuplicateWordsAndLowerCase(input1));

        // Test case with no duplicates and mixed case
        String input2 = "Java is a programming language";
        String expected2 = "a is java language programming";
        assertEquals(expected2, commonService.removeDuplicateWordsAndLowerCase(input2));

        // Test case with duplicate words and all lowercase
        String input3 = "apple banana banana orange orange";
        String expected3 = "apple banana orange";
        assertEquals(expected3, commonService.removeDuplicateWordsAndLowerCase(input3));

        // Test case with no duplicates and all uppercase
        String input4 = "JAVA PYTHON C++";
        String expected4 = "c++ java python";
        assertEquals(expected4, commonService.removeDuplicateWordsAndLowerCase(input4));

        // Test case with an empty string
        String input5 = "";
        String expected5 = "";
        assertEquals(expected5, commonService.removeDuplicateWordsAndLowerCase(input5));

        // Test case with a single word
        String input6 = "programming";
        String expected6 = "programming";
        assertEquals(expected6, commonService.removeDuplicateWordsAndLowerCase(input6));

        // Test case with leading and trailing spaces
        String input7 = "    hello   world   ";
        String expected7 = "hello world";
        assertEquals(expected7, commonService.removeDuplicateWordsAndLowerCase(input7));
    }
    
    @Test
     void testDiffLanguageFuzzyJar() { // Test case with exact match
        String s1 = "Hello World";
        String s2 = "Hello World";
        TitleValidatorOutput result1 = commonService.diffLanguageFuzzyJar(s1, s2);
        assertEquals(100.0, result1.getScore());
        assertEquals("Matched", result1.getStatus());
        assertEquals("Fuzzy Percentage is matched", result1.getComment());

        // Test case with no match
        String s3 = "Java Programming";
        String s4 = "Python Programming";
        TitleValidatorOutput result2 = commonService.diffLanguageFuzzyJar(s3, s4);
        assertEquals(71.0, result2.getScore());
        assertEquals("Not Matched", result2.getStatus());
        assertEquals("Fuzzy Percentage", result2.getComment());

        // Test case with a partial match below potentialPoint
        String s5 = "Machine Learning";
        String s6 = "Artificial Intelligence";
        TitleValidatorOutput result3 = commonService.diffLanguageFuzzyJar(s5, s6);
        assertEquals(31.0, result3.getScore());
        assertEquals("Not Matched", result3.getStatus());
        assertEquals("Fuzzy Percentage", result3.getComment());

        // Test case with a partial match above potentialPoint
        String s7 = "Database Management";
        String s8 = "Data Management";
        TitleValidatorOutput result4 = commonService.diffLanguageFuzzyJar(s7, s8);
        assertEquals(88.0, result4.getScore());
        assertEquals("Potentially Matched", result4.getStatus());
        assertEquals("Fuzzy Percentage", result4.getComment());
    }
   
}