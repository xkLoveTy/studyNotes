package com.study.notes.java.annotation;

import java.lang.reflect.Field;
import java.util.Arrays;

public class FruitInfoUtil {
    public static void getFruitInfo(Class<?> clazz) {

        final String[] strFruitName = {" 水果名称："};
        final String[] strFruitColor = {" 水果颜色："};
        final String[] strFruitProvicer = {"供应商信息："};

        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                strFruitName[0] = strFruitName[0] + fruitName.value();
                System.out.println(strFruitName[0]);
            } else if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor[0] = strFruitColor[0] + fruitColor.fruitColor().toString();
                System.out.println(strFruitColor[0]);
            } else if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer[0] = " 供应商编号：" + fruitProvider.id() + " 供应商名称：" + fruitProvider.name() + " 供应商地址：" + fruitProvider.address();
                System.out.println(strFruitProvicer[0]);
            }
        });
    }

    public static void main(String[] args) {
        getFruitInfo(Apple.class);
    }
}
