package com.mk.agent.hot;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc TODO
 * @Author zhxy
 * @Version V1.0
 **/
public class HotAgent {
    // 目标程序编译路径
    public static final String PATH_PREFIX = "/Users/heronsbill/code/agent-share/zarthas-demo/target/classes";
    // 待监听的包
    public static final String CLASS_NAME_PREFIX = "com/mk/agent/arthas/service";
    // JVMTI 所创建的插桩对象
    private static Instrumentation instrumentation;

    public static Map<String, String> pathToName = new HashMap<>();
    public static Map<String, String> nameToPath = new HashMap<>();

    /**
     * 启动时执行入口
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=============================premain方法执行=======================");
        instrumentation = inst;
        inst.addTransformer(new HotClassFileTransformer(), true);

        // 注册文件监听器
        try {
            FileAlterationObserver observer = new FileAlterationObserver(String.format("%s/%s", PATH_PREFIX, CLASS_NAME_PREFIX));
            observer.addListener(new FileListener());

            FileAlterationMonitor monitor = new FileAlterationMonitor(2000);
            monitor.addObserver(observer);

            monitor.start();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

    }

    public static void reloadByFullName(String fullName) {
        for (Class loadedClass : instrumentation.getAllLoadedClasses()) {
            if (loadedClass.getName().contains(fullName.replaceAll("/", "."))) {
                try {
                    instrumentation.retransformClasses(loadedClass);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
