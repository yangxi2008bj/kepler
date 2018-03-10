/**   
 * Project Name: windranger-common
 * File Name: MnisConstants.java 
 * @Package com.lachesis.windranger.common.constants  
 * @author ziwei.liu   
 * @date 2017年6月5日 上午10:43:59 
 * @version V1.0   
 * Copyright (c) 2017, Shenzhen Lachesis Mhealth Co., Ltd All Rights Reserved.
 * 
 */
package com.lachesis.windranger.common.constants;

/**
 * @ClassName MnisConstants
 * @Description 移动护理的常量
 * @author ziwei.liu
 * @date 2017年6月5日 上午10:43:59
 * 
 */
public class MnisConstants {
	
	private MnisConstants(){}

	public static final String PROCESS_ORDER_EXECUTE = "order_execute";

	public static final String PROCESS_VAR_BARCODE = "orderBarcode";
	public static final String PROCESS_VAR_REPEAT_INDICATOR = "repeatIndicator";
	// public static final String PROCESS_VAR_ORDER_TYPE = "orderType"; // 医嘱类型：
	// 输液，口服药，检验等
	public static final String PROCESS_VAR_WARD_CODE = "wardCode";
	public static final String PROCESS_VAR_PAT_INHOS_CODE = "patInhosCode";
	public static final String PROCESS_VAR_PLAN_TIME = "planTime";

	public static final String ORDER_STATUS_INIT = "0"; // 开立
	public static final String ORDER_STATUS_VERIFIED = "1"; // 审核
	public static final String ORDER_STATUS_EXECUTE = "2"; // 执行
	public static final String ORDER_STATUS_STOP = "3"; // 作废, 停止
	
	public static final String ORDER_TO_EXECUTE = "execute"; // 待执行code
	
	// 医嘱执行状态
	public static final int ORDER_EXE_STATE_NO = 0; // 未执行
	public static final int ORDER_EXE_STATE_ACTIVE = 1; // 执行中
	public static final int ORDER_EXE_STATE_YES = 2; // 已执行


	
	public static final String ORDER_EXE_TYPE_INFUSION="INFUSION"; // 输液类型医嘱
	
	public static final String DIC_TYPE_ORDER_USAGE="orderUsage"; // 医嘱执行方式字典类型
	public static final String DIC_TYPE_ADMIN_GROUP="orderType"; // 用法组
	public static final String CONFIG_CODE_DISPENSE_ORDER_TYEP="dispenseOrderType"; // 医嘱执行方式字典类型
	public static final String CONFIG_CODE_ORDER_CLASS_SHOW="orderClassToShow"; // 医嘱会显示的类型
	public static final String CONFIG_CODE_ADMIN_CODE_FILTER="adminCodeToFilter"; // 医嘱会显示的类型
	public static final String CONFIG_CODE_DISP_TIME_RANGE="dispenseTimeRange"; // 医嘱会显示的类型
	public static final String CONFIG_CODE_SEARCH_ORIGINAL_ORDER="searchOriginal"; // 是否单独查询原始医嘱表
	public static final String CONFIG_CODE_SHOW_STOP_ORDER="showStopOrder"; // 是否单独查询原始医嘱表
	public static final String CONFIG_CODE_SPLIT_KEY="orderbarSplitKey"; // 包药机混合医嘱条码的分格符号
	public static final String CONFIG_CODE_DIC_CODE_FILTER="dicCode";//需要过滤的0点时刻的数据
	
	public static final String INFUSION_ORDER_STATUS_I = "I"; // 执行中
	public static final String INFUSION_ORDER_STATUS_PAUSE = "P"; // 暂停
	public static final String INFUSION_ORDER_STATUS_STOP = "S"; // 终止
	public static final String INFUSION_ORDER_STATUS_FINISH = "F"; // 完成
	public static final String INFUSION_ORDER_STATUS_ED = "E"; // 拔针

	public static final String VITALSIGN_TEMPERATURE = "temperature"; // 体温
	public static final String VITALSIGN_PULSE = "pulse"; // 脉搏
	public static final String VITALSIGN_HEARTRATE = "heartRate"; // 心率
	public static final String VITALSIGN_RP = "breath"; // 呼吸
	public static final String VITALSIGN_PAIN = "pain"; // 疼痛
	public static final String VITALSIGN_BLOODPRESS = "bloodPress"; // 血压
	public static final String VITALSIGN_COOLWAY = "coolway"; //降温方式
	public static final String VITALSIGN_SKIN_TEST = "skinTest"; //皮试
	public static final String VITALSIGN_SKIN_TEST_INFO = "skinTestInfo"; //皮试

