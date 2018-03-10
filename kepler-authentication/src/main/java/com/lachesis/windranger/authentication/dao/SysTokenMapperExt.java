package com.lachesis.windranger.authentication.dao;

import com.lachesis.windranger.authentication.dbmodel.SysToken;
import com.lachesis.windranger.common.model.ISearchableDAO;

public interface SysTokenMapperExt extends ISearchableDAO {
	public SysToken getSysToken(SysToken sysToken); 
	
	public void updateSysToken(SysToken sysToken);
}