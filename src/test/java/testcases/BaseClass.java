package testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadConfig;

public class BaseClass {
	
	//to get values of url, browser from ReadConfig class we have to create object of ReadConfig
	   ReadConfig readConfig = new ReadConfig();
	 
		String browser = readConfig.getBrowser();
		String url = readConfig.getBaseUrl();
		String username= readConfig.getUsername();
		String password=readConfig.getPassword();
		public static WebDriver driver;
		public static Logger logger;
		
		//Launch Browser
		@BeforeClass
		public void setup() {
		
			switch(browser.toLowerCase())
			{
		
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;

			case "msedge":
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
				break;
			
			default: 
				driver = null;
			break;
			}
			
			
			//import implicit waits
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
			
			//for logging
			logger = LogManager.getLogger("SwagLabs");
			
			//Navigate to url
			driver.get(url);
			
			
		}

		@AfterClass
		public void tearDown() {
			driver.close();
			driver.quit();
}
		//user method to capture screen shot
		public void captureScreenShot(WebDriver driver,String testName) throws IOException
		{
			//step1: convert webdriver object to TakesScreenshot interface
			TakesScreenshot screenshot = ((TakesScreenshot)driver);
			
			//step2: call getScreenshotAs method to create image file
			
			File src = screenshot.getScreenshotAs(OutputType.FILE);
			
			File dest = new File(System.getProperty("user.dir") + "//Screenshots//" + testName + ".png");
		
			//step3: copy image file to destination
			FileUtils.copyFile(src, dest);
		}
}
