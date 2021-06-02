package com.org.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

public class UtilityFunctions{
	//private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private FileInputStream stream;
	private String RepositoryFile;
	private static Properties propertyFile = new Properties();	
	/** This function is used to Load Properties file.
	 * @author  shobaghe
	 * @since  01-March-2016
	 * @param  1.fileName - provide property file name which need to be loaded
	 */
	public void LoadPropertiesFile(String fileName) throws IOException
	{
	 try{
			this.RepositoryFile = fileName;
			stream = new FileInputStream(RepositoryFile);
			propertyFile.load(stream);
			stream.close();
		}catch (IOException e){
		    System.out.println(e.getMessage());
	    }
	}
	
	/** This function is used to get locator of any Object also with flexibility to modify locators value at run time if required.
	* @author  shobaghe
	* @since  01-March-2016
	* @param  1.locatorName - provide locator name which is given in the property file to identify objects on AUT.
	* @param  2.withParam  - Provide the counter 1 or 0 weather want to modify locator at run time or not.
	* @param  3.oldStr  - Provide the string which needs to be replaced at run time.
	* @param  4.newStr  - Provide the the new string value to replace the old string.  
	*/
	public By getbjectLocator(String locatorName,String withParam,String oldStr,String newStr)
	{
		String locatorProperty = propertyFile.getProperty(locatorName);
		String locatorType = locatorProperty.split(":")[0];
		String locatorValue = locatorProperty.split(":")[1];
		if ((withParam.equalsIgnoreCase("0")))
		{
			locatorValue = locatorValue;
			
		}else if((withParam.equalsIgnoreCase("1")))
		{
			String NewLocator = locatorValue.replace(oldStr, newStr);
			locatorValue = NewLocator;
		}else{
			System.out.println("Please provide correct arguments values to find Object Locator");
		}
		
			By locator = null;
			switch(locatorType)
			{
			case "Id":
				locator = By.id(locatorValue);
				break;
			case "Name":
				locator = By.name(locatorValue);
				break;
			case "CssSelector":
				locator = By.cssSelector(locatorValue);
				break;
			case "LinkText":
				locator = By.linkText(locatorValue);
				break;
			case "PartialLinkText":
				locator = By.partialLinkText(locatorValue);
				break;
			case "TagName":
				locator = By.tagName(locatorValue);
				break;
			case "Xpath":
				locator = (By.xpath(locatorValue.trim()));
				break;
			}
			
			return locator;
	}
	
