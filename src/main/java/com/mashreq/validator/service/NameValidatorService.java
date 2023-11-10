package com.mashreq.validator.service;

import org.springframework.stereotype.Service;

import com.mashreq.validator.model.TitleInput;
import com.mashreq.validator.model.TitleValidatorOutput;

@Service
public interface NameValidatorService {
	
	public TitleValidatorOutput mainValidator(TitleInput entityInput) throws Exception;
	

}
