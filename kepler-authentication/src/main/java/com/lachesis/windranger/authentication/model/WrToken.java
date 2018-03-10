package com.lachesis.windranger.authentication.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lachesis.windranger.authentication.dbmodel.SysRole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Token令牌，用户登录后获取此令牌")
public class WrToken implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列号
	 */
	private static final long serialVersionUID = -1817236351400428465L;

	@ApiModelProperty(value = "令牌内容")
	private String tokenContent;

	@ApiModelProperty(value = "用户编号")
	private String userCode;
	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty(value = "令牌过期时间", example = "2017-01-16T15:30:19.000+0800")
	private Date expiredDate;

	@ApiModelProperty(value = "用户所在的角色组")
	private List<SysRole> roles;

	@ApiModelProperty(value = "令牌是否有效")
	private boolean isValid;

	@ApiModelProperty(value = "ip地址")
	private String ipAddress;

	@ApiModelProperty(value = "是否为移动设备")
	private boolean isMobile;

	/**
	 * @return tokenContent
	 */
	public String getTokenContent() {
		return tokenContent;
	}

	/**
	 * @param tokenContent
	 *            要设置的 tokenContent
	 */
	public void setTokenContent(String tokenContent) {
		this.tokenContent = tokenContent;
	}

	/**
	 * @return userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param userCode
	 *            要设置的 userCode
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * @return expiredDate
	 */
	public Date getExpiredDate() {
		return expiredDate;
	}

	/**
	 * @param expiredDate
	 *            要设置的 expiredDate
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	/**
	 * @return roles
	 */
	public List<SysRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            要设置的 roles
	 */
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	/**
	 * @return isValid
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @param isValid
	 *            要设置的 isValid
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            要设置的 ipAddress
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return isMobile
	 */
	public boolean isMobile() {
		return isMobile;
	}

	/**
	 * @param isMobile
	 *            要设置的 isMobile
	 */
	public void setMobile(boolean isMobile) {
		this.isMobile = isMobile;
	}

	/* (非 Javadoc) 
	 * <p>Title: toString</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "WrToken [tokenContent=" + tokenContent + ", userCode=" + userCode + ", expiredDate=" + expiredDate
				+ ", roles=" + roles + ", isValid=" + isValid + ", ipAddress=" + ipAddress + ", isMobile=" + isMobile
				+ "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
