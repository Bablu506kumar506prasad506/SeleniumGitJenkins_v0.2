package Exe_1;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Global_M.GlobalMethods_1;
import Scenarios__1.TS_21;
import jxl.Sheet;
import jxl.Workbook;

public class TS_21_Exes {

	ExtentReports extend;
	ExtentTest test;

	@BeforeTest
	public void ExtetReport() throws Exception {
		// D:\AutomationTesting\Create4_v2\test-output
		extend = new ExtentReports(System.getProperty("user.dir") + "/MyExtendReport.html", true);
		extend.addSystemInfo("Host Name", "LocalHost");
		extend.addSystemInfo("Environment", "QA");
		extend.addSystemInfo("User Name", "Bablu Kumar");
		extend.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

	}

	@BeforeMethod
	public void beforeMethod() throws Exception {
		
		test = extend.startTest("beforeMethod");
		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Login");

		String URL = r1.getCell(1, 0).getContents();
		String chrome = r1.getCell(1, 2).getContents();
		GlobalMethods_1.LaunchBrowser(chrome, URL);
		test.log(LogStatus.PASS, "Test Case is true");
	}

	@Test(priority = 1)
	public void CRe4_970_971_972_973_977() throws Exception {

		test = extend.startTest("CRe4_970_971_972_973_977");
		TS_21 SearchIECMember = new TS_21();
		SearchIECMember.CRe4_970_971_972_973_977();
//		test.log(LogStatus.INFO, "Test Case is true");

	}

	@Test(priority = 2)
	public void CRe4_974() throws Exception {

		test = extend.startTest("CRe4_974");
		TS_21 SearchIECMember = new TS_21();
		SearchIECMember.CRe4_974();
//		test.log(LogStatus.INFO, "Test Case is true");

	}

	@Test(enabled = false)
	public void CRe4_975() throws Exception {
		test = extend.startTest("CRe4_975");
		TS_21 SearchIECMember = new TS_21();
		SearchIECMember.CRe4_975();
//		test.log(LogStatus.INFO, "Test Case is true");

	}

	@Test(enabled = true)
	public void CRe4_976() throws Exception {
		test = extend.startTest("CRe4_976");
		TS_21 SearchIECMember = new TS_21();
		SearchIECMember.CRe4_976();
//		test.log(LogStatus.INFO, "Test Case is true");

	}

	@Test(enabled = true)
	public void CRe4_978() throws Exception {
		test = extend.startTest("CRe4_978");
		TS_21 SearchIECMember = new TS_21();
		SearchIECMember.CRe4_978();
//		test.log(LogStatus.INFO, "Test Case is true");

	}

//	@BeforeMethod
//	public void registerM(Method method) {
//		String testName = method.getName();
//		test = extend.startTest(testName);
//	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "The test method named as : " + result.getName() + " is pass");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, "The test method named as : " + result.getName() + " is Fail");
			test.log(LogStatus.FAIL, "The test method named as : " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "The test method named as : " + result.getName() + " is Skiped");
		}
		// extend.endTest(test);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		GlobalMethods_1.driver.close();

	}

	@AfterTest
	public void clear() {
		extend.flush();
	}

}
