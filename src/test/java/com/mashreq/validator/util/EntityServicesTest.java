package com.mashreq.validator.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EntityServicesTest {

    @Test
    void firstWordMatchPhonetically() {
        EntityServices es=new EntityServices();
        assertEquals(true,es.firstWordMatchPhonetically("Mashreq G S","Mashreq Bank"));
    }

    @Test
     void testFirstWordMatchPhonetically() {
        assertTrue(EntityServices.firstWordMatchPhonetically("apple pie", "aple py"));
        assertTrue(EntityServices.firstWordMatchPhonetically("hello world", "helo world"));
        assertFalse(EntityServices.firstWordMatchPhonetically("hello", "world"));
        assertFalse(EntityServices.firstWordMatchPhonetically("a", "b"));
        assertFalse(EntityServices.firstWordMatchPhonetically("abc", "def"));
    }

    @Test
     void testFirstCharectorMatch() {
        assertFalse(EntityServices.firstCharectorMatch("apple pie", "apricot jam"));
        assertFalse(EntityServices.firstCharectorMatch("hello", "world"));
        assertTrue(EntityServices.firstCharectorMatch("a", "apple"));
        assertTrue(EntityServices.firstCharectorMatch("apple", "a"));
        assertFalse(EntityServices.firstCharectorMatch("abc", "def"));
        assertFalse(EntityServices.firstCharectorMatch("", "apple"));
        assertFalse(EntityServices.firstCharectorMatch("banana", ""));
    }

    @Test
     void testCleaningStrings() {
        assertEquals("apple pie", EntityServices.cleaningStings("apple! pie"));
        assertEquals("hello world", EntityServices.cleaningStings("Hello   World"));
        assertEquals("1 2 3", EntityServices.cleaningStings("  1.2.3  "));
        assertEquals("", EntityServices.cleaningStings(""));
    }

}
