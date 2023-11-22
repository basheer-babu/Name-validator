package com.mashreq.validator.service;

import com.mashreq.validator.model.TitleInput;
import com.mashreq.validator.model.TitleModel;
import org.junit.jupiter.api.Test;

import com.mashreq.validator.model.TitleValidatorOutput;
import com.mashreq.validator.util.CommonService;

import static org.junit.jupiter.api.Assertions.*;

class NameValidatorServiceImplTest {
	
	private CommonService commonService;

   private  NameValidatorServiceImpl nameValidatorService;

	NameValidatorServiceImplTest() {
        commonService = new CommonService();
       nameValidatorService=new NameValidatorServiceImpl();
    }


   @Test
    void testMainValidatorForMatchingEntityTypeAndLanguage() throws Exception {
      TitleInput input = new TitleInput();
      TitleModel titleModel=new TitleModel();
      titleModel.setEntityType("INDIVIDUAL");
      titleModel.setText("hai");
      titleModel.setLanguage("eng");
     input.setPaymentcopy(titleModel);
      TitleModel titleModel2=new TitleModel();
      titleModel2.setEntityType("En");
      titleModel2.setText("hai");
      titleModel2.setLanguage("eng");
      input.setWatchlist(titleModel2);

      TitleValidatorOutput result = nameValidatorService.mainValidator(input);
      assertEquals(0.0,result.getScore());
      assertEquals("error",result.getStatus());
      assertEquals("EntityType should match!",result.getComment());
   }
    @Test
     void testMakeDesition() {
        // Test case 1: Valid input
        TitleValidatorOutput output1 = commonService.makeDesition(90.5, "Matched", "Valid case");
        assertEquals(90.5, output1.getScore(), 0.01);
        assertEquals("Matched", output1.getStatus());
        assertEquals("Valid case", output1.getComment());

    }
	
}