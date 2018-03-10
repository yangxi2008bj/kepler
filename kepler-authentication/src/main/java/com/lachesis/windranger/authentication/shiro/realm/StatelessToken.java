package com.lachesis.windranger.authentication.shiro.realm;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationToken;


public class StatelessToken implements AuthenticationToken {

    /**
	* serialVersionUID: TODO(用一句话描述这个变量表示什么).
	* @since JDK 1.7.0.45
	*/		
	private static final long serialVersionUID = 1L;
	
	
	private String username;
    private Map<String, ?> params;
    private String clientDigest;

    public StatelessToken(String username,  Map<String, ?> params, String clientDigest) {
        this.username = username;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public  Map<String, ?> getParams() {
        return params;
    }

    public void setParams( Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }

    public Object getPrincipal() {
       return username;
    }

    public Object getCredentials() {
        return clientDigest;
    }
}
