package DriverFactory;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import CommonFunctions.PBFunctions;
import Constants.PBConstant;
import Utilities.ReadExcelUtil;
public class AppTest extends PBConstant {
String TCSheet="TestCases";
String TSSheet="TestSteps";
String inputpath="D:\\4Oclock_Framework\\Maven_Project\\TestInput\\Controller.xlsx";
String outputpath="D:\\4Oclock_Framework\\Maven_Project\\TestOutput\\Keyword.xlsx";
ExtentReports report;
ExtentTest test;
@Test
public void startTest()throws Throwable
{
boolean res=false;
String tcres="";
//to access excel method
ReadExcelUtil xl=new ReadExcelUtil(inputpath);
//count no of rows in TCSheet
int TCCount=xl.rowCount(TCSheet);
//count no of rows in TSSheet
int TSCount=xl.rowCount(TSSheet);
for(int i=1;i<=TCCount;i++)
{
//generate html reports
report=new ExtentReports("./Reports/keyword.html");
//read execute column
String Execute=xl.getCelldata(TCSheet, i, 2);
if(Execute.equalsIgnoreCase("Y"))
{
//read tcid from TC Sheet
String tcid=xl.getCelldata(TCSheet, i, 0);
for(int j=1;j<=TSCount;j++)
{
test=report.startTest("Keyword Framework");	
String description=xl.getCelldata(TSSheet, j, 2);
//read tsid from TS Sheet
String tsid=xl.getCelldata(TSSheet, j, 0);
if(tcid.equalsIgnoreCase(tsid))
{
//read keyword column from TS Sheet
String keyword=xl.getCelldata(TSSheet, j, 3);
if(keyword.equalsIgnoreCase("AdminLogin"))
{
//call login method
res=PBFunctions.verifyLogin("Admin", "Admin");
test.log(LogStatus.INFO, description);
}
else if(keyword.equalsIgnoreCase("NewBranchCreation"))
{
PBFunctions.navigatebranches();	
res=PBFunctions.verifyBranch("ameerpet", "Hyderabad1", "12345", 1, 1, 1);
test.log(LogStatus.INFO, description);
}
else if(keyword.equalsIgnoreCase("UpdateBranch"))
{
PBFunctions.navigatebranches();
res=PBFunctions.verifyBranchupdate("kukatpalli", "kadiri");
test.log(LogStatus.INFO, description);
}
else if(keyword.equalsIgnoreCase("AdminLogout"))
{
res=PBFunctions.verifylogout();
test.log(LogStatus.INFO, description);
}
String tsres="";
if(res)
{
tsres="Pass";
xl.setCellData(TSSheet, j, 4, tsres, outputpath);
xl.greenColour(TSSheet, j, 4, outputpath);
test.log(LogStatus.PASS, description);
}
else
{
tsres="Fail";
xl.setCellData(TSSheet, j, 4, tsres, outputpath);
xl.redColour(TSSheet, j, 4, outputpath);
test.log(LogStatus.FAIL, description);
}
//if not tcres equal to null then write as pass or fail into 
if(!tsres.equalsIgnoreCase("Fail"))
{
tcres=tsres;
}
}
report.flush();
report.endTest(test);
}
xl.setCellData(TCSheet, i, 3, tcres, outputpath);
if(tcres.equalsIgnoreCase("Pass"))
{
xl.greenColour(TCSheet, i, 3, outputpath);
}
else
{
xl.redColour(TCSheet, i, 3, outputpath);
}
}
else
{
//write as not executed into status column into TCSheet
xl.setCellData(TCSheet, i, 3, "Not Executed", outputpath);	
}
}
}
}
















