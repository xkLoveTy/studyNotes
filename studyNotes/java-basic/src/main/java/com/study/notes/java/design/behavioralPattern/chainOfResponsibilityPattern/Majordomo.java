package com.study.notes.java.design.behavioralPattern.chainOfResponsibilityPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class Majordomo extends Manager {

    void requestApplications(Request request) {
        if (request.getRequestType().equals("请假") && request.getNumber() <= 5) {
            System.out.printf("%s 数量%d 被批准", request.getRequestContent(), request.getNumber());
        } else if (superior != null) {
            superior.requestApplications(request);
        }
    }
}
