package com.study.notes.java.design.structuralPattern.flyweightPattern;

/**
 * Created by null on 29/5/16.
 */
public class Client {
    public static void main(String[] args) {
        WebSiteFactory f = new WebSiteFactory();

        WebSite fx = f.getWebSiteCategory("产品展示");
        fx.use(new User("小菜"));

        WebSite fy = f.getWebSiteCategory("产品展示");
        fx.use(new User("大鸟"));

        WebSite fz = f.getWebSiteCategory("产品展示");
        fx.use(new User("娇娇"));

        WebSite f1 = f.getWebSiteCategory("博客");
        f1.use(new User("小菜"));

        WebSite f2 = f.getWebSiteCategory("博客");
        f2.use(new User("大鸟"));

        WebSite f3 = f.getWebSiteCategory("博客");
        f3.use(new User("娇娇"));

        System.out.printf("网站分类总数为%d\n", f.getWebSiteCount());



        //int extrinsicstate = 22;

        /*FlyweightFactory f = new FlyweightFactory();

        Flyweight fx = f.getFlyweight("X");
        fx.operation(--extrinsicstate);

        Flyweight fy = f.getFlyweight("Y");
        fy.operation(--extrinsicstate);

        Flyweight fz = f.getFlyweight("Z");
        fz.operation(--extrinsicstate);

        UnsharedConcreteFlyweight uf = new UnsharedConcreteFlyweight();
        uf.operation(--extrinsicstate);*/
    }
}
