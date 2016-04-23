package com.test.base;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Page extends Locator{
//	protected WebDriver driver;

	public Page(WebDriver driver) {
		super(driver);
		this.setYamlFile(this.getClass().getSimpleName());
		this.getYamlFile();
	}
	public Actions getAction(){
		return new Actions(driver);
	}
	public void switchWindowByIndex(int index){
		Object[] handles = driver.getWindowHandles().toArray();
		if(index>handles.length){
			return;
		}
		driver.switchTo().window(handles[index].toString());
	}
	public boolean isExist(WebElement element){
		if(element==null){
			return false;
		}else{
			return true;
		}
	}
	//≤‚ ‘
	public void test(){
		driver.navigate().to("");
	}

//	private boolean waitToDisplayed(WebDriver Driver, final By key) {
//		boolean waitDisplayed = new WebDriverWait(Driver, 10)
//				.until(new ExpectedCondition<Boolean>() {
//					public Boolean apply(WebDriver d) {
//						return d.findElement(key).isDisplayed();
//					}
//				});
//		return waitDisplayed;
//	}
//
//	protected WebElement getElement(WebDriver Driver, By key) {
//		WebElement element = null;
//		if (this.waitToDisplayed(Driver, key)) {
//			element = Driver.findElement(key);
//		}
//		return element;
//	}
}
