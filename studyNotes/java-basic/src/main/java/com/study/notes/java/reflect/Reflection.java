package com.study.notes.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

public class Reflection {
    public static void main(String[] args) throws Exception {
        /****************获取Class的方法**************/
        Class class1 = Reflection.class;
        System.out.println(class1.getName());

        Reflection reflection = new Reflection();
        Class c2 = reflection.getClass();
        System.out.println(c2.getName());

        Class c3 = Class.forName("com.study.notes.java.reflect.Reflection");
        System.out.println(c3.getName());

        /******获取成员方法信息**********/
        /** 两个参数分别是方法名和方法参数类的类类型列表
         * public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
         * // 得到该类所有的方法，不包括父类的
           public Method getMethod(String name, Class<?>... parameterTypes)
         * // 得到该类所有的public方法，包括父类的
         * */
        Class c = Class.forName("com.study.notes.java.reflect.Person");//先生成class
        Object o = c.newInstance();//newInstance可以初始化一个实例
        Method method = c.getMethod("fun", String.class, int.class);//获取方法
        method.invoke(o, "tengj", 10); //通过invoke调用该方法，参数第一个为实例对象，后面为具体参数值

        Method[] methods = c.getDeclaredMethods();// 得到该类所有的方法，不包括父类的
        Arrays.stream(methods).forEach(method1 -> System.out.println(method1.getName()));

        Method[] allMethods = c.getMethods();// 得到该类所有的public方法，包括父类的
        Arrays.stream(allMethods).forEach(method1 -> System.out.println(method1.getName()));

        /********** 获取成员变量信息 **********/
        /**
         * public Field getDeclaredField(String name) // 获得该类自身声明的所有变量，不包括其父类的变量
           public Field getField(String name) // 获得该类自所有的public成员变量，包括其父类变量
         */
        //获取成员变量
        Field field = c.getDeclaredField("msg"); //因为msg变量是private的，所以不能用getField方法
        field.setAccessible(true);//设置是否允许访问，因为该变量是private的，所以要手动设置允许访问，如果msg是public的就不需要这行了。
        Object obj = field.get(o);
        System.out.println(obj);

        Field[] fields = c.getDeclaredFields();//获取所有成员变量的信息
        Stream.of(fields).forEach(field1 -> System.out.println(field1.getName()));


        /********* 获取构造函数 ***********/
        /**
         * public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) //  获得该类所有的构造器，不包括其父类的构造器
           public Constructor<T> getConstructor(Class<?>... parameterTypes) // 获得该类所以public构造器，包括父类
         */
        Constructor constructor = c.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);//设置是否允许访问，因为该构造器是private的，所以要手动设置允许访问，如果构造器是public的就不需要这行了。
        constructor.newInstance("str");

        Constructor[] constructors = c.getDeclaredConstructors();
        Stream.of(constructors).forEach(constructor1 -> System.out.println(constructor1));

        /********* 注解 *********/
        /**
         * Annotation[] annotations = (Annotation[]) class1.getAnnotations();//获取class对象的所有注解
           Annotation annotation = (Annotation) class1.getAnnotation(Deprecated.class);//获取class对象指定注解
           Type genericSuperclass = class1.getGenericSuperclass();//获取class对象的直接超类的
           Type Type[] interfaceTypes = class1.getGenericInterfaces();//获取class对象的所有接口的type集合

         boolean isPrimitive = class1.isPrimitive();//判断是否是基础类型
         boolean isArray = class1.isArray();//判断是否是集合类
         boolean isAnnotation = class1.isAnnotation();//判断是否是注解类
         boolean isInterface = class1.isInterface();//判断是否是接口类
         boolean isEnum = class1.isEnum();//判断是否是枚举类
         boolean isAnonymousClass = class1.isAnonymousClass();//判断是否是匿名内部类
         boolean isAnnotationPresent = class1.isAnnotationPresent(Deprecated.class);//判断是否被某个注解类修饰
         String className = class1.getName();//获取class名字 包含包名路径
         Package aPackage = class1.getPackage();//获取class的包信息
         String simpleName = class1.getSimpleName();//获取class类名
         int modifiers = class1.getModifiers();//获取class访问权限
         Class<?>[] declaredClasses = class1.getDeclaredClasses();//内部类
         Class<?> declaringClass = class1.getDeclaringClass();//外部类

         getSuperclass()：获取某类的父类
         getInterfaces()：获取某类实现的接口
         */
        String className = class1.getName();//获取class名字 包含包名路径
        Package aPackage = class1.getPackage();//获取class的包信息
        String simpleName = class1.getSimpleName();//获取class类名
        int modifiers = class1.getModifiers();//获取class访问权限
        String modifier = Modifier.toString(modifiers);

        System.out.println("className: " + className
                            + ", aPackage: " + aPackage
                            + ", simpleName: " + simpleName
                            + ", modifiers: " + modifier);
    }
}
