package org.tutu.springsecurity.browser.security;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tutu.security.core.properties.SecurityProperties;
import org.tutu.springsecurity.browser.support.SimpleResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cguisheng
 * @className: BorwserSecurityController.java
 * @description: 身份认证跳转
 * @date 2019/1/20 13:18
 */
@RestController
public class BorwserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 将请求缓存到session中
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 用于重定向
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时，跳转到这里
     *
     * @param request  请求
     * @param response 响应
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)   // 返回401状态码
    public SimpleResponse RequireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 从session中获取到缓存的请求 (引发跳转的请求)
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            // 引发跳转的url
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("引发的请求是：" + redirectUrl);
            // 判断请求是否是以”.html“结尾
            if (StringUtils.endsWith(redirectUrl, ".html")) {
                // 以 .html结尾的请求 重定向到指定页面
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        // 其他请求则返回401状态码
        return new SimpleResponse("访问的服务需要身份验证，请引导用户到登录页");


    }
}
