package org.tutu.springsecurity.browser.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.tutu.security.core.properties.SecurityProperties;

/**
 * @author cguisheng
 * @className: BrowserSecurityConfig.java
 * @description: spring security配置类
 * @date 2019/1/16 21:40
 */
@Component
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandler myAuthenticationFailureHandler;
    /**
     * 定义密码加密类
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()  // 表单方式登录
                // 配置自定义登录页
                .loginPage("/authentication/require")
                // 配置处理登录请求的接口
                .loginProcessingUrl("/authentication/login")
                .successHandler(myAuthenticationSuccessHandler)  // 指定登录成功执行的处理器
                .failureHandler(myAuthenticationFailureHandler)  // 认证失败处理器
//        http.httpBasic()   // 默认方式登录，浏览器弹出对话框
                .and()
                .authorizeRequests()   // 配置请求的授权
                // 排除不需要身份认证的路径
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest().authenticated()    // 任何请求都需要身份验证
                .and()
                .csrf().disable();   // 关闭跨域防护
    }
}
