package com.testing.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.testing.model.ResponseModel;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import xuggle.Player;

public class OCRUtil {
	static Properties prop=new Properties();
	static Logger logger = Logger.getLogger(OCRUtil.class);

	public static JSONObject checkString(String name, double time, double accuracy) throws IOException {
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		Player player = new Player();
		//System.out.println("Capture");
		String path = player.capture(time);
		System.out.println(path);
		ResponseModel responseModel = new ResponseModel();
		Response response = new Response();
		File imageFile = new File(path);
		//System.out.println("Tess");
		ITesseract instance = new Tesseract();
		instance.setDatapath(prop.getProperty("TESSERACT_PATH"));
		try{
			String result = instance.doOCR(imageFile);
			//System.out.println(result);
			boolean retval = result.toLowerCase().contains(name.toLowerCase());
			if(retval){
			responseModel.setOutput(100);
			responseModel.setStatus(true);
			responseModel.setMessage(prop.getProperty("OCR_SUCCESS"));
			}
			else{
				responseModel.setOutput(0);
				responseModel.setStatus(false);
				responseModel.setMessage(prop.getProperty("OCR_FAIL"));	
			}
		}
		catch(TesseractException e){
			responseModel.setOutput(0);
			responseModel.setStatus(false);
			responseModel.setMessage(e.toString());
		}
		
		return response.getResponse(responseModel);
	}
}
