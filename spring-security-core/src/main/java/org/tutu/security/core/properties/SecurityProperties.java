package org.tutu.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cguisheng
 * @className: SecurityProperties.java
 * @description: TODO
 * @date 2019/1/20 13:54
 */
@Component
@ConfigurationProperties(prefix = "tutu.security")
public class SecurityProperties {

    private BrowserProperties browser;


    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browserProperties) {
        this.browser = browserProperties;
    }


}
