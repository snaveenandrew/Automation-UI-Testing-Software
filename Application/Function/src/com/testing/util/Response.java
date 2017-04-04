package com.testing.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONException;
import org.json.JSONObject;

import com.testing.model.ResponseModel;


public class Response {
	
	JSONObject response=new JSONObject();
	Properties prop=new Properties();
	static Logger logger = Logger.getLogger(Response.class);
	public JSONObject getResponse(ResponseModel model){
		try {
			InputStream input = new FileInputStream("E:\\Final year project\\Function\\config\\config.properties");
			prop.load(input);
			PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
			response.put(prop.getProperty("RESULT_STATUS"), model.getStatus());
			response.put(prop.getProperty("RESULT_DESC"), model.getMessage());
			response.put(prop.getProperty("RESULT_VALUE"), model.getOutput());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
