package com;

import jdk.nashorn.internal.objects.Global;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/17 11:12
 * @description TODO
 */
public class MyCasRealm extends CasRealm {

    /**
     * CAS认证 ,验证用户身份
     * 将用户基本信息设置到会话中
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        //调用CasRealm实现的认证方法,其包含验证ticket、填充CasToken的principal等操作)
        AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
        String account = (String) authc.getPrincipals().getPrimaryPrincipal();
        return authc;
    }

    /**
     * 设置角色和权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
            return null;
    }
}
