package com.testing.test;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.testing.model.ImageModel;
import com.testing.process.ImageProcessor;

public class ImageTest {
	static Logger logger = Logger.getLogger(TestProcessor.class);
	public void test() throws Exception{
				// Initializing CONFIG Property
				Properties prop=new Properties();
				InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
				prop.load(input);
				
				//Initializing LOGGER Property
				PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
				logger.info("IMAGE COMPARISON");
				ImageProcessor imageProcess = new ImageProcessor();
				
		/*		//Image Comparison Scenario 1 : Direct Image
				String sourcePath=prop.getProperty("SOURCE_IMAGE");
				String targetPath=prop.getProperty("TARGET_IMAGE");
				logger.info(imgComp.compareImage(sourcePath,sourcePath,80));
				logger.info(imgComp.compareImage(sourcePath,targetPath,75));
				logger.info(imgComp.compareImage(targetPath,targetPath,90));
		*/
				
				//Image Comparison Scenario 2 : Screenshot and cropping image
				BufferedImage image;
				ImageModel source = new ImageModel();
				int x = Integer.parseInt(prop.getProperty("X"));
				int y = Integer.parseInt(prop.getProperty("Y"));		
				int width = Integer.parseInt(prop.getProperty("WIDTH"));
				int height = Integer.parseInt(prop.getProperty("HEIGHT"));
				
				//Getting Source Image
				image = imageProcess.captureImage();
				image = imageProcess.cropImage(image,x,y,width,height);
				source = imageProcess.saveImage(image);
				logger.info("Image Successully Saved in "+source.getFileName());
				logger.info(imageProcess.compareImage(source,75));
		
	}

}
