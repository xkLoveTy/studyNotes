package com.study.notes.java.basic.java8;

import java.util.ArrayList;
import java.util.List;

public class Game {
    String name;
    int ranking;

    public Game(String name, int ranking) {
        this.name = name;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public static void main(String[] args) {
        List<Long> orderList = new ArrayList();

        orderList.add(new Long("814836761000521"));
        orderList.add(new Long("807311837000014"));

        orderList.add(new Long("814548376001621"));
        orderList.add(new Long("814544385000822"));

        orderList.add(new Long("814556285000021"));
        orderList.add(new Long("814549781000722"));

        orderList.add(new Long("814555852001521"));
        orderList.add(new Long("814556337000541"));

        orderList.add(new Long("814555884001821"));
        orderList.add(new Long("814556559001241"));

        orderList.stream().forEach(orderId -> {
            int hashCode = orderId.hashCode();
            int task_iterm_id = (hashCode & 0x7fffffff) % 50;
            System.out.println("orderId: " + orderId
                            + ", hashCode: " + hashCode
                            + ", task_iterm_id: " + task_iterm_id);
        });
    }
}
