package com.testing.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.testing.process.VideoProcessor;

public class VideoTest {
	static Logger logger = Logger.getLogger(VideoTest.class);
	public void test(String name,double time,double accuracy) throws IOException {
		// TODO Auto-generated constructor stub
		Properties prop=new Properties();
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		logger.info(VideoProcessor.DetectVideo());
	}
}
