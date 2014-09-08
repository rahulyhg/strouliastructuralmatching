package com.isistan.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StrugliaMatchingProperties {
	
	private static StrugliaMatchingProperties instance = new StrugliaMatchingProperties();
	private Properties properties; 
	
	private StrugliaMatchingProperties () {
		properties = new Properties();
	}
	
	public static StrugliaMatchingProperties instance() {
		return instance;
	}
	
	public void loadProperties(InputStream inStream) {
		try {
			properties.loadFromXML(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(StrugliaPropertyName property) {	
		return properties.getProperty(property.toString().toLowerCase());
	}
	
	public float getPrimitiveTypeCompatibility(PrimitiveType type1, PrimitiveType type2) {
		String property = properties.getProperty(type1.toString().toLowerCase() + "_to_" + type2.toString().toLowerCase());
		return property != null ? Float.parseFloat(property) : 0;
	}
}
