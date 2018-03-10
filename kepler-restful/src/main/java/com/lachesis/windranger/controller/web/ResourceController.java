package com.lachesis.windranger.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lachesis.windranger.authentication.dbmodel.SysResource;
import com.lachesis.windranger.authentication.service.IResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/authentication", produces = { "application/json;charset=UTF-8" })
@Api(value = "/authentication", tags = { "Authentication-Resource" }, description = "系统权限-资源接口")
public class ResourceController implements IResourceService {

	@Autowired
	private IResourceService resourceService;

	@Override
	@RequestMapping(value = { "/resources" }, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取所有系统资源", httpMethod = "GET", response = SysResource.class, responseContainer = "List", notes = "")
	public List<SysResource> getAllResources() {

		return this.resourceService.getAllResources();
	}

	@Override
	@RequestMapping(value = { "/resources" }, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加系统资源", httpMethod = "POST", response = SysResource.class, notes = "")
	public SysResource addResource(
			@ApiParam(required = true, name = "resource", value = "添加的资源信息") @RequestBody SysResource resource) {

		return this.resourceService.addResource(resource);
	}

	@Override
	@RequestMapping(value = { "/resources/{resourceCode}" }, method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "更新系统资源", httpMethod = "PUT", response = SysResource.class)
	public SysResource updateResource(@ApiParam(value = "更新的资源") @PathVariable String resourceCode,
			@ApiParam(value = "更新的资源信息") @RequestBody SysResource resource) {
		return this.resourceService.updateResource(resourceCode, resource);
	}

	@Override
	@RequestMapping(value = { "/resources/{resourceCode}" }, method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "根据资源Code删除系统资源", httpMethod = "DELETE")
	public void deleteResourceByCode(@ApiParam(value = "删除的资源Code") @PathVariable String resourceCode) {

		this.resourceService.deleteResourceByCode(resourceCode);
	}

}
