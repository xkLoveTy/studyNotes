package com.study.notes.jdbc.sharding;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal();
    private String defaultDataSource;

    public DataSourceContextHolder() {
    }

    public String get() {
        return (String)contextHolder.get();
    }

    public void set(String dataSource) {
        contextHolder.set(dataSource);
    }

    public void clear() {
        contextHolder.remove();
    }

    public void setDefaultDataSource(String defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }
}
