package com.study.notes.dao.impl;

import java.util.HashMap;
import java.util.Map;

public class DaoUtils {

    public static Integer indexOf(String pin, Integer shardsNumber) {

        if (shardsNumber != null && shardsNumber > 1) {
            return Math.abs(pin.hashCode()) % shardsNumber;
        }
        return 0;
    }

    /**
     * 获取pin路由到指定索引的数据表中
     */
    public static Map<String, Object> getTableShards(String pin, Integer shardsNumber) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tableIndex", DaoUtils.indexOf(pin, shardsNumber));
        return params;
    }
}
