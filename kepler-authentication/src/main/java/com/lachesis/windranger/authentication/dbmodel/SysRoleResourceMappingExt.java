package com.lachesis.windranger.authentication.dbmodel;

import io.swagger.annotations.ApiModelProperty;

public class SysRoleResourceMappingExt extends SysRoleResourceMapping {

	@ApiModelProperty(value="角色名称")
	private String roleName;
	
	@ApiModelProperty(value="资源名称")
	private String resourceName;
	
	@ApiModelProperty(value="资源内容")
	private String resourceContent;
	
	@ApiModelProperty(value="资源类型")
	private String resourceType;
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceContent() {
		return resourceContent;
	}
	public void setResourceContent(String resourceContent) {
		this.resourceContent = resourceContent;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	
	
}