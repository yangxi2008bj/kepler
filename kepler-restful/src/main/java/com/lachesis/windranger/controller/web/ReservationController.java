package com.lachesis.windranger.controller.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lachesis.windranger.authentication.dbmodel.PrdReservation;
import com.lachesis.windranger.authentication.service.IReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/Reservation", produces = {"application/json;charset=UTF-8"})
@Api(value = "/reservation", tags = {"reservation"}, description = "预约产品-预约接口")
public class ReservationController {

	@Autowired
	IReservationService iReservationService;
	
	@RequestMapping(value = {"/product/{seqId}"}, method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "预约的产品", httpMethod = "GET")
	public PrdReservation getPrdReservation(@ApiParam(value = "seqId") @PathVariable long seqId) {
		return iReservationService.getBySeqId(seqId);
	}
	
	@RequestMapping(value = {"/product"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加预约产品单", httpMethod = "POST")
	public void addReservation(@ApiParam(value = "预约产品") @RequestBody PrdReservation prdReservation) {
		iReservationService.insert(prdReservation);
	}
	
	@RequestMapping(value = {"/product"}, method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "更新预约产品单", httpMethod = "PUT")
	public void updateReservation(@ApiParam(value = "预约产品") @RequestBody PrdReservation prdReservation) {
		iReservationService.update(prdReservation);
	}
	
	@RequestMapping(value = {"/queryProduct"}, method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "添加预约产品单", httpMethod = "POST")
	public List<PrdReservation> queryReservationByCondition(@ApiParam(value = "预约产品") @RequestBody PrdReservation prdReservation) {
		return iReservationService.queryByCondition(prdReservation);
	}
}
