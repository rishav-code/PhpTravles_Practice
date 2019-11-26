package com.atmecs.practice1.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * The class loads the data of the property file and returns the value
 * associated with the key in the property file.
 * 
 * @author Rishav kumar
 *
 */

public class ReadProp {
	static Properties property;
	static File file;
	static FileReader reader;

	/**
	 * methods takes parameter as
	 * 
	 * @param filePath
	 * @return the propeties of the property file
	 * @throws IOException
	 */

	public static Properties loadProperty(String pathName) {
		property = new Properties();
		file = new File(pathName);
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			property.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return property;

	}

}
