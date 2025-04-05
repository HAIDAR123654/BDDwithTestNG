package org.haidar.cucumberbdd;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	//singleton design pattern
	
	private static final DriverFactory DRIVER_FACTORY = new DriverFactory();
	
	private DriverFactory() {}
	
	public static DriverFactory getInstance() {
		return DRIVER_FACTORY;
	}
	
	//factory design pattern
	
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public void setDriver(WebDriver webDriver) {
		driver.set(webDriver);
	}
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	public void closeDriver() {
		getDriver().close();
		driver.remove();
	}
}
