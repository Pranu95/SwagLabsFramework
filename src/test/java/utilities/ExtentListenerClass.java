package utilities;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener{
	ExtentSparkReporter  htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	public void configureReport() {
	ReadConfig readConfig = new ReadConfig();
	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date(0));
	String reportName = "MyStoreTestReport-" + timestamp + ".html";
	
	//Intialize object 
	htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//" + reportName);
	reports = new ExtentReports();
	reports.attachReporter(htmlReporter);
	
	
	//Intialize object 
	htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//" + reportName);
	reports = new ExtentReports();
	reports.attachReporter(htmlReporter);
	
	//add system information/environment info to reports
	reports.setSystemInfo("Machine:", "DESKTOP-U09UDJ5");
	reports.setSystemInfo("OS", "Windows 10 Pro");
	reports.setSystemInfo("browser:", readConfig.getBrowser());
	
	//configuration to change look and feel of report
	htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
	htmlReporter.config().setReportName("This is my First Report");
	htmlReporter.config().setTheme(Theme.DARK);
	}

	
	//Implements ItestListner methods
	//OnStart method is called when any Test starts.
			public void onStart(ITestContext Result)					
			{		
				configureReport();
				System.out.println("On Start method invoked....");  		
			}	

			//onFinish method is called after all Tests are executed
			public void onFinish(ITestContext Result) 					
			{		
				System.out.println("On Finished method invoked....");  	
				reports.flush();//it is mandatory to call flush method to ensure information is written to the started reporter.

			}		

			// When Test case get failed, this method is called.		

			public void onTestFailure(ITestResult Result) 					
			{		
				System.out.println("Name of test method failed:" + Result.getName() );  		
				test = reports.createTest(Result.getName()); //create failed test cases entries in html report
				test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: " + Result.getName() ,ExtentColor.RED));
			
				//call method addscreencapturedfrompath()
				//the method is avaialble in Extent test class
				//so here we are using object of Etent Test class i.e test to call addscreencapturedfrompath()
				
				
				//given path for screenshot folder
				// from this System.getProperty("user.dir") we get path till project name MyStoreV2 then add screenshot folder
			String screenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + Result.getName() + ".png";
			
			//get file from specified path
			File screenShotFile = new File(screenShotPath);
			
			//verify file is there or not 
			if(screenShotFile.exists())
			{
				test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));
				
			}
			
			
			//	test.addScreenCaptureFromPath(null)
				
			}		

			// When Test case get Skipped, this method is called.		
			//ITestResultclass= describes results of the test so we are using
			public void onTestSkipped(ITestResult Result)					
			{		
				System.out.println("Name of test method skipped:" + Result.getName() );  		

				test = reports.createTest(Result.getName()); //create skipped test cases entries in html report
				test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skip test case is: " + Result.getName() ,ExtentColor.YELLOW));
			}			

			// When Test case get Started, this method is called.		

			public void onTestStart(ITestResult Result)					
			{		
				System.out.println("Name of test method started:" + Result.getName() );  		

			}		

			// When Test case get passed, this method is called.		

			public void onTestSuccess(ITestResult Result)					
			{		
				System.out.println("Name of test method sucessfully executed:" + Result.getName() );  		

				test = reports.createTest(Result.getName());
				test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed test case is: " + Result.getName() ,ExtentColor.GREEN));
			}		


			public void onTestFailedButWithinSuccessPercentage(ITestResult Result)					
			{		

			}		


}
