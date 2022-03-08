package com.hxy.learning.java.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author huangxy
 * @date 2022/03/08
 */
public class TransformAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("[Agent] In premain method");
        String className = "com.hxy.learning.java.agent.HelloMain";
        transformClass(className, instrumentation);
    }

    public static void transformClass(String className, Instrumentation instrumentation) {
        Class<?> targetClass;
        ClassLoader classLoader;

        try {
            targetClass = Class.forName(className);
            classLoader = targetClass.getClassLoader();
            transform(targetClass, classLoader, instrumentation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void transform(Class<?> targetClass, ClassLoader classLoader, Instrumentation instrumentation) {
        MyTransformer transformer = new MyTransformer(targetClass.getName(), classLoader);
        instrumentation.addTransformer(transformer,true);
        try {
            instrumentation.retransformClasses(targetClass);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException("Transform failed for: [" + targetClass.getName() + "]", e);
        }
    }

}
