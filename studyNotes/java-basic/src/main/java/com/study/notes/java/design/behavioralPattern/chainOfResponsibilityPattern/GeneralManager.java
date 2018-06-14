package com.study.notes.java.design.behavioralPattern.chainOfResponsibilityPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class GeneralManager extends Manager {

    void requestApplications(Request request) {
        if (request.getRequestType().equals("请假")) {
            System.out.printf("%s 数量%d 被批准", request.getRequestContent(), request.getNumber());
        } else if (request.getRequestType().equals("加薪") && request.getNumber() <= 500) {
            System.out.printf("%s 数量%d 被批准", request.getRequestContent(), request.getNumber());
        } else if (request.getRequestType().equals("加薪") && request.getNumber() > 500) {
            System.out.printf("%s 数量%d 在说吧", request.getRequestContent(), request.getNumber());
        }
    }
}
