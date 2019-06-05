package com.study.notes.design.creational;

/**
 * @ClassName BuilderPattern
 * @Description 建造者模式
 * 将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。
 * @Author xiangke
 * @Date 2019/6/5 23:42
 * @Version 1.0
 **/
public class BuilderPattern {


    public static void main(String[] args) {
        FoodStore foodStore = new FoodStore();
        Meal meal = foodStore.createBreakfast(new Breakfast());
        Meal meal2 = foodStore.createBreakfast(new Lunch());
        System.out.println("小明早上吃的是:" + meal.getFood() + ",喝的饮料是:" + meal.getDrinks());
        System.out.println("小明中午吃的是:" + meal2.getFood() + ",喝的饮料是:" + meal2.getDrinks());
    }
}


class Meal {
    private String food;
    private String drinks;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }
}

interface IBuilderFood {
    void buildFood();

    void buildDrinks();

    Meal createMeal();
}

class Breakfast implements IBuilderFood {
    Meal meal;

    public Breakfast() {
        meal = new Meal();
    }

    @Override
    public void buildFood() {
        meal.setFood("煎饼");
    }

    @Override
    public void buildDrinks() {
        meal.setDrinks("豆浆");
    }

    @Override
    public Meal createMeal() {
        return meal;
    }
}

class Lunch implements IBuilderFood {
    Meal meal;

    public Lunch() {
        meal = new Meal();
    }

    @Override
    public void buildFood() {
        meal.setFood("盒饭");
    }

    @Override
    public void buildDrinks() {
        meal.setDrinks("果汁");
    }

    @Override
    public Meal createMeal() {
        return meal;
    }
}

class FoodStore {
    public Meal createBreakfast(IBuilderFood bf) {
        bf.buildDrinks();
        bf.buildFood();
        return bf.createMeal();
    }
}

