package com.study.notes.jdbc.sharding;

import java.util.HashMap;

public class MultidbContext<K, V> extends HashMap<K, V> {
    private static ThreadLocal<MultidbContext> contextHolder = new ThreadLocal();
    private String pin;

    public MultidbContext() {
    }

    public static MultidbContext getInstance() {
        return new MultidbContext();
    }

    public void setMultidbContext(MultidbContext multidbContext) {
        contextHolder.set(multidbContext);
    }

    public MultidbContext getMultidbContext() {
        return (MultidbContext)contextHolder.get();
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
