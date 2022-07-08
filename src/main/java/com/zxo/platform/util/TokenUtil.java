package com.zxo.platform.util;

import cn.dev33.satoken.jwt.SaJwtUtil;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.zxo.platform.model.TokenInfo;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Token Util
 *
 * @ClassName: TokenUtil
 * @Author: zzzxxxooo
 * @Date: 2022 /7/7 9:21
 * @Note:
 */
public class TokenUtil extends StpUtil {
    public static StpLogic stpLogic = new StpLogic("login");

    /**
     * Login by jwt string.
     *
     * @param token token的详细内容
     * @throws IntrospectionException the introspection exception
     */
    public static void loginByJwt (String id, TokenInfo token) throws IntrospectionException {
        HashMap<String, Object> tokenMap = getTokenInfoValue(token);
        SaLoginModel model = new SaLoginModel();
        model.setExtraData(tokenMap);
        login(id, model);
    }

    public static TokenInfo getTokenContent () {
        JSONObject token = SaJwtUtil.getPayloads(stpLogic.getTokenValue(), stpLogic.getLoginType(), stpLogic.getConfig().getJwtSecretKey());
        return JSON.parseObject(JSONUtil.toJsonStr(token), TokenInfo.class);
    }

    /**
     * 设置自定义Token值.
     *
     * @param token the token args details.
     * @return 自定义token的Map
     * @throws IntrospectionException the introspection exception
     */
    private static HashMap<String, Object> getTokenInfoValue (TokenInfo token) throws IntrospectionException {
        HashMap<String, Object> tokenMap = new HashMap<>();
        Class<? extends TokenInfo> clazz = token.getClass();
        //获得属性
        Field[] fields = token.getClass().getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            Method getMethod = pd.getReadMethod();
            Object res = ReflectUtil.invoke(token, getMethod.getName());
            if (!ObjectUtil.isEmpty(res)) {
                tokenMap.put(field.getName(), res);
            }
        }
        return tokenMap;
    }
}
