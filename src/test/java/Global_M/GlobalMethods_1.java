package Global_M;

import java.awt.Robot;


import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
/*import org.testng.Assert;*/

import com.mongodb.client.model.FindOptions;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;

public class GlobalMethods_1 {

	public static WebDriver driver;

	public GlobalMethods_1() {

		// this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css=".logo-inner>a>img")
	static WebElement logo;
	
	@FindBy(id = "register_email")
	static

	WebElement email;

	@FindBy(id = "register_password")
	static WebElement pwd;

	@FindBy(id = "register_role")
	static WebElement rol;

	@FindBy(id = "login_institute")
	static WebElement login;

	@FindBy(css = ".logout")
	static WebElement logoutt;

	@FindBy(css = "span.fa.fa-eye")
	static WebElement overview;
	
	@FindBy(css = "label.col-sm-4.control-label")
	static WebElement forgotemailaddressfield;
	
	@FindBy(xpath = "//form[@id='invittee_activation']/div/div[2]/label")
	static WebElement forgotpwdfield;
	
	@FindBy(xpath = "//div/div[3]/div/div/div/div/p")
	static WebElement forgotspwdmsg;
	
	@FindBy(css = "span.fontsize-14.clearfix")
	static WebElement Adminloginname;

	// ---------- Browser Launching Method --------------//

	public static void LaunchBrowser(String browserName, String Url) {
		if (browserName.equals("firefox")) {
			
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Dump\\selenium-java-2.51.0\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("IE")) {
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.get(Url);
	}

	// ------------- Super Admin Login Method-----------//

	// This method is to login as Superadmin
	public static void Super_Login() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute_Logins");

		// Super admin Institute click

		String Super_Institute = r1.getCell(0, 1).getContents();

		String Superadminemail = r1.getCell(1, 1).getContents();
		String Super_Password = r1.getCell(2, 1).getContents();
		String Super_Role = r1.getCell(3, 1).getContents();
		Thread.sleep(6000);

		System.out.println(Super_Institute);
		Thread.sleep(4000);
		driver.findElement(By.linkText(Super_Institute)).click();
		Thread.sleep(4000);
		// driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/ul/li[2]/a")).click();
		/*
		 * Thread.sleep(2000); email.sendKeys(Superadminemail);
		 * 
		 * Thread.sleep(4000); pwd.sendKeys(Super_Password); Thread.sleep(4000);
		 * Select dropdown = new Select(rol); dropdown.selectByValue("8");
		 * login.click();
		 */

		driver.findElement(By.id("register_email")).sendKeys(Superadminemail);
		driver.findElement(By.id("register_password")).sendKeys(Super_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("8");
		driver.findElement(By.id("login_institute")).click();

	}

	// ------------- Principal Investigator Login Method-----------//

	public static void PI_Login() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute_Logins");
		String PI_Institute = r1.getCell(0, 2).getContents();
		String PIemail = r1.getCell(1, 2).getContents();
		String PI_Password = r1.getCell(2, 2).getContents();
		String PI_Role = r1.getCell(3, 2).getContents();

		

		driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);

		driver.findElement(By.linkText(PI_Institute)).click();
		Thread.sleep(4000);
		// driver.findElement(By.linkText("Research approval institute"));
		driver.findElement(By.id("register_email")).sendKeys(PIemail);
		driver.findElement(By.id("register_password")).sendKeys(PI_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("1");
		driver.findElement(By.id("login_institute")).click();

	}
	// ------------- IEC Admin Login Method-----------//

	public static void Admin_Login_update() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute_Logins");

		String Admin_Institute = r1.getCell(0, 13).getContents();
		String Adminemail = r1.getCell(1, 13).getContents();
		String Admin_Password = r1.getCell(2, 13).getContents();
		String Admin_Role = r1.getCell(3, 13).getContents();
		driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
		
		driver.findElement(By.linkText(Admin_Institute)).click();
		Thread.sleep(4000);
		driver.findElement(By.id("register_email")).sendKeys(Adminemail);
		driver.findElement(By.id("register_password")).sendKeys(Admin_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("3");
		driver.findElement(By.id("login_institute")).click();

	}
	
	public static void Admin_Login() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb1 = Workbook.getWorkbook(fi);

		Sheet r1 = wb1.getSheet("Institute_Logins");
		
		String Inst_tite = r1.getCell(0, 3).getContents();
		String admin_email = r1.getCell(1, 3).getContents();
		String Admin_Password = r1.getCell(2, 3).getContents();
		String Admin_Role = r1.getCell(3, 3).getContents();
		driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
		
		driver.findElement(By.linkText(Inst_tite)).click();
		Thread.sleep(4000);
		driver.findElement(By.id("register_email")).sendKeys(admin_email);
		driver.findElement(By.id("register_password")).sendKeys(Admin_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("3");
		driver.findElement(By.id("login_institute")).click();

	}

	// ------------- Member Secretary Login Method-----------//

	public static void MS_Login() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb1 = Workbook.getWorkbook(fi);

		Sheet r1 = wb1.getSheet("Institute_Logins");
		
		String Inst_tite = r1.getCell(0, 2).getContents();
		String MS_email = r1.getCell(1, 2).getContents();
		String MS_Password = r1.getCell(2, 2).getContents();
		String MS_Role = r1.getCell(3, 2).getContents();
		driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);

