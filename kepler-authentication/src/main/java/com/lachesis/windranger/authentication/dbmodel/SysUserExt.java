package com.lachesis.windranger.authentication.dbmodel;

public class SysUserExt extends SysUser {
    private String wardCode;
    private String wardName;

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}