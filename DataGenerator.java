package com.michaelpage.utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import com.michaelpage.utility.*;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataGenerator {
	private static Workbook  workBook ;
	private static Sheet sheet;
	private static Row row ;
	private static Cell cell;
	//private ArrayList<String> FeedbackData;
	public static Object[][] returnValue;
	@DataProvider(name = "TestData")
	public static  Object[][] GetTestData(Method method)
	{
		ArrayList<Object> testData = new ArrayList<Object>();
		String testMethodName = method.getName();
		//String testMethodName = method.getConstructorOrMethod().getMethod().getName();
		try {
				String fileName = "D:\\SeleniumWorkSpace\\DDTestNg\\testData\\TestData.xlsx";
				// load excel file
				FileInputStream fis= new FileInputStream(fileName);
				//Load Workbook weather if it's a xls or xlsx file
				if (fileName.toLowerCase().endsWith("xlsx") == true) {
					workBook = new XSSFWorkbook(fis);
				} else if (fileName.toLowerCase().endsWith("xls") == true) {
					workBook = new HSSFWorkbook(fis);
				}
				// load sheet based on the name of the @Test method provided by variable - testMethod
				sheet = workBook.getSheet(testMethodName);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
				// get number of rows and columns so that we can run loop based on this
				int numrow =  sheet.getLastRowNum();
				int numcol = sheet.getRow(0).getLastCellNum();
				int startRow = 1;
				int startCol = 0;
				//Read the data from excel sheet only for which rows RunMode column value is Y 
				//and assign the values to arrayLisy object
				int modifyrow = 0;
				for (int i=startRow;i<=numrow;i++) {
					String runMode = getCellData(sheet,i,0);
					if(runMode.equalsIgnoreCase("Y"))
					{
						for (int j=startCol;j<numcol;j++){
							testData.add(getCellData(sheet,i,j));
						}
						modifyrow++;
					}
				}
				//FeedbackData=new String[numrow][numcol];
				returnValue = new Object[modifyrow][numcol];
		
				int k=0;
				for (int i=startRow;i<=modifyrow;i++) {           	  
					for (int j=startCol;j<numcol;j++){
						returnValue[i-1][j]=testData.get(k);
						 //System.out.println("\t"+ returnValue[i][j]);
						k++;
					}
				}
		// Return 2d array object so that test script can use the same
		return returnValue;	
	}

	public static String getCellData(Sheet sh1,int RowNum, int ColNum)
	{
			cell =sh1.getRow(RowNum).getCell(ColNum);
			String CellData = "";
			
				if(cell.getCellType() == cell.CELL_TYPE_STRING){
					CellData = String.valueOf(cell.getStringCellValue().trim());
				}
				else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
					String tCellData = Double.toString(cell.getNumericCellValue());
					CellData = tCellData.split("\\.")[0];
				}
				else if(cell.getCellType() == cell.CELL_TYPE_BLANK) {
					CellData="";
				}
			return CellData;
	}

}