	/** 
	 * This function is used to read values from properties file
	* @author  shobaghe
	* @since  01-March-2016
	* @param  1.propKey - provide the key name for which value needs to be fetched
	*/
	public String getPropertiesValue(String propKey)
	{
		String propValue =  propertyFile.getProperty(propKey);
		return propValue;
	}
	
	
	/** 
	 * This function is used to save screen shots at specified folders
	* @author  shobaghe
	* @since  01-March-2016
	* @param  1.propKey - provide webDriver instance
	 * @throws IOException 
	*/
	public void CaptureScreenShot(WebDriver driver,String testMethodName) throws IOException {
		LoadPropertiesFile(System.getProperty("user.dir")+"/src/com/michaelpage/configuration/Constants.properties");
		String baseFolderPath = getPropertiesValue("PATH_SCREENSHOT_BASE");
		String fileSeperator = "\\";
		File destfile = new File(baseFolderPath+fileSeperator+testMethodName);
		if (!destfile.exists())
		{
			destfile.mkdir();
		}
		try{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Thread.sleep(4000);
		//String srcStr = scrFile.toString();
		//String [] srcImgName = srcStr.split(StringEscapeUtils.escapeJava("\\"));
		File screenShotFile = new File(destfile+fileSeperator+testMethodName+System.currentTimeMillis()+".png");
		
		FileUtils.copyFile(scrFile,screenShotFile);
		Reporter.log("<a href=" + screenShotFile + ">click for screenshot</a>");
		}catch(Exception e)
		{
			System.out.println("Exception while taking screenshot "+e.getMessage());
		}

	  }
/*
 * This function is used to read data from xlsx file
 */
	public void ReadExcelData() throws IOException,FileNotFoundException{
		 // Specify the path of file
		File src=new File("C:\\Users\\shobaghe\\workspace\\NewTestNgProject\\TestData\\TestData.xlsx");
		 // load file
		 FileInputStream fis=new FileInputStream(src);
		 // Load workbook
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
		 // Load sheet- Here we are loading first sheetonly
		 XSSFSheet sh1= wb.getSheetAt(0);
		 
		// getRow() specify which row we want to read.
		// and getCell() specify which column to read.
		// getStringCellValue() specify that we are reading String data.
		System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());
		System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());
		System.out.println(sh1.getRow(1).getCell(0).getStringCellValue());
		System.out.println(sh1.getRow(1).getCell(1).getStringCellValue());
		System.out.println(sh1.getRow(2).getCell(0).getStringCellValue());
		System.out.println(sh1.getRow(2).getCell(1).getStringCellValue());
	}
	/** 
	 * This function is used to Verify Element is present on the page or not 
	* @author  shobaghe
	* @since  05-July-2016 
	*/
	public boolean isElementPresent(WebDriver driver,By locator)
	{
		try{
			driver.findElement(locator);
			return true;
			}
			catch(NoSuchElementException e){
				return false;
			}
	}
	/** 
	 * This function is used to scroll down to the bottom of the screen
	* @author  shobaghe
	* @since  01-March-2016 
	*/
	
	public void scrollDownToBottom(WebDriver sdriver)
	{
		JavascriptExecutor js = (JavascriptExecutor)sdriver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	/** 
	* This function is used to wait until the element is visible,provided time out 10 seconds.
	* @author  shobaghe
	* @return  Webelement
	* @since  20-June-2016 
	*/
	public WebElement waitForElementToBeVisible(WebDriver driver,By locator)
	{
		  WebDriverWait myDynamicElement =new WebDriverWait(driver, 30);
		  WebElement returnWebElement =  myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(locator));
		  return returnWebElement;
	 }
	
	/** 
	* This function is used to wait until the element is visible and active ,for click on it.
	* @author  shobaghe
	* @return  Webelement
	* @since  20-June-2016 
	*/
	
	public WebElement waitForElementToBeClickable(WebDriver driver,By locator)
	{
		  WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
		  WebElement returnWebElement = myDynamicElement.until(ExpectedConditions.elementToBeClickable(locator));
		  return returnWebElement;
	}
	
	
	/** 
	* This function is used to wait until the target URL contains expected string. 
	* @author  shobaghe
	* @return  Webelement
	* @since  20-June-2016 
	*/
	
	public void waitForTitleTobePresent(WebDriver driver,String title)
	{
			WebDriverWait myDynamicElement = new WebDriverWait(driver,30);
			if(myDynamicElement.until(ExpectedConditions.titleContains(title)))
			{}else{
				Reporter.log("STATUS : FAIL");
			}
	}
	
	/** 
	* This function is used to Select Drop Down value 
	* @author  shobaghe
	* @return  Webelement
	* @since  15-July-2016 
	*/
	public void selectDropDownValue(WebDriver driver,int index,String DropDownObjectName)
	{
		
		Select dropDown = new Select(driver.findElement(getbjectLocator(DropDownObjectName,"0","","")));
		dropDown.selectByIndex(index);
	} 
	
	/** 
	* This function is used to minimize all the windows
	* @author  shobaghe
	* @return  Webelement
	 * @throws AWTException 
	* @since  15-July-2016 
	*/
	public void minimizeAll() throws AWTException{
		Robot robot = new Robot();
		robot.setAutoDelay(250);
		robot.keyPress(KeyEvent.VK_WINDOWS);
	    robot.keyPress(KeyEvent.VK_D);
	    robot.keyRelease(KeyEvent.VK_D);
	    robot.keyRelease(KeyEvent.VK_WINDOWS);
	}
	
	/**This function is used to do mouse hover on elements
	 * @author  shobaghe
	 * @since  19-July-2016
	 * @param  1.driver - WebDriver Object 
	 */
	public void mouseHover(WebDriver driver,By locator)
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();
	}
}
