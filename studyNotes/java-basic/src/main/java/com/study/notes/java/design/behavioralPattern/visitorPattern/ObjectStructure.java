package com.study.notes.java.design.behavioralPattern.visitorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangke on 2016/6/2.
 */
public class ObjectStructure {
    private List<Element> elements = new ArrayList<Element>();

    public void attach(Element element) {
        elements.add(element);
    }

    public void detach(Element element) {
        elements.remove(element);
    }

    public void accept(Visitor visitor) {
        for (Element e : elements) {
            e.accept(visitor);
        }
    }
}
