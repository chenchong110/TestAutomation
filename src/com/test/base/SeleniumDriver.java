package com.test.base;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.test.bean.Config;

public class SeleniumDriver {
	private WebDriver driver;
	public WebDriver getDriver(){
		return driver;
	}
	public SeleniumDriver(){
		this.initialDriver();
	}
	private void initialDriver(){
		if("firefox".equals(Config.Browser))
			driver=new FirefoxDriver();
		else if("ie".equals(Config.Browser)){
			System.setProperty ("webdriver.ie.driver","files/IEDriverServer.exe");
			DesiredCapabilities capabilities=DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			driver = new InternetExplorerDriver();
		}
		else if("chrome".equals(Config.Browser)){
			System.setProperty ("webdriver.chrome.driver","files/chromedriver.exe");
			ChromeOptions options=new ChromeOptions();
			options.addArguments(new String[]{"--test-type"});
			driver = new ChromeDriver();
		}
		else{
			System.out.println("‰Ø¿¿∆˜∆•≈‰÷µ¥ÌŒÛ£∫ "+Config.Browser);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Config.waitTime, TimeUnit.SECONDS);		
	}
	public static void main(String[] args){
		SeleniumDriver selenium=new SeleniumDriver();
		WebDriver driver=selenium.getDriver();
		driver.navigate().to("http://www.baidu.com");
		driver.close();
		driver.quit();
	}
}
