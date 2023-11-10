package com.mashreq.validator.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashreq.validator.model.TitleInput;
import com.mashreq.validator.model.TitleValidatorOutput;
import com.mashreq.validator.service.NameValidatorService;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
//@CrossOrigin()
//@RequiredArgsConstructor
public class NameController {
	
	@Autowired
	private NameValidatorService nameValidatorService;
	
	private static final org.slf4j.Logger log=LoggerFactory.getLogger(NameController.class);
	
	@PostMapping(value = "/titlevalidator")
	public TitleValidatorOutput consume(@Valid @RequestBody TitleInput titleInput)  {
		//input
		log.info("---------------------------");
		log.info("Payment Copy::"+titleInput.getPaymentcopy().getText());
		log.info("Watch Name::"+titleInput.getWatchlist().getText());
		log.info("Entity Type::"+titleInput.getPaymentcopy().getEntityType());
		
		TitleValidatorOutput titleValidatorOutput=null;
		
		try {
			 titleValidatorOutput=nameValidatorService.mainValidator(titleInput);
			log.info("Match Score::"+titleValidatorOutput.getScore());
			log.info("Match Result::"+titleValidatorOutput.getStatus());
			log.info("---------------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return titleValidatorOutput;
	}
	

}
