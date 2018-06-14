package com.study.notes.java.design.creationalPattern.prototypePattern;

/**
 * Created by xiangke on 2016/5/27.
 */
public class Resume_ShallowCopy implements Cloneable {
    private String name;
    private String sex;
    private String age;
    private String timeArea;
    private String company;


    public Resume_ShallowCopy(String name) {
        this.name = name;
    }

    public void SetPersonalInfo(String sex, String age) {
        this.sex = sex;
        this.age = age;
    }

    public void SetWorkExperience(String timeArea, String company) {
        this.timeArea = timeArea;
        this.company  = company;
    }

    public void Display() {
        System.out.printf("%s %s %s\n", name, sex, age);
        System.out.printf("工作经历: %s %s\n", timeArea, company);
    }


    protected Resume_ShallowCopy clone() {
        Object obj = null;
        try {
            obj = super.clone();
            return (Resume_ShallowCopy) obj;
        }
        catch (CloneNotSupportedException e){
            System.out.println("不支持复制");
            return null;
        }
    }
}
