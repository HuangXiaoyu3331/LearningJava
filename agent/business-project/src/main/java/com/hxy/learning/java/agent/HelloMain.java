package com.hxy.learning.java.agent;

/**
 * @author huangxy
 * @date 2022/03/05
 */
public class HelloMain {

    /**
     * 如果在打包的时候指定该类为主类，可以使用以下命令运行 agent
     * java -javaagent:/Users/huangxy/work/IdeaWorkspace/study/LearningJava/agent/target/agent.jar=huangxy -jar target/agent.jar
     *
     * 如果没将该类指定为主类，则可以使用下面命令运行
     * java -javaagent:/Users/huangxy/work/IdeaWorkspace/study/LearningJava/agent/target/agent.jar=huangxy -cp . com.hxy.learning.java.agent.HelloMain
     *
     * [huangxy] 是一个输入参数，可以随意填写，可以在 HelloAgent 类的 premain 方法中获取该参数
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("[Application] hello world");
    }

}
