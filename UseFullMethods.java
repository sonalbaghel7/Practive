package com.org.utility;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class UseFullMethods {
	private static XSSFWorkbook  wb ;
	private static XSSFSheet sh1;
	private static XSSFRow Row ;
	private static XSSFCell Cell;
	static int numrow;
	static int numcol;
	String[][] FeedbackData;
	public static Object[][] dp11()
	{
		Object[][] dataprovider= GetFeedbackData1();
		return (dataprovider);
		
	}
	
	
	public static  Object[][] GetFeedbackData1()
	{
		 String[][] FeedbackData = null;
	try {
			 //File src=new File("C:\\Users\\shobaghe\\workspace\\NewTestNgProject\\TestData\\TestData.xlsx");
			 // load file
			 FileInputStream fis= new FileInputStream("D:\\SeleniumWorkSpace\\DDTestNg\\TestData\\TestData.xlsx");
		     // load workbook
			 XSSFWorkbook wb = new XSSFWorkbook(fis);
		     // load sheet
			 XSSFSheet sh1= wb.getSheetAt(0);
			  // get number of rows so that we can run loop based on this
			 numrow =  sh1.getLastRowNum();
			 numcol = sh1.getRow(0).getLastCellNum();
			 int startRow = 1;
			 int startCol = 0;
			 int ci,cj;
			 FeedbackData=new String[numrow][numcol];
			 ci=0;
			 for (int i=startRow;i<=numrow;i++, ci++) {           	   
			 cj=0;
				 for (int j=startCol;j<numcol;j++, cj++){
				 FeedbackData[ci][cj]=getCellData1(sh1,i,j);
				 }
			 }
			 
		}catch (Exception e){
			e.printStackTrace();}
			// Return 2d array object so that test script can use the same
			return (FeedbackData);
	}

	public static String getCellData1(XSSFSheet sh1,int RowNum, int ColNum) throws Exception
	{
	try{
			Cell =sh1.getRow(RowNum).getCell(ColNum);
			//System.out.println(sh1.getRow(RowNum).getRowNum());
			//System.out.println(sh1.getRow(RowNum).getCell(ColNum).getColumnIndex());
			int dataType = Cell.getCellType();
			if  (dataType == 3) {
				return "";
			}else{
				String CellData = Cell.getStringCellValue();
				return CellData;
			}
		   	}	
			catch (Exception e){
			System.out.println(e.getMessage());
			throw (e);
			}
		}

}
