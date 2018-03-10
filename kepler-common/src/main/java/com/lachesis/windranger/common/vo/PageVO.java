package com.lachesis.windranger.common.vo;

import java.util.List;

public class PageVO<E> {
	
	private List<E> datas;
	
	private int totalCount;

	public List<E> getDatas() {
		return datas;
	}

	public void setDatas(List<E> datas) {
		this.datas = datas;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
