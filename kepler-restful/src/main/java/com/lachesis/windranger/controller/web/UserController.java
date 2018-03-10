package com.lachesis.windranger.controller.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lachesis.windranger.authentication.dbmodel.SysUser;
import com.lachesis.windranger.authentication.dbmodel.SysUserMapping;
import com.lachesis.windranger.authentication.service.IUserService;
import com.lachesis.windranger.common.constants.StatusMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/authentication", produces = {"application/json;charset=UTF-8"})
@Api(value="/authentication", tags={"Authentication-User"}, description="系统权限-用户接口")
public class UserController {

	@Autowired
	private IUserService userService;	

	@RequestMapping(value={"/users"}, method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取系统所有用户", httpMethod = "GET", response = SysUser.class, responseContainer="List")	
	public List<SysUser> getAllUsers()
	{
		return this.userService.getAllUsers();   
	}

	@RequestMapping(value={"/users/{userCode}"}, method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "通过用户名获取用户", httpMethod = "GET", response = SysUser.class)	
	public SysUser getUserByUserCode(
			@ApiParam(required = true, name = "userCode", value = "用户名")	@PathVariable String userCode)
	{	
		return this.userService.getUserByUserCode(userCode);
	}

	@RequestMapping(value={"/users"}, method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加用户信息", httpMethod = "POST")	
	public SysUser addUser(@ApiParam(required=true, value="用户信息json数据") @RequestBody SysUser user)
	{	
		return this.userService.addUser(user);
	}

	@RequestMapping(value={"/users/{userCode}"}, method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "更新用户信息", httpMethod = "PUT")
	public SysUser updateUser(
			@ApiParam(required=true, value="更新用户Code") @PathVariable String userCode, 
			@ApiParam(required=true, value="更新用户信息数据") @RequestBody SysUser user) {

		return this.userService.updateUser(userCode, user);
	}

	@RequestMapping(value={"/users/{userCode}"}, method=RequestMethod.DELETE)
	@ApiOperation(value = "通过用户Code删除用户", httpMethod = "DELETE")
	public void deleteUserByCode(
			@ApiParam(value = "删除的用户Code") @PathVariable String userCode) {

		this.userService.deleteUserByCode(userCode);
	}

	@RequestMapping(value={"/users/{userCode}/userResourceMappings"}, method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取平台外部系统用户code和密码", httpMethod = "GET")	
	public List<SysUserMapping> getSysUserMapByBean(@ApiParam(required=true, value="用户code") @PathVariable String userCode)
	{	
		SysUserMapping sysUserMapping = new SysUserMapping();
		sysUserMapping.setUserCodeLocal(userCode);
		return userService.getSysUserMapByBean(sysUserMapping);
	}
	
	@RequestMapping(value={"/userResourceMappings"}, method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加外部系统用户code和密码与平台对应关系", httpMethod = "POST")	
	public Map<String, String> addSysUserMapByBean(@ApiParam(required=true, value="用户code,resource,outPwd,outCode") @RequestBody SysUserMapping sysUserMapping)
	{	
		HashMap<String, String> res = new HashMap<>();
		userService.updateSysUserMapBybeans(sysUserMapping);
		res.put("Code", StatusMessage.SUCCESS_MESSAGE);
		return res;
	}
	
	@RequestMapping(value={"/userResourceMappings/{seqId}"}, method=RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "更新外部系统用户code和密码与平台对应关系", httpMethod = "PUT", response = HashMap.class, responseContainer="HashMap")	
	public Map<String, String> updateSysUserMapByBean(@ApiParam(required=true, value="更新的关联记录seqId") @PathVariable long seqId,
			@ApiParam(required=true, value="用户code,resource,outPwd,outCode") @RequestBody SysUserMapping sysUserMapping)
	{	
		HashMap<String, String> res = new HashMap<>();
		sysUserMapping.setSeqId(seqId);
		userService.updateSysUserMapBybeans(sysUserMapping);
		res.put("Code", StatusMessage.SUCCESS_MESSAGE);
		return res;
	}
	@RequestMapping(value={"/userResourceMappings/{seqId}"}, method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "删除外部系统用户code和密码与平台对应关系", httpMethod = "DELETE", response = HashMap.class, responseContainer="HashMap")	
	public Map<String, String> deleteSysUserMapBySeqId(@ApiParam(required=true, value="删除的关联记录seqId")  @PathVariable long seqId)
	{	
		HashMap<String, String> res = new HashMap<>();
		userService.deleteSysUserMapBySeqId(seqId);
		res.put("Code", StatusMessage.SUCCESS_MESSAGE);
		return res;
	}
}
