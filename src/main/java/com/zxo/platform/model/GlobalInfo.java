package com.zxo.platform.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: GlobalInfo
 * @Author: zzzxxxooo
 * @Date: 2022/6/21 17:27
 * @Note:
 */
@Data
public class GlobalInfo implements Serializable {
    private Long spendTime;
    private Object affectedRows;
    private String sqlString;
    private String errorMessage;
    private String tag;

    public GlobalInfo () {
    }

    public GlobalInfo (String errorMessage, String tag) {
        this.errorMessage = errorMessage;
        this.tag = tag;
    }

    public GlobalInfo (Long spendTime, Object affectedRows, String sqlString, String tag) {
        this.spendTime = spendTime;
        this.affectedRows = affectedRows;
        this.sqlString = sqlString;
        this.tag = tag;
    }

    public String getSpendTime () {
        if (this.spendTime >= 1000 && this.spendTime < 60000) {
            return String.format("%.2f", (float) spendTime / 1000) + "秒";   // Returns a formatted string using the specified format string and arguments.
        } else if (spendTime >= 60000 && spendTime < 3600000) {
            return String.format("%.2f", (float) spendTime / 60000) + "分钟";   // Returns a formatted string using the specified format string and arguments.
        } else if (spendTime > 3600000) {
            return String.format("%.2f", (float) spendTime / 3600000) + "小时";   // Returns a formatted string using the specified format string and arguments.
        } else {
            return spendTime + "毫秒";
        }
    }
}


