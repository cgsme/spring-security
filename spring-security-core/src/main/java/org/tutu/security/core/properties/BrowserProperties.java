package org.tutu.security.core.properties;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author cguisheng
 * @className: BroswerProperties.java
 * @description: TODO
 * @date 2019/1/20 13:55
 */
public class BrowserProperties {

    private String loginPage = "/login.html";

    /**
     * 登录类型，默认返回json
     */
    private LoginType loginType = LoginType.JSON;


    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
