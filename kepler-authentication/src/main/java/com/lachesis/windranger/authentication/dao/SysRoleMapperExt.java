package com.lachesis.windranger.authentication.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lachesis.windranger.authentication.dbmodel.SysRole;
import com.lachesis.windranger.common.model.ISearchableDAO;

public interface SysRoleMapperExt extends ISearchableDAO {
	
	List<SysRole> selectRoleListByUserCode(@Param("userCode") String userCode);
}