package com.lachesis.windranger.common.constants;

public class AttendConstants {
	
	public static final String HLPGD="hlpgd";//护理评估单
	public static final String HLJLD="hljld";//护理记录单
	public static final String RISK_LEVEL_CONFIG="riskLevel";//评估单得分级别配置
	public static final String DOC_TO_VITAL_MAPPING="docToVitalMapping";//文书转抄至体征表字段映射配置
	
	/**
	 * 护理记录数据来源
	 * @author administrator
	 *
	 */
	public enum AttendDataSource{
		DOC,VITAL,ORDER
	} 
}
