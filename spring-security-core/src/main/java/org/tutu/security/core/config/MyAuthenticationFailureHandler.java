package org.tutu.security.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.tutu.security.core.properties.LoginType;
import org.tutu.security.core.properties.SecurityProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cguisheng
 * @className: MyAuthenticationFailueHandler.java
 * @description: TODO
 * @date 2019/1/24 22:13
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception));
        } else {
            // 默认进行重定向
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
