package com.study.notes.java.design.creationalPattern.prototypePattern;

/**
 * Created by xiangke on 2016/5/27.
 */
public class Client {

    public static void main(String[] args) {
        //浅复制
        Resume_ShallowCopy a = new Resume_ShallowCopy("大鸟");
        a.SetPersonalInfo("男", "29");
        a.SetWorkExperience("1998-2000", "XX公司");

        Resume_ShallowCopy b = a.clone();
        b.SetWorkExperience("1998-2006", "YY企业");

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

        Resume_DeepCopy  bd = ad.deep_clone();
        bd.SetWorkExperience("1998-2006", "YY企业");

        Resume_DeepCopy  cd = ad.deep_clone();
        cd.SetWorkExperience("2000-2013", "ZZ企业");

        ad.Display();
        bd.Display();
        cd.Display();
    }
}
