package com.zxo.platform.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @ClassName: TokenProperties
 * @Author: zzzxxxooo
 * @Date: 2022/6/22 17:15
 * @Note:
 */
@Data
@EnableConfigurationProperties(value = TokenProperties.class)
@ConfigurationProperties(prefix = "platform.token")
public class TokenProperties {
    private String name = "token";
    private Long timeout = -1L;
    private Long activityTimeout = -1L;
    private String jwtSecret;
    private boolean enableJwtToken = false;
}
