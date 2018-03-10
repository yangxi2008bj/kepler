/*
 * @ClassName PrdFund
 * @Description 
 * @version 1.0
 * @Date 2018-01-27 20:44:40
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;

public class PrdFund extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="")
    private Long seqId;

    @ApiModelProperty(name="fund_company", value="")
    private String fundCompany;

    @ApiModelProperty(name="description", value="")
    private String description;

    @ApiModelProperty(name="product_name", value="")
    private String productName;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getFundCompany() {
        return fundCompany;
    }

    public void setFundCompany(String fundCompany) {
        this.fundCompany = fundCompany == null ? null : fundCompany.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }
}