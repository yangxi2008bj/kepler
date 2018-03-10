/*
 * @ClassName SysResource
 * @Description 
 * @version 1.0
 * @Date 2017-04-13 21:16:05
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class SysResource extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="自增长流水号")
    private Long seqId;

    @ApiModelProperty(name="resource_code", value="")
    private String resourceCode;

    @ApiModelProperty(name="resource_name", value="资源名")
    private String resourceName;

    @ApiModelProperty(name="resource_type", value="0-应用模块，1-实体资源")
    private String resourceType;

    @ApiModelProperty(name="resource_content", value="资源内容")
    private String resourceContent;

    @ApiModelProperty(name="resource_desc", value="资源描述")
    private String resourceDesc;

    @ApiModelProperty(name="parent_resource_code", value="父资源Id")
    private String parentResourceCode;

    @ApiModelProperty(name="app_type", value="应用类型")
    private String appType;

    @ApiModelProperty(name="resource_icon", value="资源图标")
    private String resourceIcon;

    @ApiModelProperty(name="app_version", value="应用版本")
    private String appVersion;

    @ApiModelProperty(name="ad", value="缩写")
    private String ad;

    @ApiModelProperty(name="is_out", value="平台外系统的flag")
    private String isOut;

    @ApiModelProperty(name="create_time", value="创建时间", example="2017-01-16T15:30:19.000+0800")
    private Date createTime;

    @ApiModelProperty(name="create_person", value="创建人")
    private String createPerson;

    @ApiModelProperty(name="update_time", value="修改时间", example="2017-01-16T15:30:19.000+0800")
    private Date updateTime;

    @ApiModelProperty(name="update_person", value="修改人")
    private String updatePerson;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getResourceContent() {
        return resourceContent;
    }

    public void setResourceContent(String resourceContent) {
        this.resourceContent = resourceContent == null ? null : resourceContent.trim();
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc == null ? null : resourceDesc.trim();
    }

    public String getParentResourceCode() {
        return parentResourceCode;
    }

    public void setParentResourceCode(String parentResourceCode) {
        this.parentResourceCode = parentResourceCode == null ? null : parentResourceCode.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon == null ? null : resourceIcon.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad == null ? null : ad.trim();
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut == null ? null : isOut.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }
}