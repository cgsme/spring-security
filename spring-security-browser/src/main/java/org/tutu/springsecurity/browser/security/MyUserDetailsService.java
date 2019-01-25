package org.tutu.springsecurity.browser.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author cguisheng
 * @className: MyUserDetailsService.java
 * @description: 自定义UserDetailsService
 * @date 2019/1/17 21:37
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名查找用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456");
        System.out.println("用户名：" + username);
        System.out.println("数据库加密后的密码为：" + password);
        // 该User对象为security提供
        return new User(username, password, true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
