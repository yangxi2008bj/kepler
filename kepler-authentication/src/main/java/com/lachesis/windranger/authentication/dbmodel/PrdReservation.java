/*
 * @ClassName PrdReservation
 * @Description 
 * @version 1.0
 * @Date 2018-03-18 13:38:25
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;

public class PrdReservation extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="")
    private Long seqId;

    @ApiModelProperty(name="product_type", value="")
    private String productType;

    @ApiModelProperty(name="detail", value="")
    private String detail;

    @ApiModelProperty(name="process_status", value="")
    private String processStatus;

    @ApiModelProperty(name="attachment", value="")
    private String attachment;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }
}