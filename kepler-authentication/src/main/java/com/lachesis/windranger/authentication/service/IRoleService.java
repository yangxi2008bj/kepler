package com.lachesis.windranger.authentication.service;

import java.util.List;

import com.lachesis.windranger.authentication.dbmodel.SysRole;

public interface IRoleService {

	public List<SysRole> getAllRoles();
	
	public SysRole addRole(SysRole role);
	public SysRole updateRole(String roleCode, SysRole role);
	public void deleteRoleByCode(String roleCode);
	
	
}
