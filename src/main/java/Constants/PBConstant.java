package Constants;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBConstant {
public static WebDriver driver;
public static Properties p;
public static FileInputStream fi;
@BeforeMethod
public void setup()throws Throwable
{
p=new Properties();
fi=new FileInputStream("D:\\4Oclock_Framework\\Maven_Project\\PropertyFile\\Enviroment.properties");
p.load(fi);
if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
{
System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
driver=new ChromeDriver();
driver.get(p.getProperty("objurl"));
driver.manage().window().maximize();
}
else if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
{
System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
driver=new ChromeDriver();
driver.get(p.getProperty("objurl"));	
}
else 
{
System.out.println("browser is not matching");	
}
}
@AfterMethod
public void tearDown()
{
	driver.close();
}
}
















