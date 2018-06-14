package com.study.notes.java.design.creationalPattern.abstractFactory;

/**
 * Created by xiangke on 2016/5/25.
 */
public class SqlserverFactory implements IFactory {


    public IUser createUser() {
        return new SqlServerUser();
    }


    public IDepartment createDepartment() {
        return new SqlServerDepartment();
    }
}
