/*
 * @ClassName PrdInsurance
 * @Description 
 * @version 1.0
 * @Date 2018-01-27 20:44:40
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;

public class PrdInsurance extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="自增id")
    private Long seqId;

    @ApiModelProperty(name="product_category", value="产品类型")
    private String productCategory;

    @ApiModelProperty(name="product_company", value="产品公司")
    private String productCompany;

    @ApiModelProperty(name="age_lower", value="")
    private Integer ageLower;

    @ApiModelProperty(name="age_upper", value="")
    private Integer ageUpper;

    @ApiModelProperty(name="product_tag", value="")
    private String productTag;

    @ApiModelProperty(name="product_name", value="")
    private String productName;

    @ApiModelProperty(name="currency", value="")
    private String currency;

    @ApiModelProperty(name="years", value="")
    private String years;

    @ApiModelProperty(name="product_status", value="0-暂停 1-启用")
    private String productStatus;

    @ApiModelProperty(name="role_code", value="")
    private String roleCode;

    @ApiModelProperty(name="debit_year", value="债权年限")
    private Integer debitYear;

    @ApiModelProperty(name="country", value="国家")
    private String country;

    @ApiModelProperty(name="type", value="种类")
    private String type;

    @ApiModelProperty(name="description", value="")
    private String description;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory == null ? null : productCategory.trim();
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany == null ? null : productCompany.trim();
    }

    public Integer getAgeLower() {
        return ageLower;
    }

    public void setAgeLower(Integer ageLower) {
        this.ageLower = ageLower;
    }

    public Integer getAgeUpper() {
        return ageUpper;
    }

    public void setAgeUpper(Integer ageUpper) {
        this.ageUpper = ageUpper;
    }

    public String getProductTag() {
        return productTag;
    }

    public void setProductTag(String productTag) {
        this.productTag = productTag == null ? null : productTag.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus == null ? null : productStatus.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public Integer getDebitYear() {
        return debitYear;
    }

    public void setDebitYear(Integer debitYear) {
        this.debitYear = debitYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}