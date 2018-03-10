package com.lachesis.windranger.authentication.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lachesis.windranger.authentication.dao.PrdCreditorMapper;
import com.lachesis.windranger.authentication.dao.PrdCreditorMapperExt;
import com.lachesis.windranger.authentication.dao.PrdEstateMapper;
import com.lachesis.windranger.authentication.dao.PrdEstateMapperExt;
import com.lachesis.windranger.authentication.dao.PrdFundMapper;
import com.lachesis.windranger.authentication.dao.PrdFundMapperExt;
import com.lachesis.windranger.authentication.dao.PrdImmigrationMapper;
import com.lachesis.windranger.authentication.dao.PrdImmigrationMapperExt;
import com.lachesis.windranger.authentication.dao.PrdInsuranceMapper;
import com.lachesis.windranger.authentication.dao.PrdInsuranceMapperExt;
import com.lachesis.windranger.authentication.dbmodel.PrdCreditor;
import com.lachesis.windranger.authentication.dbmodel.PrdEstate;
import com.lachesis.windranger.authentication.dbmodel.PrdFund;
import com.lachesis.windranger.authentication.dbmodel.PrdImmigration;
import com.lachesis.windranger.authentication.dbmodel.PrdInsurance;
import com.lachesis.windranger.authentication.service.IProduct;

@Service
public class Product implements IProduct {
	@Autowired
	PrdInsuranceMapper prdInsuranceMapper;
	@Autowired
	PrdImmigrationMapper prdImmigrationMapper;
	@Autowired
	PrdFundMapper prdFundMapper;
	@Autowired
	PrdEstateMapper prdEstateMapper;
	@Autowired
	PrdCreditorMapper prdCreditorMapper;
	@Autowired
	PrdInsuranceMapperExt prdInsuranceMapperExt;
	@Autowired
	PrdImmigrationMapperExt prdImmigrationMapperExt;
	@Autowired
	PrdFundMapperExt prdFundMapperExt;
	@Autowired
	PrdEstateMapperExt prdEstateMapperExt;
	@Autowired
	PrdCreditorMapperExt prdCreditorMapperExt;

	@Override
	public void insertInsurance(PrdInsurance prdInsurance) {
		prdInsuranceMapper.insert(prdInsurance);
	}

	@Override
	public void deleteInsurance(long seqId) {
		prdInsuranceMapper.deleteByPrimaryKey(seqId);
	}

	@Override
	public PrdInsurance getInsuranceBySeqId(long seqId) {
		return prdInsuranceMapper.selectByPrimaryKey(seqId);
	}

	@Override
	public List<PrdInsurance> getInsurancesByCondition(PrdInsurance prdInsurance) {
		return prdInsuranceMapperExt.getInsurancesByCondition(prdInsurance);
	}

	@Override
	public void insertImmigration(PrdImmigration prdImmigration) {
		prdImmigrationMapper.insert(prdImmigration);
	}

	@Override
	public void deleteImmigration(long seqId) {
		prdImmigrationMapper.deleteByPrimaryKey(seqId);
	}

	@Override
	public PrdImmigration getImmigrationBySeqId(long seqId) {
		return prdImmigrationMapper.selectByPrimaryKey(seqId);
	}

	@Override
	public List<PrdImmigration> getImmigrationByCondition(PrdImmigration prdImmigration) {
		return prdImmigrationMapper.selectByBean(prdImmigration);
	}

	@Override
	public void insertFund(PrdFund prdFund) {
		prdFundMapper.insert(prdFund);
	}

	@Override
	public void deleteFund(long seqId) {
		prdFundMapper.deleteByPrimaryKey(seqId);
	}

	@Override
	public PrdFund getFundBySeqId(long seqId) {
		return prdFundMapper.selectByPrimaryKey(seqId);
	}

	@Override
	public List<PrdFund> getFundByCondition(PrdFund prdFund) {
		return prdFundMapper.selectByBean(prdFund);
	}

	@Override
	public void insertEstate(PrdEstate prdEstate) {
		prdEstateMapper.insert(prdEstate);
	}

	@Override
	public void deleteEstate(long seqId) {
		prdEstateMapper.deleteByPrimaryKey(seqId);
	}

	@Override
	public PrdEstate getEstateBySeqId(long seqId) {
		return prdEstateMapper.selectByPrimaryKey(seqId);
	}

	@Override
	public List<PrdEstate> getEstateByCondition(PrdEstate prdEstate) {
		return prdEstateMapper.selectByBean(prdEstate);
	}

	@Override
	public void insertCreditor(PrdCreditor prdCreditor) {
		prdCreditorMapper.insert(prdCreditor);
	}

	@Override
	public void deleteCreditor(long seqId) {
		prdCreditorMapper.deleteByPrimaryKey(seqId);
	}

	@Override
	public PrdCreditor getCreditorBySeqId(long seqId) {
		return prdCreditorMapper.selectByPrimaryKey(seqId);
	}

	@Override
	public List<PrdCreditor> getCreditorByCondition(PrdCreditor prdCreditor) {
		return prdCreditorMapper.selectByBean(prdCreditor);
	}

	@Override
	public List<String> getMenu(String roleCode) {
		List<String> res = new ArrayList<>();
		res.add("保险-1-/insurance/search");
		res.add("债权-2-/creditor/search");
		res.add("基金-3-/fund/search");
		res.add("移民-4-/immigration/search");
		res.add("房产-5-/estate/search");
		return res;
	}

	@Override
	public Map<String, List<String>> getSearchConditionByType(String type) {
		Map<String, List<String>> res = new HashMap<>();
		switch (type) {
			case "1":
				List<String> company = prdInsuranceMapperExt.getInsuranceCompany();
				res.put("保险公司-productCompany", company);
				List<String> category = prdInsuranceMapperExt.getInsuranceCategory();
				res.put("产品种类-productCategory", category);
				break;
			case "2":
				List<String> year = prdCreditorMapperExt.getCreditorYear();
				res.put("债权年期-year", year);
				break;
			case "3":
				List<String> fundCompany = prdFundMapperExt.getFundCompany();
				res.put("基金公司-fundCompany", fundCompany);
				break;
			case "4":
				List<String> immigrationCountry = prdImmigrationMapperExt.getImmigrationCountry();
				res.put("国家-country", immigrationCountry);
				break;
			case "5":
				List<String> estateCountry = prdEstateMapperExt.getEstateCountry();
				res.put("国家-country", estateCountry);
				break;
			default:
				break;
		}
		return res;
	}

	@Override
	public Object getDetail(String type, Long seqId) {
		Object res = null;
		switch (type) {
			case "1":
				res = prdInsuranceMapper.selectByPrimaryKey(seqId);
				break;
			case "2":
				res = prdCreditorMapper.selectByPrimaryKey(seqId);
				break;
			case "3":
				res = prdFundMapper.selectByPrimaryKey(seqId);
				break;
			case "4":
				res = prdImmigrationMapper.selectByPrimaryKey(seqId);
				break;
			case "5":
				res = prdEstateMapper.selectByPrimaryKey(seqId);
				break;
			default:
				break;
		}
		return res;
	}

}
