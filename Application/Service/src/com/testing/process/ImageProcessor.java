package com.testing.process;

import java.io.IOException;

import org.json.JSONObject;

import com.testing.util.ImageUtil;

public class ImageProcessor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static JSONObject compareImage(String file1,double time,double accuracy) throws IOException {
		System.out.println("Processor");
		return ImageUtil.compareImage(file1,time,accuracy);
	}
}
