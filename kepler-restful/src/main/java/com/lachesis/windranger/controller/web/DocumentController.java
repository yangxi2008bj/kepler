package com.lachesis.windranger.controller.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.lachesis.windranger.authentication.dbmodel.SysAttachment;
import com.lachesis.windranger.authentication.model.vo.MailDTO;
import com.lachesis.windranger.authentication.service.IFileManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/document", produces = {"application/json;charset=UTF-8"})
@Api(value = "/document", tags = {"Document"}, description = "资料库-文件接口")
public class DocumentController {
	@Autowired
	IFileManager iFileManager;

	@RequestMapping(value = {"/upload"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "上传文件", httpMethod = "POST")
	public Map<String, Object> uploadDoc(@ApiParam(value = "文件") MultipartFile file,
					@ApiParam(value = "附件内容") SysAttachment sysAttachment) {
		return iFileManager.uploadFile(file, sysAttachment);
	}
	
	@RequestMapping(value = {"/uploadImage"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "上传文件", httpMethod = "POST")
	public Map<String, Object> uploadImage(@ApiParam(value = "文件") MultipartFile file) {
		return iFileManager.uploadImage(file);
	}

	@RequestMapping(value = {"/download"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "下载文件", httpMethod = "GET")
	public void uploadDoc(@ApiParam(value = "文件") @RequestParam Long seqId,
					HttpServletResponse response) {
		iFileManager.downloadFile(seqId, response);
	}
	
	@RequestMapping(value = {"/downloadImage"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "下载文件", httpMethod = "GET")
	public void downloadImage(@ApiParam(value = "文件") @RequestParam Long seqId,
					HttpServletResponse response) {
		iFileManager.downloadImage(seqId, response);
	}

	@RequestMapping(value = {"/searchFile"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询文件", httpMethod = "POST")
	public List<SysAttachment> searchFileByCondition(
					@ApiParam(value = "搜索条件") @RequestBody SysAttachment sysAttachment) {
		return iFileManager.searchByCondition(sysAttachment);
	}

	@RequestMapping(value = {"/{seqId}"}, method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "根据seqId删除文件", httpMethod = "DELETE")
	public void deleteResourceByCode(@ApiParam(value = "删除的文件的seqId") @PathVariable Long seqId) {
		iFileManager.deleteAttachment(seqId);
	}

	@RequestMapping(value = {"/companyName"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取产品公司", httpMethod = "GET")
	public List<String> getCompanyName() {
		return iFileManager.getCompanyName();
	}

	@RequestMapping(value = {"/sourceType"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取资料类型", httpMethod = "GET")
	public List<String> getSourceType() {
		return iFileManager.getSourceType();
	}
}
