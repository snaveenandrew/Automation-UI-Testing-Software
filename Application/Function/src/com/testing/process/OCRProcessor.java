package com.testing.process;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.testing.util.OCRUtil;

public class OCRProcessor {
	static Logger logger = Logger.getLogger(OCRProcessor.class);
	public static JSONObject checkString(String name,double time) throws IOException {
		//System.out.println("Processor");
		Properties prop=new Properties();
		InputStream input = new FileInputStream("E:\\Final year project\\Function\\config\\config.properties");
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		logger.info("Text Extraction Service Started...");
		return OCRUtil.checkString(name,time);
	}
}
