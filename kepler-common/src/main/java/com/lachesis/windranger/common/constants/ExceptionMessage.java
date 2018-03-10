package com.lachesis.windranger.common.constants;


public class ExceptionMessage {
	
	public static final String DATE_PARSE_EXCEPTION = "日期转化异常";
	public static final String DATA_TYPE_CONFIG_EXCEPTION = "数据类型配置异常";
	public static final String HIS_TRANSPORT_EXCEPTION = "发送至HIS传输异常";
	public static final String UTILITY_CLASS = "工具类，请参照使用静态类使用方法";
	public static final String IO_EXCEPTION = "输入输出文件异常";
	public static final String DECRYPT_EXCEPTION = "解密异常";
	
	private ExceptionMessage() {
		throw new IllegalStateException(UTILITY_CLASS);
	}
}
