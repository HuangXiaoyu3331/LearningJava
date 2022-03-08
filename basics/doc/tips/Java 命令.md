# 概述
记录一些常用的 java 命令参数，及其使用场景

## 语法
1. `java [options] classname [args]`
2. `java [options] -jar filename [args]`

`options`：命令行选项，多个选项通过空格分割  
`classname`：需要执行的类的名字  
`filename`：jar 文件的名字（路径），只有在 java -jar 中才会使用到  
`args`：传递给 main 方法的参数（String[] args），多个参数用空格分割  

## java 命令作用
`java` 命令用来启动 java 程序，执行 java 命令时，会启动 JRE，加载主类并调用该类的 main 方法  

## 常见使用场景
* 执行 Java 类  
在我们刚学习 java 的时候，就是使用该方式启动 java 程序的，比如我们有以下类
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
```
使用 javac 编译该类  
```
$ javac Main.java
```
接着使用 java 命令启动执行该类  
```
$ java Main
hello world
```

* 执行 jar  
java 中可以使用`java -jar filename`或`java -cp filename classname`两种方式来运行 jar，比如我们可以使用以下两种方式来运行 test.jar：  
```
$ java -jar test.jar
$ java -cp test.jar com.hxy.learning.java.Main
```
> com.hxy.learning.java.Main 是入口类，即 mian 方法所在类

这两种启动方式的区别是，使用`java -jar`需要我们在打包 jar 的时候指定入口类的位置，在 pom 文件的 `maven-jar-plugin` 中配置
```xml
<build>
    <finalName>agent</finalName>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <configuration>
                <archive>
                    <manifest>
                        <mainClass>com.hxy.learning.java.agent.HelloMain</mainClass>
                    </manifest>
                </archive>
            </configuration>
        </plugin>
    </plugins>
</build>
```

使用`java -cp`的方式，则不需要在打包的时候指定入口类的位置，但需要我们在 java 命令中指定入口类的位置，不仅如此，`java -cp`运行 jar 的时候，可以不用将所有依赖都打包到 test.jar 中，我们只需要在运行的时候指定所要用到的 jar 即可，多个 jar 用`:`分割(Windows 系统用`;`分割)，假设 test.jar 运行时需要依赖 log4j.jar，则命令可以写为
```
$ java -cp log4j.jar:test.jar com.hxy.learning.java.Main
```
> 参考：https://juejin.cn/post/7028861376353271816


* 使用`-D<name>=<value>`设置系统属性  
执行程序时，使用`-D`参数设置的系统变量，可以在代码中通过`System.getProperty(String key)`读取出来
```java
public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("name"));
    }
}
```
使用`-D`参数设置 name=huangxy，程序执行打印 huangxy
```
$ javac Main.java && java -Dname=huangxy Main
huangxy
```

* 指定 JVM 参数  
线上环境一般都要指定 JVM 的启动参数，如
```
$ java -jar test.jar -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -Xms128M -Xmx256M
```

* 设置 JVM 参数  
可以在启动 java 程序的时候设置 JVM 的参数，如`-Xms250M -Xmx250M`设置 JVM 的最小最大堆内存  

> 参考
> [java -jar 和 java -cp 的区别](https://juejin.cn/post/7028861376353271816)
> [java 命令详解](https://ningg.top/java-command/)
> [Java document - Launches a Java application](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html#BABDJJFI)