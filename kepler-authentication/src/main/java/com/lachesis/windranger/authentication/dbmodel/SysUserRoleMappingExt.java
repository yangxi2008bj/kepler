package com.lachesis.windranger.authentication.dbmodel;

import io.swagger.annotations.ApiModelProperty;

public class SysUserRoleMappingExt extends SysUserRoleMapping {
	
	@ApiModelProperty(value="用户姓名")
	private String userName;
	
	@ApiModelProperty(value="角色名称")
	private String roleName;
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
}