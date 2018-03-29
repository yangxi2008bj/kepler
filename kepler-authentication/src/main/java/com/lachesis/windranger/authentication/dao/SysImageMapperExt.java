package com.lachesis.windranger.authentication.dao;

import com.lachesis.windranger.authentication.dbmodel.SysImage;
import com.lachesis.windranger.common.model.ISearchableDAO;

public interface SysImageMapperExt extends ISearchableDAO {
	int insertImage(SysImage sysImage);
}