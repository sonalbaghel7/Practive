package com.org.utility;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.michaelpage.utility.*;


public class FunctionLibrary {
	public static WebDriver driver;
	UtilityFunctions utl = new UtilityFunctions();
	SoftAssert soft_assert = new SoftAssert();
	
	
	
	/** This function is used to login into the application
	 * @author  shobaghe
	 * @since  16-June-2016
	 * @param  1.userName - provide username 
	 * @param  2.pass - provide password 
	 */
	public void login(WebDriver driver,String userName,String pass)
	{
		utl.waitForElementToBeVisible(driver,utl.getbjectLocator("Login_UserName", "0", "", ""));
		driver.findElement(utl.getbjectLocator("Login_UserName","0","","")).sendKeys(userName);
		driver.findElement(utl.getbjectLocator("Login_Password","0","","")).sendKeys(pass);
		driver.findElement(utl.getbjectLocator("Login_Button","0","","")).click();
	}
	
	/** This function is used to logout from the application
	 * @author  shobaghe
	 * @since  16-June-2016 
	 * @param WebDriver
	 */
	public static void logout(WebDriver driver)
	{
		//driver.findElement(utl.getbjectLocator("EmailAddress")).sendKeys(Email);
		driver.quit();
	}
	
	/** This function is used to visit any mp/pp site
	 * @author  Shital Thorat
	 * @since  16-June-2016 
	 * @param 	1.WebDriver 
	 * @param 	2.env = provide environment on which scripts need to be executed fields 
	 * @param	3.pageSite = provide on which site whether MP/PP
	 * @param	4.country = provide country on which script will execute 
	 */
	public void VisitPage(WebDriver driver,String env,String pageSite,String country) throws IOException
	{
		String baseUrl = "http://wwwuat.pagesite.com.sg";
		String visitUrl = "";
		String visitCountry = country;
		String tempUrl = baseUrl.replace("pagesite", pageSite);
		String tempUrl1 = tempUrl.replace("uat",env);
		String tempUrl2 = tempUrl.replace("uat","code");
		try{
			if(country.equalsIgnoreCase("us") || (country.equalsIgnoreCase("oneafrica"))){
				visitUrl = tempUrl1.replace("com.sg","com");
			}else if((country.equalsIgnoreCase("ch")) || (country.equalsIgnoreCase("ie")) || (country.equalsIgnoreCase("ae")) || (country.equalsIgnoreCase("at")) || (country.equalsIgnoreCase("de")) || (country.equalsIgnoreCase("ca")) || (country.equalsIgnoreCase("pl")) || (country.equalsIgnoreCase("fr")) || (country.equalsIgnoreCase("it"))){
				visitUrl = tempUrl1.replace("com.sg",visitCountry);			
			}else if(country.equalsIgnoreCase("jp") || (country.equalsIgnoreCase("in")) || (country.equalsIgnoreCase("id")) || (country.equalsIgnoreCase("za")) || (country.equalsIgnoreCase("uk")) || (country.equalsIgnoreCase("nz"))){
				String NewUrl=tempUrl1.replace("com", "co");
				visitUrl = NewUrl.replace("sg",visitCountry);
			}else if(country.equalsIgnoreCase("br")){
				visitUrl = tempUrl1.replace("sg",visitCountry);	
			}else if (country.equalsIgnoreCase("au") || (country.equalsIgnoreCase("sg")) || (country.equalsIgnoreCase("hk")) || (country.equalsIgnoreCase("my")) || (country.equalsIgnoreCase("cn")) || (country.equalsIgnoreCase("tw"))){
				visitUrl = tempUrl2.replace("sg",visitCountry);
			}
			driver.get(visitUrl);
		}catch(Exception e){
				System.out.println("Please Provide A Valid VisitPage URL");
			}
     }
	
