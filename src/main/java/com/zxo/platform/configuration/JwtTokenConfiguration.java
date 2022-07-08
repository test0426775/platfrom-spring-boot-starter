package com.zxo.platform.configuration;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import com.zxo.platform.properties.TokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: JwtTokenConfiguration
 * @Author: zzzxxxooo
 * @Date: 2022/6/30 13:14
 * @Note:
 */
@EnableConfigurationProperties({TokenProperties.class})
public class JwtTokenConfiguration {
    private final TokenProperties tokenProperties;

    public JwtTokenConfiguration (TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    @Bean
    public StpLogic getStpLogicJwt () {
        if (tokenProperties.isEnableJwtToken()) {
            return new StpLogicJwtForSimple();
        }
        return null;
    }
}
