import java.io.IOException;

import com.testing.test.AudioTest;
import com.testing.test.ImageTest;
import com.testing.test.OCRTest;
import com.testing.test.VideoTest;

public class testScript {
	public static void main(String args[]) throws IOException{
		ImageTest imageTest = new ImageTest();
		OCRTest ocrTest = new OCRTest();
		AudioTest audioTest = new AudioTest();
		VideoTest videoTest = new VideoTest();
		imageTest.test("hai_100_100_100_100.png", 100, 75);
		ocrTest.test("SCHOOL", 100, 75);
		audioTest.test();
		videoTest.test();
		
		imageTest.test("hai_100_100_100_100.png", 100, 75);
		ocrTest.test("SCHOOL", 100, 75);
		audioTest.test();
		videoTest.test();
		
		imageTest.test("hai_100_100_100_100.png", 100, 75);
		ocrTest.test("SCHOOL", 100, 75);
		audioTest.test();
		videoTest.test();
		
		imageTest.test("hai_100_100_100_100.png", 100, 75);
		ocrTest.test("SCHOOL", 100, 75);
		audioTest.test();
		videoTest.test();
		
		imageTest.test("hai_100_100_100_100.png", 100, 75);
		ocrTest.test("SCHOOL", 100, 75);
		audioTest.test();
		videoTest.test();
	}
}
