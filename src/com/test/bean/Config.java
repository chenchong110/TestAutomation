package com.test.bean;

import com.test.util.ParseXml;

public class Config {
	public static String Browser;
	public static int waitTime;
	static{
		ParseXml px=new ParseXml("config/Config.xml");
		Browser =px.getElementText("/*/browser");
		waitTime=Integer.valueOf(px.getElementText("/*/waitTime"));
	}
/*	public static void main(String[] args){
		System.out.println(Browser);
		System.out.println(waitTime);
	}*/

}