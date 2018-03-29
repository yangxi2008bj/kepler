package com.lachesis.windranger.authentication.service;

import java.util.List;
import com.lachesis.windranger.authentication.dbmodel.PrdReservation;


public interface IReservationService {

	void insert(PrdReservation prdReservation);

	void update(PrdReservation prdReservation);

	PrdReservation getBySeqId(long seqId);

	List<PrdReservation> queryByCondition(PrdReservation query);

}
