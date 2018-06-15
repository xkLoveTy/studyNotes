package com.study.notes.java.basic.java8.stream;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        /**********************     filter   *************************/
        List<String> lines = Arrays.asList("spring", "node", "mkyong");
        List<String> result = lines.stream()              // 转化为一个流
                .filter(line -> !"mkyong".equals(line))   // 排除 'mkyong'
                .collect(Collectors.toList());            // 把输出流收集回List中

        result.forEach(System.out :: println);//输出 : spring, node

        /*******************    filter findAny().orElse  ***************/
        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
        Person result1 = persons.stream()
                .filter(x -> "jack".equals(x.getName()))
                .findAny()           // 如果找到了就返回
                .orElse(null); // 如果找不到就返回null
        System.out.println(result1);

        Person result2 = persons.stream()
                .filter(x -> "ahmook".equals(x.getName()))
                .findAny()
                .orElse(null);

        System.out.println(result2);

        /*******************   filter  多重条件指定***********************/
        Person result3 =  persons.stream()
                .filter(p -> {
                    if ("jack".equals(p.getName()) && 20 == p.getAge()) {
                        return true;
                    }
                    return false;
                }).findAny()
                .orElse(null);
        System.out.println("result 1 :" + result3);
        Person result4 =  persons.stream()
                .filter(p -> "jack".equals(p.getName()) && 20 == p.getAge())
                .findAny()
                .orElse(null);
        System.out.println("result 1 :" + result4);

        /*******************   Streams filter() 和 map() ***********************/
        String name = persons.stream()
                .filter(x -> "jack".equals(x.getName()))
                .map(Person :: getName)//把流转化为String
                .findAny()
                .orElse("");
        System.out.println("name : " + name);
        List<String> collect = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

        collect.forEach(System.out :: println);

        /*******************  filter null ***********************/
        Stream<String> language = Stream.of("java", "python", "node", null, "ruby", null, "php");
        //List<String> result8 = language.filter(x -> x != null).collect(Collectors.toList());
        //TODO 同一Stream不能用2次 stream has already been operated upon or closed
        List<String> result9 = language.filter(Objects :: nonNull).collect(Collectors.toList());
        //System.out.println("result8: ");
       // result8.forEach(System.out :: println);
        System.out.println("result9: ");
        result9.forEach(System.out :: println);
        //TODO 解决同一Stream不能用2次的问题
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("A", "B", "C", "D");
        List<String> result10 = streamSupplier.get().filter(Objects :: nonNull).collect(Collectors.toList());
        List<String> result11 = streamSupplier.get().filter(x -> x != null).collect(Collectors.toList());
        result10.forEach(System.out :: println);
        result11.forEach(System.out :: println);

        /*************************  StreamExample  map  *******************************/
        List<Staff> staff = Arrays.asList(
                new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );
        List<String> collect1 = staff.stream().map(x -> x.getName()).collect(Collectors.toList());
        System.out.println(collect1); //[mkyong, jack, lawrence]

        /****************  objects List转化为 其他 objects List  ****************/
        List<StaffPublic> result5 = staff.stream().map(temp -> {
            StaffPublic obj = new StaffPublic();
            obj.setName(temp.getName());
            obj.setAge(temp.getAge());
            if ("mkyong".equals(temp.getName())) {
                obj.setExtra("this field is for mkyong only!");
            }
            return obj;
        }).collect(Collectors.toList());
        System.out.println(result5);

        /************************  Group By, Count and Sort  *************************/
        List<String> item =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");
        Map<String, Long> result6 =
                item.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        System.out.println(result6);

        /************************  Sort Map *************************/
        Map<String, Long> finalMap = new LinkedHashMap<>();
        result6.entrySet().stream()
               .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        System.out.println(finalMap);

        /************** Group by the name + Count or Sum the Qty  **********/
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );
        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item :: getName, Collectors.counting()));
        System.out.println(counting);

        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Item :: getName, Collectors.summingInt(Item :: getQty)));
        System.out.println(sum);

        /***************** Group by Price  *************/
        //group by price
        Map<BigDecimal, List<Item>> groupByPriceMap =
                items.stream().collect(Collectors.groupingBy(Item::getPrice));
        System.out.println(groupByPriceMap);
        // group by price, uses 'mapping' to convert List<Item> to Set<String>
        Map<BigDecimal, Set<String>> result7 = items.stream().collect(
                Collectors.groupingBy(Item :: getPrice,
                    Collectors.mapping(Item :: getName, Collectors.toSet())
                )
        );
        System.out.println(result7);

        /************  Convert a StreamExample to List  *************/
        Stream<Integer> number = Stream.of(1, 2, 3, 4, 5);
        List<Integer> result12 = number.filter(x -> x!= 3).collect(Collectors.toList());
        result12.forEach(System.out :: println);

        /***********  Arrays.stream or StreamExample.of  ************/
        String[] array = {"a", "b", "c", "d", "e"};
        //Arrays.stream
        Stream<String> stream1 = Arrays.stream(array);
        stream1.forEach(System.out :: println);

        //StreamExample.of
        Stream<String> stream2 = Stream.of(array);
        stream2.forEach(System.out :: println);

        int[] intArray = {1, 2, 3, 4, 5};

        // 1. Arrays.stream -> IntStream
        IntStream intStream1 = Arrays.stream(intArray);
        intStream1.forEach(x -> System.out.println(x));

        // 2. StreamExample.of -> StreamExample<int[]>
        Stream<int[]> temp = Stream.of(intArray);

        //TODO Can't print StreamExample<int[]> directly
        //temp.forEach(x -> System.out.println(x));
        // TODO Can't print StreamExample<int[]> directly, convert / flat it to IntStream
        IntStream intStream2 = temp.flatMapToInt(x -> Arrays.stream(x));
        intStream2.forEach(x -> System.out.println(x));

        /***********  sort a map *************/
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("j", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);
        System.out.println("Original...");
        System.out.println(unsortMap);

        System.out.println("Sort By Key...");
        Map<String, Integer> resultKey = compareByKey(unsortMap);
        System.out.println(resultKey);

        System.out.println("Sort By Value...");
        Map<String, Integer> resultValue = compareByValue(unsortMap);
        System.out.println(resultValue);

        /*************  Convert List to Map  ************/
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", new Date()));
        list.add(new Hosting(2, "linode.com", new Date()));
        list.add(new Hosting(3, "digitalocean.com", new Date()));
        Map<Integer, String> result13 = list.stream().collect(
                Collectors.toMap(Hosting :: getId, Hosting :: getName));
        //Map<Integer, String> result13 = list.stream().collect(
                //Collectors.toMap(x -> x.getId(), x -> x.getName()));
        System.out.println("Result 13 : " + result1);

        /*********   Filter a Map   ************/
        Map<Integer, String> HOSTING = new HashMap<>();
        HOSTING.put(1, "linode.com");
        HOSTING.put(2, "heroku.com");
        HOSTING.put(3, "digitalocean.com");
        HOSTING.put(4, "aws.amazon.com");

        /*Map<Integer, String> collect2 =  HOSTING.entrySet().stream()
                .filter(map -> map.getKey() == 2)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        System.out.println(collect2);*/

        String resultStr = HOSTING.entrySet().stream()
                .filter(map -> map.getKey() == 2)
                .map(map -> map.getValue())
                .collect(Collectors.joining());
        System.out.println(resultStr);

       /* String resultStr = HOSTING.entrySet().stream()
                .filter(map -> "aws.amazon.com".equals(map.getValue()))
                .map(map -> map.getValue())
                .collect(Collectors.joining());
        System.out.println(resultStr);*/

        /*********   flatMap   ************/
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        Arrays.stream(data)
                .flatMap(x -> Arrays.stream(x))
                .filter(x -> "a".equals(x.toString()))
                .forEach(System.out :: println);

        Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");

        List<Student> studentList = new ArrayList<>();
        studentList.add(obj1);
        studentList.add(obj2);

        List<String> collect3 = studentList.stream()
                .map(x -> x.getBook())    //Stream<Set<String>>
                .flatMap(x -> x.stream()) //Stream<String>
                .distinct()
                .collect(Collectors.toList());
        collect3.forEach(x -> System.out.println(x));

        /*********   Convert Map to List   ************/
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");

        System.out.println("\n1. Export Map Key to List...");

        List<Integer> result14 = map.entrySet().stream()
                .map(x -> x.getKey())
                .collect(Collectors.toList());

        result14.forEach(System.out::println);

        System.out.println("\n2. Export Map Value to List...");

        List<String> result15 = map.entrySet().stream()
                .map(x -> x.getValue())
                .collect(Collectors.toList());

        result15.forEach(System.out::println);


    }

    public static <K, V extends Comparable<? super V>> Map<K, V> compareByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap();
        Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream();

        mapInStream.sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        return result;
    }

    public static <K extends Comparable<? super K>, V> Map<K, V> compareByKey(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap();
        Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream();

        mapInStream.sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        return result;
    }
}
