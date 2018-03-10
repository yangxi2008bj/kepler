package com.lachesis.windranger.authentication.shiro.model;

/*public class UserRealm extends AuthorizingRealm {  

	@Autowired 
	private UserService userService;


	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  

		String username = (String)principals.getPrimaryPrincipal();  
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
		authorizationInfo.setRoles(userService.findRoles(username));  
		authorizationInfo.setStringPermissions(userService.findPermissions(username));  
		System.out.println(userService.findPermissions(username));  
		return authorizationInfo;  
	}  

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String)token.getPrincipal();  
		SysUser user = userService.findByUsername(username);  
		if(user == null) {  
			throw new UnknownAccountException();//没找到帐号  
		}  
		if(Boolean.TRUE.equals(user.getLocked())) {  
			throw new LockedAccountException(); //帐号锁定  
		}  
		
		return new SimpleAuthenticationInfo(  
				user.getUsername(), //用户名  
				user.getPassword(), //密码

				//salt=username+salt
				ByteSource.Util.bytes(user.getCredentialsSalt()),                  
				getName()  //realm name  
				);  
	}  
}  */