	/** This function is used to visit myPage of any mp/pp site
	 * @author  Shital Thorat
	 * @since  16-June-2016 
	 * @param 	1.WebDriver object
	 * @param 	2.env = provide environment on which scripts need to be executed fields 
	 * @param	3.pageSite = provide on which site whether MP/PP
	 * @param	4.country = provide country on which script will execute 
	 */
	public void VisitMyPage(WebDriver driver,String env,String pageSite,String country) throws IOException
	{
		String baseUrl = "http://wwwuat.pagesite.com.sg/mypage/profile";
		String visitUrl = "";
		String visitCountry = country;
		String tempUrl = baseUrl.replace("pagesite", pageSite);
		String tempUrl1 = tempUrl.replace("uat", env);
		String tempUrl2 = tempUrl.replace("uat","code");
		
		try{
			if (country.equalsIgnoreCase("us")|| (country.equalsIgnoreCase("oneafrica"))){
				visitUrl = tempUrl1.replace("com.sg","com");
			}else if((country.equalsIgnoreCase("ch")) || (country.equalsIgnoreCase("ie")) || (country.equalsIgnoreCase("ae")) || (country.equalsIgnoreCase("at")) || (country.equalsIgnoreCase("de")) || (country.equalsIgnoreCase("ca")) || (country.equalsIgnoreCase("pl")) || (country.equalsIgnoreCase("fr")) || (country.equalsIgnoreCase("it"))){
				visitUrl = tempUrl1.replace("com.sg",visitCountry);			
			}else if(country.equalsIgnoreCase("jp") || (country.equalsIgnoreCase("in")) || (country.equalsIgnoreCase("id")) || (country.equalsIgnoreCase("za")) || (country.equalsIgnoreCase("uk")) || (country.equalsIgnoreCase("nz"))){
				String NewUrl=tempUrl1.replace("com", "co");
				visitUrl = NewUrl.replace("sg",visitCountry);
			}else if(country.equalsIgnoreCase("br")){
				visitUrl = tempUrl1.replace("sg",visitCountry);	
			}else if (country.equalsIgnoreCase("au") || (country.equalsIgnoreCase("sg")) || (country.equalsIgnoreCase("hk")) || (country.equalsIgnoreCase("my")) || (country.equalsIgnoreCase("cn")) || (country.equalsIgnoreCase("tw"))){
				visitUrl = tempUrl2.replace("sg",visitCountry);
			}
				driver.get(visitUrl);
			Thread.sleep(10000);
		}catch(Exception e){
			System.out.println("Please Provide A Valid VisitMyPage URL");
		}
	}
	
	/** This function is used to configure county and language as given
	 * @author  shobaghe
	 * @since  16-June-2016
	 * @param  1.WebDriver - WebDriver object
	 * @param  2.country - provide country on which script will execute
	 * @param  3.lang - provide on which language script will execute
	 */
	public void checkCountryLanguage(WebDriver driver,String country,String lang)
	{
		String detailUrl = driver.getCurrentUrl();
		if(lang.equalsIgnoreCase("en")){
			if(country.equalsIgnoreCase("jp") || country.equalsIgnoreCase("at")){
				String newUrl = detailUrl.concat("en");
				driver.get(newUrl);
			}
		}else if(lang.equalsIgnoreCase("zh")){
			if(country.equalsIgnoreCase("cn") || country.equalsIgnoreCase("tw")){
				String newUrl = detailUrl.concat("zh");
				driver.get(newUrl);
			}	
		}else if (lang.equalsIgnoreCase("fr")){
			if(country.equalsIgnoreCase("fr")){
				String newUrl = detailUrl.concat("fr");
				driver.get(newUrl);
			}	
		}else if (lang.equalsIgnoreCase("fr-ca")){
			String newUrl = detailUrl.concat("fr-ca");
			driver.get(newUrl);
		}else{
			driver.get(detailUrl);
		}
		
	}
	
	/** This function is used to fill job apply form for a non logged in user
	 * @author  shobaghe
	 * @since  16-June-2016
	 * @param  1.driver = WebDriver Object,2.fName=Provide first name,3.lName=Provide last Name ,4.email=Procide Email address
	 * 		   5.telNo=Provide Telephone no,6.browseCv=Provide 1 to Upload a CV ,7.message = Provide Message 
	 * @throws InterruptedException 
	 */
	
	public void fillJobApplyFormWithOutLogin(WebDriver driver,String fName,String lName,String email,String telNo,String cvUpload,String browseCv,String coverLetter,String bPrivacy,String bCreateAlert) throws IOException, InterruptedException
	{
		String fileUploadScriptPath = utl.getPropertiesValue("FILEUPLOAD_AUTOIT_SCRIPTPATH");
		utl.waitForElementToBeVisible(driver, utl.getbjectLocator("JobApply_FirstName","0","",""));
		driver.findElement(utl.getbjectLocator("JobApply_FirstName","0","","")).sendKeys(fName);
		driver.findElement(utl.getbjectLocator("JobApply_LastName","0","","")).sendKeys(lName);
		driver.findElement(utl.getbjectLocator("JobApply_Telephone","0","","")).sendKeys(telNo);
		driver.findElement(utl.getbjectLocator("JobApply_Email","0","","")).sendKeys(email);
		driver.findElement(utl.getbjectLocator("JobApply_Message","0","","")).sendKeys(coverLetter);
		driver.findElement(utl.getbjectLocator("JobApply_BrowseCVButton","0","","")).click();
		if(cvUpload.equalsIgnoreCase("1")){
			if(browseCv.endsWith(".docx")){
				Runtime.getRuntime().exec(fileUploadScriptPath);
			}
		}
		Thread.sleep(8000);
		//utl.scrollDownToBottom(driver);
		selectPrivacyCheckBox(driver,bPrivacy);
		selectCreateAlertCheckBox(driver,bCreateAlert);
	}
	/** This function is used to fill job apply form for a logged in registered user 
	 * @author  shobaghe
	 * @since  16-June-2016
	 * @param  1.driver = WebDriver Object,2.fName=Provide first name,3.lName=Provide last Name ,4.email=Procide Email address
	 * 		   5.telNo=Provide Telephone no,6.browseCv=Provide 1 to Upload a CV ,7.message = Provide Message 
	 * @throws InterruptedException 
	 */
	
