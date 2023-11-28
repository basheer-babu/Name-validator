package com.mashreq.validator;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mashreq.validator.util.CommonService;

@SpringBootApplication
public class TitleValidatorApplication {
	private static final org.slf4j.Logger log=LoggerFactory.getLogger(TitleValidatorApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TitleValidatorApplication.class, args);
		log.info("TitleValidatorApplication has been started successfully!");
	}

}
