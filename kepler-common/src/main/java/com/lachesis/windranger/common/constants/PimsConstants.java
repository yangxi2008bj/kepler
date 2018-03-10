package com.lachesis.windranger.common.constants;

public class PimsConstants {
	public static final Integer PAT_IN_OUT_EVENT_TYPE_0=0;//进入病区
	public static final Integer PAT_IN_OUT_EVENT_TYPE_1=0;//出病区
	
	
	public class SysConfigConstants{
		 public static final String LDMIP="LDMIP";//设备管理服务器IP
		 public static final String LDMPORT = "LDMPORT";//设备管理服务器端口
		 public static final String LDMSERVE = "LDMSERVE";//设备管理后台服务地址
		 public static final String SOCKETPORT = "SOCKETPORT";//护理质量门禁socket端口
	}
	
	public static final String HC="\n";
	public static final String RFID="RFID";//门禁腕带
	
	public static final String CMDID_1="1";//门禁注册
	 public static final String CMDID_2="2";//位置变化
	 
	 public static final String POSITION_0="0";//门内
	 public static final String POSITION_1="1";//门外
	 public static final String EVENT_1="01";//外出
	 public static final String EVENT_2="02";//护士报警
}
