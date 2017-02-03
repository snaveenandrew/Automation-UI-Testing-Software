package com.testing.process;

import org.json.JSONObject;
import com.testing.util.OCRUtil;

public class OCRProcessor {
	public JSONObject textExtract(String test_ocr) throws Exception {
		return OCRUtil.textExtract(test_ocr);
	}
	
	//compareText
}
