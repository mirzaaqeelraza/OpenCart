package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // Populates common info on the report
	public ExtentTest test; // Creates test case entries in the report and updates the status of test
							// methods
	String repName;

	// This method runs before the test suite starts
	public void onStart(ITestContext context) {

		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt =new Date(); 
		 * String currentdatetimestamp = df.format(dt);
		 */

		// Creates a unique report name with a timestamp to avoid overwriting previous reports.
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); 
		repName = "Test-Report-" + timestamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // Specify the location of the report

		// ExtentSparkReporter
		// sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +
		// "/report/myReport.html"); // Specify the location of the report

		sparkReporter.config().setDocumentTitle("Opencart Automaton Report"); // Title of the report
		sparkReporter.config().setReportName("OpenCart Functional Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.DARK); // Set the theme of the report

		// ExtentReports
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter); // Attach the reporter to ExtentReports

		// Adding system information to the report
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	// This method runs when a test case passes
	public void onTestSuccess(ITestResult result) {

		// ExtentTest
		test = extent.createTest(result.getName()); // Create a new entry in the report for the passed test
		test.log(Status.PASS, "Test case PASSED: " + result.getName()); // Log the status as PASS/fail/skipped
		test.assignCategory(result.getMethod().getGroups());
	}

	// This method runs when a test case fails
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); // Create a new entry in the report for the failed test
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, "Test case FAILED: " + result.getName()); // Log the status as FAIL
		test.log(Status.INFO, result.getThrowable().getMessage()); // Log the reason for failure (exception/stack trace)

		try {
			String imgpath = new BaseClass().captureSceen(result.getName());
			test.addScreenCaptureFromPath(imgpath); // captureSreen(result.getName())
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	// This method runs when a test case is skipped
	public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());  // Create a new entry in the report for the skipped test
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test case SKIPPED: " + result.getName());  // Log the status as SKIP
        test.log(Status.INFO, result.getThrowable().getMessage());
	}

	// This method runs after all tests in the suite are done
	public void onFinish(ITestContext context) {
		extent.flush(); // Write all test information to the report
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
