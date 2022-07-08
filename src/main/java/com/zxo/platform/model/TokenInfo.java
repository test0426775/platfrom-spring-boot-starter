package com.zxo.platform.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * Jwt Token 的具体内容
 *
 * @ClassName: TokenInfo
 * @Author: zzzxxxooo
 * @Date: 2022/7/7 9:27
 * @Note:
 */
@Data
public class TokenInfo {
    /**
     * User ID
     */
    @JSONField(name = "loginId")
    private String userId;
    /**
     * User Name
     */
    private String userName;
    /**
     * Tenant ID
     */
    private String tenantId;
    /**
     * Role Ids
     */
    private List roleIds;
    /**
     * Data Ids
     */
    private List dataIds;
    /**
     * Resource Ids
     */
    private List resourceIds;

}