	public void fillJobApplyFormWithLogin(WebDriver driver,String fName,String lName,String email,String telNo,String ChooseStoredCv,String coverLetter,String bPrivacy,String bCreateAlert) throws IOException, InterruptedException
	{
		utl.waitForElementToBeVisible(driver, utl.getbjectLocator("JobApply_FirstName","0","",""));
		driver.findElement(utl.getbjectLocator("JobApply_FirstName","0","","")).clear();
		driver.findElement(utl.getbjectLocator("JobApply_FirstName","0","","")).sendKeys(fName);
		driver.findElement(utl.getbjectLocator("JobApply_LastName","0","","")).clear();
		driver.findElement(utl.getbjectLocator("JobApply_LastName","0","","")).sendKeys(lName);
		driver.findElement(utl.getbjectLocator("JobApply_Telephone","0","","")).sendKeys(telNo);
		driver.findElement(utl.getbjectLocator("JobApply_Email","0","","")).clear();
		driver.findElement(utl.getbjectLocator("JobApply_Email","0","","")).sendKeys(email);
		driver.findElement(utl.getbjectLocator("JobApply_Message","0","","")).sendKeys(coverLetter);
		if(ChooseStoredCv.equalsIgnoreCase("1")){
		utl.selectDropDownValue(driver,1,"JobApply_ChooseStoredCVDropdown");
		Thread.sleep(5000);
		}
		selectPrivacyCheckBox(driver,bPrivacy);
		selectCreateAlertCheckBox(driver,bCreateAlert);
	}
	/** This function is used to fill job apply form for a logged in registered user 
	 * @author  shobaghe
	 * @since  16-June-2016
	 * @param  1.driver = WebDriver Object,2.fName=Provide first name,3.lName=Provide last Name ,4.email=Procide Email address
	 * 		   5.telNo=Provide Telephone no,6.browseCv=Provide 1 to Upload a CV ,7.message = Provide Message 
	 * @throws InterruptedException 
	 */
	public void fillSubmitCvFormWithOutLogin(WebDriver driver,String fName,String lName,String email,String telephone,String sector,String location,String jobType,String cvUpload,String browseCv,String comments,String bPrivacy,String output,String outPutType) throws IOException, InterruptedException
	{
		String fileUploadScriptPath = utl.getPropertiesValue("FILEUPLOAD_AUTOIT_SCRIPTPATH");
		utl.waitForElementToBeVisible(driver,utl.getbjectLocator("SubmitCV_FirstName", "0", "", ""));
		driver.findElement(utl.getbjectLocator("SubmitCV_FirstName", "0", "", "")).sendKeys(fName);
		driver.findElement(utl.getbjectLocator("SubmitCV_LastName", "0", "", "")).sendKeys(lName);
		driver.findElement(utl.getbjectLocator("SubmitCV_Email", "0", "", "")).sendKeys(email);
		driver.findElement(utl.getbjectLocator("SubmitCV_Telephone", "0", "", "")).sendKeys(telephone);
		if(sector.equalsIgnoreCase("1")){
			utl.selectDropDownValue(driver,1,"SubmitCV_SectorDropDown");
		}
		if(location.equalsIgnoreCase("1")){
			utl.selectDropDownValue(driver,1,"SubmitCV_LocationDropDown");
		}
		if(jobType.equalsIgnoreCase("1")){
			utl.selectDropDownValue(driver,1,"SubmitCV_JobTypeDropDown");
		}
		driver.findElement(utl.getbjectLocator("SubmitCV_CommentsTextArea", "0", "", "")).sendKeys(comments);
		driver.findElement(utl.getbjectLocator("SubmitCV_BrowseCvButton","0","","")).click();
		if(cvUpload.equalsIgnoreCase("1")){
			if(browseCv.endsWith(".docx")){
				Runtime.getRuntime().exec(fileUploadScriptPath);
			}
		}
		selectPrivacyCheckBox(driver,bPrivacy);
		driver.findElement(utl.getbjectLocator("SubmitCV_SubmitButton", "0", "", "")).click();
		
	}
	
