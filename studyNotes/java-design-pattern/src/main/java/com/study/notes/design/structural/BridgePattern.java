package com.study.notes.design.structural;

/**
 * @ClassName BridgePattern
 * @Description 将抽象部分与实现部分分离，使它们都可以独立的变化。
 * @Author xiangke
 * @Date 2019/6/12 22:52
 * @Version 1.0
 **/
public class BridgePattern {

    public static void main(String[] args) {
        Paper paper = new ExaminationPaper();
        paper.setPen(new RedPen());
        paper.writing();

        Paper paper2 = new NewsPaper();
        paper2.setPen(new BlackPen());
        paper2.writing();

    }
}

interface Pen {
    void write();
}

class RedPen implements Pen {
    @Override
    public void write() {
        System.out.println("红色的字");
    }
}

class BlackPen implements Pen {
    @Override
    public void write() {
        System.out.println("黑色的字");
    }
}


abstract class Paper {
    protected Pen pen;

    void setPen(Pen pen) {
        this.pen = pen;
    }

    abstract void writing();
}


class ExaminationPaper extends Paper {
    @Override
    void writing() {
        pen.write();
    }
}

class NewsPaper extends Paper {
    @Override
    void writing() {
        pen.write();
    }
}
