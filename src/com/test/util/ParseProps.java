package com.test.util;

import java.io.IOException;
import java.util.Properties;

public class ParseProps {
	/**
	 * 默认的配置文件路径
	 */
	private static final String CONFIG = "config/config.properties";
	private Properties props;

	/**
	 * 获取指定配置的值
	 * 
	 * @param keyName
	 * @return
	 */
	public String getElementValue(String keyName) {
		if (props == null) {
			configLoad();
		}
		System.out.println(props.getProperty(keyName));
		return props.getProperty(keyName);
	}

	/**
	 * 加载配置文件
	 */
	private void configLoad() {
		props = new Properties();
		try {
			props.load(ParseProps.class.getResourceAsStream(CONFIG));
		} catch (IOException e) {
			throw new RuntimeException("加载配置文件失败!" + CONFIG);
		}
	}
}