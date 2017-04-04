package com.testing.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class DataFile {
	static Properties prop=new Properties();
	public void prepend(String Data) throws IOException{
		InputStream input = new FileInputStream("E:\\Final year project\\Function\\config\\config.properties");
		prop.load(input);
		String path = prop.getProperty("GRAPH_DATA");
		File mFile = new File(path);
		//FileInputStream fis = new FileInputStream(mFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(mFile)));
		String result = "";
		String line="";
		String linebreak = " ";
		while( (line = br.readLine()) != null){
		 result = result + line + linebreak; 
		}
		result = Data + linebreak + result;
		FileWriter fw=new FileWriter(path);
		fw.write(result);
		fw.close();
	}

}
