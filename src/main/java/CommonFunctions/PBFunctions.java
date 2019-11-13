package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Constants.PBConstant;

public class PBFunctions extends PBConstant {
	
public static boolean verifyLogin(String username,String password)
throws Throwable{
driver.findElement(By.xpath(p.getProperty("objusername"))).sendKeys(username);
driver.findElement(By.xpath(p.getProperty("objpassword"))).sendKeys(password);
driver.findElement(By.xpath(p.getProperty("objloginbtn"))).click();
Thread.sleep(3000);
String Expval="adminflow";
String Acval=driver.getCurrentUrl();
if(Acval.toLowerCase().contains(Expval.toLowerCase()))
{
Reporter.log("Login success",true);
return true;
}
else
{
Reporter.log("Login Fail",true);
return true;	
}
}
public static void navigatebranches()throws Throwable
{
driver.findElement(By.xpath(p.getProperty("objBranches"))).click();
Thread.sleep(3000);
}
public static boolean verifyBranch(String bname,String add1,
String zcode,int country,int state,int city)
throws Throwable{
driver.findElement(By.xpath(p.getProperty("objnewbranch"))).click();
Thread.sleep(4000);
driver.findElement(By.xpath(p.getProperty("objbname"))).sendKeys(bname);
driver.findElement(By.xpath(p.getProperty("objadd1"))).sendKeys(add1);
driver.findElement(By.xpath(p.getProperty("objzcode"))).sendKeys(zcode);
new Select(driver.findElement(By.xpath(p.getProperty("objcountry")))).selectByIndex(country);
Thread.sleep(3000);
new Select(driver.findElement(By.xpath(p.getProperty("objstate")))).selectByIndex(state);
Thread.sleep(3000);
new Select(driver.findElement(By.xpath(p.getProperty("objcity")))).selectByIndex(city);
Thread.sleep(3000);
driver.findElement(By.xpath(p.getProperty("objsubmit"))).click();
Thread.sleep(3000);
String alertmessage=driver.switchTo().alert().getText();
Reporter.log(alertmessage,true);
Thread.sleep(4000);
driver.switchTo().alert().accept();
Thread.sleep(4000);
String Expval="new branch";
if(alertmessage.toLowerCase().contains(Expval.toLowerCase()))
{
Reporter.log("Branch Created",true);
return true;
}
else
{
Reporter.log("Branch Failed",true);
return false;
}
}
public static boolean verifyBranchupdate(String bname,String address)
throws Throwable{
driver.findElement(By.xpath(p.getProperty("Obj_Click_Edit"))).click();
Thread.sleep(4000);
WebElement branch=driver.findElement(By.xpath(p.getProperty("Obj_Update_Bname")));
branch.clear();
branch.sendKeys(bname);
WebElement add1=driver.findElement(By.xpath(p.getProperty("Obj_Update_Add1")));
add1.clear();
add1.sendKeys(address);
driver.findElement(By.xpath(p.getProperty("Obj_Click_Update"))).click();
Thread.sleep(4000);
String alertmessage1=driver.switchTo().alert().getText();
System.out.println(alertmessage1);
Thread.sleep(4000);
driver.switchTo().alert().dismiss();
Thread.sleep(4000);
String Expval="branch update";
if(alertmessage1.toLowerCase().contains(Expval.toLowerCase()))
{
Reporter.log("Branch Update Success",true);
return true;
}
else
{
Reporter.log("Branch Update FAil",true);
return false;
}
}
public static boolean verifylogout()throws Throwable
{
driver.findElement(By.xpath(p.getProperty("objlogout"))).click();
Thread.sleep(4000);
if(driver.findElement(By.xpath(p.getProperty("objloginbtn"))).isDisplayed())
{
Reporter.log("Logout Success",true);
return true;
}
else
{
Reporter.log("Logout Fail",true);
return false;
}
}
}











