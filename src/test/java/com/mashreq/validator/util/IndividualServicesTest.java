package com.mashreq.validator.util;

import org.junit.jupiter.api.Test;
import com.mashreq.validator.util.*;
import static org.junit.jupiter.api.Assertions.*;

class IndividualServicesTest {

    @Test
    void areJumbledWordsPhoneticallyMatching() {
        IndividualServices is=new IndividualServices();
        assertEquals(true,is.areJumbledWordsPhoneticallyMatching("ram kumar","kumar ram"));
    }
    @Test
    void areJumbledWordsPhoneticallyMatchingFalsecase() {
        IndividualServices is=new IndividualServices();
        assertEquals(false,is.areJumbledWordsPhoneticallyMatching("ram kumar","kumar "));
    }
    @Test
    void areJumbledWordsPhoneticallyMatchingDiffLang() {
        IndividualServices is=new IndividualServices();
        assertEquals(false,is.areJumbledWordsPhoneticallyMatching("",""));
    }
    @Test
    void areJumbledWordsPhoneticallyMatchingempty() {
        IndividualServices is=new IndividualServices();
        assertEquals(false,is.areJumbledWordsPhoneticallyMatching("abc",""));
    }
}