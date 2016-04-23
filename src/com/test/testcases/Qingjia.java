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
		//�ȵ�¼
		LoginPage lp=new LoginPage(driver);
		lp.getElement("login_name").sendKeys(param.get("username"));
		lp.getElement("login_pwd").sendKeys(param.get("password"));
		lp.getElement("login_button").click();
		//�����������
		HomePage hp= new HomePage(driver);
		hp.getElement("workspace_home").click();
		//�����������
		WorkspaceHome wh=new WorkspaceHome(driver);
		wh.getElement("createWf").click();
		//��д�����
		QingjiaPage qp=new QingjiaPage(driver);
		qp.getElement("reason").sendKeys(param.get("reason"));
		qp.getElement("qingjiatype").click();
		qp.getElement("start").click();
		qp.getElement("starttime").click();
		qp.getElement("end").click();
		qp.getElement("endtime").click();
		qp.getElement("config").click();
		//�ύ�������
		qp.getElement("submit").click();
		Thread.sleep(2000);
		ScreenShot ss=new ScreenShot(driver);
		ss.takesScreenshot();
		String message=qp.getElement("message").getText().trim();
		Log.logInfo("���̷���ķ�����Ϣ�ǣ�"+message);
		Assert.assertEquals(message, "�����ѷ���");
	} 
}