package com.test.bean;

import java.util.Map;

import com.test.util.ParseXml;

public class Global {
	public static Map<String,String> global;
	static{
		ParseXml px=new ParseXml("config/global.xml");
		global=px.getChildrenInfoByElement(px.getElementObject("/*"));
	}
}