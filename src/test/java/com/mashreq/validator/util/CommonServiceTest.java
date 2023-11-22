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
     void testDiffLanguageFuzzyJar() {
        TitleValidatorOutput output1 = commonService.diffLanguageFuzzyJar("hello", "hello");
        assertEquals("Matched", output1.getStatus());

        TitleValidatorOutput output2 = commonService.diffLanguageFuzzyJar("hello", "hola");
        assertEquals("Not Matched", output2.getStatus());
    }

    @Test
     void testCleanString() {
        assertEquals("hello world", commonService.cleanString("Hello, World!"));
    }

    @Test
     void testRemoveDuplicateWordsAndLowerCase() {
        assertEquals("a b", CommonService.removeDuplicateWordsAndLowerCase("a a b"));
    }

    @Test
     void testComparePhonetically() {
        assertTrue(CommonService.comparePhonetically("hello", "hola"));
        assertFalse(CommonService.comparePhonetically("hello", "world"));
        assertFalse(CommonService.comparePhonetically("hello", "world"));
        assertFalse(CommonService.comparePhonetically(null, ""));
    }

    @Test
     void testReplaceAbbreviations() {
        assertEquals("limited private company", CommonService.replaceAbbreviations("ltd pvt company"));
    }

    @Test
     void testMakeDecision() {
        TitleValidatorOutput output = commonService.makeDesition(80.0, "Matched", "Test comment");
        assertEquals(80.0, output.getScore(), 0.01);
        assertEquals("Matched", output.getStatus());
        assertEquals("Test comment", output.getComment());
    }

}