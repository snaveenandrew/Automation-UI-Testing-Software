package com.testing.process;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.testing.util.AudioUtil;

public class AudioProcessor {
	static Logger logger = Logger.getLogger(AudioProcessor.class);
	public static JSONObject DetectAudio() throws IOException {
		Properties prop=new Properties();
		InputStream input = new FileInputStream(System.getProperty("CONFIG_FILE"));
		prop.load(input);
		PropertyConfigurator.configure(prop.getProperty("LOGGER_PATH"));
		logger.info("Audio Detection Service Started...");
		return AudioUtil.DetectAudio();
	}
}
