package com.study.notes.java.design.creationalPattern.abstractFactory;

/**
 * Created by xiangke on 2016/5/25.
 */
public interface IFactory {
    public IUser createUser();
    public IDepartment createDepartment();
}
