package com.testing.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.testing.model.ResponseModel;

import xuggle.Player;

public class AudioUtil {
	static Properties prop=new Properties();
	static Logger logger = Logger.getLogger(AudioUtil.class);
	
	//Scenario 1
	public static JSONObject DetectAudio() throws IOException {
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		DataFile d = new DataFile();
		Player p = new Player();
		boolean result;
		result = p.checkAudio();
		ResponseModel responseModel = new ResponseModel();
		Response response = new Response();
		if(result){
			responseModel.setMessage("Audio is running");
			responseModel.setStatus(true);
			responseModel.setOutput(100);
			d.prepend("100");
		}
		else{
			responseModel.setMessage("Audio is not running");
			responseModel.setStatus(false);
			responseModel.setOutput(0);
			d.prepend("0");
			
		}
		return response.getResponse(responseModel);
	}
}
