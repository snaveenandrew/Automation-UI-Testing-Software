package com.testing.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.testing.process.OCRProcessor;

public class OCRTest {
	static Logger logger = Logger.getLogger(TestProcessor.class);
	public void test() throws Exception{
		Properties prop=new Properties();
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		
		logger.info("OCR COMPARISON");
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		
		OCRProcessor process = new OCRProcessor();
		logger.info(process.textExtract("WAKE UP"));
	}
}
