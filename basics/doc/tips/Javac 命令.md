# 概述
介绍 javac 命令及其常见使用场景

# 作用
`javac`是 JDK 提供的编译工具，用来读取 java 类和接口定义，并将其编译为字节码和class文件

# 语法
`javac [options] [filenames]`

`options`：命令行选项  
`sourcefiles`：需要编译的 java 文件

# 常见使用场景
* 可以使用 javac 将其编译为 .class 文件
```
$ javac Main.java
$ ls
Main.java Main.class
```

* 多个类需要编译的时候，可以是用逗号分隔
```
$ ls
Main1.java Main2.java
$ javac Main1.java Main2.java
$ ls
Main1.java Main2.java Main1.class Main2.class
```

* 可以使用通配符编译同个目录下的所有 java 文件
```
$ ls
Main1.java Main2.java
$ javac *.java
$ ls
Main1.java Main2.java Main1.class Main2.class
```
* 从配置文件读取 java 类进行编译  
`javac`还支持通过读取配置文件的方式，对配置文件中的所有 java 类进行编译，命令格式为`javac @<文件名>`
```
$ cat complieFiles
Main1.java
Main2.java
Main3.java
$ javac @complieFiles
$ ls 
Main1.java Main3.java Main3.java Main1.class Main3.class Main3.class
```
* 使用`-d`参数编译带 package 的类  
假设我们有以下类
```java
package com.hxy.learning.java;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
```
尝试对以下类进行编译执行
```
$ javac Main.java
$ java Main
错误: 找不到或无法加载主类 Main
```
发现执行的时候报了无法加载主类的 error，这是因为 Main 类是定义在 com.hxy.learning.java 下的，如果要执行该类，需要将 Main.class 放到 /com/hxy/learning/java 目录下
```
$ mkdir -p ./com/hxy/learning/java 
$ mv Main.class ./com/hxy/learning/java/
$ java com.hxy.learning.java.Main
hello world 
```
> 这是因为 java 类是通过包名+类名唯一确定的  

可以在编译的时候指定`-d`参数，`javac`在编译的时候就会自动创建包结构对应的目录
```
$ javac -d . Main.java
./com/hxy/learning/java/Main.class
$ java com.hxy.learning.java.Main
hello world
```