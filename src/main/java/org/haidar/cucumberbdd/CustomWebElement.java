package org.haidar.cucumberbdd;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebElement;

public class CustomWebElement {
    private WebElement element;
    private ExtentTest test;

    public CustomWebElement(WebElement element, ExtentTest test) {
        this.element = element;
        this.test = test;
    }

    public void click() {
        test.log(Status.INFO, "Clicking on element: " + element.toString());
        element.click();
    }

    public void sendKeys(String text) {
    	System.out.println("Logging: Entering text '" + text + "' into element: " + element.toString());
        test.log(Status.INFO, "Entering text '" + text + "' into element: " + element.toString());
        element.sendKeys(text);
    }

    public WebElement getElement() {
        return element;
    }
}
