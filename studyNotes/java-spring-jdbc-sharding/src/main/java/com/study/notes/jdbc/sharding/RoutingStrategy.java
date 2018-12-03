package com.study.notes.jdbc.sharding;

public abstract  class RoutingStrategy {
    public RoutingStrategy() {
    }

    protected abstract Object determine();

}
