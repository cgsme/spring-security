package org.tutu.springsecurity.browser.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author cguisheng
 * @className: MyUserDetailsService.java
 * @description: 自定义UserDetailsService
 * @date 2019/1/17 21:37
 */
@Component
public class MyUserDetailsService implements UserDetailsService {


    // 根据用户名查找用户信息
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