		//driver.findElement(By.linkText("Research approval institute")).click();
		driver.findElement(By.linkText(Inst_tite)).click();
		Thread.sleep(5000);
		driver.findElement(By.id("register_email")).sendKeys(MS_email);
		driver.findElement(By.id("register_password")).sendKeys(MS_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("5");
		driver.findElement(By.id("login_institute")).click();

	}

	// ------------- Chairperson Login Method-----------//

	public static void CPLogin() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute_Logins");

		String CP_Institute = r1.getCell(0, 5).getContents();
		String CPemail = r1.getCell(1, 5).getContents();
		String CP_Password = r1.getCell(2, 5).getContents();
		String CP_Role = r1.getCell(3, 5).getContents();
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);

		//driver.findElement(By.linkText("Research approval institute")).click();
		driver.findElement(By.linkText(CP_Institute)).click();
		Thread.sleep(4000);
		driver.findElement(By.id("register_email")).sendKeys(CPemail);
		driver.findElement(By.id("register_password")).sendKeys(CP_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("6");
		driver.findElement(By.id("login_institute")).click();

	}

	// ------------- Member Login Method-----------//

	public static void Member1Login() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute_Logins");

		String Mem1_Institute = r1.getCell(0, 6).getContents();
		String Mem1email = r1.getCell(1, 6).getContents();
		String Mem1_Password = r1.getCell(2, 6).getContents();
		String Mem1_Role = r1.getCell(3, 6).getContents();
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);

		//driver.findElement(By.linkText("Research approval institute")).click();
		driver.findElement(By.linkText(Mem1_Institute)).click();
		Thread.sleep(4000);
		driver.findElement(By.id("register_email")).sendKeys(Mem1email);
		driver.findElement(By.id("register_password")).sendKeys(Mem1_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("7");
		driver.findElement(By.id("login_institute")).click();

	}

	public static void Member3Login() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute_Logins");

		String Mem3_Institute = r1.getCell(0, 10).getContents();
		String Mem3email = r1.getCell(1, 10).getContents();
		String Mem3_Password = r1.getCell(2, 10).getContents();
		String Mem3_Role = r1.getCell(3, 10).getContents();
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);

		//driver.findElement(By.linkText("Research approval institute")).click();
		driver.findElement(By.linkText(Mem3_Institute)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("register_email")).sendKeys(Mem3email);
		driver.findElement(By.id("register_password")).sendKeys(Mem3_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("7");
		driver.findElement(By.id("login_institute")).click();

	}

	public static void Member2Login() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute_Logins");

		String Mem2_Institute = r1.getCell(0, 9).getContents();
		String Mem2email = r1.getCell(1, 9).getContents();
		String Mem2_Password = r1.getCell(2, 9).getContents();
		String Mem2_Role = r1.getCell(3, 9).getContents();
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);

		//driver.findElement(By.linkText("Research approval institute")).click();
		driver.findElement(By.linkText(Mem2_Institute)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("register_email")).sendKeys(Mem2email);
		driver.findElement(By.id("register_password")).sendKeys(Mem2_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("7");
		driver.findElement(By.id("login_institute")).click();

	}

	public static void Member4Login() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute_Logins");

		String Mem4_Institute = r1.getCell(0, 11).getContents();
		String Mem4email = r1.getCell(1, 11).getContents();
		String Mem4_Password = r1.getCell(2, 11).getContents();
		String Mem4_Role = r1.getCell(3, 11).getContents();
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);

		//driver.findElement(By.linkText("Research approval institute")).click();
		driver.findElement(By.linkText(Mem4_Institute)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("register_email")).sendKeys(Mem4email);
		driver.findElement(By.id("register_password")).sendKeys(Mem4_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("7");
		driver.findElement(By.id("login_institute")).click();

	}

	// ------------- Alert Accept Method-----------//
