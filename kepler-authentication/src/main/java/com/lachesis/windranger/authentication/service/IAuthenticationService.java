package com.lachesis.windranger.authentication.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lachesis.windranger.authentication.dbmodel.SysRoleResourceMapping;
import com.lachesis.windranger.authentication.dbmodel.SysToken;
import com.lachesis.windranger.authentication.dbmodel.SysUserRoleMapping;
import com.lachesis.windranger.authentication.dbmodel.SysUserRoleMappingExt;
import com.lachesis.windranger.authentication.model.WrToken;
import com.lachesis.windranger.authentication.model.vo.LoginModel;

public interface IAuthenticationService {

	/**
	 * 签名验证用户名-密码是否正确
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	public boolean verifyUserNamePassword(String userCode, String userPassword);

	public WrToken getToken(LoginModel loginModel, HttpServletRequest request,
					HttpServletResponse response);

	public SysUserRoleMapping createUserRoleMapping(SysUserRoleMapping mapping);

	public void deleteUserRoleMapping(Long seqId);

	public SysRoleResourceMapping createRoleResrouceMapping(SysRoleResourceMapping mapping);

	public SysRoleResourceMapping updateRoleReourceMapping(Long seqId,
					SysRoleResourceMapping mapping);

	public void deleteRoleResourceMapping(Long seqId);


	public List<SysUserRoleMappingExt> getRoleMappingsByUserCode(String userCode);

	public List<SysRoleResourceMapping> getResourceMappingsByRoleCode(String roleCode);

	/**
	 * 
	 * @Title: getWrToken 
	 * @Description: 从数据库获取令牌 
	 * @param @param wrToken
	 * @param @return    设定文件 
	 * @return WrToken    返回类型 
	 * @throws
	 */
	public SysToken getSysToken(SysToken sysToken);

	/**
	 * 
	 * @Title: generateSaveToken 
	 * @Description: 新生成一个令牌，并保存在数据库 
	 * @param @param sysToken
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return SysToken    返回类型 
	 * @throws
	 */
	public SysToken generateSaveToken(SysToken sysToken) throws Exception;

	/**
	 * 
	 * @Title: updateToken 
	//	 * @Description: 过期更新，或者请求更新token的过期时间 
	 * @param @param sysToken
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return SysToken    返回类型 
	 * @throws
	 */
	public SysToken updateToken(SysToken sysToken) throws Exception;

	public String getCurrentUser(String token);
	
	void forgetPassword(String userCode);
}
