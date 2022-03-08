package com.hxy.learning.java.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author huangxy
 * @date 2022/03/05
 */
public class HelloAgent {

    /**
     * 当 premain(String agent) 方法与 premain(String agent,Instrumentation instrumentation) 方法同时存在时，
     * 会执行 premain(String agent, Instrumentation instrumentation) 方法
     */
    public static void premain(String agentArgs) {
        System.out.println("agent premain1:" + agentArgs);
    }

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("agent premain2:" + agentArgs);
    }

}
