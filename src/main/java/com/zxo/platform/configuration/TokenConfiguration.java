package com.zxo.platform.configuration;

import cn.dev33.satoken.config.SaTokenConfig;
import com.zxo.platform.properties.TokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @ClassName: TokenConfiguration
 * @Author: zzzxxxooo
 * @Date: 2022/6/22 17:33
 * @Note:
 */
@EnableConfigurationProperties({TokenProperties.class})
public class TokenConfiguration {
    private final TokenProperties tokenProperties;

    public TokenConfiguration (TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary () {
        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName(tokenProperties.getName());
        config.setTimeout(tokenProperties.getTimeout());
        config.setActivityTimeout(tokenProperties.getActivityTimeout());
        config.setIsConcurrent(false);
        config.setIsShare(true);
        config.setIsReadBody(false);
        /* 是否尝试从 cookie 里读取 Token */
        config.setIsReadCookie(false);
        config.setIsLog(false);
        config.setIsPrint(false);
        if (tokenProperties.isEnableJwtToken()) {
            config.setJwtSecretKey(tokenProperties.getJwtSecret());
        } else {
            config.setTokenStyle("uuid");
        }
        return config;
    }
}
