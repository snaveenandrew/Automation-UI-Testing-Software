package com.testing.test;

public class TestProcessor {	
	public static void main(String[] args) throws Exception {
	
	ImageTest image = new ImageTest();
	image.test();
	
	VideoTest video = new VideoTest();
	video.test();
	
	AudioTest audio = new AudioTest();
	audio.test();
	
	OCRTest OCR = new OCRTest();
	OCR.test();
	}
}
