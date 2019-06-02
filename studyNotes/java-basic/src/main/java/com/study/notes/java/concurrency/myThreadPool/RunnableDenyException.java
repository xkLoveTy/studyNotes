package com.study.notes.java.concurrency.myThreadPool;

public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String message) {
        super(message);
    }
}
