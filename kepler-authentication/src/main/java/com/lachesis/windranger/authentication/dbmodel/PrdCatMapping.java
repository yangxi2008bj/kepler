/*
 * @ClassName PrdCatMapping
 * @Description 
 * @version 1.0
 * @Date 2017-12-30 22:25:25
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;

public class PrdCatMapping extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="")
    private Long seqId;

    @ApiModelProperty(name="category", value="")
    private String category;

    @ApiModelProperty(name="searchCondition", value="")
    private String searchcondition;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getSearchcondition() {
        return searchcondition;
    }

    public void setSearchcondition(String searchcondition) {
        this.searchcondition = searchcondition == null ? null : searchcondition.trim();
    }
}