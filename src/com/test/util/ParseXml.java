package com.test.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXml {
	private String filePath;
	private Document document;
	public ParseXml(String filePath){
		this.filePath=filePath;
		this.load(this.filePath);
	}
	public void load(String filePath){
		//xml解析，首先载入xml文件，对这个文件对象进行xml分析处理
		File file=new File(filePath);
		if(file.exists()){
			SAXReader saxReader=new SAXReader();
			try {
				document=saxReader.read(file);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
//				System.out.println("文件加载异常 "+filePath);
				Log.logError("文件加载异常 "+filePath);
			}
		}else{
//			System.out.println("文件加载异常"+filePath);
			Log.logError("文件加载异常"+filePath);
		}
	}
	public Element getElementObject(String elementPath){
		return (Element) document.selectSingleNode(elementPath);
	}
	public String getElementText(String elementPath){
		Element element=this.getElementObject(elementPath);
		if(element!=null)
			return element.getTextTrim();
		else
			return null;
	}
	public boolean isExist(String elementPath){
		boolean flag=false;
		Element element=this.getElementObject(elementPath);
		if(element!=null)
			flag=true;
		return flag;
	}
	@SuppressWarnings("unchecked")
	public List<Element> getElementObjects(String elementPath){
		return document.selectNodes(elementPath);
	}
	@SuppressWarnings("unchecked")
	public Map<String,String> getChildrenInfoByElement(Element element){
		Map<String,String> map=new HashMap<String,String>();
		List<Element> children=element.elements();
		for(Element e:children){
			map.put(e.getName(), e.getText());
		}
		return map;
	}

}
