package com.lachesis.windranger.common.constants;

public class ComsConstants {
	
	public static final String PATIENT_MANAGE_KEY="patient_manage"; //患者管理流程
	public static final String INSTRUMENT_MANAGE_KEY="instrument_manage";//器械管理流程
	
	
	public static final String COMS_STATUS_0="0"; //手术未开始
	public static final String COMS_STATUS_1="1"; //送程中
	public static final String COMS_STATUS_2="2"; //进行中
	public static final String COMS_STATUS_3="3"; //手术完成
	public static final String COMS_STATUS_4="4"; //返程中
	public static final String COMS_STATUS_5="5"; //已送回
	
	public static final String INSTRUMENT_STAGE_0="0"; //术前清点
	public static final String INSTRUMENT_STAGE_1="1"; //术中添加
	public static final String INSTRUMENT_STAGE_2="2"; //关前清点
	public static final String INSTRUMENT_STAGE_3="3"; //关后清点
	public static final String INSTRUMENT_STAGE_4="4"; //术后清点
	
	
	public static final String SCAN_STAGE_0="0"; //普通扫描
	public static final String SCAN_STAGE_1="1"; //手术开始前安全检查，检查完毕需要开启器械流程
	public static final String SCAN_STAGE_2="2"; //最后一个节点，执行完则手术结束
	
	
	
	public static final String DOC_STAGE_0="0"; //0:患者交接文书填写 ;
	public static final String DOC_STAGE_1="1"; //1：麻醉前文书填写
	public static final String DOC_STAGE_2="2"; //2：手术开始前安全检查文书填写 
	public static final String DOC_STAGE_3="3"; //3：离开手术间前文书填写
	
	public static final String NOTICE_PATIENT="notice_patient";
	public static final String NOTICE_PATIENT_CN="通知患者";
	public static final String SURGERYROOM_OUT="sugeryroom_out";//手术间送出
	public static final String TRANSTEAM_RECEIVE_2="transTeam_receive2";//返程运送队接收
	
	public static final String sugeryJian_sendout="sugeryJian_sendout";//手术间送出 ps:原先的
	
	public static final String HOCUS_RESUME_RECEIVE="hocusResume_receive";
	public static final String deptNurse_receive="deptNurse_receive";
	public static final String dead_declare="dead_declare";
	
	public static final String CONFIRM_CH="确认";
	
	public static final String PATIENT_OUT_GOING_KEY_CN="患者去向";
	
	
	public static final String SICKROOM_OUT="sickroom_out";//术前护士
	public static final String BEFORE_SURGERY_NURSE_RECEIVE="before_surgery_nurse_receive";//手术室护士接收
	public static final String BEFORE_HOCUS_CHECK="beforeHocus_check";//麻醉前安全核查
	public static final String CAREWORKER_RECEIVE_2="careWorker_receive2";//手术室护工接收
	public static final String BEFORE_SURGERY_CHECK="beforeSugery_check";//术前安全核查
	public static final String AFTER_SURGERY_CHECK="afterSurgery_check";//术后安全核查
	public static final String HOCUS_RESUME_OUT="hocusResume_out";//复苏室送出
	
	public static final String ICU_RECEIVE="ICU_receive";//复苏室送出
	public static final String SICK_ROOM_RECEIVE="sickroom_receive";//复苏室送出
	public static final String EMERGENCY_RECEIVE="emergency_receive";//复苏室送出
	public static final String LEAVE_HOSPITAL="leave_hospital";//复苏室送出
	public static final String OTHERS_RECEIVE="others_receive";//复苏室送出
	
	// 手术阶段编码
	public static final String SURGERY_STAGE_1="1"; // 手术间阶段
	public static final String SURGERY_STAGE_2="2"; // 复苏间阶段
	
	// 字典类型
	public static final String DICT_TYPE_SURGERY_STAGE="surgeryStageCode"; // 手术阶段字典类型
	public static final String DICT_TYPE_TRANSFUSION="transfusionType"; // 术中记录字典类型
	public static final String DICT_TYPE_BLOOD_TRANSFUSION="bloodTransfusionType"; // 输血字典类型
	
	public static final String BLOOD_CHECK_STAGE_01="01"; // 血袋核对
	public static final String BLOOD_CHECK_STAGE_02="02"; // 患者核对
	
	
}