	// 工作量类型
	public static final String WORKLOAD_ORDER = "order"; // 医嘱工作量
	public static final String WORKLOAD_VITAL = "vitalSign"; // 体征录入工作量
	public static final String WORKLOAD_MONITOR = "monitor"; // 输液巡视工作量
	public static final String WORKLOAD_SPECIMEN = "specimen"; // 标本采集工作量
	public static final String WORKLOAD_NURSING = "nursing"; // 护理工作量

	public static final String IN_OUT_STAT_TIME = "inOutStatTime";// 出入量统计配置时间点

	public static final int NUM_OF_DAYS_IN_TEMP_SHEET = 7;
	public static final String TEMP_CONFIG_VITALSIGN_RULE = "extractRule";
	public static final String TEMP_CONFIG_VITALSIGN_RULE_HIGHEST = "highest";
	public static final String TEMP_CONFIG_VITALSIGN_RULE_NORMAL = "normal";//只显示录入时间点的体征
	public static final String TEMP_CONFIG_VITALSIGN_TIME_INTERVAL = "timesInterval"; // 体温单配置的时间点
	public static final String ROW_24_HOUR = "row24Hour";
	public static final String TEMP_CONFIG_VITALSIGN_EVENT = "event"; // 事件
	public static final String ROW_12_HOUR = "row12Hour";
	
	public static final String CONFIG_CODE_LAB_BAR_REGEX = "labOrderBar";
	public static final String CONFIG_CODE_USE_LAB_MASTER = "useLabMaster";
	public static final String SPEC_ORDER_EXECUTE_KEY = "spec_order_execute"; // 检验医嘱执行流程key
	public static final String COLLECT = "collect"; // 检验医嘱流程-采集
	public static final String INSPECT = "inspect"; // 检验医嘱流程-送检
	public static final String ORDERBAR = "orderbar"; 
	public static final String FINISHED = "finished"; //已完成
	public static final String ORDER_EXE_TYPE_LAB = "LAB"; //检验类医嘱
	public static final String ORDER_ADMIN_GROUP_BLOOD = "BLOOD"; //检验类医嘱
	
	public static final String EVENT_MEASURE_CODE_SS = "ss"; //体温单事件-手术
	public static final String EVENT_MEASURE_CODE_FM = "fm"; //体温单事件-分娩
	
	public static final String SELFT_DECOMPOSE_ORDER = "selfDecomposeOrder"; //系统是否自己分解医嘱
	public static final String SELF_DECOMPOSE_ORDER_1 = "1"; //自己分解医嘱
	
	public static final int IS_PRINT_1=1;//已打印
	public static final int IS_PRINT_0=0;//未打印
	public static final int IS_LONGTERM_1=1;//长期医嘱
	public static final int IS_LONGTERM_0=0;//临时医嘱
	
	public static final String SPLIT_TYPE_01="01";//医嘱拆分类型 按次/天
	public static final String SPLIT_TYPE_02="02";//医嘱拆分类型 按天
	public static final String SPLIT_TYPE_03="03";//医嘱拆分类型 按周
	public static final String SPLIT_TYPE_04="04";//医嘱拆分类型 按自然周
	public static final String SPLIT_TYPE_05="05";//医嘱拆分类型 立即
	
	public static final String PAT_PATROL_EVENT="patPatrolEvent";
	
	public static final int ORDER_SORT_NO_1=1; //医嘱拆分一天执行多次,当天内的序号,1表示第一个时间点
	
	public static final String CONFIG_HIDE_ORDER_STOPED="hideOrderStoped";//打印标签时是否隐藏计划时间在停止时间后的医嘱
	public static final String CONFIG_HIDE_ORDER_STOPED_1="1";//1代表不显示
	public static final String ORDER_INOUT_TO_DOC="OrderInoutToDoc";//执行医嘱包含出入量数据转抄至文书
	public static final String ORDER_INOUT_TO_DOC_1="1";//1表示执行转抄
	
	
	

}
