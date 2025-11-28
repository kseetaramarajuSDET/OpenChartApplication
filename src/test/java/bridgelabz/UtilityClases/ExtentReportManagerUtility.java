package bridgelabz.UtilityClases;

import java.awt.Desktop;
import java.io.File;
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

import bridgelabz.PageClasses.BasePage;
import bridgelabz.TestClasses.BaseTest;

public class ExtentReportManagerUtility implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext)
	{

		String timeStamp= new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());

		repName="TestReport"+timeStamp+".html";

		sparkReporter=new ExtentSparkReporter("./reports/"+repName);

		sparkReporter.config().setDocumentTitle("OpenCart Autmation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);


		extentReports=new ExtentReports();
		extentReports.attachReporter(sparkReporter);

		extentReports.setSystemInfo("Application","Open Cart");
		extentReports.setSystemInfo("Module","Admin");
		extentReports.setSystemInfo("Sub-Module","Customers");
		extentReports.setSystemInfo("UserName",System.getProperty("user.name"));
		extentReports.setSystemInfo("Environment","QA");


		String os = testContext.getCurrentXmlTest().getParameter("os");
		String browser = testContext.getCurrentXmlTest().getParameter("browser");

		extentReports.setSystemInfo("Operating System",os);
		extentReports.setSystemInfo("Browser",browser);


		List<String> groups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!groups.isEmpty())
		{
			extentReports.setSystemInfo("Groups",groups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		test=extentReports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+"Got Successfully Executed ");
	}


	public void onTestFailure(ITestResult result) {
		test=extentReports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL,result.getName()+"Got Failed ");
		test.log(Status.INFO,result.getThrowable().getMessage());

		try {
			String imgPath= BaseTest.captureScreenShot(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {

		test=extentReports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP,result.getName()+"Got Skipped ");
		test.log(Status.INFO,result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext context) {
        extentReports.flush();
        
        String pathOfExtendReport=System.getProperty("user.dir")+"/reports/"+repName;
        File extentReport=new File(pathOfExtendReport);
        
        try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	



}
