package com.study.notes.jdbc.sharding;

import org.springframework.util.StringUtils;

public class PinRoutingStrategy extends RoutingStrategy {
    private String dbName;
    private int dbNum;

    public PinRoutingStrategy() {
    }

    protected Object determine() {
        MultidbContext context = MultidbContext.getInstance().getMultidbContext();
        if (context != null && !StringUtils.isEmpty(context.getPin())) {
            String pin = context.getPin();
            if (pin == null) {
                return -1;
            } else {
                int hash = pin.hashCode();
                int inx = Math.abs(hash) % this.dbNum;
                return this.dbName + "_" + (inx > 0 ? inx : -inx) ;
            }
        } else {
            return -1;
        }
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setDbNum(int dbNum) {
        this.dbNum = dbNum;
    }
}
