package com.study.notes.java.basic.java8;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class StringJoinerExample {
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(",");
        sj.add("aaa");
        sj.add("bbb");
        sj.add("ccc");
        String result = sj.toString(); //aaa,bbb,ccc
        System.out.println(result);

        StringJoiner sj1 = new StringJoiner("/", "prefix-", "-suffix");
        sj1.add("2016");
        sj1.add("02");
        sj1.add("26");
        String result1 = sj1.toString(); //prefix-2016/02/26-suffix
        System.out.println(result1);

        List<String> list = Arrays.asList("java", "python", "nodejs", "ruby");
        //java | python | nodejs | ruby
        String result2 = list.stream().map(x -> x).collect(Collectors.joining(" | "));
        List<Game> list1 = Arrays.asList(
                new Game("Dragon Blaze", 5),
                new Game("Angry Bird", 5),
                new Game("Candy Crush", 5)
        );

        //{Dragon Blaze, Angry Bird, Candy Crush}
        String result3 = list1.stream().map(x -> x.getName())
                .collect(Collectors.joining(", ", "{", "}"));
        System.out.println(result3);

        String password = "password123";

        password.chars() //IntStream
                .mapToObj(x -> (char) x)//Stream<Character>
                .forEach(System.out::println);

    }
}
