package com.testing.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.testing.model.ImageModel;


public class FileUtil {
	static Logger logger = Logger.getLogger(FileUtil.class);
	public int isvalidFile(ImageModel model) throws IOException{
		Properties prop=new Properties();
		InputStream input = new FileInputStream("E:\\Final year project\\Function\\config\\config.properties");
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		int errorCode=0;
		if(null==model.getFileName().toString() || model.getFileName().toString().equals("")){
			errorCode =  -1;
		}
		else if(model.getFileName().exists()==false){
			errorCode =  -2;
		}
		else{
			String support = prop.getProperty("SUPPORTED_IMAGE_FORMAT");
			for(String supportedFormated:support.split(",")){
				if(model.getFileName().toString().endsWith(supportedFormated)){
						errorCode = 0;
						break;
				}
				else{	
					errorCode = -3;
				}
			}
		}
	
		return errorCode;
	}
}
