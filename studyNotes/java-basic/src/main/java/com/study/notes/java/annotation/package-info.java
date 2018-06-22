package com.study.notes.java.annotation;
/**
 * 概念
 注解即元数据,就是源代码的元数据
 注解在代码中添加信息提供了一种形式化的方法,可以在后续中更方便的 使用这些数据
 Annotation是一种应用于类、方法、参数、变量、构造器及包声明中的特殊修饰符。它是一种由JSR-175标准选择用来描述元数据的一种工具。

 作用
 生成文档
 跟踪代码依赖性，实现替代配置文件功能,减少配置。如Spring中的一些注解
 在编译时进行格式检查，如@Override等
 每当你创建描述符性质的类或者接口时,一旦其中包含重复性的工作，就可以考虑使用注解来简化与自动化该过程。

 元注解
 自定义注解的时候用到的，也就是自定义注解的注解；（这句话我自己说的，不知道对不对）

 元注解的作用就是负责注解其他注解。Java5.0定义了4个标准的meta-annotation类型，它们被用来提供对其它 annotation类型作说明。

 Java5.0定义的4个元注解：
 @Target

 @Retention

 @Documented

 @Inherited

 @Target
 @Target说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。在Annotation类型的声明中使用了target可更加明晰其修饰的目标。

 作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
 取值(ElementType)有：

    类型	               用途
 CONSTRUCTOR	   用于描述构造器
 FIELD	           用于描述域
 LOCAL_VARIABLE	   用于描述局部变量
 METHOD	           用于描述方法
 PACKAGE	       用于描述包
 PARAMETER	      用于描述参数
 TYPE	         用于描述类、接口(包括注解类型) 或enum声明

 @Retention
 @Retention定义了该Annotation被保留的时间长短：某些Annotation仅出现在源代码中，而被编译器丢弃；
 而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，而另一些在class被装载时将被
 读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。使用这个meta-Annotation可以对
 Annotation的“生命周期”限制。

 作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）

 取值（RetentionPoicy）有：

 类型	             用途	                   说明
 SOURCE	    在源文件中有效（即源文件保留）	   仅出现在源代码中，而被编译器丢弃
 CLASS	    在class文件中有效（即class保留）	  被编译在class文件中
 RUNTIME	在运行时有效（即运行时保留）	     编译在class文件中

 @Documented
 @Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的
 工具文档化。Documented是一个标记注解，没有成员。

 作用：将注解包含在javadoc中

 @Inherited
 是一个标记注解
 阐述了某个被标注的类型是被继承的
 使用了@Inherited修饰的annotation类型被用于一个class,则这个annotation将被用于该class的子类
 @Inherited annotation类型是被标注过的class的子类所继承。类并不从实现的接口继承annotation,方法不从它所重载的方法继承annotation
 当@Inherited annotation类型标注的annotation的Retention是RetentionPolicy.RUNTIME，则反射API增强了这种继承性。如果我们使用java.lang.reflect去查询一个@Inherited annotation类型的annotation时，反射代码检查将展开工作：检查class和其父类，直到发现指定的annotation类型被发现，或者到达类继承结构的顶层。
 作用：允许子类继承父类中的注解

 注解参数的可支持数据类型:
 所有基本数据类型(int,float,double,boolean,byte,char,long,short)
 String 类型
 Class类型
 enum类型
 Annotation类型
 以上所有类型的数组

 规则:
 修饰符只能是public 或默认(default)
 参数成员只能用基本类型byte,short,int,long,float,double,boolean八种基本类型和String,Enum,Class,
 annotations及这些类型的数组
 如果只有一个参数成员,最好将名称设为”value”
 注解元素必须有确定的值,可以在注解中定义默认值,也可以使用注解时指定,非基本类型的值不可为null,常使用空字符串或0作默认值
 在表现一个元素存在或缺失的状态时,定义一下特殊值来表示,如空字符串或负值

 Java 8 中注解新特性
 @Repeatable 元注解,表示被修饰的注解可以用在同一个声明式或者类型加上多个相同的注解（包含不同的属性值）
 @Native 元注解,本地方法
 java8 中Annotation 可以被用在任何使用 Type 的地方
  /**
  //初始化对象时
  String myString = new @NotNull String();
  //对象类型转化时
  myString = (@NonNull String) str;
  //使用 implements 表达式时
  class MyList<T> implements @ReadOnly List<@ReadOnly T>{
  ...
  }
  //使用 throws 表达式时
  public void validateValues() throws @Critical ValidationFailedException{
  ...
  }

 优:
 方便，简洁，配置信息和 Java 代码放在一起，有助于增强程序的内聚性
 若要对配置项进行修改，不得不修改 Java 文件，重新编译打包应用
 缺:
 分散到各个class文件中，维护性较差
 配置项编码在 Java 文件中，可扩展性差
 与XML比较:
 简洁
 没有XML配置更强大
 不便于修改,不便于统一管理

 作者：风清723
 链接：https://www.jianshu.com/p/948549b92e0a
 來源：简书
 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 **/

