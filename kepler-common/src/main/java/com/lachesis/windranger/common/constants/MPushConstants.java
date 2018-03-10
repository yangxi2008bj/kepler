/**   
 * @Title: MPushConstants.java 
 * @Package com.lachesis.windranger.common.constants 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Xi   
 * @date 2017年4月12日 上午10:58:23 
 * @version V1.0   
 */
package com.lachesis.windranger.common.constants;

/** 
 * @ClassName: MPushConstants 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Xi xi.yang@lachesis-mh.com
 * @date 2017年4月12日 上午10:58:23 
 *  
 */
public class MPushConstants {
	public static final String MPUSH_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCghPCWCobG8nTD24juwSVataW7iViRxcTkey/B792VZEhuHjQvA3cAJgx2Lv8GnX8NIoShZtoCg3Cx6ecs+VEPD2fBcg2L4JK7xldGpOJ3ONEAyVsLOttXZtNXvyDZRijiErQALMTorcgi79M5uVX9/jMv2Ggb2XAeZhlLD28fHwIDAQAB";
    public static final String MSG_TYPE_WORK = "work"; //工作消息
    public static final String MSG_TYPE_USERLIST = "userList"; //用户列表消息
    //需配置
    public static final String ALLOC_SERVER = "http://10.2.10.122:9999/"; 
    public static final String SERVER_HOST = "10.2.10.122";
    public static final String OPER_SYS = "windows";
    public static final String OPER_SYS_VERSION = "2008";
    public static final String USER_ID = "LDM-SERVER";
    public static final String DEVICE_ID = "LDM-SERVER";
    
    //消息类型
    public static final String MSG_BLUEBRIDGE_STATUS = "BLUEBRIDGE_STATUS";
    public static final String MSG_BLUETOOTH_DEVICE_STATUS = "INFUSIONDEVICE_STATUS";
    public static final String MSG_BATTERY_STATUSINFO = "BATTERY_STATUSINFO";
    
    public static final String MSG_DETECTOR_BASICINFO = "INFUSIONDEVICE_BASEINFO";
    public static final String MSG_BLUEBRIDGE_BASICINFO = "BLUEBRIDGE_BASEINFO";
    public static final String MSG_WINDOWS_BASEINFO = "WINDOWS_BASEINFO";
    
    // NDA消息
    public static final String MSG_ANDROID_BASE_INFO = "0"; // android_base_info
    public static final String MSG_ANDROID_BATTERY_INFO = "1"; // android_battery_info
    public static final String MSG_NDA_STATUS = "2"; // NDA_STATUS
    public static final String MSG_NDA_EXCPT_INFO = "3"; // android_excp_info
    
    public static final String MSG_DEVICE_DATA = "DeviceData";
   
    
    //扩展字段
    public static final String EXT_Field_LAN = "lanMac";
    public static final String EXT_Field_BLUETOOTH = "bluetoothMacList";
    public static final String EXT_Field_VERSION = "version";
    public static final String EXT_FIELD_SUBMODEL = "subModel";
    public static final String EXT_FIELD_SCREEN = "screen";
    public static final String EXT_FIELD_SCANNING = "scanning";
    public static final String EXT_FIELD_BATTERY = "battery";
    public static final String EXT_FIELD_WIFI = "wifi";
    public static final String EXT_FIELD_CPU = "cpu";
    public static final String EXT_FIELD_ROM = "rom";
    public static final String EXT_FIELD_RAM = "ram";
    public static final String EXT_FIELD_CAMERA = "camera";
    public static final String EXT_FIELD_BLUETOOTHMAC = "bluetoothMac";
    public static final String EXT_FIELD_WIFIMAC = "wifiMac";
    public static final String EXT_FIELD_WIFI_IP = "wifiIP";
    // Windows的扩展字段
    public static final String EXT_FIELD_COMPUTERNAME = "computerName";
    public static final String EXT_FIELD_MEMORYTOTAL = "memoryTotal";
    public static final String EXT_FIELD_OSNAME = "osName";
    public static final String EXT_FIELD_EXTRA_MACADDRESS = "extraMacAddress";
    // window电池扩展字段
    public static final String EXT_FIELD_BAT_PREFIX = "battery-";
    public static final String EXT_FIELD_CAPACITY = "capacity";
    public static final String EXT_FIELD_CYCLECOUNT = "cycleCount";
    public static final String EXT_FIELD_FULLCAPACITY = "fullCapacity";
    public static final String EXT_FIELD_MFD = "mfd";
    public static final String EXT_FIELD_MANUFACTURER = "manufacturer";
    public static final String EXT_FIELD_USEDATE = "useDate";
    public static final String EXT_FIELD_VOLTAGE = "voltage";

    
    //设备中文名字
    public static final String BLUE_BRIDGE_NAME = "蓝桥";
    public static final String DETECTOR_NAME = "检测器";
    
    // 设备名
    public static final String ANDROID_NAME = "Android";
    
    public static final int NDA_TYPE = 1001;
    public static final int BLUE_BRIDGE_TYPE = 1004; // 蓝桥
    
    
    public static final int STATUS_NEW_DEVICE = 10000;
    
    // 设备子型号code
    public static final int SUBMODEL_DB400 = 3001;
    public static final int SUBMODEL_IM620 = 3002;
    public static final int SUBMODEL_PW100 = 3003;
    public static final int SUBMODEL_MSG100 = 3004;
    public static final int SUBMODEL_IDTHM01 = 3005;
    public static final int SUBMODEL_IDSPH01 = 3010;
    public static final int SUBMODEL_IDGLU01 = 3013;
    
    // 充放电状态
    public static final String CHARGE_STATUS_DISCHARGE = "2"; // 放电
    
    // 设备信息状态
    public static final int DEVICE_DATA_STATUS_VALID = 1;
    public static final int DEVICE_DATA_STATUS_INVALID = 9;
    
    // 设备数据采集 - 数据类型
    public static final String EQP_TYPE_SAMO4 = "Samo4";
    public static final String EQP_TYPE_KIDOO = "KIDOO";
    public static final String EQP_TYPE_LOCATION = "location";
    public static final String EQP_TYPE_TAIDOC = "TAIDOC";
    public static final String EQP_TYPE_FORA = "FORA";
    public static final String EQP_TYPE_TD4286 = "TD4286";
    
    public static final String DEVICE_MEDXING_PULSE_OXIMETER = "MEDXING-Sp";
	public static final String DEVICE_MEDXING_EAR_THERMOMETER = "MEDXING-I";
	public static final String DEVICE_MEDXING_BLOOD_PRESSURE = "MEDXING-N";
    
    public static final String DATA_TYPE_OXYGEN = "oxygen";
    public static final String DATA_TYPE_HEART_RATE = "heartRate";
    public static final String DATA_TYPE_TEMPERATURE = "temperature";
    public static final String DATA_TYPE_SYSTOLIC = "systolic";
    public static final String DATA_TYPE_DIASTOLIC = "diastolic";
    public static final String DATA_TYPE_PULSE = "pulse";
    public static final String DATA_TYPE_SUGAR = "sugar";
    public static final String DATA_TYPE_START_TIME = "startTime";
    public static final String DATA_TYPE_STABLE_FLAG = "stable";
    
}
