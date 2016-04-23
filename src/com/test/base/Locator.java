package com.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.bean.Config;

public class Locator {
	private String yamlFile;
	protected WebDriver driver;
	
	private Map<String,Map<String,String>> extendLocator;
	
	public void setYamlFile(String yamlFile){
		this.yamlFile=yamlFile;
	}
	public Locator(WebDriver driver){
//		yamlFile="TestBaidu";
//		this.getYamlFile();
		this.driver=driver;
	}
	private HashMap<String,HashMap<String,String>> ml;
	@SuppressWarnings("unchecked")
	public void getYamlFile(){
		File f=new File("locator/"+yamlFile+".yaml");
		try{
			ml=Yaml.loadType(new FileInputStream(f.getAbsolutePath()),HashMap.class);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	private By getBy(String type,String value){
		By by=null;
		if(type.equals("id")){
			by=By.id(value);
		}
        if(type.equals("name")){
            by = By.name(value);
        }
        if(type.equals("xpath")){
            by = By.xpath(value);
        }
        if(type.equals("className")){
            by = By.className(value);
        }
        if(type.equals("linkText")){
            by = By.linkText(value);
        }
		return by;		
	}
	public WebElement getElement(String key){
		return this.getLocator(key, null,true);
	}
	public WebElement getElement(String key,String replace[]){
		return this.getLocator(key, replace,true);
	}
	public WebElement getElementNoWait(String key){
		return this.getLocator(key, null,false);
	}
	public WebElement getElementNoWait(String key,String replace[]){
		return this.getLocator(key, replace,false);
	}
	private WebElement waitForElement(final By by) {
		WebElement element=null;
		int waitTime=Config.waitTime;
		try{
			element=new WebDriverWait(driver, waitTime)
			.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(by);
				}
			});
		}catch(Exception e){
			System.out.println(by.toString()+"is not exist until "+waitTime);
		}
		return element;
	}
	private boolean waitElementToBeDisplayed(final WebElement element) {
		boolean wait=false;
		if(element==null)
			return wait;
		int waitTime=Config.waitTime;
		try{
			wait=new WebDriverWait(driver, waitTime)
			.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return element.isDisplayed();
				}
			});
		}catch(Exception e){
			System.out.println(element.toString()+"is not displayed");
		}
		return wait;
	}
	@SuppressWarnings("unused")
	private boolean waitElementToBeNonDisplayed(final WebElement element) {
		boolean wait=false;
		if(element==null)
			return wait;
		int waitTime=Config.waitTime;
		try{
			wait=new WebDriverWait(driver, waitTime)
			.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return !element.isDisplayed();
				}
			});
		}catch(Exception e){
			System.out.println(element.toString()+"is also dispalyed");
		}
		return wait;
	}

	private WebElement getLocator(String key,String[] replace,boolean wait){
		WebElement element=null;
		if(ml.containsKey(key)){
			HashMap<String,String> m=ml.get(key);
			String type=m.get("type");
			String value=m.get("value");
			if(replace!=null)
				value=this.getLocatorString(value, replace);
			By by=this.getBy(type, value);
			if(wait){
				element=this.waitForElement(by);
				boolean flag=this.waitElementToBeDisplayed(element);
				if(!flag)
					element=null;
				else
					try{
						element=driver.findElement(by);
					}catch(Exception e){
						element=null;
					}
			}
		}else{
			System.out.println("Locator "+key+" is not exist in "+yamlFile+".yaml");
		}
		return element;
	}
	private String getLocatorString(String locatorString,String[] ss){
		for(String s:ss){
			locatorString=locatorString.replaceFirst("%s", s);
		}
		return locatorString;
	}
	public static void main(String[] args){
		SeleniumDriver selenium=new SeleniumDriver();
		WebDriver driver=selenium.getDriver();
		Locator l=new Locator(driver);
		driver.navigate().to("http://www.baidu.com");
//		WebElement element=l.getElement("baidu_input1");
//		element.sendKeys("aa");
		String replace[]={"kw"};
		WebElement element=l.getElement("baidu_input", replace);
		element.sendKeys("aa");
		String replace2[]={"su"};
		element=l.getElement("baidu_input",replace2);
		element.click();
		driver.close();
	}
}
