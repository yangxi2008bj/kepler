package com.lachesis.windranger.authentication.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.lachesis.windranger.authentication.dao.SysUserMapperExt;
import com.lachesis.windranger.authentication.dbmodel.SysUserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lachesis.windranger.authentication.dao.SysRoleMapper;
import com.lachesis.windranger.authentication.dao.SysUserMapper;
import com.lachesis.windranger.authentication.dao.SysUserMappingMapper;
import com.lachesis.windranger.authentication.dao.SysUserRoleMappingMapper;
import com.lachesis.windranger.authentication.dbmodel.SysUser;
import com.lachesis.windranger.authentication.dbmodel.SysUserMapping;
import com.lachesis.windranger.authentication.dbmodel.SysUserRoleMapping;
import com.lachesis.windranger.authentication.service.IUserService;
import com.lachesis.windranger.common.exception.RfNotFoundException;
import com.lachesis.windranger.common.util.UpdateOperationValidation;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	SysUserMapper userDaoMapper;

	@Autowired
	SysUserRoleMappingMapper userRoleMapingDaoMapper;

	@Autowired
	SysUserMappingMapper sysUserMappingMapper;

	@Autowired
	SysUserMapperExt sysUserMapperExt;

	@Autowired
	SysUserRoleMappingMapper sysUserRoleMappingMapper;

	public List<SysUser> getAllUsers() {
		return userDaoMapper.selectAll();
	}

	public SysUser getUserByUserCode(String userCode) {

		SysUser user = new SysUser();
		user.setUserCode(userCode);

		// List<SysUserExt> users = SysUserMapperExt.selectWithWard(user);
		List<String> userCodes = new ArrayList<>();
		userCodes.add(userCode);

		List<SysUser> users = sysUserMapperExt.selectSysUserByUserCodes(userCodes);

		if (users.size() == 1)
			return users.get(0);
		else
			return null;
	}

	public SysUser addUser(SysUser user) {

		this.userDaoMapper.insertAndReturnKey(user);
		SysUserRoleMapping role = new SysUserRoleMapping();
		role.setUserCode(user.getUserCode());
		role.setRoleCode("1");
		role.setCreateTime(new Date());
		role.setCreatePerson("admin");
		sysUserRoleMappingMapper.insertAndReturnKey(role);
		return user;
	}


	public SysUser updateUser(String userCode, SysUser user) {

		UpdateOperationValidation.validate(user.getUserCode(), userCode);

		if (1 == this.userDaoMapper.updateByPrimaryKey(user)) {
			return user;
		}

		throw new RfNotFoundException("未找到指定的资源(userCode:%s)", userCode);
	}


	public void deleteUserByCode(String userCode) {


		SysUser userDeleteCondition = new SysUser();
		userDeleteCondition.setUserCode(userCode);

		if (this.userDaoMapper.deleteByBean(userDeleteCondition) != 0) {

			SysUserRoleMapping mappingDeleteCondition = new SysUserRoleMapping();
			mappingDeleteCondition.setUserCode(userCode);
			this.userRoleMapingDaoMapper.deleteByBean(mappingDeleteCondition);
			return;
		}

		throw new RfNotFoundException("找不到指定的用户(userCode:%s)", userCode);
	}

	@Override
	public List<SysUserMapping> getSysUserMapByBean(SysUserMapping sysUserMapping) {
		// List<SysUserMapping> res = sysUserMappingMapper.selectByBean(sysUserMapping);
		// if(res!=null&&res.size()>0){
		// return res.get(0);
		// }
		return sysUserMappingMapper.selectByBean(sysUserMapping);
		// if(res!=null&&res.size()>0){;
	}

	@Override
	public void updateSysUserMapBybeans(SysUserMapping sysUserMapping) {
		SysUserMapping search = new SysUserMapping();
		search.setUserCodeLocal(sysUserMapping.getUserCodeLocal());
		search.setResourceCode(sysUserMapping.getResourceCode());
		List<SysUserMapping> res = sysUserMappingMapper.selectByBean(search);
		search.setUseCodeOut(sysUserMapping.getUseCodeOut());
		search.setUsePwdOut(sysUserMapping.getUsePwdOut());
		search.setUpdateTime(new Date());
		search.setUpdatePerson(sysUserMapping.getUserCodeLocal());
		if (res != null && res.size() > 0) {
			SysUserMapping tmp = res.get(0);
			search.setCreatePerson(tmp.getCreatePerson());
			search.setCreateTime(tmp.getCreateTime());
			search.setSeqId(tmp.getSeqId());
			sysUserMappingMapper.updateByPrimaryKey(search);
		} else {
			search.setCreatePerson(sysUserMapping.getUserCodeLocal());
			search.setCreateTime(new Date());
			sysUserMappingMapper.insert(search);
		}
	}

	@Override
	public void deleteSysUserMapBySeqId(long seqId) {
		sysUserMappingMapper.deleteByPrimaryKey(seqId);
	}
}
