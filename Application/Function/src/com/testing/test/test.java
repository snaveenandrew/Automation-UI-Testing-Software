package com.testing.test;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class test {
	public void imageTest(String file,double time,double accuracy) throws IOException{
		ImageTest image = new ImageTest();
		image.test(file, time, accuracy);
	}
	public void ocrTest(String file,double time) throws IOException{
		OCRTest ocr = new OCRTest();
		ocr.test(file, time);
		
	}
	public void audioTest() throws IOException{
		AudioTest audio = new AudioTest();
		audio.test();
		
	}
	public void videoTest() throws IOException{
		VideoTest video = new VideoTest();
		video.test();
	}
	static Properties prop=new Properties();
	public void start() throws IOException{
		InputStream input = new FileInputStream("E:\\Final year project\\Function\\config\\config.properties");
		prop.load(input);
		String path = prop.getProperty("GRAPH_DATA");
		FileWriter fw=new FileWriter(path);
		fw.write("");
		fw.close();
	}
	public static void main(String args[]){
		test t = new test();
	}
}

