package com.lachesis.windranger.controller.web;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lachesis.windranger.authentication.dbmodel.PrdCreditor;
import com.lachesis.windranger.authentication.dbmodel.PrdEstate;
import com.lachesis.windranger.authentication.dbmodel.PrdFund;
import com.lachesis.windranger.authentication.dbmodel.PrdImmigration;
import com.lachesis.windranger.authentication.dbmodel.PrdInsurance;
import com.lachesis.windranger.authentication.service.IProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/product", produces = {"application/json;charset=UTF-8"})
@Api(value = "/product", tags = {"Product"}, description = "产品介绍-产品接口")
public class ProductController {
	@Autowired
	IProduct iProduct;

	@RequestMapping(value = {"/menu"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取产品目录", httpMethod = "GET")
	public List<String> getMenu(@ApiParam(value = "type") @RequestParam String roleCode) {
		return iProduct.getMenu(roleCode);
	}
	
	@RequestMapping(value = {"/searchCondition"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取产品目录", httpMethod = "GET")
	public Map<String, List<String>> getSearchConditionByType(@ApiParam(value = "type") @RequestParam String type) {
		return iProduct.getSearchConditionByType(type);
	}
	
	@RequestMapping(value = {"/insurance/{seqId}"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取保险产品详情", httpMethod = "GET")
	public PrdInsurance getInsuranceDetail(@ApiParam(value = "seqId") @PathVariable Long seqId) {
		return iProduct.getInsuranceBySeqId(seqId);
	}

	@RequestMapping(value = {"/insurance"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加保险产品", httpMethod = "POST")
	public void addInsurance(@ApiParam(value = "保险产品") @RequestBody PrdInsurance prdInsurance) {
		iProduct.insertInsurance(prdInsurance);
	}

	@RequestMapping(value = {"/insurance/{seqId}"}, method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "根据seqId删除保险", httpMethod = "DELETE")
	public void deleteResourceByCode(@ApiParam(value = "删除的保险的seqId") @PathVariable Long seqId) {
		iProduct.deleteInsurance(seqId);
	}

	@RequestMapping(value = {"/insurance/search"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询保险", httpMethod = "POST")
	public List<PrdInsurance> searchInsuranceByCondition(
					@ApiParam(value = "搜索条件") @RequestBody PrdInsurance prdInsurance) {
		return iProduct.getInsurancesByCondition(prdInsurance);
	}

	@RequestMapping(value = {"/immigration/{seqId}"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取移民产品详情", httpMethod = "GET")
	public PrdImmigration getImmigrationDetail(
					@ApiParam(value = "seqId") @PathVariable Long seqId) {
		return iProduct.getImmigrationBySeqId(seqId);
	}

	@RequestMapping(value = {"/immigration"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加移民产品", httpMethod = "POST")
	public void addImmigration(
					@ApiParam(value = "移民产品") @RequestBody PrdImmigration prdImmigration) {
		iProduct.insertImmigration(prdImmigration);
	}

	@RequestMapping(value = {"/immigration/{seqId}"}, method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "根据seqId删除移民产品", httpMethod = "DELETE")
	public void deleteImmigrationByCode(@ApiParam(value = "删除的移民的seqId") @PathVariable Long seqId) {
		iProduct.deleteImmigration(seqId);
	}

	@RequestMapping(value = {"/immigration/search"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询移民", httpMethod = "POST")
	public List<PrdImmigration> searchImmigrationByCondition(
					@ApiParam(value = "搜索条件") @RequestBody PrdImmigration prdImmigration) {
		return iProduct.getImmigrationByCondition(prdImmigration);
	}

	@RequestMapping(value = {"/fund/{seqId}"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取基金产品详情", httpMethod = "GET")
	public PrdFund getFundDetail(@ApiParam(value = "seqId") @PathVariable Long seqId) {
		return iProduct.getFundBySeqId(seqId);
	}

	@RequestMapping(value = {"/fund"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加基金产品", httpMethod = "POST")
	public void addFund(@ApiParam(value = "基金产品") @RequestBody PrdFund prdFund) {
		iProduct.insertFund(prdFund);
	}

	@RequestMapping(value = {"/detail"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据seqId和type获取产品详情", httpMethod = "GET")
	public Object getDetail(@ApiParam(value = "产品类型") @RequestParam String type, @ApiParam(value = "产品的seqId") @RequestParam Long seqId) {
		return iProduct.getDetail(type, seqId);
	}
	
	@RequestMapping(value = {"/fund/{seqId}"}, method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "根据seqId删除基金产品", httpMethod = "DELETE")
	public void deleteFundByCode(@ApiParam(value = "删除的基金的seqId") @PathVariable Long seqId) {
		iProduct.deleteFund(seqId);
	}

	@RequestMapping(value = {"/fund/search"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询基金", httpMethod = "POST")
	public List<PrdFund> searchFundByCondition(
					@ApiParam(value = "搜索条件") @RequestBody PrdFund prdFund) {
		return iProduct.getFundByCondition(prdFund);
	}

	@RequestMapping(value = {"/estate/{seqId}"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取房产产品详情", httpMethod = "GET")
	public PrdEstate getEstateDetail(@ApiParam(value = "seqId") @PathVariable Long seqId) {
		return iProduct.getEstateBySeqId(seqId);
	}

	@RequestMapping(value = {"/estate"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加房产产品", httpMethod = "POST")
	public void addEstate(@ApiParam(value = "房产产品") @RequestBody PrdEstate prdEstate) {
		iProduct.insertEstate(prdEstate);
	}

	@RequestMapping(value = {"/estate/{seqId}"}, method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "根据seqId删除房产产品", httpMethod = "DELETE")
	public void deleteEstateByCode(@ApiParam(value = "删除的房产产品的seqId") @PathVariable Long seqId) {
		iProduct.deleteEstate(seqId);
	}

	@RequestMapping(value = {"/estate/search"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询房产产品", httpMethod = "POST")
	public List<PrdEstate> searchEstateByCondition(
					@ApiParam(value = "搜索条件") @RequestBody PrdEstate prdEstate) {
		return iProduct.getEstateByCondition(prdEstate);
	}

	@RequestMapping(value = {"/creditor/{seqId}"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取债权产品详情", httpMethod = "GET")
	public PrdCreditor getCreditorDetail(@ApiParam(value = "seqId") @PathVariable Long seqId) {
		return iProduct.getCreditorBySeqId(seqId);
	}

	@RequestMapping(value = {"/creditor"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加债权产品", httpMethod = "POST")
	public void addCreditor(@ApiParam(value = "债权产品") @RequestBody PrdCreditor prdCreditor) {
		iProduct.insertCreditor(prdCreditor);
	}

	@RequestMapping(value = {"/creditor/{seqId}"}, method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "根据seqId删除债权产品", httpMethod = "DELETE")
	public void deleteCreditorByCode(@ApiParam(value = "删除的债权产品的seqId") @PathVariable Long seqId) {
		iProduct.deleteCreditor(seqId);
	}

	@RequestMapping(value = {"/creditor/search"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "查询债权产品", httpMethod = "POST")
	public List<PrdCreditor> searchCreditorByCondition(
					@ApiParam(value = "搜索条件") @RequestBody PrdCreditor prdCreditor) {
		return iProduct.getCreditorByCondition(prdCreditor);
	}

}
