package com.lachesis.windranger.authentication.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户登录model")
public class LoginModel {
	@ApiModelProperty(value="登录userCode")
	private String userCode;
	
	@ApiModelProperty(value="登录passwordd")
	private String password;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
