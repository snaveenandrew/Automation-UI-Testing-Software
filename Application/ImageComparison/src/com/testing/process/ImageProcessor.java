package com.testing.process;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.json.JSONObject;

import com.testing.model.ImageModel;
import com.testing.util.ImageUtil;

/*
 * Encapsulation of ImageUtil
 */

public class ImageProcessor {
	public JSONObject compareImage(String sourcePath, String targetPath, double accuracy) throws Exception {
		return ImageUtil.compareImage(sourcePath,targetPath,accuracy);
	}
	public JSONObject compareImage(ImageModel source, double accuracy) throws Exception {
		return ImageUtil.compareImage(source, accuracy);
	}
	public BufferedImage captureImage(){
		return ImageUtil.captureImage();
	}
	public BufferedImage cropImage(BufferedImage image,int x,int y,int width,int height){
		return ImageUtil.cropImage(image,x,y,width,height);
	}
	public ImageModel saveImage(BufferedImage image) throws IOException{
		return ImageUtil.saveImage(image);
	}

}