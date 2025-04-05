package org.haidar.cucumberbdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


@CucumberOptions(
		features = "src/test/resources/featuresToBeRunInParallel", 
		glue = "org.haidar.cucumberbdd", 
		tags = "@TestNG",
		plugin = {"pretty", "rerun:target/failed.txt" })

public class TestNGRunner extends AbstractTestNGCucumberTests {

    BrowserFactory bf = new BrowserFactory();
	
    public WebDriver driver;
    
    public static ExtentReports extent;
    
    public ExtentTest test; 
    
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("I am in before suite");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("I am in after suite");
	}
	
	@BeforeClass
 	public static ExtentReports setUpExtentReport() {
 		
 		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
 		Date date = new Date();
 		String ActualDate = format.format(date);
 		
 		String reportPath = System.getProperty("user.dir")+
 				"/Reports/ExecutionReport_" +ActualDate+".html";
 		
 		ExtentSparkReporter spartReport = new ExtentSparkReporter(reportPath);
 		extent = new ExtentReports();
 		extent.attachReporter(spartReport);
 		
 		spartReport.config().setDocumentTitle("DocumentTitle");
 		spartReport.config().setTheme(Theme.DARK);
 		spartReport.config().setReportName("ReportName");
 		
 		extent.setSystemInfo("Executed on Environment: ", "url");
 		extent.setSystemInfo("Executed on Browser: ", "Chrome");
 		extent.setSystemInfo("Executed by OS: ", System.getProperty("os.name"));
 		extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
 		
 		return extent;
 		
 	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("I am in after class");
	}
}
