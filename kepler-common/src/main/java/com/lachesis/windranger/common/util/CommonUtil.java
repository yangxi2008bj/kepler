package com.lachesis.windranger.common.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;

public class CommonUtil {
	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	private CommonUtil() {
		throw new IllegalStateException("Utilty class");
	}

	/**
	 * 
	 * @Title: isEmptyString
	 * @Description: 判断字符串是否为空
	 * @param string
	 * @return boolean 返回类型
	 */
	public static boolean isEmptyString(String string) {
		return !(string != null && string.length() > 0);
	}

	/**
	 * 
	 * @Title: isEmptyList
	 * @Description: 判断list是否为空
	 * @param list
	 * @return boolean 返回类型
	 */
	public static <T> boolean isEmptyList(List<T> list) {
		return list == null || list.isEmpty();
	}

	public static String encodeChineseChar(String chinese) {
		if (StringUtils.isEmpty(chinese)) {
			return "";
		}
		try {
			return new String(chinese.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("context", e);
		}
		return null;
	}

	/**
	 * 
	 * @Title: listToHashMap
	 * @Description: 将list转化成hashmap且hashmap中的key，value均为list的对象
	 * @param list
	 *            待转化的列表
	 * @return Map<Object,Object> 返回类型
	 */
	public static Map<Object, Object> listToHashMap(List<Object> list) {
		Map<Object, Object> map = new HashMap<>();
		if (!isEmptyList(list)) {
			for (Object o : list) {
				map.put(o, o);
			}
		}
		return map;
	}
}
