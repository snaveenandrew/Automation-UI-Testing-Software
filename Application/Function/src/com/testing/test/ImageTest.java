package com.testing.test;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.testing.process.AudioProcessor;
import com.testing.process.ImageProcessor;
import com.testing.process.OCRProcessor;
import com.testing.process.VideoProcessor;

public class ImageTest {
	static Logger logger = Logger.getLogger(ImageTest.class);
	public void test(String file,double time,double accuracy) throws IOException {
		// TODO Auto-generated constructor stub
		Properties prop=new Properties();
		InputStream input = new FileInputStream("E:\\Final year project\\Function\\config\\config.properties");
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		
		logger.info(ImageProcessor.compareImage(file,time,accuracy));
		
	}
}
