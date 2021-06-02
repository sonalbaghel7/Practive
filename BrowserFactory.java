package com.org.utility;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
	private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	public static WebDriver driver = null;
	/**
	 * This method is a Factory method for getting browsers.
	 * @author  shobaghe
	 * @since  01-March-2016
	 * @param  1.browserName - provide browser name which is required to be invoked for ex.chrome, firefox etc
	 */
	public  static WebDriver getBrowser(String browserName) {
		WebDriver driver = null;
		switch (browserName) {
		case "Firefox":
			driver = drivers.get("Firefox");
			if (driver == null) {
				driver = new FirefoxDriver();
				drivers.put("Firefox", driver);
			}
			break;
		case "IE":
			driver = drivers.get("IE");
			if (driver == null) {
				System.setProperty("webdriver.ie.driver",
						"C:\\Users\\abc\\Desktop\\Server\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				drivers.put("IE", driver);
			}
			break;
		case "Chrome":
			driver = drivers.get("Chrome");
			if (driver == null) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
				driver = new ChromeDriver();
				drivers.put("Chrome", driver);
			}
			break;
		}
		return driver;
	}

}
