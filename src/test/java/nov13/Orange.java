package nov13;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
public class Orange {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
DesiredCapabilities cap;
String node="http://localhost:5550/wd/hub";
@Parameters({"browser"})
@BeforeTest
 public void setup(String brw)throws Throwable {
 report=new ExtentReports("./Reports/Dataprovider.html");
if(brw.equalsIgnoreCase("chrome"))
{
System.out.println("Executing on chrome");
cap=new DesiredCapabilities();
cap.setBrowserName("chrome");
cap.setPlatform(Platform.WINDOWS);
driver=new RemoteWebDriver(new URL(node), cap);	
}
else if(brw.equalsIgnoreCase("firefox"))
{
System.out.println("Executing on firefox");
cap.setBrowserName("firefox");	
cap.setPlatform(Platform.WINDOWS);
driver=new RemoteWebDriver(new URL(node), cap);
}
else if(brw.equalsIgnoreCase("ie"))
{
cap.setBrowserName("ie");	
cap.setPlatform(Platform.WINDOWS);
driver=new RemoteWebDriver(new URL(node), cap);
}
  }
  @Test(dataProvider = "supplyData")
  public void verifylogin(String username,String password) 
  {
	  driver.get("http://orangehrm.qedgetech.com/");
	  driver.manage().window().maximize();
  test=report.startTest("Login Test");
 driver.findElement(By.name("txtUsername")).sendKeys(username);
driver.findElement(By.name("txtPassword")).sendKeys(password);
	 driver.findElement(By.name("Submit")).click();
if(driver.getCurrentUrl().contains("dash"))
{
Reporter.log("Login success",true);
test.log(LogStatus.PASS, "Login success");
}
else
{
Reporter.log("Login Fail",true);
test.log(LogStatus.FAIL, "Login Fail");
}
	
  }

  @DataProvider
  public Object[][] supplyData() {
   Object [][] login=new Object[4][2];//no of rows are four and columns are 2
   //first iteration
   login[0][0]="admin";
   login[0][1]="Qedge123!@#";
   //second iteration
   login[1][0]="test";
   login[1][1]="test";
   //third iteration
   login[2][0]="Admin";
   login[2][1]="Qedge123!@#";
   //fourth iteration
   login[3][0]="test1";
   login[3][1]="test3";
   return login;
  }
  

  @AfterTest
  public void afterTest() 
  {
	  report.flush();
		report.endTest(test);
	  driver.quit();
  }

}