	/** This function is used to Verify message displaying is as expected
	 * @author  shobaghe
	 * @since  16-June-2016
	 * @param - expMessage - Meassage to be verified
	 * @param - type - this is related to the type of a message whether it is an error message or an normal label or an message on  Thank you page
	 */
	public void VerifyMessage(WebDriver driver,String expMessage,String type)
	{
		if (type.equalsIgnoreCase("ERROR")){
			utl.waitForElementToBeVisible(driver,utl.getbjectLocator("Global_ErrorMessageLabel", "0", "", ""));
			String currMessage = driver.findElement(utl.getbjectLocator("Global_ErrorMessageLabel", "0", "", "")).getAttribute("text");
			soft_assert.assertEquals(currMessage, expMessage);
		}else if(type.equalsIgnoreCase("MESSAGE")){
			utl.waitForElementToBeVisible(driver,utl.getbjectLocator("Global_ThankYouMessageLabel", "0", "", ""));
			boolean MsgCurrent = driver.getPageSource().contains(expMessage);
			soft_assert.assertTrue(MsgCurrent,expMessage);
		}else{
			utl.waitForElementToBeVisible(driver,utl.getbjectLocator("Global_ErrorMessageDiv", "0", "", ""));
			String currMessage = driver.findElement(utl.getbjectLocator("Global_ErrorMessageDiv", "0", "", "")).getAttribute("text");
			soft_assert.assertEquals(currMessage, expMessage);
		}
	}
	
	/** This function is used to click on Apply for job button on job detail page
	 * @author  shobaghe
	 * @since	16-June-2016
	 * @param 	1.driver - WebDriver Object
	 * @param	2.country - provide country on which script will execute
	 * @param  	3.language - provide on which language script will execute
	 */
	public void ClickApplyForJob(WebDriver driver,String country,String language)
	{
		if(language.equalsIgnoreCase("en")){
			driver.findElement(utl.getbjectLocator("JobDetail_ApplyButton_EN", "0", "", "")).click();
		}else if(language.equalsIgnoreCase("zh") && country.equalsIgnoreCase("cn")){
			driver.findElement(utl.getbjectLocator("JobDetail_ApplyButton_EN", "0", "", "")).click();
		}else if(language.equalsIgnoreCase("br")){
			driver.findElement(utl.getbjectLocator("JobDetail_ApplyButton_EN", "0", "", "")).click();
		}else if(language.equalsIgnoreCase("de")){
			driver.findElement(utl.getbjectLocator("JobDetail_ApplyButton_EN", "0", "", "")).click();
		}else if(language.equalsIgnoreCase("zh") && country.equalsIgnoreCase("tw")){
			driver.findElement(utl.getbjectLocator("JobDetail_ApplyButton_EN", "0", "", "")).click();
		}else if(language.equalsIgnoreCase("jp") && country.equalsIgnoreCase("jp")){
			driver.findElement(utl.getbjectLocator("JobDetail_ApplyButton_EN", "0", "", "")).click();
		}
		
	}
	
	/** This function is used to click on the Job Link on the Job Search Results page
	 * @author  shobaghe
	 * @since  16-June-2016
	 * @param  1.driver - WebDriver Object 
	 */
	public void ClickOnJobLink(WebDriver driver) throws InterruptedException
	{
		driver.manage().timeouts().pageLoadTimeout(90,TimeUnit.SECONDS);
		utl.waitForElementToBeVisible(driver,utl.getbjectLocator("JobSearchResult_JobLinkFirst","0","",""));
		Thread.sleep(10000);
		driver.findElement(utl.getbjectLocator("JobSearchResult_JobLinkFirst","0","","")).click();
		//clickElement(driver);
		Thread.sleep(10000);
		if(utl.isElementPresent(driver,utl.getbjectLocator("Global_PositionAlreadyFilled","0", "", ""))){
			driver.findElement(utl.getbjectLocator("BackToSearchResults","0","","")).click();
			utl.waitForElementToBeVisible(driver,utl.getbjectLocator("JobSearchResultSearchTextBox","0", "", ""));
			String jobSearchKeywordNew = utl.getPropertiesValue("SECOND_JOB_SEARCH_KEYWORD");
			driver.findElement(utl.getbjectLocator("JobSearchResult_SearchTextBox","0", "", "")).clear();
			driver.findElement(utl.getbjectLocator("JobSearchResult_SearchTextBox","0", "", "")).sendKeys(jobSearchKeywordNew);
			driver.findElement(utl.getbjectLocator("JobSearchResult_SearchButton","0", "", "")).click();
			driver.findElement(utl.getbjectLocator("JobSearchResult_JobLinkFirst","0", "", "")).click();
		}
	}
	
