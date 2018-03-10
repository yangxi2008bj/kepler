package com.lachesis.windranger.authentication.service;

import java.util.List;

import com.lachesis.windranger.authentication.dbmodel.SysUser;
import com.lachesis.windranger.authentication.dbmodel.SysUserExt;
import com.lachesis.windranger.authentication.dbmodel.SysUserMapping;

public interface IUserService {

	public List<SysUser> getAllUsers();

	public SysUser getUserByUserCode(String userCode);

	public SysUser addUser(SysUser user);

	public SysUser updateUser(String userCode, SysUser user);

	public void deleteUserByCode(String userCode);

	public List<SysUserMapping> getSysUserMapByBean(SysUserMapping sysUserMapping);
	
	public void updateSysUserMapBybeans(SysUserMapping sysUserMapping);
	
	public void deleteSysUserMapBySeqId(long seqId);
}
