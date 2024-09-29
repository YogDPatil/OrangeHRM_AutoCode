package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.constants.Env;

public class TestUtils {
	private WebDriver driver;

	public static String getValueFromPropertyFile(String key, Env env) {
		String envPropertiesFile = env.toString().toLowerCase();
		File file = new File(
				System.getProperty("user.dir") + "/src/test/resources/config/" + envPropertiesFile + "_config.properties");
		FileReader fileReader;
		Properties properties = null;
		try {
			fileReader = new FileReader(file);
			properties = new Properties();
			properties.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}
	
	
}
