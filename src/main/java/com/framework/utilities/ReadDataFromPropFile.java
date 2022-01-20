package com.framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropFile {
	
	public static Properties readProperties(String filename){
		Properties prop = null;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+filename);// To read files in Java
			prop = new Properties(); //create properties variable
			prop.load(fis);//load properties		
		} catch (IOException e) {		
			e.printStackTrace();
		}	
		return prop;		
	}

}
