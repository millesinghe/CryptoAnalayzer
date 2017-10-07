package com.cmcpredict.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
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
	
	public String readSystemPropery(String key) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = loader.getResourceAsStream(Constant.SYSTEM_PROPERTIES_FILE);
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
			return prop.getProperty(key.toUpperCase());

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

	public void writeToSystemPorperty(String fileName, String key, String value) throws URISyntaxException {
		
		URL url = this.getClass().getClassLoader().getResource(fileName +".properties");
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			File file = new File(url.getPath());
			output = new FileOutputStream(file);

			// set the properties value
			prop.setProperty(key,value);

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
