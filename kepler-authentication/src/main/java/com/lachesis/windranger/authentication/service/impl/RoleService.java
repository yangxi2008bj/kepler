package com.lachesis.windranger.authentication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lachesis.windranger.authentication.dao.SysRoleMapper;
import com.lachesis.windranger.authentication.dao.SysRoleResourceMappingMapper;
import com.lachesis.windranger.authentication.dao.SysUserRoleMappingMapper;
import com.lachesis.windranger.authentication.dbmodel.SysRole;
import com.lachesis.windranger.authentication.dbmodel.SysRoleResourceMapping;
import com.lachesis.windranger.authentication.dbmodel.SysUserRoleMapping;
import com.lachesis.windranger.authentication.service.IRoleService;
import com.lachesis.windranger.common.exception.RfNotFoundException;
import com.lachesis.windranger.common.util.UpdateOperationValidation;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private SysRoleMapper roleDaoMapper;

	@Autowired
	private SysUserRoleMappingMapper userRoleMappingDaoMapper;

	@Autowired
	private SysRoleResourceMappingMapper roleResourceMappingDaoMapper;

	public List<SysRole> getAllRoles() {

		return this.roleDaoMapper.selectAll();
	}

	public SysRole addRole(SysRole role) {

		this.roleDaoMapper.insertAndReturnKey(role);
		return role;
	}

	public SysRole updateRole(String roleCode, SysRole role) {

		UpdateOperationValidation.validate(role.getRoleCode(), roleCode);
		
		if(1 == this.roleDaoMapper.updateByPrimaryKey(role)) {
			return role;
		}

		throw new RfNotFoundException("未找到指定的角色(seqId:%s)", role.getSeqId());	
	}

	@Transactional
	public void deleteRoleByCode(String roleCode) {
		
		SysRole deleteCondition = new SysRole();
		deleteCondition.setRoleCode(roleCode);
		

		if(this.roleDaoMapper.deleteByBean(deleteCondition) != 0){

			SysUserRoleMapping urDeleteCondition = new SysUserRoleMapping();
			urDeleteCondition.setRoleCode(roleCode);
			this.userRoleMappingDaoMapper.deleteByBean(urDeleteCondition);

			SysRoleResourceMapping rrDeleteCondition = new SysRoleResourceMapping();
			rrDeleteCondition.setRoleCode(roleCode);
			this.roleResourceMappingDaoMapper.deleteByBean(rrDeleteCondition);
		}
		else {

			throw new RfNotFoundException("未找到指定的角色(roleCode:%s)", roleCode);	
		}
	}

		
}
