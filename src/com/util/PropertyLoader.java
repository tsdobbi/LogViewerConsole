package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

	private static PropertyLoader plObj;
	private Properties prop = new Properties();

	private String propertyFileName = "logViewerConsole.properties";

	private void loadProperties() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private PropertyLoader() {
		loadProperties();
	}

	public static PropertyLoader getInstance() {
		if (plObj == null)
			plObj = new PropertyLoader();
		return plObj;
	}

	public Properties getProperties() {

		return prop;
	}

}
