package com.study.notes.java.basic.java8.optional;

import java.util.Optional;
import java.util.Random;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> gender = Optional.of("MALE");
        String answer1 = "Yes";
        String answer2 = null;

        System.out.println("Non-Empty Optional:" + gender);
        System.out.println("Non-Empty Optional: Gender value : " + gender.get());
        System.out.println("Empty Optional: " + Optional.empty());

        System.out.println("ofNullable on Non-Empty Optional: " + Optional.ofNullable(answer1));
        System.out.println("ofNullable on Empty Optional: " + Optional.ofNullable(answer2));

        // java.lang.NullPointerException
        //System.out.println("ofNullable on Non-Empty Optional: " + Optional.of(answer2));

        /**************   Optional.map and flatMap  ********************/
        Optional<String> nonEmptyGender = Optional.of("male");
        Optional<String> emptyGender = Optional.empty();

        System.out.println("Non-Empty Optional:: " + nonEmptyGender.map(String::toUpperCase));
        System.out.println("Empty Optional    :: " + emptyGender.map(String::toUpperCase));

        Optional<Optional<String>> nonEmptyOtionalGender = Optional.of(Optional.of("male"));
        System.out.println("Optional value   :: " + nonEmptyOtionalGender);
        System.out.println("Optional.map     :: " + nonEmptyOtionalGender.map(gender1 -> gender.map(String::toUpperCase)));
        System.out.println("Optional.flatMap :: " + nonEmptyOtionalGender.flatMap(gender1 -> gender.map(String::toUpperCase)));

        /**************   Optional.filter  ********************/
        Optional<String> gender1 = Optional.of("MALE");
        Optional<String> emptyGender1 = Optional.empty();

        //Filter on Optional
        System.out.println(gender1.filter(g -> g.equals("male"))); //Optional.empty
        System.out.println(gender1.filter(g -> g.equalsIgnoreCase("MALE"))); //Optional[MALE]
        System.out.println(emptyGender1.filter(g -> g.equalsIgnoreCase("MALE"))); //Optional.empty

        /**************   Optional isPresent and ifPresent  ********************/
        if (gender1.isPresent()) {
            System.out.println("Value available.");
        } else {
            System.out.println("Value not available.");
        }
        gender1.ifPresent(g -> System.out.println("In gender Option, value available."));
        //condition failed, no output print
        emptyGender1.ifPresent(g -> System.out.println("In emptyGender Option, value available."));

        /**************   Optional orElse methods  ********************/
        System.out.println(gender1.orElse("<N/A>")); //MALE
        System.out.println(emptyGender1.orElse("<N/A>")); //<N/A>

        System.out.println(gender1.orElseGet(() -> "<N/A>")); //MALE
        System.out.println(emptyGender1.orElseGet(() -> "<N/A>")); //<N/A>

        //Generates random integers in a range between 33 (inclusive) and 38 (exclusive)
        // , with stream size of 10. And print out the items with forEach.
        new Random().ints(10, 33, 38).forEach(System.out::println);
    }
}
