package com.study.notes.jdbc.sharding;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private DataSourceContextHolder dataSourceContextHolder;

    public DynamicDataSource() {
    }

    protected Object determineCurrentLookupKey() {
        return this.dataSourceContextHolder.get();
    }

    public void setDataSourceContextHolder(DataSourceContextHolder dataSourceContextHolder) {
        this.dataSourceContextHolder = dataSourceContextHolder;
    }
}
