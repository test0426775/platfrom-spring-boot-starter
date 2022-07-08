package com.zxo.platform.model;

import lombok.Data;

/**
 * @ClassName: RedisTopic
 * @Author: zzzxxxooo
 * @Date: 2022/7/7 15:13
 * @Note:
 */
@Data
public class RedisTopic {
    /* register listen class Name */
    private String className;
    /* register listen topic name */
    private String topicName;
}
