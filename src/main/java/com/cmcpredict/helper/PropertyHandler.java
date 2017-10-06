package com.cmcpredict.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.cmcpredict.util.Constant;

public class PropertyHandler {

	public String readUserPropery(String key) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = loader.getResourceAsStream(Constant.USER_PROPERTIES_FILE);
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			return prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public String readCoin(String key) {
		
		//Coin List
		//https://api.coinmarketcap.com/v1/ticker/
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = loader.getResourceAsStream(Constant.COINLIST_PROPERTIES_FILE);
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			return prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
