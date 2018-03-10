package com.lachesis.windranger.authentication.service;

import java.util.List;

import com.lachesis.windranger.authentication.dbmodel.SysResource;

public interface IResourceService {

	public List<SysResource> getAllResources();

	public SysResource addResource(SysResource resource);
	public SysResource updateResource(String resourceCode, SysResource resource);
	public void deleteResourceByCode(String resourceCode);

}
