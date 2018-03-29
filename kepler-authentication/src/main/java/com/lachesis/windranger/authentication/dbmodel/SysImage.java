/*
 * @ClassName SysImage
 * @Description 
 * @version 1.0
 * @Date 2018-03-24 12:46:52
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class SysImage extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="")
    private Long seqId;

    @ApiModelProperty(name="file_name", value="")
    private String fileName;

    @ApiModelProperty(name="file_type", value="")
    private String fileType;

    @ApiModelProperty(name="length", value="")
    private Long length;

    @ApiModelProperty(name="create_person", value="")
    private String createPerson;

    @ApiModelProperty(name="create_time", value="", example="2017-01-16T15:30:19.000+0800")
    private Date createTime;

    @ApiModelProperty(name="url", value="")
    private byte[] url;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
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

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte[] getUrl() {
        return url;
    }

    public void setUrl(byte[] url) {
        this.url = url;
    }
}