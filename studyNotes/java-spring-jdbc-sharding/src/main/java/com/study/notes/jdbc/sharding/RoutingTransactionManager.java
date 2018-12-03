package com.study.notes.jdbc.sharding;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

import java.util.HashMap;
import java.util.Map;

public class RoutingTransactionManager implements PlatformTransactionManager {
    private Map<Object, PlatformTransactionManager> targetTransactionManagers = new HashMap();
    private RoutingStrategy routingStrategy;

    public RoutingTransactionManager() {
    }

    protected PlatformTransactionManager getTargetTransactionManager() {
        Object obj = this.routingStrategy.determine();
        return (PlatformTransactionManager)this.targetTransactionManagers.get(obj);
    }

    public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
        return this.getTargetTransactionManager().getTransaction(transactionDefinition);
    }

    public void commit(TransactionStatus transactionStatus) throws TransactionException {
        this.getTargetTransactionManager().commit(transactionStatus);
    }

    public void rollback(TransactionStatus transactionStatus) throws TransactionException {
        this.getTargetTransactionManager().rollback(transactionStatus);
    }

    public void setTargetTransactionManagers(Map<Object, PlatformTransactionManager> targetTransactionManagers) {
        this.targetTransactionManagers = targetTransactionManagers;
    }

    public void setRoutingStrategy(RoutingStrategy routingStrategy) {
        this.routingStrategy = routingStrategy;
    }
}
