package com.lachesis.windranger.controller.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lachesis.windranger.authentication.dbmodel.SysRoleResourceMapping;
import com.lachesis.windranger.authentication.dbmodel.SysToken;
import com.lachesis.windranger.authentication.dbmodel.SysUserRoleMapping;
import com.lachesis.windranger.authentication.dbmodel.SysUserRoleMappingExt;
import com.lachesis.windranger.authentication.model.WrToken;
import com.lachesis.windranger.authentication.model.vo.LoginModel;
import com.lachesis.windranger.authentication.service.IAuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/authentication", produces = {"application/json;charset=UTF-8"})
@Api(value = "/authentication", tags = {"Authentication"}, description = "系统权限-授权接口")
public class AuthenticationController {

	@Autowired
	IAuthenticationService authenticationService;

	@RequestMapping(value = {"/passwordCorrect"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "签名前验证用户名-密码是否正确", httpMethod = "GET")
	public boolean verifyUserNamePassword(@ApiParam(value = "用户名") @RequestParam String userCode,
					@ApiParam(value = "密码") @RequestParam String userPassword) {
		return authenticationService.verifyUserNamePassword(userCode, userPassword);
	}

	@RequestMapping(value = {"/tokens"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取用户令牌", httpMethod = "POST", response = WrToken.class)
	public WrToken getToken(@ApiParam(value = "用户名/密码") @RequestBody LoginModel loginModel,
					HttpServletRequest request, HttpServletResponse response) {

		return this.authenticationService.getToken(loginModel, request, response);
	}

	@RequestMapping(value = {"/userRoleMappings"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "创建用户与角色关联记录", httpMethod = "POST", response = SysUserRoleMapping.class)
	public SysUserRoleMapping createUserRoleMapping(
					@ApiParam(value = "用户角色对应关系") @RequestBody SysUserRoleMapping mapping) {

		return this.authenticationService.createUserRoleMapping(mapping);
	}

	@RequestMapping(value = {"/userRoleMappings/{seqId}"}, method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "删除用户与角色关联记录", httpMethod = "DELETE")
	public void deleteUserRoleMapping(
					@ApiParam(value = "删除的用户角色关联记录的seqId") @PathVariable Long seqId) {

		this.authenticationService.deleteUserRoleMapping(seqId);
	}

	@RequestMapping(value = {"/roleResourceMappings"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "创建角色与资源关联记录", httpMethod = "POST",
					response = SysRoleResourceMapping.class)
	public SysRoleResourceMapping createRoleResrouceMapping(
					@ApiParam(value = "创建的角色与资源关联记录") @RequestBody SysRoleResourceMapping mapping) {

		return this.authenticationService.createRoleResrouceMapping(mapping);
	}

	@RequestMapping(value = {"/roleResourceMappings/{seqId}"}, method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "更新角色与资源关联记录", httpMethod = "PUT",
					response = SysRoleResourceMapping.class)
	public SysRoleResourceMapping updateRoleReourceMapping(
					@ApiParam(value = "更新的关联记录seqId") @PathVariable Long seqId,
					@ApiParam(value = "更新的关联记录") @RequestBody SysRoleResourceMapping mapping) {

		return this.authenticationService.updateRoleReourceMapping(seqId, mapping);
	}

	@RequestMapping(value = {"/roleResourceMappings/{seqId}"}, method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "删除角色与资源关联记录", httpMethod = "DELETE")
	public void deleteRoleResourceMapping(
					@ApiParam(value = "删除的关联记录seqId") @PathVariable Long seqId) {

		this.authenticationService.deleteRoleResourceMapping(seqId);
	}

	@RequestMapping(value = {"/roles/{roleCode}/roleResourceMappings"}, method = RequestMethod.GET)
	@ApiOperation(value = "通过角色Code获取资源对应列表", httpMethod = "GET",
					response = SysRoleResourceMapping.class, responseContainer = "List")
	public List<SysRoleResourceMapping> getResourceMappingsByRoleCode(
					@ApiParam(value = "获取的角色Code") @PathVariable String roleCode) {

		return this.authenticationService.getResourceMappingsByRoleCode(roleCode);
	}

	@RequestMapping(value = {"/users/{userCode}/userRoleMappings"}, method = RequestMethod.GET)
	@ApiOperation(value = "通过用户Code获取角色对应列表", httpMethod = "GET",
					response = SysUserRoleMappingExt.class, responseContainer = "List")
	public List<SysUserRoleMappingExt> getRoleMappingsByUserCode(
					@ApiParam(value = "获取的用户Code") @PathVariable String userCode) {

		return this.authenticationService.getRoleMappingsByUserCode(userCode);
	}


	@RequestMapping(value = {"/sysToken/getSysToken"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取系统令牌", httpMethod = "POST", response = SysToken.class)
	public SysToken getSysToken(@ApiParam(value = "获取的系统令牌") @RequestBody SysToken sysToken) {
		return this.authenticationService.getSysToken(sysToken);
	}

	/*
	 * (非 Javadoc) <p>Title: generateSaveToken</p> <p>Description: </p>
	 * 
	 * @param sysToken
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see com.lachesis.windranger.authentication.service.IAuthenticationService#
	 * generateSaveToken(com.lachesis.windranger.authentication.dbmodel. SysToken)
	 */
	@RequestMapping(value = {"/sysToken/generateSaveToken"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "产生新的令牌并保存", httpMethod = "POST", response = SysToken.class)
	public SysToken generateSaveToken(@ApiParam(value = "用来产生的基本令牌") SysToken sysToken)
					throws Exception {
		return this.authenticationService.generateSaveToken(sysToken);
	}

	/*
	 * (非 Javadoc) <p>Title: updateToken</p> <p>Description: </p>
	 * 
	 * @param sysToken
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see com.lachesis.windranger.authentication.service.IAuthenticationService#
	 * updateToken(com.lachesis.windranger.authentication.dbmodel.SysToken)
	 */
	@RequestMapping(value = {"/sysToken/updateToken"}, method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "更新令牌并保存", httpMethod = "PUT", response = SysToken.class)
	public SysToken updateToken(@ApiParam(value = "用来更新的令牌") SysToken sysToken) throws Exception {
		return this.authenticationService.updateToken(sysToken);
	}

	@RequestMapping(value = {"/forget"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "通过邮件发送密码", httpMethod = "GET")
	public void sendMail(@ApiParam(value = "用户名") @RequestParam String userCode) {
		authenticationService.forgetPassword(userCode);
	}
}
