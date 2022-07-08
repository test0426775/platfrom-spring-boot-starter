package com.zxo.platform.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @ClassName: BasePackageProperties
 * @Author: zzzxxxooo
 * @Date: 2022/6/29 14:45
 * @Note:
 */
@Data
@EnableConfigurationProperties(value = BasePackageProperties.class)
@ConfigurationProperties(prefix = "platform.global")
public class BasePackageProperties {

}
