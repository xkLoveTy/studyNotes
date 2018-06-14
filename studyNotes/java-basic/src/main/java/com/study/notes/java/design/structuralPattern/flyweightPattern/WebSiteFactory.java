package com.study.notes.java.design.structuralPattern.flyweightPattern;

import java.util.HashMap;

/**
 * Created by null on 29/5/16.
 */
public class WebSiteFactory {
    private HashMap flyweights = new HashMap();

    public WebSite getWebSiteCategory(String key) {
        if (!flyweights.containsKey(key))
            flyweights.put(key, new ConcreteWebSite(key));
        return (WebSite) flyweights.get(key);
    }

    public int getWebSiteCount() {
        return flyweights.size();
    }
}
