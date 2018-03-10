package com.lachesis.windranger.authentication.component;

import java.util.Dictionary;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;

import com.lachesis.windranger.authentication.dbmodel.SysUser;
import com.lachesis.windranger.authentication.model.WrToken;
import com.lachesis.windranger.authentication.service.IUserService;


/**
* ClassName: AuthenticationPool <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2017年1月17日 下午4:04:45 <br/>
*
* @author Shiwei.hu
* @version 
* @since JDK 1.7.0.45
*/	
public final class AuthenticationPool {

	
	private static volatile AuthenticationPool instance;
	public static AuthenticationPool getIstance() { 
        if (instance == null) {
            synchronized (AuthenticationPool.class) {
                if (instance == null) {
                    instance = new AuthenticationPool();   
                } 
            }   
        }   
        return instance;   
    }
	
	private Dictionary<String, SysUser> loginUserTokenDictionary;
	
    private AuthenticationPool() { 
     
    	loginUserTokenDictionary = new Hashtable<String, SysUser>();    	
    }
    
    private WrToken generateToken(SysUser user)
    {
    	WrToken token = new WrToken();
    	
    	return token;
    }
}
