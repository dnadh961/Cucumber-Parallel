package com.webtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private Properties prop = null;

	public Configuration() {
		try {
			FileInputStream fis = new FileInputStream(new File("config.properties"));
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Please check for config.properties file");
		}
	}

	public String getBrowserName() {
		if (System.getenv("browser") != null) {
			return System.getenv("browser");
		} else if (System.getProperty("browser") != null) {
			return System.getProperty("browser");
		}
		return prop.getProperty("browser");
	}

	public String getOSName() {
		if (System.getenv("os") != null) {
			return System.getenv("os");
		} else if (System.getProperty("os") != null) {
			return System.getProperty("os");
		}
		return prop.getProperty("os");
	}

	public String getURL() {
		if (System.getenv("instance.url") != null) {
			return System.getenv("instance.url");
		} else if (System.getProperty("instance.url") != null) {
			return System.getProperty("instance.url");
		}
		return prop.getProperty("instance.url");
	}

}
