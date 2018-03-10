package com.lachesis.windranger.authentication.dao;

import java.util.List;

import com.lachesis.windranger.authentication.dbmodel.SysUserRoleMappingExt;
import com.lachesis.windranger.common.model.ISearchableDAO;

public interface SysUserRoleMappingMapperExt extends ISearchableDAO {
	
	public List<SysUserRoleMappingExt> selectUserRoleMappingByUserCode(String userCode);
}