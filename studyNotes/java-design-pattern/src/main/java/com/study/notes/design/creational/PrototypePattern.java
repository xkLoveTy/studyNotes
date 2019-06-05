package com.study.notes.design.creational;

import java.io.*;

/**
 * @ClassName PrototypePattern
 * @Description 原型模式
 * 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 * 所谓原型模式，就是java中的克隆技术，以某个对象为原型。复制出新的对象。显然新的对象具备原型对象的特点。
 * 1、实现克隆操作，在 JAVA 继承 Cloneable，重写 clone()。
 * 2、原型模式同样用于隔离类对象的使用者和具体类型（易变类）之间的耦合关系，它同样要求这些"易变类"拥有稳定的接口。
 * @Author xiangke
 * @Date 2019/6/5 23:33
 * @Version 1.0
 **/
public class PrototypePattern {

    public static void main(String[] args) {
        //浅复制
        WorkExperience work = new WorkExperience("1998-2000", "XX公司");

        Resume_ShallowCopy a = new Resume_ShallowCopy("大鸟");
        a.SetPersonalInfo("男", "29");
        a.SetWorkExperience(work);

        Resume_ShallowCopy b = a.clone();
        work.setWorkDate("1998-2006");
        work.setCompany("YY企业");

        Resume_ShallowCopy c = a.clone();
        c.SetPersonalInfo("男", "24");

        a.Display();
        b.Display();
        c.Display();

        System.out.println();
        System.out.println("深复制：");
        //深复制
        Resume_DeepCopy ad = new Resume_DeepCopy("大鸟");
        ad.SetPersonalInfo("男", "29");
        ad.SetWorkExperience("1998-2000", "XXX公司");

        Resume_DeepCopy bd = ad.deep_clone();
        bd.SetWorkExperience("1998-2006", "YY企业");

        Resume_DeepCopy cd = ad.deep_clone();
        cd.SetWorkExperience("2000-2013", "ZZ企业");

        ad.Display();
        bd.Display();
        cd.Display();
    }
}

/**
 * 签拷贝
 */
class Resume_ShallowCopy implements Cloneable {
    private String name;
    private String sex;
    private String age;
    private WorkExperience work;


    public Resume_ShallowCopy(String name) {
        this.name = name;
        this.work = new WorkExperience();
    }

    public void SetPersonalInfo(String sex, String age) {
        this.sex = sex;
        this.age = age;
    }

    public void SetWorkExperience(WorkExperience work) {
        this.work = work;
    }

    public void Display() {
        System.out.printf("%s %s %s\n", name, sex, age);
        System.out.printf("工作经历: %s %s\n", work.getWorkDate(), work.getCompany());
    }

    protected Resume_ShallowCopy clone() {
        Object obj = null;
        try {
            obj = super.clone();
            return (Resume_ShallowCopy) obj;
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制");
            return null;
        }
    }
}


/**
 * 序列化实现深拷贝
 */
class Resume_DeepCopy implements Serializable {

    private String name;
    private String sex;
    private String age;
    private WorkExperience work;


    public Resume_DeepCopy(String name) {
        this.name = name;
        work = new WorkExperience();
    }

    public void SetPersonalInfo(String sex, String age) {
        this.sex = sex;
        this.age = age;
    }

    public void SetWorkExperience(String workDate, String company) {
        work.setWorkDate(workDate);
        work.setCompany(company);
    }

    public void Display() {
        System.out.printf("%s %s %s\n", name, sex, age);
        System.out.printf("工作经历: %s %s\n", work.getWorkDate(), work.getCompany());
    }

    protected Resume_DeepCopy deep_clone() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Resume_DeepCopy resume_deepCopy = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("a.txt"));
            oos.writeObject(this);

            ois = new ObjectInputStream(new FileInputStream("a.txt"));
            resume_deepCopy = (Resume_DeepCopy) ois.readObject();

            oos.close();
            ois.close();
        } catch (Throwable t) {
            System.out.println("不支持复制");
        }

        return resume_deepCopy;
    }

}

class WorkExperience implements Serializable {

    private String workDate;
    private String company;

    public WorkExperience() {
    }

    public WorkExperience(String workDate, String company) {
        this.workDate = workDate;
        this.company = company;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
