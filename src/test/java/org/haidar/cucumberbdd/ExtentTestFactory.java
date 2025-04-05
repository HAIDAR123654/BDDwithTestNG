package org.haidar.cucumberbdd;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestFactory {

	   //singleton design pattern
	
		private static ExtentTestFactory EXTENT_TEST_FACTORY = new ExtentTestFactory();
		
		private ExtentTestFactory() {}
		
		public static ExtentTestFactory getInstance() {
			return EXTENT_TEST_FACTORY;
		}
		
		//factory design pattern
		
		private static final ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
		
		public void setExtentTest(ExtentTest extentTest) {
			extent.set(extentTest);
		}
		
		public ExtentTest getExtentTest() {
			return extent.get();
		}
		
		public void removeExtentObjec() {
			extent.remove();
		}
}
