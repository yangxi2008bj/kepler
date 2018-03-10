package com.lachesis.windranger.authentication.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lachesis.windranger.authentication.dao.SysRoleMapperExt;
import com.lachesis.windranger.authentication.dao.SysRoleResourceMappingMapper;
import com.lachesis.windranger.authentication.dao.SysTokenMapper;
import com.lachesis.windranger.authentication.dao.SysTokenMapperExt;
import com.lachesis.windranger.authentication.dao.SysUserMapper;
import com.lachesis.windranger.authentication.dao.SysUserMapperExt;
import com.lachesis.windranger.authentication.dao.SysUserRoleMappingMapper;
import com.lachesis.windranger.authentication.dao.SysUserRoleMappingMapperExt;
import com.lachesis.windranger.authentication.dbmodel.SysRole;
import com.lachesis.windranger.authentication.dbmodel.SysRoleResourceMapping;
import com.lachesis.windranger.authentication.dbmodel.SysToken;
import com.lachesis.windranger.authentication.dbmodel.SysUser;
import com.lachesis.windranger.authentication.dbmodel.SysUserRoleMapping;
import com.lachesis.windranger.authentication.dbmodel.SysUserRoleMappingExt;
import com.lachesis.windranger.authentication.model.WrToken;
import com.lachesis.windranger.authentication.model.vo.LoginModel;
import com.lachesis.windranger.authentication.model.vo.MailDTO;
import com.lachesis.windranger.authentication.service.IAuthenticationService;
import com.lachesis.windranger.authentication.service.IFileManager;
import com.lachesis.windranger.common.constants.CommonConstants;
import com.lachesis.windranger.common.exception.RfException;
import com.lachesis.windranger.common.exception.RfNotFoundException;
import com.lachesis.windranger.common.util.AESUtil;
import com.lachesis.windranger.common.util.HttpRequestDeviceUtils;
import com.lachesis.windranger.common.util.IPAddressUtil;
import com.lachesis.windranger.common.util.StringUtils;
import com.lachesis.windranger.common.util.UpdateOperationValidation;

@Service
public class AuthenticationService implements IAuthenticationService {

	@Autowired
	private SysUserRoleMappingMapper userRoleMappingDaoMapper;
	@Autowired
	private SysRoleResourceMappingMapper roleResourceMappingDaoMapper;
	@Autowired
	SysUserRoleMappingMapperExt userRoleMapingDaoMapperExt;
	@Autowired
	SysUserMapper sysUserMapper;
	@Autowired
	SysUserMapperExt sysUserMapperExt;
	@Autowired
	SysRoleMapperExt sysRoleMapperExt;
	@Autowired
	SysTokenMapperExt sysTokenMapperExt;
	@Autowired
	SysTokenMapper sysTokenMapper;
	@Autowired
	IFileManager iFileManager; 

	private static Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

	@Override
	public boolean verifyUserNamePassword(String userCode, String userPassword) {
		boolean isSuccess = false;
		SysUser conditionSysUser = new SysUser();
		conditionSysUser.setUserCode(userCode);
		// conditionSysUser.setUserPassword(HmacSHA256Utils.encryptMD5App(userPassword));
		conditionSysUser.setUserPassword(userPassword);
		List<SysUser> sysUsers = sysUserMapper.selectByBean(conditionSysUser);
		if (sysUsers != null && sysUsers.size() > 0) {
			isSuccess = true;
		}
		conditionSysUser = new SysUser();
		conditionSysUser.setHisCode(userCode);
		conditionSysUser.setUserPassword(userPassword);

		sysUsers = sysUserMapper.selectByBean(conditionSysUser);
		if (sysUsers != null && sysUsers.size() > 0) {
			isSuccess = true;
		}
		return isSuccess;
	}

	@Override
	public String getCurrentUser(String token) {
		String userCode = getInfoByToken(token).get("uid");
		SysUser conditionSysUser = new SysUser();
		conditionSysUser.setUserCode(userCode);
		List<SysUser> sysUsers = sysUserMapper.selectByBean(conditionSysUser);
		if (CollectionUtils.isNotEmpty(sysUsers)) {
			return userCode;
		} else {
			conditionSysUser = new SysUser();
			conditionSysUser.setHisCode(userCode);
			List<SysUser> sysUsers2 = sysUserMapper.selectByBean(conditionSysUser);
			if (CollectionUtils.isNotEmpty(sysUsers2)) {
				return sysUsers2.get(0).getUserCode();
			}
		}
		return "";
	}

