package com.study.notes.java.design.behavioralPattern.chainOfResponsibilityPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class ConcreteHandler3 extends Handler {

    void handleRequest(int request) {
        if (request >= 20 && request < 40) {
            System.out.printf("%s 处理请求 %d\n", this.getClass().getName(), request);
        } else if (sucessor != null){
            sucessor.handleRequest(request);
        }
    }
}
