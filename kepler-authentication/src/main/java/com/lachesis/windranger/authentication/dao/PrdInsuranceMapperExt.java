package com.lachesis.windranger.authentication.dao;

import java.util.List;
import com.lachesis.windranger.authentication.dbmodel.PrdInsurance;
import com.lachesis.windranger.common.model.ISearchableDAO;

public interface PrdInsuranceMapperExt extends ISearchableDAO {
	List<PrdInsurance> getInsurancesByCondition(PrdInsurance prdInsurance);
	
	List<String> getInsuranceCompany();
	
	List<String> getInsuranceCategory();
	
}