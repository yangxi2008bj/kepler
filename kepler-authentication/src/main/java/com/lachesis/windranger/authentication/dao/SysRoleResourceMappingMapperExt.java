package com.lachesis.windranger.authentication.dao;

import java.util.List;

import com.lachesis.windranger.authentication.dbmodel.SysRoleResourceMappingExt;
import com.lachesis.windranger.common.model.ISearchableDAO;

public interface SysRoleResourceMappingMapperExt extends ISearchableDAO {
	
	public List<SysRoleResourceMappingExt> selectRoleResourceMappingByRoleCode(String roleCode);
	
}