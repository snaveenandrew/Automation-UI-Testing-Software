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

import net.sourceforge.tess4j.*;

public class OCRUtil{
	static Properties prop=new Properties();
	static Logger logger = Logger.getLogger(ImageUtil.class);
	public static JSONObject textExtract(String test_ocr) throws IOException {
		// TODO Auto-generated method stub
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		ResponseModel responseModel = new ResponseModel();
		Response response = new Response();
		File imageFile = new File(prop.getProperty("OCR_IMAGE_PATH"));	
		ITesseract instance = new Tesseract();
		instance.setDatapath(prop.getProperty("TESSERACT_PATH"));
		try{
			String result = instance.doOCR(imageFile);
			boolean retval = result.contains(test_ocr);
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