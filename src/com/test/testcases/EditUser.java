package com.test.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.TestBase;
import com.test.page.HomePage;
import com.test.page.LoginPage;
import com.test.page.PersonalPage;
import com.test.page.QingjiaPage;
import com.test.page.WorkspaceHome;
import com.test.util.Assertion;
import com.test.util.Log;
import com.test.util.ScreenShot;

public class EditUser extends TestBase{
	@Test(dataProvider="providerMethod")
	public void testEditUser(Map<String,String> param) throws InterruptedException{
		Assertion.flag=true;
		this.goTo(param.get("url"));
		//先登录
		LoginPage lp=new LoginPage(driver);
		lp.getElement("login_name").sendKeys(param.get("username"));
		lp.getElement("login_pwd").sendKeys(param.get("password"));
		lp.getElement("login_button").click();
		//点击右上角姓名
		HomePage hp= new HomePage(driver);
		hp.getElement("personal").click();
		//点击个人信息
		hp.getElement("personalinformation").click();
		
		//修改电话、邮箱
		PersonalPage pp=new PersonalPage(driver);
		pp.getElement("email").clear();
		pp.getElement("email").sendKeys(param.get("email"));
		pp.getElement("cellphone").clear();
		pp.getElement("cellphone").sendKeys(param.get("telephone"));
		pp.getElement("submit").click();
		Thread.sleep(5000);
		ScreenShot ss=new ScreenShot(driver);
		ss.takesScreenshot();
		String phone=pp.getElement("cellphone").getAttribute("value");
		String email=pp.getElement("email").getAttribute("value");
		Log.logInfo("手机号码为："+phone+",邮箱号码为："+email);
		Assert.assertEquals(phone, "15895875868");
		Assert.assertEquals(email, "280207685@qq.com");
	} 
}