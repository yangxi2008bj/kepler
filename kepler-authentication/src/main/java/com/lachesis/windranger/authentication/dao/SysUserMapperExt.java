package com.lachesis.windranger.authentication.dao;

import com.lachesis.windranger.authentication.dbmodel.SysUser;
import com.lachesis.windranger.authentication.dbmodel.SysUserExt;
import com.lachesis.windranger.common.model.ISearchableDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapperExt extends ISearchableDAO {

	List<SysUserExt> selectWithWard(SysUser bean);

	/**
	 * 根据用户编号列表查询用户信息
	 * @param userCodes
	 * @return
	 */
	List<SysUser> selectSysUserByUserCodes(@Param("userCodes") List<String> userCodes);

	List<SysUser> verifyLoginInfo(SysUser sysUser);
}
