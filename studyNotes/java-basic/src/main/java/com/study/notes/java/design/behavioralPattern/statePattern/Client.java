package com.study.notes.java.design.behavioralPattern.statePattern;

/**
 * Created by xiangke on 2016/6/2.
 */
public class Client {
    public static void main(String[] args) {
        Work emergencyProjects = new Work();
        emergencyProjects.setHour(9);
        emergencyProjects.writeProgram();

        emergencyProjects.setHour(12);
        emergencyProjects.writeProgram();

        emergencyProjects.setHour(15);
        emergencyProjects.writeProgram();

        emergencyProjects.setHour(19);
        emergencyProjects.writeProgram();

        emergencyProjects.setHour(22);
        emergencyProjects.writeProgram();



        /*Context context = new Context(new ConcreteStateA());

        context.request();
        context.request();
        context.request();
        context.request();*/
    }
}
