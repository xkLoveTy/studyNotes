package com.study.notes.java.design.behavioralPattern.chainOfResponsibilityPattern;

/**
 * Created by xiangke on 2016/5/30.
 */
public class Client {

    public static void main(String[] args) {
        CommonManager jinli = new CommonManager();
        Majordomo zongjian = new Majordomo();
        GeneralManager zongjinli = new GeneralManager();

        jinli.setSuperior(zongjian);
        zongjian.setSuperior(zongjinli);

        Request request = new Request();
        request.setRequestType("请假");
        request.setRequestContent("小菜请假");
        request.setNumber(5);

        jinli.requestApplications(request);


       /* Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();

        h1.setSucessor(h2);
        h2.setSucessor(h3);

        int[] requests = {2, 5, 14, 26, 33};

        for (int request : requests) {
            h1.handleRequest(request);
        }*/
    }

}
