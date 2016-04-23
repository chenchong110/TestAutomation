package com.test.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.base.TestBase;
import com.test.page.HomePage;
import com.test.page.LoginPage;
import com.test.page.QingjiaPage;
import com.test.page.WorkspaceHome;
import com.test.util.Assertion;
import com.test.util.Log;
import com.test.util.ScreenShot;

public class Qingjia extends TestBase{
	@Test(dataProvider="providerMethod")
	public void testQingjia(Map<String,String> param) throws InterruptedException{
		Assertion.flag=true;
		this.goTo(param.get("url"));
		//先登录
		LoginPage lp=new LoginPage(driver);
		lp.getElement("login_name").sendKeys(param.get("username"));
		lp.getElement("login_pwd").sendKeys(param.get("password"));
		lp.getElement("login_button").click();
		//点击个人事务
		HomePage hp= new HomePage(driver);
		hp.getElement("workspace_home").click();
		//发起请假申请
		WorkspaceHome wh=new WorkspaceHome(driver);
		wh.getElement("createWf").click();
		//填写请假条
		QingjiaPage qp=new QingjiaPage(driver);
		qp.getElement("reason").sendKeys(param.get("reason"));
		qp.getElement("qingjiatype").click();
		qp.getElement("start").click();
		qp.getElement("starttime").click();
		qp.getElement("end").click();
		qp.getElement("endtime").click();
		qp.getElement("config").click();
		//提交请假流程
		qp.getElement("submit").click();
		Thread.sleep(2000);
		ScreenShot ss=new ScreenShot(driver);
		ss.takesScreenshot();
		String message=qp.getElement("message").getText().trim();
		Log.logInfo("流程发起的返回信息是："+message);
		Assert.assertEquals(message, "流程已发起");
	} 
}