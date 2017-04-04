package com.testing.process;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.testing.util.ImageUtil;

public class ImageProcessor {
	static Logger logger = Logger.getLogger(ImageProcessor.class);
	public static JSONObject compareImage(String file,double time,double accuracy) throws IOException {
		//System.out.println("Processor");
		Properties prop=new Properties();
		InputStream input = new FileInputStream("E:\\Final year project\\Function\\config\\config.properties");
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		logger.info("Image Comparison Service Started...");
		return ImageUtil.compareImage(file,time,accuracy);
	}
}
