package com.lachesis.windranger.authentication.service;

import java.util.List;
import java.util.Map;
import com.lachesis.windranger.authentication.dbmodel.PrdCreditor;
import com.lachesis.windranger.authentication.dbmodel.PrdEstate;
import com.lachesis.windranger.authentication.dbmodel.PrdFund;
import com.lachesis.windranger.authentication.dbmodel.PrdImmigration;
import com.lachesis.windranger.authentication.dbmodel.PrdInsurance;

public interface IProduct {

	List<String> getMenu(String roleCode);
	
	Map<String, List<String>> getSearchConditionByType(String type);
	
	Object getDetail(String type, Long seqId);

	// insurance
	void insertInsurance(PrdInsurance prdInsurance);

	void deleteInsurance(long seqId);

	PrdInsurance getInsuranceBySeqId(long seqId);

	List<PrdInsurance> getInsurancesByCondition(PrdInsurance prdInsurance);

	// immigration
	void insertImmigration(PrdImmigration prdImmigration);

	void deleteImmigration(long seqId);

	PrdImmigration getImmigrationBySeqId(long seqId);

	List<PrdImmigration> getImmigrationByCondition(PrdImmigration prdImmigration);

	// fund
	void insertFund(PrdFund rpdFund);

	void deleteFund(long seqId);

	PrdFund getFundBySeqId(long seqId);

	List<PrdFund> getFundByCondition(PrdFund prdFund);


	// estate
	void insertEstate(PrdEstate prdEstate);

	void deleteEstate(long seqId);

	PrdEstate getEstateBySeqId(long seqId);

	List<PrdEstate> getEstateByCondition(PrdEstate prdEstate);


	// creditor
	void insertCreditor(PrdCreditor prdCreditor);

	void deleteCreditor(long seqId);

	PrdCreditor getCreditorBySeqId(long seqId);

	List<PrdCreditor> getCreditorByCondition(PrdCreditor prdCreditor);
}
