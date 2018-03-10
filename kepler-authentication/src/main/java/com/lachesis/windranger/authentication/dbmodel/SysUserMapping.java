/*
 * @ClassName SysUserMapping
 * @Description 
 * @version 1.0
 * @Date 2017-04-13 21:16:05
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class SysUserMapping extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="自增长流水号")
    private Long seqId;

    @ApiModelProperty(name="user_code_local", value="平台用户名")
    private String userCodeLocal;

    @ApiModelProperty(name="resource_code", value="资源名")
    private String resourceCode;

    @ApiModelProperty(name="use_code_out", value="外部应用用户名")
    private String useCodeOut;

    @ApiModelProperty(name="use_pwd_out", value="外部应用密码")
    private String usePwdOut;

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

    public String getUserCodeLocal() {
        return userCodeLocal;
    }

    public void setUserCodeLocal(String userCodeLocal) {
        this.userCodeLocal = userCodeLocal == null ? null : userCodeLocal.trim();
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    public String getUseCodeOut() {
        return useCodeOut;
    }

    public void setUseCodeOut(String useCodeOut) {
        this.useCodeOut = useCodeOut == null ? null : useCodeOut.trim();
    }

    public String getUsePwdOut() {
        return usePwdOut;
    }

    public void setUsePwdOut(String usePwdOut) {
        this.usePwdOut = usePwdOut == null ? null : usePwdOut.trim();
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