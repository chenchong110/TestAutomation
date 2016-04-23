package com.test.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.TestBase;
import com.test.page.HomePage;
import com.test.page.LoginPage;
import com.test.util.Assertion;
import com.test.util.Log;
import com.test.util.ScreenShot;

public class Login extends TestBase{
	@Test(dataProvider="providerMethod")
	public void testLogin(Map<String,String> param){
		Assertion.flag=true;
		//打开URL
		this.goTo(param.get("url"));
		LoginPage lp=new LoginPage(driver);
		Log.logInfo("登录用户名为："+param.get("username"));
		lp.getElement("login_name").sendKeys(param.get("username"));
		Log.logInfo("登录密码为："+param.get("password"));
		lp.getElement("login_pwd").sendKeys(param.get("password"));
		lp.getElement("login_button").click();
		HomePage hp= new HomePage(driver);
		String commonWf=hp.getElement("common_wf").getText().trim();
		//截图
		ScreenShot ss=new ScreenShot(driver);
		ss.takesScreenshot();
		Log.logInfo("常用流程为'"+commonWf+"'");
		//断言,若登录成功能在常用流程看到请假流程
		Assert.assertEquals(commonWf, "请假流程");
	} 

}
