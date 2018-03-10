package com.lachesis.windranger.common.vo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BaseMapVo extends BaseVo{
	private static final long serialVersionUID = 1L;
	
	private Map<String , Object> data = new HashMap<String,Object>();
	
	
	public BaseMapVo() {
		super();
	}

	public BaseMapVo(String ackResult, String msg) {
		super(ackResult, msg);
		// TODO Auto-generated constructor stub
	}

	public Map<String , Object> getData() {
		return data;
	}

	public void setData(Map<String , Object> data) {
		this.data = data;
	}
	
	public void addData(String key,Object value){
		this.data.put(key, value);
	}
	
}
