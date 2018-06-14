package com.study.notes.java.design.creationalPattern.prototypePattern;

import java.io.*;

/**
 * Created by xiangke on 2016/5/27.
 */
public class Resume_DeepCopy implements Serializable {


    private String name;
    private String sex;
    private String age;
    private WorkExperience work;


    public Resume_DeepCopy(String name) {
        this.name = name;
        work      = new WorkExperience();
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
        ObjectInputStream ois  = null;
        Resume_DeepCopy resume_deepCopy = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("a.txt"));
            oos.writeObject(this);

            ois = new ObjectInputStream(new FileInputStream("a.txt"));
            resume_deepCopy = (Resume_DeepCopy)ois.readObject();

            oos.close();
            ois.close();
        }
        catch (Throwable t) {
            System.out.println("不支持复制");
        }

        return resume_deepCopy;
    }

}