	private HashMap<String, String> getInfoByToken(String token) {
		HashMap<String, String> map = new HashMap<>();
		if (token == null || token.length() < 1) {
			return map;
		}
		try {
			String currentUser = AESUtil.decrypt(token, CommonConstants.AES_SEED);
			String[] type = currentUser.split("&");
			for (int i = 0; i < type.length; i++) {
				String[] val = type[i].split("=");
				if (val.length > 1) {
					map.put(val[0], val[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public WrToken getToken(LoginModel loginModel, HttpServletRequest request,
					HttpServletResponse response) {

		SysUser sysUser = new SysUser();
		sysUser.setUserCode(loginModel.getUserCode());
		sysUser.setHisCode(loginModel.getUserCode());
		// sysUser.setUserPassword(HmacSHA256Utils.encryptMD5App(loginModel.getPassword()));
		sysUser.setUserPassword(loginModel.getPassword());
		return getWrToken(sysUser, request);

	}

	private WrToken getWrToken(SysUser sysUser, HttpServletRequest request) {
		List<SysUser> resultUsers = sysUserMapperExt.verifyLoginInfo(sysUser);
		if (resultUsers != null && resultUsers.size() > 0) {
			WrToken token = new WrToken();
			token.setUserCode(resultUsers.get(0).getUserCode());
			token.setUserName(resultUsers.get(0).getUserName());
			SysToken sysToken = new SysToken();
			sysToken.setUserCode(sysUser.getUserCode());
			// 新增token验证，基于AES加密。xi.yang 修改
			String ipAddress = IPAddressUtil.getIpAddress(request);
			boolean isMobile = HttpRequestDeviceUtils.isMobileDevice(request);
			sysToken.setIsmobile(StringUtils.booleanToInt(isMobile));
			sysToken.setIpaddress(ipAddress);
			SysToken dbToken = getSysToken(sysToken);

			try {
				if (dbToken != null) {
					dbToken.setIpaddress(ipAddress);
					sysToken = updateToken(dbToken);
				} else {
					// 补充HisCode查询token
					sysToken.setUserCode(sysUser.getHisCode());
					SysToken hisToken = getSysToken(sysToken);
					if (hisToken != null) {
						hisToken.setIpaddress(ipAddress);
						sysToken = updateToken(hisToken);
					} else {
						sysToken = generateSaveToken(sysToken);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			token.setTokenContent(sysToken.getTokenContent());

			// 获取对应角色列表
			List<SysRole> roles = sysRoleMapperExt.selectRoleListByUserCode(token.getUserCode());
			if (roles == null || roles.size() == 0) {
				throw new RfException("用户还未分配角色");
			}
			token.setRoles(roles);
			return token;
		} else {
			LOG.error("用户名或密码错误");
			throw new RfNotFoundException("用户名或密码错误");
		}
	}

	// 指纹登录
	public WrToken getToken(String userCode, HttpServletRequest request) {

		SysUser sysUser = new SysUser();
		sysUser.setUserCode(userCode);
		return getWrToken(sysUser, request);

	}

	public SysUserRoleMapping createUserRoleMapping(SysUserRoleMapping mapping) {

		this.userRoleMappingDaoMapper.insertAndReturnKey(mapping);
		return mapping;
	}

	public void deleteUserRoleMapping(Long seqId) {

		if (this.userRoleMappingDaoMapper.deleteByPrimaryKey(seqId) == 0) {
			throw new RfNotFoundException("用户角色对应关系", "seqId", seqId.toString());
		}

	}

	public SysRoleResourceMapping createRoleResrouceMapping(SysRoleResourceMapping mapping) {

		this.roleResourceMappingDaoMapper.insertAndReturnKey(mapping);
		return mapping;
	}

	public SysRoleResourceMapping updateRoleReourceMapping(Long seqId,
					SysRoleResourceMapping mapping) {

		UpdateOperationValidation.validate(mapping.getSeqId(), seqId);

		SysRoleResourceMapping updateMapping = new SysRoleResourceMapping();
		updateMapping.setSeqId(seqId);
		updateMapping.setRelation(mapping.getRelation());

		if (this.roleResourceMappingDaoMapper.updateByPrimaryKeySelective(updateMapping) == 0) {

			throw new RfNotFoundException("角色资源对应关系", "seqId", seqId.toString());
		}

		return this.roleResourceMappingDaoMapper.selectByPrimaryKey(seqId);
	}

	public void deleteRoleResourceMapping(Long seqId) {

		if (this.roleResourceMappingDaoMapper.deleteByPrimaryKey(seqId) == 0) {

			throw new RfNotFoundException("角色资源对应关系", "seqId", seqId.toString());
		}

	}

	public List<SysUserRoleMappingExt> getRoleMappingsByUserCode(String userCode) {

		return this.userRoleMapingDaoMapperExt.selectUserRoleMappingByUserCode(userCode);
	}

	public List<SysRoleResourceMapping> getResourceMappingsByRoleCode(String roleCode) {

		SysRoleResourceMapping queryCondition = new SysRoleResourceMapping();
		queryCondition.setRoleCode(roleCode);

		return this.roleResourceMappingDaoMapper.selectByBean(queryCondition);
	}

	/*
	 * (非 Javadoc) <p>Title: getWrToken</p> <p>Description: 用户获取token信息</p>
	 * 
	 * @param wrToken
	 * 
	 * @return
	 * 
	 * @see com.lachesis.windranger.authentication.service.IAuthenticationService#
	 * getWrToken(com.lachesis.windranger.authentication.model.SysToken)
	 */
	public SysToken getSysToken(SysToken sysToken) {
		if ((sysToken.getTokenContent() != null && sysToken.getTokenContent().length() > 0)
						|| (sysToken.getUserCode() != null
										&& sysToken.getUserCode().length() > 0)) {
			return sysTokenMapperExt.getSysToken(sysToken);
		}
		return new SysToken();
	}

	/*
	 * (非 Javadoc) <p>Title: generateSaveToekn</p> <p>Description: 生成并保存token</p>
	 * 
	 * @param wrToken 传入参数必须包含用户名，IP地址，和是否为移动设备三个属性
	 * 
	 * @see com.lachesis.windranger.authentication.service.IAuthenticationService#
	 * generateSaveToekn(com.lachesis.windranger.authentication.model.WrToken)
	 */
	@Transactional
	public SysToken generateSaveToken(SysToken sysToken) throws Exception {
		sysToken = generateToken(sysToken);
		sysTokenMapper.insert(sysToken);
		return sysToken;
	}

	@Transactional
	public SysToken updateToken(SysToken sysToken) throws Exception {
		sysToken = generateToken(sysToken);
		if (sysToken.getSeqId() != null) {
			sysTokenMapperExt.updateSysToken(sysToken);
		}
		return sysToken;
	}

	private SysToken generateToken(SysToken sysToken) throws Exception {
		if (sysToken != null && sysToken.getUserCode() != null && sysToken.getIpaddress() != null) {
			Long expTime = new Date().getTime() + CommonConstants.LIVE_TIME;
			String ori = CommonConstants.TOKEN_USER_NAME + sysToken.getUserCode() + "&"
							+ CommonConstants.TOKEN_IP + sysToken.getIpaddress() + "&"
							+ CommonConstants.TOKEN_EXPIRE_TIME + expTime;
			String encriptedToken = AESUtil.encrypt(ori, CommonConstants.AES_SEED);
			sysToken.setExpiredate(new Date(expTime));
			sysToken.setTokenContent(encriptedToken);
			sysToken.setIsvalid(1);
		}
		return sysToken;
	}

	@Override
	public void forgetPassword(String userCode) {
		SysUser sysUser = new SysUser();
		sysUser.setUserCode(userCode);
		List<SysUser> res = sysUserMapper.selectByBean(sysUser);
		if(res != null && CollectionUtils.isNotEmpty(res)) {
			sysUser = res.get(0);
			MailDTO mailDTO = new MailDTO();
			mailDTO.setTo(sysUser.getHisCode());
			mailDTO.setSubject("忘记密码");
			String message = "你的密码为："+ sysUser.getUserPassword()+" 请及时修改密码";
			mailDTO.setMessage(message);
			iFileManager.sendMail(mailDTO);
		}
	}

}
