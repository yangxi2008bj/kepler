package com.lachesis.windranger.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lachesis.windranger.authentication.dbmodel.SysRole;
import com.lachesis.windranger.authentication.service.IRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/authentication", produces = { "application/json;charset=UTF-8" })
@Api(value = "/authentication", tags = { "Authentication-Role" }, description = "系统权限-角色接口")
public class RoleController implements IRoleService {

	@Autowired
	private IRoleService roleService;

	@Override
	@RequestMapping(value = { "/roles" }, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取所有系统角色", httpMethod = "GET", response = SysRole.class, responseContainer = "List", notes = "")
	public List<SysRole> getAllRoles() {

		return this.roleService.getAllRoles();
	}

	@Override
	@RequestMapping(value = { "/roles" }, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加系统角色", httpMethod = "POST", response = SysRole.class, notes = "")
	public SysRole addRole(@ApiParam(required = true, name = "role", value = "添加的角色信息") @RequestBody SysRole role) {

		return this.roleService.addRole(role);
	}

	@Override
	@RequestMapping(value = { "/roles/{roleCode}" }, method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "更新系统角色", httpMethod = "PUT", response = SysRole.class)
	public SysRole updateRole(@ApiParam(value = "更新的角色Code") @PathVariable String roleCode,
			@ApiParam(value = "更新的角色信息") @RequestBody SysRole role) {

		return this.roleService.updateRole(roleCode, role);
	}

	@Override
	@RequestMapping(value = { "/roles/{roleCode}" }, method = RequestMethod.DELETE)
	@ApiOperation(value = "根据角色Code删除角色", httpMethod = "DELETE")
	public void deleteRoleByCode(
			@ApiParam(required = true, name = "roleCode", value = "删除的角色Code") @PathVariable String roleCode) {

		this.roleService.deleteRoleByCode(roleCode);
	}

}
