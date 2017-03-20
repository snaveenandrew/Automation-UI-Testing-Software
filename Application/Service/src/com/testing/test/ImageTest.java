package com.testing.test;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.testing.process.ImageProcessor;

public class ImageTest {
	static Logger logger = Logger.getLogger(ImageTest.class);
	public void test(String file1,double time,double accuracy) throws IOException {
		// TODO Auto-generated constructor stub
		Properties prop=new Properties();
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		logger.info(ImageProcessor.compareImage(file1,time,accuracy));
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ImageTest i = new ImageTest();
		i.test("hai_0_0_100_100.png", 100, 75);
	}

}