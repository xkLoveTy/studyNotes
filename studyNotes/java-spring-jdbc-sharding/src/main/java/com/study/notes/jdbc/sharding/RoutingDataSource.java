package com.study.notes.jdbc.sharding;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private static Logger logger = Logger.getLogger(RoutingDataSource.class);
    private RoutingStrategy routingStrategy;

    public RoutingDataSource() {
    }

    protected Object determineCurrentLookupKey() {
        Object obj = this.routingStrategy.determine();
        if (obj == null) {
            logger.error("Getting the DataSource is null!");
        }

        return obj;
    }

    public void setRoutingStrategy(RoutingStrategy routingStrategy) {
        this.routingStrategy = routingStrategy;
    }

}
