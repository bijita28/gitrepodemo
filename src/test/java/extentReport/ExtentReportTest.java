package extentReport;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ExtentReportTest {
	
	WebDriver driver;
	ExtentReports extent;
	
	
	@BeforeMethod
	public void configuration()
	{
		String reportPath=System.getProperty("user.dir")+"//reports//index.html";
		System.out.println(reportPath);
		
		ExtentSparkReporter reporter=new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Omayo Test Report");
		reporter.config().setDocumentTitle("Omayo Test Report Title");
		
		extent=new ExtentReports();
		
		extent.attachReporter(reporter);

		extent.setSystemInfo("Operating System", "Mac OS");	
		extent.setSystemInfo("Tested By", "Bijita");
			
	}
	
	
	@Test
	public void DemoRun()
	{
		ExtentTest etest = extent.createTest("Test One Created");
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
		etest.info("Chrome Browser Launched");
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://omayo.blogspot.com/");
		etest.info("Navigated to Omayo Home page");
		Assert.assertEquals(driver.findElement(By.id("pah")).getText(), "PracticeAutomationHere");
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		
		driver.close();
		extent.flush();
		
		
	}
	
	

}
