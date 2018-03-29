package com.lachesis.windranger.authentication.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lachesis.windranger.authentication.dao.PrdReservationMapper;
import com.lachesis.windranger.authentication.dbmodel.PrdReservation;
import com.lachesis.windranger.authentication.service.IReservationService;

@Service
public class ReservationService implements IReservationService {
	@Autowired
	PrdReservationMapper prdReservationMapper;
	
	/* (非 Javadoc) 
	 * <p>Title: insert</p> 
	 * <p>Description: </p> 
	 * @param prdReservation 
	 * @see com.lachesis.windranger.authentication.service.impl.IReservationService#insert(com.lachesis.windranger.authentication.dbmodel.PrdReservation) 
	 */
	@Override
	public void insert(PrdReservation prdReservation) {
		prdReservationMapper.insert(prdReservation);
	}

	/* (非 Javadoc) 
	 * <p>Title: update</p> 
	 * <p>Description: </p> 
	 * @param prdReservation 
	 * @see com.lachesis.windranger.authentication.service.impl.IReservationService#update(com.lachesis.windranger.authentication.dbmodel.PrdReservation) 
	 */
	@Override
	public void update(PrdReservation prdReservation) {
		prdReservationMapper.updateByPrimaryKeySelective(prdReservation);
	}
	
	/* (非 Javadoc) 
	 * <p>Title: getBySeqId</p> 
	 * <p>Description: </p> 
	 * @param seqId
	 * @return 
	 * @see com.lachesis.windranger.authentication.service.impl.IReservationService#getBySeqId(long) 
	 */
	@Override
	public PrdReservation getBySeqId(long seqId) {
		return prdReservationMapper.selectByPrimaryKey(seqId);
	}
	
	/* (非 Javadoc) 
	 * <p>Title: queryByCondition</p> 
	 * <p>Description: </p> 
	 * @param query
	 * @return 
	 * @see com.lachesis.windranger.authentication.service.impl.IReservationService#queryByCondition(com.lachesis.windranger.authentication.dbmodel.PrdReservation) 
	 */
	@Override
	public List<PrdReservation> queryByCondition(PrdReservation query) {
		return prdReservationMapper.selectByBean(query);
	}
}
