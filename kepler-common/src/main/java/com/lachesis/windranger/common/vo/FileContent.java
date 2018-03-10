package com.lachesis.windranger.common.vo;

import com.lachesis.windranger.common.model.ValuedBean;

import io.swagger.annotations.ApiModelProperty;

public class FileContent extends ValuedBean {
	
	 public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ApiModelProperty(value="文件内容")
	    private String content; 

}
