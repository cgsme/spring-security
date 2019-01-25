package org.tutu.security.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.tutu.security.core.properties.SecurityProperties;

/**
 * @author cguisheng
 * @className: SecurityCoreConfig.java
 * @description: TODO
 * @date 2019/1/20 13:57
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
