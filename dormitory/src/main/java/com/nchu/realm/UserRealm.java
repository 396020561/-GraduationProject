package com.nchu.realm;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.jboss.logging.Logger;

import com.nchu.entity.Account;
import com.nchu.entity.Role;
import com.nchu.service.AccountRoleService;
import com.nchu.service.AccountService;
import com.nchu.service.RolePermissionService;

public class UserRealm extends AuthorizingRealm {

    @Resource
    private AccountService accountService;
    
    @Resource
    private AccountRoleService accountRoleService;
    
    @Resource
    private RolePermissionService rolePermissionService;

    private Logger logger=Logger.getLogger(UserRealm.class);

    /**
     * 提供用户信息，返回权限信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------------------->授权认证：");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        String userName=(String) principals.getPrimaryPrincipal();
        Account account = accountService.findAccountByUid(userName);
        Set<String> roleSet=accountRoleService.findRoleNamesByAid(account.getAid());
        Set<Role> roles=accountRoleService.findRolesByAid(account.getAid());
        Set<String>  pemissionSet=new HashSet<>();
        for(Role role:roles){
        	pemissionSet.addAll(rolePermissionService.findPermissionsByRid(role.getRid()));
        }
        // 将角色名称提供给授权info
        authorizationInfo.setRoles( roleSet );
        // 将权限名称提供给info
        authorizationInfo.setStringPermissions(pemissionSet);
        return authorizationInfo;
    }

    /**
     * 提供帐户信息，返回认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        logger.info("---------------------------->登陆验证:");
        String userName=(String)authenticationToken.getPrincipal();
        Account account = accountService.findAccountByUid(userName);
        if(account==null) {
            //用户不存在就抛出异常
            throw new UnknownAccountException();
        }
//        密码可以通过SimpleHash加密，然后保存进数据库。
//        此处是获取数据库内的账号、密码、盐值，保存到登陆信息info中
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(account.getUid(),
                account.getPassword(),
                ByteSource.Util.bytes(account.getSalt()),getName());                   //realm name
        return authenticationInfo;
    }

}
