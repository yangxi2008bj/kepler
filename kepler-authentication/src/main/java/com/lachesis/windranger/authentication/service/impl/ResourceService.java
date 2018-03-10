package com.lachesis.windranger.authentication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lachesis.windranger.authentication.dao.SysResourceMapper;
import com.lachesis.windranger.authentication.dao.SysRoleResourceMappingMapper;
import com.lachesis.windranger.authentication.dbmodel.SysResource;
import com.lachesis.windranger.authentication.dbmodel.SysRoleResourceMapping;
import com.lachesis.windranger.authentication.service.IResourceService;
import com.lachesis.windranger.common.exception.RfForbiddenException;
import com.lachesis.windranger.common.exception.RfNotFoundException;
import com.lachesis.windranger.common.util.UpdateOperationValidation;

@Service
public class ResourceService implements IResourceService {

	@Autowired
	private SysResourceMapper resourceDaoMapper;

	@Autowired
	private SysRoleResourceMappingMapper roleResourceDaoMappingMapper;

	public List<SysResource> getAllResources() {

		return resourceDaoMapper.selectAll();
	}

	public SysResource addResource(SysResource resource) {

		if(resource.getResourceType().equals("0") || resource.getResourceType().equals("1") ||resource.getResourceType().equals("2")) {

			this.resourceDaoMapper.insertAndReturnKey(resource);
			return resource;
		}

		throw new RfForbiddenException("资源的类型(resourceType)值错误，值: 0-应用模块，1-实体资源");
	}


	public SysResource updateResource(String resourceCode, SysResource resource) {

		UpdateOperationValidation.validate(resource.getResourceCode(), resourceCode);

		if(1 == this.resourceDaoMapper.updateByPrimaryKey(resource)) {
			return resource;			
		}

		throw new RfNotFoundException("未找到指定的资源(seqId:%s)", resource.getSeqId());	
	}


	@Transactional
	public void deleteResourceByCode(String resourceCode) {

		SysResource deleteCondtion = new SysResource();
		deleteCondtion.setResourceCode(resourceCode);

		if(this.resourceDaoMapper.deleteByBean(deleteCondtion) != 0) {

			SysRoleResourceMapping deleteCondition = new SysRoleResourceMapping();
			deleteCondition.setResourceCode(resourceCode);
			this.roleResourceDaoMappingMapper.deleteByBean(deleteCondition);
			return;
		}

		throw new RfNotFoundException("找不到指定的资源(resourceCode:%s)", resourceCode);		
	}

}
