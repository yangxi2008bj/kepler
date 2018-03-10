package com.lachesis.windranger.authentication.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RoleResourceMappingUpdateVo {
	
	@ApiModelProperty(value="更新的关系SeqId")
	private Long roleResourceMappingSeqId;
	
	@ApiModelProperty(value="更新的关系描述")
	private String relation;
	
	
	public Long getRoleResourceMappingSeqId() {
		return roleResourceMappingSeqId;
	}
	public void setRoleResourceMappingSeqId(Long roleResourceMappingSeqId) {
		this.roleResourceMappingSeqId = roleResourceMappingSeqId;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	
}
