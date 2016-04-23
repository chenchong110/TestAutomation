package com.test.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	public WebDriver driver;
	public ScreenShot(WebDriver driver){
		this.driver=driver;
	}
	private void takeScreenShot(String screenPath){
		try{
			File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(screenPath));
		}catch(IOException e){
			System.out.println("Screen shot error:"+screenPath);
		}
	
	}
	public void takesScreenshot(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_hhmmss");
		String screenName=String.valueOf(sdf.format(new Date().getTime())+".jpg");
		File dir=new File("test-output/snapshot");
		if(!dir.exists())
			dir.mkdirs();
		String screenPath=dir.getAbsolutePath()+"/"+screenName;
		this.takeScreenShot(screenPath);
	}
}
