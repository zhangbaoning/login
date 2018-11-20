package me.zhangbaoning.login.shiro;

import me.zhangbaoning.login.entity.User;
import me.zhangbaoning.login.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: zhangbaoning
 * @date: 2018/11/8
 * @since: JDK 1.8
 * @description: 登陆拦截
 */
public class LoginShrio extends AuthorizingRealm {
    @Autowired
    private UserService service;

    /**
     * 权限认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

        /**
         * 登录认证
         * @param authenticationToken
         * @return
         * @throws AuthenticationException
         */
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken authenticationToken) throws
        AuthenticationException {
            String idCard = (String) authenticationToken.getPrincipal();
            User user = service.getByIdCard(idCard);
            AuthenticationInfo info =null;
            if (user!=null){
                String getIdCard = user.getIdCard();
                // 截取后六位进行验证
                String authIdCard= getIdCard.substring(getIdCard.length()-6);
                info = new SimpleAuthenticationInfo(authIdCard,user.getFullName(),getName());

            }
            return info;
        }


}