	/** This function is used to click on privacy check box on User Register,Job Apply page
	 * @author  shobaghe
	 * @since  03-July-2016
	 * @param  1.driver - WebDriver Object 
	 */
	public void selectPrivacyCheckBox(WebDriver driver,String toBeSelected)
	{
		if(toBeSelected.equals("1")){
			if(!driver.findElement(utl.getbjectLocator("Global_PrivacyCheckBox","0", "", "")).isSelected()){
				List<WebElement> checkBox = driver.findElements(utl.getbjectLocator("Global_PrivacyCheckBox","0", "", ""));
				for( WebElement chkbox : checkBox){
					if(chkbox.getAttribute("class").equals("form-required")){
						chkbox.click();
					}
				}
			}
		 }
	
	}
	/** This function is used to click on create Alert check  box on User Register,Job Apply page
	 * @author  shobaghe
	 * @since  03-July-2016
	 * @param  1.driver - WebDriver Object 
	 */
	public void selectCreateAlertCheckBox(WebDriver driver,String toBeSelected)
	{
		if(toBeSelected.equals("1")){
			if(!driver.findElement(utl.getbjectLocator("Global_CreateAlertCheckBox","0", "", "")).isSelected()){
				List<WebElement> checkBox = driver.findElements(utl.getbjectLocator("Global_CreateAlertCheckBox","0", "", ""));
				for( WebElement chkbox : checkBox){
					chkbox.click();
				}
			}
		 }
	}
	/** This function is used to collect information about job from JOB'S DETAIL page , while applying for a job with alert and returns it 
	 * in a form of string which will have same pattern as recently created job alert TITLE on the "Job Alerts & Saved Search" section on mypage.
	 * @author  shobaghe
	 * @since  18-July-2016
	 * @param  1.driver - WebDriver Object 
	 */
	public String getJobSummary(WebDriver driver)
	{
		StringBuilder  OptStr = new StringBuilder();
		utl.waitForElementToBeVisible(driver, utl.getbjectLocator("JobDetail_JobSummarySection","0","",""));
		if(driver.findElements(utl.getbjectLocator("JobDetail_JobSummarySection","0","",""))!=null){
			List<WebElement> jobSummary = driver.findElements(utl.getbjectLocator("JobDetail_JobSummarySection","0","",""));
			 int i = 1;
			for(WebElement value : jobSummary){
				   if(i==1 || i==2 || i==4){
					String valueTemp1 = value.getText();
					OptStr.append(valueTemp1+":");
				    }
					i++;
			}
		}
		return OptStr.toString();
	}
	/** This function is used to clikc on any element by using java script 
	 * @author  shobaghe
	 * @since  18-July-2016
	 * @param  1.driver - WebDriver Object 
	 */
	public void clickElement(WebDriver driver){
		try{
		WebElement webElement = driver.findElement(utl.getbjectLocator("JobSearchResult_JobLinkFirst", "0","", ""));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", webElement);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	/** This function is used to Remove All other Alerts which are created previously
	 * @author  shobaghe
	 * @since  21-July-2016
	 * @param  1.driver - WebDriver Object 
	 */
	public void RemoveOldAlerts(WebDriver driver)
	{
		try{
			utl.waitForElementToBeVisible(driver, utl.getbjectLocator("MyPage_JobAlertRemoveButton", "0", "", ""));
			List<WebElement> alertsList = driver.findElements(utl.getbjectLocator("MyPage_JobAlertRemoveButton", "0", "", ""));
			int lenght = alertsList.size();
			for(int i=lenght-1;i>=1;i--) {
				alertsList.get(i).click();
				//alertsList.get(i+1).click();
				driver.findElement(utl.getbjectLocator("MyPage_RemoveAlertPopupConfirmButton", "0", "", "")).click();
				alertsList = driver.findElements(utl.getbjectLocator("MyPage_JobAlertRemoveButton", "0", "", ""));
			  }
			}catch (Exception e) {
				e.getMessage();
				
			}
	}
	
	
}

