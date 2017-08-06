package com.company.demo.itext.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationUtility {
	
	private static final Logger logger = Logger.getLogger(ConfigurationUtility.class);

	public Properties loadProperties() throws IOException {
		Properties properties = new Properties();
		InputStream input = ConfigurationUtility.class.getResourceAsStream("/configuration.properties");
		if (input == null) {
			logger.error("Cannot load property file!");
			throw new IOException();
		}
		logger.info("Loading property file");
		properties.load(input);
		logger.info("Loading property file OK");
		return properties;
	}

}
