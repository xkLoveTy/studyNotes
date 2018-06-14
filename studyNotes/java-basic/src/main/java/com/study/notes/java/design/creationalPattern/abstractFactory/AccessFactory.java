package com.study.notes.java.design.creationalPattern.abstractFactory;

/**
 * Created by xiangke on 2016/5/25.
 */
public class AccessFactory implements IFactory {


    public IUser createUser() {
        return new AccessUser();
    }


    public IDepartment createDepartment() {
        return new AccessDepartment();
    }
}
