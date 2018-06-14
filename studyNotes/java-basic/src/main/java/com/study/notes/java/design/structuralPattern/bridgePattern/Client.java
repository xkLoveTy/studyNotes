package com.study.notes.java.design.structuralPattern.bridgePattern;

/**
 * Created by null on 28/5/16.
 */
public class Client {
    public static void main(String[] args) {
        HandsetBrand ab = new HandsetBrandN();
        ab.setHandsetSoft(new HandsetGame());
        ab.run();

        ab.setHandsetSoft(new HandsetAddressList());
        ab.run();

        ab = new HandsetBrandM();
        ab.setHandsetSoft(new HandsetGame());
        ab.run();

        ab.setHandsetSoft(new HandsetAddressList());
        ab.run();

    }
}
