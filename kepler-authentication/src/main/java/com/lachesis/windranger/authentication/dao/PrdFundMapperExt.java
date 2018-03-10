package com.lachesis.windranger.authentication.dao;

import java.util.List;
import com.lachesis.windranger.common.model.ISearchableDAO;

public interface PrdFundMapperExt extends ISearchableDAO {
	
	List<String> getFundCompany();
}