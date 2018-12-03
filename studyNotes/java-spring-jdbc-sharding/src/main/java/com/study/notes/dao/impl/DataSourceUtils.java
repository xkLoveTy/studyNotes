package com.study.notes.dao.impl;

import com.study.notes.jdbc.sharding.MultidbContext;

public class DataSourceUtils {
    /**
     * 根据pin找到数据库
     * @param pin
     */
    public static void setPin4DivideDB(String pin) {
        @SuppressWarnings("rawtypes")
        MultidbContext multidbContext = MultidbContext.getInstance();
        multidbContext.setPin(pin);
        multidbContext.setMultidbContext(multidbContext);
    }
}
