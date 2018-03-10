/*
 * @ClassName SysToken
 * @Description 
 * @version 1.0
 * @Date 2017-04-13 21:16:05
 */
package com.lachesis.windranger.authentication.dbmodel;

import com.lachesis.windranger.common.model.ValuedBean;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class SysToken extends ValuedBean {
    @ApiModelProperty(name="seq_id", value="自增长流水号")
    private Long seqId;

    @ApiModelProperty(name="user_code", value="用户编号")
    private String userCode;
    
    @ApiModelProperty(name="token_content", value="令牌内容")
    private String tokenContent;

    @ApiModelProperty(name="expiredate", value="过期时间", example="2017-01-16T15:30:19.000+0800")
    private Date expiredate;

    @ApiModelProperty(name="isvalid", value="令牌是否有效 0为无效，1为有效")
    private Integer isvalid;

    @ApiModelProperty(name="ipaddress", value="IP地址")
    private String ipaddress;

    @ApiModelProperty(name="ismobile", value="是否为移动设备 0为PC，1位移动设备")
    private Integer ismobile;

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getTokenContent() {
        return tokenContent;
    }

    public void setTokenContent(String tokenContent) {
        this.tokenContent = tokenContent == null ? null : tokenContent.trim();
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }

    public Integer getIsmobile() {
        return ismobile;
    }

    public void setIsmobile(Integer ismobile) {
        this.ismobile = ismobile;
    }

    
    
}