package com.mk.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @Desc agent 主类
 * @Author zhxy
 * @Version V1.0
 **/
public class AgentDemo {

    /**
     * 启动时执行入口
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=============================premain方法执行=======================");
        inst.addTransformer(new MyClassFileTransformer());
    }


    /**
     * 运行时执行入口
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("=============================agentmain方法执行=======================");
        inst.addTransformer(new MyClassFileTransformer(), true);

        /**
         * transformer是会对尚未加载的类进行增加代理层，这里是已经运行中的jvm，所以类已经被加载了，
         * 必须主动调用 retransformClasses 让jvm再对运行中的类进行加上代理层
         */
        for (Class loadedClass : inst.getAllLoadedClasses()) {
            if (loadedClass.getName().contains("com.mk.agent.web.demo.TransClass")) {
                System.out.println(loadedClass.getName());
                try {
                    inst.retransformClasses(loadedClass);
                } catch (UnmodifiableClassException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
