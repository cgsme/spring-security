package org.tutu.springsecurity.browser.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author cguisheng
 * @className: BrowserSecurityConfig.java
 * @description: spring security配置类
 * @date 2019/1/16 21:40
 */
@Component
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()  // 表单方式登录
        http.httpBasic()   // 默认方式登录，浏览器弹出对话框
                .and()
                .authorizeRequests()   // 配置请求的授权
                .anyRequest().authenticated();    // 任何请求都需要身份验证
    }
}
