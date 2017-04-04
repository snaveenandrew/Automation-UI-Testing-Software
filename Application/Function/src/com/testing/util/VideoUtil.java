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

public class VideoUtil {
	static Properties prop=new Properties();
	static Logger logger = Logger.getLogger(VideoUtil.class);
	
	//Scenario 1
	public static JSONObject DetectVideo() throws IOException {
		InputStream input = new FileInputStream("E:\\Final year project\\Function\\config\\config.properties");		
		//InputStream graphData = new FileInputStream(prop.getProperty("GRAPH_DATA"));
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		DataFile d = new DataFile();
		Player p = new Player();
		boolean result;
		result = p.checkVideo();
		ResponseModel responseModel = new ResponseModel();
		Response response = new Response();
		if(result){
			responseModel.setMessage("Video is running");
			responseModel.setStatus(true);
			responseModel.setOutput(100);
			d.prepend("100");
		}
		else{
			responseModel.setMessage("Video is not running");
			responseModel.setStatus(false);
			responseModel.setOutput(0);
			d.prepend("0");
		}
		return response.getResponse(responseModel);
	}
}