package com.testing.util;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import com.testing.model.ImageModel;
import com.testing.model.ResponseModel;


public class ImageUtil {
	static Properties prop=new Properties();
	static Logger logger = Logger.getLogger(ImageUtil.class);
	
	//Scenario 1
	public static JSONObject compareImage(String sourcePath, String targetPath, double accuracy) throws IOException {
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		double result = 0.0;
		int sourceFileError,targetFileError;
		ResponseModel responseModel = new ResponseModel();
		ImageModel sourceImage  = new ImageModel();
		ImageModel targetImage = new ImageModel();
		FileUtil check = new FileUtil();
		Response response = new Response();
		String errorCode="FILE_ERROR_";
		try{
			sourceImage.setFileName(new File(sourcePath));
			targetImage.setFileName(new File(targetPath));
			sourceFileError=check.isvalidFile(sourceImage);
			targetFileError=check.isvalidFile(targetImage);
			if (sourceFileError !=0 || targetFileError !=0){
				errorCode = (sourceFileError !=0)?errorCode+sourceFileError:errorCode+targetFileError;
				logger.error("Error" + errorCode);
				Exception fileException= new Exception();
				throw fileException;
			}
			else{
				//sourceImage.setBufferedImage(ImageIO.read(sourceImage.getFileName()));
				//targetImage.setBufferedImage(ImageIO.read(targetImage.getFileName()));					
				sourceImage.setHeight(sourceImage.getBufferedImage().getHeight());
				targetImage.setHeight(targetImage.getBufferedImage().getHeight());
				sourceImage.setWidth(sourceImage.getBufferedImage().getWidth());
				targetImage.setWidth(targetImage.getBufferedImage().getWidth());	
				if (sourceImage.getHeight() != targetImage.getHeight() || (sourceImage.getWidth() != sourceImage.getWidth())) {
					responseModel.setMessage("Source Image dimensions does not match with the target image");
					responseModel.setStatus(false);
				}
				else{
					// Iterate through each pixel 
					for (int x = 0; x < sourceImage.getHeight(); x++) {
						for (int y = 0; y < sourceImage.getWidth(); y++) {
							//get(y,x) ==> get(column, row)
							if (sourceImage.getBufferedImage().getRGB(y, x) == targetImage.getBufferedImage().getRGB(y, x)) {
								result+=1;
							}
						}
					}
					result=(result*100)/(sourceImage.getHeight()*sourceImage.getWidth());
					responseModel.setOutput(result);
					if(result < accuracy){
						responseModel.setMessage("Source Image does not match with the target image");
						responseModel.setStatus(false);
					}
					else{
						responseModel.setMessage("Image Comparison success");
						responseModel.setStatus(true);
					}
				}
			}
		}
		catch (Exception fileException ) {
			// TODO Auto-generated catch block
			responseModel.setOutput(result);
			responseModel.setMessage(prop.getProperty(errorCode));
			responseModel.setStatus(false);
			return response.getResponse(responseModel);
		}
		return response.getResponse(responseModel);
	}

	//Scenario 2
	public static JSONObject compareImage(ImageModel source, double accuracy) throws IOException{
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		ResponseModel responseModel = new ResponseModel();
		Response response = new Response();
		BufferedImage sourceImage;
		try {
			sourceImage = ImageIO.read(source.getFileName());
			BufferedImage targetImage = captureImage();
			int x = source.getX();
			int y = source.getY();
			int width = source.getWidth();
			int height = source.getHeight();
			targetImage = cropImage(targetImage,x,y,width,height);
			int result=0;
			// Iterate through each pixel 
			for (int i = 0; i < sourceImage.getHeight(); i++) {
				for (int j = 0; j < sourceImage.getWidth(); j++) {
					//get(y,x) ==> get(column, row)
					if (sourceImage.getRGB(j, i) == targetImage.getRGB(j, i)) {
					result+=1;
					}
				}
			}
			result=(result*100)/(sourceImage.getHeight()*sourceImage.getWidth());
			responseModel.setOutput(result);
			if(result < accuracy){
				responseModel.setMessage("Source Image does not match with the target image");
				responseModel.setStatus(false);
			}
			else{
				responseModel.setMessage("Image Comparison success");
				responseModel.setStatus(true);
			}
			return response.getResponse(responseModel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			responseModel.setStatus(false);
			responseModel.setMessage(e.toString());
			responseModel.setStatus(false);
			return response.getResponse(responseModel);
		}
		
		
	}
	public static BufferedImage captureImage(){
		try {
			return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (HeadlessException e) {
			// 
			logger.error(e.toString());
		} catch (AWTException e) {
			logger.error(e.toString());
		};
		return null;
	}
	
	public static BufferedImage cropImage(BufferedImage image,int x,int y,int width,int height){
		return image = image.getSubimage(x, y, width, height);
	}
	
	public static ImageModel saveImage(BufferedImage image) throws IOException{
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		Date date= new Date( );
		SimpleDateFormat dateFormat = new SimpleDateFormat (prop.getProperty("SAVE_IMAGE_NAME"));
		String saveImageFormat = prop.getProperty("SAVE_IMAGE_FORMAT");
		String sourcePath=prop.getProperty("SOURCE_IMAGE_FOLDER")+ dateFormat.format(date).toString()+"."+saveImageFormat;
		ImageModel sourceImage  = new ImageModel();
		//Save Image
		try {
			ImageIO.write(image, saveImageFormat , new File(sourcePath));
		
		//Creating Image Model		
			sourceImage.setFileName(new File(sourcePath));
			sourceImage.setBufferedImage(ImageIO.read(sourceImage.getFileName()));
			sourceImage.setHeight(sourceImage.getBufferedImage().getHeight());
			sourceImage.setWidth(sourceImage.getBufferedImage().getWidth());
			sourceImage.setX(Integer.parseInt(prop.getProperty("X")));
			sourceImage.setY(Integer.parseInt(prop.getProperty("Y")));
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		return sourceImage; 
	}
}
