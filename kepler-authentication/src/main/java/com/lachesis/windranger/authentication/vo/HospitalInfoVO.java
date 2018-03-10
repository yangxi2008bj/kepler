/**   
 * Project Name: windranger-authentication
 * File Name: HospitalInfoVO.java 
 * @Package com.lachesis.windranger.authentication.vo  
 * @author ziwei.liu   
 * @date 2017年8月15日 下午2:04:57 
 * @version V1.0   
 * Copyright (c) 2017, Shenzhen Lachesis Mhealth Co., Ltd All Rights Reserved.
 * 
 */
package com.lachesis.windranger.authentication.vo;

/** 
 * @ClassName HospitalInfoVO 
 * @Description TODO(这里用一句话描述这个类的作用) 
 * @author ziwei.liu 
 * @date 2017年8月15日 下午2:04:57 
 *  
 */
public class HospitalInfoVO {
	private String name;
	private String path;
	private String icon;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
