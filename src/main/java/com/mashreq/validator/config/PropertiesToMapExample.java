package com.mashreq.validator.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import com.mashreq.validator.controller.NameController;

public class PropertiesToMapExample {
	
	private static final org.slf4j.Logger log=LoggerFactory.getLogger(PropertiesToMapExample.class);

	public static Properties GetPropertice() {
		Properties properties = new Properties();

		try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/abbreviations.properties")) {
			properties.load(fileInputStream);
			return properties;
		} catch (IOException e) {
			log.error("error in PropertiesToMapExample"+e.getMessage());
			return null;
		}

	}

	public static String getAbbrivation(String input) {
		Properties properties = GetPropertice();
		if (properties != null) {
			String prop = properties.getProperty(input);

			return prop;
		}
		return null;

	}
	
	public static Properties getProperticeMap(String input) {
		Properties properties = GetPropertice();
		return properties;

	}

//	public static void main(String[] args) throws Exception {
//		System.out.println("abbri::"+getAbbrivation("ltd"));
//		
//		System.out.println("abbri::"+getAbbrivation("ld"));
//		System.out.println("abbri::"+getAbbrivation("mohammed"));
//	}

	// get key based on value

//	for (String key : properties.stringPropertyNames()) {
//		if (properties.getProperty(key).equals(input)) {
//			return key;
//		}

}
