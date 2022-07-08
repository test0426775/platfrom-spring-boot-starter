package com.zxo.platform.properties;

import com.zxo.platform.model.RedisTopic;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RedisProperties
 * @Author: zzzxxxooo
 * @Date: 2022/7/7 15:04
 * @Note:
 */
@Data
@EnableConfigurationProperties(value = RedisProperties.class)
@ConfigurationProperties(prefix = "platform.redis")
public class RedisProperties {
    private List<RedisTopic> topic = new ArrayList<>();
}
