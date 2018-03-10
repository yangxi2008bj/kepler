/*
 * @ClassName SysAttachment
 * @Description 
 * @version 1.0
 * @Date 2017-12-30 22:25:25
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class SysAttachment extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="自增长流水号")
    private Long seqId;

    @ApiModelProperty(name="company_name", value="产品公司")
    private String companyName;

    @ApiModelProperty(name="use_type", value="用途类别")
    private String useType;

    @ApiModelProperty(name="source_type", value="资料类别")
    private String sourceType;

    @ApiModelProperty(name="module_key", value="子系统（模块）名")
    private String moduleKey;

    @ApiModelProperty(name="file_name", value="文件名")
    private String fileName;

    @ApiModelProperty(name="file_type", value="文件类型")
    private String fileType;

    @ApiModelProperty(name="length", value="文件大小")
    private Long length;

    @ApiModelProperty(name="create_time", value="创建时间", example="2017-01-16T15:30:19.000+0800")
    private Date createTime;

    @ApiModelProperty(name="create_person", value="创建人")
    private String createPerson;

    @ApiModelProperty(name="update_time", value="修改时间", example="2017-01-16T15:30:19.000+0800")
    private Date updateTime;

    @ApiModelProperty(name="update_person", value="修改人")
    private String updatePerson;

    @ApiModelProperty(name="url", value="文件存放地址")
    private byte[] url;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType == null ? null : useType.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getModuleKey() {
        return moduleKey;
    }

    public void setModuleKey(String moduleKey) {
        this.moduleKey = moduleKey == null ? null : moduleKey.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
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

    public byte[] getUrl() {
        return url;
    }

    public void setUrl(byte[] url) {
        this.url = url;
    }
}