package org.haidar.cucumberbdd;

import com.aventstack.extentreports.Status;

public class TestBaseforFailedTestRunner extends FailedTestRunner{

    public void setup() {
    	DriverFactory.getInstance().setDriver(bf.getWebDriverInstance("chrome"));
    	driver = DriverFactory.getInstance().getDriver();
        driver.manage().window().maximize();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

    // Utility method to log actions
    public void logStep(String stepDescription) {
        test.log(Status.INFO, stepDescription);
    }
}
