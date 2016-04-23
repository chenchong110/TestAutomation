package com.test.util;

import java.io.IOException;
import java.util.Properties;

public class ParseProps {
	/**
	 * Ĭ�ϵ������ļ�·��
	 */
	private static final String CONFIG = "config/config.properties";
	private Properties props;

	/**
	 * ��ȡָ�����õ�ֵ
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
	 * ���������ļ�
	 */
	private void configLoad() {
		props = new Properties();
		try {
			props.load(ParseProps.class.getResourceAsStream(CONFIG));
		} catch (IOException e) {
			throw new RuntimeException("���������ļ�ʧ��!" + CONFIG);
		}
	}
}