//Get text and compare
	public static void alertaccept() throws Exception {

		Alert al = driver.switchTo().alert();
	    String msgalert = al.getText();
		al.accept();

	}
	// ------------- Alert Accept Method with text comparision-----------//
	public static void alertaccept1(String Msg) throws Exception {

		Alert al = driver.switchTo().alert();
	    String msgalert = al.getText();
		al.accept();

	}

	// ------------- Alert decline Method-----------//

	public static void alertdismiss() throws Exception {

		Alert al = driver.switchTo().alert();
		al.dismiss();

	}

	/*// ------------- Logout Method-----------//

	public static void Logout() throws Exception {
		WebElement logoutt = GWait.Wait_GetElementByXpath("//span[3]/a");
		
		logoutt.click();

	}*/

	
	/*// ------------- Logo Method-----------//

			
	public static void logo(){
		WebElement logo = GWait.Wait_GetElementByCSS("img");
		
		logo.click();
	}*/

	// ------------- Window Handler Method-----------//

	public static void Commentswindhandler() throws Exception {

		driver.findElement(By.id("addPopup")).click();

		Thread.sleep(2000);

		String winHandleBefore = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		driver.findElement(By.id("comments")).clear();

		Thread.sleep(2000);
		driver.findElement(By.id("comments")).sendKeys("Test Comments");

		Thread.sleep(3000);

		driver.findElement(By.id("submitforIEC")).click();
		//alertaccept("Comments saved successfully");
		alertaccept();
		String winHandleBefore1 = "";
		driver.switchTo().window(winHandleBefore1);

		// driver.findElement(By.linkText("IEC Decision")).click();
	}

	// ------------- Current Page Get URL Method-----------//

	public static void geturl() throws Exception {

		String url = driver.getCurrentUrl();
		System.out.println(url);
		Thread.sleep(2000);
	}

	// ------------- Institute click on home page Method-----------//

	public static void Inst_click() throws Exception {

		// driver.findElement(By.id(id))
		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute_Creation");

		String Institute_Title_1 = r1.getCell(0, 1).getContents();

		Thread.sleep(2000);

		WebElement i = driver.findElement(By.linkText(Institute_Title_1));
		if (i.isDisplayed()) {
			overview.click();

		} else {
			System.out.println("Value is inactive");
		}

		/*
		 * WebElement myLink =
		 * driver.findElement(By.linkText(Institute_Title_1)); if
		 * (myLink.Exists) { myLink.click(); }
		 */

	}

	public static void AcceptDoenloadPopup() throws Exception {
		Robot robot = new Robot();

		// A short pause, just to be sure that OK is selected
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
	}

	// ------------- Investigator login-----------//

	public static void Investigator_Login() throws Exception {

		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Institute");

		String invest_Institute = r1.getCell(0, 12).getContents();
		String investemail = r1.getCell(1, 12).getContents();
		String invest_Password = r1.getCell(2, 12).getContents();
		String invest_Role = r1.getCell(3, 12).getContents();
		Thread.sleep(4000);

		driver.findElement(By.linkText(invest_Institute)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("register_email")).sendKeys(investemail);
		driver.findElement(By.id("register_password")).sendKeys(invest_Password);
		Select dropdown = new Select(driver.findElement(By.id("register_role")));
		dropdown.selectByValue("2");
		driver.findElement(By.id("login_institute")).click();

	}

	public static void mail_Login() throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"D:\\eclipseProject\\StackOverFlow\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://accounts.google.com");
		// driver.get("https://accounts.google.com/ServiceLogin?");

		// gmail login
		driver.findElement(By.id("identifierId")).clear();
		driver.findElement(By.id("identifierId")).sendKeys("qualeetee112@gmail.com");
		driver.findElement(By.xpath("//div[@id='identifierNext']/content/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='password']/div/div/div/input")).clear();
		driver.findElement(By.xpath("//div[@id='password']/div/div/div/input")).sendKeys("qa@123456");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='passwordNext']/content/span")).click();
		Thread.sleep(6000);

		driver.get("https://mail.google.com/mail/u/0/#inbox");
		

		// now talking un-read email form inbox into a list
		List<WebElement> unreademeil = driver.findElements(By.xpath("//*[@class='zF']"));

		// Mailer name for which i want to check do i have an email in my inbox
		String MyMailer = "e-EC";

		// real logic starts here
		for (int i = 0; i < unreademeil.size(); i++) {
			if (unreademeil.get(i).isDisplayed() == true) {
				// now verify if you have got mail form a specific mailer (Note
				// Un-read mails)
				// for read mails xpath loactor will change but logic will
				// remain same
				if (unreademeil.get(i).getText().equals(MyMailer)) {
					System.out.println("Yes we have got mail form " + MyMailer);
					// also you can perform more actions here
					// like if you want to open email form the mailer
					break;
				} else {
					System.out.println("No mail form " + MyMailer);
				}
			}
		}
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("span.gb_bb.gbii")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("gb_71")).click();
		Thread.sleep(2000);
	}
	
	public static void PIMail(String url, String Email, String Password) throws Exception {
		
		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb1 = Workbook.getWorkbook(fi);

		Sheet r = wb1.getSheet("Emails_Data");
		Sheet r1 = wb1.getSheet("PI_Project_Data");
		Sheet r2 = wb1.getSheet("Login");
		Sheet r3 = wb1.getSheet("Institute_Logins");
		
		String Admin_Role = r3.getCell(3, 1).getContents();
		String URL = r2.getCell(1, 0).getContents();
		String Inst_tite = r.getCell(0, 2).getContents();
		String PI_email = r1.getCell(6, 230).getContents();
		driver.get(url);
	    driver.findElement(By.id("identifierId")).clear();
	    driver.findElement(By.id("identifierId")).sendKeys(Email);
	    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys(Password);
	    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
	    Thread.sleep(60000);
	    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
	    System.out.println("Refresh button Clicked");
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//span/b[text()='e-EC - Approved: Request to Register as Principal Investigator']")).click();
	    Thread.sleep(6000);
	    
	  
	  
	   driver.findElement(By.xpath("//div[starts-with(@class,'a3s aXjCH m1')]/a")).click();  
	   Thread.sleep(6000);
	   
		Thread.sleep(2000);

		String winHandleBefore = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		driver.findElement(By.id("savedoc")).click();
		driver.findElement(By.name("password")).sendKeys("123456admin");
		String Password1=driver.findElement(By.name("password")).getAttribute("Value");
		Thread.sleep(2000);
		driver.findElement(By.id("savedoc")).click();
		Thread.sleep(6000);
		//System.out.println(forgotspwdmsg.getText());
		Thread.sleep(2000);

		String winHandleBefore1 = "";
		driver.switchTo().window(winHandleBefore1);
		
		
		File source= new File("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		FileInputStream input = new FileInputStream(source);
		HSSFWorkbook wb=new HSSFWorkbook(input);
		
		HSSFSheet sheet=wb.getSheetAt(0);
		sheet.getRow(5).createCell(0).setCellValue(Inst_tite);
		sheet.getRow(5).createCell(1).setCellValue(PI_email);
		sheet.getRow(5).createCell(2).setCellValue("123456admin");
		
		FileOutputStream output = new FileOutputStream(source);
		wb.write(output);
		wb.close();
		

		
	}
	
	


	public static void AdminMail(String url, String Email, String Password) throws Exception {
		 
		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb1 = Workbook.getWorkbook(fi);

		Sheet r1 = wb1.getSheet("Emails_Data");
		Sheet r2 = wb1.getSheet("Login");
		Sheet r3 = wb1.getSheet("Institute_Logins");
		
		String Admin_Role = r3.getCell(3, 1).getContents();
		String URL = r2.getCell(1, 0).getContents();
		String Inst_tite = r1.getCell(0, 2).getContents();
		String admin_email = r1.getCell(10, 2).getContents();
		driver.get(url);
		    driver.findElement(By.id("identifierId")).clear();
		    driver.findElement(By.id("identifierId")).sendKeys(Email);
		    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys(Password);
		    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
		    Thread.sleep(30000);
		    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
		    Thread.sleep(10000);
		    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
		    System.out.println("Refresh button Clicked");
		    Thread.sleep(10000);
		   /* System.out.println("Refresh button Clicked");*/
		    Thread.sleep(10000);
		    driver.findElement(By.xpath("//span/b[text()='e-EC - You have been Registered as IEC Admin by e-EC']")).click();
		    Thread.sleep(6000);
		    driver.findElement(By.xpath("//div[starts-with(@class,'a3s aXjCH m1')]/a")).click();  
		    Thread.sleep(6000);
		    Thread.sleep(2000);

			String winHandleBefore = driver.getWindowHandle();

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}


		
            
			Thread.sleep(2000);
			driver.findElement(By.name("activation_password")).sendKeys("123456admin");
			String Password1=driver.findElement(By.name("activation_password")).getAttribute("Value");
			Thread.sleep(2000);
			driver.findElement(By.id("activation_update_id")).click();
			Thread.sleep(6000);
			//System.out.println(forgotspwdmsg.getText());
			Thread.sleep(2000);

			String winHandleBefore1 = "";
			driver.switchTo().window(winHandleBefore1);
			
			File source= new File("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
			FileInputStream input = new FileInputStream(source);
			HSSFWorkbook wb=new HSSFWorkbook(input);
			
			HSSFSheet sheet=wb.getSheetAt(0);
			sheet.getRow(13).createCell(0).setCellValue(Inst_tite);
			sheet.getRow(13).createCell(1).setCellValue(admin_email);
			sheet.getRow(13).createCell(2).setCellValue("123456admin");
			
			FileOutputStream output = new FileOutputStream(source);
			wb.write(output);
			wb.close();
			Thread.sleep(1000);
			
			
		
	}
		
	/*public static void AdminMail_04(String url, String Email, String Password) throws InterruptedException, BiffException, IOException {
		 driver.get(url);
		    driver.findElement(By.id("identifierId")).clear();
		    driver.findElement(By.id("identifierId")).sendKeys(Email);
		    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys(Password);
		    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
		    Thread.sleep(60000);
		    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
		    System.out.println("Refresh button Clicked");
		    Thread.sleep(20000);
		   System.out.println("Refresh button Clicked");
		    Thread.sleep(10000);
		    driver.findElement(By.xpath("//span/b[text()='e-EC - You have been Registered as IEC Admin by e-EC']")).click();
		    Thread.sleep(10000);
		    driver.findElement(By.xpath("//div[starts-with(@class,'a3s aXjCH m1')]/a")).click();  
		    Thread.sleep(6000);
		    Thread.sleep(2000);

		    String winHandleBefore = driver.getWindowHandle();

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}


		
            
			Thread.sleep(2000);
			driver.findElement(By.name("activation_password")).sendKeys("123456admin");
			String Password1=driver.findElement(By.name("activation_password")).getAttribute("Value");
			Thread.sleep(2000);
			driver.findElement(By.id("activation_update_id")).click();
			Thread.sleep(6000);
			//System.out.println(forgotspwdmsg.getText());
			Thread.sleep(2000);

			String winHandleBefore1 = "";
			driver.switchTo().window(winHandleBefore1);
			
			
			FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
			Workbook wb = Workbook.getWorkbook(fi);
			Sheet r1 = wb.getSheet("Institute");

			String Admin_Institute = r1.getCell(0, 3).getContents();
			String Adminemail = r1.getCell(1, 3).getContents();
			String Admin_Password = r1.getCell(2, 3).getContents();
			String Admin_Role = r1.getCell(3, 3).getContents();
			Thread.sleep(4000);

			logo.click();
			
			
			driver.findElement(By.linkText(Admin_Institute)).click();
			driver.findElement(By.id("register_email")).sendKeys(Adminemail);
			driver.findElement(By.id("register_password")).sendKeys("123456admin");
			Select dropdown = new Select(driver.findElement(By.id("register_role")));
			dropdown.selectByValue("3");
			driver.findElement(By.id("login_institute")).click();

			String winHandleBefore1 = "";
			driver.switchTo().window(winHandleBefore1);

			
			
		
	}*/
	
	public static void MS_MemMail(String url, String Email, String Password) throws Exception {
		
		FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		Workbook wb1 = Workbook.getWorkbook(fi);

		Sheet r1 = wb1.getSheet("Emails_Data");
		Sheet r2 = wb1.getSheet("Login");
		Sheet r3 = wb1.getSheet("Institute_Logins");
		
		
		String MS_Role = r3.getCell(3, 2).getContents();
		String URL = r2.getCell(1, 0).getContents();
		String Inst_tite = r1.getCell(0, 2).getContents();
		String MS_email = r1.getCell(3, 4).getContents();
		
		driver.get(url);
	    driver.findElement(By.id("identifierId")).clear();
	    driver.findElement(By.id("identifierId")).sendKeys(Email);
	    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys(Password);
	    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
	    Thread.sleep(60000);
	    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
	    System.out.println("Refresh button Clicked");
	    Thread.sleep(20000);
	    driver.findElement(By.xpath("//span/b[text()='e-EC - You have been Registered as IEC Member by IEC Admin']")).click();
	    Thread.sleep(6000);
	    driver.findElement(By.xpath("//div[starts-with(@class,'a3s aXjCH m1')]/a")).click();  
		Thread.sleep(6000);
		
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		Thread.sleep(4000);
		driver.findElement(By.name("activation_password")).sendKeys("123456admin");
		String Password1=driver.findElement(By.name("activation_password")).getAttribute("Value");
		Thread.sleep(2000);
		driver.findElement(By.id("activation_update_id")).click();
		Thread.sleep(6000);
		//System.out.println(forgotspwdmsg.getText());
		Thread.sleep(2000);
		
		/*driver.findElement(By.xpath("//form[@id='activation_user_form']/div/div[2]/div/input")).sendKeys("123456admin");
		String Password1=driver.findElement(By.xpath("//div[starts-with(@class,'a3s aXjCH m15f')]/a")).getAttribute("Value");
		Thread.sleep(2000);
		driver.findElement(By.id("savedoc")).click();*/
		String winHandleBefore1 = "";
		driver.switchTo().window(winHandleBefore1);
		
		File source= new File("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
		FileInputStream input = new FileInputStream(source);
		HSSFWorkbook wb=new HSSFWorkbook(input);
		
		HSSFSheet sheet=wb.getSheetAt(0);
		sheet.getRow(2).createCell(0).setCellValue(Inst_tite);
		sheet.getRow(2).createCell(1).setCellValue(MS_email);
		sheet.getRow(2).createCell(2).setCellValue("123456admin");
		
		FileOutputStream output = new FileOutputStream(source);
		wb.write(output);
		wb.close();
		Thread.sleep(1000);
		driver.get(URL);
		
		Thread.sleep(1000);
	    MS_Login();
//	    Assert.assertEquals(Adminloginname.getText().trim(), MS_Role);
	    Thread.sleep(4000);
	    logoutt.click();
		
		
	}
	
	
public static void forgotMail(String url, String Email, String Password) throws InterruptedException {
		
		driver.get(url);
	    driver.findElement(By.id("identifierId")).clear();
	    driver.findElement(By.id("identifierId")).sendKeys(Email);
	    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys(Password);
	    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
	    Thread.sleep(6000);
	    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
	    System.out.println("Refresh button Clicked");
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//span/b[text()='Your Password Reset Link']")).click();
	    Thread.sleep(20000);
	    //driver.findElement(By.xpath("//div[starts-with(@class,'a3s aXjCH m15f')]div[2]/div[2]/a/a")).click();  
	    driver.findElement(By.xpath("//div[starts-with(@class,'a3s aXjCH m1')]/div[2]/div[2]/a")).click();  
	    Thread.sleep(20000);
		
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
         Thread.sleep(1000);
		 driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		  
		 assertEquals(driver.findElement(By.id("processing_fee_paid_error")).getText(), "The email field is required.");
		   assertEquals(driver.findElement(By.xpath("//form/div[2]/div")).getText(), "The password field is required.");
		   Thread.sleep(1000);
		  driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys("qualeetee112+23@gmail.com");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("123456admin");
		    driver.findElement(By.name("password_confirmation")).clear();
		    driver.findElement(By.name("password_confirmation")).sendKeys("123456admin");
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    Thread.sleep(2000);
		    
		    
		 
		
		
		
		String winHandleBefore1 = "";
		driver.switchTo().window(winHandleBefore1);
		
	}
		
		
		
	
	

private static void assertEquals(String text, String string) {
	// TODO Auto-generated method stub
	
}

public static void InvestigatorMail(String url, String Email, String Password) throws InterruptedException, BiffException, IOException {
	
	FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
	Workbook wb1 = Workbook.getWorkbook(fi);

	Sheet r1 = wb1.getSheet("Emails_Data");
	Sheet r2 = wb1.getSheet("Login");
	Sheet r3 = wb1.getSheet("Institute_Logins");
	Sheet r4 = wb1.getSheet("PI_Project_Data");
	
	String Inv_Role = r3.getCell(3, 6).getContents();
	String URL = r2.getCell(1, 0).getContents();
	String Inst_tite = r1.getCell(0, 2).getContents();
	String studyteammem_email = r4.getCell(2, 16).getContents();
	
	driver.get(url);
    driver.findElement(By.id("identifierId")).clear();
    driver.findElement(By.id("identifierId")).sendKeys(Email);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(60000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    System.out.println("Refresh button Clicked");
    Thread.sleep(10000);
    driver.findElement(By.xpath("//span/b[text()='You have been invited for the project']")).click();
    Thread.sleep(10000);
    driver.findElement(By.xpath("//div[starts-with(@class,'a3s aXjCH m1')]/a")).click();  
	Thread.sleep(6000);
	
	for (String winHandle : driver.getWindowHandles()) {
		driver.switchTo().window(winHandle);
	}

	Thread.sleep(4000);
	driver.findElement(By.name("activation_password")).sendKeys("123456admin");
	String Password1=driver.findElement(By.name("activation_password")).getAttribute("Value");
	Thread.sleep(2000);
	driver.findElement(By.id("activation_update_id")).click();
	Thread.sleep(6000);
	//System.out.println(forgotspwdmsg.getText());
	Thread.sleep(2000);
	
	/*driver.findElement(By.xpath("//form[@id='activation_user_form']/div/div[2]/div/input")).sendKeys("123456admin");
	String Password1=driver.findElement(By.xpath("//div[starts-with(@class,'a3s aXjCH m15f')]/a")).getAttribute("Value");
	Thread.sleep(2000);
	driver.findElement(By.id("savedoc")).click();*/
	String winHandleBefore1 = "";
	driver.switchTo().window(winHandleBefore1);
	
	File source= new File("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
	FileInputStream input = new FileInputStream(source);
	HSSFWorkbook wb=new HSSFWorkbook(input);
	
	HSSFSheet sheet=wb.getSheetAt(0);
	sheet.getRow(2).createCell(0).setCellValue(Inst_tite);
	sheet.getRow(2).createCell(1).setCellValue(studyteammem_email);
	sheet.getRow(2).createCell(2).setCellValue("123456admin");
	
	FileOutputStream output = new FileOutputStream(source);
	wb.write(output);
	wb.close();

	
	
}
	
	
	


public static void meetingMail(String url, String Email, String Password) throws InterruptedException {
	
	driver.get(url);
    driver.findElement(By.id("identifierId")).clear();
    driver.findElement(By.id("identifierId")).sendKeys(Email);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(60000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    System.out.println("Refresh button Clicked");
    Thread.sleep(20000);
    driver.findElement(By.xpath("//span/b[text()='e-EC - Invitation for Upcoming IEC Meeting']")).click();
    Thread.sleep(6000);
    Thread.sleep(2000);
   /*	driver.findElement(By.cssSelector("span.gb_bb.gbii")).click();
   	Thread.sleep(2000);
   	driver.findElement(By.id("gb_71")).click();
   	Thread.sleep(2000);*/
   
	
	
	
}

public static void remeetingMail(String url, String Email, String Password) throws InterruptedException {
	
	driver.get(url);
    driver.findElement(By.id("identifierId")).clear();
    driver.findElement(By.id("identifierId")).sendKeys(Email);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(60000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    System.out.println("Refresh button Clicked");
    Thread.sleep(20000);
    driver.findElement(By.xpath("//span/b[text()='e-EC - Update in Invitation for the Upcoming IEC Meeting']")).click();
    Thread.sleep(6000);
    Thread.sleep(2000);
   	/*driver.findElement(By.cssSelector("span.gb_bb.gbii")).click();
   	Thread.sleep(2000);
   	driver.findElement(By.id("gb_71")).click();
   	Thread.sleep(2000);
   
   	for (String winHandle : driver.getWindowHandles()) {
		driver.switchTo().window(winHandle);
	}

	
	String winHandleBefore1 = "";
	driver.switchTo().window(winHandleBefore1);*/
	
	
}

public static void msassignreviewerMail(String url, String Email, String Password) throws InterruptedException {
	
	driver.get(url);
    driver.findElement(By.id("identifierId")).clear();
    driver.findElement(By.id("identifierId")).sendKeys(Email);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(60000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    System.out.println("Refresh button Clicked");
    Thread.sleep(20000);
    driver.findElement(By.xpath("//span/b[text()='e-EC - Project for Review']")).click();
    Thread.sleep(6000);
    Thread.sleep(2000);
	/*driver.findElement(By.cssSelector("span.gb_bb.gbii")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("gb_71")).click();
	Thread.sleep(2000);*/
   
	
	
	
}

public static void msreassignreviewerMail(String url, String Email, String Password) throws InterruptedException {
	
	driver.get(url);
    driver.findElement(By.id("identifierId")).clear();
    driver.findElement(By.id("identifierId")).sendKeys(Email);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(60000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    System.out.println("Refresh button Clicked");
    Thread.sleep(20000);
    driver.findElement(By.xpath("//span/b[text()='e-EC - Project re-assigned for Review']")).click();
    Thread.sleep(6000);
    Thread.sleep(2000);
	/*driver.findElement(By.cssSelector("span.gb_bb.gbii")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("gb_71")).click();
	Thread.sleep(2000);*/
	

	
	
	
}


public static void MsdecisionrMailLOP(String url, String Email, String Password) throws InterruptedException {
	
	driver.get(url);
    driver.findElement(By.id("identifierId")).clear();
    driver.findElement(By.id("identifierId")).sendKeys(Email);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(60000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    System.out.println("Refresh button Clicked");
    Thread.sleep(20000);
    driver.findElement(By.xpath("//span/b[text()='Letter of Permission issued by Ethics Committee']")).click();
    Thread.sleep(6000);
    Thread.sleep(2000);
	/*driver.findElement(By.cssSelector("span.gb_bb.gbii")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("gb_71")).click();
	Thread.sleep(2000);
	*/
	
	
}


public static void MsdecisionrMailreturned(String url, String Email, String Password) throws InterruptedException {
	
	driver.get(url);
    driver.findElement(By.id("identifierId")).clear();
    driver.findElement(By.id("identifierId")).sendKeys(Email);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.cssSelector("span.RveJvd.snByac")).click();
    Thread.sleep(60000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector(".asf.T-I-J3.J-J5-Ji")).click();
    System.out.println("Refresh button Clicked");
    Thread.sleep(20000);
    driver.findElement(By.xpath("//span/b[text()='e-EC - Project Returned by IEC Admin']")).click();
    Thread.sleep(6000);
    Thread.sleep(2000);
	/*driver.findElement(By.cssSelector("span.gb_bb.gbii")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("gb_71")).click();
	Thread.sleep(2000);*/
	
	
	
}
// ------------- Institute click on home page Method-----------//

public static void ActiveInstitute() throws Exception{
	
	
	FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
	Workbook wb = Workbook.getWorkbook(fi);
	Sheet r1 = wb.getSheet("Institute_Creation1");
	
	String Institute_Title_1 = r1.getCell(2,19).getContents();
	
	Thread.sleep(2000);
	
	WebElement i = driver.findElement(By.linkText(Institute_Title_1));
	 if(i.isDisplayed())
	 { 
		 System.out.println("Inside if");
		 Thread.sleep(4000);
		 i.click();
		 
		 
	 }else
	 {
//	 Assert.assertEquals("X","Y");
	 }
	 
}



	

public static void DeActiveInstitute() throws Exception{


	FileInputStream fi = new FileInputStream("C:\\Selenium_Files\\Create4_v2\\CReATE4_Data.xls");
	Workbook wb = Workbook.getWorkbook(fi);
	Sheet r1 = wb.getSheet("Institute_Creation1");

	String Institute_Title_1 = r1.getCell(2,21).getContents();

	Thread.sleep(2000);	
	
	WebElement e = null;
    try {
        e = driver.findElement(By.linkText(Institute_Title_1));
    } catch (NoSuchElementException e1) {
    	
    }
//   Assert.assertFalse(e != null);
	
}



public static void Screenshot(String error){

try {
TakesScreenshot st = (TakesScreenshot)driver;
File Source = st.getScreenshotAs(OutputType.FILE);
org.apache.commons.io.FileUtils.copyFile(Source, new File("./ScreenShots/"+error+".png"));
} catch (Exception e) {
// TODO Auto-generated catch block
System.out.println("Exception while taking screen shot"+e.getMessage());
}
}

public static void deleteAllCookiesExample() throws Exception
{
	driver.manage().deleteAllCookies();
	Thread.sleep(5000); //wait 5 seconds to clear cookies.
}

public static void clearBrowserCache() {
	driver.manage().deleteAllCookies();
	
	driver.navigate().to("file:///c:/tmp/ClearCacheFirefox.html");
}


}