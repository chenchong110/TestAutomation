package com.test.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.TestBase;
import com.test.page.LoginPage;
import com.test.util.Assertion;
import com.test.util.Log;
import com.test.util.ScreenShot;

public class IllegalLogin extends TestBase{
	@Test(dataProvider="providerMethod")
	public void testIllegalLogin(Map<String,String> param){
		Assertion.flag=true;
		//打开URL
		this.goTo(param.get("url"));
		LoginPage lp=new LoginPage(driver);
		Log.logInfo("登录用户名为："+param.get("username"));
		lp.getElement("login_name").sendKeys(param.get("username"));
		Log.logInfo("登录密码为："+param.get("password"));
		lp.getElement("login_pwd").sendKeys(param.get("password"));
		lp.getElement("login_button").click();
		String errorMessage=lp.getElement("error_message").getText().trim();
		//截图
		ScreenShot ss=new ScreenShot(driver);
		ss.takesScreenshot();
		Log.logInfo("登录错误提示信息：'"+errorMessage+"'");
		//断言
		Assert.assertEquals(errorMessage, "登陆失败");
	} 

}
