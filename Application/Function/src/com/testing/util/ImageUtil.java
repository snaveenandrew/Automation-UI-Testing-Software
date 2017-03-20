package com.testing.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.testing.model.FileMeta;
import com.testing.model.ImageModel;
import com.testing.model.ResponseModel;

import xuggle.Player;


public class ImageUtil {
	static Properties prop=new Properties();
	static Logger logger = Logger.getLogger(ImageUtil.class);
	
	//Scenario 1
	public static JSONObject compareImage(String sourcePath, double time, double accuracy) throws IOException {
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		
		double result = 0.0;
		int sourceFileError = -4,targetFileError=-4;
		ResponseModel responseModel = new ResponseModel();
		ImageModel sourceImage  = new ImageModel();
		ImageModel targetImage = new ImageModel();
		DataFile d = new DataFile();
		FileUtil check = new FileUtil();
		Response response = new Response();
		String errorCode="FILE_ERROR_";
		
		Player player = new Player();
		String targetPath = player.capture(15);
		//System.out.println(targetPath);
		sourcePath = prop.getProperty("IMAGE_DIR")+sourcePath;
		try{
			FileMeta fileMeta = new FileMeta(sourcePath);
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
				sourceImage.setBufferedImage(ImageIO.read(sourceImage.getFileName()));
				targetImage.setBufferedImage(ImageIO.read(targetImage.getFileName()));
		
				targetImage.setBufferedImage(cropImage(targetImage.getBufferedImage(),(int)fileMeta.getX(),(int)fileMeta.getY(),(int)fileMeta.getWidth(),(int)fileMeta.getHeight()));			
//DELETE AFTER SETTING SAVE PATH TO UI
				sourceImage.setBufferedImage(cropImage(sourceImage.getBufferedImage(),(int)fileMeta.getX(),(int)fileMeta.getY(),(int)fileMeta.getWidth(),(int)fileMeta.getHeight()));
//##################				
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
			d.prepend(String.valueOf(result));
		}
		catch (Exception fileException ) {
			// TODO Auto-generated catch block
			responseModel.setOutput(result);
			responseModel.setMessage(prop.getProperty(errorCode));
			responseModel.setStatus(false);
			d.prepend("0");
			return response.getResponse(responseModel);
		}
		return response.getResponse(responseModel);
	}
	
	public static BufferedImage cropImage(BufferedImage image,int x,int y,int width,int height){
		return image = image.getSubimage(x, y, width, height);
	}
}
