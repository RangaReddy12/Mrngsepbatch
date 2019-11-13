package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelUtil {
Workbook wb;
FileOutputStream fo;
CellStyle style;
FileInputStream fi;
//to read excel path we write constructor
public ReadExcelUtil(String excelpath)throws Throwable
{
fi=new FileInputStream(excelpath);
wb=WorkbookFactory.create(fi);
}
//count no of rows in sheet
public int rowCount(String sheetname)
{
return wb.getSheet(sheetname).getLastRowNum();	
}
//count no of cell  in rows
public int colCount(String sheetname)
{
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
//reading cell data
public String getCelldata(String sheetname,int row,int column)
{
String data="";
if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
{
int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
 data=String.valueOf(celldata);
}
else
{
data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
}
return data;
}
//writing cell data
public void setCellData(String sheetname,int row,int column,String status,String writeexcel)
throws Throwable{
wb.getSheet(sheetname).getRow(row).createCell(column).setCellValue(status);
fo=new FileOutputStream(writeexcel);
wb.write(fo);
}
//method for green colour
public void greenColour(String sheetname,int row,int column,String writeexcel)
throws Throwable{
style=wb.createCellStyle();
Font font=wb.createFont();
style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
style.setFillPattern(CellStyle.SOLID_FOREGROUND);
font.setBoldweight(Font.BOLDWEIGHT_BOLD);
style.setFont(font);
wb.getSheet(sheetname).getRow(row).getCell(column).setCellStyle(style);
fo=new FileOutputStream(writeexcel);
wb.write(fo);
}
public void redColour(String sheetname,int row,int column,String writeexcel)
throws Throwable{
	style=wb.createCellStyle();
	Font font=wb.createFont();
	style.setFillForegroundColor(IndexedColors.RED.getIndex());
	style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	style.setFont(font);
	wb.getSheet(sheetname).getRow(row).getCell(column).setCellStyle(style);
	fo=new FileOutputStream(writeexcel);
	wb.write(fo);	
}
public void closefile()throws Throwable
{
	fi.close();
	fo.close();
	wb.close();
}
}













