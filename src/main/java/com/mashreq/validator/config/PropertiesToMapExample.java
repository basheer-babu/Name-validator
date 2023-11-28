package com.mashreq.validator.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

//import com.mashreq.validator.controller.NameController;

public class PropertiesToMapExample {
	
	private static final org.slf4j.Logger log=LoggerFactory.getLogger(PropertiesToMapExample.class);

	public static Properties GetPropertice() {
		Properties properties = new Properties();
		String filename="abbreviations.properties";
		
		

		try  {
			InputStream inputStream= PropertiesToMapExample.class.getClassLoader().getResourceAsStream(filename);
//			BufferedReader reader = new BufferedReader(new FileReader(new File("classpath:abbreviations.properties")));
			properties.load(inputStream);
			inputStream.close();
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
