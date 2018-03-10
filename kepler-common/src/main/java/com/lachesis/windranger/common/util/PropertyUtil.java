package com.lachesis.windranger.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class PropertyUtil {

	static Map<String ,Properties> allConfig;

	static{
		allConfig = new HashMap();
	}
	
	/**
	 * 根据key获取值
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String findPropertiesKey(String fileName,String key,String charsetName) {
		try {
			return getProperties(fileName,charsetName).getProperty(key);
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 读取属性文件
	 * @param fileName
	 * @return
	 */
	public static Properties getProperties(String fileName,String charsetName){
		InputStream in = null;
		try {
			if(allConfig.get(fileName)==null){
				//使用io流读取，解决中文乱码
				in = PropertiesUtil.class.getResourceAsStream(fileName);
				BufferedReader bf = new BufferedReader(new InputStreamReader(in, charsetName));
				Properties propConfig = new Properties();
				propConfig.load(bf);
				allConfig.put(fileName,propConfig);
				return propConfig;
			}else{
				return allConfig.get(fileName);
			}
		} catch (Exception e) {
			return null;
		}finally{
				try {
					if(in!=null)
						in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
