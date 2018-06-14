package com.study.notes.java.design.creationalPattern.abstractFactory;

/**
 * Created by xiangke on 2016/5/25.
 */
public class Client {
    public static void main(String[] args) {
        IFactory factory = new AccessFactory();
        IUser user = factory.createUser();
        user.Insert();
        user.getUser();

        IDepartment department = factory.createDepartment();
        department.Insert();
        department.getDepartMent();
    }
}
