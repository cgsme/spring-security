package org.tutu.security.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.tutu.security.core.properties.LoginType;
import org.tutu.security.core.properties.SecurityProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功
 *
 * @author cguisheng
 * @className: MyAuthenticationSuccessHandler.java
 * @description: TODO
 * @date 2019/1/24 20:42
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 认证成功执行
     *
     * @param request
     * @param response
     * @param authentication 封装了认证信息、
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=utf-8");
            // 将authentication对象以json形式返回给前台
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            // 默认进行重定向
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
